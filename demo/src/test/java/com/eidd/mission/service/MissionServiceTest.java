package com.eidd.mission.service;

import com.eidd.exceptions.RobotException;
import com.eidd.mission.db.entity.Mission;
import com.eidd.mission.db.repository.MissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.PessimisticLockingFailureException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MissionServiceTest {

    @InjectMocks
    private MissionService missionService;  // pas de = new MissionService()

    @Mock
    MissionRepository missionRepository;

    @Test
    void getAll() {
        when(missionRepository.findAll()).thenReturn(Arrays.asList(
                new Mission(1, 0.0, 0.0, 0.0),
                new Mission(2, 0.0, 10.0, 0.0)
        ));

        List<Mission> result = missionService.getAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1);
        verify(missionRepository, times(1)).findAll();
    }

    @Test
    void toTest() {
        Mission mission = new Mission(1, 0.0, 0.0, 0.0);
        assertThat(mission).isNotNull();
        assertThat(mission.getId()).isEqualTo(1);
    }

    @Test
    void get_all_should_catch_exception() {
        doThrow(new PessimisticLockingFailureException("DB lock"))
                .when(missionRepository).findAll();
        assertThatCode(() -> missionService.getAll())
                .isInstanceOf(RobotException.class);
    }

    @Test
    void create() {
        Mission mission = new Mission(1, 0.0, 0.0, 0.0);
        when(missionRepository.save(any(Mission.class))).thenReturn(mission);

        Mission result = missionService.create(mission);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        verify(missionRepository, times(1)).save(mission);
    }

    @Test
    void getMission() {
        when(missionRepository.findAll()).thenReturn(Arrays.asList(
                new Mission(1, 0.0, 0.0, 0.0),
                new Mission(2, 0.0, 10.0, 0.0),
                new Mission(3, 10.0, 10.0, -90.0)
        ));
        missionService.createMissionMap();

        Mission first  = missionService.getMission();
        Mission second = missionService.getMission();
        Mission third  = missionService.getMission();
        Mission cycled = missionService.getMission();

        assertThat(first.getId()).isEqualTo(1);
        assertThat(second.getId()).isEqualTo(2);
        assertThat(third.getId()).isEqualTo(3);
        assertThat(cycled.getId()).isEqualTo(1);
    }
}