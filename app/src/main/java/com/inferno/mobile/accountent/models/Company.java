package com.inferno.mobile.accountent.models;

import java.io.Serializable;

public class Company implements Serializable {
    private final int id;
    private final String name;
    private final int catCount;

    public int getCatCount() {
        return catCount;
    }

    public Company(int id, String name, int catCount) {
        this.id = id;
        this.name = name;
        this.catCount = catCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
