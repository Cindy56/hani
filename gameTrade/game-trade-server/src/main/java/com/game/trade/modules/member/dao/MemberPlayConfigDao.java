/**
0 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.member.entity.MemberPlayConfig;

/**
 * 会员玩法奖金配置DAO接口
 * @author freeman
 * @version 2017-11-20
 */
@MyBatisDao
public interface MemberPlayConfigDao extends CrudDao<MemberPlayConfig> {
	public MemberPlayConfig getMemberPlayConfigByUserId(String userId);
	
	/**
	 * 根据会员ID 查询 所有 会员玩法配置
	 * @param accountIds
	 * @return
	 */
	public List<MemberPlayConfig> queryByAccountIdList(@Param("accountIds") List<String> accountIds);
	
}