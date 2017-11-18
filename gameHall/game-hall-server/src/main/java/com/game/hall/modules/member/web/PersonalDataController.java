/**
 * 
 */
package com.game.hall.modules.member.web;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.hall.common.utils.StringUtils;
import com.game.hall.modules.member.entity.MemberAccount;
import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.modules.memberAccountCard.service.MemberAccountCardService;
import com.game.hall.utils.PassWordUtils;

/**
 * @author antonio
 *
 */
@Controller
@RequestMapping("/member")
public class PersonalDataController {

	@Autowired
	private PersonalDataService personalDataService;
	private MemberAccountCardService memberAccountCardService;

	// 查找某个用户个人信息
	@ResponseBody
	@RequestMapping(value = "/personalData", method = RequestMethod.GET)
	/*
	 * public MemberAccount personalData(String id) { MemberAccount memberAccount =
	 * personalDataService.get(id); return memberAccount; }
	 */

	public ResultData personalData(String id) {

		// BetData betData;
		// ResultData rd = personalDataService.get(id);

		return null;
	}

	/*
	 * //修改用户信息
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/modifyPersonalData", method = RequestMethod.GET)
	 * public String modifyPersonalData(MemberAccount memberAccount) {
	 * //根据id查找这个用户的信息 memberAccount =
	 * personalDataService.get(memberAccount.getId()); //前台变更的数据封装到memberAccount
	 * memberAccount.setBlanceNo("12345"); int updateInfo =
	 * personalDataService.update(memberAccount); return "修改成功"+ updateInfo+"条记录";
	 * 
	 * }
	 */

	// 查找所有用户个人信息
	@ResponseBody
	@RequestMapping(value = "/allList", method = RequestMethod.GET)
	public List<MemberAccount> personalDataList() {
		//
		return null;
	}

	// 验证用户安全码
	@ResponseBody
	@RequestMapping(value = "/verSecPwd", method = RequestMethod.GET)
	public String verSecPwd(String id, String secPassWord) {
		// 根据id查询该用户的安全密码
		String str = memberAccountCardService.getSec(id);
		if (StringUtils.isNotBlank(secPassWord)) {
			if (PassWordUtils.validatePassword(secPassWord, str)) {
				// 输入的安全密码正确
				return "输入的安全码正确";
			} else {
				return "输入的安全码错误";
			}
		} else {
			return "安全码不能为空";
		}

	}

	// 验证用户安全码
	public boolean verSecPassWord(String id, String secPassWord) {
		// 根据id查询该用户的安全密码
		String str = memberAccountCardService.getSec(id);
		if (StringUtils.isNotBlank(secPassWord)) {
			if (PassWordUtils.validatePassword(secPassWord, str)) {
				// 输入的安全密码正确
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	// 修改用户安全码
	@ResponseBody
	@RequestMapping(value = "/modifySecPwd", method = RequestMethod.GET)
	public String modifySecPwd(String id, String secPassWord, String newPassWord) {
		// 验证该用户输入的安全码是否正确
		if (verSecPassWord(id, secPassWord)) {
			// 验证通过 更新安全密码
			int i = 0;// personalDataService.modifySec(id, newPassWord);

			return "安全码更新成功";
		} else {
			// 验证不通过

			return "输入的安全码错误11";
		}

	}

	@Test
	public void test() {

		// 根据id查询该用户的安全密码
		String str = memberAccountCardService.getSec("7d6a3f913c3f4882b9a6f2ec8a348bda");

		/*
		 * if(PassWordUtils.validatePassword("123", str)) { System.out.println("11"); }
		 */

		// System.out.println(PassWordUtils.validatePassword("123",
		// "cd78e9ac1a77553a7bec20d9e58715fb7c5b9108ee141b061c5da9b0"));
	}

}
