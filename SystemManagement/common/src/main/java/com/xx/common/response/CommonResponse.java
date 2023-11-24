package com.xx.common.response;

public class CommonResponse<T> {
    private int status;
    private T data;
    private boolean flag;

    // Constructors
    public CommonResponse() {
    }

    public CommonResponse(int status, T data, boolean flag) {
        this.status = status;
        this.data = data;
        this.flag = flag;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
