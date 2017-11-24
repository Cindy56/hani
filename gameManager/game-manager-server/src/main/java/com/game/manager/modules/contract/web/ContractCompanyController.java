/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.contract.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.common.config.Global;
import com.game.common.persistence.Page;
import com.game.common.utils.StringUtils;
import com.game.common.web.BaseController;
import com.game.manager.modules.sys.service.OfficeService;
import com.game.manager.modules.sys.service.SystemService;
import com.game.manager.modules.sys.utils.UserUtils;
import com.game.modules.contract.entity.Contract;
import com.game.modules.contract.service.ContractService;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.Role;
import com.game.modules.sys.entity.User;
import com.game.modules.todo.entity.TodoTask;
import com.game.modules.todo.service.TodoTaskService;

/**
 * 开设分公司Controller
 * @author freeman
 * @version 2017-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/contract/contractCompany")
public class ContractCompanyController extends BaseController {

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private TodoTaskService todoTaskService;
	
	/*************************机构模板*************************/
	/**
	 * 公司模板id
	 */
	private final static String COMPANY_TEMPLET_ID="ce6f9bcd5512471cabd6a281d4f0f19c";
	/**
	 * 市场模板id
	 */
	private final static String MARKET_HOUSE_TEMPLET_ID="1724d89569ab48208824b2b47e7f76a6";
	/**
	 * 财务模板id
	 */
	private final static String FINANCE_TEMPLET_ID="87aa06e95bbc44bba59c0c44a6b10da3";
	/**
	 * 推广模板id
	 */
	private final static String POPULARIZE_TEMPLET_ID="e6debb2c9a2c41a7b52b488287c84514";
	/**
	 * 客服模板id
	 */
	private final static String CUSTOM_SERVICE_TEMPLET_ID="dce6062d5a1848689a644549e4d1e9cb";
	
	/*************************角色模板*************************/
	/**
	 * 公司股东
	 */
	private final static String SHAREHOLDER_ID="cf94fb8be8e74a6b847e9be418c3efa5";
	/**
	 * 客服专员
	 */
	private final static String CUSTOM_SERVICE_ID="7fa24f3b31644f5f8b8e39abaf1e20c3";
	/**
	 * 客服主管
	 */
	private final static String CUSTOM_SERVICE_SUPERVISO_ID="646785a40d89486bb69cdbf79999e0cc";
	/**
	 * 财务专员 
	 */
	private final static String FINANCE_ID="266b49ce68ab450399dbc9dc9cee0cff";
	/**
	 * 财务主管
	 */
	private final static String FINANCE_SUPERVISO_ID="292fd51d14a34f1fb652e42510b0edf6";
	
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
	@Transactional(readOnly=false)
	public String save(Contract contract, Model model, RedirectAttributes redirectAttributes) {
		
		//保存用户信息
		User user=contract.getUser();
		//登录名称
		String userLoginName=contract.getUserName();
		if(null!=systemService.getUserByLoginName(userLoginName)) {
			addMessage(redirectAttributes, "登录名称已存在！");
			return "redirect:"+Global.getAdminPath()+"/contract/contractCompany/?repage";
		}
		user.setLoginName(userLoginName);
		
		/****************************************************
		 ********************* 复制模板start********************
		 ****************************************************/
		//复制模板公司
		Office company=officeService.get(COMPANY_TEMPLET_ID);
		company.setId(null);
		company.setName(contract.getOrgName());
		officeService.save(company);
		System.out.println(company.getName()+"id："+company.getId());
		
		//复制市场模板
		Office marketHouse=officeService.get(MARKET_HOUSE_TEMPLET_ID);
		marketHouse.setId(null);
		marketHouse.setName(contract.getOrgName()+"市场部");
		marketHouse.setParent(company);
		marketHouse.setParentIds(company.getParentId()+","+company.getId());
		officeService.save(marketHouse);
		System.out.println(marketHouse.getName()+"id："+marketHouse.getId());
		
		//财务模板
		Office finance=officeService.get(FINANCE_TEMPLET_ID);
		finance.setId(null);
		finance.setName(contract.getOrgName()+"财务部");
		finance.setParent(company);
		finance.setParentIds(company.getParentId()+","+company.getId());
		officeService.save(finance);
		System.out.println(finance.getName()+"id："+finance.getId());
		
		//推广模板
		Office popularize=officeService.get(POPULARIZE_TEMPLET_ID);
		popularize.setId(null);
		popularize.setName(contract.getOrgName()+"推广部");
		popularize.setParent(company);
		popularize.setParentIds(company.getParentId()+","+company.getId());
		officeService.save(popularize);
		System.out.println(popularize.getName()+"id："+popularize.getId());
		
		//客服模板
		Office customService=officeService.get(CUSTOM_SERVICE_TEMPLET_ID);
		customService.setId(null);
		customService.setName(contract.getOrgName()+"客服部");
		customService.setParent(company);
		customService.setParentIds(company.getParentId()+","+company.getId());
		officeService.save(customService);
		System.out.println(customService.getName()+"id："+customService.getId());
		/****************************************************
		 ********************* 复制模板end	*********************
		 ****************************************************/
		
		//用户公司
		user.setCompany(company);
		//用户的部门为新建的公司模板
		user.setOffice(company);
		//用户姓名
		user.setName(contract.getUserName());
		user.setLoginName(userLoginName);
		user.setPassword(SystemService.entryptPassword(user.getPassword()));
		user.setNo("");
		
		/****************************************************
		 ********************* 复制角色start********************
		 ****************************************************/
		
		//股东角色
		Role shareholder=systemService.getRole(SHAREHOLDER_ID);
		shareholder.setId(null);
		shareholder.setOffice(company);
		shareholder.setName(contract.getOrgName()+shareholder.getName());
		shareholder.setEnname(contract.getOffice().getCode()+shareholder.getEnname());
		systemService.saveRole(shareholder);
		
		//客服角色
		Role custom=systemService.getRole(CUSTOM_SERVICE_ID);
		custom.setId(null);
		custom.setOffice(company);
		custom.setName(contract.getOrgName()+custom.getName());
		custom.setEnname(contract.getOffice().getCode()+custom.getEnname());
		systemService.saveRole(custom);
		
		//客服主管
		Role customServiceSuperviso=systemService.getRole(CUSTOM_SERVICE_SUPERVISO_ID);
		customServiceSuperviso.setId(null);
		customServiceSuperviso.setOffice(company);
		customServiceSuperviso.setName(contract.getOrgName()+customServiceSuperviso.getName());
		customServiceSuperviso.setEnname(contract.getOffice().getCode()+customServiceSuperviso.getEnname());
		systemService.saveRole(customServiceSuperviso);
		
		//财务角色
		Role fin=systemService.getRole(FINANCE_ID);
		fin.setId(null);
		fin.setOffice(company);
		fin.setName(contract.getOrgName()+fin.getName());
		fin.setEnname(contract.getOffice().getCode()+fin.getEnname());
		systemService.saveRole(fin);
		
		//财务主管
		Role financeSuperviso=systemService.getRole(FINANCE_SUPERVISO_ID);
		financeSuperviso.setId(null);
		financeSuperviso.setOffice(company);
		financeSuperviso.setName(contract.getOrgName()+financeSuperviso.getName());
		financeSuperviso.setEnname(contract.getOffice().getCode()+financeSuperviso.getEnname());
		systemService.saveRole(financeSuperviso);
		
		
		/****************************************************
		 ********************* 复制角色end**********************
		 ****************************************************/
		
		//保存的公司的机构为新建的公司模板
		contract.setOffice(company);
		
		if (!beanValidator(model, contract)){
			return form(contract, model);
		}
		
		user.getRoleList().add(shareholder);
		
		/**
		 * id, 
			company_id, 
			office_id, 
			login_name, 
			password, 
			no, 
			name, 
			email, 
			phone, 
			mobile, 
			user_type, 
			create_by, 
			create_date, 
			update_by, 
			update_date, 
			remarks, 
			login_flag, 
			photo, 
			del_flag
		 */
		//保存用户信息
		systemService.saveUser(user);
		
		
		
		//保存账户信息
		
		//保存contract
		
		//保存config
		
		//分配角色
		
		//公司模板
		
		
		MemberAccount memberAccount=new MemberAccount();
		//获取当前用户信息 从seesion取出
		User seesionUser = UserUtils.getUser();
		memberAccount.setParentAgentId(seesionUser.getId());
		memberAccount.setParentAgentIds(seesionUser.getId()+","+user.getId());
		memberAccount.setOrgId(company);
		memberAccount.setBlance("0");
		memberAccount.setBlanceFrozen("0");
		memberAccount.setStatus("0");
		memberAccount.setUser(user);
		//会员类型为股东
		memberAccount.setAccountType("3");
		memberAccount.setQqNo(contract.getQqNo());
		memberAccount.setMobileNo(contract.getMobileNo());
		memberAccount.setSecPassword(SystemService.entryptPassword(contract.getSecPassword()));
		//账户状态1正常，2冻结
		memberAccount.setStatus("1");
		//保存会员信息
		memberAccountService.save(memberAccount);
		
		
		//保存代办任务
		TodoTask todo=new TodoTask();
		todo.setTitle("公司开户提醒");	//任务标题
		todo.setContent("");	//任务正文
		todo.setType("1"); 	//任务类型
		todo.setStatus("1"); 	//任务状态
		todo.setSenderId(user); 	//申请者
		todo.setReceiverId(seesionUser);	//处理者
		todoTaskService.save(todo);
		
		
		contract.setAccountId(memberAccount.getId());
		//保存公司信息
		contractService.save(contract);
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

}