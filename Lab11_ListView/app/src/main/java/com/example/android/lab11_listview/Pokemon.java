package com.example.android.lab11_listview;

import java.io.Serializable;

public class Pokemon implements Serializable{ //要用intent傳遞要實作這個序列化

    private String id;
    private String name;
    private int drawableId;

    public Pokemon(String id, String name, int drawableId) {
        this.id = id;
        this.name = name;
        this.drawableId = drawableId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDrawableId() {
        return drawableId;
    }

}
