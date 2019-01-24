package com.lucas.concept.codechallengeasync.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucas.concept.codechallengeasync.controller.DeviceController;
import com.lucas.concept.codechallengeasync.controller.IInformationAvailableListener;
import com.lucas.concept.codechallengeasync.model.Device;
import com.lucas.concept.codechallengeasync.R;
import com.squareup.picasso.Picasso;

public class DeviceDetailActivity extends Activity implements IInformationAvailableListener {
    private Device mDevice;
    public TextView mDeviceTitle;
    public TextView mDeviceDescription;
    public ImageView mDeviceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_detail);

        mDeviceTitle = findViewById(R.id.device_title);
        mDeviceDescription = findViewById(R.id.device_description);
        mDeviceImage = findViewById(R.id.device_image);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int deviceIndex = extras.getInt(DeviceController.DEVICE_INDEX);
            mDevice = DeviceController.getInstance(this).getData().get(deviceIndex);
            if (mDevice != null) {
                mDeviceTitle.setText(mDevice.getTitle());
                mDeviceDescription.setText(mDevice.getDescription());
                Picasso.with(this).load(mDevice.getImageUrl())
                        .resize((int) this.getResources().getDimension(R.dimen.width_image_large), (int) this.getResources().getDimension(R.dimen.height_image_large))
                        .error(android.R.drawable.ic_menu_camera)
                        .placeholder(android.R.drawable.ic_menu_camera)
                        .into(mDeviceImage);
            }
        }


    }

    @Override
    public void refreshViews() {

    }
}
