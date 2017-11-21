/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.dao;

import com.game.manager.common.persistence.CrudDao;
import com.game.manager.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.member.entity.MemberPlayConfig;

/**
 * 会员玩法奖金配置DAO接口
 * @author freeman
 * @version 2017-11-20
 */
@MyBatisDao
public interface MemberPlayConfigDao extends CrudDao<MemberPlayConfig> {
	public MemberPlayConfig getMemberPlayConfigByUserId(String userId);
}