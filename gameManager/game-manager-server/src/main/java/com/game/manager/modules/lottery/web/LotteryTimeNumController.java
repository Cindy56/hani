/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.manager.common.config.Global;
import com.game.manager.common.persistence.Page;
import com.game.manager.common.web.BaseController;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.lottery.dto.TimeTask;
import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.lottery.service.LotteryTimeNumService;

/**
 * 开奖时刻和开奖结果Controller
 * @author jerry
 * @version 2017-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/lotteryTimeNum")
public class LotteryTimeNumController extends BaseController {

	@Autowired
	private LotteryTimeNumService lotteryTimeNumService;
	
	@ModelAttribute
	public LotteryTimeNum get(@RequestParam(required=false) String id) {
		LotteryTimeNum entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lotteryTimeNumService.get(id);
		}
		if (entity == null){
			entity = new LotteryTimeNum();
		}
		return entity;
	}
	
	@RequiresPermissions("lottery:lotteryTimeNum:view")
	@RequestMapping(value = {"list", ""})
	public String list(LotteryTimeNum lotteryTimeNum, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LotteryTimeNum> page = lotteryTimeNumService.findPage(new Page<LotteryTimeNum>(request, response), lotteryTimeNum); 
		model.addAttribute("page", page);
		return "modules/lottery/lotteryTimeNumList";
	}
	
	@RequestMapping(value ="batchView")
	public String batchView(LotteryTimeNum lotteryTimeNum, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LotteryTimeNum> page = null;
		if(StringUtils.isNoneBlank(lotteryTimeNum.getLotteryCode())) {
			page = lotteryTimeNumService.findPage(new Page<LotteryTimeNum>(request, response), lotteryTimeNum); 
		}
		model.addAttribute("page", page);
		return "modules/lottery/lotteryBatchTimeNumList";
	}


	
	@RequiresPermissions("lottery:lotteryTimeNum:view")
	@RequestMapping(value = "form")
	public String form(LotteryTimeNum lotteryTimeNum, Model model) {
		model.addAttribute("lotteryTimeNum", lotteryTimeNum);
		return "modules/lottery/lotteryTimeNumForm";
	}

	@RequiresPermissions("lottery:lotteryTimeNum:edit")
	@RequestMapping(value = "save")
	public String save(TimeTask timeTaskDTO, Model model, RedirectAttributes redirectAttributes) throws SchedulerException {
		lotteryTimeNumService.generatePlanTime(timeTaskDTO);
		addMessage(redirectAttributes,"生成计划时刻成功！");
		return "redirect:"+Global.getAdminPath()+"/lottery/lotteryTimeNum/?repage";
	}
	
	@RequiresPermissions("lottery:lotteryTimeNum:edit")
	@RequestMapping(value = "delete")
	public String delete(LotteryTimeNum lotteryTimeNum, RedirectAttributes redirectAttributes) {
		lotteryTimeNumService.delete(lotteryTimeNum);
		addMessage(redirectAttributes, "删除保存开奖时刻成功成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lotteryTimeNum/?repage";
	}

	
	@RequestMapping(value = "findCurrentIssueNo")
	@ResponseBody
	public LotteryTimeNum findCurrentIssueNo(String lotteryCode) {
		
		return lotteryTimeNumService.findCurrentIssueNo(lotteryCode);
	}
	
	@RequestMapping(value = "findLotteryTimeNum")
	@ResponseBody
	public List<LotteryTimeNum> findLotteryTimeNum(String lotteryCode,Integer rows) {
		return lotteryTimeNumService.findLotteryTimeNum(lotteryCode,rows);
	}

	@RequestMapping(value = "batchUpdateHalt", method = RequestMethod.POST)
	@ResponseBody
	public String batchUpdateHalt(@RequestParam(value = "ids[]",required=false) String[] ids, @RequestParam(value = "betHaltDate") Integer betHaltDate) {
		return lotteryTimeNumService.batchUpdateHalt(ids,betHaltDate);
	}
}