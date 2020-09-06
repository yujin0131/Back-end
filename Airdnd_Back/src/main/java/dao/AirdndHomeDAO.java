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

import vo.AirdndBedroomVO;
import vo.AirdndDistanceVO;
import vo.AirdndFacilityVO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndNoticeVO;
import vo.AirdndReviewVO;
import vo.AirdndRuleVO;
import vo.AirdndSearchVO;
import vo.AirdndUserVO;

@Repository("homeDAO")
public class AirdndHomeDAO implements AirdndHomeDAOI {

	@Autowired
	DataSource dataSource;

	//호스트 정보
	@Override
	public AirdndHomeVO selectHost(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		AirdndHomeVO hostvo = jdbcTemplate.queryForObject("select * from airdnd_host where home_idx='" + home_idx + "'",
			new RowMapper<AirdndHomeVO>() {

				@Override
				public AirdndHomeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					AirdndHomeVO vo = new AirdndHomeVO();
					vo.setCheck_superhost(rs.getInt("check_superhost"));
					 vo.setCheck_certification(rs.getInt("check_certification"));
					 vo.setHost_review_num(rs.getInt("host_review_num"));
					 vo.setHost_name(rs.getString("host_name"));
					 vo.setHost_sign_in_date(rs.getString("host_sign_in_date"));
					 vo.setHost_status_message(rs.getString("host_status_message"));
					 vo.setInteraction_with_guests(rs.getString("Interaction_with_guests"));
					 vo.setHost_language(rs.getString("host_language"));
					 vo.setResponse_rate(rs.getString("response_rate"));
					 vo.setResponse_time(rs.getString("response_time"));
					 vo.setHost_profileImg(rs.getString("host_profileImg"));
					 return vo;
				}
			});
		return hostvo;
	}

	//전반적 집
	@Override
	public AirdndHomeVO selectHome(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		AirdndHomeVO homevo = jdbcTemplate.queryForObject("select * from airdnd_home where home_idx='" + home_idx + "'",
			new RowMapper<AirdndHomeVO>(){
				@Override
				public AirdndHomeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					AirdndHomeVO vo = new AirdndHomeVO();
					vo.setPlace(rs.getString("place"));
					vo.setTitle(rs.getString("title"));
					vo.setAddr(rs.getString("addr"));
					vo.setLat(rs.getDouble("lat"));
					vo.setLng(rs.getDouble("lng"));
					vo.setSub_title(rs.getString("sub_title"));
					vo.setFilter_max_person(rs.getInt("filter_max_person"));
					vo.setFilter_bedroom(rs.getInt("filter_bedroom"));
					vo.setFilter_bed(rs.getInt("filter_bed"));
					vo.setFilter_bathroom(rs.getInt("filter_bathroom"));
					vo.setPrice(rs.getInt("price"));
					vo.setHost_notice(rs.getString("host_notice"));
					vo.setLoc_info(rs.getString("loc_info"));
					return vo;
				}
		});
		
		return homevo;
	}
	
	//집주변 놀거리
	@Override
	public List<AirdndDistanceVO> selectDistance(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndDistanceVO> list = jdbcTemplate.query("select * from airdnd_home_attractions_distance where home_idx=" + home_idx, new RowMapper<AirdndDistanceVO>() {

	         @Override
	         public AirdndDistanceVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	        	 AirdndDistanceVO list = new AirdndDistanceVO(
	                  rs.getString("attractions_name"),
	                  rs.getString("attractions_distance"));

	            return list;
	         }

	      });

	      return list;
	}
	
	//사진
	@Override
	public List<AirdndHomePictureVO> selectPicture(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndHomePictureVO> list = jdbcTemplate.query("select * from airdnd_home_picture where home_idx=" + home_idx, new RowMapper<AirdndHomePictureVO>() {

	         @Override
	         public AirdndHomePictureVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	        	 AirdndHomePictureVO list = new AirdndHomePictureVO(
	                  rowNum, rowNum, rs.getString("url"));

	            return list;
	         }

	      });

	      return list;
	}
	
	//침실안내
	@Override
	public List<AirdndBedroomVO> selectBedroom(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndBedroomVO> list = jdbcTemplate.query("select * from airdnd_home_bed where home_idx=" + home_idx, new RowMapper<AirdndBedroomVO>() {

	         @Override
	         public AirdndBedroomVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	        	 AirdndBedroomVO list = new AirdndBedroomVO(
	                  rs.getString("bed_room_name"),
	                  rs.getString("bed_room_option"),
	                  rs.getString("bed_icons"));

	            return list;
	         }

	      });
		return list;
	}
	
	//시설안내
	@Override
	public List<AirdndFacilityVO> selectFacility(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndFacilityVO> list = jdbcTemplate.query("select * from airdnd_home_convenient_facility where home_idx=" + home_idx, new RowMapper<AirdndFacilityVO>() {

	         @Override
	         public AirdndFacilityVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	        	 AirdndFacilityVO list = new AirdndFacilityVO(
	                  rs.getString("facility"),
	                  rs.getString("facility_icon"));

	            return list;
	         }

	      });
		return list;
	}
	
	//안내
	@Override
	public List<AirdndNoticeVO> selectNotice(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndNoticeVO> list = jdbcTemplate.query("select * from airdnd_home_notice where home_idx=" + home_idx, new RowMapper<AirdndNoticeVO>() {

	         @Override
	         public AirdndNoticeVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	        	 AirdndNoticeVO list = new AirdndNoticeVO(
	                  rs.getString("home_notice_sort"),
	                  rs.getString("home_notice_content"),
	                  rs.getString("home_notice_icon"));

	            return list;
	         }

	      });
		return list;
	}
	
	//리뷰
	@Override
	public List<AirdndReviewVO> selectReview(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndReviewVO> list = jdbcTemplate.query("select * from airdnd_home_review where home_idx=" + home_idx, new RowMapper<AirdndReviewVO>() {

	         @Override
	         public AirdndReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	        	 AirdndReviewVO list = new AirdndReviewVO(
	                  rs.getString("user_name"),
	                  rs.getString("review_date"),
	                  rs.getString("review_content"),
	                  rs.getDouble("room_cleanliness"),
	                  rs.getDouble("room_accuracy"),
	                  rs.getDouble("room_communication"),
	                  rs.getDouble("room_position"),
	                  rs.getDouble("room_checkin"),
	                  rs.getDouble("room_cost_effectiveness"));

	            return list;
	         }

	      });
		return list;
	}
	
	//안전규칙
	@Override
	public List<AirdndRuleVO> selectSafetyRule(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndRuleVO> list = jdbcTemplate.query("select * from airdnd_home_safety_rule where home_idx=" + home_idx, new RowMapper<AirdndRuleVO>() {

	         @Override
	         public AirdndRuleVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	        	 AirdndRuleVO list = new AirdndRuleVO(
	        			 rs.getString("safety_rule"), null);

	            return list;
	         }

	      });
		return list;
	}
	
	//사용규칙
	@Override
	public List<AirdndRuleVO> selectUseRule(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndRuleVO> list = jdbcTemplate.query("select * from airdnd_home_use_rule where home_idx=" + home_idx, new RowMapper<AirdndRuleVO>() {

	         @Override
	         public AirdndRuleVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	        	 AirdndRuleVO list = new AirdndRuleVO(
	                  null, rs.getString("use_rule"));

	            return list;
	         }

	      });
		return list;
	}
}
