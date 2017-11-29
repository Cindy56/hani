/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.lottery.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.config.Global;
import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.common.utils.DateUtils;
import com.game.common.utils.StringUtils;
import com.game.modules.finance.entity.FinanceTradeDetail;
import com.game.modules.lottery.dto.OpenCaiResult;
import com.game.modules.lottery.dto.TimeTask;
import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.lottery.entity.LotteryType;
import com.game.modules.lottery.entity.LotteryTypeTime;
import com.game.modules.lottery.exception.LotteryNumDrawException;
import com.game.modules.lottery.service.LotteryTimeNumService;
import com.game.modules.sys.entity.Dict;
import com.game.trade.modules.lottery.dao.LotteryTimeNumDao;
import com.game.trade.modules.lottery.manager.LotteryCodeConstants;
import com.game.trade.modules.lottery.manager.OpenCaiDrawService;
import com.game.trade.modules.lottery.manager.TimeTaskService;
import com.google.common.collect.Lists;

/**
 * 开奖时刻和开奖结果Service
 * @author jerry
 * @version 2017-11-10
 */
@Service("lotteryTimeNumService")
@Transactional(readOnly = true)
public class LotteryTimeNumServiceImpl 
	extends CrudService<LotteryTimeNumDao, LotteryTimeNum> implements LotteryTimeNumService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private LotteryTypeServiceImpl lotteryTypeService;
	@Autowired
    private TimeTaskService timeTaskService;
	
	@Autowired
    private LotteryTimeNumDao lotteryTimeNumDao;
	
	@Autowired
    private OpenCaiDrawService openCaiDrawService;
	
	
//	@Autowired
//    private LotteryTypeTimeService lotteryTypeTimeService;
//    @Autowired
//    private LotteryTypeService lotteryTypeService;
	
	
	public LotteryTimeNum get(String id) {
		LotteryTimeNum lotteryTimeNum = super.get(id);
		return lotteryTimeNum;
	}
	
	public List<LotteryTimeNum> findList(LotteryTimeNum lotteryTimeNum) {
		return super.findList(lotteryTimeNum);
	}
	
	public Page<LotteryTimeNum> findPage(Page<LotteryTimeNum> page, LotteryTimeNum lotteryTimeNum) {
		return super.findPage(page, lotteryTimeNum);
	}
	
	@Transactional(readOnly = false)
	public LotteryTimeNum save(LotteryTimeNum lotteryTimeNum) {
		return super.save(lotteryTimeNum);
	}
	
	@Transactional(readOnly = false)
	public void delete(LotteryTimeNum lotteryTimeNum) {
		super.delete(lotteryTimeNum);
	}
	
	public List<Dict> getLotteryDict(){
		
		return null;
	}
	
	@Transactional(readOnly = false)
	public void updateLotteryNum(String lotteryNum,String lotteryCode,String lotteryIssueNo,String status,String openDate) {
		lotteryTimeNumDao.updateLotteryNum(lotteryNum, lotteryCode, lotteryIssueNo,status,openDate);
	}
	
	
	
	
	@Transactional(readOnly = false)
    public  void  generatePlanTime (TimeTask timeTask) throws SchedulerException, LotteryNumDrawException {
		//TODO:返回一个数组
		LotteryType lotteryType = lotteryTypeService.getByCode(timeTask.getLotteryCode());
		if(null == lotteryType) {
			return;
		}
		List<LotteryTypeTime> lotteryTypeTimeList = lotteryType.getLotteryTypeTimeList();
    	if(CollectionUtils.isEmpty(lotteryTypeTimeList)) {
    		return;
    	}
    	//升序排列时间模板
		//生成时刻表和定时任务
    	List<LotteryTimeNum> lotteryTimeNumList = new ArrayList<LotteryTimeNum>();
    	List<TimeTask> timeTaskList = new ArrayList<TimeTask>();
		LocalDate startDate = LocalDateTime.ofInstant(timeTask.getRunStartTime().toInstant(), ZoneId.systemDefault()).toLocalDate();
		LocalDate endDate = LocalDateTime.ofInstant(timeTask.getRunEndTime().toInstant(), ZoneId.systemDefault()).toLocalDate();
		LocalDate tempDate = startDate;
		
		LocalDate endDatex = endDate.plusDays(1);
		int plusDays =0;
		LocalDateTime endDateXj = null;
		Integer no = 0;
		if(LotteryCodeConstants.SSC_XJ.equals(timeTask.getLotteryCode())){
			plusDays=1;
		}else if(LotteryCodeConstants.PK10_BJ.equals(timeTask.getLotteryCode())) {
			OpenCaiResult result = openCaiDrawService.drawLotteryNum(timeTask.getLotteryCode());
			if(null == result) {
				return;
			}	
			/*Date openDate = DateUtils.parseDate(result.getOpentime());
			//openDate.
			Date nowDate = new Date();*/
			no = Integer.parseInt(result.getExpect());
		}
		//循环天
		while (!tempDate.isAfter(endDatex)) {
			String lotteryIssueNo = null;
			String temp = null;
			if(LotteryCodeConstants.PK10_BJ.equals(timeTask.getLotteryCode())) {
				temp = DateUtils.formatDate(Date.from(tempDate.atTime(LocalTime.parse("02:00")).atZone(ZoneId.systemDefault()).toInstant()),"yyyy-MM-dd HH:mm:ss");
			}else {
				 lotteryIssueNo = DateUtils.formatDate(Date.from(tempDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),"yyyyMMdd");
				 temp = DateUtils.formatDate(Date.from(tempDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),"yyyy-MM-dd");
			}
			if(!tempDate.isEqual(endDatex)) {//过滤最后一期
				List<LotteryTimeNum> existList = lotteryTimeNumDao.queryByLikeLotteryBetDate(timeTask.getLotteryCode(), 
						DateUtils.formatDate(Date.from(tempDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),"yyyy-MM-dd"),
						temp
						);
				if(CollectionUtils.isNotEmpty(existList)) {//判断当天是否生成开奖计划，如生成 则覆盖
					//存在，先删除表数据 在删除定时任务
					lotteryTimeNumDao.batchDel(timeTask.getLotteryCode(), 
							DateUtils.formatDate(Date.from(tempDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),"yyyy-MM-dd"),
							temp);
					for (LotteryTimeNum lotteryTimeNum : existList) {
						timeTaskService.deleteJob(timeTask.getLotteryCode()+":"+lotteryTimeNum.getLotteryIssueNo());
					}
				}
			}
			
			//为每个菜种生成一天的时刻明细,
			int issueNO = 1;
			for (LotteryTypeTime lotteryTypeTime : lotteryTypeTimeList) {
				//开始时间  startTime
				LocalDateTime startTime = tempDate.atTime(LocalTime.parse(lotteryTypeTime.getStartTime()));//每日开售时间
				//截至时间 endTime
				endDateXj = tempDate.atTime(LocalTime.parse(lotteryTypeTime.getEndTime()));
				LocalDateTime endTime = tempDate.plusDays(plusDays).atTime(LocalTime.parse(lotteryTypeTime.getEndTime()));//每日开售时间
				//每期总时间 5分钟/10分钟
				int periodTotalTime = lotteryTypeTime.getPeriodTotalTime();
				//封单时间
				int periodHaltTime = lotteryTypeTime.getPeriodHaltTime();
				//计算次数(endTime - startTime) / periodTotalTime
				Long minutes = Duration.between(startTime, endTime).toMinutes();
		    	BigDecimal bg = new BigDecimal(minutes);
				int times =bg.divide(new BigDecimal(periodTotalTime),BigDecimal.ROUND_HALF_DOWN).intValue();
				//生成明细
				for (int i = 0; i < times; i++) {
					LocalDateTime betStartTime = startTime.plusMinutes(periodTotalTime * i);
					LocalDateTime betEndTime = betStartTime.plusMinutes(periodTotalTime);
					LocalDateTime betHaltTime = betEndTime.minusSeconds(periodHaltTime);
					
					// ==========================生成时刻明细
					LotteryTimeNum lotteryTimeNum =new LotteryTimeNum();
					lotteryTimeNum.preInsert();
					if(LotteryCodeConstants.PK10_BJ.equals(timeTask.getLotteryCode())) {
						lotteryTimeNum.setLotteryIssueNo(no+"");
						no++;
					}else {
						lotteryTimeNum.setLotteryIssueNo(lotteryIssueNo+String.format("%03d", issueNO));//期数
					}
					lotteryTimeNum.setLotteryCode(timeTask.getLotteryCode());
					lotteryTimeNum.setBetStartDate(Date.from(betStartTime.atZone(ZoneId.systemDefault()).toInstant()));//开始时间
					lotteryTimeNum.setBetEndDate(Date.from(betEndTime.atZone(ZoneId.systemDefault()).toInstant()));//截至时间
					lotteryTimeNum.setBetHaltDate(Date.from(betHaltTime.atZone(ZoneId.systemDefault()).toInstant()));//封单时刻时间
					lotteryTimeNum.setDelFlag("0");
					lotteryTimeNum.setStatus("0");
					lotteryTimeNum.setCreateBy(timeTask.getCreateBy());
					lotteryTimeNumList.add(lotteryTimeNum);
					//递增旗号
					issueNO++;
					
				}				
			}
			tempDate = tempDate.plusDays(1);
		}
		lotteryTimeNumList.sort((h1, h2) -> h1.getBetStartDate().compareTo(h2.getBetStartDate()));
		LotteryTimeNum current = null;//当前期号
		LotteryTimeNum next = null;//下一下期号
		Date convertEndDate = Date.from(endDatex.atStartOfDay(ZoneId.systemDefault()).toInstant());
		if(LotteryCodeConstants.SSC_XJ.equals(timeTask.getLotteryCode())){
			convertEndDate = DateUtils.LocalDateTimeToUdate(endDateXj);
		}
		//Date convertEndDate =endDatex.atTime(time);
		List<LotteryTimeNum> list = new ArrayList<LotteryTimeNum>();
		//记录下一期 期号 、 封单时间 
		for (Iterator<LotteryTimeNum> iterator = lotteryTimeNumList.iterator(); iterator.hasNext();) {
			LotteryTimeNum lotteryTimeNum =  iterator.next();
			if(next != null) {
				//第二次  next  就是 当前期  current 当前期就是下一期 的期号
				current = lotteryTimeNum; 
				lotteryTimeNum = next; 
				next = current; 
			}else {
				//第一次 进来 生成下一期 期号
				next =  iterator.next();  
			}
			if(next.getBetEndDate().getTime() <= convertEndDate.getTime()) {
				lotteryTimeNum.setNextIssueNo(next.getLotteryIssueNo());
				lotteryTimeNum.setNextHaltDate(next.getBetHaltDate());
				list.add(lotteryTimeNum);
			}
			if(next.getBetEndDate().getTime() > convertEndDate.getTime()) {
				lotteryTimeNum.setNextIssueNo(next.getLotteryIssueNo());
				lotteryTimeNum.setNextHaltDate(next.getBetHaltDate());
				list.add(lotteryTimeNum);
				break;
			}
			
		}
		Calendar cal = Calendar.getInstance();
		//生成定时任务
		list.stream().forEach(c->{
			TimeTask task = new TimeTask();
			task.setLotteryCode(c.getLotteryCode());
			task.setLotteryIssueNo(c.getLotteryIssueNo());
			cal.setTime(c.getBetEndDate());
			cal.add(Calendar.SECOND, Integer.parseInt(Global.getConfig("game.ssc.second")));
			//延迟40s 
			task.setRunAtTime(cal.getTime());
			timeTaskList.add(task);
		});
		//掉服务保存，生成定时
		if(CollectionUtils.isNotEmpty(list)) {
			//保存开奖时刻信息
    		lotteryTimeNumDao.batchTimeNum(list);
    		//保存timetaskList
    		timeTaskService.addJobs(timeTaskList);
		}
    }
  
	/**
	 * 获取当前期号
	 * @param lotteryCode 彩种code
	 * @return 
	 */
	public LotteryTimeNum findCurrentIssueNo(String lotteryCode) {
		if(StringUtils.isBlank(lotteryCode)) {
			return null;
		}
		LotteryTimeNum lotteryTimeNum = lotteryTimeNumDao.queryCurrentIssueNo(lotteryCode, new Date());
		if(null == lotteryTimeNum) {
			return null;
		}
		return lotteryTimeNum;
	}
	/**
	 * 根据彩种和期号 查询 
	 * @param lotteryCode 彩种code
	 * @return 
	 */
	public LotteryTimeNum findByLotteryCodeIssueNo(String lotteryCode,String lotteryIssueNo) {
		if(StringUtils.isBlank(lotteryCode) || StringUtils.isBlank(lotteryIssueNo) ) {
			return null;
		}
		LotteryTimeNum lotteryTimeNum = lotteryTimeNumDao.queryByLotteryCodeIssueNo(lotteryCode, lotteryIssueNo);
		if(null == lotteryTimeNum) {
			return null;
		}
		return lotteryTimeNum;
	}
	
	
	/**
	 * 获取历史期号
	 * @param lotteryCode 彩种code
	 * @param rows 行数
	 * @return 
	 */
	public List<LotteryTimeNum> findLotteryTimeNum(String lotteryCode,int rows) {
		if(StringUtils.isBlank(lotteryCode)) {
			return null;
		}
		List<LotteryTimeNum>  hisList = lotteryTimeNumDao.queryHisLotteryTimeNum(lotteryCode,rows,new Date());
		if(CollectionUtils.isEmpty(hisList)) {
			return null;
		}
		return hisList;
	}
	
	@Transactional(readOnly = false)
	public String batchUpdateHalt(String[] ids,Integer betHaltDate) {
		if(ids == null || betHaltDate == null) {
			return "更新失败";
		}
		List<LotteryTimeNum>  list = lotteryTimeNumDao.queryByIdList(ids);
		if(CollectionUtils.isEmpty(list) ){
			return "更新失败";
		}
		list.stream().forEach(c->{
			LocalDateTime localDateTime =DateUtils.UDateToLocalDateTime(c.getBetEndDate());
			c.setBetHaltDate(DateUtils.LocalDateTimeToUdate(localDateTime.minusSeconds(betHaltDate)));
		});
		LotteryTimeNum lotteryTimeNum = null;
		if(list.size() > 1) {
			 lotteryTimeNum = lotteryTimeNumDao.queryByLotteryIssueNo(list.get(0).getNextIssueNo());
		}
		Map<String,Date> map =list.stream().collect(Collectors.toMap(LotteryTimeNum::getNextIssueNo, LotteryTimeNum::getBetHaltDate));
		if(null != lotteryTimeNum) {
			map.put(lotteryTimeNum.getNextIssueNo(), lotteryTimeNum.getBetHaltDate());
		}
		list.stream().forEach(c->{
			if(null != map.get(c.getLotteryIssueNo())) {
				c.setNextHaltDate(map.get(c.getLotteryIssueNo()));
				lotteryTimeNumDao.batchUpdateHaltDate(c);
			}
		});
		for (LotteryTimeNum c :list) {
			lotteryTimeNumDao.batchUpdateHaltDate(c);
		}
		return "更新成功";
	}
	
	
	
    public static void main(String[] args) throws ParseException {
    	/*Calendar cal = Calendar.getInstance();
    	BigDecimal i =new BigDecimal(1).divide(new BigDecimal(1000),0,BigDecimal.ROUND_CEILING);
    	Calendar cal2 = Calendar.getInstance();
    	cal.setTime(new Date());
    	cal2.setTime(DateUtils.parseDate("2017-11-29 13:48:50"));
    	Date nowDate = new Date();
//    	System.out.println(cal.get(Calendar.MINUTE));
    	System.out.println(i);*/
    	

		
	}
	
}