package service;

import java.util.List;
import vo.AirdndUserVO;

public interface AirdndUserServiecI {
	
	List<AirdndUserVO> daoserviceconnect();
	int daoserviceinsert(AirdndUserVO vo);
}
