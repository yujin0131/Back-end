package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndChatDAO;
import vo.AirdndChatVO;

@Service("airdndChatService")
public class AirdndChatService implements AirdndChatServiceI {
	@Autowired
	AirdndChatDAO airdnd_chat_dao;
	
	//Select chatting list
	@Override
	public List<AirdndChatVO> selectChatList(){
		List<AirdndChatVO> list = airdnd_chat_dao.selectChatList();
		
		return list;
	}
	
	//Insert chatting
	@Override
	public AirdndChatVO insertChat(AirdndChatVO vo) {
		airdnd_chat_dao.insertChat(vo);
		
		return vo;
	}
}
