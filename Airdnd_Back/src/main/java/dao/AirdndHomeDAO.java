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

import vo.AirdndHomeVO;


@Repository("homeDAO")
public class AirdndHomeDAO implements AirdndHomeDAOI{

	@Autowired
	DataSource dataSource;

	@Override	
	public List<AirdndHomeVO> select(){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndHomeVO> list = jdbcTemplate.query("select * from airdnd_home", new RowMapper<AirdndHomeVO>() {

			@Override
			public AirdndHomeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
	
				AirdndHomeVO list = new AirdndHomeVO(
						rs.getInt("home_idx"),
						rs.getString("place"),
						rs.getString("title"),
						rs.getBoolean("isSuperHost"),
						rs.getString("addr"),
						rs.getString("lat"),
						rs.getString("lng"),
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
		
		return list;
		

	}

}
