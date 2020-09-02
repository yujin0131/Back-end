package service;

import java.util.List;

import vo.AirdndBookmarkVO;
import vo.AirdndBookmarkedHomesVO;
import vo.AirdndHomePictureVO;

public interface AirdndBookmarkServiceI {
	//Select bookmark list
	List<AirdndBookmarkVO> selectBookmark();
	//Select bookmark homes
	List<AirdndBookmarkedHomesVO> selectBookmarkHomes();
	//Search bookmark homes' count
	int selectBookmarkHomesCount(int bookmark_idx);
	//Select user's reservation home's main picture
	List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx);
	//Create a new bookmark
	AirdndBookmarkVO insert_bookmark(AirdndBookmarkVO vo);
	//Search an idx
	int selectIdx(String bookmark_list_title);
	//Add the home in the bookmark
	AirdndBookmarkedHomesVO insert_bookmarkHome(AirdndBookmarkedHomesVO vo);
	//Delete the home in the bookmark
	String delete_bookmarkHome(int bookmark_idx);
	//Delete the bookmark
	String delete_bookmark(int idx);
	//Update update_date_time
	String update_updateTime(int idx);
}
