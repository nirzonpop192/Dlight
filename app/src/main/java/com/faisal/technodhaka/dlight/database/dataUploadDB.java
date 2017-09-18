package com.faisal.technodhaka.dlight.database;

/**
 * Created by Faisal on 1/12/2016.
 */
public class dataUploadDB implements Comparable<dataUploadDB> {

    public String _id = null;
    public String _syntax = null;
    int _sqn = -1;

    @Override
    public int compareTo(dataUploadDB another) {
        return 0;
    }
}
