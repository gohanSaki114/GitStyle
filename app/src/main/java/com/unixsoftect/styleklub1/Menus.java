package com.unixsoftect.styleklub1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Menus {
    String data;
    JSONArray jsonArray;

    public Menus(String data) {
        this.data = data;
        try {
            String2JSON(this.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void String2JSON(String data) throws Exception {
        jsonArray = new JSONArray(data);
    }

    public List<String> getMenu() throws Exception {
        List<String> list = new ArrayList<>();
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has("value")) {
                    list.add(jsonObject.getString("value"));
                }
            }
        }
        return list;
    }

    public List<String> getCategory(String main) throws Exception {
        List<String> list = new ArrayList<>();
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has("value")) {
                    if (jsonObject.getString("value").equals(main)) {
                        if (jsonObject.has("children")) {
                            JSONArray array = new JSONArray(jsonObject.getString("children"));
                            for (int j = 0; j < array.length(); j++) {
                                JSONObject jsonObject1 = array.getJSONObject(j);
                                if (jsonObject1.has("value")) {
                                    list.add(jsonObject1.getString("value"));
                                }
                            }

                        }
                    }
                }
            }
        }
        return list;
    }

    public List<String> getSubCategory(String main, String category) throws Exception {
        List<String> list = new ArrayList<>();
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has("value")) {
                    if (jsonObject.getString("value").equals(main)) {
                        if (jsonObject.has("children")) {
                            JSONArray array = new JSONArray(jsonObject.getString("children"));
                            for (int j = 0; j < array.length(); j++) {
                                JSONObject jsonObject1 = array.getJSONObject(j);
                                if (jsonObject1.has("value")) {
                                    if (jsonObject1.getString("value").equals(category)) {
                                        if (jsonObject1.has("children")) {
                                            JSONArray jsonArray1 = new JSONArray(jsonObject1.getString("children"));
                                            for (int k = 0; k < jsonArray1.length(); k++) {
                                                JSONObject jsonObject2 = jsonArray1.getJSONObject(k);
                                                if (jsonObject2.has("value")) {
                                                    list.add(jsonObject2.getString("value"));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

}
