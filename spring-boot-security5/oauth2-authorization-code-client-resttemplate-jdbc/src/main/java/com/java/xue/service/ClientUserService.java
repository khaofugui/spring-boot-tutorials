package com.java.xue.service;

import com.java.xue.dao.ClientUserRepositories;
import com.java.xue.domain.entity.ClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author xue.zeng
 * @date 2021/1/19 2:43 PM
 */
@Service
public class ClientUserService {

  @Autowired
  private ClientUserRepositories repositories;

  public void updateClientUser(ClientUser clientUser) {
    Optional<ClientUser> userById = repositories.findById(clientUser.getId());
    if (userById.isPresent()) {
      repositories.save(clientUser);
    }
  }
}
