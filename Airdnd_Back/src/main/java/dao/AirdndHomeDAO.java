package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vo.AirdndBedroomVO;
import vo.AirdndBookmarkedHomesVO;
import vo.AirdndDistanceVO;
import vo.AirdndFacilityVO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndHostVO;
import vo.AirdndNoticeVO;
import vo.AirdndReviewVO;
import vo.AirdndSafetyRuleVO;

import vo.AirdndUseRuleVO;
import vo.AirdndUserResInfoVO;

@Repository("homeDAO")
public class AirdndHomeDAO implements AirdndHomeDAOI {

	@Autowired
	DataSource dataSource;

	//호스트 정보
	@Override
	public AirdndHostVO selectHost(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		AirdndHostVO hostvo = jdbcTemplate.queryForObject("select * from airdnd_host where home_idx='" + home_idx + "'",
				new RowMapper<AirdndHostVO>() {

			@Override
			public AirdndHostVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndHostVO vo = new AirdndHostVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setCheck_superhost(rs.getBoolean("check_superhost"));
				vo.setCheck_certification(rs.getBoolean("check_certification"));
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
						rs.getInt("idx"),
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
	public List<AirdndSafetyRuleVO> selectSafetyRule(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndSafetyRuleVO> list = jdbcTemplate.query("select * from airdnd_home_safety_rule where home_idx=" + home_idx, new RowMapper<AirdndSafetyRuleVO>() {
			@Override
			public AirdndSafetyRuleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndSafetyRuleVO list = new AirdndSafetyRuleVO(rowNum, rowNum, rs.getString("safety_rule"));

				return list;
			}

		});
		return list;
	}

	//사용규칙
	@Override
	public List<AirdndUseRuleVO> selectUseRule(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndUseRuleVO> list = jdbcTemplate.query("select * from airdnd_home_use_rule where home_idx=" + home_idx, new RowMapper<AirdndUseRuleVO>() {
			@Override
			public AirdndUseRuleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndUseRuleVO list = new AirdndUseRuleVO(rowNum, rowNum, rs.getString("use_rule"));

				return list;
			}
		});
		return list;
	}

	public List<AirdndUserResInfoVO> selectUserResInfo(int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndUserResInfoVO> list = jdbcTemplate.query("select * from airdnd_user_res_info where home_idx=" + home_idx, new RowMapper<AirdndUserResInfoVO>() {
			@Override
			public AirdndUserResInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndUserResInfoVO list = new AirdndUserResInfoVO(rowNum, rowNum, rowNum, rs.getString("checkin"), null, rowNum, rowNum);

				return list;
			}
		});
		return list;
	}

	@Override
	public List<AirdndUserResInfoVO> selectUserResInfo(int home_idx, String checkin, String checkout) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndUserResInfoVO> list = null;
		try {
			list = jdbcTemplate.query("SELECT * FROM airdnd_user_res_info where home_idx=" + home_idx + " and (checkin>='" + checkin + "' AND checkin<='" + checkout + "' or checkout>='" + checkin + "' and checkout <='" + checkout + "');", new RowMapper<AirdndUserResInfoVO>() {
				@Override
				public AirdndUserResInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					AirdndUserResInfoVO list = new AirdndUserResInfoVO(rowNum, rowNum, rowNum, rs.getString("checkin"), null, rowNum, rowNum);
					return list;
				}
			});
		} catch (Exception e) {
		}
		return list;
	}

	@Override
	public AirdndBookmarkedHomesVO selectBookmarkedHomes(int signInIdx, int home_idx) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		AirdndBookmarkedHomesVO vo = new AirdndBookmarkedHomesVO();

		try {

			vo = jdbcTemplate.queryForObject("select * from airdnd_bookmarked_homes where user_idx='" + signInIdx + "' and home_idx='" + home_idx + "'",
					new RowMapper<AirdndBookmarkedHomesVO>(){
				@Override
				public AirdndBookmarkedHomesVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					AirdndBookmarkedHomesVO vo = new AirdndBookmarkedHomesVO();
					vo.setIdx(rs.getInt("idx"));
					if (rs.wasNull()) vo.setIdx(0);
					vo.setBookmark_idx(rs.getInt("bookmark_idx"));
					if (rs.wasNull()) vo.setBookmark_idx(0);
					vo.setUser_idx(rs.getInt("user_idx"));
					if (rs.wasNull()) vo.setUser_idx(0);
					vo.setHome_idx(rs.getInt("home_idx"));
					if (rs.wasNull()) vo.setHome_idx(0);
					return vo;
				}
			});
		} catch (Exception e) {
			vo.setBookmark_idx(0);
			vo.setHome_idx(0);
			vo.setIdx(0);
			vo.setUser_idx(0);
		}

		return vo;
	}
	
	@Override
	public int book(AirdndUserResInfoVO vo) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int userIdx = vo.getUser_idx();
		int homeIdx = vo.getHome_idx();
		String checkin = vo.getCheckin();
		String checkout = vo.getCheckout();
		int guestIdx = vo.getGuest_idx();
		int res = jdbcTemplate.update("insert into airdnd_user_res_info (user_idx, home_idx, checkin, checkout, guest_idx, is_canceled) "
				+ "VALUES(?,?,?,?,?,0)", userIdx, homeIdx, checkin, checkout, guestIdx);
		return res;
	}
	
}
