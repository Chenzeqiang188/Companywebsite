$("document").ready(function(){
//次请求获取查询的关键字,返回的是json格式
$.getJSON("/shopping/good.do",{status:"queAuto"},function(data){
	$("#text").focus().autocomplete(data,{
		minChars:1,
		max:10,
		//autoFill:false
		matchContains:true,
		formatItem:function(row,i,max){   //格式话所有下拉列表的值
			return i+ ": <I>" + row + "</I>";
		},
		formatResult:function(row) {  //格式话最后填充到文本框的值
	         return row;
		}
	});
});
});