/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.member.entity.MemberPlayConfig;
import com.game.manager.modules.member.dao.MemberPlayConfigDao;

/**
 * 会员玩法奖金配置Service
 * @author freeman
 * @version 2017-11-20
 */
@Service
@Transactional(readOnly = true)
public class MemberPlayConfigService extends CrudService<MemberPlayConfigDao, MemberPlayConfig> {
	
	@Autowired
	private MemberPlayConfigDao memberPlayConfigDao;

	public MemberPlayConfig get(String id) {
		return super.get(id);
	}
	
	public List<MemberPlayConfig> findList(MemberPlayConfig memberPlayConfig) {
		return super.findList(memberPlayConfig);
	}
	
	public Page<MemberPlayConfig> findPage(Page<MemberPlayConfig> page, MemberPlayConfig memberPlayConfig) {
		return super.findPage(page, memberPlayConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberPlayConfig memberPlayConfig) {
		super.save(memberPlayConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberPlayConfig memberPlayConfig) {
		super.delete(memberPlayConfig);
	}
	
	/**
	 * 根据userid查询用户玩法配置信息
	 */
	public MemberPlayConfig getMemberPlayConfigByUserId(String userId) {
		return memberPlayConfigDao.getMemberPlayConfigByUserId(userId);
	}
	
	/**
	 *  根据会员ID 查询 所有 会员玩法配置
	 */
	public List<MemberPlayConfig> queryByAccountIdList(List<String> ids) {
		return memberPlayConfigDao.queryByAccountIdList(ids);
	}
	
}