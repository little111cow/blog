package com.littlecow.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Link;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.LinkService;
import com.littlecow.blog.util.DateFormatUtils;
import com.littlecow.blog.util.MailUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class LinkController {
    @Resource
    private LinkService linkService;

    @GetMapping("/link")
    public String link(@RequestParam(name = "pagenum", defaultValue = "1") int pagenum, Model model){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Link> linkList = linkService.getLinkListPublished();
        PageInfo<Link> pageInfo = new PageInfo<>(linkList);
        model.addAttribute(Contants.PAGE_INFO, pageInfo);
        model.addAttribute("linkCount", linkList.size());
        return "link";
    }

    @GetMapping("/link/input")
    public String linkInput(Model model){
        model.addAttribute("link", new Link());
        return "link-input";
    }

    @PostMapping("/link")
    public String saveLink(Link link, RedirectAttributes attributes, HttpServletRequest request){
        boolean isOk = linkService.saveLink(link);
        if (isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "申请成功，等待管理员审核<^-^>");
            User user = (User) request.getSession().getAttribute(Contants.USER_SESSION);
            if (user != null) {
                return "redirect:/admin/link";
            }
            return "redirect:link";
        }
        attributes.addFlashAttribute(Contants.MESSAGE, "提交失败！");
        return "redirect:/link/input";
    }

    @GetMapping("/admin/link/{id}/delete")
    public String delete(@PathVariable("id")Integer id, RedirectAttributes attributes){
        boolean isOk = linkService.delete(id);
        if (isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "删除成功^-^");
            return "redirect:/admin/link";
        }
        attributes.addFlashAttribute(Contants.MESSAGE, "删除失败！");
        return "redirect:/admin/link";
    }

    @GetMapping("/admin/link/{id}/publish")
    public String publish(@PathVariable("id")Integer id, RedirectAttributes attributes){
        Link link = linkService.getLinkById(id);
        link.setPublished(!link.getPublished());
        boolean isOk = linkService.updateLink(link);
        if (isOk && link.getPublished()) {
            final String title = "友链审核通过通知。";
            String content = "你好，" + link.getNickName()+"!\n你的链接[" + link.getUrl() + "]已经通过审核，成为本站的友情链接。" +
                    "如果需要修改，请联系管理员。" + DateFormatUtils.dateFormat(new Date());
            MailUtils.sendMail(link.getEmail(), title, content);
            attributes.addFlashAttribute(Contants.MESSAGE, "审核通过,发布成功！");
            return "redirect:/admin/link";
        }
        if (isOk && !link.getPublished()) {
            attributes.addFlashAttribute(Contants.MESSAGE, "取消发布成功！");
            return "redirect:/admin/link";
        }
        if (!isOk && !link.getPublished()) {
            attributes.addFlashAttribute(Contants.MESSAGE, "取消发布失败！");
            return "redirect:/admin/link";
        }
        attributes.addFlashAttribute(Contants.MESSAGE, "取消发布成功！");
        return "redirect:/admin/link";
    }

    @GetMapping("/admin/link/{id}")
    public String toEdit(@PathVariable("id")Integer id, Model model){
        Link link = linkService.getLinkById(id);
        model.addAttribute("link", link);
        return "link-input";
    }

    @PostMapping("/admin/link/{id}")
    public String edit(@PathVariable("id")Integer id, Link link, RedirectAttributes attributes){
        Link link1 = linkService.getLinkById(id);
        if (link.equals(link1)) {
            attributes.addFlashAttribute(Contants.MESSAGE, "编辑成功^-^");
            return "redirect:/admin/link";
        }
        boolean isOk = linkService.updateLink(link);
        if (isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "编辑成功^-^");
            return "redirect:/admin/link";
        }
        attributes.addFlashAttribute(Contants.MESSAGE, "编辑失败！");
        return "redirect:/admin/link";
    }

    @GetMapping("/admin/link")
    public String linkPage(@RequestParam(name = "pagenum", defaultValue = "1") int pagenum, Model model){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Link> linkList = linkService.getLinkListAll();
        PageInfo<Link> pageInfo = new PageInfo<>(linkList);
        model.addAttribute(Contants.PAGE_INFO, pageInfo);
        return "admin/link";
    }
}
