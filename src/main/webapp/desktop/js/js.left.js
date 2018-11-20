$(document).ready(function(){
	var bool=1;
	var All=1;
	
	$(".menu1").click(function(){
		if(bool==1){
		
		$(".menu1").css("background","url(images/left_menu_bg.png) no-repeat top").css("color","#6e6e6e");
		$(this).css("background","url(images/left_menu_bg1.png) no-repeat top").css("color","#fff");
		$(".menu_1").slideUp();
		$(this).parent().children(".menu_1").slideDown();bool=0}
		else{
		
		$(".menu1").css("background","url(images/left_menu_bg.png) no-repeat top").css("color","#6e6e6e");
		$(this).css("background","url(images/left_menu_bg1.png) no-repeat top").css("color","#fff");
		$(".menu_1").slideUp();
		$(this).parent().children(".menu_1").slideDown();bool=1}
		
	})
	$(".menu_1 ul li").mouseover(function(){
		$(this).removeClass('li_click');
		$(this).addClass("li_click");
		
	}).mouseout(function(){
		
		$(this).addClass("li_click");
		$(this).removeClass('li_click');
	}).click(function(){
		$(".menu_1 ul li").removeClass();
		$(this).addClass("li_clicks").css("color","#6e6e6e");
		
	})
	
	
	$("#all").click(function(){
		if(All==1){
			$(".menu1").css("background","url(images/open.png) no-repeat 10px center #fff");
			
			$("#all").text("�������в˵�");
			$(".menu1").parent().children(".menu_1").slideDown();All=0}
		else{
			$(".menu1").css("background","url(images/close.png) no-repeat 10px center #fff");
			$("#all").text("չ�����в˵�");
			$(".menu1").parent().children(".menu_1").slideUp();All=1}
		
	})
	
	
})