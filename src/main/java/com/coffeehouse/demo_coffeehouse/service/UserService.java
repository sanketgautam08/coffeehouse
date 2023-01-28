package com.coffeehouse.demo_coffeehouse.service;

import com.coffeehouse.demo_coffeehouse.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserInfo> getUsers();
    Optional<List<UserInfo>> getUserById(int id);
    UserInfo createUser(UserInfo userInfo);
}
