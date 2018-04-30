<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  <script type="text/javascript" src="webpage/com/demo/jquery-1.8.3.js"></script>
  <script type="text/javascript">
  function query()
  {
  	$.ajax({
		url : "demologinController.do?find",
		type : "POST",
		data : {},
		success : function(data) {
		    alert("123");
		}
    });
  }
  </script>
  </head>
 
  <body>
  <h1>Hello World!</h1>
  <span>登录用户名为：${username}</span>
  <span>登录密码为：${password }</span>
  <input name="queryBtn" type="button" onClick="query()" value="查询账户数据">
  <table border="1" >
  <thead>
   <tr>
    <td>账户名称</td>
    <td>账号</td>
  </tr>
  </thead>
  <tbody id="mytable">
  </tbody>
 </table>
  </body>
</html>