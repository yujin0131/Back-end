package service;

import java.util.List;

import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndHostVO;
import vo.AirdndUseRuleVO;
import vo.AirdndUserResInfoVO;
import vo.AirdndUserVO;

public interface AirdndUserResInfoServiceI {
	//Select user's reservation home list
	List<AirdndUserResInfoVO> selectUserResInfo(int user_idx);
	//Select user's information
	AirdndUserVO selectUserInfo(int user_idx);
	//Select user's reservation home's main picture
	List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx);
	//Select host's information
	AirdndHostVO selectHostInfo(int home_idx);
	//Select home's information
	AirdndHomeVO selectHomeInfo(int home_idx);
	//Select UseRule's information
	List<AirdndUseRuleVO> selectUseRuleInfo(int home_idx);
	//Update is_canceled
	int userResIsCanceled(int idx);
}
