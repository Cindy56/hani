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

import com.entity.ResultData;
import com.game.hall.modules.member.dao.PersonalDataDao;
import com.game.hall.modules.member.entity.MemberAccount;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * @author antonio
 *
 */
@Service
public class PersonalDataService implements AccountMgrApi {

	@Override
	public String test1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData addBet(LotteryOrder betData) {
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
