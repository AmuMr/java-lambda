package com.example.javalambda.exception;

public class TestException extends RuntimeException {

    private String code;

    public TestException(String message) {
        super(message);
    }

    public TestException(String code, Throwable throwable) {
        super(code, throwable);
        setCode(code);
    }

    public TestException(String code, String message) {
        super(message);
        setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
