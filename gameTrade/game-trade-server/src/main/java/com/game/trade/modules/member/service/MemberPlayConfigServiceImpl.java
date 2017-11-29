/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.member.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.mapper.JsonMapper;
import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.common.utils.StringUtils;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.member.entity.MemberPlayConfig;
import com.game.modules.member.service.MemberPlayConfigService;
import com.game.trade.modules.member.dao.MemberPlayConfigDao;

/**
 * 会员玩法奖金配置Service
 * @author freeman
 * @version 2017-11-20
 */
@Service("memberPlayConfigService")
@Transactional(readOnly = true)
public class MemberPlayConfigServiceImpl 
	extends CrudService<MemberPlayConfigDao, MemberPlayConfig> implements MemberPlayConfigService {
	
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
	public MemberPlayConfig save(MemberPlayConfig memberPlayConfig) {
		return super.save(memberPlayConfig);
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

	@Override
	public Map<String, List<LotteryPlayConfig>> getPlayConfigByUserId(String userId) {
		//查询当前登录用户的玩法配置
		MemberPlayConfig memberPlayConfig=memberPlayConfigDao.getMemberPlayConfigByUserId(userId);
		if(null != memberPlayConfig) {
			return formatPlayConfig(memberPlayConfig.getPlayConfig());
		}
		return new HashMap<String, List<LotteryPlayConfig>>();
	}
	
	/**
	 * 把玩法json转换为map
	 * @author freemam
	 * 2017年11月29日 下午6:45:34
	 * @param playConfig
	 * @return
	 */
	@Override
	public Map<String, List<LotteryPlayConfig>> formatPlayConfig(String playConfig){
		//找出所有彩种
		Map<String, List<LotteryPlayConfig>> repeatMap=new HashMap<>();
		
		if(StringUtils.isNotBlank(playConfig)) {
			
			//String playConfig=memberPlayConfig.getPlayConfig();
			//包含当前登录用户的玩法配置
			JsonMapper jsonMapper = JsonMapper.getInstance();
			List<LotteryPlayConfig> playConfigList  =  jsonMapper.fromJson(playConfig, jsonMapper.createCollectionType(List.class, LotteryPlayConfig.class));
			for (LotteryPlayConfig lottery : playConfigList) {
				if(repeatMap.containsKey(lottery.getLotteryCode().getName())) {
					List<LotteryPlayConfig> repList = repeatMap.get(lottery.getLotteryCode().getName());
					repList.add(lottery);
				}else {
					List<LotteryPlayConfig> list1 = new ArrayList<>();
					list1.add(lottery);
					repeatMap.put(lottery.getLotteryCode().getName(), list1);
				}
			}
			Set<Entry<String, List<LotteryPlayConfig>>> setEntry = repeatMap.entrySet();
			for (Entry<String, List<LotteryPlayConfig>> entry : setEntry) {
				List<LotteryPlayConfig> repeatList=entry.getValue();
				
				for (LotteryPlayConfig lottery : repeatList) {
					//循环每种玩法
					//中奖几率
					BigDecimal winningProbability=new BigDecimal(lottery.getWinningProbability());
					//最大返水
					BigDecimal commissionRateMax=lottery.getCommissionRateMax();
					//最小返水
					BigDecimal commissionRateMin=lottery.getCommissionRateMin();
					//每种玩法的list
					List<Map<String, Object>> groupList=new ArrayList<Map<String, Object>>();
					
					while (commissionRateMax.compareTo(commissionRateMin)>=0) {
						//循环算出玩法的奖金与返点
						BigDecimal awardMoney=new BigDecimal(2).divide(winningProbability,3).multiply(new BigDecimal(1).subtract(commissionRateMin));
						DecimalFormat dfFormat = new DecimalFormat("0.000"); 
						String Money=dfFormat.format(awardMoney);
						
						Map<String, Object> awardMap=new HashMap<String, Object>();
						//奖金
						//awardMap.put("awardMoney", awardMoney);
						awardMap.put("awardMoney", Money);
						//返点百分比
						awardMap.put("commissionRate", commissionRateMin);
						//.multiply(new BigDecimal(100))
						//把每种返点添加到groupList中
						groupList.add(awardMap);
						commissionRateMin=commissionRateMin.add(new BigDecimal("0.001"));
					}
					if(null!=lottery.getMap()) {
						lottery.getMap().put("awardList", groupList);
					}else {
						lottery.setMap(new HashMap<String,List<Map<String, Object>>>());
						lottery.getMap().put("awardList", groupList);
					}
					
				}
			}
		}
		return repeatMap;
		
	}
	
}