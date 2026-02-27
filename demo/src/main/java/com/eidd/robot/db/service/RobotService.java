package com.eidd.robot.db.service;

import com.eidd.exceptions.RobotException;
import com.eidd.robot.db.entity.Robot;

import java.util.Optional;

/**
 * Service that manages Robot read from and write to database.
 */
public interface RobotService {
    /**
     * Get all Robots.
     */
    Iterable<Robot> getAll() throws RobotException;

    /**
     * Find the Robot which has the Id.
     */
    Optional<Robot> find(Integer id) throws RobotException;

    /**
     * Delete the Robot which has the Id and send a RobotDeleted event.
     *
     * @throws RobotException when publish failed
     */
    void delete(Integer id) throws RobotException;

    /**
     * Create a Robot in DB. The Id must be set.
     *
     * @return the Robot with version set.
     * @throws RobotException when publish failed
     */
    Robot create(Robot robot) throws RobotException;

    /**
     * Update the Robot which has the Id and send a RobotUpdated event. The version is incremented.
     *
     * @throws RobotException when publish failed
     */
    Robot update(Robot robot) throws RobotException;

}
