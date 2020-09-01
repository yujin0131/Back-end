package service;

import java.util.List;

import vo.AirdndBookmarkVO;
import vo.AirdndHomePictureVO;

public interface AirdndBookmarkServiceI {
	//Select bookmark list
	List<AirdndBookmarkVO> selectBookmark();
	//Select user's reservation home's main picture
	List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx);
	//Create a new bookmark
	AirdndBookmarkVO insert_bookmark(AirdndBookmarkVO vo);
	//Add the home in the bookmark
	AirdndBookmarkVO update_bookmarkHome(AirdndBookmarkVO vo);
	//Delete the home in the bookmark
	AirdndBookmarkVO delete_bookmarkHome(AirdndBookmarkVO vo);
}
