package com.example.spring.controller;

import com.example.spring.model.Holiday;
import com.example.spring.service.HolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    private final HolidaysService holidaysService;

    @Autowired
    public HolidaysController(HolidaysService holidaysService) {
        this.holidaysService = holidaysService;
    }


    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable(required = false) String display, Model model) {
        if(null != display && display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if(null != display && display.equals("federal")) {
            model.addAttribute("federal", true);
        } else if(null != display && display.equals("festival")) {
            model.addAttribute("festival", true);

        }
        List<Holiday> holidays = holidaysService.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        for(Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";
    }

}
