package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndHomeDAO;
import dao.AirdndSearchDAO;
import vo.AirdndBedroomVO;
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


@Service("airdndhomeService")
public class AirdndHomeService implements AirdndHomeServiceI{
	
	@Autowired
	AirdndHomeDAO airdnd_home_dao;
	
	@Override
	public AirdndHostVO hostselect(int home_idx){

		AirdndHostVO vo = airdnd_home_dao.selectHost(home_idx);

		return vo;
	}

	@Override
	public AirdndHomeVO homeselect(int home_idx) {
		AirdndHomeVO vo = airdnd_home_dao.selectHome(home_idx);
		return vo;
	}
	
	@Override
	public List<AirdndDistanceVO> distanceselect(int home_idx) {
		List<AirdndDistanceVO> list = airdnd_home_dao.selectDistance(home_idx);
		return list;
	}
	
	@Override
	public List<AirdndHomePictureVO> pictureselect(int home_idx) {
		List<AirdndHomePictureVO> list = airdnd_home_dao.selectPicture(home_idx);
		return list;
	}
	
	@Override
	public List<AirdndBedroomVO> bedroomeselect(int home_idx) {
		List<AirdndBedroomVO> list = airdnd_home_dao.selectBedroom(home_idx);
		return list;
	}
	
	@Override
	public List<AirdndFacilityVO> facilityselect(int home_idx) {
		List<AirdndFacilityVO> list = airdnd_home_dao.selectFacility(home_idx);
		return list;
	}
	
	@Override
	public List<AirdndNoticeVO> noticeselect(int home_idx) {
		List<AirdndNoticeVO> list = airdnd_home_dao.selectNotice(home_idx);
		return list;
	}
	
	@Override
	public List<AirdndReviewVO> reviewselect(int home_idx) {
		List<AirdndReviewVO> list = airdnd_home_dao.selectReview(home_idx);
		return list;
	}
	
	@Override
	public List<AirdndSafetyRuleVO> safetyruleselect(int home_idx) {
		List<AirdndSafetyRuleVO> list = airdnd_home_dao.selectSafetyRule(home_idx);
		return list;
	}
	
	@Override
	public List<AirdndUseRuleVO> useruleselect(int home_idx) {
		List<AirdndUseRuleVO> list = airdnd_home_dao.selectUseRule(home_idx);
		return list;
	}
	
	@Override
	public List<AirdndUserResInfoVO> userresinfoselect(int home_idx) {
		List<AirdndUserResInfoVO> list = airdnd_home_dao.selectUserResInfo(home_idx);
		return list;
	}

}
