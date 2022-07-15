package com.example.spring.model;

import com.example.spring.annotations.FieldsValueMatch;
import com.example.spring.annotations.PasswordValidator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email confirmation must match email!"
        ),
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Password confirmation must match password!"
        ),

})
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a email address")
    private String email;

    @NotBlank(message = "Confirm Email must not be blank")
    @Email(message = "Please provide a email address")
    @Transient
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, message = "Password be at least 5 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message = "Confirm Password must not be blank")
    @Size(min = 6, message = "Confirm password be at least 5 characters long")
    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private ESClass esClass;
}
