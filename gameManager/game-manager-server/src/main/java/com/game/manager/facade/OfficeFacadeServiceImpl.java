package com.game.manager.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.manager.modules.sys.service.OfficeService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.service.OfficeServiceFacade;

@Service("officeFacadeService")
public class OfficeFacadeServiceImpl implements OfficeServiceFacade  {
	@Autowired
	private OfficeService officeService;
	
	@Override
	public Office get(String id) {
		return this.get(id);
	}

	@Override
	public List<Office> findOfficesByParentId(String parentOfficeId) {
		Office queryVO = new Office();
		queryVO.setParentIds(parentOfficeId);
		return this.officeService.findList(queryVO);
	}

	@Override
	public void save(Office office) {
		this.save(office);
	}

}
