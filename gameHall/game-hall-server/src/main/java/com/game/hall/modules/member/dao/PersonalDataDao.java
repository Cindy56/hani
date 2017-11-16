/**
 * 今日开奖
 */
package com.game.hall.modules.member.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.member.entity.MemberAccount;

/**
 * @author antonio
 *
 */

@MyBatisDao
public interface PersonalDataDao {
	
	//查找所有会员用户列表
	public List<MemberAccount> findAllList();
	
	//查找某个用户信息
	public MemberAccount get(String name);
	
	//修改用户信息
	public int update(MemberAccount memberAccount);
}
