/**
 * 
 */
package com.game.hall.modules.bet.web;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.common.mapper.JsonMapper;
import com.game.hall.modules.bet.service.LotteryAddBetService;
import com.game.hall.modules.bet.service.OrderUtils;
import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.lottery.entity.GameError;
import com.game.modules.lottery.service.LotteryCalculateService;
import com.game.modules.lottery.service.LotteryPlayConfigService;
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.member.service.MemberPlayConfigService;
import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.User;
import com.game.modules.sys.service.SystemServiceFacade;

/**
 * 投注
 * 
 * @author antonio
 */
@Controller
@RequestMapping("/bet/bet")
public class LotteryBetController {

	private static final Logger LOG = LoggerFactory.getLogger(LotteryBetController.class);
	// @Autowired
	// private LotteryOpenTodayService lotteryTimeNumService;

	@Autowired
	private LotteryAddBetService lotteryAddBetService;
	@Autowired
	private LotteryCalculateService lotteryCalculateService;

	// ---------------------test

	@Autowired
	LotteryPlayConfigService mylotteryPlayConfigService;

	@Autowired
	MemberPlayConfigService myMemberPlayConfigService;

	@Autowired
	MemberAccountService memberAccountService;
	
	@Autowired
	SystemServiceFacade systemServiceFacade;

	@ResponseBody
	@RequestMapping(value = "/addbet", method = RequestMethod.POST)
	public ResultData addBet(String jsbetData) {

		// testLotteryBetController test = new testLotteryBetController();
		// test.setMylotteryPlayConfigService(mylotteryPlayConfigService);
		// test.setMyMemberPlayConfigService(myMemberPlayConfigService);
		// List<LotteryOrder> lstest = test.testLotteryBetControllerMethodaddBet();

		List<LotteryOrder> lsBetData = (List<LotteryOrder>) JsonMapper.getInstance().fromJson(jsbetData,
				JsonMapper.getInstance().createCollectionType(List.class, LotteryOrder.class));

		// List<LotteryOrder> betData = lstest;
		// betData.add(getOrder());
		System.out.println("1");

		User user = RandomMember.getMember(memberAccountService,systemServiceFacade);

		int ret = 0;

		// 前置校验
		ResultData rd = ResultData.ResultDataOK();

		for (int i = 0; i < lsBetData.size(); i++) {

			LotteryOrder lotOrder = lsBetData.get(i);
			lotOrder.setAccountId(memberAccountService.getByUserId(user.getId()).getId());
			lotOrder.setOrderNo(OrderUtils.getOrderNo());


			lotOrder.setUser(user);
			lotOrder.setCurrentUser(user);
			lotOrder.preInsert();

			ret = this.lotteryCalculateService.checkOrder(lotOrder);
			if (ret != 0) {
				rd.setErrorCode(ret);
				rd.setMessage(GameError.getInstance().findErrorString(ret));
				return rd;
			}
		}

		System.out.println("2");

		// BetData betData;

		if (ret != 0)
			return ResultData.ResultDataFail();

		// ---------------------

		// List<LotteryOrder> lsBetData = lsBetData;

		rd = ResultData.ResultDataOK();

		if (lsBetData == null) {
			LOG.debug("投注信息为空");
			return rd;
		}

		for (int i = 0; i < lsBetData.size(); i++) { // 前置校验 LotteryOrder betData =
			LotteryOrder betData = lsBetData.get(i);

			if (betData.getBetIssueNo().isEmpty()) {
				rd.setErrorCode(GameError.errCodeIssuseNo);
				rd.setMessage(GameError.errIssuseNo);
				return rd;
			}

			if (betData.getPlayModeMoneyType().isEmpty()) {
				rd.setErrorCode(GameError.errCodeBettingModel);
				rd.setMessage(GameError.errBettingModel);
				return rd;
			}

			if (betData.getBetAmount().compareTo(new BigDecimal(0)) <= 0) {
				rd.setErrorCode(GameError.errCodeBettingMoney);
				rd.setMessage(GameError.errBettingMoney);
				return rd;
			}

			// 订单编号应该在此处生成
			if (betData.getOrderNo() != null && betData.getOrderNo().isEmpty()) {
				rd.setErrorCode(GameError.errCodeOrderNo);
				rd.setMessage(GameError.errOrderNo);
				return rd;
			}

			if (betData.getLotteryCode().isEmpty()) {
				rd.setErrorCode(GameError.errCodeLotteryCode);
				rd.setMessage(GameError.errLotteryCode);
				return rd;
			}

			if (betData.getBetDetail() != null && betData.getBetDetail().isEmpty()) {
				rd.setErrorCode(GameError.errCodeBetDetial);
				rd.setMessage(GameError.errBetDetial);
				return rd;
			}

			/*
			 * LotteryOrder bet1 = new LotteryOrder(); User user = new User();
			 * bet1.setUser(user); String lotteryCode = "CQSSC";
			 * bet1.setLotteryCode(lotteryCode); BigDecimal betAmount = new BigDecimal(100);
			 * bet1.setBetAmount(betAmount); String betIssueNo = "20171117";
			 * bet1.setBetIssueNo(betIssueNo); String betType = "";
			 * bet1.setBetType(betType); User currentUser = null;
			 * bet1.setCurrentUser(currentUser); String id = ""; bet1.setId(id); String
			 * orderSource = "0"; bet1.setOrderSource(orderSource); String orderType = "1";
			 * bet1.setOrderType(orderType); String playModeCommissionRate = "0";
			 * bet1.setPlayModeCommissionRate(playModeCommissionRate); String playModeMoney
			 * = "1960"; bet1.setPlayModeMoney(playModeMoney); String playModeMoneyType =
			 * "0"; bet1.setPlayModeMoneyType(playModeMoneyType);
			 */

			int bRet = lotteryAddBetService.addBet(betData);

			if (bRet != 0) {
				return rd;
			}

		}

		return rd;

		// System.out.println();

	}

	@ResponseBody
	@RequestMapping(value = "/cancelorder", method = RequestMethod.POST)
	public ResultData cancelOrder(@RequestParam("orderIds") List<String> orderIds) {

		// List<LotteryOrder> betData = null;
		System.out.println("1");

		try {
			// 前置校验
			boolean ret = false;
			ResultData rd = ResultData.ResultDataOK();

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		System.out.println("2");

		// BetData betData;
		User user = UserUtils.getUser();
		user.preInsert();
		return lotteryAddBetService.cancelOrder(user, orderIds);
		// System.out.println();

	}

	// @ResponseBody
	// @RequestMapping(value = "/getopentoday", method = RequestMethod.GET)
	// public List<LotteryTimeNum> getOpenToday(String name) {
	//
	// ResultData rd = lotteryTimeNumService.OpenToday();
	// System.out.println();
	//
	// return null;
	// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/getcur", method = RequestMethod.GET)
	// public List<LotteryTimeNum> getCur() {
	//
	// Date dt = new Date();
	//
	// List<LotteryTimeNum> openToday = lotteryTimeNumService.Cur(dt);
	//
	// return openToday;
	// }

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
		String betIssueNo = "20171125083";
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

		// bet1.setOrderNum("10000001");
		bet1.setBetDetail("detail");
		bet1.setBetRate(1);
		bet1.setStatus("0");
		// Date createDate = new Date();
		// bet1.setCreateDate(createDate);
		// bet1.setCreateBy(user);
		// bet1.setUpdateBy(user);

		return bet1;
	}

}
