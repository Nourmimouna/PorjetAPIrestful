package com.eidd.exceptions;

public class RobotException extends RuntimeException {

    protected String detail;

    public RobotException(String message) {
        super(message);
    }

    public RobotException(String message, Throwable cause) {
        super(message, cause);
    }

    public RobotException(String message, String detail) {
        super(message);
        this.detail = detail;
    }

    public RobotException(String message, Throwable cause, String detail) {
        super(message, cause);
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

}
