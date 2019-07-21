package com.example.thenextepisode;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    static void searchForTvShow(final Context context, final String tvShow) {
        String url = "https://api.thetvdb.com/search/series?";
        Map<String, String> params = new HashMap<>();
        params.put("name", tvShow);
        url+=encodeParametersIntoUrl(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Log.d("tvshow", response.toString());
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
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer " + PreferenceManager
                        .getDefaultSharedPreferences(context)
                        .getString("APIKEY", ""));

                return params;
            }
        };

        RequestQueueSingleton.getInstance(context).getRequestQueue().add(jsonObjectRequest);
    }

    private static String encodeParametersIntoUrl(Map<String, String> params) {
        if (params != null && params.size() > 0) {
            return encodeParameters(params, "UTF-8");
        }
        return null;
    }

    /** Converts <code>params</code> into an application/x-www-form-urlencoded encoded string. */
    private static String encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    throw new IllegalArgumentException(
                            String.format(
                                    "Request#getParams() or Request#getPostParams() returned a map "
                                            + "containing a null key or value: (%s, %s). All keys "
                                            + "and values must be non-null.",
                                    entry.getKey(), entry.getValue()));
                }
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }
}
