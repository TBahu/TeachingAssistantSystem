package ustc.sse.sa16225300.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ustc.sse.sa16225300.dao.UserDao;
import ustc.sse.sa16225300.domain.Mail;
import ustc.sse.sa16225300.domain.User;
import ustc.sse.sa16225300.util.DBCPUtil;
import ustc.sse.sa16225300.util.MD5Util;
import ustc.sse.sa16225300.util.MailUtils;
import ustc.sse.sa16225300.util.PwdGenerator;

public class UserDaoImpl implements UserDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	//登录
	public String login(User user) throws SQLException {
		List<User> loginUser;
		Boolean findUser=false;
		loginUser=qr.query("select * from User where UserID=?",new BeanListHandler<User>(User.class),user.getUserID());
		if(loginUser.isEmpty()){
			return "不存在该用户";
		}
		else{
			for(User dbUser:loginUser){
				if(dbUser.getUserType().equals(user.getUserType())){
					findUser=true;
					if(dbUser.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
						return "登录成功";
					}
					else{
						return "密码有误";
					}
				}
			}
			if(findUser==false){
				return "用户类型错误";
			}
		}
		return null;
	}
	//添加用户
	public void addUser(User user) throws SQLException {
		qr.update("insert into User(userID,userName,userType,password,phoneNumber,email) values(?,?,?,?,?,?)", user.getUserID(),user.getUserName(),user.getUserType(),MD5Util.getMD5(user.getPassword()),user.getPhoneNumber(),user.getEmail());
	}
	//删除用户
	public void deleteUser(User user) throws SQLException {
		qr.update("delete from User where UserID=? and UserType=?",user.getUserID(),user.getUserType());
	}
	//获取用户列表
	public List<User> checkUser() throws SQLException {
		return qr.query("select * from User", new BeanListHandler<User>(User.class));
	}
	//找回密码
	public String findPWD(User user) throws SQLException {
		User dbUser=null;
		String pwd=PwdGenerator.getPwd();
		user.setPassword(pwd);
		
		String mailAdd=user.getEmail();
		String sql="select * from User where userID=? and userType=?";
		dbUser=qr.query(sql, new BeanHandler<User>(User.class),user.getUserID(),user.getUserType());
		if(dbUser.getEmail().equals(user.getEmail())){
			modifyPWD(user);
			Mail mail = new Mail();  
			mail.setHost("mail.ustc.edu.cn"); // 设置邮件服务器,如果不用163的,自己找找看相关的  
			mail.setSender("junwangu@mail.ustc.edu.cn");  
			mail.setReceiver(mailAdd); // 接收人  
			mail.setUsername("junwangu@mail.ustc.edu.cn"); // 登录账号,一般都是和邮箱名一样吧  
			mail.setPassword("09jun21"); // 发件人邮箱的登录密码  
			mail.setSubject("找回密码");  
			mail.setMessage("您的新密码是："+pwd);  
			new MailUtils().send(mail);  
			return "新密码已发至您的邮箱！";
		}
		else{
			return "请输入您的科大邮箱或联系管理员！";
		}
	}
	//修改密码
	public void modifyPWD(User user) throws SQLException {
		String sql="update User set password=? where userID=? and userType=?";
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		qr.update(sql,user.getPassword(),user.getUserID(),user.getUserType());
	}
}
