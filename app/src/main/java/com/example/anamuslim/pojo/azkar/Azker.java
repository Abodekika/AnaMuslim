package com.example.anamuslim.pojo.azkar;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.example.anamuslim.utils.Converter;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "Azkar_table")
@TypeConverters(Converter.class)
public class Azker implements Serializable {



    @PrimaryKey(autoGenerate = true)
    int id2;
    String id;

    public String title;

    public String count;
    public String bookmark;
   List<ContentRoom> content;

    public Azker(String id, String title, String count, String bookmark, List<ContentRoom> content
    ) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.bookmark = bookmark;
        this.content = content;

    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public List<ContentRoom> getContent() {
        return content;
    }

    public void setContent(List<ContentRoom> content) {
        this.content = content;
    }
}
