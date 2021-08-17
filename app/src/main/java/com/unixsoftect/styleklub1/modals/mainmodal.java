package com.unixsoftect.styleklub1.modals;

import java.util.List;

public class mainmodal {
    List<String> category;
    List<Integer> price;
    List<String> item;
    List<String> description;
    List<String> type;
    List<Integer> id;
    List<String> image;

    public mainmodal(List<String> category, List<Integer> price, List<String> item, List<String> description, List<String> type, List<Integer> id, List<String> image) {
        this.category = category;
        this.price = price;
        this.item = item;
        this.description = description;
        this.type = type;
        this.id = id;
        this.image = image;
    }

    public List<String> getCategory() {
        return category;
    }

    public List<Integer> getPrice() {
        return price;

    }

    public List<String> getItem() {
        return item;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getType() {
        return type;
    }

    public List<Integer> getId() {
        return id;
    }

    public List<String> getImage() {
        return image;
    }
}
