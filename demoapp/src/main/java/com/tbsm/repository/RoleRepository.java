package com.tbsm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>{

}
