package com.example.spring.controller;

import com.example.spring.model.Address;
import com.example.spring.model.Person;
import com.example.spring.model.Profile;
import com.example.spring.repository.PersonRepository;
import com.example.spring.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class ProfileController {

    private final PersonRepository personRepository;

    @Autowired
    public ProfileController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/displayProfile")
    public ModelAndView displayProfilePage(Model model, HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        Profile profile = getProfileFromPerson(person);
        ModelAndView modelAndView = new ModelAndView("profile.html");
        model.addAttribute("profile", profile);
        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession session) {
        if(errors.hasErrors()) {
            return "profile.html";
        }
        Person person = (Person) session.getAttribute("loggedInPerson");
        updatePersonFromProfile(person, profile);
        personRepository.save(person);
        session.setAttribute("loggedInPerson", person);
        return "redirect:/displayProfile";
    }

    private Profile getProfileFromPerson(Person person) {
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());
        if(person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        return profile;
    }

    private void updatePersonFromProfile(Person person, Profile profile) {
        person.setName(profile.getName());
        person.setMobileNumber(profile.getMobileNumber());
        person.setEmail(profile.getEmail());
        if(person.getAddress() == null || person.getAddress().getAddressId() <= 0) {
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());
    }

}
