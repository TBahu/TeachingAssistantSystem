package ustc.sse.sa16225300.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import ustc.sse.sa16225300.domain.FileInfo;

public interface FileInfoDao {
	void uploadFile(FileInfo fileinfo, FileItem item) throws SQLException, ParseException;
	void deleteFile(String id) throws SQLException;
	void downloadFile(String id, OutputStream os) throws SQLException, FileNotFoundException, IOException;
	List<FileInfo> checkFile() throws SQLException;
}
