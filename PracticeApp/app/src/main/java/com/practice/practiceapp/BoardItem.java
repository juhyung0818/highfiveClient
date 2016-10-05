package com.practice.practiceapp;


public class BoardItem {
    String title;
    String writer;
    String content;

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

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public BoardItem() {
        this.content = "I need help for touring Korea\naaaaaaaaaaaaaaa\naaaaa\naaaaaaa\naaaaaa\naaaaa\naa";
        this.title = "Testing title";
        this.writer = "OJHYJHKSSLYJCSH";
    }

    public BoardItem(String title, String writer, String content) {
        this.content = content;
        this.title = title;
        this.writer = writer;
    }
}
