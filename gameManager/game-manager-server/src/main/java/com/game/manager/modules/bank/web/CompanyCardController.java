/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.bank.web;

import java.util.Date;

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
import com.game.modules.bank.entity.CompanyCard;
import com.game.modules.bank.service.CompanyCardService;
import com.game.modules.sys.entity.User;

/**
 * 银行卡管理Controller
 * @author David
 * @version 2017-11-29
 */
@Controller
@RequestMapping(value = "${adminPath}/bank/companyCard")
public class CompanyCardController extends BaseController {

	@Autowired
	private CompanyCardService companyCardService;
	
	@ModelAttribute
	public CompanyCard get(@RequestParam(required=false) String id) {
		CompanyCard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = companyCardService.get(id);
		}
		if (entity == null){
			entity = new CompanyCard();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(CompanyCard companyCard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CompanyCard> page = companyCardService.findPage(new Page<CompanyCard>(request, response), companyCard); 
		model.addAttribute("page", page);
		return "modules/bank/companyCardList";
	}

	@RequiresPermissions("bank:companyCard:edit") 
	@RequestMapping(value = "form")
	public String form(CompanyCard companyCard, Model model) {
		model.addAttribute("companyCard", companyCard);
		companyCard.setBankCode("银行代码");
		companyCard.setStatus("0");
		companyCard.setCreateDate(new Date());
		companyCard.setCreateBy(new User());
		companyCard.setUpdateDate(new Date());	
		companyCard.setCreateBy(new User("2222"));
		
		companyCardService.save(companyCard);
		return "redirect:"+Global.getAdminPath()+"/bank/companyCard/list";
	}

	
	@RequestMapping(value = "bankform")
	public String bankform(CompanyCard companyCard, Model model) {
		model.addAttribute("companyCard", companyCard);
		return "modules/bank/companyCardForm";
	}
	
	@RequiresPermissions("bank:companyCard:edit")
	@RequestMapping(value = "save")
	public String save(CompanyCard companyCard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, companyCard)){
			return form(companyCard, model);
		}
		companyCardService.save(companyCard);
		addMessage(redirectAttributes, "保存银行卡管理成功");
		return "redirect:"+Global.getAdminPath()+"/bank/companyCard/?repage";
	}
	
	@RequiresPermissions("bank:companyCard:edit")
	@RequestMapping(value = "delete")
	public String delete(CompanyCard companyCard, RedirectAttributes redirectAttributes) {
		companyCardService.delete(companyCard);
		addMessage(redirectAttributes, "删除银行卡管理成功");
		return "redirect:"+Global.getAdminPath()+"/bank/companyCard/?repage";
	}
	
	@RequiresPermissions("bank:companyCard:edit")
	@RequestMapping(value = "disable")
	public String disable(CompanyCard companyCard, RedirectAttributes redirectAttributes) {
		//禁用银行卡
		companyCard.setStatus("2");
		companyCardService.save(companyCard);
		//companyCardService.disable(companyCard);
		addMessage(redirectAttributes, "禁用银行卡管理成功");
		return "redirect:"+Global.getAdminPath()+"/bank/companyCard/?repage";
	}

}