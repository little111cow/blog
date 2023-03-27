package com.littlecow.blog.controller;

import com.littlecow.blog.entity.Data;
import com.littlecow.blog.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class DataController {
    @Resource
    private DataService dataService;

    @RequestMapping("/data")
    public String toDataPage(Model model) {
        Data data = dataService.getData();
        model.addAttribute("Data", data);
        return "admin/data";
    }
}
