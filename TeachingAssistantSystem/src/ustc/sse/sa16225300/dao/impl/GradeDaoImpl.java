package ustc.sse.sa16225300.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ustc.sse.sa16225300.dao.GradeDao;
import ustc.sse.sa16225300.domain.Grade;
import ustc.sse.sa16225300.domain.GradeTable;
import ustc.sse.sa16225300.util.DBCPUtil;

public class GradeDaoImpl implements GradeDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	//上传成绩
	public void inputGrade(Grade grade) throws SQLException {
		String sql="insert into Grade(studentID,gradeType,score) values(?,?,?)";
		qr.update(sql,grade.getStudentID(),grade.getGradeType(),grade.getScore());
	}

	//管理员查看成绩
	public List<Grade> aCheckGrade() throws SQLException {
		String sql="select * from Grade";
		return qr.query(sql, new BeanListHandler<Grade>(Grade.class));
	}
	
	//教师获得成绩单
	public List<GradeTable> getGradeTable() throws SQLException {
		String sql="select * from TeacherView";
		return qr.query(sql, new BeanListHandler<GradeTable>(GradeTable.class));
	}
	
	//获取个人成绩详细信息
	public List<Grade> getGrade(String studentID) throws SQLException {
		return qr.query("select * from Grade where studentID=?", new BeanListHandler<Grade>(Grade.class),studentID);
	}

	//删除成绩
	public void deleteGrade(Grade grade) throws SQLException {
		String sql="delete from Grade where studentID=? and gradeType=?";
		qr.update(sql,grade.getStudentID(),grade.getGradeType());
	}

	//修改成绩
	public void modifyGrade(Grade grade) throws SQLException {
		String sql="update Grade set score=? where studentID=? and gradeType=?";
		qr.update(sql,grade.getScore(),grade.getStudentID(),grade.getGradeType());
	}
}
