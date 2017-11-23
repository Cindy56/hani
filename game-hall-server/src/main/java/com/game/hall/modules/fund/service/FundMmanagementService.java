package com.game.hall.modules.fund.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hall.common.utils.StringUtils;
import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.utils.PassWordUtils;

@Service
@Transactional(readOnly = true)
public class FundMmanagementService {
	
	
	
	@Autowired
	private PersonalDataService personalDataService;
	
	
	
	/**
	 * 验证安全码
	 * @param userId 用户id
	 * @param secPassWord 安全码明文
	 * @return  boolean
	 */
	public int verSecPassWord(String userId,String secPassWord) {
		 String str = 	personalDataService.getSec(userId);
		 if(StringUtils.isNotBlank(secPassWord)) {
			 if(PassWordUtils.validatePassword(secPassWord, str)) {
	    		 //输入的安全密码正确
	    		 System.out.println("=====================输入的安全码正确");
	    		 return 1;
		 }
		
		}else {
					//输入的密码为空
					System.out.println("=====================输入的安全码为空");
					return 2;
				
		}
		 			//输出的密码错误
		return 0;
	}
}
