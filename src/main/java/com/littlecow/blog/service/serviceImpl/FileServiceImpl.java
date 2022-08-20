package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.File;
import com.littlecow.blog.mapper.FileMapper;
import com.littlecow.blog.service.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{
    @Resource
    private FileMapper fileMapper;

    @Override
    public List<File> getFileListPublished() {
        return fileMapper.getFileListPublished();
    }

    @Override
    @Transactional
    public Boolean publish(Integer id, Boolean published) {
        return fileMapper.publish(id, published);
    }

    @Override
    public File getFileByName(String filename, String suffix) {
        return fileMapper.getFileByName(filename,suffix);
    }

    @Override
    @Transactional
    public Boolean updateUploadTime(Integer id) {
        return fileMapper.updateUploadTime(id);
    }

    @Override
    @Transactional
    public Boolean uploadFile(MultipartFile file,Boolean isAvatar) {
        boolean isOk ;
        File file1 = new File();
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
            file1.setFileSuffix(suffixName);
            file1.setFileName(fileName.substring(0,fileName.lastIndexOf(".")));
            // 设置文件存储路径
            String filePath = getFilePathByOs();
            file1.setPath(filePath);
            file1.setUploadTime(new Date());
            String path = filePath +"/"+ fileName;
            if(isAvatar){//头像始终保存固定名称avatar每次上传时替换
                file1.setFileName("avatar");
                file1.setFileSuffix("jpg");
                path = filePath+"/avatar.jpg";  //全部转换为jpg格式便于前端统一读取
            }

            java.io.File dest = new java.io.File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            isOk = true;
        } catch (IllegalStateException e) {
            isOk = false;
            e.printStackTrace();
        } catch (IOException e) {
            isOk = false;
            e.printStackTrace();
        }
        File file2 = getFileByName(file1.getFileName(),file1.getFileSuffix());  //先判断是否已经存在同名文件
        if(file2 != null){  //存在不更新数据库只覆盖磁盘文件更新上传时间即可
            if(isOk){
                isOk = updateUploadTime(file2.getId());
            }
            return isOk;
        }else{  //不存在新增
            if(isOk){
                isOk = fileMapper.uploadFile(file1);
            }
        }
        return isOk;
    }

    private String getFilePathByOs() {
        String filePath = System.getProperty("user.home").replaceAll("\\\\", "/");
        filePath += "/myapps/files";
        return filePath;
    }

    @Override
    @Transactional
    public Boolean deleteFileById(Integer id) {
        File file = getFileById(id);
        String path = getFilePathByOs() + "/";
        path += file.getFileNameWithExt();
        java.io.File file1;
        boolean ok = false;
        if(path != null) {
            try {
                file1 = new java.io.File(path);
                ok = file1.delete(); //操作磁盘
                if (ok) {
                    ok = fileMapper.deleteFileById(id);  //操作数据库
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ok;
    }

    @Override
    public File getFileById(Integer id) {
        return fileMapper.getFileById(id);
    }

    @Override
    public List<File> getFileList() {
        return fileMapper.getFileList();
    }
}
