package com.example.spring.repository;

import com.example.spring.model.Contact;
import com.example.spring.rowmappers.ContactRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Slf4j
public class ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(Contact contact) {
        final String query = "INSERT INTO contact_msg (NAME, MOBILE_NUM, EMAIL, SUBJECT, MESSAGE, STATUS, CREATED_AT, CREATED_BY) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(query, contact.getName(), contact.getMobileNum(),
                contact.getEmail(), contact.getSubject(), contact.getMessage(),
                contact.getStatus(), contact.getCreatedAt(), contact.getCreatedBy());
    }

    public List<Contact> findMsgsWithStatus(String status) {
        String query = "SELECT * FROM contact_msg WHERE status = ?;";
        return jdbcTemplate.query(query,
                preparedStatement -> preparedStatement.setString(1, status),
                new ContactRowMapper());
    }

    public int updateMessageStatus(int contactId, String status, String updatedBy) {
        String query = "UPDATE contact_msg SET status = ?, updated_at = ?, updated_by = ? WHERE contact_id = ?";
        return jdbcTemplate.update(query, ps -> {
            ps.setString(1, status);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(3, updatedBy);
            ps.setInt(4, contactId);
        });
    }
}
