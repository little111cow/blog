package com.littlecow.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.File;
import com.littlecow.blog.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class FileController {
    @Resource
    private FileService fileService;

    @RequestMapping("/admin/files")
    public String toFiles(@RequestParam(defaultValue = "1") int pagenum,  Model model){
        PageHelper.startPage(pagenum,Contants.PAGE_SIZE);
        List<File> fileList = fileService.getFileList();
        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/files";
    }

    @RequestMapping("/admin/files/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file,
                             @RequestParam(defaultValue = "false")Boolean isAvatar,
                             RedirectAttributes attributes){
        if (file.isEmpty()) {
            attributes.addFlashAttribute(Contants.MESSAGE,"文件为空！");
            return "redirect:/admin/files";
        }
        boolean isOk = fileService.uploadFile(file,isAvatar);
        if(isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "上传成功！");
        }else {
            attributes.addFlashAttribute(Contants.MESSAGE,"上传失败！");
        }
        return "redirect:/admin/files";
    }

    @RequestMapping("/admin/files/{id}/publish/{published}")
    public String publish(@PathVariable Boolean published,
                          @PathVariable int id,
                          RedirectAttributes attributes){
        boolean isOk = fileService.publish(id, published);
        if(isOk && published) {
            attributes.addFlashAttribute(Contants.MESSAGE, "资源发布成功！");
        }
        if(isOk && !published) {
            attributes.addFlashAttribute(Contants.MESSAGE, "成功取消资源发布！");
        }
        if(!isOk && published){
            attributes.addFlashAttribute(Contants.MESSAGE,"资源发布失败！");
        }
        if(!isOk && !published) {
            attributes.addFlashAttribute(Contants.MESSAGE,"取消资源发布失败！");
        }
        return "redirect:/admin/files";
    }

    @RequestMapping("/admin/files/{id}/delete")
    public String deleteFile(@PathVariable("id")Integer id, RedirectAttributes attributes){
        boolean isOk = fileService.deleteFileById(id);
        if(isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "删除成功！");
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"删除失败!");
        }
        return "redirect:/admin/files";
    }

    @RequestMapping("/files/{id}/download")
    @ResponseBody
    public String downloadFile(@PathVariable("id")Integer id,HttpServletResponse response){
       File file = fileService.getFileById(id);
        String filename = file.getFileNameWithExt();
        String filepath = getFilePathByOs() + "/"+filename;
        if(!"".equals(filepath)){
            java.io.File file1 = new java.io.File(filepath);
            if(file1.exists()){
                response.setContentType("application/force-download");// 设置强制下载不打开
                try {
                    filename = URLEncoder.encode(filename,"utf-8");  //解决中文文件名乱码问题
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                response.addHeader("Content-Disposition", "attachment;fileName=" + filename);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file1);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功！";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败！";
    }

    private String getFilePathByOs() {
        String filePath = System.getProperty("user.home").replaceAll("\\\\", "/");
        filePath += "/myapps/files";
        return filePath;
    }

    @RequestMapping("/files")
    public String sourcePage(@RequestParam(defaultValue = "1") int pagenum,  Model model) {
        PageHelper.startPage(pagenum,Contants.PAGE_SIZE);
        List<File> fileList = fileService.getFileListPublished();
        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        model.addAttribute("pageInfo",pageInfo);
        return "files";
    }
}
