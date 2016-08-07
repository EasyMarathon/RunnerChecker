$(function(){

	$('#webcam').photobooth().on("image",function( event, dataUrl ){
		predata = dataUrl.replace("data:image/png;base64,", "");
		$('.nopic').hide();
		$( "#pictures" ).html( '<img id="currentimg" src="' + dataUrl + '" >');
		//alert("拍摄完成！");
		sendpost(new Action("upimg", predata).addData("athleteID", athlete.athleteID));
		alert("拍摄完成！");
	});
	
	$('#webcam1').photobooth().on("image",function( event, dataUrl ){
		predata = dataUrl.replace("data:image/png;base64,", "");
		$('.nopic').hide();
		$( "#pictures" ).html( '<img id="currentimg" src="' + dataUrl + '" >');
		//alert("athleteID:"+runner.athleteID);
		//sendpost(new Action("upimg", predata).addData("id", runner.athleteID));
		send(new Action("validate", predata));
		
	});
	
	if(!$('#webcam').data('photobooth').isSupported){
		alert('HTML5 webcam is not supported by your browser, please use latest firefox, opera or chrome!');
	}	
	
	$('.photobooth ul li:last').qtip({
		content: {
			text: '点击进行拍照',
			title: {
				button: true
			}
		},
		show: {
			ready: false
		},
		position: {
			target: 'event',
	      	my: 'left center', 
	      	at: 'right center'
		},
		style: {
			classes: 'ui-tooltip-shadow ui-tooltip-bootstrap',
			width: 300
		}
	});	
	
	$('#site').qtip({
		content: {
			text: 'Demo from our geek blog: http://www.gbin1.com',
			title: {
				text: 'wlecome',
				button: true
			}
		},
		position: {
			target: 'event',
	      	my: 'bottom center', 
	      	at: 'top center',
			viewport: $(window)
		},
		style: {
			classes: 'ui-tooltip-shadow ui-tooltip-jtools'
		}
	});	
});