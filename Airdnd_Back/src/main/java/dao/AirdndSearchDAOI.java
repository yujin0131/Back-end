package dao;

import java.util.List;

import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;

public interface AirdndSearchDAOI {

	List<AirdndSearchVO> select(String place);

	List<AirdndHomePictureVO> pictureselect(int home_idx);

}
