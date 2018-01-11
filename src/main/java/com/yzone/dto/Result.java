package com.yzone.dto;

public class Result<T> {

    private boolean success;

    private String message;

    private T data;

    public Result(boolean success, T data) {

        this.success = success;
        this.data = data;
        this.message = "";
    }

    public Result(boolean success, String message) {

        this.success = success;
        this.message = message;
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
