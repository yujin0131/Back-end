package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndRoomDAO;
import vo.AirdndRoomVO;

@Service("airdndroomService")
public class AirdndRoomService implements AirdndRoomServiceI{
	
	@Autowired
	AirdndRoomDAO airdnd_room_dao;
	
	@Override
	public List<AirdndRoomVO> daoserviceconnect(){

		List<AirdndRoomVO> list = airdnd_room_dao.select();

		return list;
	}

}
