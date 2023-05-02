package com.example.anamuslim.pojo.azkar;

import java.io.Serializable;
import java.util.List;

public class FavItem implements Serializable {

    String id;
    private String title;
    private String count;
    public String bookmark;
    List<ContentRoom> content;




    public FavItem(String id, String title, String count, String bookmark, List<ContentRoom> content) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.bookmark = bookmark;
        this.content = content;
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


}
