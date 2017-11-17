/**
 * 
 */
package com.game.hall.modules.member.service;


import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.AccountMgrApi;
import com.entity.BetData;
import com.entity.ResultData;
import com.game.hall.modules.member.dao.PersonalDataDao;
import com.game.hall.modules.member.entity.MemberAccount;

/**
 * @author antonio
 *
 */
@Service
public class PersonalDataService implements AccountMgrApi{

	@Autowired
	private PersonalDataDao personalDataDao;

/*	public MemberAccount get(String name) {
		return personalDataDao.get(name);
	}*/
	
	public ResultData get(String id) {
		personalDataDao.get(id);

		return null;
	}
	
	public List<MemberAccount> findAllList(){
		return personalDataDao.findAllList();
	}
	
	public int update(MemberAccount memberAccount) {
		return personalDataDao.update(memberAccount);
	}
	
	public int modifySec(String id, String newPassWord) {
		return personalDataDao.modifySec(id,newPassWord);
	}

	@Override
	public String test1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData addBet(BetData betData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData openToday() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData personalData(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
