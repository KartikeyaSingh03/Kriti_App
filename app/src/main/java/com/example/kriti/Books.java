package com.example.kriti;

import android.net.Uri;

import java.net.URL;

public class Books {

    String bookOverview;
    String url;



    public String getBookOverview() {
        return bookOverview;
    }

    public String getUrl() {
        return url;
    }

    public Books( String bookOverview, String url) {

        this.bookOverview = bookOverview;
        this.url = url;
    }
}
