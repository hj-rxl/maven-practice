package kr.co.greenart.db;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Select;

public interface DBTimeMapper {
	@Select("select current_timestamp()")
	LocalDateTime selectDbTime();
}
