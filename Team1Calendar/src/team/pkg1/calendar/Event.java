/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.pkg1.calendar;

/**
 *
 * @author JR
 */
public class Event {
    public Event(String eDescription, int eMonth, int eDay, int eYear, int eTime, int eID) {
        this.eventDescription = eDescription;
        this.eventMonth = eMonth;
        this.eventDay = eDay;
        this.eventYear = eYear;
        this.eventTime = eTime;
        this.eventID = eID;
    }
    
    private String eventDescription;
    private int eventMonth;
    private int eventDay;
    private int eventYear;
    private int eventTime;
    private int eventID;

    public String getEventDescription() {
        return eventDescription;
    }

    public int getEventMonth() {
        return eventMonth;
    }

    public int getEventDay() {
        return eventDay;
    }

    public int getEventYear() {
        return eventYear;
    }

    public int getEventTime() {
        return eventTime;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventMonth(int eventMonth) {
        this.eventMonth = eventMonth;
    }

    public void setEventDay(int eventDay) {
        this.eventDay = eventDay;
    }

    public void setEventYear(int eventYear) {
        this.eventYear = eventYear;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }
    
    
}
