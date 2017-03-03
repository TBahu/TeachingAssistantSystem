<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
<body>
	<br>
	<ul>
		<li><b><a href="middleFrm.jsp" target="middleFrame" style="text-decoration:none; color:black">首页</a></b>
		<li><b>系统管理</b>
    	<ul>
			<li><a href="addUser.jsp" target="middleFrame">添加用户</a></li>
			<li><a href="${pageContext.request.contextPath}/servlet/UserServlet?operation=checkUser" target="middleFrame">查看用户</a></li>
			<li><a href="/TeachingAssistantSystem/common/modifyPWD.jsp" target="middleFrame">修改密码</a></li>
			<li><a href="${pageContext.request.contextPath}/servlet/UserServlet?operation=logout" target=_parent>注销登录</a></li>
		</ul>
		<br/>
		</li>
		
		<li><b>成绩管理</b>
    	<ul>
			<li><a href="inputGrade.jsp" target="middleFrame">录入成绩</a></li>
			<li><a href="${pageContext.request.contextPath}/servlet/GradeServlet?operation=aCheckGrade" target="middleFrame">查看成绩</a></li>
		</ul>
		<br/>
		</li>
		
		<li><b>通知管理</b>
   	    <ul>
			<li><a href="releaseNotice.jsp" target="middleFrame">发布通知</a></li>
			<li><a href="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=checkNotice" target="middleFrame">查看通知</a></li>
		</ul>
		<br/>
		</li>
		
		<li><b>资源管理</b>
    	<ul>
			<li><a href="uploadFile.jsp" target="middleFrame">上传文件</a></li>
			<li><a href="${pageContext.request.contextPath}/servlet/FileServlet?operation=checkFile" target="middleFrame">查看文件</a></li>
		</ul>
		</li>
	</ul>
</body>
</html>
