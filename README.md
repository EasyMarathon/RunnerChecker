# RunnerChecker
## 模块介绍

### base.js
拿取物资部分的js,通信使用websocket，识别到身份证时自动通知页面更新
### validator.js
检录时的js，通信使用ajax，手动点击按钮以触发
### IDCard
身份证bean，js内也有一个这个类，name姓名，id身份证号，gender性别（true为男），head头像
### OpData
通信用的基本的bean，js内为Action类，act行为，msg附带信息，datmap额外信息（java中表现为map，js中用addData添加）
### HostService
基础的Service，对OpData中不同的act做响应
### MainService
实际的Service，做实际的工作（上传照片、检录时人脸验证）
### CardUtil
与读卡器通讯的工具类。JNI
## API
### MainService
看注释，要求都写好了
### base.js
onHasCard(idcard, runner)
检测到身份证时调用，idcard为身份证数据，runner为运动员信息数据
onNoCard()
身份证被挪走时调用
sendpost(Action)
拍摄完照片上传时调用,sendpost(new Action("upimg", dat).addData("id", 1)),dat为照片base64（去除data头）
### validate.js
onPass(runner, head)
检测通过时调用，runner为运动员信息数据，head为昨日拍下的头像
onUnPass(isFindAthlete, runner, head);
检测不通过时调用，isFindAthlete为是否识别到了运动员的身份（关系到runner值存不存在），runner为运动员信息数据，head为昨日拍下的头像
