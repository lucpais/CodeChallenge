package com.lucas.concept.codechallengeasync.model;

import java.io.Serializable;

public class Device implements Serializable {

    private String mTitle;
    private String mDescription;
    private String mImageUrl;

    public Device(String mTitle, String mDescription, String mImageUrl) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImageUrl = mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
