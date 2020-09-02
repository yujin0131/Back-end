package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vo.AirdndHomePictureVO;
import vo.AirdndUserResInfoVO;

@Repository("airdndUserResInfoDAO")
public class AirdndUserResInfoDAO implements AirdndUserResInfoDAOI {
	@Autowired
	DataSource dataSource;
	
	@Override
	public List<AirdndUserResInfoVO> selectUserResInfo() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//서버 연결할 때 order by 앞에 "WHERE user_idx= + user_idx(세션이나 쿠키에서 받아온 값) <- 추가
		//유저 및 게스트 프로필 사진 가져오는 쿼리문 추가(연결 후)
		List<AirdndUserResInfoVO> list = jdbcTemplate.query("select * from airdnd_user_res_info order by checkin desc, idx desc", new RowMapper<AirdndUserResInfoVO>() {
			@Override
			public AirdndUserResInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndUserResInfoVO list = new AirdndUserResInfoVO(
					rs.getInt("idx"),
					rs.getInt("user_idx"),
					rs.getInt("home_idx"),
					rs.getString("checkin"),
					rs.getString("checkout"),
					rs.getInt("guest_idx"),
					rs.getInt("is_canceled"));

				return list;
			}
		});
		
		return list;
	}
	
	@Override	
	public List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndHomePictureVO> list = jdbcTemplate.query("SELECT * FROM (SELECT * FROM AirdndDB.airdnd_home_picture as P WHERE home_idx=" + home_idx + ") P", new RowMapper<AirdndHomePictureVO>() {
			@Override
			public AirdndHomePictureVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndHomePictureVO list = new AirdndHomePictureVO(
					rs.getInt("idx"),
					rs.getInt("home_idx"),
					rs.getString("url"));
				return list;
			}
		});

		return list;
	}
}
