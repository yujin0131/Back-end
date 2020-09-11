package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndUserDAO;
import vo.AirdndUserVO;

@Service("airdnduserService")
public class AirdndUserService implements AirdndUserServiecI{

	@Autowired
	AirdndUserDAO airdnd_user_dao;
	
	@Override
	public List<AirdndUserVO> userselect(){

		List<AirdndUserVO> list = airdnd_user_dao.select();

		return list;
	}
	
	@Override
	public int emailcheck(String email_check) {
		
		int res = airdnd_user_dao.select(email_check);
		
		return res;
	}
	
	@Override
	public int signup(AirdndUserVO vo){

		int res = airdnd_user_dao.insert(vo);

		return res;
	}
	
	@Override
	public AirdndUserVO signin(AirdndUserVO vo){
		
		AirdndUserVO res = airdnd_user_dao.select_one(vo);
		return res;
	}
	
	public boolean userishost(int user_idx){
		
		boolean res = airdnd_user_dao.select_user_host(user_idx);
		return res;
	}
	
}
