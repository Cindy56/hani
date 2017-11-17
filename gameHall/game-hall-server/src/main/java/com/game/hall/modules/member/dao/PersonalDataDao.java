/**
 * 今日开奖
 */
package com.game.hall.modules.member.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.MemberAccountCard;

import com.game.hall.modules.member.entity.MemberAccount;
import com.game.manager.common.persistence.annotation.MyBatisDao;

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
	
	//更新安全码
	public int modifySec(String id,String newPassWord);
	
	//增加银行卡绑定
	public int insertCard(MemberAccountCard memberAccountCard);
	
	//增加账户
	public int insert(com.entity.MemberAccount memberAccount);
	
	//删除银行卡
	public int deleteAccountCard(String bankCardNo);
	
	//查询该用户所有银行卡信息
	public List<MemberAccountCard> getAllAccountCard(String id);
}
