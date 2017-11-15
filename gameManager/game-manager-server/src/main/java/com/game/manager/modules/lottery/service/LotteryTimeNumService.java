/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.manager.common.persistence.Page;
import com.game.manager.common.service.CrudService;
import com.game.manager.common.utils.DateUtils;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.lottery.entity.LotteryTypeTime;
import com.game.manager.modules.lottery.schedule.TimeTaskService;
import com.game.manager.modules.sys.entity.Dict;
import com.game.manager.modules.lottery.dao.LotteryTimeNumDao;
import com.game.manager.modules.lottery.dto.ResponseLotteryNumDto;
import com.game.manager.modules.lottery.dto.TimeTask;

/**
 * 开奖时刻和开奖结果Service
 * @author jerry
 * @version 2017-11-10
 */
@Service
@Transactional(readOnly = true)
public class LotteryTimeNumService extends CrudService<LotteryTimeNumDao, LotteryTimeNum> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private LotteryTypeService lotteryTypeService;
	@Autowired
    private TimeTaskService timeTaskService;
	
	@Autowired
    private LotteryTimeNumDao lotteryTimeNumDao;
	
	@Autowired
    private LotteryTypeTimeService lotteryTypeTimeService;
	
	
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
	public void save(LotteryTimeNum lotteryTimeNum) {
		super.save(lotteryTimeNum);
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
	
	 /**
     * 生成开奖时刻明细
	 * @throws SchedulerException 
     * 
     */
   /*
    public Map<String,Object> generatePlanTime (LotteryTimeNum lotteryTimeNum) {
    	Map<String,Object> msg = new HashMap<String,Object>();
    	Double num = DateUtils.getDistanceOfTwoDate(lotteryTimeNum.getBetStartDate(),lotteryTimeNum.getBetEndDate());
    	LotteryType lotteryType = lotteryTypeService.queryLotteryType(lotteryTimeNum.getLotteryCode());
    	if(null != lotteryType) {
    		try {
    			List<LotteryTimeNum> timeNumList = new ArrayList<LotteryTimeNum>();
    			Date  betStartDate = DateUtils.parseDate(DateUtils.formatDateTime(lotteryTimeNum.getBetStartDate())+" "+lotteryType.getStartDate(), "yyyy-MM-dd HH:mm:ss");
    			Date  betEndDate = DateUtils.parseDate(DateUtils.formatDateTime(lotteryTimeNum.getBetStartDate())+" "+lotteryType.getEndDate(), "yyyy-MM-dd HH:mm:ss");
    			Calendar calStart= Calendar.getInstance();
    			Calendar calEnd= Calendar.getInstance();
    			calStart.setTime(betStartDate);
    			calEnd.setTime(betStartDate);
        		for (int i = 0; i < num; i++) {
        			calStart.add(Calendar.DATE, i);
        			calEnd.add(Calendar.DATE, i);
        			if(calStart.getTime().getTime() <= betEndDate.getTime()) {
        				int times = Integer.parseInt(lotteryType.getTimes());
        				for (int j = 1; j <= times; j++) {
        						LotteryTimeNum timeNum =new LotteryTimeNum();
        					//timeNum.preInsert();
        					timeNum.setLotteryIssueNo(DateUtils.formatDate(calStart.getTime(),"yyyyMMdd")+String.format("%03d", j));//期数
        					timeNum.setLotteryCode(lotteryTimeNum.getLotteryCode());
        					timeNum.setBetStartDate(calStart.getTime());//开始时间
        					calStart.add(Calendar.MINUTE, Integer.parseInt(lotteryType.getPeriodTotalTime()));
        					cal.setTime(calStart.getTime());
        					timeNum.setBetEndDate(calStart.getTime());//截至时间
        					cal.add(Calendar.SECOND,-(Integer.parseInt(lotteryType.getPeriodHaltTime())));
        					timeNum.setBetHaltDate(cal.getTime());//封单时刻时间
        					timeNumList.add(timeNum);
						}
            		}		
				}
    		} catch (Exception e) {
    			msg.put("msg", "生成开奖计划失败!");
    			logger.error("生成开奖计划失败！",e);
			}
    	}else {
    		msg.put("msg", "该彩种未配置");
    	}
        return msg;
    }
    */
	@Transactional(readOnly = false)
    public  void  generatePlanTime (TimeTask timeTask) throws SchedulerException {
		//TODO:返回一个数组
		List<LotteryTypeTime> lotteryTypeTimeList = lotteryTypeTimeService.queryLotteryTypeTime(timeTask.getLotteryCode());
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
		
		
		//循环天
		while (!tempDate.isAfter(endDatex)) {
			//为每个菜种生成一天的时刻明细,
			int issueNO = 1;
			for (LotteryTypeTime lotteryTypeTime : lotteryTypeTimeList) {
				//开始时间  startTime
				LocalDateTime startTime = tempDate.atTime(LocalTime.parse(lotteryTypeTime.getStartTime()));//每日开售时间
				//截至时间 endTime
				LocalDateTime endTime = tempDate.atTime(LocalTime.parse(lotteryTypeTime.getEndTime()));//每日开售时间
				//每期总时间 5分钟/10分钟
				int periodTotalTime = Integer.parseInt(lotteryTypeTime.getPeriodTotalTime());
				//封单时间
				int periodHaltTime = Integer.parseInt(lotteryTypeTime.getPeriodHaltTime());
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
					lotteryTimeNum.setLotteryIssueNo(DateUtils.formatDate(Date.from(betStartTime.atZone(ZoneId.systemDefault()).toInstant()),"yyyyMMdd")+String.format("%03d", issueNO));//期数
					lotteryTimeNum.setLotteryCode(timeTask.getLotteryCode());
					lotteryTimeNum.setBetStartDate(Date.from(betStartTime.atZone(ZoneId.systemDefault()).toInstant()));//开始时间
					lotteryTimeNum.setBetEndDate(Date.from(betEndTime.atZone(ZoneId.systemDefault()).toInstant()));//截至时间
					lotteryTimeNum.setBetHaltDate(Date.from(betHaltTime.atZone(ZoneId.systemDefault()).toInstant()));//封单时刻时间
					lotteryTimeNum.setDelFlag("0");
					lotteryTimeNum.setStatus("0");
					lotteryTimeNumList.add(lotteryTimeNum);
					//递增旗号
					issueNO++;
				}				
			}
			tempDate = tempDate.plusDays(1);
		}
		lotteryTimeNumList.sort((h1, h2) -> h1.getBetStartDate().compareTo(h2.getBetStartDate()));
		LotteryTimeNum current = null;
		LotteryTimeNum next = null;
		Date convertEndDate = Date.from(endDatex.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<LotteryTimeNum> list = new ArrayList<LotteryTimeNum>();
		for (Iterator<LotteryTimeNum> iterator = lotteryTimeNumList.iterator(); iterator.hasNext();) {
			LotteryTimeNum lotteryTimeNum =  iterator.next();
			if(next != null) {
				current = lotteryTimeNum; 
				lotteryTimeNum = next; 
				next = current; 
			}else {
				next =  iterator.next();  
			}
			if(!next.getBetEndDate().after(convertEndDate)) {
				lotteryTimeNum.setNextIssueNo(next.getLotteryIssueNo());
				lotteryTimeNum.setNextHaltDate(next.getBetHaltDate());
				list.add(lotteryTimeNum);
			}
			if(next.getBetEndDate().after(convertEndDate)) {
				lotteryTimeNum.setNextIssueNo(next.getLotteryIssueNo());
				lotteryTimeNum.setNextHaltDate(next.getBetHaltDate());
				list.add(lotteryTimeNum);
				break;
			}
		}
		
		list.stream().forEach(c->{
			TimeTask task = new TimeTask();
			task.setLotteryCode(c.getLotteryCode());
			task.setLotteryIssueNo(c.getLotteryIssueNo());
			task.setRunAtTime(c.getBetEndDate());// 截止时间后10秒开始拉开奖号码
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
	
	
	
	
    public static void main(String[] args) throws ParseException {
    
    	
	}
	
}