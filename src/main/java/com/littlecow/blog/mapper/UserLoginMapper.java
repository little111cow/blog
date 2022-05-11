package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserLoginMapper {
    //校验登录用户名和密码
    @Select("select * from t_user where username=#{username} and password=#{password} ")
    User checkUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Update("update t_user set nickname = #{user.nickname},username=#{user.username},password=#{user.password}," +
            "email=#{user.email},update_time = #{user.updateTime} where id = #{user.id}")
    boolean updateUser(@Param("user") User user);

    @Select("select * from t_user where id = #{uid}")
    User getUserById(@Param("uid") Long id);

    @Select("select vcode from t_user where id = 1")
    String getVcode();

    @Update("update t_user set vcode = #{code} where id = 1")
    Boolean updateVcode(@Param("code") String code);
}
