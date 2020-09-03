package dao;

import java.util.List;

import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;

public interface AirdndSearchDAOI {

	List<AirdndSearchVO> select(String place, int page, int filter_price_min, int filter_price_max);

	List<AirdndHomePictureVO> pictureselect(int home_idx);
	List<AirdndSearchVO> facilityList(String place);
}
