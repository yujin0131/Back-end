package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndBookmarkDAO;
import vo.AirdndBookmarkVO;
import vo.AirdndHomePictureVO;

@Service("airdndBookmarkService")
public class AirdndBookmarkService implements AirdndBookmarkServiceI {
	@Autowired
	AirdndBookmarkDAO airdnd_bookmark_dao;
	
	//Select bookmark list
	@Override
	public List<AirdndBookmarkVO> selectBookmark(){
		List<AirdndBookmarkVO> list = airdnd_bookmark_dao.selectBookmark();

		return list;
	}
	
	//Select user's reservation home's main picture
	@Override
	public List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx) {
		List<AirdndHomePictureVO> list = airdnd_bookmark_dao.selectHomeMainPicture(home_idx);

		return list;
	}
	
	//Create a new bookmark
	@Override
	public AirdndBookmarkVO insert_bookmark(AirdndBookmarkVO vo) {
		airdnd_bookmark_dao.insert_bookmark(vo);
		
		return vo;
	}
	
	//Add the home in the bookmark
	@Override
	public AirdndBookmarkVO update_bookmarkHome(AirdndBookmarkVO vo) {
		airdnd_bookmark_dao.update_bookmarkHome(vo);
		
		return vo;
	}
	
	//Delete the home in the bookmark
	@Override
	public AirdndBookmarkVO delete_bookmarkHome(AirdndBookmarkVO vo) {
		airdnd_bookmark_dao.delete_bookmarkHome(vo);
		
		return vo;
	}
	
}
