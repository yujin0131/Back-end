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
	
	@Override
	public List<AirdndChatVO> daoserviceconnect(){

		List<AirdndChatVO> list = airdnd_chat_dao.select();

		return list;
	}
}
