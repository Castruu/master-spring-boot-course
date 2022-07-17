package com.example.spring.controller;

import com.example.spring.model.Contact;
import com.example.spring.model.Person;
import com.example.spring.repository.PersonRepository;
import com.example.spring.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class DashboardController {

    private final ContactService contactService;

    private final PersonRepository personRepository;

    @Autowired
    public DashboardController(ContactService contactService, PersonRepository personRepository) {
        this.contactService = contactService;
        this.personRepository = personRepository;
    }


    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession httpSession) {
        Person person = personRepository.findByEmail(authentication.getName());
        httpSession.setAttribute("loggedInPerson", person);
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(person.getEsClass() != null && person.getEsClass().getName() != null) {
            model.addAttribute("enrolledClass", person.getEsClass().getName());
        }
        logMessage();
        return "dashboard.html";
    }


    @RequestMapping(value = "closeMsg", method = RequestMethod.GET)
    public String closeMsg(@RequestParam int id) {
        contactService.closeMessage(id);
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
    }


    private void logMessage() {
        log.warn("WARN");
        log.info("INFO");
        log.error("ERROR");
        log.debug("DEBUG");
        log.trace("TRACE");
    }

}