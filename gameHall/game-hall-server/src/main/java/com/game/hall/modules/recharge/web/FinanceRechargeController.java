package com.game.hall.modules.recharge.web;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.common.config.Global;
import com.game.common.persistence.Page;
import com.game.common.utils.StringUtils;
import com.game.common.web.BaseController;
import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.modules.recharge.entity.FinanceRecharge;
import com.game.hall.modules.recharge.service.FinanceRechargeService;

/**
 * 账户充值管理Controller
 * @author David
 * @version 2017-11-24
 */
@Controller
@RequestMapping(value = "/recharge/financeRecharge")
public class FinanceRechargeController extends BaseController {

	@Autowired
	private FinanceRechargeService financeRechargeService;
	@Autowired
	private PersonalDataService personalDataService;
	
	@ModelAttribute
	public FinanceRecharge get(@RequestParam(required=false) String id) {
		FinanceRecharge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = financeRechargeService.get(id);
		}
		if (entity == null){
			entity = new FinanceRecharge();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(FinanceRecharge financeRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FinanceRecharge> page = financeRechargeService.findPage(new Page<FinanceRecharge>(request, response), financeRecharge); 
		model.addAttribute("page", page);
		return "modules/recharge/financeRechargeList";
	}


	@RequestMapping(value = "form")
	public String form(FinanceRecharge financeRecharge, Model model) {
		model.addAttribute("financeRecharge", financeRecharge);
		return "modules/recharge/financeRechargeForm";
	}


	@RequestMapping(value = "save")
	public String save(FinanceRecharge financeRecharge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, financeRecharge)){
			return form(financeRecharge, model);
		}
		financeRechargeService.save(financeRecharge);
		addMessage(redirectAttributes, "保存账户充值成功");
		return "redirect:"+Global.getAdminPath()+"/recharge/financeRecharge/?repage";
	}
	

	@RequestMapping(value = "delete")
	public String delete(FinanceRecharge financeRecharge, RedirectAttributes redirectAttributes) {
		financeRechargeService.delete(financeRecharge);
		addMessage(redirectAttributes, "删除账户充值成功");
		return "redirect:"+Global.getAdminPath()+"/recharge/financeRecharge/?repage";
	}
	
	
	/**
	 * 充值服务
	 * @param userId
	 * @param secPassWord
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "reChargeForm")
	public String reChargeForm(String userId,String secPassWord,RedirectAttributes redirectAttributes) {
		//验证安全码
		boolean verSecPassWordFlag = personalDataService.verSecPassWord(userId, secPassWord);
		if(BooleanUtils.isFalse(verSecPassWordFlag)) {
				//安全码错误 重定向到安全码输入页面
				System.out.println("==========================安全码错误");
				return "redirect:"+"/recharge/financeRecharge/reChargeList";
		}
		
		//验证账户状态
		boolean statusFlag = personalDataService.isNormalStatus(userId);
		if(BooleanUtils.isFalse(statusFlag)) {
			//账户状态异常  重定向到安全码输入页面
			System.out.println("==========================账户状态异常");
			return "redirect:"+"/recharge/financeRecharge/error";
			
		}
		
		//验证账户是否绑定银行卡
		boolean isBindingCardFlag = personalDataService.isBindingCard(userId);
		if(BooleanUtils.isFalse(isBindingCardFlag)) {
			//用户未绑定银行卡 跳转到银行卡绑定页面
			System.out.println("==========================用户未绑定银行卡 请绑定！");
			return "redirect:"+"/recharge/financeRecharge/bindingCard";
		}
		
		
		//跳转到充值页面
		return "/modules/recharge/chongzhi";
	}
	
	
	/**
	 * 充值页面
	 * @return
	 */
	@RequestMapping(value = "reChargeList")
	public String reChargeList() {
		return "/modules/recharge/anquanma";
	} 
	
	/**
	 * 错误页面
	 * @return
	 */
	@RequestMapping(value = "error")
	public String error() {
		return "/modules/recharge/error";
	} 
	
	/**
	 * 绑定银行卡
	 * @return
	 */
	@RequestMapping(value = "bindingCard")
	public String bindingCard() {
		return "/modules/recharge/bangdingka";
	} 

}