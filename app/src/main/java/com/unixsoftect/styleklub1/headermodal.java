package com.unixsoftect.styleklub1;

import android.graphics.drawable.Drawable;

public class headermodal {
    public String menuName;
    public Drawable icon;
    public boolean isGroup, hasChildren;

    public headermodal(String menuName, Drawable icon, boolean isGroup, boolean hasChildren) {
        this.menuName = menuName;
        this.icon = icon;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }
}
