package com.example.spring.service;

import com.example.spring.constants.ESConstants;
import com.example.spring.model.Contact;
import com.example.spring.repository.ContactRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
        log.info("Contact Service Initialized");
    }

    /**
     * Save Contact Details into DB
     * @param contact Contact received from form submit
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(ESConstants.OPEN);
        contact.setCreatedBy(ESConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
        isSaved = savedContact.getContactId() > 0;
        log.info(contact.toString());
        return isSaved;
    }


    public List<Contact> findMsgsWithOpenStatus() {
        return contactRepository.findByStatus(ESConstants.OPEN);
    }

    public boolean closeMessage(int contactId, String updatedBy) {
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(c -> {
            c.setStatus(ESConstants.CLOSED);
            c.setUpdatedBy(updatedBy);
            c.setUpdatedAt(LocalDateTime.now());
        });
        Contact savedContact = contactRepository.save(contact.orElseThrow());
        isUpdated = savedContact.getContactId() > 0;;
        return isUpdated;
    }
}
