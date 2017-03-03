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
    <h4 align="center">成绩列表</h4>
  	<c:if test="${empty tList }">
  		尚未上传成绩！
  	</c:if>
	<c:if test="${!empty tList}">
    	<table border="1" width="60%" align="center">
    		<tr>
    			<td width="30%">姓名</td>
    			<td>学号</td>
    			<td>分数</td>
    			<td>操作</td>
    		</tr>
    		<c:forEach items="${tList}" var="c">
    			<tr height="10">
    			<td>${c.studentName }</td>
    			<td>${c.studentID }</td>
    			<td>${c.score }</td>
    			<td>
    				<form action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=getGrade" method="post">
    				<input type="hidden" name="studentID" value=${c.studentID }> 
    				<div style="margin-top:15px">
                    <input type="submit" value="查看详情">  
                    </div>
    				</form>
    			</td>
    			</tr>
    		</c:forEach>
    	</table>
    </c:if>
  </body>
</html>
