package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndSearchDAO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndSearchVO;

@Service("airdndsearchService")
public class AirdndSearchService implements AirdndSearchServiceI{

	@Autowired
	AirdndSearchDAO airdnd_search_dao;

	@Override
	public List<AirdndSearchVO> searchselect(){

		List<AirdndSearchVO> list = airdnd_search_dao.select();

		return list;
	}
	
	@Override
	public List<AirdndHomePictureVO> pictureselect(int home_idx){

		List<AirdndHomePictureVO> list = airdnd_search_dao.pictureselect(home_idx);

		return list;
	}


}
