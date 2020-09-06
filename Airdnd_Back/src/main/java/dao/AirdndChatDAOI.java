package dao;

import java.util.List;

import vo.AirdndChatVO;
import vo.AirdndHostVO;
import vo.AirdndUserResInfoVO;
import vo.AirdndUserVO;

public interface AirdndChatDAOI {
	//Select chatting list
	List<AirdndChatVO> selectChatList(int user_idx, int host_idx);
	//Select the user info
	List<AirdndUserVO> selectUser(int user_idx);
	//Select the host info list
	List<AirdndHostVO> selectHostList(int user_idx);
	//Select the userResInfo
	List<AirdndUserResInfoVO> selectUserResInfo(int user_idx, int host_idx);
	//Select the latest message
	List<AirdndChatVO> selectLatestMsg(int user_idx, int host_idx);
	//Insert chatting
	AirdndChatVO insertChat(AirdndChatVO vo);
}
