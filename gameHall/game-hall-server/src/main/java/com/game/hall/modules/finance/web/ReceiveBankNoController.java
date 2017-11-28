/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.finance.web;

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
import com.game.modules.finance.entity.ReceiveBankNo;
import com.game.modules.finance.service.ReceiveBankNoService;

/**
 * 收款人银行卡管理Controller
 * @author David
 * @version 2017-11-28
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/receiveBankNo")
public class ReceiveBankNoController extends BaseController {

	@Autowired
	private ReceiveBankNoService receiveBankNoService;
	
	@ModelAttribute
	public ReceiveBankNo get(@RequestParam(required=false) String id) {
		ReceiveBankNo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = receiveBankNoService.get(id);
		}
		if (entity == null){
			entity = new ReceiveBankNo();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(ReceiveBankNo receiveBankNo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReceiveBankNo> page = receiveBankNoService.findPage(new Page<ReceiveBankNo>(request, response), receiveBankNo); 
		model.addAttribute("page", page);
		return "modules/finance/receiveBankNoList";
	}

	@RequiresPermissions("finance:receiveBankNo:view")
	@RequestMapping(value = "form")
	public String form(ReceiveBankNo receiveBankNo, Model model) {
		model.addAttribute("receiveBankNo", receiveBankNo);
		return "modules/finance/receiveBankNoForm";
	}

	@RequiresPermissions("finance:receiveBankNo:edit")
	@RequestMapping(value = "save")
	public String save(ReceiveBankNo receiveBankNo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, receiveBankNo)){
			return form(receiveBankNo, model);
		}
		receiveBankNoService.save(receiveBankNo);
		addMessage(redirectAttributes, "保存收款人信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/finance/receiveBankNo/?repage";
	}
	
	@RequiresPermissions("finance:receiveBankNo:edit")
	@RequestMapping(value = "delete")
	public String delete(ReceiveBankNo receiveBankNo, RedirectAttributes redirectAttributes) {
		receiveBankNoService.delete(receiveBankNo);
		addMessage(redirectAttributes, "删除收款人信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/finance/receiveBankNo/?repage";
	}

}