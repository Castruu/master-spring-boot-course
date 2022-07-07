package com.example.spring.repository;

import com.example.spring.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidaysRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HolidaysRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHolidays() {
        String query = "SELECT * FROM holidays;";
        RowMapper<Holiday> holidayRowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return jdbcTemplate.query(query, holidayRowMapper);
    }
}
