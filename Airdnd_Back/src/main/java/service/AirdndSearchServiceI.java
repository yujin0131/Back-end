package service;

import java.util.List;
import java.util.Map;

import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;
import vo.AirdndUserVO;

public interface AirdndSearchServiceI {
	
	List<AirdndSearchVO> searchselect(Map<Object, Object> param);
	
	List<AirdndHomePictureVO> pictureselect(int home_idx);
	
	List<AirdndSearchVO> searchtotalselect(Map<Object, Object> param);
	
	List<AirdndSearchVO> facilityList(Map<Object, Object> param);
	
	List<AirdndUserVO> hostLanlist(String place);
}
