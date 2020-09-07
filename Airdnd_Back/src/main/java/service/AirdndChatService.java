package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dao.AirdndChatDAO;
import vo.AirdndChatVO;
import vo.AirdndHostVO;
import vo.AirdndUserResInfoVO;
import vo.AirdndUserVO;

@Service("airdndChatService")
public class AirdndChatService implements AirdndChatServiceI {
	@Autowired
	AirdndChatDAO airdnd_chat_dao;
	
	//Select chatting list
	@Override
	public List<AirdndChatVO> selectChatList(int user_idx, int host_idx){
		List<AirdndChatVO> list = airdnd_chat_dao.selectChatList(user_idx, host_idx);
		
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
	public List<AirdndHostVO> selectHostList(int user_idx) {
		List<AirdndHostVO> list = airdnd_chat_dao.selectHostList(user_idx);
		
		return list;
	}
	
	//Select the userResInfo_idx
	@Override
	public List<AirdndUserResInfoVO> selectUserResInfo(int user_idx, int host_idx) {
		List<AirdndUserResInfoVO> list = airdnd_chat_dao.selectUserResInfo(user_idx, host_idx);

		return list;
	}
	
	//Select the latest message
	@Override
	public List<AirdndChatVO> selectLatestMsg(int user_idx, int host_idx) {
		List<AirdndChatVO> list = airdnd_chat_dao.selectLatestMsg(user_idx, host_idx);
		
		return list;
	}
	
	//Insert chatting
	@Override
	public AirdndChatVO insertChat(AirdndChatVO vo) {
		airdnd_chat_dao.insertChat(vo);
		
		return vo;
	}
}
