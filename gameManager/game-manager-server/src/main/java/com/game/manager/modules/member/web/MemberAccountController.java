/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.web;

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

import com.game.manager.common.config.Global;
import com.game.manager.common.persistence.Page;
import com.game.manager.common.web.BaseController;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.member.entity.MemberAccount;
import com.game.manager.modules.member.service.MemberAccountService;
import com.game.manager.modules.sys.entity.Office;
import com.game.manager.modules.sys.entity.User;
import com.game.manager.modules.sys.service.SystemService;
import com.game.manager.modules.sys.utils.UserUtils;

/**
 * 会员管理Controller
 * @author David
 * @version 2017-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/member/memberAccount")
public class MemberAccountController extends BaseController {

	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public MemberAccount get(@RequestParam(required=false) String id) {
		MemberAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberAccountService.get(id);
		}
		if (entity == null){
			entity = new MemberAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("member:memberAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberAccount memberAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemberAccount> page = memberAccountService.findPage(new Page<MemberAccount>(request, response), memberAccount); 
		model.addAttribute("page", page);
		return "modules/member/memberAccountList";
	}

	@RequiresPermissions("member:memberAccount:view")
	@RequestMapping(value = "form")
	public String form(MemberAccount memberAccount, Model model) {
		model.addAttribute("memberAccount", memberAccount);
		return "modules/member/memberAccountForm";
	}

	@RequiresPermissions("member:memberAccount:edit")
	@RequestMapping(value = "save")
	public String save(MemberAccount memberAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberAccount)){
			return form(memberAccount, model);
		}
		//保存会员信息
		User user = new User();
		//获取当前用户信息 从seesion取出
		User seesionUser = UserUtils.getUser();
		//新开户用户设置和开户者同一个公司
		user.setCompany(seesionUser.getCompany());
		//新开户用户设置和开户者同一个机构
		user.setOffice(seesionUser.getOffice());
		
		user.setCreateBy(seesionUser.getCreateBy());	
		user.setCreateDate(seesionUser.getCreateDate());
		user.setCurrentUser(seesionUser.getCurrentUser());
		user.setLoginDate(seesionUser.getLoginDate());
		user.setLoginFlag(seesionUser.getLoginFlag());	
		user.setDelFlag(seesionUser.getDelFlag());
		user.setEmail(seesionUser.getEmail());
		user.setIsNewRecord(seesionUser.getIsNewRecord());
		user.setLoginIp(seesionUser.getLoginIp());
		user.setLoginName(seesionUser.getLoginName());
		user.setMobile(seesionUser.getMobile());
		user.setName(seesionUser.getName());
		user.setNo(seesionUser.getNo());	
		user.setOldLoginDate(seesionUser.getOldLoginDate());
		user.setOldLoginIp(seesionUser.getOldLoginIp());
		user.setOldLoginName(seesionUser.getOldLoginName());
		user.setPage(seesionUser.getPage());
		user.setPassword(seesionUser.getPassword());
		user.setPhone(seesionUser.getPhone());
		user.setPhoto(seesionUser.getPhoto());
		user.setRemarks(seesionUser.getRemarks());
		user.setRole(seesionUser.getRole());
		user.setRoleIdList(seesionUser.getRoleIdList());
		user.setSqlMap(seesionUser.getSqlMap());
		user.setUpdateBy(seesionUser.getUpdateBy());
		user.setUpdateDate(seesionUser.getUpdateDate());
		user.setUserType(seesionUser.getUserType());
		user.setLoginName(memberAccount.getUser().getLoginName());
		user.setNewPassword(memberAccount.getUser().getNewPassword());
		
		//保存会员信息
		this.systemService.saveUser(user);	
		//保存账户信息
		
		//设置账户上级ID（登录账号id）
		memberAccount.setParentAgentId(seesionUser.getId());
		//设置账户对应的user表主键
		memberAccount.setUser(user);
		//设置账户对应的机构（登录账号同机构）
		memberAccount.setOrgId(seesionUser.getOffice());
		memberAccountService.save(memberAccount);
		
		addMessage(redirectAttributes, "保存会员管理成功");
		return "redirect:"+Global.getAdminPath()+"/member/memberAccount/?repage";
	}
	
	@RequiresPermissions("member:memberAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberAccount memberAccount, RedirectAttributes redirectAttributes) {
		memberAccountService.delete(memberAccount);
		addMessage(redirectAttributes, "删除会员管理成功");
		return "redirect:"+Global.getAdminPath()+"/member/memberAccount/?repage";
	}

}