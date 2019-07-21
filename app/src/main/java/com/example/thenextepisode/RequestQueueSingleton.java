package com.example.thenextepisode;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class RequestQueueSingleton {
    private static RequestQueueSingleton instance;
    private RequestQueue requestQueue;

    private RequestQueueSingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    static synchronized RequestQueueSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueSingleton(context);
        }
        return instance;
    }

    RequestQueue getRequestQueue() {
        return requestQueue;
    }
}