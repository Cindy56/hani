/**
 * 
 */
package com.game.hall.modules.member.service;


import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.hall.modules.member.dao.PersonalDataDao;
import com.game.hall.modules.member.entity.MemberAccount;

/**
 * @author antonio
 *
 */
@Service
public class PersonalDataService {

	@Autowired
	private PersonalDataDao personalDataDao;

	public MemberAccount get(String name) {
		return personalDataDao.get(name);
	}
	
	public List<MemberAccount> findAllList(){
		return personalDataDao.findAllList();
	}
	
	public int update(MemberAccount memberAccount) {
		return personalDataDao.update(memberAccount);
	}

}
