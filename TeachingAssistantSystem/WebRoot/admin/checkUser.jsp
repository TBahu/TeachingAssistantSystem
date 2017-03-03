<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
    <h4 align="center">所有用户列表</h4>
  	<c:if test="${empty uList }">
  		没有录入用户！
  	</c:if>
	<c:if test="${!empty uList}">
    	<table border="1" width="80%" align="center">
    		<tr>
    			<td>用户编号</td>
    			<td>姓名</td>
    			<td>用户类型</td>
    			<td>密码（已加密存储）</td>
    			<td>电话号码</td>
    			<td>邮箱</td>
    			<td>操作</td>
    		</tr>
    		<c:forEach items="${uList}" var="c">
    			<tr>
    			<td>${c.userID }</td>
    			<td>${c.userName }</td>
    			<td>${c.userType }</td>
    			<td>${c.password }</td>
    			<td>${c.phoneNumber }</td>
    			<td>${c.email }</td>
    			<td>
    				<a href="${pageContext.request.contextPath}/servlet/UserServlet?operation=deleteUser&userID=${c.userID}&userType=${c.userType}">删除</a>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    </c:if>
  </body>
</html>
