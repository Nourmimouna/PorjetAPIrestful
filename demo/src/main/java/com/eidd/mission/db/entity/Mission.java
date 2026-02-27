package com.eidd.mission.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Mission {

    /**
     * Robot unique Id
     */
    @Id
    private Integer id;
    private Double x;
    private Double y;
    private Double theta;

    public Mission() {
    }

    public Mission(Integer id, Double x, Double y, Double theta) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getTheta() {
        return theta;
    }

    public void setTheta(Double theta) {
        this.theta = theta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return Objects.equals(id, mission.id) && Objects.equals(x, mission.x) && Objects.equals(y, mission.y) && Objects.equals(theta, mission.theta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, theta);
    }

    @Override
    public String toString() {
        return "Mission{" + "id=" + id + ", x=" + x + ", y=" + y + ", theta=" + theta + '}';
    }
}
