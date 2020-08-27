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

import vo.AirdndSearchVO;


@Repository("airdndDAO")
public class AirdndSearchDAO implements AirdndSearchDAOI{

	@Autowired
	DataSource dataSource;

	@Override	
	public List<AirdndSearchVO> select(){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndSearchVO> list = jdbcTemplate.query("select * from airdnd_search_view", new RowMapper<AirdndSearchVO>() {

			@Override
			public AirdndSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
	
				AirdndSearchVO list = new AirdndSearchVO(
						rs.getString("sub_title"),
						rs.getString("title"),
						rs.getInt("filter_max_person"),
						rs.getInt("filter_bedroom"),
						rs.getInt("filter_bed"),
						rs.getInt("filter_bathroom"),
						rs.getInt("price"),
						rs.getFloat("score"),
						rs.getInt("review_num"));


				return list;
			}

		});
		
		return list;
		

	}

}
