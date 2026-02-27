package com.eidd.mission.service;

import com.eidd.mission.db.repository.MissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MissionServiceTest {

    @InjectMocks
    private final MissionService missionService = new MissionService();
    @Mock
    MissionRepository missionRepository;

    @Test
    void getAll() {
        //verify(missionRepository).findAll();

    }

    @Test
    void toTest() {
        //Mockito.doReturn(new Mission()).when(missionRepository);
        //assertThat(mission).isNotNull();
        //assertThat(true).isEqualTo(true);
    }

    @Test
    void get_all_should_catch_exception() {
        //doThrow(RobotException.class).when(missionRepository).findAll();
        //assertThatCode(missionService::getAll).isInstanceOf(RobotException.class);
    }

    @Test
    void create() {
    }

    @Test
    void getMission() {
    }
}