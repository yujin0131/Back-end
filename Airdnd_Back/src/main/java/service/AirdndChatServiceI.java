package service;

import java.util.List;

import vo.AirdndChatVO;
import vo.AirdndHostVO;
import vo.AirdndUserVO;

public interface AirdndChatServiceI {
	//Select chatting list
	List<AirdndChatVO> selectChatList();
	//Select the user info
	List<AirdndUserVO> selectUser(int user_idx);
	//Select the host info list
	List<AirdndHostVO> selectHostList();
	//Insert chatting
	AirdndChatVO insertChat(AirdndChatVO vo);
}
