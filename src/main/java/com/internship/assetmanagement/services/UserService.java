package com.internship.assetmanagement.services;

import com.internship.assetmanagement.dtos.AccessTokens;
import com.internship.assetmanagement.dtos.UserCreate;
import com.internship.assetmanagement.dtos.UserLogIn;
import com.internship.assetmanagement.entities.user.UserEntity;
import com.internship.assetmanagement.mappers.UserMapper;
import com.internship.assetmanagement.repositories.other.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class UserService {

    private static final String JWT_SECRET = "adasd12312eqwdqwdaqdasdasd";

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    public UserEntity addUser (UserCreate userCreate) {
        UserEntity userEntity = userMapper.userCreateToUserEntity(userCreate);
        return userRepository.save(userEntity);
    }

    public AccessTokens getToken (UserEntity userEntity) {
        // TODO: JWT_SECRET should not be used for both tokens, every token should have different secret key

        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", userEntity.getId());

        Date now = new Date(System.currentTimeMillis());
        Date accessTokenExp = new Date(now.getTime() + 900000);
        Date refreshTokenExp = new Date(now.getTime() + 259200000);

        var accessToken = Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setExpiration(accessTokenExp)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
                .setHeaderParam("typ", "JWT")
                .compact();

        var refreshToken = Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setExpiration(refreshTokenExp)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
                .setHeaderParam("typ", "JWT")
                .compact();

        return new AccessTokens(accessToken, refreshToken);
    }

    public AccessTokens logIn (UserLogIn userLogIn) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(userLogIn.getEmail());
        if(!userLogIn.getPassword().equals(userEntity.getPassword())) {
            throw new Exception("Unauthorised");
        }
        return getToken(userEntity);
    }

    public UserEntity getUser(String token) {
        var claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
                .parseClaimsJws(token)
                .getBody();
        var userId = (Integer) claims.get("uid");
        var optionalUser = userRepository.findById(userId);
        assert !optionalUser.isEmpty();
        return optionalUser.get();
    }

    public AccessTokens refresh(String refreshToken) {
        var user = getUser(refreshToken);
        return getToken(user);
    }
}
