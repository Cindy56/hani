package com.game.manager.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.manager.modules.sys.service.OfficeService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.service.OfficeServiceFacade;

@Service("officeServiceFacade")
public class OfficeServiceFacadeImpl implements OfficeServiceFacade  {
	@Autowired
	private OfficeService officeService;
	
	@Override
	public Office get(String id) {
		return this.officeService.get(id);
	}

	@Override
	public List<Office> findOfficesByParentId(String parentOfficeId) {
		Office queryVO = new Office();
		queryVO.setParentIds(parentOfficeId);
		return this.officeService.findList(queryVO);
	}

	@Override
	public Office save(Office office) {
		this.officeService.save(office);
		return office;
	}

}
