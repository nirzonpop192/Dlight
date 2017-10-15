package com.faisal.technodhaka.dlight.activity;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.faisal.technodhaka.dlight.R;
import com.faisal.technodhaka.dlight.data_model.DynamicDataIndexDataModel;
import com.faisal.technodhaka.dlight.fragments.BaseActivity;
import com.faisal.technodhaka.dlight.database.SQLiteHandler;
import com.faisal.technodhaka.dlight.utils.KEY;
import com.faisal.technodhaka.dlight.views.adapters.DynamicDataIndexAdapter;
import com.faisal.technodhaka.dlight.views.notifications.ADNotificationManager;

import java.util.ArrayList;
import java.util.List;

public class DynamicTable extends BaseActivity {

    /**
     * list view to show Dynamic table list
     */
    private ListView listView;
    private Button btnHome/*, btnDTSearch*/;
    private final Context mContext = DynamicTable.this;
    private SQLiteHandler sqlH;

    //    private EditText edtDTSearch;
    private String idCountry;

    private DynamicDataIndexAdapter mAdapter = null;
    private static ProgressDialog pDialog;
    public int TOTAL_LIST_ITEMS;
    public int NUM_ITEMS_PAGE = 5;
    private int noOfBtns;
    private Button[] btns;
    private int pageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_table);

        initialize();

        // get intent
        Intent intent = getIntent();
        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);


        btnFooter(idCountry, "");
        loadListViewForFirstTime(idCountry);

        setListener();
    }

    private void loadListViewForFirstTime(String cCode) {
        loadList(0, cCode, "");
        CheckBtnBackGroud(0);
    }

    /**
     * this block is for checking the number of pages
     * ====================================================
     */
    private int getNumberOfPages(final String cCode, String c) {
        TOTAL_LIST_ITEMS = sqlH.getDynamicTableTotalNumber(cCode, "", session.getStaffId());
        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val == 0 ? 0 : 1;
        pageCount = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;
        return val;
    }

    private void btnFooter(final String cCode, String c) {
        int val = getNumberOfPages(cCode, c);

        noOfBtns = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;


        LinearLayout ll = (LinearLayout) findViewById(R.id.btnLay);

        btns = new Button[noOfBtns];

        for (int i = 0; i < noOfBtns; i++) {
            btns[i] = new Button(this);
            btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btns[i].setText("" + (i + 1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);

            final int j = i;
            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    loadList(j, cCode, "");
                    CheckBtnBackGroud(j);
                }
            });
        }

    }

    /**
     * Method for Checking Button Backgrounds
     */
    private void CheckBtnBackGroud(int index) {
//        title.setText("Page "+(index+1)+" of "+noOfBtns);
        for (int i = 0; i < noOfBtns; i++) {
            if (i == index) {
                btns[index].setBackgroundDrawable(getResources().getDrawable(R.color.green));
                btns[i].setTextColor(getResources().getColor(android.R.color.white));
            } else {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
            }
        }

    }


    /**
     * Method for loading data in list view
     */
    private void loadList(int number, String cCode, String dtName) {
        ArrayList<DynamicDataIndexDataModel> sort = new ArrayList<>();


        int start = number * NUM_ITEMS_PAGE;
        List<DynamicDataIndexDataModel> dataList = sqlH.getDynamicTableIndexList(cCode, dtName,
                "0001", start);


        if (dataList.size() != 0) {


            mAdapter = new DynamicDataIndexAdapter((Activity) mContext, dataList);                  //Assign the Adapter in list
            mAdapter.notifyDataSetChanged();
            listView.setAdapter(mAdapter);
        } else {
            new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
        }

    }

    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });

    }


    private void initialize() {
        viewReference();
        sqlH = new SQLiteHandler(mContext);
    }

    private void viewReference() {
        listView = (ListView) findViewById(R.id.lvDynamicTableIndex);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);


        // button er Gaiyeb korra jonno
        Button button = (Button) findViewById(R.id.btnRegisterFooter);
        button.setVisibility(View.GONE);
    }




}
