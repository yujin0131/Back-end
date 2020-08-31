package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndSearchDAO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndSearchTotalVO;
import vo.AirdndSearchVO;

@Service("airdndsearchService")
public class AirdndSearchService implements AirdndSearchServiceI{

	@Autowired
	AirdndSearchDAO airdnd_search_dao;

	@Override
	public List<AirdndSearchVO> searchselect(String place, int page){

		List<AirdndSearchVO> list = airdnd_search_dao.select(place, page);

		return list;
	}
	
	@Override
	public List<AirdndHomePictureVO> pictureselect(int home_idx){

		List<AirdndHomePictureVO> list = airdnd_search_dao.pictureselect(home_idx);

		return list;
	}

	@Override
	public List<AirdndSearchTotalVO> searchtotalselect(String place){

		List<AirdndSearchTotalVO> list = airdnd_search_dao.totalselect(place);

		return list;
	}

	public List<AirdndSearchVO> unitpriceselect(String place) {
		
		List<AirdndSearchVO> list = airdnd_search_dao.unitpriceselect(place);
		return list;
	}


}
