package dao;

import java.util.List;

import vo.AirdndHomeVO;

public interface AirdndHomeDAOI {
	
	List<AirdndHomeVO> select();

	int insert(AirdndHomeVO vo);

}
