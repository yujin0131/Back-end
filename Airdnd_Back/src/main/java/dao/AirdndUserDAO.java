package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import exception.NoIdService;
import vo.AirdndUserVO;

@Repository("userDAO")
public class AirdndUserDAO implements AirdndUserDAOI{

	@Autowired
	DataSource dataSource;

	//전체 회원 목록
	@Override	
	public List<AirdndUserVO> select(){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<AirdndUserVO> list = jdbcTemplate.query("select * from airdnd_user", new RowMapper<AirdndUserVO>() {
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
						rs.getString("description")
						);

				return list;
			}
		});
		System.out.println("DAO : " + list.get(0).getUser_idx());
		return list;
	}

	//이메일 중복 여부 확인
	@Override
	public int select(String email_check) {

		int res = -1;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select count(*) from airdnd_user where email='" + email_check + "'";

		//사용가능한 이메일이면 0, 있으면 1 나오게,,
		res = jdbcTemplate.queryForObject(sql, Integer.class);

		return res;
		
	}
	
	//회원가입 정보 입력
	@Override	
	public int insert(AirdndUserVO vo){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String email = vo.getEmail();
		String pwd = vo.getPwd();
		String last_name = vo.getLast_name();
		String first_name = vo.getFirst_name();
		String birthday = vo.getBirthday();
		String profileImg = vo.getProfileImg();
		String phone = vo.getPhone();
		String signupDate = vo.getSignupDate();
		String description = vo.getDescription();

		int res = jdbcTemplate.update("insert into airdnd_user (user_idx, email, pwd, last_name, first_name, birthday, profileImg, phone, signupDate, description) "
				+ "VALUES (0, ?, ?, ?, ?, ?, ?, ?, now(), ?)", email, pwd, last_name, first_name, birthday, profileImg, phone, description);
		
		return res;
	}
	
	
	//로그인 정보 가져오기
	@Override
	public AirdndUserVO select_one(AirdndUserVO vo) {
		
		int user_idx = -1;
		String email = "";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from airdnd_user where email='" + vo.getEmail() + "'";
		
		List<AirdndUserVO> loginlist = jdbcTemplate.query(sql, new RowMapper<AirdndUserVO>() {
			@Override
			public AirdndUserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AirdndUserVO vo = new AirdndUserVO(
						rs.getInt("user_idx"),
						rs.getString("email"),
						rs.getString("pwd"),
						rs.getString("last_name"),
						rs.getString("first_name"),
						rs.getString("birthday"),
						rs.getString("profileImg"),
						rs.getString("phone"),
						rs.getString("signupDate"),
						rs.getString("description")
						);
				return vo;
			}
		});
		
		
		NoIdService checkEmail = new NoIdService();
		AirdndUserVO loginvo = new AirdndUserVO();
		
		try {
			user_idx = loginlist.get(0).getUser_idx();
		} catch (Exception e) {
			user_idx = -1;
		}		
		//이메일 존재 여부 체크
		try{
			checkEmail.NoIdMethod(user_idx);
			loginvo = loginlist.get(0);
			return loginvo;
        }catch(Exception ex){
            ex.printStackTrace();
        }
		return null;
	}
}
