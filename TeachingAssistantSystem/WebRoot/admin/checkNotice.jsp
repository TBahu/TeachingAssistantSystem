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
    <h4 align="center">通知列表</h4>
  	<c:if test="${empty nList }">
  		尚未发布通知！
  	</c:if>
	<c:if test="${!empty nList}">
    	<table border="1" width="80%" align="center">
    		<tr>
    			<td width="40%">标题</td>
    			<td>发布者</td>
    			<td>发布时间</td>
    			<td>操作</td>
    		</tr>
    		<c:forEach items="${nList}" var="c">
    			<tr>
    			<td><a href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=getNotice&id=${c.id}">${c.title }</a></td>
    			<td>${c.author }</td>
    			<td>${c.releaseDate }</td>
    			<td>
    				<a href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=deleteNotice&id=${c.id}">删除</a>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    </c:if>
  </body>
</html>
