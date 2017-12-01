/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.mailsite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.common.persistence.Page;
import com.game.common.web.BaseController;
import com.game.hall.modules.mailsite.entity.MailSite;
import com.game.hall.modules.mailsite.service.MailSiteService;
import com.game.modules.sys.entity.User;

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
		MailSite mail = new MailSite();
		mail.setUserId("userID2");
		mail.preInsert();
		mail.setContext("context");
		mail.setSender("sender");
		mail.setReceiver("receiver");
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
	public ResultData sendMail(MailSite mailSite) {
		
		// TODO 前置校验
		mailSiteService.save(mailSite);

		return ResultData.ok();
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

		// TODO 前置校验
		String userId = "user_id";
		Page<MailSite> pMail = mailSiteService.findPageByUserId(userId, pageNo, pageSize);

		ResultData rd = ResultData.ok();
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

		// TODO 前置校验
		mailSiteService.deleteById(mailId);

		return ResultData.ok();
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

		// TODO 前置校验
	
		MailSite mailsite = mailSiteService.selectById(mailId);
		
		return ResultData.ok(mailsite);
	}

}