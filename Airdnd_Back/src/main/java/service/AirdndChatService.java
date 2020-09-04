package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndChatDAO;
import vo.AirdndChatVO;
import vo.AirdndHostVO;
import vo.AirdndUserVO;

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
	
	//Select the user info
	@Override
	public List<AirdndUserVO> selectUser(int user_idx) {
		List<AirdndUserVO> vo = airdnd_chat_dao.selectUser(user_idx);
		
		return vo;
	}
	
	//Select the host info list
	@Override
	public List<AirdndHostVO> selectHostList() {
		List<AirdndHostVO> list = airdnd_chat_dao.selectHostList();
		
		return list;
	}
	
	//Insert chatting
	@Override
	public AirdndChatVO insertChat(AirdndChatVO vo) {
		airdnd_chat_dao.insertChat(vo);
		
		return vo;
	}
}
