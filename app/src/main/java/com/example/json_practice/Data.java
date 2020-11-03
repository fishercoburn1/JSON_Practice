package com.example.json_practice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Response.ErrorListener;
import static com.android.volley.Response.Listener;

public class Data {
    private RequestQueue nQueue;

    //a map of json items returned
    public static Map<String, Company> ITEM_MAP = null;

    public static List<Company> COMPANIES = null;

    public void getThatData (Context context){
            if(COMPANIES != null){
                return;
            }

            nQueue = Volley.newRequestQueue(context);

            parseJason(context);

            COMPANIES = new ArrayList<>();
            ITEM_MAP = new HashMap<>();
    }
    private void parseJason(final Context context){
        String url = context.getString(R.string.company_url);

        final Gson gson = new Gson();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Listener<JSONObject>() {
            @Override
                    public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray = response.getJSONArray("Company");

                        if(jsonArray.length() > 0) {
                            List<Company> companies = Arrays.asList(gson.fromJson(jsonArray.toString(), Company[].class));
                            for(Company Company: companies) {
                                addObjectToList(Company);
                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
                            nQueue.add(request);
    }

    private void addObjectToList(Company theCompany) {
        COMPANIES.add(theCompany);
        ITEM_MAP.put(theCompany.companyName, theCompany);
    }


        }




