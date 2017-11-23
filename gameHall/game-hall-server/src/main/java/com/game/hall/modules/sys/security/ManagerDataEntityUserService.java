package com.game.hall.modules.sys.security;

import org.springframework.stereotype.Service;

import com.game.common.persistence.DataEntityUserService;
import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.sys.entity.User;

@Service
public class ManagerDataEntityUserService implements DataEntityUserService {

	@Override
	public User getCurrentUser() {
		return UserUtils.getUser();
	}

}
