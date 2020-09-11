package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vo.AirdndBookmarkVO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndHostVO;
import vo.AirdndUseRuleVO;
import vo.AirdndUserResInfoVO;
import vo.AirdndUserVO;

@Repository("airdndUserResInfoDAO")
public class AirdndUserResInfoDAO implements AirdndUserResInfoDAOI {
	@Autowired
	DataSource dataSource;
	
	//Select user's reservation home list
	@Override
	public List<AirdndUserResInfoVO> selectUserResInfo(int user_idx) {
		String sql = "select * from airdnd_user_res_info where user_idx=" + user_idx + " order by checkin desc, idx desc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//유저 및 게스트 프로필 사진 가져오는 쿼리문 추가(연결 후)
		List<AirdndUserResInfoVO> list = jdbcTemplate.query(sql, new RowMapper<AirdndUserResInfoVO>() {
			@Override
			public AirdndUserResInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndUserResInfoVO list = new AirdndUserResInfoVO(
					rs.getInt("idx"),
					rs.getInt("user_idx"),
					rs.getInt("home_idx"),
					rs.getString("checkin"),
					rs.getString("checkout"),
					rs.getInt("guest_idx"),
					rs.getInt("is_canceled"),
					rs.getInt("adult"),
					rs.getInt("child"),
					rs.getInt("infant"));

				return list;
			}
		});
		
		return list;
	}
	
	//Select user's information
	@Override
	public AirdndUserVO selectUserInfo(int user_idx) {
		String sql = "select * from airdnd_user where user_idx=" + user_idx;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndUserVO> list = jdbcTemplate.query(sql, new RowMapper<AirdndUserVO>() {
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
					rs.getString("description"));

				return list;
			}
		});
		
		return list.get(0);
	}
	
	//Select user's reservation home's main picture
	@Override
	public List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx){
		String sql = "SELECT * FROM (SELECT * FROM airdnd_home_picture as P WHERE home_idx=" + home_idx + ") P";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndHomePictureVO> list = jdbcTemplate.query(sql, new RowMapper<AirdndHomePictureVO>() {
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
	
	//Select host's information
	@Override
	public AirdndHostVO selectHostInfo(int home_idx) {
		String sql = "select * from airdnd_host where home_idx=" + home_idx;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndHostVO> list = jdbcTemplate.query(sql, new RowMapper<AirdndHostVO>() {
			@Override
			public AirdndHostVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndHostVO list = new AirdndHostVO(
					rs.getInt("idx"),
					rs.getInt("home_idx"),
					rs.getBoolean("check_superhost"),
					rs.getBoolean("check_certification"),
					rs.getInt("host_review_num"),
					rs.getString("host_name"),
					rs.getString("host_sign_in_date"),
					rs.getString("host_status_message"),
					rs.getString("interaction_with_guests"),
					rs.getString("host_language"),
					rs.getString("response_rate"),
					rs.getString("response_time"),
					rs.getString("host_profileImg"));

				return list;
			}
		});
		
		return list.get(0);
	}
	
	//Select home's information
	@Override
	public AirdndHomeVO selectHomeInfo(int home_idx) {
		String sql = "select * from airdnd_home where home_idx=" + home_idx;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndHomeVO> list = jdbcTemplate.query(sql, new RowMapper<AirdndHomeVO>() {
			@Override
			public AirdndHomeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndHomeVO list = new AirdndHomeVO(
					rs.getInt("home_idx"),
					rs.getString("place"),
					rs.getString("title"),
					rs.getBoolean("isSuperhost"),
					rs.getString("addr"),
					rs.getDouble("lat"),
					rs.getDouble("lng"),
					rs.getString("sub_title"),
					rs.getInt("filter_max_person"),
					rs.getInt("filter_bedroom"),
					rs.getInt("filter_bed"),
					rs.getInt("filter_bathroom"),
					rs.getInt("price"),
					rs.getString("host_notice"),
					rs.getString("loc_info"));

				return list;
			}
		});
		
		return list.get(0);
	}
	
	//Select UseRule's information
	@Override
	public List<AirdndUseRuleVO> selectUseRuleInfo(int home_idx) {
		String sql = "select * from airdnd_home_use_rule where home_idx=" + home_idx;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndUseRuleVO> list = jdbcTemplate.query(sql, new RowMapper<AirdndUseRuleVO>() {
			@Override
			public AirdndUseRuleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndUseRuleVO list = new AirdndUseRuleVO(
					rs.getInt("idx"),
					rs.getInt("home_idx"),
					rs.getString("use_rule"));

				return list;
			}
		});
		
		return list;
	}
	
	//Update is_canceled
	@Override
	public int userResIsCanceled(int idx) {
		String sql = "update airdnd_user_res_info set is_canceled=1 where idx=" + idx;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int res = jdbcTemplate.update(sql);
		
		return res;
	}
}
