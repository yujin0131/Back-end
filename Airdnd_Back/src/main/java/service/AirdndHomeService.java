package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndHomeDAO;
import vo.AirdndHomeVO;

@Service("airdndroomService")
public class AirdndHomeService implements AirdndHomeServiceI{
	
	@Autowired
	AirdndHomeDAO airdnd_room_dao;
	
	@Override
	public List<AirdndHomeVO> daoserviceconnect(){

		List<AirdndHomeVO> list = airdnd_room_dao.select();

		return list;
	}
	
	@Override
	public int daoserviceinsert(AirdndHomeVO vo) {
		
		int res = airdnd_room_dao.insert(vo);
		return res;
		
	}

}
