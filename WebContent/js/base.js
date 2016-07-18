/**
 * 
 */

var ws;
var ws_url;
var isCam = false;

function ws_wait(callback)
{
	console.log("here wait");
	if (ws.readyState == 1)
		callback();
	else setTimeout(function()
	{
		ws_wait(callback)
	}, 200);
}

function ws_open()
{
	console.log("ws try open");
	ws = new WebSocket(ws_url);
	ws.onmessage = function(event)
	{
		$("#ws_msg").append(event.data + "<br>");
		var op = JSON.parse(event.data);
		main(op);
	};
	ws.onclose = function(event)
	{
		console.log("Server Closed!");
		setTimeout(ws_open, 1000);
	};
	ws.onopen = function(event)
	{
		console.log("ws opened");
	};
	ws.onerror = function(event)
	{
		console.log("Server Errored!");
	};
	return;
}

function ws_send(data)
{
	ws_wait(function()
	{
		ws.send(data);
	})
}

function send(obj)
{
	var data = JSON.stringify(obj);
	ws_wait(function()
	{
		ws.send(data);
	})
}

function sendpost(obj)
{
	$.ajax(
	{
		type : "POST",
		url : "host",
		data : JSON.stringify(obj)
	});
}

function init()
{
	if (!window.WebSocket)
	{
		alert("WebSocket not supported by this browser!");
		return;
	}
	ws_url = "ws://" + window.location.host;
	var part = window.location.pathname;
	ws_url += part.substr(0, part.lastIndexOf('/'));
	ws_url += "/ws/host";
	ws_open();
	ws_send('{"act":"init"}');
}

function main(op)
{
	switch (op.act)
	{
	case "nop":// do nothing
		break;
	case "log":
		console.log("GET:%s", op.msg);
		break;
	case "msg":
		//alert(op.msg);
		break;
	case "idcard":
		if(op.datmap.idcard)// has card
		{
			var idcard = JSON.parse(op.datmap.idcard);
			var runner = op.datmap.athlete ? JSON.parse(op.datmap.athlete) : undefined;
			if(typeof onHasCard === "function")
			{
				onHasCard(idcard, runner);
			}
			else
			{
				sethead($("#headimg"), idcard.head);
			}
		}
		else
		{
			if(typeof onNoCard === "function")
			{
				onNoCard();
			}
			else
			{
				sethead($("#headimg"), "");
			}
		}
		break;
	case "result":
		alert(op.msg ? "验证通过" : "验证不通过");
		break;
	default:
		console.log("unknown response");
		break;
	}
}

class IDCard 
{
	constructor(name, id, head)
	{
		this.name = name || "";
		this.id = id || "";
		this.gender = true;
		this.head = head || "";
	}
}

class Action
{
	constructor(act, msg)
	{
		this.act = act;
		this.msg = msg;
		this.datmap = {};
	}
	addData(key, val)
	{
		this.datmap[key] = val;
		return this;
	}
}

var canvas = $("#buffer")[0];

function sethead(obj, img)
{
	obj.attr('src', 'data:image/jpg;base64,' + img);
}

function uppic()
{
	var dat = canvas.toDataURL("image/png").replace("data:image/png;base64,", "");
	sendpost(new Action("upimg", dat).addData("id", 1));
}

$('document').ready(function()
{
	init();
	navigator.getUserMedia = (navigator.getUserMedia || navigator.webkitGetUserMedia
		|| navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia);
	var cvctx = canvas.getContext("2d");
	$('#uppic').click(function()
	{
		cvctx.drawImage($("#headimg")[0], 0, 0, 640, 480);
		uppic();
	});
	$('#trycard').click(function()
	{
		send(new Action("getcard"));
	});
	$('#validate').click(function()
	{
		var dat = canvas.toDataURL("image/png").replace("data:image/png;base64,", "");
		send(new Action("validate", dat));
	});
});