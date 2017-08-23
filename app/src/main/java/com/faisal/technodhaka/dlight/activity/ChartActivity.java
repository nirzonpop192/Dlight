package com.faisal.technodhaka.dlight.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.faisal.technodhaka.dlight.R;
import com.faisal.technodhaka.dlight.data_model.adapters.AssignDataModel;
import com.faisal.technodhaka.dlight.fragments.BaseActivity;
import com.faisal.technodhaka.dlight.manager.SQLiteHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends BaseActivity {

   private LineChart mChart;
   private Context mContext = this;
   private SQLiteHandler sqlH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        initial();

        // in this example, a LineChart is initialized from xml




        List<AssignDataModel.DynamicDataIndexDataModel> dataModels = sqlH.getDynamicTableIndexList("0002", "", session.getStaffId());


        List<Entry> entries = new ArrayList<Entry>();

        for (AssignDataModel.DynamicDataIndexDataModel data : dataModels) {

            // turn your data into Entry objects
            entries.add(new Entry(Float.parseFloat(data.getDtBasicCode()), Float.parseFloat(data.getProgramCode())));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.BLUE);
//        dataSet.setValueTextColor(...); // styling, ...

        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);
        mChart.invalidate(); // refresh
    }

    private void initial() {
        sqlH= new SQLiteHandler(mContext);
        viewReference();
    }


    private void viewReference() {
         mChart = (LineChart) findViewById(R.id.chart);
    }
}
