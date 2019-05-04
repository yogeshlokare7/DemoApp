package com.tbsm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.Resident;

@Repository
public interface ResidentRepository extends PagingAndSortingRepository<Resident, Long>{

}
