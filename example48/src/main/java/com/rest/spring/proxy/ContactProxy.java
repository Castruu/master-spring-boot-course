package com.rest.spring.proxy;

import com.rest.spring.config.ProjectConfiguration;
import com.rest.spring.model.Contact;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name= "contact", configuration = ProjectConfiguration.class, url = "http://localhost:8080/api/contact")
public interface ContactProxy {

    @GetMapping("/getMessageByStatus")
    @Headers(
            value = "Content-Type: application/json"
    )
    List<Contact> getMessageByStatus(@RequestParam("status") String status);

}
