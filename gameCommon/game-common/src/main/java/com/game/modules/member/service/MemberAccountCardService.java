/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.member.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.member.entity.MemberAccountCard;

/**
 * 会员银行卡管理Service
 * @author freeman
 * @version 2017-11-14
 */
public interface MemberAccountCardService {

	public MemberAccountCard get(String id);
	
	public List<MemberAccountCard> findList(MemberAccountCard memberAccountCard);
	
	public Page<MemberAccountCard> findPage(Page<MemberAccountCard> page, MemberAccountCard memberAccountCard);
	
	public MemberAccountCard save(MemberAccountCard memberAccountCard);
	
	public void delete(MemberAccountCard memberAccountCard);
	
}