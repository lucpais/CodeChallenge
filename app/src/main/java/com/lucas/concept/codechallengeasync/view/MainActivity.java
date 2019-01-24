package com.lucas.concept.codechallengeasync.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lucas.concept.codechallengeasync.controller.DeviceController;
import com.lucas.concept.codechallengeasync.controller.IItemClickListener;
import com.lucas.concept.codechallengeasync.controller.IInformationAvailableListener;
import com.lucas.concept.codechallengeasync.R;

public class MainActivity extends AppCompatActivity implements IItemClickListener, IInformationAvailableListener {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private RecyclerView mRecyclerView;
    private DeviceAdapter mDeviceAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DividerItemDecoration mDividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.device_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), ((LinearLayoutManager) mLayoutManager).getOrientation());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mDeviceAdapter = new DeviceAdapter(this);
        mDeviceAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mDeviceAdapter);
    }

    @Override
    public void onClickDevice(View view, int position) {
        Intent i = new Intent(this, DeviceDetailActivity.class);
        i.putExtra(DeviceController.DEVICE_INDEX, position);
        startActivity(i);
    }

    @Override
    public void refreshViews() {
        mDeviceAdapter.notifyDataSetChanged();
    }
}
