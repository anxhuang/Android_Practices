package com.example.android.lab11_listview;

public class Pokemon {
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
