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
import com.game.modules.lottery.entity.LotteryType;
import com.game.modules.lottery.service.LotteryTypeService;

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

    /**
     * 根据记录ID获取单条数据
     * @param id 记录ID,数据表主键
     * @return 如果传入的记录ID不为空且能从库中查询到相应的彩种信息数据，返回苦衷彩种信息；
     *         如果传入的记录ID为空或者库中不存在对应彩种信息数据，返回一个初始化的彩种信息对象；
     * @author Terry
     */
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

    /**
     * 根据彩种代码获取单条数据
     * @param code 彩种代码
     * @return 返回查询的实体对象，如果没有数据返回一个初始化的实体对象
     * @author Terry
     */
    @ModelAttribute
    public LotteryType getByCode(@RequestParam(required = false) String code) {
        LotteryType entity = null;
        if (StringUtils.isNotBlank(code)) {
            entity = lotteryTypeService.getByCode(code);
        }
        if (entity == null) {
            entity = new LotteryType();
        }
        return entity;
    }

    /**
     * 分页查询数据
     * @param lotteryType 提供查询的参数对象
     * @param request 请求消息体
     * @param response 响应消息体
     * @param model 参数传递模型
     * @return 视图模型路径
     * @author Terry
     */
    @RequiresPermissions("lottery:lotteryType:view")
    @RequestMapping(value = { "list", "" })
    public String list(LotteryType lotteryType, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<LotteryType> page = lotteryTypeService.findPage(new Page<LotteryType>(request, response), lotteryType);
        model.addAttribute("page", page);
        return "modules/lottery/lotteryTypeList";
    }

    /**
     * 进入编辑页面（新增或编辑）带入已知数据
     * @param lotteryType 已知彩种数据对象数据对象
     * @param model 参数传递模型
     * @return 视图模型路径
     * @author Terry
     */
    @RequiresPermissions("lottery:lotteryType:view")
    @RequestMapping(value = "form")
    public String form(LotteryType lotteryType, Model model) {
        model.addAttribute("lotteryType", lotteryType);
        return "modules/lottery/lotteryTypeForm";
    }

    /**
     * 校验提交的彩种数据（新增和编辑），通过校验将数据更新到数据库，不通过则返回编辑页面
     * @param lotteryType 彩种数据对象
     * @param model 参数传递模型
     * @param redirectAttributes 重定向属性对象，在此方法中用于传递提示信息
     * @return ModelAndView
     * @author Terry
     */
    @RequiresPermissions("lottery:lotteryType:edit")
    @RequestMapping(value = "save")
    public String save(LotteryType lotteryType, Model model, RedirectAttributes redirectAttributes) {
        // 对提交的数据进行校验，校验不通过则直接返回编辑页面
        if (!beanValidator(model, lotteryType)) {
            return form(lotteryType, model);
        }
        // 校验通过更新数据到数据库
        lotteryType.setCurrentUser(UserUtils.getUser());
        lotteryTypeService.save(lotteryType);
        addMessage(redirectAttributes, LotteryConstants.SAVE_SUCCESS);
        // 重定向到查询列表页面
        return "redirect:" + Global.getAdminPath() + "/lottery/lotteryType/?repage";
    }

    /**
     * 通过彩种数据记录ID删除彩种信息（将对应数据的删除标识修改为1）
     * @param lotteryType 要删除的彩种信息对象
     * @param redirectAttributes 重定向属性对象，在此方法中用于传递提示信息
     * @return ModelAndView
     * @author Terry
     */
    @RequiresPermissions("lottery:lotteryType:edit")
    @RequestMapping(value = "delete")
    public String delete(LotteryType lotteryType, RedirectAttributes redirectAttributes) {
    	lotteryType.setCurrentUser(UserUtils.getUser());
        lotteryTypeService.delete(lotteryType);
        addMessage(redirectAttributes, LotteryConstants.REMOVE_SUCCESS);
        return "redirect:" + Global.getAdminPath() + "/lottery/lotteryType/?repage";
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
        model.addAttribute("lotteryPlayCode", DictUtils.getDictList(request.getParameter("lotterytype")));
        PrintWriter p = response.getWriter();
        p.write(JsonMapper.toJsonString(model));
        p.close();
    }
}