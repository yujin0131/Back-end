package service;

import java.util.List;

import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;
import vo.AirdndUserVO;

public interface AirdndSearchServiceI {
	
	List<AirdndSearchVO> searchselect(String place, int page, int filter_price_min, int filter_price_max);
	
	List<AirdndHomePictureVO> pictureselect(int home_idx);
	
	List<AirdndSearchVO> searchtotalselect(String place);
	
	List<AirdndSearchVO> facilityList(String place);
	
	List<AirdndUserVO> hostLanlist(String place);
}
