package com.rest.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {


    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnore
    private String updatedBy;


}
