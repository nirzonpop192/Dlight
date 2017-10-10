package com.faisal.technodhaka.dlight.parse;

import android.util.Base64;
import android.util.Log;

import com.faisal.technodhaka.dlight.data_model.AdmCountryDataModel;
import com.faisal.technodhaka.dlight.data_model.VillageItem;
import com.faisal.technodhaka.dlight.database.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faisal on 7/16/2016.
 * this Class parse Json Deserialization  the Json data
 */
public class Parser extends Parse {

    /**
     * Json column values constant
     */
    public static final String ADM_COUNTRY_CODE = "AdmCountryCode";
    public static final String ADM_DONOR_CODE = "AdmDonorCode";
    public static final String ADM_AWARD_CODE = "AdmAwardCode";
    public static final String LAY_R_1_LIST_CODE = "LayR1ListCode";
    public static final String LAY_R_2_LIST_CODE = "LayR2ListCode";
    public static final String LAY_R_3_LIST_CODE = "LayR3ListCode";
    public static final String LAY_R_4_LIST_CODE = "LayR4ListCode";
    public static final String PROG_CODE = "ProgCode";
    public static final String SRV_CODE = "SrvCode";
    public static final String OP_MONTH_CODE = "OpMonthCode";
    public static final String FDP_CODE = "FDPCode";
    public static final String ID = "ID";
    //  public static final String ID = ID;
    public static final String DIST_STATUS = "DistStatus";
    public static final String HHID = "HHID";
    public static final String MEM_ID = "MemID";
    public static final String GEO_LAY_R_CODE = "GeoLayRCode";
    public static final String GEO_LAY_R_NAME = "GeoLayRName";
    public static final String LAY_R_4_LIST_NAME = "LayR4ListName";
    public static final String ADM_PROG_CODE = "AdmProgCode";
    public static final String ADM_SRV_CODE = "AdmSrvCode";
    public static final String GRD_CODE = "GRDCode";
    public static final String DEFAULT_CAT_ACTIVE = "DefaultCatActive";
    public static final String GRD_TITLE = "GRDTitle";
    public static final String DEFAULT_CAT_EXIT = "DefaultCatExit";
    public static final String LAY_R_1_CODE = "LayR1Code";
    public static final String LAY_R_2_CODE = "LayR2Code";
    public static final String WH_CODE = "WHCode";
    public static final String FDP_CAT_CODE = "FDPCatCode";
    public static final String FDP_NAME = "FDPName";
    public static final String RPT_GROUP = "RptGroup";
    public static final String RPT_CODE = "RptCode";
    public static final String REQUEST_SL = "RequestSL";
    public static final String BTN_NEW1 = "btnNew";
    public static final String BTN_NEW = BTN_NEW1;
    public static final String BTN_SAVE = "btnSave";
    public static final String BTN_DEL = "btnDel";
    public static final String PLANTING_VALUE_CHAIN_CROP = "PlantingValueChainCrop";
    public static final String VULNERABLE_HH = "VulnerableHH";
    public static final String WINTER_CULTIVATION = "WinterCultivation";
    public static final String WILLINGNESS = "Willingness";
    public static final String DEPEND_ON_GANYU = "DependOnGanyu";
    public static final String PROG_NAME = "ProgName";
    public static final String PROG_SHORT_NAME = "ProgShortName";
    public static final String REG_N_DATE = "RegNDate";
    public static final String ENTRY_BY = "EntryBy";
    public static final String ENTRY_DATE = "EntryDate";
    public static final String ELDERLY_YN = "ElderlyYN";
    public static final String LAND_SIZE = "LandSize";
    public static final String REASON_CODE = "ReasonCode";

    public static final String ADM_COUNTRY_NAME = "AdmCountryName";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String GRP_CODE = "GrpCode";

    public static final String GRP_NAME = "GrpName";
    public static final String DESCRIPTION = "Description";
    public static final String SUB_GRP_CODE = "SubGrpCode";
    public static final String SUB_GRP_NAME = "SubGrpName";





    public static final String STAFF_FDP_ACCESS_JSON_A = "staff_fdp_access";
    public static final String FDP_MASTER_JSON_A = "fdp_master";

    public static final String LUP_SRV_OPTION_LIST = "lup_srv_option_list";
    public static final String CARD_PRINT_REASON_JSON_A = "card_print_reason";
    public static final String REPORT_TEMPLATE_JSON_A = "report_template";

    public static final String VILLAGE_JSON_A = "village";
    public static final String UNIT_JSON_A = "unit";
    public static final String UPAZILLA_JSON_A = "upazilla";
    public static final String DISTRICT_JSON_A = "district";
    public static final String REG_LUP_GRADUATION_JSON_A = "reg_lup_graduation";
    public static final String LB_REG_HH_CATEGORY_JSON_A = "lb_reg_hh_category";
    public static final String REGN_CA_2 = "regn_ca2";
    public static final String ADM_PROGRAM_MASTER_JSON_A = "adm_program_master";
    public static final String ADM_SERVICE_MASTER_JSON_A = "adm_service_master";
    public static final String ADM_COUNTRY_AWARD_JSON_A = "adm_countryaward";
    public static final String ADM_AWARD_JSON_A = "adm_award";
    public static final String GPS_LOCATION_JSON_A = "gps_location";
    public static final String GPS_SUBGROUP_JSON_A = "gps_subgroup";
    public static final String GPS_GROUP_JSON_A = "gps_group";
    public static final String VALID_DATES_JSON_A = "valid_dates";
    public static final String COUNTRIES_JSON_A = "countries";

    public static final String USR_ID = "UsrID";

    public static final String STAFF_ACCESS_INFO_JSON_A = "staff_access_info";
    public static final String RELATION_JSON_A = "relation";
    public static final String USER_JSON_A = "user";
    public static final String ADM_DONOR_JSON_A = "adm_donor";
    public static final String ADM_OP_MONTH_JSON_A = "adm_op_month";
    public static final String ADM_COUNTRY_PROGRAM_JSON_A = "adm_country_program";
    public static final String DOB_SERVICE_CENTER_JSON_A = "dob_service_center";
    public static final String LAYER_LABELS_JSON_A = "layer_labels";

    public static final String FOOD_FLAG = "FoodFlag";
    public static final String N_FOOD_FLAG = "NFoodFlag";
    public static final String CASH_FLAG = "CashFlag";
    public static final String VO_FLAG = "VOFlag";

    public static final String SRV_CENTER_CODE = "SrvCenterCode";
    public static final String SRV_CENTER_NAME = "SrvCenterName";
    private static final String BTN_PEPR = "btnPepr";
    private static final String BTN_APRV = "btnAprv";
    private static final String BTN_REVW = "btnRevw";
    private static final String BTN_VRFY = "btnVrfy";
    private static final String BTN_D_TRAN = "btnDTran";
    public static final String STF_CODE = "StfCode";
    public static final String LAY_R_LIST_CODE = "LayRListCode";
    public static final String HH_HEAD_CAT_CODE = "HHHeadCatCode";
    public static final String CAT_NAME = "CatName";

    public static final String LAY_R_LIST_NAME = "LayRListName";
    public static final String LAY_R_2_LIST_NAME = "LayR2ListName";
    public static final String LAY_R_3_LIST_NAME = "LayR3ListName";
    public static final String HH_COUNT = "HHCount";
    public static final String HH_RELATION_CODE = "HHRelationCode";
    public static final String RELATION_NAME = "RelationName";
    public static final String RPT_LABEL = "RptLabel";
    public static final String RPT_G_N_CODE = "Code";
    public static final String REASON_TITLE = "ReasonTitle";
    public static final String LUP_OPTION_CODE = "LUPOptionCode";
    public static final String LUP_OPTION_NAME = "LUPOptionName";
    public static final String USR_LOG_IN_NAME = "UsrLogInName";
    public static final String USR_EMAIL_VERIFICATION = "UsrEmailVerification";
    public static final String USR_STATUS = "UsrStatus";
    public static final String USR_EMAIL = "UsrEmail";
    public static final String USR_LAST_NAME = "UsrLastName";
    public static final String USR_FIRST_NAME = "UsrFirstName";
    public static final String USR_LOG_IN_PW = "UsrLogInPW";

    private static final String LOCATION_CODE = "LocationCode";
    private static final String LOCATION_NAME = "LocationName";
    private static final String LONG = "Long";
    private static final String LATD = "Latd";
    private static final String AWARD_REF_NUMBER = "AwardRefNumber";
    private static final String AWARD_START_DATE = "AwardStartDate";
    private static final String AWARD_END_DATE = "AwardEndDate";
    private static final String AWARD_SHORT_NAME = "AwardShortName";
    private static final String AWARD_STATUS = "AwardStatus";
    private static final String ADM_DONOR_NAME = "AdmDonorName";
    public static final String OP_CODE = "OpCode";
    private static final String SRV_SL = "SrvSL";
    private static final String SRV_DT = "SrvDT";
    private static final String SRV_STATUS = "SrvStatus";
    private static final String DIST_DT = "DistDT";

    public static final String MONTH_LABEL = "MonthLabel";
    public static final String USA_START_DATE = "UsaStartDate";
    public static final String USA_END_DATE = "UsaEndDate";

    public static final String MULTIPLE_SRV = "MultipleSrv";

    public static final String VO_ITM_SPEC = "VOItmSpec";

    public static final String DEFAULT_FOOD_DAYS = "DefaultFoodDays";
    public static final String DEFAULT_N_FOOD_DAYS = "DefaultNFoodDays";
    public static final String DEFAULT_CASH_DAYS = "DefaultCashDays";
    public static final String DEFAULT_VO_DAYS = "DefaultVODays";
    public static final String SRV_SPECIFIC = "SrvSpecific";


    public static final String VO_ITM_MEAS_TABLE_JSON_A = "vo_itm_meas_table";
    public static final String LUP_GPS_TABLE_JSON_A = "lup_gps_table";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_JSON_A = "gps_sub_group_attributes";
    public static final String GPS_LOCATION_ATTRIBUTES_JSON_A = "gps_location_attributes";


    private static final String TAG = Parser.class.getSimpleName();
    public static final String COUNTRY_NO = "CountryNo";












    public static void gpsLocationParse(JSONArray gps_locations, SQLiteHandler sqlH) {
        String AdmCountryCode;
        String GrpCode;
        String SubGrpCode;
        String LocationCode;
        String LocationName;
        String Long;
        String Latd;
        int size = gps_locations.length();
        for (int i = 0; i < size; i++) {
            try {

                JSONObject gps_location = gps_locations.getJSONObject(i);
                AdmCountryCode = gps_location.getString(ADM_COUNTRY_CODE);
                GrpCode = gps_location.getString(GRP_CODE);
                SubGrpCode = gps_location.getString(SUB_GRP_CODE);
                LocationCode = gps_location.getString(LOCATION_CODE);
                LocationName = gps_location.getString(LOCATION_NAME);
                Long = gps_location.getString(LONG);
                Latd = gps_location.getString(LATD);


                sqlH.addGpsLocation(AdmCountryCode, GrpCode, SubGrpCode, LocationCode, LocationName, Latd, Long);


            } catch (Exception e) {
                Log.d(TAG, "Expetion : " + e);
                e.printStackTrace();
            }
        }
    }




    public static void DTA_Parser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic, DTQCode, DTACode, DTALabel, DTAValue, DTSeq, DTAShort, DTScoreCode, DTSkipDTQCode, DTACompareCode, ShowHide, MaxValue, MinValue, DataType, MarkOnGrid;

        Log.d(TAG, "The Number of the data inserted in DTable :" + size);
        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                DTQCode = jsonObject.getString("DTQCode");
                DTACode = jsonObject.getString("DTACode");
                DTALabel = jsonObject.getString("DTALabel");
                DTAValue = jsonObject.getString("DTAValue");
                DTSeq = jsonObject.getString("DTSeq");
                DTAShort = jsonObject.getString("DTAShort");
                DTScoreCode = jsonObject.getString("DTScoreCode");
                DTSkipDTQCode = jsonObject.getString("DTSkipDTQCode");
                DTACompareCode = jsonObject.getString("DTACompareCode");
                ShowHide = jsonObject.getString("ShowHide");
                MaxValue = jsonObject.getString("MaxValue");
                MinValue = jsonObject.getString("MinValue");
                DataType = jsonObject.getString("DataType");
                MarkOnGrid = jsonObject.getString("MarkOnGrid");

                sqlH.addIntoDTATable(DTBasic, DTQCode, DTACode, DTALabel, DTAValue, StringToLongNullCheck(DTSeq), DTAShort, DTScoreCode, DTSkipDTQCode, DTACompareCode, ShowHide, MaxValue, MinValue, DataType, MarkOnGrid, "", "");


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTEnu_Parser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String dtStfCoe;
        String admCountryCode;
        String dtBasicCol;
        String dtBtnSave;
        String entryBy;
        String usaEntryDate;

        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                dtStfCoe = jsonObject.getString("StfCode");
                admCountryCode = jsonObject.getString("AdmCountryCode");
                dtBasicCol = jsonObject.getString("DTBasic");
                dtBtnSave = jsonObject.getString("btnSave");
                entryBy = jsonObject.getString("EntryBy");
                usaEntryDate = jsonObject.getString("UsaEntryDate");




                sqlH.addIntoDtEnuTable(dtStfCoe, admCountryCode, dtBasicCol, dtBtnSave, entryBy, usaEntryDate);



            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }


    public static void DTBasicParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic, DTTitle, DTSubTitle, DTDescription, DTAutoScroll, DTAutoScrollText,
                DTActive, DTCategory, DTGeoListLevel, DTOPMode, DTShortName,EntryDate,EntryBy
                ,FreezePoint;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                DTTitle = jsonObject.getString("DTTitle");
                DTSubTitle = jsonObject.getString("DTSubTitle");
                DTDescription = jsonObject.getString("DTDescription");
                DTAutoScroll = jsonObject.getString("DTAutoScroll");
                DTAutoScrollText = jsonObject.getString("DTAutoScrollText");
                DTActive = jsonObject.getString("DTActive");
                DTCategory = jsonObject.getString("DTCategory");
                DTGeoListLevel = jsonObject.getString("DTGeoListLevel");
                DTOPMode = jsonObject.getString("DTOPMode");
                DTShortName = jsonObject.getString("DTShortName");
                EntryBy = jsonObject.getString("EntryBy");
                EntryDate = jsonObject.getString("EntryDate");
                FreezePoint = jsonObject.getString("FreezePoint");

                sqlH.addIntoDTBasic(DTBasic, DTTitle, DTSubTitle, DTDescription, DTAutoScroll,
                        DTAutoScrollText, DTActive, DTCategory, DTGeoListLevel, DTOPMode,
                        DTShortName,EntryBy,EntryDate,FreezePoint);



            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void DTCategoryParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String CategoryName, Frequency, DTCategory;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTCategory = jsonObject.getString("DTCategory");
                CategoryName = jsonObject.getString("CategoryName");
                Frequency = jsonObject.getString("Frequency");


                sqlH.addIntoDTCategory(DTCategory, CategoryName, Frequency, "", "");

                Log.d(TAG, " DTCategory Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void DTCountryProgramParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, ProgActivityCode, ProgActivityTitle, DTBasic, RefIdentifier, Status, RptFrequency;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                ProgActivityCode = jsonObject.getString("ProgActivityCode");
                ProgActivityTitle = jsonObject.getString("ProgActivityTitle");
                DTBasic = jsonObject.getString("DTBasic");
                RefIdentifier = jsonObject.getString("RefIdentifier");
                Status = jsonObject.getString("Status");
                RptFrequency = jsonObject.getString("RptFrequency");


                sqlH.addIntoDTCountryProgram(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, ProgActivityCode, ProgActivityTitle, DTBasic, RefIdentifier, Status, RptFrequency, "", "");

                Log.d(TAG, "D_T_CountryProgram");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTTableListCategoryParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String TableName, TableGroupCode, UseAdminOnly, UseReport, UseTransaction, UseLUP;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                TableName = jsonObject.getString("TableName");
                TableGroupCode = jsonObject.getString("TableGroupCode");
                UseAdminOnly = jsonObject.getString("UseAdminOnly");
                UseReport = jsonObject.getString("UseReport");
                UseTransaction = jsonObject.getString("UseTransaction");
                UseLUP = jsonObject.getString("UseLUP");

                sqlH.addIntoDTTableListCategory(TableName, TableGroupCode, UseAdminOnly, UseReport, UseTransaction, UseLUP);

                //    Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTTableDefinitionParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String TableName, FieldName, FieldDefinition, FieldShortName, ValueUDF, LUPTableName, AdminOnly;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                TableName = jsonObject.getString("TableName");
                FieldName = jsonObject.getString("FieldName");
                FieldDefinition = jsonObject.getString("FieldDefinition");
                FieldShortName = jsonObject.getString("FieldShortName");
                ValueUDF = jsonObject.getString("ValueUDF");
                LUPTableName = jsonObject.getString("LUPTableName");
                AdminOnly = jsonObject.getString("AdminOnly");

                sqlH.addIntoDTTableDefinition(TableName, FieldName, FieldDefinition, FieldShortName, ValueUDF, LUPTableName, AdminOnly, "", "");

                //  Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void DTResponseTableParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, DTRSeq, DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, ufile;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                DTEnuID = jsonObject.getString("DTEnuID");
                DTQCode = jsonObject.getString("DTQCode");
                DTACode = jsonObject.getString("DTACode");
                DTRSeq = jsonObject.getString("DTRSeq");
                DTAValue = jsonObject.getString("DTAValue");
                ProgActivityCode = jsonObject.getString("ProgActivityCode");
                DTTimeString = jsonObject.getString("DTTimeString");
                OpMode = jsonObject.getString("OpMode");
                OpMonthCode = jsonObject.getString("OpMonthCode");
                DataType = jsonObject.getString("DataType");
                ufile = "";
                sqlH.addIntoDTResponseTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, DTRSeq, DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, ufile, false);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTQResModeParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String QResMode, QResLupText, DataType, LookUpUDFName, ResponseValueControl;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                QResMode = jsonObject.getString("QResMode");
                QResLupText = jsonObject.getString("QResLupText");
                DataType = jsonObject.getString("DataType");
                LookUpUDFName = jsonObject.getString("LookUpUDFName");
                ResponseValueControl = jsonObject.getString("ResponseValueControl");

                sqlH.addIntoDTQResMode(QResMode, QResLupText, DataType, LookUpUDFName, ResponseValueControl);

                // Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTQTableParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic, DTQCode, QText, QResMode, QSeq, AllowNull, LUPTableName;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                DTQCode = jsonObject.getString("DTQCode");
                QText = jsonObject.getString("QText");
                QResMode = jsonObject.getString("QResMode");
                QSeq = jsonObject.getString("QSeq");
                AllowNull = jsonObject.getString("AllowNull");
                LUPTableName = jsonObject.getString("LUPTableName");

                sqlH.addIntoDTQTable(DTBasic, DTQCode, QText, QResMode, QSeq, AllowNull, LUPTableName);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTGeoListLevelParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String GeoLevel;
        String GeoLevelName;
        String ListUDFName;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);
                GeoLevel = jsonObject.getString("GeoLevel");
                GeoLevelName = jsonObject.getString("GeoLevelName");
                ListUDFName = jsonObject.getString("ListUDFName");
                sqlH.addIntoDTGeoListLevel(GeoLevel, GeoLevelName, ListUDFName, "", "");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }


    public static void DTLUPParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {


        String AdmCountryCode, TableName, ListCode, ListName;

        int size = jsonArrayData.length();
        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);
                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                TableName = jsonObject.getString("TableName");
                ListCode = jsonObject.getString("ListCode");
                ListName = jsonObject.getString("ListName");

                sqlH.addIntoDTLUP(AdmCountryCode, TableName, ListCode, ListName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTA_Skip_TableParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {


        String DTBasic, DTQCode, SkipCode, DTACodeCombN, DTSkipDTQCode;


        int size = jsonArrayData.length();
        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                DTQCode = jsonObject.getString("DTQCode");
                SkipCode = jsonObject.getString("SkipCode");
                DTACodeCombN = jsonObject.getString("DTACodeCombN");
                DTSkipDTQCode = jsonObject.getString("DTSkipDTQCode");


                sqlH.addIntoDTASkipTable(DTBasic, DTQCode, SkipCode, DTACodeCombN, DTSkipDTQCode);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void CommunityGroupParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCode, GrpName, GrpCatCode, LayR1Code, LayR2Code, LayR3Code, SrvCenterCode, EntryBy, EntryDate;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);
                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                GrpCode = jsonObject.getString("GrpCode");
                GrpName = jsonObject.getString("GrpName");
                GrpCatCode = jsonObject.getString("GrpCatCode");
                LayR1Code = jsonObject.getString("LayR1Code");
                LayR2Code = jsonObject.getString("LayR2Code");
                LayR3Code = jsonObject.getString("LayR3Code");

                SrvCenterCode = jsonObject.getString("SrvCenterCode");

                EntryBy = "";
                EntryDate = "";


                sqlH.addCommunityGroup(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode,
                        GrpCode, GrpName, GrpCatCode, LayR1Code, LayR2Code, LayR3Code, SrvCenterCode,
                        EntryBy, EntryDate);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }                                                                                           // end of for

    }



    public static void lupCommunityAnimalParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AnimalCode, AnimalType;

        for (int i = 0; i < size; i++) {
            try {
                JSONObject lup_community_animal_Data = jsonArrayData.getJSONObject(i);


                AdmCountryCode = lup_community_animal_Data.getString("AdmCountryCode");
                AdmDonorCode = lup_community_animal_Data.getString("AdmDonorCode");
                AdmAwardCode = lup_community_animal_Data.getString("AdmAwardCode");
                AdmProgCode = lup_community_animal_Data.getString("AdmProgCode");
                AnimalCode = lup_community_animal_Data.getString("AnimalCode");
                AnimalType = lup_community_animal_Data.getString("AnimalType");


                sqlH.addLUP_AnimalTypeFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AnimalCode, AnimalType);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void lupProgramGroupCropParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, CropCode, CropList, CropCatCode;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);


                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                CropCode = jsonObject.getString("CropCode");
                CropList = jsonObject.getString("CropList");
                CropCatCode = jsonObject.getString("CropCatCode");

                sqlH.addLUP_ProgramGroupCrop(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, CropCode, CropList, CropCatCode);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void lupCommunityLoanSourceParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, LoanCode, LoanSource;

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);


                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                LoanCode = jsonObject.getString("LoanCode");
                LoanSource = jsonObject.getString("LoanSource");


                sqlH.addLUP_CommunityLoanSource(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, LoanCode, LoanSource);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void lupCommunityFundSourceParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, FundCode, FundSource;

        for (int i = 0; i < size; i++) {
            try {
                JSONObject lup_community_loan_source_Data = jsonArrayData.getJSONObject(i);

                AdmCountryCode = lup_community_loan_source_Data.getString("AdmCountryCode");
                AdmDonorCode = lup_community_loan_source_Data.getString("AdmDonorCode");
                AdmAwardCode = lup_community_loan_source_Data.getString("AdmAwardCode");
                AdmProgCode = lup_community_loan_source_Data.getString("AdmProgCode");
                FundCode = lup_community_loan_source_Data.getString("FundCode");
                FundSource = lup_community_loan_source_Data.getString("FundSource");

                sqlH.addLUP_CommunityFundSource(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, FundCode, FundSource);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void lupCommunityIrrigationSystemParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, IrriSysCode, IrriSysName;

        for (int i = 0; i < size; i++) {
            try {
                JSONObject lup_community_loan_source_Data = jsonArrayData.getJSONObject(i);


                AdmCountryCode = lup_community_loan_source_Data.getString("AdmCountryCode");
                AdmDonorCode = lup_community_loan_source_Data.getString("AdmDonorCode");
                AdmAwardCode = lup_community_loan_source_Data.getString("AdmAwardCode");
                AdmProgCode = lup_community_loan_source_Data.getString("AdmProgCode");
                IrriSysCode = lup_community_loan_source_Data.getString("IrriSysCode");
                IrriSysName = lup_community_loan_source_Data.getString("IrriSysName");


                sqlH.addLUP_CommunityIrrigationSystem(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, IrriSysCode, IrriSysName);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void lupCommunityLeadPositionParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, LeadCode, LeadPosition;

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);


                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                LeadCode = jsonObject.getString("LeadCode");
                LeadPosition = jsonObject.getString("LeadPosition");


                sqlH.addLUP_CommunityLeadPostition(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, LeadCode, LeadPosition);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void lupCommunityGroupCategoryParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCatCode, GrpCatName, GrpCatShortName;

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);


                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                GrpCatCode = jsonObject.getString("GrpCatCode");
                GrpCatName = jsonObject.getString("GrpCatName");
                GrpCatShortName = jsonObject.getString("GrpCatShortName");


                sqlH.addCommunityGroupCategoryFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCatCode, GrpCatName, GrpCatShortName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void CommunityGroupDetailsParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCode, OrgCode, StfCode, LandSizeUnderIrrigation, IrrigationSystemUsed, FundSupport, ActiveStatus, RepName, RepPhoneNumber, FormationDate, TypeOfGroup, Status, EntryBy, EntryDate, ProjectNo, ProjectTitle, LayR1Code, LayR2Code, LayR3Code;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);


                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                GrpCode = jsonObject.getString("GrpCode");
                OrgCode = jsonObject.getString("OrgCode");
                StfCode = jsonObject.getString("StfCode");
                LandSizeUnderIrrigation = jsonObject.getString("LandSizeUnderIrrigation");
                IrrigationSystemUsed = jsonObject.getString("IrrigationSystemUsed");
                FundSupport = jsonObject.getString("FundSupport");
                ActiveStatus = jsonObject.getString("ActiveStatus");
                RepName = jsonObject.getString("RepName");
                RepPhoneNumber = jsonObject.getString("RepPhoneNumber");
                FormationDate = jsonObject.getString("FormationDate");
                TypeOfGroup = jsonObject.getString("TypeOfGroup");
                Status = jsonObject.getString("Status");
                ProjectNo = jsonObject.getString("ProjectNo");
                ProjectTitle = jsonObject.getString("ProjectTitle");

                LayR1Code = jsonObject.getString("LayR1Code");
                LayR2Code = jsonObject.getString("LayR2Code");
                LayR3Code = jsonObject.getString("LayR3Code");


                EntryBy = "";
                EntryDate = "";


                sqlH.addIntoGroupDetails(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode,
                        GrpCode, OrgCode, StfCode, LandSizeUnderIrrigation, IrrigationSystemUsed,
                        FundSupport, ActiveStatus, RepName, RepPhoneNumber, FormationDate,
                        TypeOfGroup, Status, EntryBy, EntryDate, ProjectNo, ProjectTitle,
                        LayR1Code, LayR2Code, LayR3Code);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }


    public static void admCountryAwardParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();


        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AwardRefNumber, AwardStartDate, AwardEndDate, AwardShortName, AwardStatus;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                AdmDonorCode = jsonObject.getString(Parser.ADM_DONOR_CODE);
                AdmAwardCode = jsonObject.getString(Parser.ADM_AWARD_CODE);
                AwardRefNumber = jsonObject.getString(Parser.AWARD_REF_NUMBER);
                AwardStartDate = jsonObject.getString(Parser.AWARD_START_DATE);
                AwardEndDate = jsonObject.getString(Parser.AWARD_END_DATE);
                AwardShortName = jsonObject.getString(Parser.AWARD_SHORT_NAME);
                AwardStatus = jsonObject.getString(Parser.AWARD_STATUS);


                sqlH.insertIntoAdmCountryAward(AdmCountryCode, AdmDonorCode, AdmAwardCode,
                        AwardRefNumber, AwardStartDate, AwardEndDate, AwardShortName, AwardStatus);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void admAwardParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();


        String AdmDonorCode, AwardCode, AwardName, AwardShort;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AwardCode = jsonObject.getString("AwardCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AwardName = jsonObject.getString("AwardName");
                AwardShort = jsonObject.getString("AwardShort");


                sqlH.insertIntoAdmAward(AdmDonorCode, AwardCode, AwardName, AwardShort);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }


    /**
     * Parse and insert into the db
     *
     * @param jsonArrayData country json array
     * @param sqlH          database
     */
    public static void AdmCountryParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        ArrayList<AdmCountryDataModel> arrayList = AdmCountryParser(jsonArrayData);


        for (int i = 0; i < arrayList.size(); i++) {
            sqlH.addCountry(arrayList.get(i).getAdmCountryCode(), arrayList.get(i).getAdmCountryName());


        }

    }

    public static String NumberOfCounteryAssignedUserParser(JSONArray jsonArrayData) {

        int size = jsonArrayData.length();
        String CountryNo = "0";

        for (int i = 0; i < size; i++) {
            try {
                JSONObject vil = jsonArrayData.getJSONObject(i);

                CountryNo = vil.getString(COUNTRY_NO);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }

        }
        return CountryNo;
    }

    /**
     * This method only used in LoginActivity
     *
     * @param jsonArrayData json data
     * @return AdmCountryDataModel array list
     */

    public static ArrayList<AdmCountryDataModel> AdmCountryParser(JSONArray jsonArrayData) {

        int size = jsonArrayData.length();


        String AdmCountryCode;
        String AdmCountryName;
        ArrayList<AdmCountryDataModel> arrayList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(ADM_COUNTRY_CODE);
                AdmCountryName = jsonObject.getString(ADM_COUNTRY_NAME);

                AdmCountryDataModel dataModel = new AdmCountryDataModel();
                dataModel.setAdmCountryCode(AdmCountryCode);
                dataModel.setAdmCountryName(AdmCountryName);

                arrayList.add(dataModel);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }

        }
        return arrayList;
    }


    public static void gpsGroupParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String GrpCode, GrpName, Description;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);
                GrpCode = jsonObject.getString(Parser.GRP_CODE);
                GrpName = jsonObject.getString(Parser.GRP_NAME);
                Description = jsonObject.getString(Parser.DESCRIPTION);

                sqlH.addGpsGroup(GrpCode, GrpName, Description);

//                        Log.d(TAG, " GroupCode : " + GrpCode + " GrpName : " + GrpName + " Description : " + Description);
            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void gpsSubGroupParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String GrpCode, SubGrpCode, SubGrpName, Description;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject gps_subgroup = jsonArrayData.getJSONObject(i);
                GrpCode = gps_subgroup.getString(Parser.GRP_CODE);
                SubGrpCode = gps_subgroup.getString(Parser.SUB_GRP_CODE);
                SubGrpName = gps_subgroup.getString(Parser.SUB_GRP_NAME);
                Description = gps_subgroup.getString(Parser.DESCRIPTION);

                sqlH.addGpsSubGroup(GrpCode, SubGrpCode, SubGrpName, Description);
            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void admDonorParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmDonorCode, AdmDonorName;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmDonorCode = jsonObject.getString(Parser.ADM_DONOR_CODE);
                AdmDonorName = jsonObject.getString(Parser.ADM_DONOR_NAME);
                sqlH.addDonorName(AdmDonorCode, AdmDonorName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void admProgramMasterParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmProgCode, AdmAwardCode, AdmDonorCode, ProgName, ProgShortName, MultipleSrv;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmProgCode = jsonObject.getString(Parser.ADM_PROG_CODE);
                AdmAwardCode = jsonObject.getString(Parser.ADM_AWARD_CODE);
                AdmDonorCode = jsonObject.getString(Parser.ADM_DONOR_CODE);
                ProgName = jsonObject.getString(Parser.PROG_NAME);
                ProgShortName = jsonObject.getString(Parser.PROG_SHORT_NAME);
                MultipleSrv = jsonObject.getString(Parser.MULTIPLE_SRV);
                sqlH.addAdmProgramMaster(AdmProgCode, AdmAwardCode, AdmDonorCode, ProgName, ProgShortName, MultipleSrv);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void admServiceMasterParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {
        int size = jsonArrayData.length();
        String AdmProgCode, AdmSrvCode, AdmSrvName, AdmSrvShortName;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmProgCode = jsonObject.getString(Parser.ADM_PROG_CODE);
                AdmSrvCode = jsonObject.getString(Parser.ADM_SRV_CODE);
                AdmSrvName = jsonObject.getString("AdmSrvName");
                AdmSrvShortName = jsonObject.getString("AdmSrvShortName");
                sqlH.addServiceMaster(AdmProgCode, AdmSrvCode, AdmSrvName, AdmSrvShortName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void admOpMonthParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {
        int size = jsonArrayData.length();


        String AdmCountryCode, AdmDonorCode, AdmAwardCode, OpCode, OpMonthCode, MonthLabel, StartDate, EndDate, UsaStartDate, UsaEndDate, Status;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                AdmDonorCode = jsonObject.getString(Parser.ADM_DONOR_CODE);
                AdmAwardCode = jsonObject.getString(Parser.ADM_AWARD_CODE);
                OpCode = jsonObject.getString(Parser.OP_CODE);
                OpMonthCode = jsonObject.getString(Parser.OP_MONTH_CODE);
                MonthLabel = jsonObject.getString(Parser.MONTH_LABEL);
                StartDate = jsonObject.getString(Parser.START_DATE);
                EndDate = jsonObject.getString(Parser.END_DATE);
                UsaStartDate = jsonObject.getString(Parser.USA_START_DATE);
                UsaEndDate = jsonObject.getString(Parser.USA_END_DATE);
                Status = jsonObject.getString("Status");
                sqlH.addOpMonthFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, OpCode, OpMonthCode, MonthLabel, StartDate, EndDate, UsaStartDate, UsaEndDate, Status);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void lupProgGroupCropParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, CropCode, CropList, CropCatCode;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);


                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                CropCode = jsonObject.getString("CropCode");
                CropList = jsonObject.getString("CropList");
                CropCatCode = jsonObject.getString("CropCatCode");

                sqlH.addLUP_ProgramGroupCrop(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, CropCode, CropList, CropCatCode);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void staff_access_infoParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String StfCode, AdmCountryCode, AdmDonorCode, AdmAwardCode, LayRListCode, btnNew, btnSave, btnDel, btnPepr, btnAprv, btnRevw, btnVrfy/*, disCode, upCode, unCode, vCode*/, btnDTran;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                StfCode = jsonObject.getString(Parser.STF_CODE);
                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                AdmDonorCode = jsonObject.getString(Parser.ADM_DONOR_CODE);
                AdmAwardCode = jsonObject.getString(Parser.ADM_AWARD_CODE);
                LayRListCode = jsonObject.getString(Parser.LAY_R_LIST_CODE);
                btnNew = jsonObject.getString(Parser.BTN_NEW1);
                btnSave = jsonObject.getString(Parser.BTN_SAVE);
                btnDel = jsonObject.getString(Parser.BTN_DEL);
                btnPepr = jsonObject.getString(Parser.BTN_PEPR);
                btnAprv = jsonObject.getString(Parser.BTN_APRV);
                btnRevw = jsonObject.getString(Parser.BTN_REVW);
                btnVrfy = jsonObject.getString(Parser.BTN_VRFY);
                btnDTran = jsonObject.getString(Parser.BTN_D_TRAN);

                //String FDPCode = dbo_staff_geo_info_access.getString("FDPCode");
//                disCode = LayRListCode.substring(0, 2);
//                upCode = LayRListCode.substring(2, 4);
//                unCode = LayRListCode.substring(4, 6);
//                vCode = LayRListCode.substring(6);
                sqlH.addStaffGeoAccessInfo(StfCode, AdmCountryCode, AdmDonorCode, AdmAwardCode, LayRListCode/*, disCode, upCode, unCode, vCode*/, btnNew, btnSave, btnDel, btnPepr, btnAprv, btnRevw, btnVrfy, btnDTran);//, SrvCenterCatCode, FDPCode);
            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void lupRegnHHCategoryParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, HHHeadCatCode, CatName;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                HHHeadCatCode = jsonObject.getString(Parser.HH_HEAD_CAT_CODE);
                CatName = jsonObject.getString(Parser.CAT_NAME);

                sqlH.addLupRegNHHCategory(AdmCountryCode, HHHeadCatCode, CatName);
            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void regNLupGraduationParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmProgCode, AdmSrvCode, GRDCode, GRDTitle, DefaultCatActive, DefaultCatExit;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmProgCode = jsonObject.getString(Parser.ADM_PROG_CODE);
                AdmSrvCode = jsonObject.getString(Parser.ADM_SRV_CODE);
                GRDCode = jsonObject.getString(Parser.GRD_CODE);
                GRDTitle = jsonObject.getString(Parser.GRD_TITLE);
                DefaultCatActive = jsonObject.getString(Parser.DEFAULT_CAT_ACTIVE);
                DefaultCatExit = jsonObject.getString(Parser.DEFAULT_CAT_EXIT);


                sqlH.addRegNLupGraduation(AdmProgCode, AdmSrvCode, GRDCode, GRDTitle, DefaultCatActive, DefaultCatExit);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }


    public static void geoLayRMasterParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, GeoLayRCode, GeoLayRName;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                GeoLayRCode = jsonObject.getString(Parser.GEO_LAY_R_CODE);
                GeoLayRName = jsonObject.getString(Parser.GEO_LAY_R_NAME);

                sqlH.addGeoLayRMaster(AdmCountryCode, GeoLayRCode, GeoLayRName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }


    public static void geoLayR1ListParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, GeoLayRCode, LayRListCode, LayRListName;

        Log.d(TAG, " geoLayR1List size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                GeoLayRCode = jsonObject.getString(Parser.GEO_LAY_R_CODE);
                LayRListCode = jsonObject.getString(Parser.LAY_R_LIST_CODE);
                LayRListName = jsonObject.getString(Parser.LAY_R_LIST_NAME);

                sqlH.addGeoLayR1List(AdmCountryCode, GeoLayRCode, LayRListCode, LayRListName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void geoLayR2ListParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR2ListName;

        Log.d(TAG, " geoLayR2List size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                GeoLayRCode = jsonObject.getString(Parser.GEO_LAY_R_CODE);
                LayR1ListCode = jsonObject.getString(Parser.LAY_R_1_LIST_CODE);
                LayR2ListCode = jsonObject.getString(Parser.LAY_R_2_LIST_CODE);
                LayR2ListName = jsonObject.getString(Parser.LAY_R_2_LIST_NAME);

                sqlH.addGeoLayR2List(AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR2ListName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }


    public static void geoLayR3ListParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR3ListName;
        Log.d(TAG, " geoLayR3List size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                GeoLayRCode = jsonObject.getString(Parser.GEO_LAY_R_CODE);
                LayR1ListCode = jsonObject.getString(Parser.LAY_R_1_LIST_CODE);
                LayR2ListCode = jsonObject.getString(Parser.LAY_R_2_LIST_CODE);
                LayR3ListCode = jsonObject.getString(Parser.LAY_R_3_LIST_CODE);
                LayR3ListName = jsonObject.getString(Parser.LAY_R_3_LIST_NAME);

                sqlH.addGeoLayR3List(AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR3ListName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void geoLayR4ListParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, LayR4ListName, HHCount;
        Log.d(TAG, " geoLayR4List size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                GeoLayRCode = jsonObject.getString(Parser.GEO_LAY_R_CODE);
                LayR1ListCode = jsonObject.getString(Parser.LAY_R_1_LIST_CODE);
                LayR2ListCode = jsonObject.getString(Parser.LAY_R_2_LIST_CODE);
                LayR3ListCode = jsonObject.getString(Parser.LAY_R_3_LIST_CODE);
                LayR4ListCode = jsonObject.getString(Parser.LAY_R_4_LIST_CODE);
                LayR4ListName = jsonObject.getString(Parser.LAY_R_4_LIST_NAME);
                HHCount = jsonObject.getString(Parser.HH_COUNT);

                sqlH.addGeoLayR4List(AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, LayR4ListName, HHCount);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void lupRegNHHRelationParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String Relation_Code, RelationName;

        Log.d(TAG, " lupRegNHHRelation size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                Relation_Code = jsonObject.getString(Parser.HH_RELATION_CODE);
                RelationName = jsonObject.getString(Parser.RELATION_NAME);

                sqlH.addLupRegNHHRelation(Relation_Code, RelationName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }


    public static void rptTemplateParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, RptLabel, Code;

        Log.d(TAG, " rptTemplateReport size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject report_template = jsonArrayData.getJSONObject(i);

                AdmCountryCode = report_template.getString(Parser.ADM_COUNTRY_CODE);
                RptLabel = report_template.getString(Parser.RPT_LABEL);
                Code = report_template.getString(Parser.RPT_G_N_CODE);

                sqlH.addRptTemplate(AdmCountryCode, RptLabel, Code);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void lupRegNCardPrintReasonParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String ReasonCode, ReasonTitle;
        Log.d(TAG, " LupRegNCardPrintReason size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                ReasonCode = jsonObject.getString(Parser.REASON_CODE);
                ReasonTitle = jsonObject.getString(Parser.REASON_TITLE);

                sqlH.addLupRegNCardPrintReason(ReasonCode, ReasonTitle);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void stffFdpAccessParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String StfCode, AdmCountryCode, FDPCode, btnNew, btnSave, btnDel;
        Log.d(TAG, " Staff FdpAccess size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                StfCode = jsonObject.getString(Parser.STF_CODE);
                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                FDPCode = jsonObject.getString(Parser.FDP_CODE);
                btnNew = jsonObject.getString(Parser.BTN_NEW);
                btnSave = jsonObject.getString(Parser.BTN_SAVE);
                btnDel = jsonObject.getString(Parser.BTN_DEL);


                sqlH.addStaffFDPAccess(StfCode, AdmCountryCode, FDPCode, btnNew, btnSave, btnDel);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void stffFdpMasterParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode, FDPCode, FDPName, FDPCatCode, WHCode, LayR1Code, LayR2Code;
        Log.d(TAG, " Staff FdpMaster size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                FDPCode = jsonObject.getString(Parser.FDP_CODE);
                FDPName = jsonObject.getString(Parser.FDP_NAME);
                FDPCatCode = jsonObject.getString(Parser.FDP_CAT_CODE);
                WHCode = jsonObject.getString(Parser.WH_CODE);
                LayR1Code = jsonObject.getString(Parser.LAY_R_1_CODE);
                LayR2Code = jsonObject.getString(Parser.LAY_R_2_CODE);


                sqlH.addFDPMaster(AdmCountryCode, FDPCode, FDPName, FDPCatCode, WHCode, LayR1Code, LayR2Code);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void lupSrvOptionListParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String countryCode, programCode, serviceCode, LUPOptionCode, LUPOptionName;
        Log.d(TAG, " Staff FdpMaster size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                countryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                programCode = jsonObject.getString(Parser.PROG_CODE);
                serviceCode = jsonObject.getString(Parser.SRV_CODE);
                LUPOptionCode = jsonObject.getString(Parser.LUP_OPTION_CODE);
                LUPOptionName = jsonObject.getString(Parser.LUP_OPTION_NAME);


                sqlH.addInLupSrvOptionListFromOnline(countryCode, programCode, serviceCode, LUPOptionCode, LUPOptionName);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void vo_itm_tableParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String CatCode, ItmCode, ItmName;
        Log.d(TAG, " vo itm table size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                CatCode = jsonObject.getString("CatCode");
                ItmCode = jsonObject.getString("ItmCode");
                ItmName = jsonObject.getString("ItmName");
                sqlH.addVoucherItemTableFromOnline(CatCode, ItmCode, ItmName);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void vo_itm_MassTableParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String MeasRCode, UnitMeas, MeasTitle;
        Log.d(TAG, " vo itm table size :" + size);

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                MeasRCode = jsonObject.getString("MeasRCode");
                UnitMeas = jsonObject.getString("UnitMeas");
                MeasTitle = jsonObject.getString("MeasTitle");


                sqlH.addVoucherItemMeasFromOnline(MeasRCode, UnitMeas, MeasTitle);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }


    public static void staff_srv_center_accessParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String StfCode, AdmCountryCode, btnNew, btnSave, btnDel, SrvCenterCatCode;
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                StfCode = jsonObject.getString(Parser.STF_CODE);
                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                SrvCenterCatCode = jsonObject.getString("SrvCenterCode");
                btnNew = jsonObject.getString(Parser.BTN_NEW1);
                btnSave = jsonObject.getString(Parser.BTN_SAVE);
                btnDel = jsonObject.getString(Parser.BTN_DEL);


                sqlH.addStaffSrvCenterAccess(StfCode, AdmCountryCode, SrvCenterCatCode, btnNew, btnSave, btnDel);//, SrvCenterCatCode, FDPCode);
            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }
    }


    public static void admCountryProgramParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {
        int size = jsonArrayData.length();
        String AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AdmSrvCode, ProgFlag, FoodFlag, NFoodFlag, CashFlag, VOFlag, DefaultFoodDays, DefaultNFoodDays, DefaultCashDays, DefaultVODays, SrvSpecific;

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                AdmDonorCode = jsonObject.getString(Parser.ADM_DONOR_CODE);
                AdmAwardCode = jsonObject.getString(Parser.ADM_AWARD_CODE);
                AdmProgCode = jsonObject.getString(Parser.ADM_PROG_CODE);
                AdmSrvCode = jsonObject.getString(Parser.ADM_SRV_CODE);
                ProgFlag = jsonObject.getString("ProgFlag");
                FoodFlag = jsonObject.getString(Parser.FOOD_FLAG);
                NFoodFlag = jsonObject.getString(Parser.N_FOOD_FLAG);
                CashFlag = jsonObject.getString(Parser.CASH_FLAG);
                VOFlag = jsonObject.getString(Parser.VO_FLAG);
                DefaultFoodDays = jsonObject.getString(Parser.DEFAULT_FOOD_DAYS);
                DefaultNFoodDays = jsonObject.getString(Parser.DEFAULT_N_FOOD_DAYS);
                DefaultCashDays = jsonObject.getString(Parser.DEFAULT_CASH_DAYS);
                DefaultVODays = jsonObject.getString(Parser.DEFAULT_VO_DAYS);
                SrvSpecific = jsonObject.getString(Parser.SRV_SPECIFIC);

                sqlH.insertAdmCountryProgram(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AdmSrvCode, ProgFlag, FoodFlag, NFoodFlag, CashFlag, VOFlag, DefaultFoodDays, DefaultNFoodDays, DefaultCashDays, DefaultVODays, SrvSpecific);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void srvCenterParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {
        int size = jsonArrayData.length();
        String AdmCountryCode, SrvCenterCode, SrvCenterName, SrvCenterAddress, SrvCenterCatCode;

        for (int i = 0; i < size; i++) {
            try {
                JSONObject dob_service_center = jsonArrayData.getJSONObject(i);

                AdmCountryCode = dob_service_center.getString(Parser.ADM_COUNTRY_CODE);
                SrvCenterCode = dob_service_center.getString(Parser.SRV_CENTER_CODE);
                SrvCenterName = dob_service_center.getString(Parser.SRV_CENTER_NAME);
                //  SrvCenterAddress = dob_service_center.getString("SrvCenterAddress");
                //    SrvCenterCatCode = dob_service_center.getString("SrvCenterCatCode");

                String FDPCode = dob_service_center.getString(Parser.FDP_CODE);


                sqlH.addServiceCenter(AdmCountryCode, SrvCenterCode, SrvCenterName, FDPCode);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


}// end of the class
