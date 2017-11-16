/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.manager.common.persistence.Page;
import com.game.manager.common.service.CrudService;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.lottery.dao.LotteryPlayConfigDao;
import com.game.manager.modules.lottery.dao.LotteryTypeDao;
import com.game.manager.modules.lottery.dao.LotteryTypeTimeDao;
import com.game.manager.modules.lottery.entity.LotteryPlayConfig;
import com.game.manager.modules.lottery.entity.LotteryType;
import com.game.manager.modules.lottery.entity.LotteryTypeTime;

/**
 * 彩种基本信息管理Service
 * @author Terry
 * @version 2017-11-15
 */
@Service
@Transactional(readOnly = true)
public class LotteryTypeService extends CrudService<LotteryTypeDao, LotteryType> {
	
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
     * 彩种管理DAO
     */
    @Autowired
    private LotteryTypeDao lotteryTypeDao;

    /**
     * 根据记录ID获取单条数据
     */
    public LotteryType get(String id) {
        LotteryType lotteryType = super.get(id);
        lotteryType.setLotteryPlayConfigList(lotteryPlayConfigDao.findList(new LotteryPlayConfig(lotteryType)));
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
        return lotteryTypeDao.getByCode(code);
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
    public void save(LotteryType lotteryType) {
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