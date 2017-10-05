package com.faisal.technodhaka.dlight.activity.about.datamodel;

/**
 * Created by TD-Android on 10/4/2017.
 */
public class DataHeaderHelper {
    private int imageID;
    private String header;

    public DataHeaderHelper(int imageID, String header) {
        this.imageID = imageID;
        this.header = header;
    }

    public int getImageID() {
        return imageID;
    }

    public String getHeader() {
        return header;
    }
}
