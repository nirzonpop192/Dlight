package com.faisal.technodhaka.dlight.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.faisal.technodhaka.dlight.R;
import com.faisal.technodhaka.dlight.activity.sub.DTResponseActivity;
import com.faisal.technodhaka.dlight.activity.sub.DT_ReportActivity;
import com.faisal.technodhaka.dlight.data_model.DynamicDataIndexDataModel;

import com.faisal.technodhaka.dlight.database.SQLiteHandler;
import com.faisal.technodhaka.dlight.utils.KEY;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faisal on 9/26/2016.
 *
 * @since version 03
 */
public class DynamicDataIndexAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private SQLiteHandler sqlH;

    private List<DynamicDataIndexDataModel> data = new ArrayList<>();

    public DynamicDataIndexAdapter(Activity activity, List<DynamicDataIndexDataModel> data) {
        this.activity = activity;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public DynamicDataIndexDataModel getDynamicDataIndex(int pos) {
        return (DynamicDataIndexDataModel) getItem(pos);
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        final DynamicDataIndexDataModel data = getDynamicDataIndex(position);


        if (inflater == null) {                                                                        // convert xml layout  to java object
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            sqlH = new SQLiteHandler(activity);

        }
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_dynamic_data_index, null);

            holder = new ViewHolder();

            //view reference
            viewReference(row);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.tv_dtTitle.setText(data.getDtTittle());
        holder.tv_dtResSum.setText(String.valueOf(sqlH.getSurveyTotalNumber(data.getDtBasicCode())));
        holder.tv_entryDate.setText(data.getEntryDate());


        holder.iv_Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity.getApplicationContext(), DTResponseActivity.class);
                intent.putExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY, data);
                activity.startActivity(intent);

            }
        });

        holder.iv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity.getApplicationContext(), DT_ReportActivity.class);
                intent.putExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY, data);
                activity.startActivity(intent);

            }
        });

/**
 *  Change the color of background & text color Dynamically in list view
 */
        if (position % 2 == 0) {
            row.setBackgroundColor(Color.WHITE);
            changeTextColor(activity.getResources().getColor(R.color.blue));
        } else {
            row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
            changeTextColor(activity.getResources().getColor(R.color.black));
        }


        return row;
    }

    private void viewReference(View row) {
        holder.llRow = (LinearLayout) row.findViewById(R.id.llRow);
        holder.tv_dtTitle = (TextView) row.findViewById(R.id.dt_index_row_tv_dtTitle);
        holder.tv_dtResSum = (TextView) row.findViewById(R.id.dt_index_res_total_no);
        holder.iv_Go = (ImageView) row.findViewById(R.id.dt_index_row_ibtn_go);
        holder.iv_view = (ImageView) row.findViewById(R.id.dt_index_row_ibtn_view);
        holder.tv_entryDate = (TextView) row.findViewById(R.id.dt_index_row_tv_EntryDate);
   /*         holder.tv_progName = (TextView) row.findViewById(R.id.dt_index_row_tv_ProgramName);
            holder.tv_ActivityName = (TextView) row.findViewById(R.id.dt_index_row_tv_ActivityTitle);*/

    }


    private void changeTextColor(int color) {
        holder.tv_dtTitle.setTextColor(color);

     /*   holder.tv_progName.setTextColor(color);
        holder.tv_ActivityName.setTextColor(color);*/
    }

    private static class ViewHolder {
        LinearLayout llRow;
        TextView tv_dtTitle;
        TextView tv_dtResSum;
        TextView tv_entryDate;
        /*       TextView tv_progName;
               TextView tv_ActivityName;*/
        ImageView iv_Go;
        ImageView iv_view;
    }
}
