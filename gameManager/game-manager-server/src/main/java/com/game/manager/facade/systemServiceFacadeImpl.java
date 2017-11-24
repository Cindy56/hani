package com.game.manager.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.manager.modules.sys.service.SystemService;
import com.game.modules.sys.entity.Role;
import com.game.modules.sys.entity.User;
import com.game.modules.sys.service.SystemServiceFacade;

@Service("systemServiceFacade")
public class systemServiceFacadeImpl implements SystemServiceFacade {
	@Autowired
	private SystemService systemService;
	
	@Override
	public void saveUser(User user) {
		this.systemService.saveUser(user);
	}

	@Override
	public Role getRole(String id) {
		return this.systemService.getRole(id);
	}

	@Override
	public List<Role> findRoleByOfficeId(String officeId) {
		return systemService.findRoleByOfficeId(officeId);
	}

	@Override
	public void saveRole(Role role) {
		this.systemService.saveRole(role);
	}

	@Override
	public User assignUserToRole(Role role, User user) {
		return this.systemService.assignUserToRole(role, user);
	}

	@Override
	public String entryptPassword(String plainPassword) {
		return SystemService.entryptPassword(plainPassword);
	}

}
