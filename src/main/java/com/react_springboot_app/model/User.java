package com.react_springboot_app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @UuidGenerator
    @Schema(
            description = "User ID",
            name = "id",
            type = "UUID",
            example = "123e4567-e89b-42d3-a456-556642440000")
    private UUID id;

    @Column(name = "first_name", nullable = false)
    @Schema(
            description = "User First Name",
            name = "firstName",
            type = "String",
            example = "john")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Schema(
            description = "User List Name",
            name = "lastName",
            type = "String",
            example = "dale")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @Schema(
            description = "User Email",
            name = "email",
            type = "String",
            example = "john@gmail.com")
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    @Schema(
            description = "User Username",
            name = "username",
            type = "String",
            example = "johndale10")
    private String username;

    // CONSTRUCTORS
    public User() {
    }

    public User(UUID id, String firstName, String lastName, String email, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }

    // GETTER - SETTER
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
