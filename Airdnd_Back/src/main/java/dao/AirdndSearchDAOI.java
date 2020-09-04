package dao;

import java.util.List;
import java.util.Map;

import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;

public interface AirdndSearchDAOI {

	List<AirdndSearchVO> select(Map<Object, Object> param);

	List<AirdndHomePictureVO> pictureselect(int home_idx);
	
	List<AirdndSearchVO> facilityList(String place);
}
