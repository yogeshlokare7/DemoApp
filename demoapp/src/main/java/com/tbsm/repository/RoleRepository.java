package com.tbsm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
