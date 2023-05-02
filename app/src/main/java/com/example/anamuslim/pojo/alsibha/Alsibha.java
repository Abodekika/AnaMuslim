package com.example.anamuslim.pojo.alsibha;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "alsibha")
public class Alsibha {

    @PrimaryKey(autoGenerate = false)
    private int id;

    @ColumnInfo
    private String text;
    @ColumnInfo
    private int max;
    @ColumnInfo
    private int total;
    @ColumnInfo
    private int current_7;
    @ColumnInfo
    private int current_33;
    @ColumnInfo
    private int current_100;
    @ColumnInfo
    private int current_c;
    @ColumnInfo
    private int position;
    @ColumnInfo
    private int current;

    public Alsibha(int id, String text, int max, int total, int current_7, int current_33, int current_100, int current_c, int position, int current) {
        this.id = id;
        this.text = text;
        this.max = max;
        this.total = total;
        this.current_7 = current_7;
        this.current_33 = current_33;
        this.current_100 = current_100;
        this.current_c = current_c;
        this.position = position;
        this.current = current;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent_7() {
        return current_7;
    }

    public void setCurrent_7(int current_7) {
        this.current_7 = current_7;
    }

    public int getCurrent_33() {
        return current_33;
    }

    public void setCurrent_33(int current_33) {
        this.current_33 = current_33;
    }

    public int getCurrent_100() {
        return current_100;
    }

    public void setCurrent_100(int current_100) {
        this.current_100 = current_100;
    }

    public int getCurrent_c() {
        return current_c;
    }

    public void setCurrent_c(int current_c) {
        this.current_c = current_c;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
