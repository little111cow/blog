package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
public interface TypesMapper {
    @Select("select * from t_type")
    List<Type> getTypeList();

    @Select("select * from t_type where name=#{typename}")
    Type getTypeByName(@Param("typename")String typename);

    @Insert("insert into t_type(name) values(#{typename})")
    boolean addType(@Param("typename") String typename);

    @Delete("delete from t_type where id=#{typeid}")
    boolean deleteTypeById(@Param("typeid")Long id);

    @Select("select * from t_type where id = #{typeid}")
    Type getTypeById(@Param("typeid")Long typeid);

    @Update("update t_type set name=#{typename} where id = #{typeid}")
    boolean updateTypeById(@Param("typename")String name,@Param("typeid") Long typeid);

}
