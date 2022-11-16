package com.internship.assetmanagement.dtos.user;

import lombok.Data;

@Data
public class UserCreate {
    private String name;
    private String email;
    private String password;
}
