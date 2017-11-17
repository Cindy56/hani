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
import com.entity.MemberAccountCard;
import com.entity.ResultData;
import com.game.hall.common.utils.StringUtils;
import com.game.hall.modules.member.dao.PersonalDataDao;
import com.game.hall.modules.member.entity.MemberAccount;
import com.game.hall.modules.memberAccountCard.service.MemberAccountCardService;
import com.game.hall.utils.PassWordUtils;

/**
 * @author antonio
 *
 */
@Service
public class PersonalDataService implements AccountMgrApi{

	@Autowired
	private PersonalDataDao personalDataDao;
	@Autowired
	MemberAccountCardService memberAccountCardService;
	
    /**
     * 增加银行卡绑定
     * 
     */
	@Override
	public int addMemberAccountCard(MemberAccountCard memberAccountCard) {
		return personalDataDao.insertCard(memberAccountCard);
	}


	

		
	//验证用户安全码
	public boolean verSecPassWord(String id,String secPassWord) {
			//根据id查询该用户的安全密码
	      String str = 	memberAccountCardService.getSec(id);
	      if(StringUtils.isNotBlank(secPassWord)) {
	    	 if(PassWordUtils.validatePassword(secPassWord, str)) {
	    		 //输入的安全密码正确
	    		 return true;
	    	 }else {
	    		 return false;
	    	 }
	      }else {
	    	  return false;
	      }
		
	}
	
    /**
     * 
     * 修改安全密码
     */
	@Override
	public String modifySecPwd(String id, String secPassWord, String newPassWord) {
		//验证该用户输入的安全码是否正确
		   if(verSecPassWord(id,secPassWord)) {
			//验证通过  更新安全密码
			 //修改密码
			   personalDataDao.modifySec(id, newPassWord);
			  return "安全码更新成功";
		   }else {
		    //验证不通过
			   
			return "输入的安全码错误";
		   }
		
	}



	/**
	 * 
	 * 增加账户
	 */
	@Override
	public int saveMemberAccount(com.entity.MemberAccount memberAccount) {
			return personalDataDao.insert(memberAccount);
	}
	



}
