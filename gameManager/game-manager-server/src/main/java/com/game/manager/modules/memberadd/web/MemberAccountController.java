/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.memberadd.web;

import java.util.List;
import java.util.Map;

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
import com.game.manager.common.utils.StringUtils;
import com.game.manager.common.web.BaseController;
import com.game.manager.modules.memberadd.entity.MemberAccount;
import com.game.manager.modules.memberadd.service.MemberAccountService;

/**
 * 会员开户Controller
 * @author freeman
 * @version 2017-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/memberadd/memberAccount")
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
	
	@RequiresPermissions("memberadd:memberAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberAccount memberAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemberAccount> page = memberAccountService.findPage(new Page<MemberAccount>(request, response), memberAccount); 
		model.addAttribute("page", page);
		//查询返点信息
		List<Map<String, Object>> list=memberAccountService.getLotteryPlayConfig();
		if(!list.isEmpty()) {
			for (Map<String, Object> map : list) {
				map.get("lotteryCode");//彩票代码
				map.get("parentName");//彩票名称
				map.get("playCode");//玩法代码
				map.get("name");//玩法名称
				map.get("playType");//玩法模式:0 直选返点	1 不定位返点 2 所有返点 3 趣味型返点 	
				map.get("winningProbability");//中奖几率
				map.get("commissionRateMax");//最大返水
				map.get("commissionRateMin");//最小返水
			}
		}
		//奖金组=2÷单注中奖概率×（1-平台利润）
		
		return "modules/memberadd/memberAccountList";
	}

	@RequiresPermissions("memberadd:memberAccount:view")
	@RequestMapping(value = "form")
	public String form(MemberAccount memberAccount, Model model) {
		model.addAttribute("memberAccount", memberAccount);
		return "modules/memberadd/memberAccountForm";
	}

	@RequiresPermissions("memberadd:memberAccount:edit")
	@RequestMapping(value = "save")
	public String save(MemberAccount memberAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberAccount)){
			return form(memberAccount, model);
		}
		memberAccountService.save(memberAccount);
		addMessage(redirectAttributes, "保存会员信息成功");
		return "redirect:"+Global.getAdminPath()+"/memberadd/memberAccount/?repage";
	}
	
	@RequiresPermissions("memberadd:memberAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberAccount memberAccount, RedirectAttributes redirectAttributes) {
		memberAccountService.delete(memberAccount);
		addMessage(redirectAttributes, "删除会员信息成功");
		return "redirect:"+Global.getAdminPath()+"/memberadd/memberAccount/?repage";
	}

}