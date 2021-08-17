package com.unixsoftect.styleklub1;

public class levelmodel {
    Integer icon;
    String title;
    boolean haschild;

    public levelmodel(Integer icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public levelmodel(Integer icon, String title, boolean haschild) {
        this.icon = icon;
        this.title = title;
        this.haschild = haschild;
    }

    public Integer getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
