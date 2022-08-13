package com.littlecow.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DataController {
    @RequestMapping("/data")
    public String toDataPage() {
        return "admin/data";
    }
}
