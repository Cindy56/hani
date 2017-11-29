package com.game.hall.modules.bet.web;

import java.util.List;
import java.util.Random;

import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.sys.entity.User;
import com.game.modules.sys.service.SystemServiceFacade;

public class RandomMember {

	public static User getMember(MemberAccountService memberAccountService, SystemServiceFacade systemServiceFacade) {
		MemberAccount memberAccount = new MemberAccount();

		List<MemberAccount> members = memberAccountService.findList(memberAccount);

		Random rand = new Random();

		int i = rand.nextInt(members.size());
		
		String userId = members.get(i).getUser().getId();

		
		// 获得user
		User user = systemServiceFacade.getUserById(userId);
		
				//UserUtils.getUser(); // new User();
		// user.setId("a4fff2ed9be246268fb742d9c684dba0");// 用户ID
		// user.setName("00username");// 用户名
		// Office company = new Office();
		// company.setCode("code");// 组织编号
		// user.setCompany(company);
		//

		return user;
	}

}
