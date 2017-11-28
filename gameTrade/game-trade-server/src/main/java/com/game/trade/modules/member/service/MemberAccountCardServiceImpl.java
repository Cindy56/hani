/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.member.entity.MemberAccountCard;
import com.game.modules.member.service.MemberAccountCardService;
import com.game.trade.modules.member.dao.MemberAccountCardDao;

/**
 * 会员银行卡管理Service
 * @author freeman
 * @version 2017-11-14
 */
@Service("memberAccountCardService")
@Transactional(readOnly = true)
public class MemberAccountCardServiceImpl 
	extends CrudService<MemberAccountCardDao, MemberAccountCard> implements MemberAccountCardService {

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
	public MemberAccountCard save(MemberAccountCard memberAccountCard) {
		return super.save(memberAccountCard);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberAccountCard memberAccountCard) {
		super.delete(memberAccountCard);
	}
	
}