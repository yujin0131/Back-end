package dao;

import java.util.List;

import vo.AirdndChatMsgsVO;
import vo.AirdndChatVO;
import vo.AirdndHostVO;
import vo.AirdndUserResInfoVO;

public interface AirdndChatDAOI {
   //Select User's chat list - all
   List<AirdndChatVO> selectChatListAll(int user_idx);
   //Select User's chat list - hidden
   List<AirdndChatVO> selectChatListHidden(int user_idx);
   //Select User's chat list - unread
   List<AirdndChatVO> selectChatListUnread(int user_idx);
   //Select User's messages list with filter
   List<AirdndChatMsgsVO> selectMsgList(int user_idx, int host_idx, String all_hidden_unread);
   //Select latest msg in User's chat list
   AirdndChatMsgsVO selectLatestMsg(int user_idx, int host_idx, String all_hidden_unread);
   
   //Select the host info list
   List<AirdndHostVO> selectHostList(int user_idx, String all_hidden_unread);
   //Select the userResInfo
   AirdndUserResInfoVO selectUserResInfo(int user_idx, int host_idx);
   //Select host_idx chatting
   AirdndChatVO selectHostChat(int user_idx, int host_idx);
   //Insert chatting
   AirdndChatVO insertChat(AirdndChatVO vo);
   //Insert message
   int insertMsg(AirdndChatMsgsVO vo);
}