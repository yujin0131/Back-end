package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndHomeDAO;
import vo.AirdndHomeVO;


@Service("airdndhomeService")
public class AirdndHomeService implements AirdndHomeServiceI{
	
	@Autowired
	AirdndHomeDAO airdnd_home_dao;
	
	@Override
	public List<AirdndHomeVO> homeselect(){

		List<AirdndHomeVO> list = airdnd_home_dao.select();

		return list;
	}
	


}
