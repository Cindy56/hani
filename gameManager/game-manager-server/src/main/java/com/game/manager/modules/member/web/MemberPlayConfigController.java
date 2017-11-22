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

import com.game.common.config.Global;
import com.game.common.persistence.Page;
import com.game.common.web.BaseController;
import com.game.common.utils.StringUtils;
import com.game.modules.member.entity.MemberPlayConfig;
import com.game.manager.modules.member.service.MemberPlayConfigService;

/**
 * 会员玩法奖金配置Controller
 * @author freeman
 * @version 2017-11-20
 */
@Controller
@RequestMapping(value = "${adminPath}/member/memberPlayConfig")
public class MemberPlayConfigController extends BaseController {

	@Autowired
	private MemberPlayConfigService memberPlayConfigService;
	
	@ModelAttribute
	public MemberPlayConfig get(@RequestParam(required=false) String id) {
		MemberPlayConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberPlayConfigService.get(id);
		}
		if (entity == null){
			entity = new MemberPlayConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("member:memberPlayConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberPlayConfig memberPlayConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemberPlayConfig> page = memberPlayConfigService.findPage(new Page<MemberPlayConfig>(request, response), memberPlayConfig); 
		model.addAttribute("page", page);
		return "modules/member/memberPlayConfigList";
	}

	@RequiresPermissions("member:memberPlayConfig:view")
	@RequestMapping(value = "form")
	public String form(MemberPlayConfig memberPlayConfig, Model model) {
		model.addAttribute("memberPlayConfig", memberPlayConfig);
		return "modules/member/memberPlayConfigForm";
	}

	@RequiresPermissions("member:memberPlayConfig:edit")
	@RequestMapping(value = "save")
	public String save(MemberPlayConfig memberPlayConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberPlayConfig)){
			return form(memberPlayConfig, model);
		}
		memberPlayConfigService.save(memberPlayConfig);
		addMessage(redirectAttributes, "保存会员玩法奖金配置成功");
		return "redirect:"+Global.getAdminPath()+"/member/memberPlayConfig/?repage";
	}
	
	@RequiresPermissions("member:memberPlayConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberPlayConfig memberPlayConfig, RedirectAttributes redirectAttributes) {
		memberPlayConfigService.delete(memberPlayConfig);
		addMessage(redirectAttributes, "删除会员玩法奖金配置成功");
		return "redirect:"+Global.getAdminPath()+"/member/memberPlayConfig/?repage";
	}

}