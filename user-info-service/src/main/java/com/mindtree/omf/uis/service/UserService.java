package com.mindtree.omf.uis.service;

import com.mindtree.omf.uis.model.User;

public interface UserService {

	User saveOrder(User user);

	User findUser(int id);

}
