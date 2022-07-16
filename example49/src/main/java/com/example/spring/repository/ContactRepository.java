package com.example.spring.repository;

import com.example.spring.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    //@Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true)
    @Query("SELECT c FROM Contact c where c.status = :status")
    Page<Contact> findByStatusWithQuery(/*@Param("status")*/ String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int contactId);

    Page<Contact> findOpenMessages(/*@Param("status")*/ String status, Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgStatus(String status, int contactId);

//    @Query(nativeQuery = true)
//    Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);
//
//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true)
//    int updateMsgStatusNative(String status, int id);

}
