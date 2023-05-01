package Entities;

import java.util.Date;

public class Event {

    private int id;
    private String title;
    private Date date;




    public Event() {
    }

    public Event(String title, Date date) {
        this.title=title;
        this.date = date;
    }

    public Event(int id, String title, java.sql.Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
