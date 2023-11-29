package com.umesh.Dto;

public class LoginResponseDto {
    private String username;
    private String type;
    private String name;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
     public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LoginResponseDto() {
    }
}
