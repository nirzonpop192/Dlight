package com.faisal.technodhaka.dlight.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.faisal.technodhaka.dlight.R;
import com.faisal.technodhaka.dlight.data_model.DynamicDataIndexDataModel;
import com.faisal.technodhaka.dlight.data_model.adapters.AssignDataModel;
import com.faisal.technodhaka.dlight.database.SQLiteQuery;
import com.faisal.technodhaka.dlight.fragments.BaseActivity;
import com.faisal.technodhaka.dlight.database.SQLiteHandler;
import com.faisal.technodhaka.dlight.views.helper.SpinnerHelper;
import com.faisal.technodhaka.dlight.views.spinner.SpinnerLoader;
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
    LineDataSet mDataSet;
    List<Entry> mEntries = new ArrayList<Entry>();
    private Spinner spSurveyTitle;
    private String strDtBasic;
    private String idDtBasic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        initial();

        // in this example, a LineChart is initialized from xml


        loadDtBasic();

        List<DynamicDataIndexDataModel> dataModels = sqlH.getDynamicTableIndexList("0002", "",
                session.getStaffId(), SQLiteQuery.NO_LIMIT);




        for (DynamicDataIndexDataModel data : dataModels) {

            // turn your data into Entry objects
            mEntries.add(new Entry(Float.parseFloat(data.getDtBasicCode()), Float.parseFloat(data.getProgramCode())));
        }

        chartSettings();

    }

    private void chartSettings() {
        mDataSet = new LineDataSet(mEntries, "Label");                                              // add mEntries to dataset
        mDataSet.setColor(Color.BLUE);
//        mDataSet.setValueTextColor(...); // styling, ...

        LineData lineData = new LineData(mDataSet);
        mChart.setData(lineData);
        mChart.invalidate();                                                                        // refresh
    }

    private void initial() {
        sqlH= new SQLiteHandler(mContext);
        viewReference();
    }


    private void viewReference() {
         mChart = (LineChart) findViewById(R.id.chart);
        spSurveyTitle = (Spinner) findViewById(R.id.spSurveyTitle);// add the spinner
    }

    private void loadDtBasic() {

        int operationMode = sqlH.getDeviceOperationModeCode();


        SpinnerLoader.loadDtBasicLoader(mContext, sqlH, spSurveyTitle, operationMode, idDtBasic, strDtBasic);
        spSurveyTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDtBasic = ((SpinnerHelper) spSurveyTitle.getSelectedItem()).getValue();
                idDtBasic = ((SpinnerHelper) spSurveyTitle.getSelectedItem()).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner
}
