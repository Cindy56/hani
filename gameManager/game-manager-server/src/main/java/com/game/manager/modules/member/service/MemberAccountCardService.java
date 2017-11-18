/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.manager.common.persistence.Page;
import com.game.manager.common.service.CrudService;
import com.game.manager.modules.member.dao.MemberAccountCardDao;
import com.game.manager.modules.member.entity.MemberAccountCard;

/**
 * 会员银行卡管理Service
 * @author freeman
 * @version 2017-11-14
 */
@Service
@Transactional(readOnly = true)
public class MemberAccountCardService extends CrudService<MemberAccountCardDao, MemberAccountCard> {

	public MemberAccountCard get(String id) {
		return super.get(id);
	}
	
	public List<MemberAccountCard> findList(MemberAccountCard memberAccountCard) {
		return super.findList(memberAccountCard);
	}
	
	public Page<MemberAccountCard> findPage(Page<MemberAccountCard> page, MemberAccountCard memberAccountCard) {
		return super.findPage(page, memberAccountCard);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberAccountCard memberAccountCard) {
		super.save(memberAccountCard);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberAccountCard memberAccountCard) {
		super.delete(memberAccountCard);
	}
	
}