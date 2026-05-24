package com.foodhunt.auth_service.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtAudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final String audience = "https://foodhunt-api";

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {

        if(token.getAudience().contains(audience)){
            return OAuth2TokenValidatorResult.success();
        }
        OAuth2Error error=
                new OAuth2Error
                        ("invalid_token","Invalid audience",null);

        return OAuth2TokenValidatorResult.failure(error);

    }
}
