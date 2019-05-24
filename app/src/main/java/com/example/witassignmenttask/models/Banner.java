package com.example.witassignmenttask.models;

import java.util.ArrayList;

public class Banner {

    private boolean success;
    private String message;
    private ArrayList<DataBanner> data;

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

    public ArrayList<DataBanner> getData() {
        return data;
    }

    public void setData(ArrayList<DataBanner> data) {
        this.data = data;
    }
}
