package com.daniel.form.service;

import com.daniel.form.dao.UserDao;
import com.daniel.form.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the User Service.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Integer id) {
        return this.userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.userDao.findAll();
    }

    @Override
    public void saveOrUpdate(User user) {

        if (findById(user.getId()) == null) {
            this.userDao.save(user);
        } else {
            this.userDao.update(user);
        }

    }

    @Override
    public void delete(int id) {
        this.userDao.delete(id);
    }

}