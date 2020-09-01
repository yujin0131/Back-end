package service;

import java.util.List;
import vo.AirdndUserVO;

public interface AirdndUserServiecI {
   
   List<AirdndUserVO> userselect();
   
   int emailcheck(String email_check);
   
   int signup(AirdndUserVO vo);
   
   AirdndUserVO signin(AirdndUserVO vo);
   
}