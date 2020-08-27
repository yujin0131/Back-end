package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
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
	public int select(String email_check) {
		
		int res = -1;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select count(*) from airdnd_user where email='" + email_check + "'";
		
		//사용가능한 이메일이면 0, 있으면 1 나오게,,
		res = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return res;

		
	}
	

}
