package com.example.spring.service;

import com.example.spring.config.ESProps;
import com.example.spring.constants.ESConstants;
import com.example.spring.model.Contact;
import com.example.spring.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    private final ESProps esProps;

    @Autowired
    public ContactService(ContactRepository contactRepository, ESProps esProps) {
        this.contactRepository = contactRepository;
        this.esProps = esProps;
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
        Contact savedContact = contactRepository.save(contact);
        isSaved = savedContact.getContactId() > 0;
        log.info(contact.toString());
        return isSaved;
    }


    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = esProps.getPageSize();
        if(esProps.getContact() != null && esProps.getContact().get("pageSize") != null) {
            pageSize = Integer.parseInt(esProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return contactRepository.findByStatusWithQuery(ESConstants.OPEN, pageable);
    }

    public boolean closeMessage(int contactId) {
        boolean isUpdated = false;
        int rowsAffected = contactRepository.updateMsgStatus(ESConstants.CLOSED, contactId);
        isUpdated = rowsAffected > 0;;
        return isUpdated;
    }
}
