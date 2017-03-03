<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
    <div style="border:solid 1px black; width:500px; margin-left:300px; margin-top:80px" >
    <table align="center" border="0">
    	<tr>
    		<td colspan="2" align="center">
    			<h4 align="center">成绩信息</h4>
    			<br>
    		</td>
    	</tr>
    	<tr height="40">
    		<td>学号:</td>
    		<td>
    			${pGrade.studentID}
    		</td>
    	</tr>
    	
    	<tr height="40">
    		<td>大作业:</td>
    		<td>
    			${pGrade.homework}
    		</td>
    	</tr>
    	
    	<tr height="40">
    		<td>报告:</td>
    		<td>
    			${pGrade.report}
    		</td>
    	</tr>
    	
    	<tr height="40">
    		<td>课堂交互:</td>
    		<td>
    			${pGrade.interaction}
    		</td>
    	</tr>
    	
    	<tr height="40">
    		<td>期中考试:</td>
    		<td>
    			${pGrade.middleTest}
    		</td>
    	</tr>
    	
    	<tr height="40">
    		<td>期末考试:</td>
    		<td>
    			${pGrade.finalTest}
    		</td>
    	</tr>
    	
    	<tr height="40">
    		<td>最终成绩:</td>
    		<td>
    			${pGrade.finalScore}
    		</td>
    	</tr>
    	
    </table>
    </div>
  </body>
</html>
