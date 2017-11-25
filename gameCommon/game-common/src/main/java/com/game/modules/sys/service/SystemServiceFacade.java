/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.sys.service;

import java.util.List;

import com.game.modules.sys.entity.Role;
import com.game.modules.sys.entity.User;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author ThinkGem
 * @version 2013-12-05
 */
public interface SystemServiceFacade{
	//-- User Service --//
	/** 保存用户 */
	public User saveUser(User user);
	
	//-- Role Service --//
	/** 根据主键查找role */
	public Role getRole(String id);
	/** 根据officeid查找公司所有角色 */
	public List<Role> findRoleByOfficeId(String officeId);
	/** 保存角色 */
	public Role saveRole(Role role);
	/** 为用户分配角色 */
	public User assignUserToRole(Role role, User user);
	
}
