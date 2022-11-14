package com.internship.assetmanagement.services;

import com.internship.assetmanagement.entities.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class AccessManager {

    private final HttpServletRequest request;

    public UserEntity getLoggedInUser() {
        return (UserEntity) request.getAttribute("user");
    }

}
