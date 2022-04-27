package com.littlecow.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Type;
import com.littlecow.blog.service.TypesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * create by liujiann @2022.4.23
 *
 * */

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Resource
    private TypesService typesService;

    @RequestMapping("/types")
    public String getTypes(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model,HttpSession session){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Type> typeList = typesService.getTypeList();
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @RequestMapping("/types/input")
    public String toAddTypes(Model model){
        model.addAttribute(Contants.TYPE,new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        Type t = typesService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute(Contants.MESSAGE,"不能添加重复的类型！");
            return "redirect:/admin/types/input";
        }
        boolean isTrue = typesService.addType(type);
        if (isTrue) {
            attributes.addFlashAttribute(Contants.MESSAGE, "添加成功。");
            return "redirect:/admin/types";
        } else {
            attributes.addFlashAttribute(Contants.MESSAGE, "添加失败！");
            return "redirect:/admin/types/input";
        }
    }

    @RequestMapping("/types/{id}/delete")
    public String deleteTypeById(@PathVariable Long id,RedirectAttributes attributes){
        if(typesService.deleteTypeById(id)){
            attributes.addFlashAttribute(Contants.MESSAGE,"删除成功！");
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"删除失败！");
        }
        return "redirect:/admin/types";
    }

    @RequestMapping("/types/{id}/input")
    public String toEditInput(@PathVariable Long id, Model model){
        Type type = typesService.getTypeById(id);
        type.setId(id);
        model.addAttribute(Contants.TYPE,type);
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id,Type type,RedirectAttributes attributes){
        Type t = typesService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute(Contants.MESSAGE, "不能添加重复的分类");
            return "redirect:/admin/types/{id}/input";
        }else {
            type.setId(id);
            boolean isOk = typesService.updateTypeById(type);
            if(isOk){
                attributes.addFlashAttribute(Contants.MESSAGE, "修改成功");
            }else{
                attributes.addFlashAttribute(Contants.MESSAGE, "修改失败");
            }
        }
        return "redirect:/admin/types";
    }

}
