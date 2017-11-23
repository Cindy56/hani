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
import com.game.common.utils.StringUtils;
import com.game.common.web.BaseController;
import com.game.modules.member.entity.MemberAccountCard;
import com.game.modules.member.service.MemberAccountCardService;

/**
 * 会员银行卡管理Controller
 * @author freeman
 * @version 2017-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/memberbank/memberAccountCard")
public class MemberAccountCardController extends BaseController {

	//银行卡状态
	private static final String NORMAL = "1";//1正常
	private static final String FREEZE = "2";//2冻结
	
	@Autowired
	private MemberAccountCardService memberAccountCardService;
	
	@ModelAttribute
	public MemberAccountCard get(@RequestParam(required=false) String id) {
		MemberAccountCard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberAccountCardService.get(id);
		}
		if (entity == null){
			entity = new MemberAccountCard();
		}
		return entity;
	}
	
	@RequiresPermissions("memberbank:memberAccountCard:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberAccountCard memberAccountCard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemberAccountCard> page = memberAccountCardService.findPage(new Page<MemberAccountCard>(request, response), memberAccountCard); 
		model.addAttribute("page", page);
		return "modules/member/memberAccountCardList";
	}

	/*@RequiresPermissions("memberbank:memberAccountCard:view")
	@RequestMapping(value = "form")
	public String form(MemberAccountCard memberAccountCard, Model model) {
		model.addAttribute("memberAccountCard", memberAccountCard);
		return "modules/memberbank/memberAccountCardForm";
	}*/

	/*@RequiresPermissions("memberbank:memberAccountCard:edit")
	@RequestMapping(value = "save")
	public String save(MemberAccountCard memberAccountCard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberAccountCard)){
			return form(memberAccountCard, model);
		}
		memberAccountCardService.save(memberAccountCard);
		addMessage(redirectAttributes, "保存会员银行卡成功");
		return "redirect:"+Global.getAdminPath()+"/memberbank/memberAccountCard/?repage";
	}*/
	
	/*@RequiresPermissions("memberbank:memberAccountCard:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberAccountCard memberAccountCard, RedirectAttributes redirectAttributes) {
		memberAccountCardService.delete(memberAccountCard);
		addMessage(redirectAttributes, "删除会员银行卡成功");
		return "redirect:"+Global.getAdminPath()+"/memberbank/memberAccountCard/?repage";
	}*/
	
	@RequiresPermissions("memberbank:memberAccountCard:edit")
	@RequestMapping(value = "changeStatus")
	public String changeStatus(MemberAccountCard memberAccountCard, RedirectAttributes redirectAttributes) {
		String status=memberAccountCard.getStatus();
		String message="会员银行卡审核中，暂时不能操作";
		if(NORMAL.equals(status)) {
			memberAccountCard.setStatus(FREEZE);
			message="会员银行卡冻结成功";
		}else if(FREEZE.equals(status)) {
			memberAccountCard.setStatus(NORMAL);
			message="会员银行卡启用成功";
		}
		memberAccountCardService.save(memberAccountCard);
		addMessage(redirectAttributes,message);
		return "redirect:"+Global.getAdminPath()+"/memberbank/memberAccountCard/?repage";
	}

}