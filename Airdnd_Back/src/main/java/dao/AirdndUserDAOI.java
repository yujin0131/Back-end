package dao;

import java.util.List;

import vo.AirdndUserVO;

public interface AirdndUserDAOI {
	
	List<AirdndUserVO> select();

	int select(String email_check);
	
	int insert(AirdndUserVO vo);
}
