package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndUserResInfoDAO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndHostVO;
import vo.AirdndUseRuleVO;
import vo.AirdndUserResInfoVO;
import vo.AirdndUserVO;

@Service("airdndUserResInfoService")
public class AirdndUserResInfoService implements AirdndUserResInfoServiceI {
	@Autowired
	AirdndUserResInfoDAO airdnd_user_res_info_dao;
	
	//Select user's reservation home list
	@Override
	public List<AirdndUserResInfoVO> selectUserResInfo(int user_idx){
		List<AirdndUserResInfoVO> list = airdnd_user_res_info_dao.selectUserResInfo(user_idx);

		return list;
	}
	
	//Select user's information
	@Override
	public AirdndUserVO selectUserInfo(int user_idx) {
		AirdndUserVO vo = airdnd_user_res_info_dao.selectUserInfo(user_idx);
		
		return vo;
	}
	
	//Select user's reservation home's main picture
	@Override
	public List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx){
		List<AirdndHomePictureVO> list = airdnd_user_res_info_dao.selectHomeMainPicture(home_idx);

		return list;
	}
	
	//Select host's information
	@Override
	public AirdndHostVO selectHostInfo(int home_idx) {
		AirdndHostVO vo = airdnd_user_res_info_dao.selectHostInfo(home_idx);
		
		return vo;
	}
	
	//Select home's information
	@Override
	public AirdndHomeVO selectHomeInfo(int home_idx) {
		AirdndHomeVO vo = airdnd_user_res_info_dao.selectHomeInfo(home_idx);
		
		return vo;
	}
	
	//Select UseRule's information
	@Override
	public List<AirdndUseRuleVO> selectUseRuleInfo(int home_idx) {
		List<AirdndUseRuleVO> list = airdnd_user_res_info_dao.selectUseRuleInfo(home_idx);
		
		return list;
	}
	
	//Update is_canceled
	@Override
	public int userResIsCanceled(int idx) {
		int res = airdnd_user_res_info_dao.userResIsCanceled(idx);
		
		return res;
	}
}
