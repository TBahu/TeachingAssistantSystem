package ustc.sse.sa16225300.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import ustc.sse.sa16225300.domain.User;
import ustc.sse.sa16225300.service.BusinessService;
import ustc.sse.sa16225300.service.impl.BusinessServiceImpl;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService business=new BusinessServiceImpl();
	private static Logger logger = Logger.getLogger(UserServlet.class); 

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if("login".equals(operation)){
			login(request,response);
		}
		if("logout".equals(operation)){
			logout(request,response);
		}
		if("addUser".equals(operation)){
			addUser(request,response);
		}
		if("checkUser".equals(operation)){
			checkUser(request,response);
		}
		if("deleteUser".equals(operation)){
			deleteUser(request,response);
		}
		if("findPWD".equals(operation)){
			findPWD(request,response);
		}
		if("modifyPWD".equals(operation)){
			modifyPWD(request,response);
		}
	}
	//注销登录
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);//防止创建Session 
		try{
	        if(session == null){  
				response.sendRedirect("/TeachingAssistantSystem/common/login.jsp");
	        }  
	        else {
	        	 session.removeAttribute("userID");  
	        	 session.removeAttribute("usetType");
	        	 session.invalidate();
				response.sendRedirect("/TeachingAssistantSystem/common/login.jsp");
			}
		}catch(IOException e){
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
	//修改密码
	private void modifyPWD(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userID=(String)session.getAttribute("userID");
		String userType=(String)session.getAttribute("userType");
		String password=request.getParameter("password");
		User user=new User();
		user.setUserID(userID);
		user.setUserType(userType);
		user.setPassword(password);
		try {
			business.modifyPWD(user);
			request.setAttribute("message", "<script type='text/javascript'>alert('修改成功')</script>");
			request.getRequestDispatcher("/common/modifyPWD.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} 
	}

	private void findPWD(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			business.findPWD(user);
			request.setAttribute("message", "<script type='text/javascript'>alert('密码已发送至你的邮箱！')</script>");
			request.getRequestDispatcher("/common/login.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}  catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		String loginRes=null;
		try {
			BeanUtils.populate(user, request.getParameterMap());
			loginRes=business.login(user);
			if(loginRes.equals("登录成功")){
				HttpSession session = request.getSession(true); 
				//session.setMaxInactiveInterval(60);
			    session.setAttribute("userID",user.getUserID()); 
			    session.setAttribute("userType", user.getUserType());
			    if(user.getUserType().equals("管理员")){
			    	response.sendRedirect("/TeachingAssistantSystem/admin/mainFrm.jsp");
			    }
			    else if(user.getUserType().equals("教师")){
			    	response.sendRedirect("/TeachingAssistantSystem/client/teacher/mainFrm.jsp");
			    }
			    else{
			    	response.sendRedirect("/TeachingAssistantSystem/client/student/mainFrm.jsp");
			    }
			}
			else if(loginRes.equals("密码有误")){
				request.setAttribute("message", "<script type='text/javascript'>alert('密码有误')</script>");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			}
			else if(loginRes.equals("不存在该用户")){
				request.setAttribute("message", "<script type='text/javascript'>alert('用户不存在，请检查用户编号或联系管理员！')</script>");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			}
			else if(loginRes.equals("用户类型错误")){
				request.setAttribute("message", "<script type='text/javascript'>alert('请选择正确的用户类型！')</script>");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	//删除用户
	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userID=request.getParameter("userID");
		String userType=request.getParameter("userType");
		User user=new User();
		user.setUserID(userID);
		user.setUserType(userType);
		try {
			business.deleteUser(user);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		checkUser(request,response);
	}
	//查看所有用户
	private void checkUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> uList;
		try{
			uList = business.checkUser();
			request.setAttribute("uList", uList);
			request.getRequestDispatcher("/admin/checkUser.jsp").forward(request, response);
		}catch(SQLException e){
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
	//添加用户
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			business.addUser(user);
			request.setAttribute("message", "<script type='text/javascript'>alert('添加成功！')</script>");
			request.getRequestDispatcher("/admin/addUser.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
