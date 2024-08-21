package com.hdrick.jumpee.dto;

import java.util.List;

public class UserCreationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<RoleDTO> newRoles;

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

    public List<RoleDTO> getNewRoles() {
        return newRoles;
    }

    public void setNewRoles(List<RoleDTO> newRoles) {
        this.newRoles = newRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
