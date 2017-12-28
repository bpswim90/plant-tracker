package com.williampaulsen.planttracker.models.data;

import com.williampaulsen.planttracker.models.PlantType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PlantTypeDao extends CrudRepository<PlantType, Integer> {
}
