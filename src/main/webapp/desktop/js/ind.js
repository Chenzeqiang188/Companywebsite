$(function(){

	$('.customer-server').click(function() {
		var url = 'https://qiyukf.com/client?k=78f4110eac39c99f909c687260013544&u=&d=przufprowuttz9xwubct&uuid=u0ktt27cssenzqqix2k6&gid=0&sid=0&qtype=0&dvctimer=0&robotShuntSwitch=0&hc=0&robotId=0&t=%25E8%25B0%25B7%25E5%25BE%2597%25E6%25B8%25B8%25E6%2588%258F_%25E5%25AE%2598%25E6%2596%25B9%25E7%25BD%2591%25E7%25AB%2599-%25E8%25B0%25B7%25E5%25BE%2597%25E6%25B8%25B8%25E6%2588%258F%25EF%25BC%258C%25E7%25BC%2594%25E9%2580%25A0%25E7%25B2%25BE%25E5%2593%2581';
		openWin(url, '_blank', 590, 630)
	})
	
	$(".down").mouseover(function(){
		$(this).find("img").stop().fadeOut("fast");
	}).mouseout(function(){
		$(this).find("img").fadeIn("fast");
	})

	// $(".header .menu li").eq(0).css("border-bottom-width","5px")
	// $(".header .menu li").eq(0).find(".span1").css("color","#f00")

	// $(".header .menu li").hover(function(){
	// 	$(this).css("border-bottom-width","5px")
	// 	$(this).find(".span1").css("color","#f00")
	// },function(){
	// 	$(this).css("border-bottom-width","0px")
	// 	$(this).find(".span1").css("color","#999")
	// })


	//$(".Title .title li").eq(0).css("background","#f4f4f4")
	//$(".header .menu li").eq(0).find(".span1").css("color","#f00")

	//Scroll To Top
	//$(function() {



	$(".down a,.web a").click(function(){

		if ($(this).attr("href")=="") {
			$(this).attr("href","javascript:unopen();")
			
			
		};
		
	})

/*	$(".fuwu .box .item .div2").click(function(){
			$("#bg").fadeTo("fast",0.5);
			$("#showwindow").show();	
	})*/


	$("#showwindow #clo").click(function(){
			$("#bg").hide()
			$("#showwindow").hide();	
	})

    $("#toTop").scrollToTop(1000);
$(".header .menu .li2").hover(function(){
	$("#show_1").show();
	$("#show_2").hide();
	$("#show_3").hide();
})
$(".header .menu .li3").hover(function(){
	$("#show_2").show();
	$("#show_1").hide();
	$("#show_3").hide();
})
$(".header .menu .li5").hover(function(){
	$("#show_3").show();
	$("#show_1").hide();
	$("#show_2").hide();
})

	var a=0;
	$(".header .menu .li2,.header .menu .li3, .header .menu .li5").mouseenter(function(){
	
		$(".showbox").slideDown("fast");
		if(a==1){
			 $(".showbox").show("fast");
			 a=0;
		}
	}).mouseleave(function(){
			//$(".showbox").slideUp("show");
	})


	$(".showbox").mouseenter(function(){
		$(".showbox").slideDown("fast");
	}).mouseleave(function(){
			$(".showbox").slideUp("fast");
			a=1;
	})



    $(".gimg").mouseover(function(){
		$(this).stop().fadeTo("fast",0.000001);
	}).mouseout(function(){
		$(this).stop().fadeTo("fast",1);
	})

	$(".Title .title ul li img").hide();

	$(".gametitle ul li img").hide();
	$(".gametitle ul li").eq(0).css("background","#f4f4f4").css("color","#f00");
	$(".gametitle ul li img").eq(0).show();
	$(".gametitle ul li").click(function(){
		$(".gametitle ul li").css("background","#fff").css("color","#333");
		$(this).css("background","#f4f4f4").css("color","#f00");
		$(".gametitle ul li").find("img").hide()
		$(this).find("img").show()
		$(".tab").hide().eq($(this).index()).show();
		
	})
})



function navtitle(Index){
	$(function(){
		$(".Title .title li").find("a").attr("style","").find("img").hide();
		$(".Title .title li").attr("style","").eq(Index).css("background","#f7f7f7").find("a").css("color","#f00");
		$(".Title .title ul li img").eq(Index).show();
	})
}
function headtitle(Index){
	$(function(){
		$(".header .menu li").eq(Index).css("border-bottom-width","5px")
		$(".header .menu li").eq(Index).find(".span1").css("color","#f00")
	})
}

function unopen(){

		$("#bg").fadeTo("fast",0.5);
		$("#showwindow").show();
	}

function openWin(url,name,iWidth,iHeight) { 
    //获得窗口的垂直位置 
    var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
    //获得窗口的水平位置 
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
    window.open(url, name, 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft); 
}

$(document).ready(function() {
	if ($('.banner-base').length > 0) {
		var match = window.location.search.match(/type=(\d)/) || []
		var type = +(match[1] || 0)

		if (type === 0) {
			$('.banner-base').addClass('banner3')
		} else {
			$('.banner-base').addClass('banner3-2')
		}
	}
})