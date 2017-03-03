<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<script type="text/javascript" charset="utf-8" src="/TeachingAssistantSystem/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="/TeachingAssistantSystem/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="/TeachingAssistantSystem/ueditor/lang/zh-cn/zh-cn.js"></script>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
  	<br>
    <h4 align="center">发布通知</h4>
    <form action="${pageContext.request.contextPath}/servlet/NoticeServlet?operation=releaseNotice" method="post">
    <table border="1" width="100%">
    	<tr>
    		<td width="30%">发布者:</td>
    		<td>
    			<input type="text" name="author" />
    		</td>
    	</tr>
    	<tr>
    		<td width="30%">发布时间:</td>
    		<td>
    			<input type="date" name="releaseDate"/>
    			<font size="2" color="red">若手动输入，格式为：yyyy-MM-dd，例如2017-02-02</font>
    		</td>
    	</tr>
    	<tr>
    		<td width="30%">标题:&nbsp;&nbsp;&nbsp;&nbsp;</td>
    		<td>
    			<input type="text" name="title"  style="width:500px"/>
    			<font size="2" color="red">最多可输入100字</font>
    		</td>
    	</tr>
    	<tr>
    		<td width="30%">操作:&nbsp;&nbsp;&nbsp;&nbsp;</td>
    		<td>
    			<input type="submit" value="保存并发布">
    			<font size="2" color="red">在下方输入通知内容点击发布</font>
    		</td>
    	</tr>
    </table>
    <textarea name="content" id="myEditor" Style="height:40%;width:99.9%">请输入通知内容！</textarea>
 	<script type="text/javascript">
 		UEDITOR_CONFIG.UEDITOR_HOME_URL = '/TeachingAssistantSystem/ueditor/'; //一定要用这句话，否则你需要去ueditor.config.js修改路径的配置信息
 		UE.getEditor('myEditor');
 	</script>
 	${message}
    </form>
  </body>
</html>
