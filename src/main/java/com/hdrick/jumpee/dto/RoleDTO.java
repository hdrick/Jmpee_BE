package com.hdrick.jumpee.dto;

public class RoleDTO {
    private long id;
    private String name; // Optional if you need the role name

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
