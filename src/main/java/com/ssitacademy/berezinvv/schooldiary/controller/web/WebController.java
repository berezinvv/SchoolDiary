package com.ssitacademy.berezinvv.schooldiary.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/")
    public String homepage() {
        return "index";
    }

    @GetMapping(value = "/employee-edit")
    public String employeeEdit() {
        return "employeeEdit";
    }

    @GetMapping(value = "/school-edit")
    public String schoolEdit() {
        return "schoolEdit";
    }

    @GetMapping(value = "/lesson-edit")
    public String lessonEdit() {
        return "lessonEdit";
    }
    @GetMapping(value = "/pupil-edit")
    public String pupilEdit() {
        return "pupilEdit";
    }
    @GetMapping(value = "/class-group-edit")
    public String classGroupEdit() {
        return "classGroupEdit";
    }
    @GetMapping(value = "/marks")
    public String marksEdit() {
        return "marks";
    }
    @GetMapping(value = "/marks-avg")
    public String marksAvgEdit() {
        return "marksAvg";
    }
    @GetMapping(value = "/class-groups")
    public String allClassGroups() {
        return "class-groups";
    }
    @GetMapping(value = "/schedules")
    public String schedules() {
        return "schedules";
    }
    @GetMapping(value = "/schedule-edit")
    public String scheduleEdit() {
        return "scheduleEdit";
    }
}
