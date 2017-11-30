/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.member.service;

import java.util.List;
import java.util.Map;

import com.game.common.persistence.Page;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.member.entity.MemberPlayConfig;

/**
 * 会员玩法奖金配置Service
 * @author freeman
 * @version 2017-11-20
 */
public interface MemberPlayConfigService {
	public MemberPlayConfig get(String id);
	
	public List<MemberPlayConfig> findList(MemberPlayConfig memberPlayConfig);
	
	public Page<MemberPlayConfig> findPage(Page<MemberPlayConfig> page, MemberPlayConfig memberPlayConfig);
	
	public MemberPlayConfig save(MemberPlayConfig memberPlayConfig);
	
	public void delete(MemberPlayConfig memberPlayConfig);
	
	/**
	 * 根据userid查询用户玩法配置信息
	 */
	public MemberPlayConfig getMemberPlayConfigByUserId(String userId);
	
	/**
	 *  根据会员ID 查询 所有 会员玩法配置
	 */
	public List<MemberPlayConfig> queryByAccountIdList(List<String> ids);
	
	/**
	 * 根据userId查询格式化后的用户玩法配置
	 * @author freemam
	 * 2017年11月29日 下午6:43:58
	 * @param userId
	 * @return
	 */
	public Map<String, List<LotteryPlayConfig>> getPlayConfigByUserId(String userId);
	
	/**
	 * 格式化玩法配置信息
	 * @author freemam
	 * 2017年11月29日 下午6:48:19
	 * @param playConfig
	 * @return
	 */
	public Map<String, List<LotteryPlayConfig>> formatPlayConfig(String playConfig);
	
}