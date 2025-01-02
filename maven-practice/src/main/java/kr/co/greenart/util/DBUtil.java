package kr.co.greenart.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

// dbcp (DB Connection Pool) 라이브러리 3개 활용
public class DBUtil {
	private static DataSource dataSource;
	private static SqlSessionFactory sqlSessionFactory; //mybatis
	
	static {
		initDataSource();
		initSqlSessionFactory();
	}

	private static void initDataSource() {
		//필요한 정보 설정만 하면 라이브러리에서 드라이버 로드 수행해줌
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/my_db");
		ds.setUsername("root");
		ds.setPassword("root");
		//많은 커넥션들을 활용하도록 하고싶으면
		ds.setInitialSize(0); //최초
		ds.setMaxTotal(8); //최대; 8개를 돌려쓰기
		ds.setMaxIdle(8); //최대 8개까지 idle 상태 가능
		ds.setMinIdle(0);
		
		dataSource = ds;
	}
	
	private static void initSqlSessionFactory() {
		//MyBatis 홈페이지 => 'XML 을 사용하지 않고 SqlSessionFactory 빌드하기' 복붙
		//복붙 후 ctrl+shift+o => ibatis import
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		//사용자정의 인터페이스 등록
		configuration.addMappers("kr.co.greenart.db"); //패키지 내의 모든 매퍼 일괄 등록
		
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}
	
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
