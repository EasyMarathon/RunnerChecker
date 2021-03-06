/**
 * 
 */
function send(obj)
{
	$.ajax(
	{
		type : "POST",
		url : "validate",
		data : JSON.stringify(obj),
		success : function(data)
		{
			$("#ws_msg").append(data);
			main(JSON.parse(data));
		}
	});
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
		alert(op.msg);
		break;
	case "result":
		var isFindAthlete = op.datmap.hasOwnProperty("athlete");
		var runner = isFindAthlete ? JSON.parse(op.datmap.athlete) : {};
		//alert(runner.athleteID);
		if(op.msg=="true")
		{
			if(typeof onPass === "function")
			{
				//alert("onPass");
				onPass(runner, op.datmap.head);
			}
			else
			{
				//onPass(runner, op.datmap.head);
				sethead($("#previmg"), op.datmap.head);
			}
		}
		else
		{
			if(typeof onUnPass === "function")
			{
				//alert("onUnPass");
				onUnPass(isFindAthlete, runner, op.datmap.head);
			}
			else
			{
				sethead($("#previmg"), op.datmap.head);
			}
		}
		break;
	default:
		console.log("unknown response");
		break;
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
	send(new Action("upimg", dat));
}

/*$('document').ready(function()
{
	var cvctx = canvas.getContext("2d");
	$('#validate').click(function()
	{
		send(new Action("validate", predata));
	});
});*/