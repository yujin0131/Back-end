package service;

import java.util.List;

import vo.AirdndChatVO;

public interface AirdndChatServiceI {
	//Select chatting list
	List<AirdndChatVO> selectChatList();
	
	//Insert chatting
	AirdndChatVO insertChat(AirdndChatVO vo);
}
