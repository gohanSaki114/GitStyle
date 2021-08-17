package com.unixsoftect.styleklub1;

import android.content.Context;

import java.util.ArrayList;

public class whishlistmodal {
Context context;
ArrayList<String> item ;
ArrayList<String> image;
ArrayList<Integer> price;
ArrayList<Integer> id;
ArrayList<String> size;
ArrayList<String> quantity;
    public whishlistmodal(whishlist whishlist, ArrayList<String> item, ArrayList<String> image, ArrayList<Integer> price, ArrayList<Integer> id, ArrayList<String> size, ArrayList<String> quantity) {
        this.context= whishlist;
        this.item= item;
        this.image= image;
        this.price= price;
        this.id= id;
        this.size= size;
        this.quantity= quantity;
    }

    public ArrayList<String> getItem() {
        return item;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public ArrayList<Integer> getPrice() {
        return price;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public ArrayList<String> getSize() {
        return size;
    }

    public ArrayList<String> getQuantity() {
        return quantity;
    }
}
