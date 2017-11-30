/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.contract.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.game.manager.modules.sys.service.SystemService;
import com.game.manager.modules.sys.utils.UserUtils;
import com.game.modules.contract.entity.Contract;
import com.game.modules.contract.service.ContractService;
import com.game.modules.sys.entity.User;

/**
 * 开设分公司Controller
 * @author freeman
 * @version 2017-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/contract/contractCompany")
public class ContractCompanyController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(ContractCompanyController.class);
	
	//公司状态
	private static final String NORMAL = "3";//正常
	private static final String FREEZE = "4";//冻结

	@Autowired
	private ContractService contractService;
	
	@ModelAttribute
	public Contract get(@RequestParam(required=false) String id) {
		Contract entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = contractService.get(id);
		}
		if (entity == null){
			entity = new Contract();
		}
		return entity;
	}
	
	@RequiresPermissions("contract:company:contract:view")
	@RequestMapping(value = {"list", ""})
	public String list(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model) {
		//只查询开户类型为公司的数据
		contract.setOpenType("1");
		Page<Contract> page = contractService.findPage(new Page<Contract>(request, response), contract); 
		model.addAttribute("page", page);
		return "modules/contract/contractCompanyList";
	}

	@RequiresPermissions("contract:company:contract:view")
	@RequestMapping(value = "form")
	public String form(Contract contract, Model model) {
		model.addAttribute("contract", contract);
		return "modules/contract/contractCompanyForm";
	}

	@RequiresPermissions("contract:company:contract:edit")
	@RequestMapping(value = "save")
	public String save(Contract contract, Model model, RedirectAttributes redirectAttributes) {
		
		String userLoginName=contract.getUserName();
		/*if(null!=systemService.getUserByLoginName(userLoginName)) {
			addMessage(redirectAttributes, "登录名称已存在！");
			return "redirect:"+Global.getAdminPath()+"/contract/contractCompany/?repage";
		}*/
		
		if (!beanValidator(model, contract)){
			return form(contract, model);
		}
		contract.setCurrentUser(UserUtils.getUser());
		User user=contract.getUser();
		if(StringUtils.isBlank(contract.getId())) {
			contract.setSecPassword(SystemService.entryptPassword(contract.getSecPassword()));
			user.setPassword(SystemService.entryptPassword(user.getPassword()));
		}
		contract.setUser(user);
		
		addMessage(redirectAttributes, "保存公司成功");
		return "redirect:"+Global.getAdminPath()+"/contract/contractCompany/?repage";
	}
	
	@RequiresPermissions("contract:company:contract:edit")
	@RequestMapping(value = "delete")
	public String delete(Contract contract, RedirectAttributes redirectAttributes) {
		contractService.delete(contract);
		addMessage(redirectAttributes, "删除公司成功");
		return "redirect:"+Global.getAdminPath()+"/contract/contractCompany/?repage";
	}
	
	
	/*@RequestMapping(value = "checkCompanyName")
	public String checkCompanyName(String oldCompanyName, String companyName) {
		if (companyName !=null && companyName.equals(oldCompanyName)) {
			return "true";
		} else if (companyName !=null && systemService.getUserByLoginName(companyName) == null) {
			return "true";
		}
		return "false";
	}*/
	@RequiresPermissions("contract:company:contract:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(Contract contract, RedirectAttributes redirectAttributes) {
		String status=contract.getStatus();
		String message="公司审核中，暂时不能操作";
		if(NORMAL.equals(status)) {
			contract.setStatus(FREEZE);
			message="公司冻结成功";
		}else if(FREEZE.equals(status)) {
			contract.setStatus(NORMAL);
			message="公司启用成功";
		}
		contractService.save(contract);
		addMessage(redirectAttributes,message);
		return "redirect:"+Global.getAdminPath()+"/contract/contractCompany/?repage";
	}

}