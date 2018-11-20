// JavaScript Document
var i=1;
function lockup(dizhi){
	if(i==1){
		i++;
		var scrolltop = window.pageYOffset  || document.documentElement.scrollTop  || 

document.body.scrollTop || 0;
		var msgw,msgh,bordercolor;    
   		msgh="100%";//提示窗口的高度
  		bordercolor="#F6F0A1";//提示窗口的边框颜色
  		var bgwidth= document.documentElement.clientWidth || document.body.clientWidth;
        //整个页面的高度
        var bgheight =  Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
		var _clientheight=0;
		//ie FF  在有DOCTYPE doctype时各有区别 
   		 _clientheight = Math.min(document.body.clientHeight , 

document.documentElement.clientHeight);
    	if(_clientheight==0)
     		 _clientheight= Math.max(document.body.clientHeight , 

document.documentElement.clientHeight);
        //整个页面的高度
		var msgtop = (scrolltop+(_clientheight)/2)+"px";

		var bg=document.createElement("div");
	//<div style="display:block;position:absolute;top: 0%;left: 0%;width:100%;height: 100%;background-color:#777;z-index:-1;filter:alpha(opacity=60);">
		bg.setAttribute("id","bg");
		bg.style.position="absolute";
		bg.style.top=0;
		bg.style.left=0;
		bg.style.width=bgwidth+"px";
		bg.style.height=bgheight+"px";
		bg.style.background="#777";
		bg.style.zIndex="1000";
		bg.style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity=60)";
		bg.style.opacity="0.6";
		document.body.appendChild(bg);//在body内添加该div对象
	
   		msgw=eval(bgwidth*0.5);//提示窗口的宽度  

		var show=document.createElement("div");
	//<div style="background-color:#FFFFFF;position: absolute;top:25%;left:25%;width:50%;height: 0%;border:1px solid #F6F0A1;"></div>
		show.setAttribute("id","show");
		show.style.background="#FFFFFF";
		show.style.position="absolute";
		show.style.top=msgtop;
		show.style.left="25%";
		show.style.width="50%";
		show.style.height=msgh;
		show.style.border="1px solid " + bordercolor;
		show.style.zIndex="1001";
		document.body.appendChild(show);

		var st='<h4 style="padding:8px;">\
      	<span>请填写禁言资料:</span>\
      </h4>\
   	  <form action="' + dizhi +'" method="post" name=form1>\
       		<table border="0" cellspacing="0" cellpadding="0" width="100%" >\
                <tr>\
                	<td height="31" align="right">禁言原因：<span style="color:#FF0000">*</span></td>\
                  <td><table  border="0" cellspacing="0" cellpadding="0" width="100%"><tr><td><input type="text" name="whys" /></td></tr>\</table></td>\
                </tr>\
                <tr>\
	            	<td height="31" align="right">禁言时间：<span style="color:#FF0000">*</span></td>\
	              <td><table  border="0" cellspacing="0" cellpadding="0" width="100%"><tr><td><select name=hourTime1><option value=2>2 小时</option><option value=12>12 小时</option><option value=24>1 天</option><option value=168>7 天</option><option value=720>30 天</option><option selected value=0>解除</option></select></td></tr>\</table></td>\
	            </tr>\
                <tr>\
                	<td height="45"></td>\
                	<td>\
                    	<table border="0" cellspacing="0" cellpadding="0" width="100%">\
                        	<tr>\
                           	  <td width="21%"><input type="submit" name="" value="确定" style="width:100px; height:25px;" /></td>\
                                <td width="79%"><input class="off" type="button" name="" value="关闭" style="width:60px; height:25px;" /></td>\
                            </tr>\
                        </table>\
                    </td>\
                </tr>\
        </table>\
      </form>\
        <style type="text/css">\
			*{\
				margin:0px;\
				padding:0px;\
				font-size:12px;\
			}\
			table td{padding-left:5px;}\
		</style>';
	
	//上面的代码是显示的
		$("body").css("overflow-y","hidden" );
		$("#show").html(st);
		$(".off").click(function(){
			i=1;
			$("body").css("overflow-y","" );
			document.body.removeChild(bg);//删除背景层Div   
     		document.body.removeChild(show);//删除提示框层
		}).mouseover(function(){
			$(this).css("cursor","hand");
		});	
	}
}