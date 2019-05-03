package com.tbsm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.SocietyMaster;

@Repository
public interface SocietyRepository extends PagingAndSortingRepository<SocietyMaster, Long>{

}
