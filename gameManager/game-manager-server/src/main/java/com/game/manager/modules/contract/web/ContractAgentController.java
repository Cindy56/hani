/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.contract.web;

import java.util.List;
import java.util.Map;

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
import com.game.manager.modules.sys.service.SystemService;
import com.game.manager.modules.sys.utils.UserUtils;
import com.game.modules.contract.entity.Contract;
import com.game.modules.contract.service.ContractService;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.member.service.MemberPlayConfigService;
import com.game.modules.sys.entity.User;

/**
 * 开代理Controller
 * @author freeman
 * @version 2017-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/contract/contractAgent")
public class ContractAgentController extends BaseController {

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private MemberPlayConfigService memberPlayConfigService;
	
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
	
	@RequiresPermissions("contract:agent:contract:view")
	@RequestMapping(value = {"list", ""})
	public String list(Contract contract, HttpServletRequest request, HttpServletResponse response, Model model) {
		//只查询开户类型为代理的数据
		contract.setOpenType("2");
		Page<Contract> page = contractService.findPage(new Page<Contract>(request, response), contract); 
		model.addAttribute("page", page);
		return "modules/contract/contractAgentList";
	}

	@RequiresPermissions("contract:agent:contract:view")
	@RequestMapping(value = "form")
	public String form(Contract contract, Model model) {
		User sessionUser=UserUtils.getUser();
		//当前登录用户公司id
		sessionUser.getOffice().getId();
		//根据公司id查contract_lottery表，公司彩种玩法配置
		String lottery_config="[{\"isNewRecord\":true,\"lotteryCode\":{\"isNewRecord\":true,\"code\":\"SSC_CQ\",\"name\":\"重庆时时彩\"},\"playCode\":\"SSC_CQ_QSZX\",\"name\":\"前三直选\",\"playType\":\"x\",\"winningProbability\":\"0.001\",\"commissionRateMax\":\"0.15\",\"commissionRateMin\":\"0.02\",\"betRateLimit\":\"10000\",\"isEnable\":\"1\"},{\"isNewRecord\":true,\"lotteryCode\":{\"isNewRecord\":true,\"code\":\"SSC_CQ\",\"name\":\"重庆时时彩\"},\"playCode\":\"SSC_CQ_QSZX2\",\"name\":\"前三组选\",\"playType\":\"x\",\"winningProbability\":\"0.006\",\"commissionRateMax\":\"0.15\",\"commissionRateMin\":\"0.02\",\"betRateLimit\":\"10000\",\"isEnable\":\"1\"},{\"isNewRecord\":true,\"lotteryCode\":{\"isNewRecord\":true,\"code\":\"SSC_CQ\",\"name\":\"重庆时时彩\"},\"playCode\":\"SSC_CQ_QEZX\",\"name\":\"前二直选\",\"playType\":\"x\",\"winningProbability\":\"0.009\",\"commissionRateMax\":\"0.15\",\"commissionRateMin\":\"0.02\",\"betRateLimit\":\"10000\",\"isEnable\":\"1\"},{\"isNewRecord\":true,\"lotteryCode\":{\"isNewRecord\":true,\"code\":\"SSC_CQ\",\"name\":\"重庆时时彩\"},\"playCode\":\"SSC_CQ_QEZX2\",\"name\":\"前二组选\",\"playType\":\"x\",\"winningProbability\":\"0.018\",\"commissionRateMax\":\"0.15\",\"commissionRateMin\":\"0.02\",\"betRateLimit\":\"10000\",\"isEnable\":\"1\"},{\"isNewRecord\":true,\"lotteryCode\":{\"isNewRecord\":true,\"code\":\"SSC_CQ\",\"name\":\"重庆时时彩\"},\"playCode\":\"SSC_CQ_QS\",\"name\":\"前三\",\"playType\":\"x\",\"winningProbability\":\"0.09\",\"commissionRateMax\":\"0.15\",\"commissionRateMin\":\"0.02\",\"betRateLimit\":\"10000\",\"isEnable\":\"1\"},{\"isNewRecord\":true,\"lotteryCode\":{\"isNewRecord\":true,\"code\":\"SSC_CQ\",\"name\":\"重庆时时彩\"},\"playCode\":\"SSC_5XING_ZHIXUANFU\",\"name\":\"时时彩5星直选复式\",\"playType\":\"x\",\"winningProbability\":\"0.00001\",\"commissionRateMax\":\"0.15\",\"commissionRateMin\":\"0.02\",\"betRateLimit\":\"10000\",\"isEnable\":\"1\"},{\"isNewRecord\":true,\"lotteryCode\":{\"isNewRecord\":true,\"code\":\"SSC_CQ\",\"name\":\"重庆时时彩\"},\"playCode\":\"SSC_5XING_ZHIXUANDAN\",\"name\":\"时时彩5星直选单式\",\"playType\":\"x\",\"winningProbability\":\"0.00001\",\"commissionRateMax\":\"0.15\",\"commissionRateMin\":\"0.02\",\"betRateLimit\":\"10000\",\"isEnable\":\"1\"}]";
		Map<String, List<LotteryPlayConfig>> resetMap = memberPlayConfigService.formatPlayConfig(lottery_config);
		contract.setMap(resetMap);
		model.addAttribute("contract", contract);
		return "modules/contract/contractAgentForm";
	}

	@RequiresPermissions("contract:agent:contract:edit")
	@RequestMapping(value = "save")
	@Transactional(readOnly=false)
	public String save(Contract contract, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, contract)){
			return form(contract, model);
		}
		User seesionUser = UserUtils.getUser();
		contract.setCurrentUser(seesionUser);
		
		User user=contract.getUser();
		if(StringUtils.isBlank(contract.getId())) {
			contract.setSecPassword(SystemService.entryptPassword(contract.getSecPassword()));
			user.setPassword(SystemService.entryptPassword(user.getPassword()));
		}
		contract.setUser(user);
		contract=contractService.saveAgent(contract);
		
		addMessage(redirectAttributes, "保存代理成功");
		return "redirect:"+Global.getAdminPath()+"/contract/contractAgent/?repage";
	}
	
	@RequiresPermissions("contract:agent:contract:edit")
	@RequestMapping(value = "delete")
	public String delete(Contract contract, RedirectAttributes redirectAttributes) {
		contractService.delete(contract);
		addMessage(redirectAttributes, "删除代理成功");
		return "redirect:"+Global.getAdminPath()+"/contract/contractAgent/?repage";
	}

}