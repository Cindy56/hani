/**
 * 
 */
package com.game.hall.modules.member.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.AccountMgrApi;
import com.game.manager.modules.order.entity.LotteryOrder;
import com.entity.ResultData;
import com.game.hall.modules.member.dao.PersonalDataDao;
import com.game.hall.modules.member.entity.MemberAccount;
import com.game.hall.modules.memberAccountCard.entity.MemberAccountCard;


@Service
public class PersonalDataService implements AccountMgrApi{

	@Autowired
	private PersonalDataDao personalDataDao;

	@Override
	public List<MemberAccountCard> findAllCart() {
		List<MemberAccountCard> list = personalDataDao.findAllCart();
		return list;
	}

	@Override
	public List<Map<String,Object>> get(String id) {
		return  personalDataDao.get(id);
	
	}

	@Override
	public int updateMemberAccount(MemberAccount memberAccount) {
		
		return personalDataDao.updateMemberAccount(memberAccount);
	}

	@Override
	public int modifySec(String id, String newPassWord) {
		
		return personalDataDao.modifySec(id, newPassWord);
	}

	@Override
	public int insertCard(MemberAccountCard memberAccountCard) {
		
		return personalDataDao.insertCard(memberAccountCard);
	}

	@Override
	public int insert(MemberAccount memberAccount) {
		return personalDataDao.insert(memberAccount);
	}
	 
	public String getSec(String id) {
		
		return personalDataDao.getSec(id);
	}



}
