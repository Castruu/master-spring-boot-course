package com.rest.spring.controller;

import com.rest.spring.model.Contact;
import com.rest.spring.model.Response;
import com.rest.spring.proxy.ContactProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class EndPointController {

    private final ContactProxy proxy;

    private final RestTemplate restTemplate;

    private final WebClient webClient;

    @Autowired
    public EndPointController(ContactProxy proxy, RestTemplate restTemplate, WebClient webClient) {
        this.proxy = proxy;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @GetMapping("/contact")
    public ResponseEntity<List<Contact>> getMessages(@RequestParam("status") String status) {
        return ResponseEntity.ok(proxy.getMessageByStatus(status));
    }

    @PostMapping("/saveMessage")
    public ResponseEntity<Response> saveMessageWithRestTemplate(@RequestBody Contact contact) {
        String uri = "http://localhost:8080/api/contact/saveMessage";
        HttpHeaders headers = new HttpHeaders();
        headers.add("invocationFrom", "RestTemplate");
        HttpEntity<Contact> httpEntity = new HttpEntity<>(contact, headers);
        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Response.class);
    }

    @PostMapping("/saveMessageWebClient")
    public Mono<Response> saveMessageWithWebClient(@RequestBody Contact contact) {
        String uri = "http://localhost:8080/api/contact/saveMessage";
        return webClient.post().uri(uri).
                header("invocationFrom", "WebClient")
                .body(Mono.just(contact), Contact.class)
                .retrieve()
                .bodyToMono(Response.class);
    }

}
