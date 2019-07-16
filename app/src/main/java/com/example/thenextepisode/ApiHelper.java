package com.example.thenextepisode;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiHelper {


    static void getAPIKeyAndPutIntoSharedPreferences(final Context context) {
        String url ="https://api.thetvdb.com/login";

        JSONObject obj = new JSONObject();
        try {
            //you'll need to obtain your own apikey/username/userkey to build this yourself ;)
            obj.put("apikey", context.getResources().getString(R.string.apikey));
            obj.put("username", context.getResources().getString(R.string.username));
            obj.put("userkey", context.getResources().getString(R.string.userkey));
        } catch (Exception ex) {
            Log.e("Couldn't put an object? ", ex.toString());
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            PreferenceManager.getDefaultSharedPreferences(context)
                                    .edit().putString("APIKEY", response.getString("token")).apply();
                            Log.d("api", response.getString("token"));
                        } catch (Exception ex) {
                            Log.e("Response error: ", ex.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Accept", "application/json");

                return params;
            }
        };

        RequestQueueSingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);
    }

    static void searchForTvShow(final Context context, String tvShow) {
        String url ="https://api.thetvdb.com/login";

        JSONObject obj = new JSONObject();
        try {
            //you'll need to obtain your own apikey/username/userkey to build this yourself ;)
            obj.put("apikey", context.getResources().getString(R.string.apikey));
            obj.put("username", context.getResources().getString(R.string.username));
            obj.put("userkey", context.getResources().getString(R.string.userkey));
        } catch (Exception ex) {
            Log.e("Couldn't put an object? ", ex.toString());
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            PreferenceManager.getDefaultSharedPreferences(context)
                                    .edit().putString("APIKEY", response.getString("token")).apply();
                            Log.d("api", response.getString("token"));
                        } catch (Exception ex) {
                            Log.e("Response error: ", ex.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley: ", error.toString());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Accept", "application/json");

                return params;
            }
        };

        RequestQueueSingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);
    }
}
