<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
    <h4 align="center">添加用户</h4>
    <br>
    <div style="border:solid 1px black; width:500px; margin-left:300px" >
    <form action="${pageContext.request.contextPath}/servlet/UserServlet?operation=addUser" method="post">
    <table align="center" border="0">
    	<tr height="40">
    		<td>用户编号:</td>
    		<td>
    			<input type="text" name="userID" />
    			<font size="2" color="red">学号或教师编号</font>
    		</td>
    	</tr>
    	<tr height="40">
    		<td>用户名:</td>
    		<td>
    			<input type="text" name="userName" />
    		</td>
    	</tr>
    	<tr height="40">
    		<td>用户类型:</td>
    		<td>
    		<select name="userType" >
  				<option value ="管理员">管理员</option>
  				<option value ="教师">教师</option>
  				<option value="学生">学生</option>
			</select>
			</td>
    	</tr>
    	<tr height="40">
    		<td>密&nbsp;&nbsp;码:</td>
    		<td>
    			<input type="text" name="password"/>
    		</td>
    	</tr>
    	<tr height="40">
    		<td>电话号码:&nbsp;&nbsp;&nbsp;&nbsp;</td>
    		<td>
    			<input type="text" name="phoneNumber"/>
    		</td>
    	</tr>
    	<tr height="40">
    		<td>邮&nbsp;&nbsp;箱:</td>
    		<td>
    			<input type="text" name="email"/>
    		</td>
    	</tr>
    	<tr height="40">
    		<td colspan="2" align="center">
    			<input type="submit" value="提交"/>
    		</td>
    	</tr>
    	
    </table>
    </form>
    </div>
    ${message}
  </body>
</html>
