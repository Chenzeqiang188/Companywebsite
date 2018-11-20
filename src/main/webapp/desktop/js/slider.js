(function($){
	$.fn.focusImg = function(settings){
		//speed:Í¼Æ¬ÇÐ»»Ê±¼ä£¬time:¶¨Ê±Æ÷Ê±¼ä
		var default_settings = {speed:300, time:3000};
		var settings = jQuery.extend(default_settings, settings);

		var that = $(this);
		if ($('.focus-btn'))
		{
			var oList = that.find('.focus-btn');
			var oBd = that.find('.focus-bd');
		}else{
		  return;
		}
		
		var iNow = 0;
		var timer;
		
		//Í¨¹ýÍ¼Æ¬ÊýÁ¿ ×Ô¶¯Êä³ö°´Å¥¸öÊý
		var _html = ''
		var _length = oBd.find('li').length;


		if (_length > 1){
			for (var i=1; i<_length+1; i++ )
			{
				_html += '<li></li>';
			}
			oList.html(_html);
		}

		var oBtn = that.find('.focus-btn>li');
		oBtn.eq(0).addClass('on');

		//×Ô¶¯¼ÆËã¿í¶È
		var oCont = oBd.find('li');
		var _width = oCont.length * oCont.eq(0).width();
		oBd.css('width' , _width);

		//µã»÷Ê±¹ö¶¯
		oBtn.mouseenter(function (){
			var that = $(this);
			iNow = that.index();
			doMove(iNow);
		});

		//×Ô¶¯¹ö¶¯
		function starMove(){
			if (iNow == oCont.length-1)
			{
				iNow = 0;
			}else{
				iNow++;
			}
			doMove(iNow);
		}
		//timer = setInterval(starMove, settings.time);

		function doMove(iNow){
            //fix ie6 layout bug zhe.zhu
            if(oCont.css('display') == 'inline-block'){
                oCont.css('display','inline');
            }else{
                oCont.css('display','inline-block');
            }

            oBtn.eq(iNow).addClass('on').siblings().removeClass('on');
			oBd.stop().animate({ 
				left : -oCont.eq(0).width() * iNow
			}, settings.speed);
		}
		
		//Çå³ý¶¨Ê±Æ÷
		that.mouseover(function(){
			timer = clearInterval(timer);
		}).mouseout(function(){
			timer = clearInterval(timer);
			//timer = setInterval(starMove, settings.time);
		});

		return this;
	}
})(jQuery); 

$(document).ready(function(){
       // setTimeout(function(){
            $('#banner-focus').focusImg();
       // },1000);
    });