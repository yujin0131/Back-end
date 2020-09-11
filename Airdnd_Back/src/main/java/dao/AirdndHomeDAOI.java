package dao;

import java.sql.SQLException;
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

public interface AirdndHomeDAOI {
	
	AirdndHostVO selectHost(int home_idx);
	AirdndHomeVO selectHome(int home_idx);
	List<AirdndDistanceVO> selectDistance(int home_idx);
	List<AirdndHomePictureVO> selectPicture(int home_idx);
	List<AirdndBedroomVO> selectBedroom(int home_idx);
	List<AirdndFacilityVO> selectFacility(int home_idx);
	List<AirdndNoticeVO> selectNotice(int home_idx);
	List<AirdndReviewVO> selectReview(int home_idx);
	List<AirdndSafetyRuleVO> selectSafetyRule(int home_idx);
	List<AirdndUseRuleVO> selectUseRule(int home_idx);
	List<AirdndUserResInfoVO> selectUserResInfo(int home_idx);
	List<AirdndUserResInfoVO> selectUserResInfo(int home_idx, String checkin, String checkout);
	AirdndBookmarkedHomesVO selectBookmarkedHomes(int signInIdx, int home_idx);
	int book(AirdndUserResInfoVO vo);

}
