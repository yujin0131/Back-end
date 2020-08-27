package service;

import java.util.List;

import vo.AirdndHomeVO;

public interface AirdndHomeServiceI {
	
	List<AirdndHomeVO> daoserviceconnect();
	
	int daoserviceinsert(AirdndHomeVO vo);

}
