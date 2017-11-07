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
import com.game.manager.modules.lottery.entity.LotteryType;
import com.game.manager.modules.lottery.service.LotteryTypeService;

/**
 * 彩种基本信息管理Controller
 * @author Terry
 * @version 2017-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/lotteryType")
public class LotteryTypeController extends BaseController {

    /**
     * 彩种基本信息管理service
     */
    @Autowired
    private LotteryTypeService lotteryTypeService;

    @ModelAttribute
    public LotteryType get(@RequestParam(required = false) String id) {
        LotteryType entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = lotteryTypeService.get(id);
        }
        if (entity == null) {
            entity = new LotteryType();
        }
        return entity;
    }

    @RequiresPermissions("lottery:lotteryType:view")
    @RequestMapping(value = { "list", "" })
    public String list(LotteryType lotteryType, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<LotteryType> page = lotteryTypeService.findPage(new Page<LotteryType>(request, response), lotteryType);
        model.addAttribute("page", page);
        return "modules/lottery/lotteryTypeList";
    }

    @RequiresPermissions("lottery:lotteryType:view")
    @RequestMapping(value = "form")
    public String form(LotteryType lotteryType, Model model) {
        model.addAttribute("lotteryType", lotteryType);
        return "modules/lottery/lotteryTypeForm";
    }

    @RequiresPermissions("lottery:lotteryType:edit")
    @RequestMapping(value = "save")
    public String save(LotteryType lotteryType, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, lotteryType)) {
            return form(lotteryType, model);
        }
        lotteryTypeService.save(lotteryType);
        addMessage(redirectAttributes, "保存彩种基本信息成功");
        return "redirect:" + Global.getAdminPath() + "/lottery/lotteryType/?repage";
    }

    @RequiresPermissions("lottery:lotteryType:edit")
    @RequestMapping(value = "delete")
    public String delete(LotteryType lotteryType, RedirectAttributes redirectAttributes) {
        lotteryTypeService.delete(lotteryType);
        addMessage(redirectAttributes, "删除彩种基本信息成功");
        return "redirect:" + Global.getAdminPath() + "/lottery/lotteryType/?repage";
    }
}