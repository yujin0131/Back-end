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
	public List<AirdndUserVO> daoserviceconnect(){

		List<AirdndUserVO> list = airdnd_user_dao.select();

		return list;
	}
	
	@Override
	public int daoserviceinsert(AirdndUserVO vo){

		int res = airdnd_user_dao.insert(vo);

		return res;
	}
	
}
