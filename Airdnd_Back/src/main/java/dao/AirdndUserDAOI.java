package dao;

import java.util.List;

import vo.AirdndUserVO;

public interface AirdndUserDAOI {
	List<AirdndUserVO> select();
	int insert(AirdndUserVO vo);
}
