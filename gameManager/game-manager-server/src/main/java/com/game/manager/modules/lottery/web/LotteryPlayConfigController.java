/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.web;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.game.common.mapper.JsonMapper;
import com.game.common.persistence.Page;
import com.game.common.utils.StringUtils;
import com.game.common.web.BaseController;
import com.game.manager.modules.sys.utils.DictUtils;
import com.game.manager.modules.sys.utils.UserUtils;
import com.game.modules.lottery.constant.LotteryConstants;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.entity.LotteryType;
import com.game.modules.lottery.service.LotteryPlayConfigService;
import com.game.modules.lottery.service.LotteryTypeService;

/**
 * 彩票玩法管理Controller
 * @author Terry
 * @version 2017-11-10
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/lotteryPlayConfig")
public class LotteryPlayConfigController extends BaseController {

	/**
	 * 彩种玩法Service对象
	 */
	@Autowired
	private LotteryPlayConfigService lotteryPlayConfigService;

	/**
	 * 彩种基本信息管理service对象
	 */
	@Autowired
	private LotteryTypeService lotteryTypeService;

	/**
	 * 前置方法，查询时如果传入记录ID，直接通过记录ID查找数据
	 * @param id 记录ID
	 * @return 如果存在记录ID，返回数据库对应的数据记录，如果不存在，返回一个初始化的玩法实体对象
	 * @author Terry
	 */
	@ModelAttribute
	public LotteryPlayConfig get(@RequestParam(required = false) String id) {
		LotteryPlayConfig entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = lotteryPlayConfigService.get(id);
		}
		if (entity == null) {
			entity = new LotteryPlayConfig();
		}
		return entity;
	}

	/**
	 * 前置方法，查询时如果传入玩法代码，直接通过玩法代码查找数据
	 * @param code 玩法代码
	 * @return 如果存在玩法代码，返回数据库对应的数据记录，如果不存在，返回一个初始化的玩法实体对象
	 * @author Terry
	 */
	@ModelAttribute
	public LotteryPlayConfig getByCode(@RequestParam(required = false) String code) {
		LotteryPlayConfig entity = null;
		if (StringUtils.isNotBlank(code)) {
			entity = lotteryPlayConfigService.getByCode(code);
		}
		if (entity == null) {
			entity = new LotteryPlayConfig();
		}
		return entity;
	}

	/**
	 * 分页查询，进入玩法列表页
	 * @param lotteryPlayConfig 查询参数封装玩法实体对象
	 * @param request 请求消息体
	 * @param response 响应消息体
	 * @param model 参数传递model
	 * @return 视图解析路径
	 * @author Terry
	 */
	@RequiresPermissions("lottery:lotteryPlayConfig:view")
	@RequestMapping(value = { "list", "" })
	public String list(LotteryPlayConfig lotteryPlayConfig, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 查询彩种数据传递到页面(搜索下拉框用)
		LotteryType lotteryType = new LotteryType();
		model.addAttribute("lotteryTypeList", lotteryTypeService.findList(lotteryType));
		// 查询玩法列表
		Page<LotteryPlayConfig> page = lotteryPlayConfigService.findPage(new Page<LotteryPlayConfig>(request, response),
				lotteryPlayConfig);
		model.addAttribute("page", page);
		return "modules/lottery/lotteryPlayConfigList";
	}

	/**
	 * 进入玩法管理编辑页面（新增或者编辑），带入已知参数
	 * @param lotteryPlayConfig 一直的玩法实体属性对象
	 * @param model 参数传递model
	 * @return 视图解析路径
	 * @author Terry
	 */
	@RequiresPermissions("lottery:lotteryPlayConfig:view")
	@RequestMapping(value = "form")
	public String form(LotteryPlayConfig lotteryPlayConfig, Model model) {
		// 查询彩种数据传递到页面
		LotteryType lotteryType = new LotteryType();
		model.addAttribute("lotteryTypeList", lotteryTypeService.findList(lotteryType));
		// 已知玩法数据传递到页面
		model.addAttribute("lotteryPlayConfig", lotteryPlayConfig);
		return "modules/lottery/lotteryPlayConfigForm";
	}

	/**
	 * 更新数据到数据库（新增或编辑）
	 * @param lotteryPlayConfig 要更新的数据记录
	 * @param model 参数传递model
	 * @param redirectAttributes 重定向属性对象
	 * @return 视图解析路径
	 * @author Terry
	 */
	@RequiresPermissions("lottery:lotteryPlayConfig:edit")
	@RequestMapping(value = "save")
	public String save(LotteryPlayConfig lotteryPlayConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lotteryPlayConfig)) {
			return form(lotteryPlayConfig, model);
		}
		lotteryPlayConfig.setCurrentUser(UserUtils.getUser());
		lotteryPlayConfigService.save(lotteryPlayConfig);
		addMessage(redirectAttributes, LotteryConstants.SAVE_SUCCESS);
		return "redirect:" + Global.getAdminPath() + "/lottery/lotteryPlayConfig/?repage";
	}

	/**
	 * 删除指定记录（修改数据库删除标识为1）
	 * @param lotteryPlayConfig 要删除的记录属性实体对象
	 * @param redirectAttributes 重定向属性对象
	 * @return 视图解析路径
	 * @author Terry
	 */
	@RequiresPermissions("lottery:lotteryPlayConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(LotteryPlayConfig lotteryPlayConfig, RedirectAttributes redirectAttributes) {
		lotteryPlayConfig.setCurrentUser(UserUtils.getUser());
		lotteryPlayConfigService.delete(lotteryPlayConfig);
		addMessage(redirectAttributes, LotteryConstants.REMOVE_SUCCESS);
		return "redirect:" + Global.getAdminPath() + "/lottery/lotteryPlayConfig/?repage";
	}

	/**
	 * 页面通过ajax实现下拉框级联
	 * @param request 请求消息对象
	 * @param response 响应消息对象
	 * @param model 消息传递model
	 * @throws IOException 可能抛出IO异常
	 * @author Terry
	 */
	@RequiresPermissions("lottery:lotteryPlayConfig:view")
	@RequestMapping(value = "findPlayCode")
	public void findPlayCode(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String key = request.getParameter("lotterytype").split("_")[0];
		model.addAttribute("lotteryPlayCode", DictUtils.getDictLabelForStartWith("lottery_play_code", key));
		PrintWriter p = response.getWriter();
		p.write(JsonMapper.toJsonString(model));
		p.close();
	}
}