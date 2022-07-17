package com.example.spring.controller;

import com.example.spring.config.ESProps;
import com.example.spring.model.Person;
import com.example.spring.repository.PersonRepository;
import com.example.spring.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashboardController {

     //Fetching values from application.propperties using @Value
    @Value("${eazyschool.contact.successMessage}")
    private String successMessage;
    @Value("${eazyschool.pageSize}")
    private int pageSize;

    private final ContactService contactService;

    private final PersonRepository personRepository;

    private final Environment environment;


    @Autowired
    public DashboardController(ContactService contactService, PersonRepository personRepository, Environment environment) {
        this.contactService = contactService;
        this.personRepository = personRepository;
        this.environment = environment;
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

        log.error("defaultPageSize with @Value = " + pageSize);
        log.error("successMessage with @Value = " + successMessage);

        log.error("defaultPageSize with Environment = " + environment.getProperty("eazyschool.pageSize"));
        log.error("successMessage with Environment  = " + environment.getProperty("eazyschool.contact.successMessage"));
        log.error("Java Home with Environment = " + environment.getProperty("JAVA_HOME"));

    }


}