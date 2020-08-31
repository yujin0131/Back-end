package service;

import java.util.List;

import vo.AirdndHomePictureVO;
import vo.AirdndSearchTotalVO;
import vo.AirdndSearchVO;

public interface AirdndSearchServiceI {
	
	List<AirdndSearchVO> searchselect(String place, int page);
	
	List<AirdndHomePictureVO> pictureselect(int home_idx);
	
	List<AirdndSearchTotalVO> searchtotalselect(String place);
	
	List<AirdndSearchVO> unitpriceselect(String place);
	
}
