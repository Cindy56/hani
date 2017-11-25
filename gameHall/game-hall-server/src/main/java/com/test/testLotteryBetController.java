package com.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.service.LotteryPlayConfigService;
import com.game.modules.member.entity.MemberPlayConfig;
import com.game.modules.member.service.MemberPlayConfigService;
import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.User;


@Service
public class testLotteryBetController  {


	@Autowired
	LotteryPlayConfigService mylotteryPlayConfigService;
	
	@Autowired
	MemberPlayConfigService myMemberPlayConfigService;
	
	/**
	 * @return the myMemberPlayConfigService
	 */
	public MemberPlayConfigService getMyMemberPlayConfigService() {
		return myMemberPlayConfigService;
	}

	/**
	 * @param myMemberPlayConfigService the myMemberPlayConfigService to set
	 */
	public void setMyMemberPlayConfigService(MemberPlayConfigService myMemberPlayConfigService) {
		this.myMemberPlayConfigService = myMemberPlayConfigService;
	}

	/**
	 * @return the mylotteryPlayConfigService
	 */
	public LotteryPlayConfigService getMylotteryPlayConfigService() {
		return mylotteryPlayConfigService;
	}

	/**
	 * @param mylotteryPlayConfigService the mylotteryPlayConfigService to set
	 */
	public void setMylotteryPlayConfigService(LotteryPlayConfigService mylotteryPlayConfigService) {
		this.mylotteryPlayConfigService = mylotteryPlayConfigService;
	}

	public static LotteryOrder getOrder() {

		/*
		 * bet_issue_no varchar(50) NOT NULL投注期号
		 * 
		 * bet_type varchar(50) NOT NULL投注类型： 比如重庆时时彩后三直选，不同的类型对应到不同的算奖模式
		 * 
		 * bet_detail varchar(4500) NOT NULL投注内容
		 * 
		 * bet_amount decimal(14,4) NOT NULL投注金额，单位为元
		 * 
		 * bet_rate smallint(6) NOT NULL投注倍数
		 * 
		 * play_mode_money smallint(6) NOT NULL奖金模式：1800,1700,1960， 需要在服务端做校验
		 * play_mode_commission_rate
		 * 
		 * smallint(6) NOT NULL佣金比例，返点比例，返水比例 需要在应用服务器做校验，看看奖金模式和返佣比率是否符合公式
		 * 
		 * play_mode_money_type char(1) NOT NULL玩法模式： 0元 1角2分
		 * 
		 * order_source char(1) NOT NULL注单来源： 1浏览器 2移动app
		 * 
		 * order_type char(1) NOT NULL注单类型： 1正常投注 2追号注单 2合买注单
		 * 
		 * schema_idvarchar(50) NULL方案外键： 如果是追号、合买，查看注单详情的时候链接到对应的方案
		 * 
		 * win_amountdecimal(14,4) NULL中奖金额，单位为元
		 * 
		 * withdraw_amount decimal(14,4) NULL撤单金额
		 * 
		 * statuschar(1) NOT NULL注单状态： 0等待开奖 1已中奖 2未中奖 3已撤单
		 */


		User user = new User();
		user.setId("00user");// 用户ID
		user.setName("00username");// 用户名
		Office company = new Office();
		company.setCode("code");// 组织编号
		user.setCompany(company);

		
		LotteryOrder bet1 = new LotteryOrder();
		bet1.setCurrentUser(user);
		// AddBetFormInput bet1 = new AddBetFormInput();
		bet1.preInsert();
		// bet1.setUserId("userId");
		// bet1.setUserName("userName");
		bet1.setOrgId("orgId");

		bet1.setAccountId("034f37416db44fa4a8ab05d98da6fa7d");
		String lotteryCode = "SSC_CQ";
		bet1.setLotteryCode(lotteryCode);
		BigDecimal betAmount = new BigDecimal(100);
		bet1.setBetAmount(betAmount);
		String betIssueNo = "20171117";
		bet1.setBetIssueNo(betIssueNo);
		String betType = "SSC_5_ZHIXUANFUSHI";
		bet1.setBetType(betType);
		// User currentUser = null;
		// bet1.setCurrentUser(currentUser);

		String orderSource = "0";
		bet1.setOrderSource(orderSource);
		String orderType = "2";
		bet1.setOrderType(orderType);
		BigDecimal playModeCommissionRate = new BigDecimal(0);
		bet1.setPlayModeCommissionRate(playModeCommissionRate);
		Integer playModeMoney = 1960;
		bet1.setPlayModeMoney(playModeMoney);
		String playModeMoneyType = "0";
		bet1.setPlayModeMoneyType(playModeMoneyType);

		//bet1.setOrderNum("10000001");
		bet1.setBetDetail("detail");
		bet1.setBetRate(1);
		bet1.setStatus("0");
		// Date createDate = new Date();
		// bet1.setCreateDate(createDate);
		// bet1.setCreateBy(user);
		// bet1.setUpdateBy(user);

		return bet1;
	}

	public List<LotteryOrder> testLotteryBetControllerMethodaddBet() {

		
		//获得user
		User user = new User();
		user.setId("00user");// 用户ID
		user.setName("00username");// 用户名
		Office company = new Office();
		company.setCode("code");// 组织编号
		user.setCompany(company);
		
		LotteryPlayConfig lotteryPlayConfig = new LotteryPlayConfig();
		lotteryPlayConfig.setCurrentUser(user);
		
		
		//user获得玩法配置
		MemberPlayConfig memberPlayConfig = myMemberPlayConfigService.getMemberPlayConfigByUserId("1");
		
		
		
		//选择玩法，投注号，玩法模式，倍数，奖金组，返点
		LotteryOrder bet1 = getOrder();
		String betType = "SSC_5_ZHIXUANDANSHI";
		bet1.setBetType(betType );
		
		String betDetail = "0123,123,123,123,123";
		bet1.setBetDetail(betDetail );
		
		String playModeMoneyType = "0";
		bet1.setPlayModeMoneyType(playModeMoneyType );
		
		bet1.setBetRate(1);
		
		bet1.setPlayModeMoney(1980);
		BigDecimal playModeCommissionRate = new BigDecimal(2.5);
		bet1.setPlayModeCommissionRate(playModeCommissionRate );
		//投注
		
	
		List<LotteryOrder> lsbet = new ArrayList<LotteryOrder>();


	
		lsbet.add(bet1);
		
		return lsbet;
		
	}

	

}
