/**
 * 
 */
package com.game.hall.modules.bet.web;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.common.utils.IdGen;
import com.game.hall.modules.bet.service.LotteryAddBetService;
import com.game.hall.modules.bet.service.LotteryOrderManagerService;
import com.game.hall.modules.bet.service.OrderUtils;
import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.finance.service.FinanceTradeDetailService;
import com.game.modules.lottery.entity.GameError;
import com.game.modules.lottery.entity.ResponseMsgData;
import com.game.modules.lottery.service.LotteryCalculateService;
import com.game.modules.lottery.service.LotteryPlayConfigService;
import com.game.modules.lottery.service.LotteryTimeNumService;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.member.service.MemberPlayConfigService;
import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.order.service.LotteryOrderService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.User;
import com.game.modules.sys.service.SystemServiceFacade;
import com.test.testLotteryBetController;

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

	@Autowired
	private LotteryTimeNumService lotteryTimeNumService;
	@Autowired
	private LotteryOrderService lotteryOrderService;
	@Autowired
	private FinanceTradeDetailService financeTradeDetailService;
	@Autowired
	private LotteryOrderManagerService lotteryOrderManagerService;

	public String genOrderNo() {
		return String.valueOf(IdGen.randomLong());
	}

	public static void main(String[] args) {
		Random rand = new Random();
		// StringBuilder betNumber = new StringBuilder();
		// betNumber.append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10));

		SecureRandom random = new SecureRandom();

		for (int i = 0; i < 10000; i++) {
			int j = random.nextInt(2);

			if (j < 0)
				System.out.println(j);
		}

		System.out.println("end");
	}

	// @RequiresPermissions("finance:financeRecharge:view")
	@RequestMapping(value = { "testAddBet" })
	@ResponseBody
	public ResultData testAddBet(LotteryOrder lotteryOrder, Model model) throws Exception {
		long startTime = System.currentTimeMillis();
		// =================模拟生成order
		LotteryOrder testOrder = new LotteryOrder();
		User currentUser = UserUtils.getUser();
		testOrder.setCurrentUser(currentUser);
		testOrder.setOrderNo(genOrderNo());// 订单编号
		testOrder.setUser(currentUser);
		
		testOrder.setCompanyId(currentUser.getCompany().getId());
		testOrder.setOfficeId(currentUser.getOffice().getId());
		testOrder.setLotteryCode("SSC_CQ");
		testOrder.setBetIssueNo(lotteryTimeNumService.findCurrentIssueNo("SSC_CQ").getLotteryIssueNo());
		MemberAccount currentAccount = memberAccountService.getByUserId(currentUser.getId());
		testOrder.setAccountId(currentAccount.getId());

		 Random rand = new Random();

		 StringBuilder betNumber = new StringBuilder();
		 betNumber.append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10));

		int randNum = testLotteryBetController.getInstance().getRandmonNum();

		String playCode = testLotteryBetController.getInstance().getRandmonPlayCode(randNum);

//		String betDetail = testLotteryBetController.getInstance().getRandmonBetDetail(randNum);

		testOrder.setBetType("SSC_5XING_ZHIXUANDAN");

		testOrder.setBetDetail(betNumber.toString());

		testOrder.setBetAmount(new BigDecimal(4));
		testOrder.setBetRate(2);
		testOrder.setPlayModeMoney(190000);
		testOrder.setPlayModeCommissionRate(new BigDecimal(0.03));
		testOrder.setPlayModeMoneyType("0");
		testOrder.setOrderSource("1");
		testOrder.setOrderType("1");
		testOrder.setSchemaId("xxxxxxxxxx");
		testOrder.setWinAmount(new BigDecimal(0));
		testOrder.setWithdrawAmount(new BigDecimal(0));
		testOrder.setStatus("0");

		// =================调用check
		ResponseMsgData result = this.lotteryCalculateService.checkOrder(testOrder);
		
		 if(!result.getIsSucceed()) {
			 return ResultData.error(result.getMsg());
		 }

		// =================入库
//		this.lotteryOrderService.save(testOrder);
		// =================扣钱
	/*//	boolean minusAmountResult = this.memberAccountService.minusAmount(currentAccount.getId(), testOrder.getBetAmount());
		if (BooleanUtils.isFalse(minusAmountResult)) {
			// TODO:扣款失败，返回异常提示

		}
		// =================生成流水,挪到返水服务里
		this.financeTradeDetailService.batchGenFinanceTradeDetail(Collections.singletonList(testOrder), FinanceTradeDetailType.BET_DEDUCTIONS);
*/
		this.lotteryOrderManagerService.testInOrder(testOrder);
		System.out.println("====================bet order lost time:" + (System.currentTimeMillis() - startTime));
		return ResultData.ok();
	}

	/*@ResponseBody
	@RequestMapping(value = "/addbet", method = RequestMethod.GET)
	public ResultData addBet(String jsbetData) {

		// =================模拟生成order
		String betIssueNo = lotteryTimeNumService.findCurrentIssueNo("SSC_CQ").getLotteryIssueNo();

		LotteryOrder testOrder = testLotteryBetController.getInstance().getRandmonOrder(betIssueNo);

		// ================设置用户，生成订单号
		testOrder.preInsert();
		User currentUser = UserUtils.getUser();
		//testOrder.setId(id);
		testOrder.setCurrentUser(currentUser);
		testOrder.setOrderNo(genOrderNo());//
		testOrder.setUser(currentUser);
		testOrder.setCompanyId(currentUser.getCompany().getId());
		testOrder.setOfficeId(currentUser.getOffice().getId());

		MemberAccount currentAccount = memberAccountService.getByUserId(currentUser.getId());
		testOrder.setAccountId(currentAccount.getId());

		List<LotteryOrder> lsOrders = new ArrayList<>();
		lsOrders.add(testOrder);

		System.out.println("1");


		ResultData rd = ResultData.ResultDataOK();

		for (int i = 0; i < lsOrders.size(); i++) {
			LotteryOrder lotOrder = lsOrders.get(i);
			lotOrder.setAccountId(memberAccountService.getByUserId(currentUser.getId()).getId());
			lotOrder.setOrderNo(OrderUtils.getOrderNo());

			// =============================前置校验 List<LotteryOrder> lsOrders
			ResponseMsgData responseMsgData = this.lotteryCalculateService.checkOrder(lotOrder);
			if (!responseMsgData.getIsSucceed()) {
				rd.setMessage(responseMsgData.getMsg());
				return rd;
			}

			// =============================save and deduct and Statement detail
			// List<LotteryOrder> lsOrders
			int bRet = lotteryAddBetService.addBet(lotOrder);

			if (bRet != 0) {
				rd.setErrorCode(bRet);
				rd.setMessage(GameError.getInstance().findErrorString(bRet));
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

		return rd;

	}*/

	@ResponseBody
	@RequestMapping(value = "/cancelorder", method = RequestMethod.POST)
	public ResultData cancelOrder(@RequestParam("orderIds") List<String> orderIds) {

		// List<LotteryOrder> betData = null;
		System.out.println("1");

		try {
			// 前置校验
			boolean ret = false;
		//	ResultData rd = ResultData.ResultDataOK();

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
		bet1.setCompanyId("orgId");
		bet1.setOfficeId("orgId");

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
