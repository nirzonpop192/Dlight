package com.faisal.technodhaka.dlight.activity.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.faisal.technodhaka.dlight.R;
import com.faisal.technodhaka.dlight.activity.about.adapter.ExpandableListAdapter;
import com.faisal.technodhaka.dlight.activity.about.datamodel.DataHeaderHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AboutUsActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<DataHeaderHelper> listDataHeader;
    HashMap<DataHeaderHelper, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.elv_td_toDo);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<DataHeaderHelper>();
        listDataChild = new HashMap<DataHeaderHelper, List<String>>();

        // Adding parent data
        listDataHeader.add(new DataHeaderHelper(R.drawable.software, "Software Development"));
        listDataHeader.add(new DataHeaderHelper(R.drawable.web, "Web Application"));
        listDataHeader.add(new DataHeaderHelper(R.drawable.appdev, "Mobile Applications"));
        listDataHeader.add(new DataHeaderHelper(R.drawable.digital_mkt, "Digital Marketing"));
        listDataHeader.add(new DataHeaderHelper(R.drawable.security_slns, "Security Solutions and Products"));
        listDataHeader.add(new DataHeaderHelper(R.drawable.system, "IT Strategy and Planning"));

        // Adding child data
        List<String> softDev = new ArrayList<String>();


        softDev.add(getResources().getString(R.string.softDevDetails));
        List<String> webDev = new ArrayList<String>();
        webDev.add(getResources().getString(R.string.webDevDetails));


        List<String> mobApp = new ArrayList<String>();
        mobApp.add(getResources().getString(R.string.mobDevDetails));

        List<String> digitMKT = new ArrayList<String>();
        digitMKT.add(getResources().getString(R.string.digitalMKTDetails));

        List<String> security = new ArrayList<String>();
        security.add(getResources().getString(R.string.securityDetails));

        List<String> strategy = new ArrayList<String>();
        strategy.add(getResources().getString(R.string.strategyDetails));

        listDataChild.put(listDataHeader.get(0), softDev); // Header, Child data
        listDataChild.put(listDataHeader.get(1), webDev);
        listDataChild.put(listDataHeader.get(2), mobApp);
        listDataChild.put(listDataHeader.get(3), digitMKT);
        listDataChild.put(listDataHeader.get(4), security);
        listDataChild.put(listDataHeader.get(5), strategy);
    }
}
