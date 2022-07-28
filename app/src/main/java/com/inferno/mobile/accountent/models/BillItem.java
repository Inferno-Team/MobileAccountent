package com.inferno.mobile.accountent.models;

public class BillItem {
    private final int count;
    private final Category category;

    public BillItem(int count, Category category) {
        this.count = count;
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public Category getCategory() {
        return category;
    }
}
