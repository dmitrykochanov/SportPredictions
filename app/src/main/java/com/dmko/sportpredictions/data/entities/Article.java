package com.dmko.sportpredictions.data.entities;

public class Article {

    private String header;
    private String text;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Article{" +
                "header='" + header + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
