package com.game.trade.modules;

import org.springframework.stereotype.Service;

import com.game.common.persistence.DataEntityUserService;
import com.game.modules.sys.entity.User;

@Service
public class TradeDataEntityUserService implements DataEntityUserService {

	@Override
	public User getCurrentUser() {
		return new User();
	}

}
