package dao;

import java.util.List;

import vo.AirdndBookmarkVO;
import vo.AirdndBookmarkedHomesVO;
import vo.AirdndHomePictureVO;

public interface AirdndBookmarkDAOI {
	//Select bookmark list
	List<AirdndBookmarkVO> selectBookmark(int user_idx);
	//Select bookmark homes
	List<AirdndBookmarkedHomesVO> selectBookmarkHomes(int user_idx);
	//Select bookmark homes where bookidx
	List<AirdndBookmarkedHomesVO> selectBookmarkHomesIdx(int bookmark_idx);
	//Search bookmark homes' count
	int selectBookmarkHomesCount(int bookmark_idx);
	//Select user's reservation home's main picture
	List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx);
	//Create a new bookmark
	int insert_bookmark(AirdndBookmarkVO vo);
	//Select a new bookmark info
	int selectNewBookmarkInfo();
	//Search an idx
	int selectIdx(String bookmark_list_title);
	//Select a bookmark where idx=
	int selectPreviousBookmarkInfo(int idx);
	//Add the home in the bookmark
	int insert_bookmarkHome(AirdndBookmarkedHomesVO vo);
	//Delete the home in the bookmark
	int delete_bookmarkHome(int home_idx);
	//Delete the bookmark
	int delete_bookmark(int idx);
	//Update update_date_time
	int update_updateTime(int idx);
}
