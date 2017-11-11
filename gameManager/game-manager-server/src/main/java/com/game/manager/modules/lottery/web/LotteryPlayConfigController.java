/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.web;

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
import com.game.manager.modules.lottery.entity.LotteryPlayConfig;
import com.game.manager.modules.lottery.entity.LotteryType;
import com.game.manager.modules.lottery.service.LotteryPlayConfigService;
import com.game.manager.modules.lottery.service.LotteryTypeService;

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
     * 彩种基本信息管理service
     */
    @Autowired
    private LotteryTypeService lotteryTypeService;

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

    @RequiresPermissions("lottery:lotteryPlayConfig:edit")
    @RequestMapping(value = "save")
    public String save(LotteryPlayConfig lotteryPlayConfig, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, lotteryPlayConfig)) {
            return form(lotteryPlayConfig, model);
        }
        lotteryPlayConfigService.save(lotteryPlayConfig);
        addMessage(redirectAttributes, "保存玩法基本信息成功");
        return "redirect:" + Global.getAdminPath() + "/lottery/lotteryPlayConfig/?repage";
    }

    @RequiresPermissions("lottery:lotteryPlayConfig:edit")
    @RequestMapping(value = "delete")
    public String delete(LotteryPlayConfig lotteryPlayConfig, RedirectAttributes redirectAttributes) {
        lotteryPlayConfigService.delete(lotteryPlayConfig);
        addMessage(redirectAttributes, "删除玩法基本信息成功");
        return "redirect:" + Global.getAdminPath() + "/lottery/lotteryPlayConfig/?repage";
    }

}