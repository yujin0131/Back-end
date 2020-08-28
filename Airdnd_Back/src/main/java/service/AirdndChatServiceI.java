package service;

import java.util.List;

import vo.AirdndChatVO;

public interface AirdndChatServiceI {
	
	//Select chatting list
	List<AirdndChatVO> daoserviceconnect();
	
	//Insert chatting
	AirdndChatVO daoserviceinsert(AirdndChatVO vo);
}
