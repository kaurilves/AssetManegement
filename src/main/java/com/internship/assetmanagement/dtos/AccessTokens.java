package com.internship.assetmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessTokens {
    private String accessToken;
    private String refreshToken;
}
