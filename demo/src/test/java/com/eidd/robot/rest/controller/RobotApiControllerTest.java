package com.eidd.robot.rest.controller;

import com.eidd.mission.db.entity.Mission;
import com.eidd.mission.service.MissionService;
import com.eidd.robot.db.entity.Robot;
import com.eidd.robot.db.service.RobotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RobotApiControllerTest {
    @Mock
    RobotService robotService;

    @InjectMocks
    RobotApiController robotApiController;

    @Test
    void robotGet() {
    }

    @Test
    void robotIdDelete() {
    }

    @Test
    void robotIdGet() {
    }

    @Test
    void robotIdPut() {
    }

    @Test
    void robotPost() {
    }
}