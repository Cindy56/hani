/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.finance.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.finance.entity.FinanceRecharge;
import com.game.modules.finance.entity.ReceiveBankNo;
import com.game.modules.finance.service.FinanceRechargeService;
import com.game.modules.finance.service.ReceiveBankNoService;
import com.game.modules.sys.entity.User;

/**
 * 账户充值管理Controller
 * 
 * @author David
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/financeRecharge")
public class FinanceRechargeController extends BaseController {

	@Autowired
	private FinanceRechargeService financeRechargeService;
	@Autowired
	private ReceiveBankNoService receiveBankNoService;
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
	
	/**
	 * 数据权限测试：查看本部门，查看下级，查看本人
	 * @param financeRecharge
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("finance:financeRecharge:view")
	@RequestMapping(value = {"list", ""})
	public String list(FinanceRecharge financeRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FinanceRecharge> page = financeRechargeService.findPage(new Page<FinanceRecharge>(request, response), financeRecharge); 
		model.addAttribute("page", page);
		return "modules/finance/financeRechargeList";
	}

	@RequiresPermissions("finance:financeRecharge:edit")
	@RequestMapping(value = "form")
	public String form(FinanceRecharge financeRecharge, Model model) {
		//TODO:相关验证		
		
		//充值表单提交 产生一条充值记录 状态支付中
		User currentUser = UserUtils.getUser();
//		user.setId("00ff432e9e77424e82a6e4752a6f4c37");
		financeRecharge.setUser(currentUser);
		financeRecharge.setCompanyId(currentUser.getCompany().getId());
		financeRecharge.setOfficeId(currentUser.getOffice().getId());
		financeRecharge.setRechargeNo("充值单编号");
		financeRecharge.setRechargeDate(new Date());
		financeRecharge.setPaymentChannelId("工商银行");
		financeRecharge.setStatus("0");
		//随机产生6位数附言
		financeRecharge.setValidateCode(String.valueOf((int)((Math.random()*9+1)*100000)));
		financeRechargeService.save(financeRecharge);

		model.addAttribute("financeRecharge", financeRecharge);
		//return "modules/finance/financeRechargeForm";
		return "redirect:"+Global.getAdminPath()+"/finance/financeRecharge/list"; 
	}
	
	

	@RequestMapping(value = "rechargeForm")
	public String rechargeForm(Model model) {
		List<ReceiveBankNo> list = receiveBankNoService.findList(new ReceiveBankNo());
		model.addAttribute("list", list);
		return "modules/finance/financeRechargeForm";
	}
	
	
/*	@RequiresPermissions("trade:financeRecharge:edit")
	@RequestMapping(value = "save")
	public String save(FinanceRecharge financeRecharge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, financeRecharge)){
			
			
			return form(financeRecharge, model);
		}
		financeRechargeService.save(financeRecharge);
		addMessage(redirectAttributes, "保存账户充值成功");
		//TODO:重定向到第三方充值平台
		return "redirect:"+Global.getAdminPath()+"/trade/financeRecharge/?repage";
	}*/
	
/*	@RequiresPermissions("trade:financeRecharge:edit")*/
	@RequestMapping(value = "delete")
	public String delete(FinanceRecharge financeRecharge, RedirectAttributes redirectAttributes) {
		financeRechargeService.delete(financeRecharge);
		addMessage(redirectAttributes, "撤销充值成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeRecharge/list"; 
	}	
	


}