/**
 * 
 */
package com.game.hall.modules.member.web;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.BetData;
import com.entity.MemberAccount;
import com.entity.MemberAccountCard;
import com.entity.ResultData;


import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.modules.memberAccountCard.service.MemberAccountCardService;
import com.game.hall.utils.PassWordUtils;


@Controller
@RequestMapping("/member")
public class PersonalDataController {
   
	@Autowired
	private PersonalDataService personalDataService;
	@Autowired
	private MemberAccountCardService memberAccountCardService;
/*	//查找某个用户个人信息
	@ResponseBody
	@RequestMapping(value = "/personalData", method = RequestMethod.GET)
	public MemberAccount personalData(String id) {
		MemberAccount memberAccount = personalDataService.get(id);
		return memberAccount;
	}
	*/
/*	public ResultData personalData(String id) {

		// BetData betData;
		ResultData rd = personalDataService.get(id);

		return null;
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
	
	
	//验证用户安全码
	@ResponseBody
	@RequestMapping(value = "/verSecPwd", method = RequestMethod.GET)
	public String verSecPwd(String id,String secPassWord) {
			//根据id查询该用户的安全密码
	      String str = 	memberAccountCardService.getSec(id);
	      if(StringUtils.isNotBlank(secPassWord)) {
	    	 if(PassWordUtils.validatePassword(secPassWord, str)) {
	    		 //输入的安全密码正确
	    		 return "输入的安全码正确";
	    	 }else {
	    		 return "输入的安全码错误";
	    	 }
	      }else {
	    	  return "安全码不能为空";
	      }
		
	}
	
	//验证用户安全码
	public  boolean verSecPassWord(String id,String secPassWord) {
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
	
	
	//修改用户安全码
	@ResponseBody
	@RequestMapping(value = "/modifySecPwd", method = RequestMethod.GET)
	public String modifySecPwd(String id,String secPassWord,String newPassWord) {
			//验证该用户输入的安全码是否正确
		   if(verSecPassWord(id,secPassWord)) {
			//验证通过  更新安全密码
			   int i = personalDataService.modifySec(id, newPassWord);
			   
		  return "安全码更新成功";
		   }else {
		    //验证不通过
			   
			return "输入的安全码错误11";
		   }
		
		
	}
	

	@Test
	public void test() {
		
		//根据id查询该用户的安全密码
	      String str = 	memberAccountCardService.getSec("7d6a3f913c3f4882b9a6f2ec8a348bda");

	    	 if(PassWordUtils.validatePassword("123", str)) {
	    		 System.out.println("11");
	    	 }


	      
	      
	//	System.out.println(PassWordUtils.validatePassword("123", "cd78e9ac1a77553a7bec20d9e58715fb7c5b9108ee141b061c5da9b0"));
	}
*/
	/**
	 * 
	 * 银行卡绑定
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/addMemberAccountCard", method = RequestMethod.GET)
	public String addMemberAccountCard(MemberAccountCard memberAccountCard) {
			personalDataService.addMemberAccountCard(memberAccountCard);
			return null;
		
	}
	
	/**
	 * 增加账户
	 * @param memberAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveMemberAccount", method = RequestMethod.GET)
	public String saveMemberAccount(MemberAccount memberAccount) {
			personalDataService.saveMemberAccount(memberAccount);
			return null;
		
	}
	
	/**
	 * 
	 * 修改密码
	 * @param id
	 * @param secPassWord
	 * @param newPassWord
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifySecPwd", method = RequestMethod.GET)
	public String modifySecPwd(String id, String secPassWord, String newPassWord) {
			personalDataService.modifySecPwd(id, secPassWord, newPassWord);
			return null;
		
	}
	

	/**
	 * 根据账户id查询此账户下所有绑定的银行卡信息 查看list列表显示该用户所有的银行卡信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/accountCardInfo", method = RequestMethod.GET)
	public List<MemberAccountCard> accountCardInfo(String id) {
		List<MemberAccountCard> memberAccountCard = personalDataService.accountCardInfo(id);
		return memberAccountCard;
	}
	
	/** 
	 * 删除银行卡
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAccountCard", method = RequestMethod.GET)
	public String deleteAccountCard(String bankCardNo) {
		personalDataService.deleteAccountCard(bankCardNo);
		return "删除银行卡成功";
	}  
}


