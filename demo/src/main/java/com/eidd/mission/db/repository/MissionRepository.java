package com.eidd.mission.db.repository;

import com.eidd.mission.db.entity.Mission;
import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Integer> {

}
