package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	//Select User's chat info
	@Override
	public List<AirdndChatVO> selectChatList(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<AirdndChatVO> list = jdbcTemplate.query("select * from airdnd_chatting where user_idx=1", new RowMapper<AirdndChatVO>() {
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
	
	//Select the host info
	
	//Select the user info
	
	
	//Insert chatting
	@Override
	public AirdndChatVO insertChat(AirdndChatVO vo) {
		String sql = "insert into AirdndDB.airdnd_chatting(host_idx, user_idx, content, image_url, send_date_time) values(?, ?, ?, ?, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 9 HOUR))";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, vo.getHost_idx(), vo.getUser_idx(), vo.getContent(), vo.getImage_url());
		
		/*
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://52.79.141.237:3306/AirdndDB?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=Asia/Seoul&amp;useSSL=false", "mysqluser", "1111");
			Statement st = con.createStatement();
			File imgfile = new File("C:/chat_img/" + vo.getImage_url());
			FileInputStream fin = new FileInputStream(imgfile);
			
			String sql = "insert into AirdndDB.airdnd_chatting(host_idx, user_idx, content, image_url, send_date_time) values(1, 2, ?, ?, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 9 HOUR))";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, vo.getContent());
			pre.setBinaryStream(2, fin, (int)imgfile.length());//Stream형의 파일 업로드
			
			pre.executeUpdate();
			System.out.println("Inserting Successfully!");
			pre.close();
			con.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		*/
		
		return vo;
	}
}
