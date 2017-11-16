/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.order.web;

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
import com.game.manager.modules.order.entity.LotteryOrder;
import com.game.manager.modules.order.service.LotteryOrderService;

/**
 * 订单明细Controller
 * @author antonio
 * @version 2017-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/order/lotteryOrder")
public class LotteryOrderController extends BaseController {

	@Autowired
	private LotteryOrderService lotteryOrderService;
	
	@ModelAttribute
	public LotteryOrder get(@RequestParam(required=false) String id) {
		LotteryOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lotteryOrderService.get(id);
		}
		if (entity == null){
			entity = new LotteryOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("order:lotteryOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(LotteryOrder lotteryOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LotteryOrder> page = lotteryOrderService.findPage(new Page<LotteryOrder>(request, response), lotteryOrder); 
		model.addAttribute("page", page);
		return "modules/order/lotteryOrderList";
	}

	@RequiresPermissions("order:lotteryOrder:view")
	@RequestMapping(value = "form")
	public String form(LotteryOrder lotteryOrder, Model model) {
		model.addAttribute("lotteryOrder", lotteryOrder);
		return "modules/order/lotteryOrderForm";
	}

	@RequiresPermissions("order:lotteryOrder:edit")
	@RequestMapping(value = "save")
	public String save(LotteryOrder lotteryOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lotteryOrder)){
			return form(lotteryOrder, model);
		}
		lotteryOrderService.save(lotteryOrder);
		addMessage(redirectAttributes, "保存订单明细成功");
		return "redirect:"+Global.getAdminPath()+"/order/lotteryOrder/?repage";
	}
	
	@RequiresPermissions("order:lotteryOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(LotteryOrder lotteryOrder, RedirectAttributes redirectAttributes) {
		lotteryOrderService.delete(lotteryOrder);
		addMessage(redirectAttributes, "删除订单明细成功");
		return "redirect:"+Global.getAdminPath()+"/order/lotteryOrder/?repage";
	}

}