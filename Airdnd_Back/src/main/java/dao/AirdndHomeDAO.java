package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import vo.AirdndHomeVO;


@Repository("airdndDAO")
public class AirdndHomeDAO implements AirdndHomeDAOI{

	@Autowired
	DataSource dataSource;

	@Override	
	public List<AirdndHomeVO> select(){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndHomeVO> list = jdbcTemplate.query("select * from airdnd_room_test", new RowMapper<AirdndHomeVO>() {

			@Override
			public AirdndHomeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
	
				AirdndHomeVO list = new AirdndHomeVO(
						rs.getInt("room_idx"),
						rs.getString("room_name"),
						rs.getString("room_price"),
						rs.getString("room_score"),
						rs.getString("room_review_num"),
						rs.getString("room_type"),
						rs.getString("room_option"));


				return list;
			}

		});
		
		return list;
		

	}
	
	@Override
	public int insert(AirdndHomeVO vo){
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "insert into airdnd_room_test values(0, ?, ?, ?, ?, ?, ?)";
		int res = jdbcTemplate.update(sql, vo.getRoom_name(), vo.getRoom_price(), vo.getRoom_score(), vo.getRoom_review_num(), vo.getRoom_type(), vo.getRoom_option());
		System.out.println("행 삽입 성공");


		
		return res;
	}
}
