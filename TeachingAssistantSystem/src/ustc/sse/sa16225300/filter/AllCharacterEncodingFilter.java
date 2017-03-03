package ustc.sse.sa16225300.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class AllCharacterEncodingFilter implements Filter {
	private FilterConfig filterConfig;
	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		//得到编码：配置文件中获取
		String encode = filterConfig.getInitParameter("encoding");
		if(encode==null)
			encode = "UTF-8";
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode);
		MyHttpServletRequest mrequest = new MyHttpServletRequest(request);
		chain.doFilter(mrequest, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
class MyHttpServletRequest extends HttpServletRequestWrapper{
	public MyHttpServletRequest(HttpServletRequest request){
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value==null)
			return value;
		//get请求方式asa
		String method = super.getMethod();
		if("get".equalsIgnoreCase(method)){
			try {
				value = new String(value.getBytes("ISO-8859-1"),super.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
}