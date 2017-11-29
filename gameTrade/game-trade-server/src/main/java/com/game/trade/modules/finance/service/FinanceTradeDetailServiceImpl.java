/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.finance.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.finance.entity.FinanceTradeDetail;
import com.game.modules.finance.service.FinanceTradeDetailService;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.order.entity.LotteryOrder;
import com.game.trade.modules.finance.dao.FinanceTradeDetailDao;
import com.google.common.collect.Lists;

/**
 * 账变流水Service
 * @author jerry
 * @version 2017-11-09
 */
@Service("financeTradeDetailService")
@Transactional(readOnly = true)
public class FinanceTradeDetailServiceImpl 
	extends CrudService<FinanceTradeDetailDao, FinanceTradeDetail>  implements FinanceTradeDetailService	 {
	
	@Autowired
	private FinanceTradeDetailDao financeTradeDetailDao;
	
	public FinanceTradeDetail get(String id) {
		return super.get(id);
	}
	
	public List<FinanceTradeDetail> findList(FinanceTradeDetail financeTradeDetail) {
		return super.findList(financeTradeDetail);
	}
	
	public Page<FinanceTradeDetail> findPage(Page<FinanceTradeDetail> page, FinanceTradeDetail financeTradeDetail) {
		return super.findPage(page, financeTradeDetail);
	}
	
	@Transactional(readOnly = false)
	public FinanceTradeDetail save(FinanceTradeDetail financeTradeDetail) {
		return super.save(financeTradeDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(FinanceTradeDetail financeTradeDetail) {
		super.delete(financeTradeDetail);
	}
	
	/**
	 * 批量插入账变流水
	 * @param list
	 */
	@Transactional(readOnly = false)
	public void batchTrade(List<FinanceTradeDetail> list) {
		List<FinanceTradeDetail> delList = Lists.newArrayList();
		//计算出批量次数
		BigDecimal count =new BigDecimal(list.size()).divide(new BigDecimal(1000),0,BigDecimal.ROUND_CEILING);	
		Iterator<FinanceTradeDetail> iter = list.iterator();
		for (int j = 0; j < count.intValue(); j++) {
			delList.clear();
			for (int i = 0; i < 1000; i++) {
				if(iter.hasNext()) {
					FinanceTradeDetail financeTradeDetail = iter.next();
					financeTradeDetail.preInsert();
					delList.add(financeTradeDetail);
				}
			}
			if(delList.size() > 0) {
				financeTradeDetailDao.batchTrade(delList);
			}
		}
	}
	/**
	 *  批量插入账变流水
	 */
	@Override
	public void batchGenFinanceTradeDetail(List<LotteryOrder> lotteryOrderList, FinanceTradeDetailType type) {
		if(CollectionUtils.isEmpty(lotteryOrderList) || null == type) {
			return;
		}
		List<FinanceTradeDetail> delList = Lists.newArrayList();
		lotteryOrderList.stream().forEach(c->{
			switch (type) {
				case BET_DEDUCTIONS:
					delList.add(saveTradeDetail(c,c.getBetAmount(), type.getCode()));
					break;
				case BONUS_TO_SEND:
					delList.add(saveTradeDetail(c,c.getWinAmount(), type.getCode()));
					break;
				default:
					break;
			}
		});
		if(delList.size() > 0) {
			batchTrade(delList);
		}
	}
	private FinanceTradeDetail saveTradeDetail (LotteryOrder lotteryOrder,BigDecimal amount,String tradeType) {
		FinanceTradeDetail  trade = new FinanceTradeDetail();
		trade.setUser(lotteryOrder.getUser());
		trade.setUserName(lotteryOrder.getUser().getName());
		trade.setAccountId(lotteryOrder.getAccountId());
		trade.setOrgId(lotteryOrder.getOrgId());
		trade.setBusiNo(lotteryOrder.getOrderNo());
		trade.setTradeType(tradeType);
		trade.setAmount(amount);
		trade.getUser().setId("sys");
		return trade;
	}
}