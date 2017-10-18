package com.faisal.technodhaka.dlight.views.spinner;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.faisal.technodhaka.dlight.R;

import com.faisal.technodhaka.dlight.data_model.DynamicDataIndexDataModel;
import com.faisal.technodhaka.dlight.data_model.adapters.AssignDataModel;
import com.faisal.technodhaka.dlight.data_model.adapters.DTQTableDataModel;
import com.faisal.technodhaka.dlight.database.SQLiteHandler;
import com.faisal.technodhaka.dlight.database.SQLiteQuery;
import com.faisal.technodhaka.dlight.views.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pop
 *
 * @since 1/11/2017.
 * This class is use for Load values in Spinner .
 * if  data exist in data base get the value set to the spinner value
 */

public class SpinnerLoader {
    private static String TAG = SpinnerLoader.class.getSimpleName();

    /***
     * @param context      refer to the activity which will invoke this method.
     * @param sqlH         database reference
     * @param spGroupCat   spinner view
     * @param cCode        country code
     * @param donorCode    donor code
     * @param awardCode    award code
     * @param progCode     program code
     * @param groupCatCode Group categories code
     * @param strGroupCat  Group categories name(values)
     *                     <p>
     *                     This methods load the Group Categories Code and Name to the Spinner From data base
     *                     following to the Country Code , Donor Code , Award Code , Program Code.
     *                     If data exits for certain member , Assume that member 'xyz' has group categories code 03
     *                     and it value I mean group categories name 'Producer Group Categories ' the spinner will be selected the
     *                     'Producer Group' and it's value otherwise the default value would be select .</p>
     */


    /**
     * semi universal method
     *
     * @param context     refer to the activity which will invoke this method.
     * @param sqlH        database reference
     * @param spGroup     spinner view
     * @param cCode       country code
     * @param donorCode   donor code
     * @param awardCode   award code
     * @param progCode    program code
     * @param grpCateCode Group categories code
     * @param groupCode   group Code
     * @param strGroup    group name
     *                    <p>
     *                    This methods load the Group  Code and Name to the Spinner From data base
     *                    following to the Country Code , Donor Code , Award Code , Program Code & Group Categories Code .
     *                    If data exits for certain member , Assume that member 'xyz' has group  code 0003
     *                    and it value I mean group categories name 'Tamana Group, Balaka ' the spinner will be selected the
     *                    'Tamana Group Balaka' and it's value otherwise the default value would be select .</p>
     */
    public static void loadGroupLoader(Context context, SQLiteHandler sqlH, Spinner spGroup, String cCode, String donorCode, String awardCode, String progCode, String grpCateCode, String groupCode, String strGroup) {
        int position = 0;
        String criteria = SQLiteQuery.loadGroupLoader_sql(cCode, donorCode, awardCode, progCode, grpCateCode);


        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listAward);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spGroup.setAdapter(dataAdapter);


        if (groupCode != null) {
            for (int i = 0; i < spGroup.getCount(); i++) {
                String groupCategory = spGroup.getItemAtPosition(i).toString();
                if (groupCategory.equals(strGroup)) {
                    position = i;
                }
            }
            spGroup.setSelection(position);
        }
    }

    /**
     * universal method
     *
     * @param context   refer to the activity which will invoke this method.
     * @param sqlH      database reference
     * @param spAward   spinner view
     * @param cCode     country Code
     * @param awardCode award code
     * @param strAward  award Name
     *                  This method load the Award Name .
     */
    public static void loadAwardLoader(Context context, SQLiteHandler sqlH, Spinner spAward, String cCode, String awardCode, String strAward) {
        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "'";

        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listAward);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spAward.setAdapter(dataAdapter);


        if (awardCode != null) {
            for (int i = 0; i < spAward.getCount(); i++) {
                String award = spAward.getItemAtPosition(i).toString();
                if (award.equals(strAward)) {
                    position = i;
                }
            }
            spAward.setSelection(position);
        }

    }




    /***
     * the dynamic spinner loader in Dynamic Response
     *
     * @param context    refer to the activity which will invoke this method.
     * @param sqlH       database reference
     * @param dt_spinner spinner view
     * @param cCode      country code
     * @param resLupText response Look up text
     * @param strSpinner spinner T7ext
     * @param mDTQ       Dynamic Table Question
     */
    public static void loadDynamicSpinnerListLoader(Context context, SQLiteHandler sqlH,
                                                    Spinner dt_spinner, String cCode,
                                                    String resLupText, String strSpinner,
                                                    DTQTableDataModel mDTQ,
                                                    DynamicDataIndexDataModel dyBasic) {

        int position = 0;
        List<SpinnerHelper> list = new ArrayList<SpinnerHelper>();
        String udf = SQLiteQuery.loadDynamicSpinnerListLoader_sql(cCode, resLupText, mDTQ.getLup_TableName(), dyBasic);


        list.clear();
        list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);

        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        dt_spinner.setAdapter(dataAdapter);

        /**      Retrieving Code for previous button         */
        if (strSpinner != null) {
            for (int i = 0; i < dt_spinner.getCount(); i++) {

                if (list.get(i).getId().equals(strSpinner)) {
                    position = i;
                }
            }
            dt_spinner.setSelection(position);
        }

    }


    /**
     * this method load dynamic survey  active month.
     *
     * @param context   refer to the activity which will invoke this method.
     * @param sqlH      database reference
     * @param spDtMonth spinner view
     * @param cCode     country code
     * @param opCode    operation code for dynamic table is 5
     * @param idMonth   op month code
     * @param strMonth  op month name
     */
    public static void loadDtMonthLoader(Context context, SQLiteHandler sqlH, Spinner spDtMonth,
                                         String cCode, String opCode, String idMonth,
                                         String strMonth,String donorCode,String awardCode) {
        int position = 0;
        String criteria = SQLiteQuery.loadDtMonth_sql(cCode, opCode, "",donorCode,awardCode);
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);
        /**         *  remove select value         */
        listProgram.remove(0);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listProgram);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spDtMonth.setAdapter(dataAdapter);


        if (idMonth != null) {
            for (int i = 0; i < spDtMonth.getCount(); i++) {
                String monthName = spDtMonth.getItemAtPosition(i).toString();
                if (monthName.equals(strMonth)) {
                    position = i;
                }
            }
            spDtMonth.setSelection(position);

        }

    }

    /**
     * @param context       invoking activity
     * @param sqlH          database object
     * @param spinner       country spinner
     * @param operationMode operation mode
     * @param cCode         country code
     * @param strCountry    country name
     */

    public static void loadCountryLoader(Context context, SQLiteHandler sqlH, Spinner spinner, int operationMode, String cCode, String strCountry) {

        int position = 0;
        String criteria = "";

        criteria = SQLiteQuery.loadCountry_sql(operationMode, sqlH.isMultipleCountryAccessUser());

        List<SpinnerHelper> listCountry = sqlH.getListAndID(SQLiteHandler.COUNTRY_TABLE, criteria, null, true);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listCountry);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(dataAdapter);


        if (cCode != null) {
            for (int i = 0; i < spinner.getCount(); i++) {
                String country = spinner.getItemAtPosition(i).toString();
                if (country.equals(strCountry)) {
                    position = i;
                }
            }
            spinner.setSelection(position);
        }
    }

    public static void loadDtBasicLoader(Context context, SQLiteHandler sqlH, Spinner spinner, int operationMode, String cCode, String strCountry) {

        int position = 0;
        String criteria = "";

        criteria = SQLiteQuery.loadDtBasic_sql("0002", "0001");

        List<SpinnerHelper> listCountry = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, true);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listCountry);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(dataAdapter);


        if (cCode != null) {
            for (int i = 0; i < spinner.getCount(); i++) {
                String district = spinner.getItemAtPosition(i).toString();
                if (district.equals(strCountry)) {
                    position = i;
                }
            }
            spinner.setSelection(position);
        }
    }



}                                                                                                   // end of the class
