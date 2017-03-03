package ustc.sse.sa16225300.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ustc.sse.sa16225300.domain.FileInfo;
import ustc.sse.sa16225300.service.BusinessService;
import ustc.sse.sa16225300.service.impl.BusinessServiceImpl;
import ustc.sse.sa16225300.util.IdGenerator;

public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService business=new BusinessServiceImpl();
	private static Logger logger = Logger.getLogger(UserServlet.class); 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if("checkFile".equals(operation)){
			checkFile(request,response);
		}
		if("deleteFile".equals(operation)){
			deleteFile(request,response);
		}
		if("downloadFile".equals(operation)){
			downloadFile(request,response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)){
			uploadFile(request,response);
		}
		doGet(request, response);
	}
	
	//下载文件
	private void downloadFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		try {
			OutputStream os=response.getOutputStream();
			response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("UTF-8"),"iso-8859-1"));
			response.setContentType("application/octet-stream");
			business.downloadFile(id,os);
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

	private void deleteFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		try {
			business.deleteFile(id);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		checkFile(request, response);
	}

	//查看文件
	private void checkFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userType=(String)session.getAttribute("userType");
		try {
		List<FileInfo> fList= business.checkFile();
		request.setAttribute("fList", fList);
			if(userType.equals("管理员")){
				request.getRequestDispatcher("/admin/checkFile.jsp").forward(request, response);
			}
			else if(userType.equals("教师")){
				request.getRequestDispatcher("/client/teacher/checkFile.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/client/student/checkFile.jsp").forward(request, response);
			}
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
		} catch (ServletException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
	
	//上传文件
	private void uploadFile(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		String userType=(String)session.getAttribute("userType");
		FileInfo fileinfo=new FileInfo();
		fileinfo.setId(IdGenerator.genPrimaryKey());
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload/"); 
		fileinfo.setSavePath(savePath);
		String tempPath=savePath+"/temp";
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {  
		    tmpFile.mkdir();  
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();  
	    factory.setSizeThreshold(1024 * 100);
	    factory.setRepository(tmpFile);
	    ServletFileUpload upload = new ServletFileUpload(factory);   
        int maxSize=50*1024*1024;//能上传的文件最大为50M
        try{
	        @SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);  
	        for (FileItem item : list) {  
	            if (item.isFormField()) {  //是普通表单输入项
	                String name = item.getFieldName();  
	                String value = item.getString("UTF-8");  
	                if(name.equals("uploader")){
	                	fileinfo.setUploader(value);
	                }
	                else if(name.equals("uploadTime")){
	                	fileinfo.setUploadTime(value);
	                }
	                else if(name.equals("description")){
	                	fileinfo.setDescription(value);
	                }
	            } 
	            else {                     //是上传文件  
	                String filename = item.getName();  
	                if (filename == null || filename.trim().equals("")) {  
	                	request.setAttribute("message", "<script type='text/javascript'>alert('文件名不能为空!')</script>");
	                	break;
	                }  
	                if(item.getSize()>maxSize){
	                	request.setAttribute("message", "<script type='text/javascript'>alert('文件太大！')</script>");
	                	break;
	                }
	                filename = fileinfo.getId()+"_"+filename.substring(filename.lastIndexOf("\\") + 1); 
	                fileinfo.setName(filename);
	                business.uploadFile(fileinfo, item);
					request.setAttribute("message", "<script type='text/javascript'>alert('上传成功！')</script>");
	            }  
	        } 
	        try {
	        	if(userType.equals("管理员")){
	        		request.getRequestDispatcher("/admin/uploadFile.jsp").forward(request, response);
				}
				else if(userType.equals("教师")){
					request.getRequestDispatcher("/client/teacher/uploadFile.jsp").forward(request, response);
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				String errorMsg = "IO异常,请重试";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("../common/error.jsp").forward(request, response);
			}
        }catch(Exception e){
        	logger.error(e.getMessage());
        	String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
        }
	}
}
