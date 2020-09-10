package service;

import java.util.List;

import vo.AirdndBedroomVO;
import vo.AirdndBookmarkedHomesVO;
import vo.AirdndDistanceVO;
import vo.AirdndFacilityVO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndHostVO;
import vo.AirdndNoticeVO;
import vo.AirdndReviewVO;
import vo.AirdndSafetyRuleVO;
import vo.AirdndUseRuleVO;
import vo.AirdndUserResInfoVO;

public interface AirdndHomeServiceI {
	
	AirdndHostVO hostselect(int home_idx);
	AirdndHomeVO homeselect(int home_idx);
	List<AirdndDistanceVO> distanceselect(int home_idx);
	List<AirdndHomePictureVO> pictureselect(int home_idx);
	List<AirdndBedroomVO> bedroomeselect(int home_idx);
	List<AirdndFacilityVO> facilityselect(int home_idx);
	List<AirdndNoticeVO> noticeselect(int home_idx);
	List<AirdndReviewVO> reviewselect(int home_idx);
	List<AirdndSafetyRuleVO> safetyruleselect(int home_idx);
	List<AirdndUseRuleVO> useruleselect(int home_idx);
	List<AirdndUserResInfoVO> userresinfoselect(int home_idx);
	AirdndBookmarkedHomesVO bookmarkedhomes(int signInIdx, int home_idx);

}
