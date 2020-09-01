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

@Repository("airdndBookmarkDAO")
public class AirdndBookmarkDAO implements AirdndBookmarkDAOI {
	@Autowired
	DataSource dataSource;
	
	//Select bookmark list
	@Override
	public List<AirdndBookmarkVO> selectBookmark() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//서버 연결할 때 쿼리문 뒤에 "WHERE user_idx= + user_idx <- 추가
		List<AirdndBookmarkVO> list = jdbcTemplate.query("select * from airdnd_bookmark_list order by update_date_time", new RowMapper<AirdndBookmarkVO>() {
			@Override
			public AirdndBookmarkVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndBookmarkVO list = new AirdndBookmarkVO(
					rs.getInt("idx"),
					rs.getInt("user_idx"),
					rs.getInt("home_idx"),
					rs.getInt("review_idx"),
					rs.getString("bookmark_list_title"),
					rs.getString("checkin"),
					rs.getString("checkout"),
					rs.getString("update_date_time"));

				return list;
			}
		});
		
		return list;
	}
	
	//Select user's reservation home's main picture
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
	
	//Create a new bookmark
	@Override
	public AirdndBookmarkVO insert_bookmark(AirdndBookmarkVO vo) {
		String sql = "insert into AirdndDB.airdnd_bookmark_list(user_idx, home_idx, bookmark_list_title, checkin, checkout, update_date_time) values(?, ?, ?, ?, ?, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 9 HOUR))";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, vo.getUser_idx(), vo.getHome_idx(), vo.getBookmark_list_title(), vo.getCheckin(), vo.getCheckout());
		
		return vo;
	}
	
	//Add the home in the bookmark
	@Override
	public AirdndBookmarkVO update_bookmarkHome(AirdndBookmarkVO vo) {
		String sql = "update AirdndDB.airdnd_bookmark_list set home_idx=?, update_date_time=DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 9 HOUR) where idx=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, vo.getHome_idx(), vo.getIdx());
		
		return vo;
	}
	
	//Delete the home in the bookmark
	@Override
	public AirdndBookmarkVO delete_bookmarkHome(AirdndBookmarkVO vo) {
		String sql = "update AirdndDB.airdnd_bookmark_list set home_idx=0, update_date_time=DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 9 HOUR) where idx=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, vo.getIdx());
		
		return vo;
	}
}
