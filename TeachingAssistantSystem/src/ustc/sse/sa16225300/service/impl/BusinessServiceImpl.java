package ustc.sse.sa16225300.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import ustc.sse.sa16225300.dao.FileInfoDao;
import ustc.sse.sa16225300.dao.GradeDao;
import ustc.sse.sa16225300.dao.NoticeDao;
import ustc.sse.sa16225300.dao.UserDao;
import ustc.sse.sa16225300.dao.impl.FileInfoDaoImpl;
import ustc.sse.sa16225300.dao.impl.GradeDaoImpl;
import ustc.sse.sa16225300.dao.impl.NoticeDaoImpl;
import ustc.sse.sa16225300.dao.impl.UserDaoImpl;
import ustc.sse.sa16225300.domain.FileInfo;
import ustc.sse.sa16225300.domain.Grade;
import ustc.sse.sa16225300.domain.GradeTable;
import ustc.sse.sa16225300.domain.Notice;
import ustc.sse.sa16225300.domain.User;
import ustc.sse.sa16225300.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
	//系统管理模块
	private UserDao uDao=new UserDaoImpl();
	public String login(User user) throws SQLException {
		return uDao.login(user);
	}
	public void addUser(User user) throws SQLException {
		uDao.addUser(user);
	}
	public void deleteUser(User user) throws SQLException {
		uDao.deleteUser(user);
	}
	public List<User> checkUser() throws SQLException {
		return uDao.checkUser();
	}
	public String findPWD(User user) throws SQLException {
		return uDao.findPWD(user);
	}
	public void modifyPWD(User user) throws SQLException {
		uDao.modifyPWD(user);
	}
	
	//资源管理模块
	private FileInfoDao fDao=new FileInfoDaoImpl();
	public void uploadFile(FileInfo fileinfo, FileItem item) throws SQLException, ParseException{
		fDao.uploadFile(fileinfo, item);
	}
	public void deleteFile(String id) throws SQLException {
		fDao.deleteFile(id);
	}
	public void downloadFile(String id, OutputStream os) throws FileNotFoundException, SQLException, IOException{
		fDao.downloadFile(id, os);
	}
	public List<FileInfo> checkFile() throws SQLException {
		return fDao.checkFile();
	}

	//通知管理模块
	private NoticeDao nDao=new NoticeDaoImpl();
	public void releaseNotice(Notice notice) throws SQLException, ParseException {
		nDao.releaseNotice(notice);
	}
	public void deleteNotice(String id) throws SQLException {
		nDao.deleteNotice(id);
	}
	public List<Notice> checkNotice() throws SQLException {
		return nDao.checkNotice();
	}
	public Notice getNotice(String id) throws SQLException {
		return nDao.getNotice(id);
	}
	
	//成绩管理
	private GradeDao gDao=new GradeDaoImpl();
	public void inputGrade(Grade grade) throws SQLException {
		gDao.inputGrade(grade);
	}
	public void deleteGrade(Grade grade) throws SQLException {
		gDao.deleteGrade(grade);
		
	}
	public void modifyGrade(Grade grade) throws SQLException {
		gDao.modifyGrade(grade);
	}
	public List<Grade> aCheckGrade() throws SQLException {
		return gDao.aCheckGrade();
	}
	public List<GradeTable> getGradeTable() throws SQLException {
		return gDao.getGradeTable();
	}
	public List<Grade> getGrade(String studentID) throws SQLException {
		return gDao.getGrade(studentID);
	}
}
