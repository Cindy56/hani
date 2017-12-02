/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.companylottery.web;

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
import com.game.manager.modules.companylottery.entity.CompanyLottery;
import com.game.manager.modules.companylottery.service.CompanyLotteryService;

/**
 * 公司彩票配置Controller
 * @author Terry
 * @version 2017-12-02
 */
@Controller
@RequestMapping(value = "${adminPath}/companylottery/companyLottery")
public class CompanyLotteryController extends BaseController {

	@Autowired
	private CompanyLotteryService companyLotteryService;
	
	@ModelAttribute
	public CompanyLottery get(@RequestParam(required=false) String id) {
		CompanyLottery entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = companyLotteryService.get(id);
		}
		if (entity == null){
			entity = new CompanyLottery();
		}
		return entity;
	}
	
	@RequiresPermissions("companylottery:companyLottery:view")
	@RequestMapping(value = {"list", ""})
	public String list(CompanyLottery companyLottery, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CompanyLottery> page = companyLotteryService.findPage(new Page<CompanyLottery>(request, response), companyLottery); 
		model.addAttribute("page", page);
		return "modules/companylottery/companyLotteryList";
	}

	@RequiresPermissions("companylottery:companyLottery:view")
	@RequestMapping(value = "form")
	public String form(CompanyLottery companyLottery, Model model) {
		model.addAttribute("companyLottery", companyLottery);
		return "modules/companylottery/companyLotteryForm";
	}

	@RequiresPermissions("companylottery:companyLottery:edit")
	@RequestMapping(value = "save")
	public String save(CompanyLottery companyLottery, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, companyLottery)){
			return form(companyLottery, model);
		}
		companyLotteryService.save(companyLottery);
		addMessage(redirectAttributes, "保存公司产片成功");
		return "redirect:"+Global.getAdminPath()+"/companylottery/companyLottery/?repage";
	}
	
	@RequiresPermissions("companylottery:companyLottery:edit")
	@RequestMapping(value = "delete")
	public String delete(CompanyLottery companyLottery, RedirectAttributes redirectAttributes) {
		companyLotteryService.delete(companyLottery);
		addMessage(redirectAttributes, "删除公司产片成功");
		return "redirect:"+Global.getAdminPath()+"/companylottery/companyLottery/?repage";
	}

}