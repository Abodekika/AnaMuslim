package com.example.anamuslim.pojo.quran;

public class NewAyaLocationInPage {

    String page_number;
    String number_in_surah;
    String y;
    String marker_type;
    String x;

    public NewAyaLocationInPage(String page_number, String number_in_surah, String y, String marker_type, String x) {
        this.page_number = page_number;
        this.number_in_surah = number_in_surah;
        this.y = y;
        this.marker_type = marker_type;
        this.x = x;
    }

    public String getPage_number() {
        return page_number;
    }

    public void setPage_number(String page_number) {
        this.page_number = page_number;
    }

    public String getNumber_in_surah() {
        return number_in_surah;
    }

    public void setNumber_in_surah(String number_in_surah) {
        this.number_in_surah = number_in_surah;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getMarker_type() {
        return marker_type;
    }

    public void setMarker_type(String marker_type) {
        this.marker_type = marker_type;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
