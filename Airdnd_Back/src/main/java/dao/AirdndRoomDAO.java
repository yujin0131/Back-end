package dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vo.AirdndRoomVO;

public class AirdndRoomDAO {


	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public AirdndRoomVO select(){
		AirdndRoomVO vo = sqlSession.selectOne("a.airdnd_room");
		return vo;	
	}
}
