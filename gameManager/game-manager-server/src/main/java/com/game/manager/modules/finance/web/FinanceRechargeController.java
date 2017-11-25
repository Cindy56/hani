/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.finance.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.common.persistence.Page;
import com.game.common.utils.StringUtils;
import com.game.common.web.BaseController;
import com.game.modules.finance.entity.FinanceRecharge;
import com.game.modules.finance.service.FinanceRechargeService;

/**
 * 账户充值管理Controller
 * @author David
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/financeRecharge")
public class FinanceRechargeController extends BaseController {

	@Autowired
	private FinanceRechargeService financeRechargeService;
	
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
	
	@RequiresPermissions("finance:financeRecharge:view")
	@RequestMapping(value = {"list", ""})
	public String list(FinanceRecharge financeRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FinanceRecharge> page = financeRechargeService.findPage(new Page<FinanceRecharge>(request, response), financeRecharge); 
		model.addAttribute("page", page);
		return "modules/finance/financeRechargeList";
	}

	@RequiresPermissions("finance:financeRecharge:view")
	@RequestMapping(value = "form")
	public String form(FinanceRecharge financeRecharge, Model model) {
		model.addAttribute("financeRecharge", financeRecharge);
		return "modules/finance/financeRechargeForm";
	}

}