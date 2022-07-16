package com.example.spring.rest;


import com.example.spring.constants.ESConstants;
import com.example.spring.model.Contact;
import com.example.spring.model.Response;
import com.example.spring.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController // @Controller + @ResponseBody in all methods
@RequestMapping(value = "/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
public class ContactRestController {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/getMessageByStatus")
    //@ResponseBody
    public List<Contact> getMessageByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMessagesByStatus")
    //@ResponseBody
    public List<Contact> getMessageByStatusWithRequestBody(@RequestBody Contact contact) {
        if(contact == null || contact.getStatus() == null) return List.of();
        return contactRepository.findByStatus(contact.getStatus());
    }

    @PostMapping("/saveMessage")
    public ResponseEntity<Response> saveMessage(@RequestHeader("invocationFrom") String invocationFrom,
                                                @Valid @RequestBody Contact contact) {
        log.info("Header invocationFrom = " + invocationFrom);
        Response response = new Response();
        response.setStatusCode(200);
        response.setStatusMessage("Message created successfully");
        contactRepository.save(contact);
        return ResponseEntity
                .status(200)
                .header("isMessageSaved", "true")
                .body(response);

    }

    @DeleteMapping("/deleteMessage")
    public ResponseEntity<Response> deleteMessage(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info("Header " + key + " " + value);
        });
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response(200, "Message deleted");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/closeMessage")
    public ResponseEntity<Response> closeMessage(@RequestBody Contact contactRequest) {
        Response response;
        Optional<Contact> contact = contactRepository.findById(contactRequest.getContactId());
        if(contact.isEmpty()) {
            response = new Response(404, "Invalid Contact ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        contact.get().setStatus(ESConstants.CLOSED);
        contactRepository.save(contact.get());
        response = new Response(200, "Message closed");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
