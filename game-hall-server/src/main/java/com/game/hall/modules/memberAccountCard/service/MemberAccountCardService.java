package com.game.hall.modules.memberAccountCard.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.hall.modules.memberAccountCard.dao.MemberAccountCardDao;
import com.game.hall.modules.memberAccountCard.entity.MemberAccountCard;


@Service
public class MemberAccountCardService {
	@Autowired
	private MemberAccountCardDao memberAccountCardDao;
    
	//根据账户id查询银行卡该id下的银行卡账号
	public List<MemberAccountCard> get(String id) {
		List<MemberAccountCard> memberAccountCard = memberAccountCardDao.get(id);
		return memberAccountCard;
	}
	
	//账户新增银行卡
	public int addAccountCard(MemberAccountCard memberAccountCard) {
		return memberAccountCardDao.insert(memberAccountCard);
	}
	
/*	//查询安全码
	public String getSec(String id) {
		return memberAccountCardDao.getSec(id);		
	}*/

}