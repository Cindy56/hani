/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.mailsite.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.ResultData;
import com.game.hall.common.persistence.Page;
import com.game.hall.common.service.CrudService;
import com.game.hall.modules.mailsite.dao.MailSiteDao;
import com.game.hall.modules.mailsite.entity.MailSite;
import com.game.hall.modules.sys.entity.User;

/**
 * 站内信Service
 * 
 * @author antonio
 * @version 2017-11-21
 */
@Service
@Transactional(readOnly = true)
public class MailSiteService extends CrudService<MailSiteDao, MailSite> {

	/**
	 * getmail by Id
	 */
	public MailSite get(String id) {
		return super.get(id);
	}

	public List<MailSite> findList(MailSite mailSite) {
		return super.findList(mailSite);
	}

	void fun() {
		MailSite o;
		Page<MailSite> po = new Page<MailSite>();
		po.setPageSize(10);

	}

	public Page<MailSite> findPage(Page<MailSite> page, MailSite mailSite) {
		return super.findPage(page, mailSite);
	}

	/**
	 * 批量获取信件头（无正文），by userId，分页
	 */
	public Page<MailSite> findPageByUserId(String userId, int pageNo, int pageSize) {

		// return this.dao.findPageByUserId(userId, pageIndex);
		Page<MailSite> page = new Page<MailSite>();
		MailSite mailSite = new MailSite();
		mailSite.setUserId(userId);

		page.setPageNo(pageNo);
		page.setPageSize(pageSize);

		return findPage(page, mailSite);
	}

	/**
	 * 发信
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public void save(MailSite mailSite) {

		this.dao.insert(mailSite);
	}

	@Transactional(readOnly = false)
	public void delete(MailSite mailSite) {
		super.delete(mailSite);
	}

	/**
	 * 删信byId
	 */
	@Transactional(readOnly = false)
	public void deleteById(String id) {
		this.dao.deleteById(id);
	}

	/**
	 * 查信包含正文 byId
	 */
	public MailSite selectById(String id) {
		return this.dao.selectById(id);
	}

}