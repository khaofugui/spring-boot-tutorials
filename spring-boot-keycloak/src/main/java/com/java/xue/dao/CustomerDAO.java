package com.java.xue.dao;

import com.java.xue.domian.model.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xue.zeng
 * @date 2021/1/13 2:46 PM
 */
public interface CustomerDAO extends CrudRepository<Customer, Long> {
}
