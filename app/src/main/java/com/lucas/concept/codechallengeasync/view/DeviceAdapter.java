package com.lucas.concept.codechallengeasync.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucas.concept.codechallengeasync.controller.DeviceController;
import com.lucas.concept.codechallengeasync.controller.IItemClickListener;
import com.lucas.concept.codechallengeasync.model.Device;
import com.lucas.concept.codechallengeasync.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder> {
    private final Context mContext;
    private List<Device> mData;
    private IItemClickListener mClickListener;

    public DeviceAdapter(Context context) {
        mContext = context;
        mData = DeviceController.getInstance(mContext).getData();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mDeviceTitle;
        public TextView mDeviceDescription;
        public ImageView mDeviceThumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mDeviceTitle = itemView.findViewById(R.id.device_title);
            mDeviceDescription = itemView.findViewById(R.id.device_short_desc);
            mDeviceThumbnail = itemView.findViewById(R.id.device_image);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onClickDevice(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_cell, viewGroup, false);
        DeviceAdapter.MyViewHolder vh = new DeviceAdapter.MyViewHolder(itemView);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.mDeviceTitle.setText(mData.get(i).getTitle());
        myViewHolder.mDeviceDescription.setText(mData.get(i).getDescription());
        Picasso.with(mContext)
                .load(mData.get(i).getImageUrl())
                .resize((int) mContext.getResources().getDimension(R.dimen.width_image_thumb), (int) mContext.getResources().getDimension(R.dimen.height_image_thumb))
                .error(android.R.drawable.ic_menu_camera)
                .placeholder(android.R.drawable.ic_menu_camera)
                .into(myViewHolder.mDeviceThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setClickListener(IItemClickListener itemClickListener) {
        mClickListener = itemClickListener;
    }
}
