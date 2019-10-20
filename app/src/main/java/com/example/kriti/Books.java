package com.example.kriti;

public class Books {
    String bookName;
    String bookOverview;
    String url;

    public String getBookName() {
        return bookName;
    }

    public String getBookOverview() {
        return bookOverview;
    }

    public String getUrl() {
        return url;
    }

    public Books(String bookName, String bookOverview, String url) {
        this.bookName = bookName;
        this.bookOverview = bookOverview;
        this.url = url;
    }
}
