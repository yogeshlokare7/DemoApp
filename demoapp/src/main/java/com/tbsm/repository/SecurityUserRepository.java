package com.tbsm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.SecurityUser;

@Repository
public interface SecurityUserRepository extends PagingAndSortingRepository<SecurityUser, Long>{

}
