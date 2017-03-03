package ustc.sse.sa16225300.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import ustc.sse.sa16225300.domain.Notice;

public interface NoticeDao {
	void releaseNotice(Notice notice) throws SQLException, ParseException;
	void deleteNotice(String id) throws SQLException;
	List<Notice> checkNotice() throws SQLException;
	Notice getNotice(String id) throws SQLException;
}
