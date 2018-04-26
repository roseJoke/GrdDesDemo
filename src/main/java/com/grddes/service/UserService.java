package com.grddes.service;

import com.github.pagehelper.PageHelper;
import com.grddes.mapper.UserMapper;
import com.grddes.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/12  18:00
 */

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapService.class);
    @Autowired
    UserMapper userMapper;

    @Autowired
    HouseService houseService;

    @Autowired
    WorkService workService;


    public User getUserByName(String username) {
        return userMapper.getUserByName(username);

    }

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public int backMaxId() {
        return userMapper.backmaxid();
    }

    public int deleteOne(int uid) {

        houseService.deleteHouses(uid);
        workService.deleteByUserId(uid);
        return userMapper.deleteByPrimaryKey(uid);
    }


    public void getUserById(int id) {
        userMapper.selectByPrimaryKey(id);
    }

    public int getUserType(int id) {
        return userMapper.selectUserType(id);
    }

    public int updatePwd(int user_id, String pwd) {
        return userMapper.updatePwd(user_id, pwd);
    }


    public List<User> SelectAllUser() {
        return userMapper.SelectAllUser();
    }

    public List<User> selectUserByElements(User user) {
        return  userMapper.selectUserByElements(user);
    }
}
