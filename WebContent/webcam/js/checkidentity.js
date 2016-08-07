var athlete;
var predata;
function onHasCard(idcard, runner){
	athlete=runner;
	//alert(athlete.athleteID);
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
    var img_src = "http://120.27.106.188/easyrun/bg/UserPicture/UserInfo/"+runner.identityPic;
    if(browserIsIe()){//假如是ie浏览器  
        $('.down_qr').on('click',function(){  
            img_src = $('.qr_img')[0].src;  
            DownLoadReportIMG(img_src);  
        });  
    }else{  
        $('.down_qr').attr('download',img_src);  
        $('.down_qr').attr('href',img_src);  

        $('.sutmit_btn').on('click',function(){  
            $('.down_qr').attr('download',img_src);  
            $('.down_qr').attr('href',img_src);  
        });  
    } 
	//DownLoadReportIMG("http://120.27.106.188/easyrun/bg/UserPicture/UserInfo/"+runner.identityPic);
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
	//alert("333");
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

function onNoCard(){
	document.getElementById('headimg').setAttribute("src","default_head.jpg");//default identity card picture
	document.getElementById('IDCardContent').innerHTML = '姓名：<br>'
	+'性别：<br>身份证号：';
	document.getElementById('runnerData').innerHTML = '姓名：<br>'
	+'性别：<br>号码牌：<br>血型：<br>联系方式：<br>紧急联系人：<br>紧急联系人电话：<br>住址：<br>身份证号：';
}


function DownLoadReportIMG(imgPathURL) {  
    //如果隐藏IFRAME不存在，则添加  
    if (!document.getElementById("IframeReportImg"))  
        $('<iframe style="display:none;" id="IframeReportImg" name="IframeReportImg" onload="DoSaveAsIMG();" width="0" height="0" src="about:blank"></iframe>').appendTo("body");  
    if (document.all.IframeReportImg.src != imgPathURL) {  
        //加载图片  
        document.all.IframeReportImg.src = imgPathURL;  
    }  
    else {  
        //图片直接另存为  
        DoSaveAsIMG();  
    }  
}  
function DoSaveAsIMG() {  
    if (document.all.IframeReportImg.src != "about:blank")  
        window.frames["IframeReportImg"].document.execCommand("SaveAs");  
}  
//判断是否为ie浏览器  
function browserIsIe() {  
    if (!!window.ActiveXObject || "ActiveXObject" in window)  
        return true;  
    else  
        return false;  
}  