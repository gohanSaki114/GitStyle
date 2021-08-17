package com.unixsoftect.styleklub1;

import android.content.Context;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;

public abstract class Request {
    public static final String TAG = "MyTag";
    static RequestQueue queue;
    static StringRequest stringRequest;
    static String url = "http://192.168.0.106:3001";
    Context context;
    String endpoint;
    int RequestMethod;
    Map<String, String> map;

//    public Request(Context context, String endpoint, int RequestMethod, Map<String,String> map) {
//        this.context = context;
//        this.endpoint = endpoint;
//        this.RequestMethod = RequestMethod;
//        this.map = map;
//        GetResponse();
//    }
    public Request(Context context, String endpoint, int RequesMethod) {
       this.context=context;
       this.endpoint=endpoint;
       this.RequestMethod = RequesMethod;
       GetResponse();
    }
    public void GetResponse() {
        queue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(RequestMethod, url + endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onresponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (map.isEmpty()) {
                    return super.getParams();
                } else {
                    return map;
                }            }
        };
        //add the request to the response
        //set the tag on the request
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }
    public abstract void onresponse(String response);
    public abstract void onError(VolleyError error);
}
