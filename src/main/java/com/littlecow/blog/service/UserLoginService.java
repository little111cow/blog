package com.littlecow.blog.service;

import com.littlecow.blog.entity.User;

public interface UserLoginService {
    //校验用户密码和用户名
    User checkUserByUsernameAndPassword(String username,String password);

    boolean updateUser(User user);

    User getUserById(Long id);

    String getVcode();

    User getUserByEmail(String email);

    Boolean updateVcode(String code);

    User checkUserByMailAndVcode(String mail, String vcode);
}
