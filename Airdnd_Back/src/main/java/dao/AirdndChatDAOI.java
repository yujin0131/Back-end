package dao;

import java.util.List;

import vo.AirdndChatVO;

public interface AirdndChatDAOI {
	//Select chatting list
	List<AirdndChatVO> selectChatList();
	
	//Insert chatting
	AirdndChatVO insertChat(AirdndChatVO vo);
}
