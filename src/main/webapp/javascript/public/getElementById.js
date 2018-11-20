//下面的代码段如果你页面里有，可以去掉
var ie =navigator.appName=="Microsoft Internet Explorer"?true:false;
function $id(objID){
return document.getElementById(objID);
}
function $getid(objID){
	return document.getElementById(objID).value;
}
function $setid(objID,objValue){
	return document.getElementById(objID).value=objValue;
}