/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.trade.web;

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
import com.game.common.web.BaseController;
import com.game.common.utils.StringUtils;
import com.game.modules.trade.entity.FinanceTradeDetail;
import com.game.manager.modules.trade.service.FinanceTradeDetailService;

/**
 * 账变流水Controller
 * @author jerry
 * @version 2017-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/financeTradeDetail")
public class FinanceTradeDetailController extends BaseController {

	@Autowired
	private FinanceTradeDetailService financeTradeDetailService;
	
	@ModelAttribute
	public FinanceTradeDetail get(@RequestParam(required=false) String id) {
		FinanceTradeDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = financeTradeDetailService.get(id);
		}
		if (entity == null){
			entity = new FinanceTradeDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("trade:financeTradeDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(FinanceTradeDetail financeTradeDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FinanceTradeDetail> page = financeTradeDetailService.findPage(new Page<FinanceTradeDetail>(request, response), financeTradeDetail); 
		model.addAttribute("page", page);
		return "modules/trade/financeTradeDetailList";
	}

	@RequiresPermissions("trade:financeTradeDetail:view")
	@RequestMapping(value = "form")
	public String form(FinanceTradeDetail financeTradeDetail, Model model) {
		model.addAttribute("financeTradeDetail", financeTradeDetail);
		return "modules/trade/financeTradeDetailForm";
	}

	@RequiresPermissions("trade:financeTradeDetail:edit")
	@RequestMapping(value = "save")
	public String save(FinanceTradeDetail financeTradeDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, financeTradeDetail)){
			return form(financeTradeDetail, model);
		}
		financeTradeDetailService.save(financeTradeDetail);
		addMessage(redirectAttributes, "保存账变流水成功");
		return "redirect:"+Global.getAdminPath()+"/trade/financeTradeDetail/?repage";
	}
	
	

}