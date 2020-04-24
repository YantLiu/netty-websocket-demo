package com.lyt.exception;

public class MsgException extends RuntimeException {
    private int code = 501;

    public MsgException(String message) {
        super(message);
    }

    public MsgException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
