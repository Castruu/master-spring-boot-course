package com.rest.spring.model;


import lombok.Data;

@Data
public class Contact extends BaseEntity {



    private int contactId;


    private String name;

    private String mobileNum;

    private String email;

    private String subject;

    private String message;

    private String status;

}
