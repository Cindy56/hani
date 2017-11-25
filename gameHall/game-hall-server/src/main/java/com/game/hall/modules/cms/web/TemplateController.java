package com.game.hall.modules.cms.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.game.common.web.BaseController;
import com.game.hall.modules.cms.service.FileTplService;
import com.game.hall.modules.cms.service.SiteService;
import com.game.hall.modules.cms.utils.CmsUtils;
import com.game.modules.cms.entity.Site;

/**
 * 站点Controller
 * @author SongLai
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/template")
public class TemplateController extends BaseController {

    @Autowired
   	private FileTplService fileTplService;
    @Autowired
   	private SiteService siteService;

    @RequiresPermissions("cms:template:edit")
   	@RequestMapping(value = "")
   	public String index() {
   		return "modules/cms/tplIndex";
   	}

    @RequiresPermissions("cms:template:edit")
   	@RequestMapping(value = "tree")
   	public String tree(Model model) {
        Site site = siteService.get(CmsUtils.getCurrentSiteId());
   		model.addAttribute("templateList", fileTplService.getListForEdit(site.getSolutionPath()));
   		return "modules/cms/tplTree";
   	}

    @RequiresPermissions("cms:template:edit")
   	@RequestMapping(value = "form")
   	public String form(String name, Model model) {
        model.addAttribute("template", fileTplService.getFileTpl(name));
   		return "modules/cms/tplForm";
   	}

    @RequiresPermissions("cms:template:edit")
   	@RequestMapping(value = "help")
   	public String help() {
   		return "modules/cms/tplHelp";
   	}
}
