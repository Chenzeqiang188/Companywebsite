
$("#toTop").on('click tap', function(event) {
	$("html,body").stop().animate({"scrollTop":0},400);
});

var $dialog = document.getElementById("dialog") , $dialogBg = $dialog.getElementsByClassName("closeBg")[0] , $dialogClose = $dialog.querySelectorAll(".close");
function showDialog(v,msg){
    if(msg){
        $dialog.querySelector(".msgCon p").innerHTML = msg;
    }
    $dialog.className="";
    $dialog.classList.add("show");
    $dialog.classList.add(v);
    $dialog.dialogShow=(v); 
}
    // 加载
function hideDialog(v){ 
    $dialog.classList.toggle($dialog.dialogShow);
	if($dialog.dialogShow=="video"){
		$dialog.querySelector(".videoCon").removeChild( document.getElementById("video") )
	}
    function removeShow(){        
        $dialog.classList.remove("show");
        $dialogBg.removeEventListener("webkitTransitionEnd",removeShow)
    }
    $dialogBg.addEventListener("webkitTransitionEnd",removeShow,false);
}
document.getElementById("shareOpenBtn").addEventListener("touchend",function(){
     showDialog("share");
},false);
$dialogBg.addEventListener("touchend",function(){
    hideDialog();
},false); 