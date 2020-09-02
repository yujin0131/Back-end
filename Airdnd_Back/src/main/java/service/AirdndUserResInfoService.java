package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndUserResInfoDAO;
import vo.AirdndHomePictureVO;
import vo.AirdndUserResInfoVO;

@Service("airdndUserResInfoService")
public class AirdndUserResInfoService implements AirdndUserResInfoServiceI {
	@Autowired
	AirdndUserResInfoDAO airdnd_user_res_info_dao;
	
	//Select user's reservation home list
	@Override
	public List<AirdndUserResInfoVO> selectUserResInfo(){
		List<AirdndUserResInfoVO> list = airdnd_user_res_info_dao.selectUserResInfo();

		return list;
	}
	
	//Select user's reservation home's main picture
	@Override
	public List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx){
		List<AirdndHomePictureVO> list = airdnd_user_res_info_dao.selectHomeMainPicture(home_idx);

		return list;
	}
}
