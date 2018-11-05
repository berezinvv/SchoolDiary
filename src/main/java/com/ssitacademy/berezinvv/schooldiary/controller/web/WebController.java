package com.ssitacademy.berezinvv.schooldiary.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/")
    public String homepage() {
        return "index";
    }

    @GetMapping(value = "/employeeedit")
    public String employeeEdit() {
        return "employeeEdit";
    }

    @GetMapping(value = "/schooledit")
    public String schoolEdit() {
        return "schoolEdit";
    }
}
