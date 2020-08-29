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

import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndSearchVO;
import vo.AirdndUserVO;

@Repository("searchDAO")
public class AirdndSearchDAO implements AirdndSearchDAOI{

	@Autowired
	DataSource dataSource;

	@Override   
	public List<AirdndSearchVO> select(String place){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<AirdndSearchVO> list = jdbcTemplate.query("select * from airdnd_search_view where place='" + place + "'", new RowMapper<AirdndSearchVO>() {

			@Override
			public AirdndSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub

				AirdndSearchVO list = new AirdndSearchVO(
						rs.getInt("home_idx"),
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

}
