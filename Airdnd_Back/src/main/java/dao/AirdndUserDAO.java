package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vo.AirdndUserVO;

@Repository("userDAO")
public class AirdndUserDAO implements AirdndUserDAOI{
	
	@Autowired
	DataSource dataSource;

	@Override	
	public List<AirdndUserVO> select(){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndUserVO> list = jdbcTemplate.query("select * from airdnd_user", new RowMapper<AirdndUserVO>() {

			@Override
			public AirdndUserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
	
				AirdndUserVO list = new AirdndUserVO(
						rs.getInt("user_idx"),
						rs.getString("email"),
						rs.getString("pwd"),
						rs.getString("last_name"),
						rs.getString("first_name"),
						rs.getString("birthday"),
						rs.getString("profileImg"),
						rs.getString("phone"),
						rs.getString("signupDate"),
						rs.getString("description")
						);


				return list;
			}

		});
		
		return list;
	}
	
	@Override	
	public int insert(AirdndUserVO vo){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String email = vo.getEmail();
		String pwd = vo.getPwd();
		String last_name = vo.getLast_name();
		String first_name = vo.getFirst_name();
		String birthday = vo.getBirthday();

		int res = jdbcTemplate.update("insert into airdnd_user (user_idx, email, pwd, last_name, first_name, birthday, signupDate) "
				+ "VALUES (0, ?, ?, ?, ?, ?, now())", email, pwd, last_name, first_name, birthday);
		
		return res;
	}

}
