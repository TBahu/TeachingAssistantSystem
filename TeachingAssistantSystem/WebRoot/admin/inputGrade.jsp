<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
    <h4 align="center">录入成绩</h4>
    <br>
    <div style="border:solid 1px black; width:500px; margin-left:300px" >
    <form action="${pageContext.request.contextPath}/servlet/GradeServlet?operation=inputGrade" method="post">
    <table align="center" border="0">
    	<tr height="60">
    		<td>学&nbsp;&nbsp;号:</td>
    		<td>
    			<input type="text" name="studentID" />
    		</td>
    	</tr>
    	<tr height="60">
    		<td>成绩类型:</td>
    		<td>
    		<select name="gradeType">
  				<option value ="大作业">大作业:20</option>
  				<option value ="报告">报告:10</option>
  				<option value="课堂互动">课堂互动:5</option>
  				<option value="期中考试">期中考试:20</option>
  				<option value="期末考试">期末考试:45</option>
			</select>
			<font size="2" color="red">冒号后标注为该项满分，是你可输入的最大值</font>
			</td>
    	</tr>
    	<tr height="60">
    		<td>分&nbsp;&nbsp;数:</td>
    		<td>
    			<input type="text" name="score"/>
    		</td>
    	</tr>
    	<tr height="60">
    		<td colspan="2" align="center">
    			<input type="submit" value="录入"/>
    		</td>
    	</tr>
    </table>
    </form>
    ${message}
    </div>
  </body>
</html>
