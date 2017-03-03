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
  	<c:if test="${empty gList }">
  		尚未上传成绩！
  	</c:if>
	<c:if test="${!empty gList}">
    	<table border="1" width="80%" align="center">
    		<tr>
    			<td width="40%">学号</td>
    			<td>成绩类型</td>
    			<td>分数</td>
    			<td>修改分数</td>
    			<td>操作</td>
    		</tr>
    		<c:forEach items="${gList}" var="c">
    			<tr height="10">
    			<td>${c.studentID }</td>
    			<td>${c.gradeType }</td>
    			<td>${c.score }</td>
    			<td>
    				<form action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=modifyGrade" method="post">
    				<input type="hidden" name="studentID" value=${c.studentID }> 
    				<input type="hidden" name="gradeType" value=${c.gradeType }> 
    				<div style="margin-top:15px">
    				<input type="text" height="20" name="modifyScore"> 
                    <input type="submit" value="修改">  
                    </div>
    				</form>
    			</td>
    			<td>
    				<a href="${pageContext.request.contextPath}/servlet/GradeServlet?operation=deleteGrade&studentID=${c.studentID}&gradeType=${c.gradeType}">删除</a>
    			</td>
    			</tr>
    		</c:forEach>
    	</table>
    </c:if>
  </body>
</html>
