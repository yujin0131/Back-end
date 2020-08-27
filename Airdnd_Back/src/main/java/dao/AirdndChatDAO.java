package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vo.AirdndChatVO;

@Repository("airdndChatDAO")
public class AirdndChatDAO implements AirdndChatDAOI {
	@Autowired
	DataSource dataSource;

	@Override
	public List<AirdndChatVO> select(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndChatVO> list = jdbcTemplate.query("select * from airdnd_chatting", new RowMapper<AirdndChatVO>() {
			@Override
			public AirdndChatVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndChatVO list = new AirdndChatVO(
					rs.getInt("idx"),
					rs.getInt("host_idx"),
					rs.getInt("user_idx"),
					rs.getInt("msg_hidden_or_not"),
					rs.getString("content"),
					rs.getString("image_url"),
					rs.getString("send_date_time"));

				return list;
			}
		});
		
		return list;
	}

	@Override
	public AirdndChatVO insert_chat(AirdndChatVO vo) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update("insert into AirdndDB.airdnd_chatting(host_idx, user_idx, content, image_url) values(1, 2, ?, ?)", vo.getContent(), vo.getImage_url());
		
		return vo;
	}
}
