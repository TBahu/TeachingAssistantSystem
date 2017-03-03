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
    <h4 align="center">所有文件列表</h4>
  	<c:if test="${empty fList }">
  		没有文件！
  	</c:if>
	<c:if test="${!empty fList}">
    	<table border="1" width="80%" align="center">
    		<tr>
    			<td>文件名</td>
    			<td>上传日期</td>
    			<td>上传者</td>
    			<td>文件描述</td>
    			<td>操作</td>
    		</tr>
    		<c:forEach items="${fList}" var="c">
    			<tr>
    			<td>${c.name }</td>
    			<td>${c.uploadTime }</td>
    			<td>${c.uploader }</td>
    			<td>${c.description }</td>
    			<td>
    				<a href="${pageContext.request.contextPath}/servlet/FileServlet?operation=downloadFile&id=${c.id}&name=${c.name}">下载</a>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    </c:if>
  </body>
</html>
