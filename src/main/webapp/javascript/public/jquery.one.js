	/*一级分类点击函数*/
	function Search(){
		var xmlhttp;
		if (window.XMLHttpRequest){
	 		 xmlhttp=new XMLHttpRequest();}
		else{
	  		 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	  
		{
			var user=eval("(xmlhttp.responseText)");
			var Option="";
			var obj=$.parseJSON(user);
			for(var i=0;i<obj[0].Channel.length;i++){
				var option='<option id='+obj[0].Channel[i].companyid+' value='+obj[0].Channel[i].channelid+'>'+obj[0].Channel[i].channelName+'</option>';
				Option=Option+option;
				document.getElementById("channelid").innerHTML=Option;
			}
			
		}
	  }
	  
	var url="./common/loadInfo.action";
	xmlhttp.open("GET",url,false);
	xmlhttp.send();	
	
	var company=$("#companyid").find("option:selected").val(); 
	if(company=="-1"){
		$("#channelid").prepend("<option id='-1' value='-1'>全部渠道</option>");
		eixt();
		}
	var channel=$("#channelid option").each(function(){									  
		var channel=$(this).attr("id");
			if(channel==company){
				$(this).css("display","block");

			}
			else{
				$(this).remove();
			}
				
		});
		$("#channelid").prepend("<option id='-1' value='-1'>全部渠道</option>");
	}

	/*一级分类显示函数*/
	$(document).ready(function(){
			var xmlhttp;
			if (window.XMLHttpRequest){
				 xmlhttp=new XMLHttpRequest();}
			else{
				 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		  
			{
				
				var user=eval("(xmlhttp.responseText)");
				var Option='<option value="-1">全部公司</option>';
				var obj=$.parseJSON(user);
				for(var i=0;i<obj[0].Channel.length;i++){
					var option='<option value='+obj[0].Company[i].companyid+' value='+obj[0].Channel[i].companyid+'>'+obj[0].Company[i].companyName+'</option>';
					Option=Option+option;
					document.getElementById("companyid").innerHTML=Option;
				}
				
			}
		  }
		  
		  
		var url="./common/loadInfo.action";
		
		xmlhttp.open("GET",url,false);
		xmlhttp.send();						   
	})


	/*二级分类显示函数*/
	$(document).ready(function(){
		var xmlhttp;
		if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else{// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		  
			{
				
				var user=eval("(xmlhttp.responseText)");
				var obj=$.parseJSON(user);
				var Option='<option id="-1" value="-1">全部渠道</option>';
				for(var i=0;i<obj[0].Channel.length;i++){
					var option='<option id='+obj[0].Channel[i].companyid+' value='+obj[0].Channel[i].channelid+'>'+obj[0].Channel[i].channelName+'</option>';
					Option=Option+option;
					document.getElementById("channelid").innerHTML=Option;
				}
				
			}
		  }
		  
		  
		var url="./common/loadInfo.action";
		
		xmlhttp.open("GET",url,false);
		xmlhttp.send();						   
	})