package com.csipl.lpg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csipl.lpg.model.LatterPad;

@Repository
public interface LatterPadRepository extends CrudRepository<LatterPad, Long>{

}
