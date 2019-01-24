package com.lucas.concept.codechallengeasync.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lucas.concept.codechallengeasync.model.Device;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeviceController {

    private static final String TAG = DeviceController.class.getCanonicalName();
    public static final String DEVICE_INDEX = "device_index";
    private static DeviceController mInstance;
    private final IInformationAvailableListener mIInformationAvailableListener;
    private Context mContext;
    private List<Device> mData;
    private String url = "http://private-f0eea-mobilegllatam.apiary-mock.com/list";

    public static DeviceController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DeviceController(context);
        }
        return mInstance;
    }

    private DeviceController(Context context) {
        mContext = context;
        mData = new ArrayList<>();
        mIInformationAvailableListener = (IInformationAvailableListener) context;
        pupulateInfo();
    }

    private void pupulateInfo() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int index = 0; index < response.length(); index++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(index);
                        mData.add(new Device(jsonObject.getString("title"),
                                jsonObject.getString("description"),
                                jsonObject.getString("image")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mIInformationAvailableListener.refreshViews();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(jsonArrayRequest);
    }

    public List<Device> getData() {
        return mData;
    }
}
