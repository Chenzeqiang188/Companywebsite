<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
    <table id="tablelists" class="table" style="width:100%; text-align:center; margin-top:20px;">
    <tr>
	    <td style="text-align:right">
	      <a href="javascript:prePage();">上一页</a>&nbsp;&nbsp;&nbsp;
	      <a href="javascript:nextPage();" >下一页</a>&nbsp;&nbsp;&nbsp;
	                    当前第 ${page.pageNum }/${page.totalPage } 页,每页 
	      <select name="page.pageSize" onchange="changePageSize(this.value);" style="width:50px;">
	        <option value="1" <c:if test="${page.pageSize == 1 }">selected</c:if> >1</option>
	        <option value="5" <c:if test="${page.pageSize == 5 }"> selected</c:if> >5</option>
	        <option value="10" <c:if test="${page.pageSize == 10 }"> selected</c:if> >10</option>
	        <option value="15" <c:if test="${page.pageSize == 15 }">selected</c:if> >15</option>
	        <option value="20" <c:if test="${page.pageSize == 20 }">selected</c:if> >20</option>
	        <option value="50" <c:if test="${page.pageSize == 50 }">selected</c:if> >50</option>
	        <option value="100" <c:if test="${page.pageSize == 100 }">selected</c:if> >100</option>
	      </select> 
	                   条,共${page.totalSize }条
	      <div style="display:none;"></div>
	    </td>
    </tr>
    </table>
</body>
<script type="text/javascript">
  var str="";
  str='<div><input type="hidden" name="" id="pageNum" value="" /><input type="hidden" name="" id="pageSize" value="" /><input type="hidden" name="" id="totalPage" value="" /></div>';
  $("#form").append(str);
  $("#pageNum").attr({'value': '${page.pageNum }','name': 'page.pageNum'});
  $("#pageSize").attr({'value': '${page.pageSize }','name': 'page.pageSize'});
  $("#totalPage").attr({'value': '${page.totalPage }','name': 'page.totalPage'});

   //上一页
  function prePage(){
    var pagenum = $("#pageNum").val();
    var form = $("#form");
    pagenum--;
    if(pagenum>0){
      $("#pageNum").val(pagenum);
       form.submit();
    }else{
       return false;
    }
  } 
  //下一页
  function nextPage(){
    var pagenum = $("#pageNum").val();
    var totalpage = $("#totalPage").val();
    var form = $("#form");
      pagenum++;
    if(pagenum<=totalpage){
        
      $("#pageNum").val(pagenum);
       form.submit();
    }else{
       return false;
    }
  }
  //改变每页显示数目
  function changePageSize(obj){
  	$('#pageNum').val(1);
    $('#pageSize').val(obj);
    var form = $("#form");
     form.submit();
  }
</script>

</html>