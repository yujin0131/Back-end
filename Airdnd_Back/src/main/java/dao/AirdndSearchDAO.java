package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vo.AirdndBookmarkedHomesVO;
import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;
import vo.AirdndUserVO;

@Repository("searchDAO")
public class AirdndSearchDAO implements AirdndSearchDAOI{

	@Autowired
	DataSource dataSource;

	@Override   
	public List<AirdndSearchVO> select(Map<Object, Object> param){
		int page = (Integer)(param.get("page")) * 20;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndSearchVO> list = jdbcTemplate.query("select * from airdnd_search_view_final where place='" + param.get("location")
		+ "' and filter_max_person>=" + param.get("guests") + " and filter_bed>=" + param.get("bedCount")+ " and filter_bedroom>=" + param.get("bedroomCount")
		+ " and filter_bathroom>="+ param.get("bathCount") + " and price>=" + param.get("priceMin") + " and price<=" + param.get("priceMax") 
		+ " and lat >= '" + param.get("swLat") + "' and lng >= '" + param.get("swLng") + "' and lat <= '" +param.get("neLat") + "' and lng <= '" + param.get("neLng")
		+ "' and (sub_title like '%" + param.get("roomTypeHouse1") + "%' or '%" + param.get("roomTypePrivate") + "%' or '%"  + param.get("roomTypeShared1")
		+ "%' or '%"  + param.get("roomTypeHouse2") + "%' or '%" + param.get("roomTypeShared2") + "%') and (host_language like '%" + param.get(0) 
		+ "%' or '%" + param.get(1) + "%' or '%" + param.get(2) + "%' or '%" + param.get(3) + "%' or '%" + param.get(4) + "%')" + param.get("Listquery") +" limit " + page + ", 20", new RowMapper<AirdndSearchVO>() {

			@Override
			public AirdndSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub

				AirdndSearchVO list = new AirdndSearchVO(
						rs.getInt("home_idx"),
						rs.getBoolean("isSuperHost"),
						rs.getString("sub_title"),
						rs.getString("title"),
						rs.getInt("filter_max_person"),
						rs.getInt("filter_bedroom"),
						rs.getInt("filter_bed"),
						rs.getInt("filter_bathroom"),
						rs.getInt("price"),
						rs.getDouble("rating"),
						rs.getInt("review_num"),
						rs.getString("lat"),
						rs.getString("lng"));

				return list;
			}

		});

		return list;

	}

	@Override   
	public List<AirdndHomePictureVO> pictureselect(int home_idx){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndHomePictureVO> list = jdbcTemplate.query("select * from airdnd_home_picture where home_idx=" + home_idx, new RowMapper<AirdndHomePictureVO>() {

			@Override
			public AirdndHomePictureVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub

				AirdndHomePictureVO list = new AirdndHomePictureVO(
						rs.getInt("idx"),
						rs.getInt("home_idx"),
						rs.getString("url"));

				return list;
			}

		});

		return list;
	}

	public List<AirdndSearchVO> totalselect(Map<Object, Object> param) {

		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndSearchVO> list = jdbcTemplate.query("select AVG(price) as average_price, COUNT(home_idx) as data_total from airdnd_search_view_final where place = '" + param.get("location") 
		+ "' and filter_max_person>=" + param.get("guests") + " and filter_bed>=" + param.get("bedCount")+ " and filter_bedroom>=" + param.get("bedroomCount")
		+ " and filter_bathroom>=" + param.get("bathCount") + " and price>=" + param.get("priceMin") + " and price<=" + param.get("priceMax") 
		+ " and lat >= '" + param.get("swLat") + "' and lng >= '" + param.get("swLng") + "' and lat <= '" +param.get("neLat") + "' and lng <= '" + param.get("neLng")
		+ "' and (sub_title like '%" + param.get("roomTypeHouse1") + "%' or '%" + param.get("roomTypePrivate") + "%' or '%"  + param.get("roomTypeShared1")
		+ "%' or '%"  + param.get("roomTypeHouse2") + "%' or '%" + param.get("roomTypeShared2") + "%') and (host_language like '%" + param.get(0) 
		+ "%' or '%" + param.get(1) + "%' or '%" + param.get(2) + "%' or '%" + param.get(3) + "%' or '%" + param.get(4) + "%')" + param.get("Listquery") +" Group by place", new RowMapper<AirdndSearchVO>() {

			@Override
			public AirdndSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub

				AirdndSearchVO list = new AirdndSearchVO(
						rs.getInt("average_price"),
						rs.getInt("data_total"));

				return list;
			}

		});
		return list;
	}

	public List<AirdndSearchVO> unitpriceselect(Map<Object, Object> param) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndSearchVO> list = jdbcTemplate.query("select price from airdnd_search_view_final where place = '" + param.get("location")
		+ "' and filter_max_person>=" + param.get("guests") + " and filter_bed>=" + param.get("bedCount")+ " and filter_bedroom>=" + param.get("bedroomCount")
		+ " and filter_bathroom>=" + param.get("bathCount") + " and price>=" + param.get("priceMin") + " and price<=" + param.get("priceMax")
		+ " and lat >= '" + param.get("swLat") + "' and lng >= '" + param.get("swLng") + "' and lat <= '" +param.get("neLat") + "' and lng <= '" + param.get("neLng")
		+ "' and (sub_title like '%" + param.get("roomTypeHouse1") + "%' or '%" + param.get("roomTypePrivate") + "%' or '%"  + param.get("roomTypeShared1")
		+ "%' or '%"  + param.get("roomTypeHouse2") + "%' or '%" + param.get("roomTypeShared2") + "%') and (host_language like '%" + param.get(0) 
		+ "%' or '%" + param.get(1) + "%' or '%" + param.get(2) + "%' or '%" + param.get(3) + "%' or '%" + param.get(4) + "%')" + param.get("Listquery"), new RowMapper<AirdndSearchVO>() {

			@Override
			public AirdndSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub

				AirdndSearchVO list = new AirdndSearchVO(
						rs.getInt("price"));
				
				return list;
			}

		});
		return list;
	}

	@Override   
	public List<AirdndSearchVO> facilityList(Map<Object, Object> param){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndSearchVO> list = jdbcTemplate.query("SELECT facility FROM airdnd_home_convenient_facility where home_idx = ANY(SELECT home_idx FROM airdnd_search_view_final where place='"+param.get("location") 
		+ "' and filter_max_person>=" + param.get("guests") + " and filter_bed>=" + param.get("bedCount")+ " and filter_bedroom>=" + param.get("bedroomCount")
		+ " and filter_bathroom>=" + param.get("bathCount") + " and price>=" + param.get("priceMin") + " and price<=" + param.get("priceMax") 
		+ " and lat >= '" + param.get("swLat") + "' and lng >= '" + param.get("swLng") + "' and lat <= '" +param.get("neLat") + "' and lng <= '" + param.get("neLng")
		+ "' and (sub_title like '%" + param.get("roomTypeHouse1") + "%' or '%" + param.get("roomTypePrivate") + "%' or '%"  + param.get("roomTypeShared1")
		+ "%' or '%"  + param.get("roomTypeHouse2") + "%' or '%" + param.get("roomTypeShared2") + "%') and (host_language like '%" + param.get(0) 
		+ "%' or '%" + param.get(1) + "%' or '%" + param.get(2) + "%' or '%" + param.get(3) + "%' or '%" + param.get(4) + "%')" + param.get("Listquery")+") group by facility", new RowMapper<AirdndSearchVO>() {

			@Override
			public AirdndSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {


				AirdndSearchVO list = new AirdndSearchVO(
						rs.getString("facility"));

				return list;
			}
			
		});

		return list;
	}

	public List<AirdndUserVO> hostLanlist(String place){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndUserVO> list = jdbcTemplate.query("SELECT host_language FROM airdnd_host where home_idx = ANY(SELECT home_idx FROM airdnd_search_view_final where place='"+ place +"') group by host_language", new RowMapper<AirdndUserVO>() {

			@Override
			public AirdndUserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub

				AirdndUserVO list = new AirdndUserVO(
						rs.getString("host_language"));
				return list;
			}
		});

		return list;
	}

	public List<AirdndSearchVO> select_one(int homeIdx){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndSearchVO> list = jdbcTemplate.query("SELECT * FROM airdnd_search_view_final where home_idx = "+ homeIdx , new RowMapper<AirdndSearchVO>() {

			@Override
			public AirdndSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub

				AirdndSearchVO list = new AirdndSearchVO(
						rs.getInt("home_idx"),
						rs.getBoolean("isSuperHost"),
						rs.getString("sub_title"),
						rs.getString("title"),
						rs.getInt("filter_max_person"),
						rs.getInt("filter_bedroom"),
						rs.getInt("filter_bed"),
						rs.getInt("filter_bathroom"),
						rs.getInt("price"),
						rs.getDouble("rating"),
						rs.getInt("review_num"),
						rs.getString("lat"),
						rs.getString("lng"));

				return list;
			}
		});

		return list;
	}

	public List<AirdndBookmarkedHomesVO> bookmarkselect(int user_idx) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndBookmarkedHomesVO> list = jdbcTemplate.query("SELECT home_idx FROM airdnd_bookmarked_homes where user_idx = "+ user_idx , new RowMapper<AirdndBookmarkedHomesVO>() {

			@Override
			public AirdndBookmarkedHomesVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndBookmarkedHomesVO list = new AirdndBookmarkedHomesVO(
						rs.getInt("home_idx"));
				return list;
			}
			
		});

		return list;
	}


}
