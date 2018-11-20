function isNull(id,msg){
	document.getElementById(id + "Tip").innerHTML = "";
	var value = document.getElementById(id).value;
	if(value==""){
		document.getElementById(id + "Tip").innerHTML = msg;
		return false;
	}else{
		return true;
	}
}

