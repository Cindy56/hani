/**
 * 
 */
package com.game.hall.modules.member.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.game.hall.modules.member.entity.MemberAccount;
import com.game.hall.modules.member.service.PersonalDataService;

/**
 * @author antonio
 *
 */
@Controller
@RequestMapping("/member")
public class PersonalDataController {

	@Autowired
	private PersonalDataService personalDataService;
	//查找某个用户个人信息
	@ResponseBody
	@RequestMapping(value = "/personalData", method = RequestMethod.GET)
	public MemberAccount personalData(String id) {
		MemberAccount memberAccount = personalDataService.get(id);
		return memberAccount;
	}
	
	//修改用户信息
	@ResponseBody
	@RequestMapping(value = "/modifyPersonalData", method = RequestMethod.GET)
	public String modifyPersonalData(MemberAccount memberAccount) {
		//根据id查找这个用户的信息
		memberAccount = personalDataService.get(memberAccount.getId());
		//前台变更的数据封装到memberAccount
		memberAccount.setBlanceNo("12345");
		int updateInfo = personalDataService.update(memberAccount);
		return "修改成功"+ updateInfo+"条记录";
		
	}
	
	
	//查找所有用户个人信息
	@ResponseBody
	@RequestMapping(value = "/allList", method = RequestMethod.GET)
	public List<MemberAccount> personalDataList() {
		List<MemberAccount> memberAccountList = personalDataService.findAllList();
		System.out.println("============"+memberAccountList);
		return memberAccountList;
	}
	


}
