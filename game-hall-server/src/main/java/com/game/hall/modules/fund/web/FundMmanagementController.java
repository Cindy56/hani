package com.game.hall.modules.fund.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.hall.common.utils.StringUtils;
import com.game.hall.modules.fund.service.FundMmanagementService;
import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.modules.member.web.PersonalDataController;
import com.game.hall.modules.order.service.LotteryOrderService;
import com.game.hall.utils.PassWordUtils;

@Controller
@RequestMapping("/fundMmanagement")
public class FundMmanagementController {
	@Autowired
	private FundMmanagementService fundMmanagementService;
	@Autowired
	private PersonalDataService personalDataService;
	
	
	/**
	 * 
	 * @param userId 用户id
	 * @param secPassWord 安全码明文
	 * @return
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/verSecPassWord", method = RequestMethod.GET)
	public void verSecPassWord(String userId,String secPassWord,HttpServletRequest req,HttpServletResponse res) throws ServletException {
		 String str = 	personalDataService.getSec(userId);
	      if(StringUtils.isNotBlank(secPassWord)) {
		    	 if(PassWordUtils.validatePassword(secPassWord, str)) {
		    		 //输入的安全密码正确
		    		 System.out.println("=====================输入的安全码正确");
		    		 //转发到判断银行卡判断绑定请求
		    		 try {
						req.getRequestDispatcher("/fundMmanagement/isNoCardBinding?"+userId).forward(req, res);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
		    	 }else {
		    		 System.out.println("=====================输入的安全码错误");
		    		 //重定向输入安全密码框
		    	 }
		      }else {
		    	  System.out.println("=====================安全码不能为空");
		    	  //重定向输入安全密码框
		      }

		
		
		
	}
	
	/**
	 * 判断用户的账户状态是否正常
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/isNormalStatus", method = RequestMethod.GET)
	public String isNormalStatus(String userId,HttpServletRequest req,HttpServletResponse res,Model model) {
		List<Map<String,Object>> list = personalDataService.get(userId);
		//判断该用户是否有银行卡绑定
		
		return "";
		
	}
	
	/**
	 * 判断用户是否有绑定的银行卡
	 * @param userId
	 * @return
	 */
	public boolean isCardBinding(String userId) {
		List<Map<String,Object>> list = personalDataService.get(userId);
		//判断该用户是否有银行卡绑定
		if(list.size() == 0) {
			return false;
		}
		
		return true;
		
	}
	
	
	/**
	 * 判断用户是否有绑定的银行卡
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/isNoCardBinding", method = RequestMethod.GET)
	public String isNoCardBinding(String userId,HttpServletRequest req,HttpServletResponse res,Model model) {
		List<Map<String,Object>> list = personalDataService.get(userId);
		//判断该用户是否有银行卡绑定
		if(list.size() == 0) {
			System.out.println("======================"+"用户"+userId+"没有绑定银行卡");
			//重定向到绑定银行卡页面
			try {
				res.sendRedirect(req.getContextPath()+"/NewFile.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		    System.out.println("======================"+"用户"+userId+"绑定银行卡");
		    //转发到银行卡充值页面
		    Map map = new HashMap<String,Object>();
		    map.put("userId", userId);
		    model.addAttribute("map",map);
		    return "/modules/fund/fundList";
	}
	

	
	/**
	 * 前台充值表单提交
	 * 账户充值功能实现           
	 * @param userId
	 */
	@ResponseBody
	@RequestMapping(value = "/accountRecharge", method = RequestMethod.GET)
	public void accountRecharge(String userId,String secPassWord) {
		//判断安全码是否正确
	//	verSecPassWord(userId,secPassWord);
		PersonalDataController personalDataController = new PersonalDataController();
		//账户user_id 账户安全码明文
	//	personalDataController.verSecPassWord(userId, secPassWord);
		
	}	
}
