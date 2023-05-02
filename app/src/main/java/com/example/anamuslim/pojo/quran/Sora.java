package com.example.anamuslim.pojo.quran;

import androidx.room.Entity;


@Entity(tableName = "quran")
public class Sora {
    int soraNumber;
     int startPage;
    int endPage;

    String arabicName;

    public Sora(int soraNumber, int startPage, int endPage, String arabicName) {
        this.soraNumber = soraNumber;
        this.startPage = startPage;
        this.endPage = endPage;
        this.arabicName = arabicName;
    }

    public int getSoraNumber() {
        return soraNumber;
    }

    public void setSoraNumber(int soraNumber) {
        this.soraNumber = soraNumber;
    }

    public  int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }
}
