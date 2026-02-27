package com.eidd.robot.rest.controller;

import com.eidd.robot.db.entity.Robot;
import com.eidd.robot.db.service.StdRobotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RobotApiControllerTest {

    @Mock
    StdRobotService robotService;

    RobotApiController robotApiController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        robotApiController = new RobotApiController(new ObjectMapper(), new MockHttpServletRequest());
        ReflectionTestUtils.setField(robotApiController, "robotService", robotService);
        mockMvc = MockMvcBuilders.standaloneSetup(robotApiController).build();
    }

    @Test
    void robotGet() throws Exception {
        when(robotService.getAll()).thenReturn(Arrays.asList(
                new Robot(1, 0.0, 0.0, 0.0, 1.0, 2.0),
                new Robot(2, 5.0, 10.0, 90.0, 0.5, 3.0)
        ));

        mockMvc.perform(get("/robot").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void robotIdDelete() throws Exception {
        doNothing().when(robotService).delete(1);

        mockMvc.perform(delete("/robot/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void robotIdGet() throws Exception {
        when(robotService.find(1)).thenReturn(Optional.of(new Robot(1, 0.0, 0.0, 0.0, 1.0, 2.0)));

        mockMvc.perform(get("/robot/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void robotIdPut() throws Exception {
        Robot updated = new Robot(1, 1.0, 10.0, 1.0, 1.0, 100.0);
        when(robotService.update(any(Robot.class))).thenReturn(updated);

        mockMvc.perform(put("/robot/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void robotPost() throws Exception {
        Robot robot = new Robot(1, 0.0, 0.0, 0.0, 1.0, 2.0);
        when(robotService.create(any(Robot.class))).thenReturn(robot);

        mockMvc.perform(post("/robot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(robot)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}