package com.practice.practiceapp;


public class CardView_Item {

    String title;
    String writer;
    String date;
    String from;
    String url;

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public CardView_Item() {
        this.date = "DATE";
        this.title = "TITLE";
        this.writer = "WRITER";
        this.from = "FROM";
        this.url = "http://www.naver.com";
    }

    public void initialize() {
        this.date = "2016.10.25 ~ 2016.10.31";
        this.title = "TITLE HERE";
        this.writer = "HIGHFIVE";
        this.from = "KOREA";
    }
}
