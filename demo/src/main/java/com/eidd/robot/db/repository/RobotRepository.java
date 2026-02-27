package com.eidd.robot.db.repository;

import com.eidd.robot.db.entity.Robot;
import org.springframework.data.repository.CrudRepository;

public interface RobotRepository extends CrudRepository<Robot, Integer> {
}
