package com.unixsoftect.styleklub1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Requestdata extends AppCompatActivity {
TextView textView;
    RequestQueue queue;
   public static final String TAG="MyTag";

    StringRequest stringRequest;
    static String url = "http://192.168.0.113:3001";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestdata);
        textView = findViewById(R.id.reuqestdata);
//      Instantiate the RequestQueue
//      queue = Volley.newRequestQueue(Requestdata.this);
//      Request a string response from the provided URL
//         stringRequest = new StringRequest(Request.Method.GET, url+"/api/headermenu", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //Display the first 500 characters of the response string.
//                textView.setText("Response is :" + response.substring(0, 500));
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didnt work!");
//            }
//        });
//        //add the request to the response
//        //set the tag on the request
//        stringRequest.setTag(TAG);
//        queue.add(stringRequest);
      new Request(this,"/api/headermenu", com.android.volley.Request.Method.GET) {

            @Override
            public void onresponse(String response) {
                if (!response.substring(0,500).isEmpty())
                {
                    try {
                        JSONArray array = new JSONArray(response);
                        JSONArray reduceArray = new JSONArray();
                        for(int i=0;i<array.length();i++){
                            String val = array.getJSONObject(i).get("value").toString();
                            reduceArray.put(new JSONObject().put("value",val));
                        }
//                        JSONObject obj =reduceArray.getJSONObject(0);
                        textView.setText(reduceArray.toString());
                        ArrayList<String> data = new ArrayList<String>();
                        for (int j=0;j<reduceArray.length();j++)
                        {
                            String var = reduceArray.getJSONObject(j).get("value").toString();
                            data.add(var);
                        }

                        int size = data.size();
                        Log.e("ajjsjdsf",""+size);
                        textView.setText(data.get(0));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Log.e("Response is ",response.substring(0,500));
                }
                else
                    textView.setText("response is empty");
                Log.e("Response is empty","no response");
            }

            @Override
            public void onError(VolleyError error) {

                textView.setText(error.getMessage());

            }

        };

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null)
        {
            queue.cancelAll(TAG);
        }
    }
}