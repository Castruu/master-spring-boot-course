package com.example.spring.controller;


import com.example.spring.model.ESClass;
import com.example.spring.model.Person;
import com.example.spring.service.ESClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ESClassService classService;

    @Autowired
    public AdminController(ESClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/displayClasses")
    public ModelAndView displayClassesPage(Model model) {
        List<ESClass> classes = classService.getAllClasses();
        model.addAttribute("eazyClasses", classes);
        model.addAttribute("eazyClass", new ESClass());
        ModelAndView modelAndView = new ModelAndView("classes.html");
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("eazyClass") ESClass esClass) {
        classService.createNewClass(esClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;

    }

    @GetMapping("/deleteClass")
    public ModelAndView addNewClass(Model model, @RequestParam int id) {
        classService.removeStudentsAndDeleteClassById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudentsPage(Model model, @RequestParam int classId, HttpSession session, @RequestParam(name = "error", required = false) String error) {
        ESClass esClass = classService.getClassById(classId);
        String errorMessage;
        ModelAndView modelAndView = new ModelAndView("students.html");
        modelAndView.addObject("eazyClass", esClass);
        modelAndView.addObject("person", new Person());
        session.setAttribute("eazyClass", esClass);
        if(error != null) {
            errorMessage = "Invalid Email entered";
            modelAndView.addObject("errorMessage", errorMessage);

        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        ESClass esClass = (ESClass) session.getAttribute("eazyClass");
        modelAndView.setViewName(classService.addStudent(esClass, person));
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        ESClass esClass = (ESClass) session.getAttribute("eazyClass");
        esClass = classService.deleteStudent(esClass, personId);
        session.setAttribute("eazyClass", esClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + esClass.getClassId());
        return modelAndView;
    }

}
