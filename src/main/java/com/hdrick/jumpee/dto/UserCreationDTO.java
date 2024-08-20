package com.hdrick.jumpee.dto;

import java.util.List;

public class UserCreationDTO {
    private String firstName;
    private String lastName;
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
}
