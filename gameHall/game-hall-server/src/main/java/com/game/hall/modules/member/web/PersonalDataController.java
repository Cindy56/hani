/**
 * 
 */
package com.game.hall.modules.member.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.manager.modules.order.entity.LotteryOrder;
import com.entity.ResultData;
import com.game.hall.common.utils.StringUtils;
import com.game.hall.modules.member.entity.MemberAccount;
import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.modules.memberAccountCard.entity.MemberAccountCard;
import com.game.hall.modules.memberAccountCard.service.MemberAccountCardService;
import com.game.hall.utils.PassWordUtils;


@Controller
@RequestMapping("/member")
public class PersonalDataController {

	@Autowired
	private PersonalDataService personalDataService;
	@Autowired
	private MemberAccountCardService memberAccountCardService;
	
	
	/**
	 * 
	 * 根据id查看个人银行卡信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/personalData", method = RequestMethod.GET)
	public List<Map<String,Object>> personalData(String id) {
		List<Map<String,Object>> list = personalDataService.get(id);
		System.out.println("============================");
		System.out.println(list.get(0).get("updateBy"));
		System.out.println("============================");
		System.out.println(list.get(0).get("user"));
		return personalDataService.get(id);

		
	}
	
	
/*	
	*//**
	 * 根据id修改信息
	 * @param memberAccount
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/modifyPersonalData", method = RequestMethod.GET)
	public String modifyPersonalData(MemberAccount memberAccount) {
		//根据id查找这个用户的信息
		memberAccount = personalDataService.get(memberAccount.getId());
		//前台变更的数据封装到memberAccount
		memberAccount.setBlanceNo("12345");
		int updateInfo = personalDataService.update(memberAccount);
		return "修改成功"+ updateInfo+"条记录";
		
	}*/
	
	
	//查找所有银行卡信息
	@ResponseBody
	@RequestMapping(value = "/allCart", method = RequestMethod.GET)
	public List<MemberAccountCard> personalDataList() {
		return personalDataService.findAllCart();
	}
	
	//修改账户信息
		@ResponseBody
		@RequestMapping(value = "/updateMemberAccount", method = RequestMethod.GET)
		public int updateMemberAccount(MemberAccount memberAccount ) {
			return personalDataService.updateMemberAccount(memberAccount);
		}
		
	
	//验证用户安全码
	@ResponseBody
	@RequestMapping(value = "/verSecPwd", method = RequestMethod.GET)
	public String verSecPwd(String id,String secPassWord) {
			//根据id查询该用户的安全密码
	      String str = 	personalDataService.getSec(id);
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
	      String str = 	personalDataService.getSec(id);
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
			   int i = personalDataService.modifySec(id, PassWordUtils.entryptPassword(newPassWord));
			   
		  return "安全码更新成功";
		   }else {
		    //验证不通过
			   
			return "输入的安全码错误";
		   }
		
		
	}
	
	
	//增加银行卡绑定
	@ResponseBody
	@RequestMapping(value = "/insertCard", method = RequestMethod.GET)
	public int insertCard(MemberAccountCard memberAccountCard) {
		return personalDataService.insertCard(memberAccountCard);
	}
	
	
	//增加账户
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public int insertCard(MemberAccount memberAccount) {
		return personalDataService.insert(memberAccount);
	}
	
/*	@Test
	public void test() {
		
		//根据id查询该用户的安全密码
	      String str = 	memberAccountCardService.getSec("7d6a3f913c3f4882b9a6f2ec8a348bda");

    	 if(PassWordUtils.validatePassword("123", str)) {
	    		 System.out.println("11");
	    	 }


	      
	      
	//	System.out.println(PassWordUtils.validatePassword("123", "cd78e9ac1a77553a7bec20d9e58715fb7c5b9108ee141b061c5da9b0"));
	}*/

}
