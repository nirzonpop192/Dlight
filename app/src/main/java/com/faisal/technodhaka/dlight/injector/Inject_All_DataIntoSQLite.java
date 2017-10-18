package com.faisal.technodhaka.dlight.injector;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.faisal.technodhaka.dlight.activity.LoginActivity;
import com.faisal.technodhaka.dlight.database.SQLiteHandler;
import com.faisal.technodhaka.dlight.fragments.BaseActivity;
import com.faisal.technodhaka.dlight.parse.Parser;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by TD-Android on 10/18/2017.
 */
public class Inject_All_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {

    private ProgressDialog progressDialog;
    private int progressIncremental;
    private SQLiteHandler db;

    public Inject_All_DataIntoSQLite(ProgressDialog progressDialog, int progressIncremental, SQLiteHandler db) {
        this.progressDialog = progressDialog;
        this.progressIncremental = progressIncremental;
        this.db = db;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        BaseActivity activity ;
        /**
         * Read JSON DATA  from the text file
         * */
//        String retrieveData = readDataFromFile(LoginActivity.ALL_DATA);

        String retrieveData="";
        try {
            int size;
            /**
             * The total string Convert into JSON object
             * */

            JSONObject jObj = new JSONObject(retrieveData);

            String user_id = jObj.getString(Parser.USR_ID);
            JSONObject user = jObj.getJSONObject(Parser.USER_JSON_A);
            String country_code = user.getString(Parser.ADM_COUNTRY_CODE);
            String login_name = user.getString(Parser.USR_LOG_IN_NAME);
            String login_pw = user.getString(Parser.USR_LOG_IN_PW);
            String first_name = user.getString(Parser.USR_FIRST_NAME);
            String last_name = user.getString(Parser.USR_LAST_NAME);
            String email = user.getString(Parser.USR_EMAIL);
            String email_verification = user.getString(Parser.USR_EMAIL_VERIFICATION);
            String user_status = user.getString(Parser.USR_STATUS);
            String entry_by = user.getString(Parser.ENTRY_BY);
            String entry_date = user.getString(Parser.ENTRY_DATE);

//            setUserName(first_name); // Setting User hhName into session
//            setStaffID(user_id); // Setting Staff ID to use when sync data
//
//
//            setUserCountryCode(country_code); // Setting Country code


            db.addUser(user_id, country_code, login_name, login_pw, first_name, last_name, email, email_verification, user_status, entry_by, entry_date);

            publishProgress(++progressIncremental);


            if (!jObj.isNull(Parser.COUNTRIES_JSON_A)) {

                Parser.AdmCountryParser(jObj.getJSONArray(Parser.COUNTRIES_JSON_A), db);
            }


            publishProgress(++progressIncremental);


            if (!jObj.isNull(Parser.VALID_DATES_JSON_A)) {
                JSONArray valid_dates = jObj.getJSONArray(Parser.VALID_DATES_JSON_A);
                size = valid_dates.length();

                for (int i = 0; i < size; i++) {
                    JSONObject valid_date = valid_dates.getJSONObject(i);
                    String AdmCountryCode = valid_date.getString(Parser.ADM_COUNTRY_CODE);
                    String StartDate = valid_date.getString(Parser.START_DATE);
                    String EndDate = valid_date.getString(Parser.END_DATE);

                    db.addValidDateRange(AdmCountryCode, StartDate, EndDate);


                }
            }
            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.GPS_GROUP_JSON_A)) {
                Parser.gpsGroupParser(jObj.getJSONArray(Parser.GPS_GROUP_JSON_A), db);
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.GPS_SUBGROUP_JSON_A)) {
                Parser.gpsSubGroupParser(jObj.getJSONArray(Parser.GPS_SUBGROUP_JSON_A), db);
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.GPS_LOCATION_JSON_A)) {
                Parser.gpsLocationParse(jObj.getJSONArray(Parser.GPS_LOCATION_JSON_A), db);
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.ADM_COUNTRY_AWARD_JSON_A)) {
                Parser.admCountryAwardParser(jObj.getJSONArray(Parser.ADM_COUNTRY_AWARD_JSON_A), db);
            }
            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.ADM_AWARD_JSON_A)) {
                Parser.admAwardParser(jObj.getJSONArray(Parser.ADM_AWARD_JSON_A), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.ADM_DONOR_JSON_A)) {
                Parser.admDonorParser(jObj.getJSONArray(Parser.ADM_DONOR_JSON_A), db);
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.ADM_PROGRAM_MASTER_JSON_A))
                Parser.admProgramMasterParser(jObj.getJSONArray(Parser.ADM_PROGRAM_MASTER_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.ADM_SERVICE_MASTER_JSON_A))
                Parser.admServiceMasterParser(jObj.getJSONArray(Parser.ADM_SERVICE_MASTER_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.ADM_OP_MONTH_JSON_A))
                Parser.admOpMonthParser(jObj.getJSONArray(Parser.ADM_OP_MONTH_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.ADM_COUNTRY_PROGRAM_JSON_A))
                Parser.admCountryProgramParser(jObj.getJSONArray(Parser.ADM_COUNTRY_PROGRAM_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.DOB_SERVICE_CENTER_JSON_A))
                Parser.srvCenterParser(jObj.getJSONArray(Parser.DOB_SERVICE_CENTER_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.STAFF_ACCESS_INFO_JSON_A))
                Parser.staff_access_infoParser(jObj.getJSONArray(Parser.STAFF_ACCESS_INFO_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.LB_REG_HH_CATEGORY_JSON_A))
                Parser.lupRegnHHCategoryParser(jObj.getJSONArray(Parser.LB_REG_HH_CATEGORY_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.REG_LUP_GRADUATION_JSON_A))
                Parser.regNLupGraduationParser(jObj.getJSONArray(Parser.REG_LUP_GRADUATION_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.LAYER_LABELS_JSON_A))
                Parser.geoLayRMasterParser(jObj.getJSONArray(Parser.LAYER_LABELS_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.DISTRICT_JSON_A))
                Parser.geoLayR1ListParser(jObj.getJSONArray(Parser.DISTRICT_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.UPAZILLA_JSON_A))
                Parser.geoLayR2ListParser(jObj.getJSONArray(Parser.UPAZILLA_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.UNIT_JSON_A))
                Parser.geoLayR3ListParser(jObj.getJSONArray(Parser.UNIT_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.VILLAGE_JSON_A))
                Parser.geoLayR4ListParser(jObj.getJSONArray(Parser.VILLAGE_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.RELATION_JSON_A))
                Parser.lupRegNHHRelationParser(jObj.getJSONArray(Parser.RELATION_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.REPORT_TEMPLATE_JSON_A))
                Parser.rptTemplateParser(jObj.getJSONArray(Parser.REPORT_TEMPLATE_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.CARD_PRINT_REASON_JSON_A))
                Parser.lupRegNCardPrintReasonParser(jObj.getJSONArray(Parser.CARD_PRINT_REASON_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.STAFF_FDP_ACCESS_JSON_A))
                Parser.stffFdpAccessParser(jObj.getJSONArray(Parser.STAFF_FDP_ACCESS_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.FDP_MASTER_JSON_A))
                Parser.stffFdpMasterParser(jObj.getJSONArray(Parser.FDP_MASTER_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.LUP_SRV_OPTION_LIST))
                Parser.lupSrvOptionListParser(jObj.getJSONArray(Parser.LUP_SRV_OPTION_LIST), db);

            publishProgress(++progressIncremental);
            if (!jObj.isNull("vo_itm_table"))
                Parser.vo_itm_tableParser(jObj.getJSONArray("vo_itm_table"), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.VO_ITM_MEAS_TABLE_JSON_A))
                Parser.vo_itm_MassTableParser(jObj.getJSONArray(Parser.VO_ITM_MEAS_TABLE_JSON_A), db);


            publishProgress(++progressIncremental);
            if (!jObj.isNull("vo_country_prog_itm")) {
                JSONArray vo_country_prog_itmDatas = jObj.getJSONArray("vo_country_prog_itm");
                size = vo_country_prog_itmDatas.length();


                String AdmCountryCode;
                String AdmDonorCode;
                String AdmAwardCode;
                String AdmProgCode;
                String AdmSrvCode;
                String CatCode;
                String ItmCode;
                String MeasRCode;
                String VOItmSpec;
                String UnitCost;
                String Active;
                String Currency;
                for (int i = 0; i < size; i++) {
                    JSONObject vo_country_prog_itmData = vo_country_prog_itmDatas.getJSONObject(i);

                    AdmCountryCode = vo_country_prog_itmData.getString("AdmCountryCode");
                    AdmDonorCode = vo_country_prog_itmData.getString("AdmDonorCode");
                    AdmAwardCode = vo_country_prog_itmData.getString("AdmAwardCode");
                    AdmProgCode = vo_country_prog_itmData.getString("AdmProgCode");
                    AdmSrvCode = vo_country_prog_itmData.getString("AdmSrvCode");
                    CatCode = vo_country_prog_itmData.getString("CatCode");
                    ItmCode = vo_country_prog_itmData.getString("ItmCode");
                    MeasRCode = vo_country_prog_itmData.getString("MeasRCode");
                    VOItmSpec = vo_country_prog_itmData.getString(Parser.VO_ITM_SPEC);
                    UnitCost = vo_country_prog_itmData.getString("UnitCost");
                    Active = vo_country_prog_itmData.getString("Active");
                    Currency = vo_country_prog_itmData.getString("Currency");


                    db.addVoucherCountryProgItemFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AdmSrvCode, CatCode, ItmCode, MeasRCode, VOItmSpec, UnitCost, Active, Currency);

                    //                        Log.d(TAG, "In Voucher Country  Prog Item  table : AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode);

                }
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.LUP_GPS_TABLE_JSON_A)) {
                JSONArray lup_gps_table_Datas = jObj.getJSONArray(Parser.LUP_GPS_TABLE_JSON_A);
                size = lup_gps_table_Datas.length();
                for (int i = 0; i < size; i++) {
                    JSONObject lup_gps_tableData = lup_gps_table_Datas.getJSONObject(i);

                    String GrpCode = lup_gps_tableData.getString("GrpCode");
                    String SubGrpCode = lup_gps_tableData.getString("SubGrpCode");
                    String AttributeCode = lup_gps_tableData.getString("AttributeCode");
                    String LookUpCode = lup_gps_tableData.getString("LookUpCode");
                    String LookUpName = lup_gps_tableData.getString("LookUpName");


                    db.addLUP_GPS_TableFromOnline(GrpCode, SubGrpCode, AttributeCode, LookUpCode, LookUpName);


                }
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.GPS_SUB_GROUP_ATTRIBUTES_JSON_A)) {
                JSONArray gps_sub_group_attributes_Datas = jObj.getJSONArray(Parser.GPS_SUB_GROUP_ATTRIBUTES_JSON_A);
                size = gps_sub_group_attributes_Datas.length();
                for (int i = 0; i < size; i++) {
                    JSONObject gps_sub_group_attributes_Data = gps_sub_group_attributes_Datas.getJSONObject(i);


                    String GrpCode = gps_sub_group_attributes_Data.getString("GrpCode");
                    String SubGrpCode = gps_sub_group_attributes_Data.getString("SubGrpCode");
                    String AttributeCode = gps_sub_group_attributes_Data.getString("AttributeCode");
                    String AttributeTitle = gps_sub_group_attributes_Data.getString("AttributeTitle");
                    String DataType = gps_sub_group_attributes_Data.getString("DataType");
                    String LookUpCode = gps_sub_group_attributes_Data.getString("LookUpCode");


                    db.addGPS_SubGroupAttributesFromOnline(GrpCode, SubGrpCode, AttributeCode, AttributeTitle, DataType, LookUpCode);


                }
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull(Parser.GPS_LOCATION_ATTRIBUTES_JSON_A)) {
                JSONArray gps_location_attributes_Datas = jObj.getJSONArray(Parser.GPS_LOCATION_ATTRIBUTES_JSON_A);
                size = gps_location_attributes_Datas.length();
                for (int i = 0; i < size; i++) {
                    JSONObject gps_location_attributes_Data = gps_location_attributes_Datas.getJSONObject(i);


                    String AdmCountryCode = gps_location_attributes_Data.getString("AdmCountryCode");
                    String GrpCode = gps_location_attributes_Data.getString("GrpCode");
                    String SubGrpCode = gps_location_attributes_Data.getString("SubGrpCode");
                    String LocationCode = gps_location_attributes_Data.getString("LocationCode");
                    String AttributeCode = gps_location_attributes_Data.getString("AttributeCode");
                    String AttributeValue = gps_location_attributes_Data.getString("AttributeValue");
                    String AttPhoto = gps_location_attributes_Data.getString("AttPhoto");

                    db.addGPSLocationAttributesFromOnline(AdmCountryCode, GrpCode, SubGrpCode, LocationCode, AttributeCode, AttributeValue, AttPhoto);


                }
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull("community_group")) {

                Parser.CommunityGroupParser(jObj.getJSONArray("community_group"), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("lup_community_animal")) {

                Parser.lupCommunityAnimalParser(jObj.getJSONArray("lup_community_animal"), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("lup_prog_group_crop")) {
                Parser.lupProgramGroupCropParser(jObj.getJSONArray("lup_prog_group_crop"), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("lup_community_loan_source")) {
                Parser.lupCommunityLoanSourceParser(jObj.getJSONArray("lup_community_loan_source"), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("lup_community_fund_source")) {
                Parser.lupCommunityFundSourceParser(jObj.getJSONArray("lup_community_fund_source"), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("lup_community_irrigation_system")) {
                Parser.lupCommunityIrrigationSystemParser(jObj.getJSONArray("lup_community_irrigation_system"), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("lup_community__lead_position")) {
                Parser.lupCommunityLeadPositionParser(jObj.getJSONArray("lup_community__lead_position"), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("community_group_category")) {
                Parser.lupCommunityGroupCategoryParser(jObj.getJSONArray("community_group_category"), db);
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("lup_reg_n_add_lookup")) {
                JSONArray lup_reg_n_add_lookup = jObj.getJSONArray("lup_reg_n_add_lookup");
                size = lup_reg_n_add_lookup.length();
                for (int i = 0; i < size; i++) {
                    JSONObject Lup_reg_n_add_lookup = lup_reg_n_add_lookup.getJSONObject(i);


                    String AdmCountryCode = Lup_reg_n_add_lookup.getString("AdmCountryCode");
                    String RegNAddLookupCode = Lup_reg_n_add_lookup.getString("RegNAddLookupCode");
                    String RegNAddLookup = Lup_reg_n_add_lookup.getString("RegNAddLookup");
                    String LayR1ListCode = Lup_reg_n_add_lookup.getString("LayR1ListCode");
                    String LayR2ListCode = Lup_reg_n_add_lookup.getString("LayR2ListCode");
                    String LayR3ListCode = Lup_reg_n_add_lookup.getString("LayR3ListCode");
                    String LayR4ListCode = Lup_reg_n_add_lookup.getString("LayR4ListCode");


                    db.addLUP_RegNAddLookup(AdmCountryCode, RegNAddLookupCode, RegNAddLookup, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode);

                }
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull("prog_org_n_role")) {
                JSONArray prog_org_n_role_Datas = jObj.getJSONArray("prog_org_n_role");
                size = prog_org_n_role_Datas.length();
                for (int i = 0; i < size; i++) {
                    JSONObject prog_org_n_role_Data = prog_org_n_role_Datas.getJSONObject(i);
                    String AdmCountryCode = prog_org_n_role_Data.getString("AdmCountryCode");
                    String AdmDonorCode = prog_org_n_role_Data.getString("AdmDonorCode");
                    String AdmAwardCode = prog_org_n_role_Data.getString("AdmAwardCode");
                    String OrgNCode = prog_org_n_role_Data.getString("OrgNCode");
                    String PrimeYN = prog_org_n_role_Data.getString("PrimeYN");
                    String SubYN = prog_org_n_role_Data.getString("SubYN");
                    String TechYN = prog_org_n_role_Data.getString("TechYN");
                    String LogYN = prog_org_n_role_Data.getString("LogYN");
                    String ImpYN = prog_org_n_role_Data.getString("ImpYN");
                    String OthYN = prog_org_n_role_Data.getString("OthYN");

                    db.insertIntoProgOrgNRole(AdmCountryCode, AdmDonorCode, AdmAwardCode, OrgNCode, PrimeYN, SubYN, TechYN, ImpYN, LogYN, OthYN);
                }
            }
            publishProgress(++progressIncremental);
            if (!jObj.isNull("org_n_code")) {
                JSONArray org_n_code_Datas = jObj.getJSONArray("org_n_code");
                size = org_n_code_Datas.length();
                for (int i = 0; i < size; i++) {
                    JSONObject org_n_code_Data = org_n_code_Datas.getJSONObject(i);
                    String OrgNCode = org_n_code_Data.getString("OrgNCode");
                    String OrgNName = org_n_code_Data.getString("OrgNName");
                    String OrgNShortName = org_n_code_Data.getString("OrgNShortName");

                    db.insertIntoProgOrgN(OrgNCode, OrgNName, OrgNShortName);
                }
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("community_grp_detail")) {

                Parser.CommunityGroupDetailsParser(jObj.getJSONArray("community_grp_detail"), db);
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull("staff_master")) {
                JSONArray staff_master_Datas = jObj.getJSONArray("staff_master");
                size = staff_master_Datas.length();

                String StfCode;
                String OrigAdmCountryCode;
                String StfName;
                String OrgNCode;
                String OrgNDesgNCode;
                String StfStatus;
                String StfCategory;
                String UsrLogInName;
                String UsrLogInPW;
                String StfAdminRole;
                for (int i = 0; i < size; i++) {
                    JSONObject staff_master_Data = staff_master_Datas.getJSONObject(i);


                    StfCode = staff_master_Data.getString("StfCode");
                    OrigAdmCountryCode = staff_master_Data.getString("OrigAdmCountryCode");
                    StfName = staff_master_Data.getString("StfName");
                    OrgNCode = staff_master_Data.getString("OrgNCode");
                    OrgNDesgNCode = staff_master_Data.getString("OrgNDesgNCode");
                    StfStatus = staff_master_Data.getString("StfStatus");
                    StfCategory = staff_master_Data.getString("StfCategory");
                    UsrLogInName = staff_master_Data.getString("UsrLogInName");
                    UsrLogInPW = staff_master_Data.getString("UsrLogInPW");
                    StfAdminRole = staff_master_Data.getString("StfAdminRole");


                    db.insertIntoStaffMasterTable(StfCode, OrigAdmCountryCode, StfName, OrgNCode, OrgNDesgNCode, StfStatus, StfCategory, UsrLogInName, UsrLogInPW, StfAdminRole);

                }
            }

            publishProgress(++progressIncremental);
            if (!jObj.isNull("gps_lup_list")) {
                JSONArray gps_lup_list_data = jObj.getJSONArray("gps_lup_list");
                size = gps_lup_list_data.length();

                String GrpCode;
                String SubGrpCode;
                String AttributeCode;
                String LupValueCode;
                String LupValueText;

                for (int i = 0; i < size; i++) {
                    JSONObject gps_lup_list = gps_lup_list_data.getJSONObject(i);


                    GrpCode = gps_lup_list.getString("GrpCode");
                    SubGrpCode = gps_lup_list.getString("SubGrpCode");
                    AttributeCode = gps_lup_list.getString("AttributeCode");
                    LupValueCode = gps_lup_list.getString("LupValueCode");
                    LupValueText = gps_lup_list.getString("LupValueText");


                    db.insertIntoLupGpsList(GrpCode, SubGrpCode, AttributeCode, LupValueCode, LupValueText);

                }
            }


            publishProgress(++progressIncremental);
            if (!jObj.isNull("staff_srv_center_access")) {
                Parser.staff_srv_center_accessParser(jObj.getJSONArray("staff_srv_center_access"), db);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
//        showProgressDialog();
        progressIncremental = 0;
    }

    @Override
    protected void onPostExecute(Void string) {

//        new Inject_DynamicTableIntoSQLite().execute();

    }
}