package com.eidd.mission.db.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissionTest {

    @Test
    public void testing_class() {
        // given
        int id = 1;
        double x = 0.0;
        double y = 0.0;
        double theta = 0.0;

        Mission mission = new Mission(id, x, y, theta);
        assertThat(mission.getId()).isEqualTo(id);
        assertThat(mission.getId()).isEqualTo(id);
        assertThat(mission.getId()).isEqualTo(id);
        assertThat(mission.getId()).isEqualTo(id);
    }
}