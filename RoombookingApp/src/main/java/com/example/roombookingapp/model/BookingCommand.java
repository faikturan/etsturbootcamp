package com.example.roombookingapp.model;

import com.example.roombookingapp.model.entities.Booking;
import com.example.roombookingapp.model.entities.Room;
import com.example.roombookingapp.model.entities.User;

import java.sql.Date;

public class BookingCommand {

    private Long id;
    private Room room;
    private User user;
    private Layout layout;
    private String title;
    private Date date;
    private String startTime;
    private String endTime;
    private Integer participants;

    public BookingCommand(Booking booking) {
        this.id = booking.getId();
        this.room = booking.getRoom();
        this.user = booking.getUser();
        this.layout = booking.getLayout();
        this.title = booking.getTitle();
        if(booking.getDate() != null)
        this.date = booking.getDate();
        if(booking.getStartTime() != null)
        this.startTime = booking.getStartTime().toString();
        if(booking.getEndTime() != null)
        this.endTime = booking.getEndTime().toString();
        this.participants = participants;
    }

    public BookingCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public Booking toBooking(){
        java.sql.Time xStartTime = java.sql.Time.valueOf(startTime + ":00");
        java.sql.Time xEndTime = java.sql.Time.valueOf(endTime + ":00");
        Booking booking =
                new Booking(room, user, layout, title, date, xStartTime, xEndTime, participants);
        booking.setId(id);
        return booking;
    }

}


