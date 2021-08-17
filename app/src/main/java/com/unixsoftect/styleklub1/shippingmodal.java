package com.unixsoftect.styleklub1;

import java.util.ArrayList;

public class shippingmodal
{
   String user;
  ArrayList<String> address;
    public shippingmodal(String user, ArrayList<String> address) {
        this.user= user;
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public ArrayList<String> getAddress() {
        return address;
    }
}
