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
  	<h2 align="center">${notice.title}</h2>
  	${notice.content}
  	<h5 align="right" >发布人：${notice.author}</h5>
  	<h5 align="right">发布时间：${notice.releaseDate}</h5>
  </body>
</html>
