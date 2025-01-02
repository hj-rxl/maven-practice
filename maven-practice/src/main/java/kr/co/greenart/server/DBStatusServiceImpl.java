package kr.co.greenart.server;

import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import kr.co.greenart.db.DBTimeMapper;
import kr.co.greenart.util.DBUtil;

public class DBStatusServiceImpl implements DBStatusService {
	@Override
	public LocalDateTime selectDbTime() {
		try(SqlSession session = DBUtil.getSqlSession()) {
			DBTimeMapper mapper = session.getMapper(DBTimeMapper.class);
			
			return mapper.selectDbTime();
		}
	}
}
