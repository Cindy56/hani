/**
 * 
 */
package com.game.hall.modules.member.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.common.utils.StringUtils;
import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.modules.utils.PassWordUtils;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.entity.MemberAccountCard;
import com.game.modules.member.service.MemberAccountCardService;


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
	public ResultData personalData(String userId) {
		ResultData rs = new ResultData();
		List<Map<String,Object>> list = personalDataService.getUserMap(userId);
		if(list.size() == 0) {
			//该用户未绑定银行卡
			rs.setErrorCode(001);
			rs.setMessage("该用户未绑定银行卡");
			rs.setData(list);
		}else {
			//查询银行卡信息成功
			rs.setErrorCode(200);
			rs.setMessage("查询成功");
			rs.setData(list);		
		}
		return rs;
		
	}
	
	
	//增加银行卡绑定
	@ResponseBody
	@RequestMapping(value = "/insertCard", method = RequestMethod.GET)
	public ResultData insertCard(MemberAccountCard memberAccountCard) {
		ResultData rs = new ResultData();
/*		memberAccountCard.setAccountId("222");
		memberAccountCard.setAccountType("222");
		memberAccountCard.setBankBranchCity("222");
		memberAccountCard.setBankBranchName("222");
		memberAccountCard.setBankBranchProvince("222");
		memberAccountCard.setBankCardHolder("222");
		memberAccountCard.setBankCardNo("222");
		memberAccountCard.setBankCode("222");
		memberAccountCard.setBeginCreateDate(new Date());
		memberAccountCard.setCreateBy("222");
		memberAccountCard.setCreateBy("222");
		memberAccountCard.setDelFlag("222");
		memberAccountCard.setId("222");
		memberAccountCard.setMobileNo("222");
		memberAccountCard.setQqNo("222");
		memberAccountCard.setStatus("222");
		memberAccountCard.setUpdateBy("222");
		User user = new User();
		user.setCompany(company);
		user.setCreateBy(createBy);
				user.setId(id);
		user.setIsNewRecord(isNewRecord);
		user.setLoginDate(loginDate);
		user.setLoginFlag(loginFlag);
		user.setLoginIp(loginIp);
		user.setLoginName(loginName);
		user.setMobile(mobile);
		user.setuser.setCreateDate(createDate);
		user.setCurrentUser(currentUser);
		user.setDelFlag(delFlag);
		user.setEmail(email);
		user.setId(id);
		user.setIsNewRecord(isNewRecord);
		user.setLoginDate(loginDate);
		user.setLoginFlag(loginFlag);
		user.setLoginIp(loginIp);
		user.setLoginName(loginName);
		user.setMobile(mobile);
		user.setName(name);
		user.setNewPassword(newPassword);
		user.setNo(no);
		user.setOffice(office);
		user.setOldLoginDate(oldLoginDate);
		user.setOldLoginIp(oldLoginIp);
		user.setOldLoginName(oldLoginName);
		user.setPage(page)*/
		
		
		
		
	//	memberAccountCard.setUser(user);
		int i = personalDataService.insertCard(memberAccountCard);
		if(i > 0) {
			//插入银行卡成功
			rs.setErrorCode(200);
			rs.setMessage("新增银行卡成功");
			rs.setData(null);		
		}
		return rs;
	}
	
		
	/**
	 * 
	 * 根据银行卡卡号删除银行卡信息
	 * @param bankCardNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delCard", method = RequestMethod.GET)
	public ResultData delCard(String bankCardNo) {
		ResultData rs = new ResultData();
		int i = 0;
		i= personalDataService.delCard(bankCardNo);
		if(i > 0) {
			//删除银行卡成功
			rs.setErrorCode(200);
			rs.setMessage("删除银行卡成功");
			rs.setData(null);
		}else {
			//删除银行卡失败
			rs.setErrorCode(001);
			rs.setMessage("删除银行卡失败");
			rs.setData(null);
		}
		return rs;

		
	}
	
	
	/**
	 * 
	 * 根据user_id查看个人账户信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/memberAccount", method = RequestMethod.GET)
	public ResultData memberAccount(String userId) {
		ResultData rs = new ResultData();
		Map<String,Object> map = personalDataService.memberAccount(userId);
		if(map == null) {
			//查询该用户信息失败
			rs.setErrorCode(001);
			rs.setMessage("查询用户信息失败");
			rs.setData(null);
		}else {
			//查询该用户信息成功
			rs.setErrorCode(200);
			rs.setMessage("查询账户信息成功");
			rs.setData(map);		
		}
		return rs;

		
	}
	
	
	/**
	 * 根据user_id修改个人账户信息
	 * @param user_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyMemberAccount", method = RequestMethod.GET)
	public ResultData modifyPersonalData(HttpServletRequest  request) { 
		//获取前台提交的表单数据
		 Map<String, String[]> map = request.getParameterMap();  	
		 ResultData rs = new ResultData();
		 int i = personalDataService.modifyMemberAccount(map);    
		 if(i>=1) {
			//修改成功
				rs.setErrorCode(200);
				rs.setMessage("修改成功");
				rs.setData(null);		
				return  rs;
		 }else {
				//修改失败
				rs.setErrorCode(001);
				rs.setMessage("修改失败");
				rs.setData(null);		
				return  rs;
		 }
		
	}
	
	
	//修改用户安全码
	@ResponseBody
	@RequestMapping(value = "/modifySecPwd", method = RequestMethod.GET)
	public ResultData modifySecPwd(String userId,String secPassWord,String newPassWord) {
			ResultData rs = new ResultData();
			//验证该用户输入的安全码是否正确
		   if(verSecPassWord(userId,secPassWord)) {
			//验证通过  更新安全密码
			 int i = personalDataService.modifySec(userId, PassWordUtils.entryptPassword(newPassWord));	
			 rs.setErrorCode(001);
		     rs.setMessage("修改安全码成功");
			 rs.setData(null);		 
			 return rs;
		   }else {
		    //验证不通过
			//修改失败
				rs.setErrorCode(001);
				rs.setMessage("输入的安全码错误");
				rs.setData(null);		 
				return rs;
		   }
		
		
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
	public List<Map<String,Object>> personalDataList() {
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
	public String verSecPwd(String userId,String secPassWord) {
			//根据id查询该用户的安全密码
	      String str = 	personalDataService.getSec(userId);
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
	
	

	
	

	
	
	//增加账户
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public int insertCard(MemberAccount memberAccount) {
		return personalDataService.insert(memberAccount);
	}
	
	
/*	*//**
	 * 检查用户的账户状态是否正常
	 * @param userId
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/isNormalStatus", method = RequestMethod.GET)
	public String isNormalStatus(String userId) {
		Map map = new HashMap();
		map = personalDataService.isNormalStatus(userId);
		return  (String) map.get("status");
	}*/
	
	
	@Test
	public void test() {
		


	      
	      
		System.out.println(PassWordUtils.validatePassword("123", "cd78e9ac1a77553a7bec20d9e58715fb7c5b9108ee141b061c5da9b0"));
	}

}