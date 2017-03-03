package ustc.sse.sa16225300.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.fileupload.FileItem;

import ustc.sse.sa16225300.dao.FileInfoDao;
import ustc.sse.sa16225300.domain.FileInfo;
import ustc.sse.sa16225300.util.DBCPUtil;

public class FileInfoDaoImpl implements FileInfoDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	//上传文件
	public void uploadFile(FileInfo fileinfo, FileItem item) throws SQLException, ParseException{
		String sql="insert into FileInfo(id,name,uploadTime,savePath,description,uploader) values(?,?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		java.util.Date date = sdf.parse(fileinfo.getUploadTime()); 
		Date sDate=new Date(date.getTime());
		qr.update(sql, fileinfo.getId(),fileinfo.getName(),sDate,fileinfo.getSavePath(),fileinfo.getDescription(),fileinfo.getUploader());
	    //已经确保文件名（已经加上UUID）不为空，文件大小不超过限制
	    File saveFile=new File(fileinfo.getSavePath(), fileinfo.getName());
		try {
			item.write(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除文件
	public void deleteFile(String id) throws SQLException {
		String sql="delete from FileInfo where id=?";
		String sqlSelect="select * from FileInfo where id=?";
		FileInfo fileInfo=qr.query(sqlSelect, new BeanHandler<FileInfo>(FileInfo.class),id);
		File delFile=new File(fileInfo.getSavePath(),fileInfo.getName());
		delFile.delete();
		qr.update(sql,id);
	}
	//查看文件
	public List<FileInfo> checkFile() throws SQLException {
		List<FileInfo> list=new ArrayList<FileInfo>();
		String sql="select * from FileInfo order by uploadTime desc";
		List<FileInfo> dbList=qr.query(sql, new BeanListHandler<FileInfo>(FileInfo.class));
		Iterator<FileInfo> iterator=dbList.iterator();
		while(iterator.hasNext()){
			FileInfo fileInfo=iterator.next();
			String fileName=fileInfo.getName();
			fileName=fileName.substring(fileName.indexOf("_")+1);
			fileInfo.setName(fileName);
			list.add(fileInfo);
		}
		return list;
	}
	//下载文件
	public void downloadFile(String id, OutputStream os) throws SQLException, IOException {
		String sqlSelect="select * from FileInfo where id=?";
		FileInfo fileInfo = qr.query(sqlSelect, new BeanHandler<FileInfo>(FileInfo.class),id);
		File dFile=new File(fileInfo.getSavePath(), fileInfo.getName());
		InputStream in=new FileInputStream(dFile);
		byte buffer[] = new byte[1024];  
		int len = 0;  
		while ((len = in.read(buffer)) > 0) {  
			os.write(buffer, 0, len);  
		}  
		in.close();
	}
}
