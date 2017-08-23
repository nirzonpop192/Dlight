package com.faisal.technodhaka.dlight.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faisal.technodhaka.dlight.R;

/**
 * Created by TD-Android on 8/23/2017.
 */
public class ChartFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chart, container, false);
    }
}
