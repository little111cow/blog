package com.littlecow.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Notice;
import com.littlecow.blog.service.NoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(@RequestParam(name = "pagenum", defaultValue = "1") int pagenum, Model model) {
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Notice> noticeList = noticeService.getNoticeListPublished();
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("noticeCount", noticeList.size());
        return "notice";
    }

    @GetMapping("/admin/notice")
    public String noticeManage(@RequestParam(name = "pagenum", defaultValue = "1") int pagenum, Model model) {
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Notice> noticeList = noticeService.getNoticeList();
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/notice";
    }

    @GetMapping("/admin/notice/input")
    public String input(Model model){
        model.addAttribute("notice", new Notice());
        return "admin/notice-input";
    }

    @PostMapping("/admin/notice")
    public String save(Notice notice, RedirectAttributes attributes){
        boolean isOk = noticeService.addNotice(notice);
        if (!isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "添加失败！");
            return "redirect:/admin/notice";
        }
        attributes.addFlashAttribute(Contants.MESSAGE, "添加成功^-^");
        return "redirect:/admin/notice";
    }

    @GetMapping("/admin/notice/{id}")
    public String toEditPage(@PathVariable("id")Integer id, Model model){
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "admin/notice-input";
    }

    @PostMapping("/admin/notice/{id}")
    public String edit(@PathVariable("id") Integer id, Notice notice, RedirectAttributes attributes) {
        Notice notice1 = noticeService.getNoticeById(id);
        if (notice.equals(notice1)) {
            attributes.addFlashAttribute(Contants.MESSAGE, "公告编辑成功<^-^>");
            return "redirect:/admin/notice";
        }
        boolean isOk = noticeService.editNotice(notice);
        if (!isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "公告编辑失败!");
            return "redirect:/admin/notice";
        }
        attributes.addFlashAttribute(Contants.MESSAGE, "公告编辑成功<^-^>");
        return "redirect:/admin/notice";
    }

    @GetMapping("/admin/notice/{id}/publish")
    public String publish(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        Notice notice = noticeService.getNoticeById(id);
        if (notice == null) {
            attributes.addFlashAttribute(Contants.MESSAGE, "公告不存在！");
            return "redirect:/admin/notice";
        }
        Notice notice1 = new Notice();
        BeanUtils.copyProperties(notice, notice1);
        notice1.setPublished(!notice1.getPublished());  // 取反发布状态再更新公告即可
        boolean isOk = noticeService.editNotice(notice1);
        if (!isOk) {
            if (notice1.getPublished()) {
                attributes.addFlashAttribute(Contants.MESSAGE, "公告发布失败!");
                return "redirect:/admin/notice";
            }
            attributes.addFlashAttribute(Contants.MESSAGE, "取消发布公告失败!");
            return "redirect:/admin/notice";
        }
        if (!notice1.getPublished()) {
            attributes.addFlashAttribute(Contants.MESSAGE, "取消发布公告成功<^-^>");
            return "redirect:/admin/notice";
        }
        attributes.addFlashAttribute(Contants.MESSAGE, "公告发布成功<^-^>");
        return "redirect:/admin/notice";
    }

    @GetMapping("/admin/notice/{id}/delete")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        boolean isOk = noticeService.deleteNoticeById(id);
        if (!isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "公告删除失败!");
            return "redirect:/admin/notice";
        }
        attributes.addFlashAttribute(Contants.MESSAGE, "公告删除成功<^-^>");
        return "redirect:/admin/notice";
    }
}
