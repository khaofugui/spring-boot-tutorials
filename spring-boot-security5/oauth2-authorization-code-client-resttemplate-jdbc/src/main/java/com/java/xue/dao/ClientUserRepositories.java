package com.java.xue.dao;

import com.java.xue.domain.entity.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xue.zeng
 * @date 2021/1/19 2:36 PM
 */
public interface ClientUserRepositories extends JpaRepository<ClientUser, Long> {
  ClientUser findOneByUsername(String username);
}
