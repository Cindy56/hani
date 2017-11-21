/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.mailsite.dao;

import com.game.hall.common.persistence.CrudDao;
import com.game.hall.common.persistence.Page;
import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.mailsite.entity.MailSite;

/**
 * 站内信DAO接口
 * @author antonio
 * @version 2017-11-21
 */
@MyBatisDao
public interface MailSiteDao extends CrudDao<MailSite> {

	/**
	 * 删站内信by Id
	 * @param id
	 */
	void deleteById(String id);

	/**
	 * 批量获取站内信
	 * @param userId
	 * @param pageIndex
	 * @return
	 */
	Page<MailSite> findPageByUserId(String userId, int pageIndex);

	/**
	 * 查信含正文，by Id
	 * @param id
	 * @return
	 */
	MailSite selectById(String id);
	
}