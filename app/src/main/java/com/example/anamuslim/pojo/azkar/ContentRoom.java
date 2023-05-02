package com.example.anamuslim.pojo.azkar;


import androidx.room.TypeConverters;


import com.example.anamuslim.utils.Converter;

import java.io.Serializable;

@TypeConverters({Converter.class})
public class ContentRoom implements Serializable {

    public ContentRoom(int ID, String text, String count, String fadl, String source, int counter, String AUDIO) {
        this.text = text;
        this.count = count;
        this.fadl = fadl;
        this.source = source;
        this.counter = counter;
        this.AUDIO = AUDIO;
        this.ID = ID;
    }

    public String text;
    public String count;
    public String fadl;
    public String source;
    public int counter;
    public String AUDIO;
    public int ID;

    public int getCounter() {
        return counter;
    }

    public String getAUDIO() {
        return AUDIO;
    }

    public void setAUDIO(String AUDIO) {
        this.AUDIO = AUDIO;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFadl() {
        return fadl;
    }

    public void setFadl(String fadl) {
        this.fadl = fadl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}
