package com.java.xue.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class SubjectAttributeUserTokenConverter extends DefaultUserAuthenticationConverter {
  @Override
  public Map<String, ?> convertUserAuthentication(Authentication authentication) {
    Map<String, Object> response = new LinkedHashMap<>();
    response.put("sub", authentication.getName());
    if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
      response.put(UserAuthenticationConverter.AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
    }
    return response;
  }
}
