var runner;
function onHasCard(idcard, runner){
	sethead($("#headimg"), idcard.head);
	var sex;
	if(idcard.gender==true){
		sex='男'
	}else{
		sex='女'
	}
	document.getElementById('IDCardContent').innerHTML = '姓名：'+idcard.name+'<br>'
			+'性别：'+sex+'<br>身份证号：'+idcard.id;
	var runner_gender;
	if(runner.gender==true){
		runner_gender='男'
	}else{
		runner_gender='女'
	}
	document.getElementById('runnerData').innerHTML = '姓名：'+runner.name+'<br>'
	+'性别：'+runner_gender+'<br>号码牌：'+runner.athleteID+'<br>血型：'+runner.blood
	+'<br>联系方式：'+runner.phone+'<br>紧急联系人：'+runner.urgencyName
	+'<br>紧急联系人电话：'+runner.urgencyPhone+'<br>住址：'+runner.address
	+'<br>身份证号：'+runner.iDcard;
}

function onPass(runner, head){//验证通过，修改运动员信息
	var today_img = document.getElementById('currentimg').src;
	var runner_gender;
	if(runner.gender==true){
		runner_gender='男'
	}else{
		runner_gender='女'
	}
	document.getElementById('todayimg').setAttribute("src",today_img);//今日画面
	sethead($("#previmg"), head);//昨日画面
	document.getElementById('runnerDetails').innerHTML = '姓名：'+runner.name+'<br>'
	+'性别：'+runner_gender+'<br>号码牌：'+runner.athleteID+'<br>紧急联系人：'+runner.urgencyName
	+'<br>紧急联系人电话：'+runner.urgencyPhone;
	$('#myModal').modal('show');//验证后，打开模态框
}

function onUnPass(isFindAthlete, runner, head){
	var today_img = document.getElementById('currentimg').src;
	document.getElementById('modal-dialog').style='';
	var modal_body = document.getElementById('modal-body');
	modal_body.innerHTML = '运动员身份识别失败，请再次确认该运动员的身份！！'+
	'<div class="row" style="margin:20px">'+
			'<img src="default_head1.jpg" style="width:100%;border-radius:3px;box-shadow: 0px 0px 3px #888">'+
	'</div>';
	modal_body.style = 'color:#E3170D;font-size:18px;text-align:center; margin-left:auto; margin-right:auto;'
	$('#myModal').modal('show');//验证后，打开模态框
}
