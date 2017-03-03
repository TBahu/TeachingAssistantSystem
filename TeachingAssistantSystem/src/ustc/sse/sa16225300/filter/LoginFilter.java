package ustc.sse.sa16225300.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession(false);
        
        if (request.getServletPath().indexOf("common/findPWD.jsp")>0 
        		|| request.getServletPath().indexOf("common/login.jsp") > 0 
        		|| request.getServletPath().indexOf("servlet/UserServlet") > 0
        		|| request.getServletPath().indexOf("index.jsp")>0) {
            chain.doFilter(request, response);
        } 
        else {
        	if(session!=null){
        		String userID = (String) session.getAttribute("userID");
        		String userType=(String) session.getAttribute("userType");
        		if (userID == null || userType==null) {
                    response.sendRedirect("/TeachingAssistantSystem/common/login.jsp");
                    chain.doFilter(request, response);
                }
        		else{
        			if(userType.equals("管理员")){
        				if(request.getServletPath().indexOf("admin")>0 
        						|| request.getServletPath().indexOf("servlet")>0
        						|| request.getServletPath().indexOf("ueditor")>0
        						|| request.getServletPath().indexOf("common")>0){
        					chain.doFilter(request, response);
        				}
        				else{
        					response.sendRedirect("/TeachingAssistantSystem/common/login.jsp");
                            chain.doFilter(request, response);
        				}
        			}
        			else if(userType.equals("教师")){
        				if(request.getServletPath().indexOf("client/teacher")>0 
        						|| request.getServletPath().indexOf("servlet")>0
        						|| request.getServletPath().indexOf("ueditor")>0
        						|| request.getServletPath().indexOf("common")>0){
        					chain.doFilter(request, response);
        				}
        				else{
        					response.sendRedirect("/TeachingAssistantSystem/common/login.jsp");
                            chain.doFilter(request, response);
        				}
        			}
        			else if(userType.equals("学生")){
        				if(request.getServletPath().indexOf("client/student")>0 
        						|| request.getServletPath().indexOf("servlet")>0
        						|| request.getServletPath().indexOf("common")>0){
        					chain.doFilter(request, response);
        				}
        				else{
        					response.sendRedirect("/TeachingAssistantSystem/common/login.jsp");
                            chain.doFilter(request, response);
        				}
        			}
        		}
        	}
        	else {
                response.sendRedirect("/TeachingAssistantSystem/common/login.jsp");
            }
        }
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
