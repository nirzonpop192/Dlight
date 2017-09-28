package com.faisal.technodhaka.dlight.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Faisal
 * @since 8/28/2015.
 */


public class AssignDataModel {


    private String cCode;
    /**
     * country code
     */
    private String donorCode;
    private String awardCode;

    private String districtCode;
    private String upazillaCode;
    private String unitCode;


    public String getCountryCode() {
        return cCode;
    }


    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getUpazillaCode() {
        return upazillaCode;
    }

    public void setUpazillaCode(String upazillaCode) {
        this.upazillaCode = upazillaCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }



}
