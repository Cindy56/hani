/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.mailsite.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.ResultData;
import com.game.hall.common.config.Global;
import com.game.hall.common.persistence.Page;
import com.game.hall.common.utils.StringUtils;
import com.game.hall.common.web.BaseController;
import com.game.hall.modules.mailsite.entity.MailSite;
import com.game.hall.modules.mailsite.service.MailSiteService;
import com.game.hall.modules.sys.entity.User;

/**
 * 站内信Controller
 * 
 * @author antonio
 * @version 2017-11-21
 */
@Controller
@RequestMapping(value = "/mailsite")
public class MailSiteController extends BaseController {

	@Autowired
	private MailSiteService mailSiteService;

	private MailSite getMailSite() {
		// TODO Auto-generated method stub

		MailSite mail = new MailSite();
		

		mail.setUserId("userID2");
		mail.preInsert();
		
		User user = new User();
		user.setId("userid");
		user.preInsert();
		mail.setCreateBy(user);
		mail.setUpdateBy(user);

		
		mail.setContext("context");
		mail.setSender("sender");
		mail.setReceiver("receiver");
		//mail.setId("id");
		mail.setLabel("label");
		mail.setTitle("title");

		mail.setDelFlag("0");
		mail.setReadFlag("0");
		
		mail.setIsNewRecord(true);
		return mail;
	}
	
	/**
	 * 发信
	 * 
	 * @param mailSite
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResultData sendMail(/*MailSite mailSite*/) {

		
		MailSite mailSite = getMailSite();
		// 前置校验
		if (false)
			return ResultData.ResultDataFail();

		mailSiteService.save(mailSite);

		return ResultData.ResultDataOK();
	}

	/**
	 * 查信 by userId
	 * 
	 * @param mailSite
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getmailbyuserid", method = RequestMethod.GET)
	public ResultData getMailPage(int pageSize, int pageNo) {

		// 前置校验
		if (false)
			return ResultData.ResultDataFail();

		String userId = "user_id";
		Page<MailSite> pMail = mailSiteService.findPageByUserId(userId, pageNo, pageSize);

		ResultData rd = ResultData.ResultDataOK();
		rd.setData(pMail);
		
		return rd;
	}
	
	/**
	 * 删信 by Id
	 * 
	 * @param mailSite
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delmailbyid", method = RequestMethod.POST)
	public ResultData delMail(String mailId) {

		// 前置校验
		if (false)
			return ResultData.ResultDataFail();
		
		mailSiteService.deleteById(mailId);

		return ResultData.ResultDataOK();
	}
	
	/**
	 * 查信正文 by Id
	 * 
	 * @param mailSite
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getmailcontentbyid", method = RequestMethod.GET)
	public ResultData getMailContent(String mailId) {

		// 前置校验
		if (false)
			return ResultData.ResultDataFail();
		
		MailSite mailsite = mailSiteService.selectById(mailId);

		ResultData rd = ResultData.ResultDataOK();
		rd.setData(mailsite);
		
		return rd;
	}

}