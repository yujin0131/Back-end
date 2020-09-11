package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndChatDAO;
import vo.AirdndChatMsgsVO;
import vo.AirdndChatVO;
import vo.AirdndHostVO;
import vo.AirdndUserResInfoVO;

@Service("airdndChatService")
public class AirdndChatService implements AirdndChatServiceI {
   @Autowired
   AirdndChatDAO airdnd_chat_dao;
   
   //Select User's chat list - all
   @Override
   public List<AirdndChatVO> selectChatListAll(int user_idx){
      List<AirdndChatVO> list = airdnd_chat_dao.selectChatListAll(user_idx);
      
      return list;
   }
   
   //Select User's chat list - hidden
   @Override
   public List<AirdndChatVO> selectChatListHidden(int user_idx) {
      List<AirdndChatVO> list = airdnd_chat_dao.selectChatListHidden(user_idx);
      
      return list;
   }
   
   //Select User's chat list - unread
   @Override
   public List<AirdndChatVO> selectChatListUnread(int user_idx) {
      List<AirdndChatVO> list = airdnd_chat_dao.selectChatListUnread(user_idx);
      
      return list;
   }
   
   //Select User's messages list with filter
   @Override
   public List<AirdndChatMsgsVO> selectMsgList(int user_idx, int host_idx, String all_hidden_unread) {
      List<AirdndChatMsgsVO> list = airdnd_chat_dao.selectMsgList(user_idx, host_idx, all_hidden_unread);
      
      return list;
   }
   
   //Select latest msg in User's chat list
   @Override
   public AirdndChatMsgsVO selectLatestMsg(int user_idx, int host_idx, String all_hidden_unread) {
      AirdndChatMsgsVO vo = airdnd_chat_dao.selectLatestMsg(user_idx, host_idx, all_hidden_unread);
      
      return vo;
   }
   
   //Select the host info list
   @Override
   public List<AirdndHostVO> selectHostList(int user_idx, String all_hidden_unread) {
      List<AirdndHostVO> list = airdnd_chat_dao.selectHostList(user_idx, all_hidden_unread);
      
      return list;
   }
   
   //Select the userResInfo
   @Override
   public AirdndUserResInfoVO selectUserResInfo(int user_idx, int host_idx) {
      AirdndUserResInfoVO vo = airdnd_chat_dao.selectUserResInfo(user_idx, host_idx);

      return vo;
   }
   
   //Select host_idx chatting
   @Override
   public AirdndChatVO selectHostChat(int user_idx, int host_idx) {
      AirdndChatVO vo = airdnd_chat_dao.selectHostChat(user_idx, host_idx);
      
      return vo;
   }
   
   //Insert chatting
   @Override
   public AirdndChatVO insertChat(AirdndChatVO vo) {
      AirdndChatVO vo2 = airdnd_chat_dao.insertChat(vo);
      
      return vo2;
   }
   
   //Insert chatting
   @Override
   public int insertMsg(AirdndChatMsgsVO vo) {
      int res = airdnd_chat_dao.insertMsg(vo);
      
      return res;
   }
}