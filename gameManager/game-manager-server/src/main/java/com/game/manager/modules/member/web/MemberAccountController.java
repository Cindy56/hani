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