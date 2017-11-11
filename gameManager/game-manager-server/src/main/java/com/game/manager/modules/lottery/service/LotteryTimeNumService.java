/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.game.manager.modules.lottery.entity.LotteryType;
import com.game.manager.modules.lottery.schedule.TimeTaskService;
import com.game.manager.modules.sys.entity.Dict;
import com.game.manager.modules.lottery.dao.LotteryTimeNumDao;
import com.game.manager.modules.lottery.dto.TimeTaskDTO;

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
    public  void  generatePlanTime (TimeTaskDTO timeTaskDTO) throws SchedulerException {
    	LotteryType lotteryType = lotteryTypeService.queryLotteryType(timeTaskDTO.getLotteryCode());
    	if(null != lotteryType) {
    		//生成时刻表和定时任务
    		List<LotteryTimeNum> lotteryTimeNumList = new ArrayList<LotteryTimeNum>();
			LocalDate currentDate = LocalDateTime.ofInstant(timeTaskDTO.getRunStartTime().toInstant(), ZoneId.systemDefault()).toLocalDate();
    		LocalDate endDate = LocalDateTime.ofInstant(timeTaskDTO.getRunEndTime().toInstant(), ZoneId.systemDefault()).toLocalDate();
    		LocalDate tempDate = currentDate;
    		int periodTotalTime = Integer.parseInt(lotteryType.getPeriodTotalTime());
			int periodHaltTime = Integer.parseInt(lotteryType.getPeriodHaltTime());
			int times = Integer.parseInt(lotteryType.getTimes());
    		while (!tempDate.isAfter(endDate)) {
    			LocalDateTime startTime = tempDate.atTime(LocalTime.parse(lotteryType.getStartDate()));//每日开售时间
    			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    			//DateTimeFormatter formatterxxx = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    			for (int i = 0; i < times; i++) {
    				LocalDateTime betStartTime = startTime.plusMinutes(periodTotalTime * i);
    				LocalDateTime betEndTime = betStartTime.plusMinutes(periodTotalTime);
    				LocalDateTime betHaltTime = betEndTime.minusSeconds(periodHaltTime);
    				// 赋值时刻对象
    				LotteryTimeNum lotteryTimeNum =new LotteryTimeNum();
    				lotteryTimeNum.preInsert();
    				lotteryTimeNum.setLotteryIssueNo(DateUtils.formatDate(Date.from(betStartTime.atZone(ZoneId.systemDefault()).toInstant()),"yyyyMMdd")+String.format("%03d", i+1));//期数
    				lotteryTimeNum.setLotteryCode(timeTaskDTO.getLotteryCode());
    				lotteryTimeNum.setBetStartDate(Date.from(betStartTime.atZone(ZoneId.systemDefault()).toInstant()));//开始时间
    				lotteryTimeNum.setBetEndDate(Date.from(betEndTime.atZone(ZoneId.systemDefault()).toInstant()));//截至时间
    				lotteryTimeNum.setBetHaltDate(Date.from(betHaltTime.atZone(ZoneId.systemDefault()).toInstant()));//封单时刻时间
					lotteryTimeNum.setDelFlag("0");
					lotteryTimeNum.setStatus("0");
					lotteryTimeNumList.add(lotteryTimeNum);
    			}
    			tempDate = tempDate.plusDays(1);
    		}
    		if(CollectionUtils.isNotEmpty(lotteryTimeNumList)) {
    			//保存开奖时刻信息
        		lotteryTimeNumDao.batchTimeNum(lotteryTimeNumList);
        		//保存timetaskList
        		createTimeTask(lotteryTimeNumList);
    		}
    		
    	}
    }
    
    public void createTimeTask(List<LotteryTimeNum> lotteryTimeNumList) throws SchedulerException {
    	if(CollectionUtils.isNotEmpty(lotteryTimeNumList)) {
    		for(LotteryTimeNum lotteryTimeNum:lotteryTimeNumList) {
    			TimeTaskDTO timeTask = new TimeTaskDTO();
				timeTask.setLotteryCode(lotteryTimeNum.getLotteryCode());
				timeTask.setLotteryIssueNo(lotteryTimeNum.getLotteryIssueNo());
				LocalDateTime localDateTime = lotteryTimeNum.getBetEndDate().toInstant().atZone( ZoneId.systemDefault()).toLocalDateTime().plusSeconds(10);
				timeTask.setRunAtTime( Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));// 截止时间后10秒开始拉开奖号码
				timeTaskService.addJob(timeTask);
    		}
    	}
    }
    
    
    public static void main(String[] args) {
   
	}
	
}