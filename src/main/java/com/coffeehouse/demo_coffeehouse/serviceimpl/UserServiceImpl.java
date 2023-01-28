package com.coffeehouse.demo_coffeehouse.serviceimpl;

import com.coffeehouse.demo_coffeehouse.DAO.UserDao;
import com.coffeehouse.demo_coffeehouse.model.Credential;
import com.coffeehouse.demo_coffeehouse.model.UserInfo;
import com.coffeehouse.demo_coffeehouse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<UserInfo> getUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Optional<List<UserInfo>> getUserById(int userId) {
        if (!userDao.getUser(userId).isPresent()) {
            new UsernameNotFoundException("User not found!!");
            return Optional.empty();
        } else {
            return userDao.getUser(userId);
        }
    }

    @Override
    public UserInfo createUser(UserInfo userInfo) {
        userDao.addNewUser(userInfo);
        return userInfo;
    }
}
