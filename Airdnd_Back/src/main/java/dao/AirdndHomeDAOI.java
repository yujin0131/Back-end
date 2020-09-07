package dao;

import java.util.List;

import vo.AirdndBedroomVO;
import vo.AirdndDistanceVO;
import vo.AirdndFacilityVO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndNoticeVO;
import vo.AirdndReviewVO;
import vo.AirdndSafetyRuleVO;
import vo.AirdndUseRuleVO;

public interface AirdndHomeDAOI {
	
	AirdndHomeVO selectHost(int home_idx);
	AirdndHomeVO selectHome(int home_idx);
	List<AirdndDistanceVO> selectDistance(int home_idx);
	List<AirdndHomePictureVO> selectPicture(int home_idx);
	List<AirdndBedroomVO> selectBedroom(int home_idx);
	List<AirdndFacilityVO> selectFacility(int home_idx);
	List<AirdndNoticeVO> selectNotice(int home_idx);
	List<AirdndReviewVO> selectReview(int home_idx);
	List<AirdndSafetyRuleVO> selectSafetyRule(int home_idx);
	List<AirdndUseRuleVO> selectUseRule(int home_idx);

}
