package com.tbsm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
