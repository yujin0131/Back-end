package service;

import java.util.List;

import vo.AirdndBedroomVO;
import vo.AirdndDistanceVO;
import vo.AirdndFacilityVO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndNoticeVO;
import vo.AirdndReviewVO;
import vo.AirdndRuleVO;

public interface AirdndHomeServiceI {
	
	AirdndHomeVO hostselect(int home_idx);
	AirdndHomeVO homeselect(int home_idx);
	List<AirdndDistanceVO> distanceselect(int home_idx);
	List<AirdndHomePictureVO> pictureselect(int home_idx);
	List<AirdndBedroomVO> bedroomeselect(int home_idx);
	List<AirdndFacilityVO> facilityselect(int home_idx);
	List<AirdndNoticeVO> noticeselect(int home_idx);
	List<AirdndReviewVO> reviewselect(int home_idx);
	List<AirdndRuleVO> safetyruleselect(int home_idx);
	List<AirdndRuleVO> useruleselect(int home_idx);

}
