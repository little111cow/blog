package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileMapper {
    @Select("select * from t_file order by upload_time desc")
     List<File> getFileList();

    @Select("select * from t_file where id = #{fid}")
    File getFileById(@Param("fid")Integer id);

    @Delete("delete from t_file where id = #{fid}")
    Boolean deleteFileById(@Param("fid") Integer id);

    @Insert("insert into t_file(path,file_name,file_suffix,upload_time) values(#{file.path},#{file.fileName},#{file.fileSuffix},#{file.uploadTime})")
    Boolean uploadFile(@Param("file")File file);

    @Select("select * from t_file where file_name = #{filename} and file_suffix = #{suffix}")
    File getFileByName(@Param("filename") String filename,@Param("suffix") String suffix);

    @Update("update t_file set upload_time = (select now()) where id = #{fid}")
    Boolean updateUploadTime(@Param("fid")Integer id);
}
