package com.java.xue.util;

import com.java.xue.domain.entity.ClientUser;
import com.java.xue.service.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author xue.zeng
 * @date 2021/1/19 2:49 PM
 */
@Component
public class SecurityUtils {

  @Autowired
  private ClientUserService clientUserService;

  public UserDetails getCurrentUser(){
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    Object principal = authentication.getPrincipal();
    if(principal instanceof UserDetails){
      return (UserDetails) principal;
    }
    return null;
  }

  /**
   * 更新token
   */
  public void updateToken(String token){
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    Object principal = authentication.getPrincipal();
    if(principal instanceof ClientUser){
      ClientUser clientUser = (ClientUser) principal;
      clientUser.setAccessToken(token);
      clientUserService.updateClientUser(clientUser);
    }
  }
}

