package com.game.modules.sys.service;

import java.util.List;
import com.game.modules.sys.entity.Office;

/**
 * 机构Service
 */
public interface OfficeServiceFacade {
	/** 根据机构id获取机构 */
	public Office get(String id);
	/** 设置ParentIds，查找他的下级 */
	public List<Office> findOfficesByParentId(String parentOfficeId);
	/** 保存 */
	public void save(Office office);
}
