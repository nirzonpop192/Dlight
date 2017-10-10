package com.faisal.technodhaka.dlight.activity.chat;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


/**
 * Created by TD-Android on 10/7/2017.
 */
public class YAxisValueFormatter implements IAxisValueFormatter {

    String [] mValues;

    public YAxisValueFormatter(String[] mValues) {
        this.mValues = mValues;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mValues[(int)value];
    }

}
