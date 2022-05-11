package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.User;
import com.littlecow.blog.mapper.UserLoginMapper;
import com.littlecow.blog.service.UserLoginService;
import com.littlecow.blog.util.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserLoginMapper userLoginMapper;

    @Override
    public User getUserById(Long id) {
        return userLoginMapper.getUserById(id);
    }

    @Override
    public String getVcode() {
        return userLoginMapper.getVcode();
    }

    @Override
    @Transactional
    public Boolean updateVcode(String code) {
        return userLoginMapper.updateVcode(code);
    }

    @Override
    public User checkUserByUsernameAndPassword(String username, String password) {
//        String Md5password = MD5Utils.code(password);
        return userLoginMapper.checkUserByUsernameAndPassword(username,password);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        user.setUpdateTime(new Date());
        return userLoginMapper.updateUser(user);
    }
}
