/**
 * 
 */
package com.game.hall.modules.member.service;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.AccountMgrApi;
import com.game.hall.modules.member.dao.PersonalDataDao;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.entity.MemberAccountCard;




@Service
@Transactional(readOnly = true)
public class PersonalDataService implements AccountMgrApi{

	@Autowired
	private PersonalDataDao personalDataDao;

	@Override
	public List<Map<String,Object>> findAllCart() {
		List<Map<String,Object>> list = personalDataDao.findAllCart();
		return list;
	}

	@Override
	public List<Map<String,Object>> get(String userId) {
		return  personalDataDao.get(userId);
	
	}

	@Override
	public int updateMemberAccount(MemberAccount memberAccount) {
		return 0;
		
		//return personalDataDao.updateMemberAccount(memberAccount);
	}

	@Override
	@Transactional(readOnly = false)
	public int modifySec(String id, String newPassWord) {
		
		return personalDataDao.modifySec(id, newPassWord);
	}

	@Override
	public int insertCard(MemberAccountCard memberAccountCard) {
		
		return personalDataDao.insertCard(memberAccountCard);
	}

	@Override
	public int insert(MemberAccount memberAccount) {
		return 0;
		//return personalDataDao.insert(memberAccount);
	}
	 
	public String getSec(String userId) {
		
		return personalDataDao.getSec(userId);
	}

	public int delCard(String bankCardNo) {
		// TODO Auto-generated method stub
		return personalDataDao.delCard(bankCardNo);
	}

	public Map<String, Object> memberAccount(String userId) {
		// TODO Auto-generated method stub
		return personalDataDao.memberAccount(userId);
	}

	@Transactional(readOnly = false)
	public int modifyMemberAccount(Map<String, String[]> map) {
        Set<Entry<String, String[]>> set = map.entrySet();  
        Iterator<Entry<String, String[]>> it = set.iterator();  
        Map<String,String> map1 = new HashMap<String, String>();
        Map<String,String> map2 = new HashMap<String, String>();
        String userId = "";
        while (it.hasNext()) {  
            Entry<String, String[]> entry = it.next();  
            //取出key entry.getKey()
            System.out.println(entry.getKey());
            for (String i : entry.getValue()) {  
                //取出value  i
            	if(entry.getKey().equals("userId")) {
            		userId = i;
            	}
            	if(entry.getKey().equals("blance") || entry.getKey().equals("mobileNo") || entry.getKey().equals("qqNo") ) {
            		
            	map1.put(entry.getKey(), i);
            	}else if(entry.getKey().equals("loginName") || entry.getKey().equals("eMail")) {
            		map2.put(entry.getKey(), i);
            		
            	}
            }  
        }  
        	map1.put("userId", userId);
        	map2.put("userId", userId);
        	int i = personalDataDao.modifyMemberAccount(map1);
    		int j = personalDataDao.modifyUser(map2);
        if(i>0 || j>0) {
        	return 1;
        }else {
        	return 0;
        }
       
        
	}



	public Map isNormalStatus(String userId) {
		return personalDataDao.isNormalStatus(userId);
	}



}
