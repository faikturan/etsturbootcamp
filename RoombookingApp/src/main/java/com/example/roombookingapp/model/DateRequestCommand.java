package com.example.roombookingapp.model;

import java.util.Date;

public class DateRequestCommand {
    private Date date;

    public DateRequestCommand() {
    }

    public DateRequestCommand(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
