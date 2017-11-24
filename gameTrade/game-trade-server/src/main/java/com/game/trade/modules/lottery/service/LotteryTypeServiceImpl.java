/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.common.utils.StringUtils;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.entity.LotteryType;
import com.game.modules.lottery.entity.LotteryTypeTime;
import com.game.modules.lottery.service.LotteryTypeService;
import com.game.trade.modules.lottery.dao.LotteryPlayConfigDao;
import com.game.trade.modules.lottery.dao.LotteryTypeDao;
import com.game.trade.modules.lottery.dao.LotteryTypeTimeDao;

/**
 * 彩种基本信息管理Service
 * @author Terry
 * @version 2017-11-15
 */
@Service("lotteryTypeService")
@Transactional(readOnly = true)
public class LotteryTypeServiceImpl 
	extends CrudService<LotteryTypeDao, LotteryType> implements LotteryTypeService {
	
	@Autowired
    private LotteryTypeDao lotteryTypeDao;
	
    /**
     * 彩种玩法DAO
     */
    @Autowired
    private LotteryPlayConfigDao lotteryPlayConfigDao;

    /**
     * 开奖方案DAO
     */
    @Autowired
    private LotteryTypeTimeDao lotteryTypeTimeDao;

    /**
     * 根据记录ID获取单条数据
     */
    public LotteryType get(String id) {
        LotteryType lotteryType = super.get(id);
        lotteryType.setLotteryTypeTimeList(lotteryTypeTimeDao.findList(new LotteryTypeTime(lotteryType)));
        return lotteryType;
    }

    /**
     * 通过彩种编号获取单条数据
     * @param code 彩种编号
     * @return 查询的数据实体对象
     * @author Terry
     */
    public LotteryType getByCode(String code) {
    	 LotteryType lotteryType = lotteryTypeDao.getByCode(code);
    	 if(null != lotteryType) {
    		 lotteryType.setLotteryTypeTimeList(lotteryTypeTimeDao.findList(new LotteryTypeTime(lotteryType)));
    	 }
        return lotteryType;
    }

    /**
     * 查询所有数据
     */
    public List<LotteryType> findList(LotteryType lotteryType) {
        return super.findList(lotteryType);
    }

    /**
     * 分页查询
     */
    public Page<LotteryType> findPage(Page<LotteryType> page, LotteryType lotteryType) {
        return super.findPage(page, lotteryType);
    }

    /**
     * 保存表单提交数据
     */
    @Transactional(readOnly = false)
    public LotteryType save(LotteryType lotteryType) {
        super.save(lotteryType);
        for (LotteryTypeTime lotteryTypeTime : lotteryType.getLotteryTypeTimeList()) {
            if (lotteryTypeTime.getId() == null) {
                continue;
            }
            if (LotteryTypeTime.DEL_FLAG_NORMAL.equals(lotteryTypeTime.getDelFlag())) {
                if (StringUtils.isBlank(lotteryTypeTime.getId())) {
                    lotteryTypeTime.setLotteryTypeId(lotteryType);
                    lotteryTypeTime.preInsert();
                    lotteryTypeTimeDao.insert(lotteryTypeTime);
                }
                else {
                    lotteryTypeTime.preUpdate();
                    lotteryTypeTimeDao.update(lotteryTypeTime);
                }
            }
            else {
                lotteryTypeTimeDao.delete(lotteryTypeTime);
            }
        }
        
        return lotteryType;
    }

    /**
     * 删除一条数据（更新删除标识）
     */
    @Transactional(readOnly = false)
    public void delete(LotteryType lotteryType) {
        super.delete(lotteryType);
        lotteryPlayConfigDao.delete(new LotteryPlayConfig(lotteryType));
        lotteryTypeTimeDao.delete(new LotteryTypeTime(lotteryType));
    }

    
    /**
     * 根据彩种code查询彩种信息
     */
   
    public LotteryType queryLotteryType(String lotteryCode) {
        return lotteryTypeDao.queryLotteryType(lotteryCode);
    }
 
  
    

}