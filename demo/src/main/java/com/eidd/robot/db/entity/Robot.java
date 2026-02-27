package com.eidd.robot.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Robot elements that define the objects to interact with
 */
@Entity
public class Robot {

    /**
     * Robot unique Id
     */
    @Id
    private Integer id;
    private Double x;
    private Double y;
    private Double theta;
    private Double v;
    private Double ultraSound;

    public Robot() {
    }

    public Robot(Integer id, Double x, Double y, Double theta, Double v, Double ultraSound) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.v = v;
        this.ultraSound = ultraSound;
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

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    public Double getUltraSound() {
        return ultraSound;
    }

    public void setUltraSound(Double ultraSound) {
        this.ultraSound = ultraSound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return id == robot.id && Objects.equals(x, robot.x) && Objects.equals(y, robot.y) && Objects.equals(theta, robot.theta) && Objects.equals(v, robot.v) && Objects.equals(ultraSound, robot.ultraSound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, theta, v, ultraSound);
    }

    @Override
    public String toString() {
        return "Robot{" + "id=" + id + ", x=" + x + ", y=" + y + ", theta=" + theta + ", v=" + v + ", ultraSound=" + ultraSound + '}';
    }
}
