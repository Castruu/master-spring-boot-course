package com.example.spring.service;

import com.example.spring.model.Holiday;
import com.example.spring.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HolidaysService {

    private final HolidaysRepository holidaysRepository;

    @Autowired
    public HolidaysService(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;
    }

    public List<Holiday> findAllHolidays() {
        Iterable<Holiday> holidayIterator = holidaysRepository.findAll();
        return StreamSupport.stream(holidayIterator.spliterator(), false).collect(Collectors.toList());
    }

}
