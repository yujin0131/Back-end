package dao;

import java.util.List;

import vo.AirdndChatVO;

public interface AirdndChatDAOI {
	
	List<AirdndChatVO> select();
	AirdndChatVO insert_chat(AirdndChatVO vo);
}
