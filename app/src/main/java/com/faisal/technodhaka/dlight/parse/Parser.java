package com.faisal.technodhaka.dlight.parse;

import android.util.Base64;
import android.util.Log;

import com.faisal.technodhaka.dlight.data_model.AGR_DataModel;
import com.faisal.technodhaka.dlight.data_model.AdmCountryDataModel;
import com.faisal.technodhaka.dlight.data_model.CTDataModel;
import com.faisal.technodhaka.dlight.data_model.VillageItem;
import com.faisal.technodhaka.dlight.manager.SQLiteHandler;

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
    public static final String REQUEST_DATE = "RequestDate";
    public static final String PRINT_DATE = "PrintDate";
    public static final String PRINT_BY = "PrintBy";
    public static final String DELIVERY_DATE = "DeliveryDate";
    public static final String DELIVERED_BY = "DeliveredBy";
    public static final String DEL_STATUS = "DelStatus";
    private static final String P_BSC_MEM_NAME_1_FIRST = "P_BSCMemName1_First";
    private static final String P_BSC_MEM_NAME_1_MIDDLE = "P_BSCMemName1_Middle";
    private static final String P_BSC_MEM_NAME_1_LAST = "P_BSCMemName1_Last";
    private static final String P_BSC_MEM_1_TITLE_POSITION = "P_BSCMem1_TitlePosition";
    private static final String PROXY_TYPE_ID = "Proxy_Type_ID";
    private static final String PROXY_ID_NO = "Proxy_ID_NO";
    private static final String P_BSC_MEM_NAME_2_FIRST = "P_BSCMemName2_First";
    private static final String P_BSC_MEM_NAME_2_MIDDLE = "P_BSCMemName2_Middle";
    private static final String P_BSC_MEM_NAME_2_LAST = "P_BSCMemName2_Last";
    private static final String P_BSC_MEM_2_TITLE_POSITION = "P_BSCMem2_TitlePosition";
    private static final String V_BSC_MEM_NAME_1_FIRST = "V_BSCMemName1_First";
    private static final String V_BSC_MEM_NAME_1_MIDDLE = "V_BSCMemName1_Middle";
    private static final String V_BSC_MEM_NAME_1_LAST = "V_BSCMemName1_Last";
    private static final String V_BSC_MEM_1_TITLE_POSITION = "V_BSCMem1_TitlePosition";
    private static final String V_BSC_MEM_NAME_2_FIRST = "V_BSCMemName2_First";
    private static final String V_BSC_MEM_NAME_2_MIDDLE = "V_BSCMemName2_Middle";
    private static final String V_BSC_MEM_NAME_2_LAST = "V_BSCMemName2_Last";
    private static final String V_BSC_MEM_2_TITLE_POSITION = "V_BSCMem2_TitlePosition";
    private static final String PROXY_DESIGNATION = "Proxy_Designation";
    private static final String PROXY_NAME_FIRST = "Proxy_Name_First";
    private static final String PROXY_NAME_MIDDLE = "Proxy_Name_Middle";
    private static final String PROXY_NAME_LAST = "Proxy_Name_Last";
    private static final String PROXY_BIRTH_YEAR = "Proxy_BirthYear";
    private static final String PROXY_PHOTO = "Proxy_Photo";
    private static final String CHILD_DOB = "ChildDOB";
    private static final String ELDERLY = "Elderly";
    private static final String BIRTH_YEAR = "BirthYear";
    private static final String PHOTO = "Photo";
    private static final String TYPE_ID = "Type_ID";
    public static final String ADM_COUNTRY_NAME = "AdmCountryName";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String GRP_CODE = "GrpCode";
    public static final String MEM_TYPE_FLAG = "MemTypeFlag";
    public static final String GRP_NAME = "GrpName";
    public static final String DESCRIPTION = "Description";
    public static final String SUB_GRP_CODE = "SubGrpCode";
    public static final String SUB_GRP_NAME = "SubGrpName";


    // json array key name
    public static final String SERVICE_TABLE_JSON_A = "service_table";
    public static final String REGN_LM_JSON_A = "regn_lm";
    public static final String REGN_PW_JSON_A = "regn_pw";

    public static final String REG_N_AGR_JSON_A = "reg_n_agr";


    public static final String REG_MEM_CARD_REQUEST_JSON_A = "reg_mem_card_request";
    public static final String STAFF_FDP_ACCESS_JSON_A = "staff_fdp_access";
    public static final String FDP_MASTER_JSON_A = "fdp_master";
    public static final String DISTRIBUTION_TABLE_JSON_A = "distribution_table";
    public static final String LUP_SRV_OPTION_LIST = "lup_srv_option_list";
    public static final String CARD_PRINT_REASON_JSON_A = "card_print_reason";
    public static final String REPORT_TEMPLATE_JSON_A = "report_template";
    public static final String MEMBERS_JSON_A = "members";
    public static final String REGISTRATION_JSON_A = "registration";
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
    public static final String REGN_CU_2_JSON_A = "regn_cu2";
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
    public static final String REG_M_ASSIGN_PROG_SRV_JSON_A = "reg_m_assign_prog_srv";
    private static final String CA_2_DOB = "CA2DOB";
    private static final String CA_2_GRD_DATE = "CA2GRDDate";
    private static final String LMGRD_DATE = "LMGRDDate";
    private static final String LMDOB = "LMDOB";
    private static final String CU_2_DOB = "CU2DOB";
    private static final String CU_2_GRD_DATE = "CU2GRDDate";
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
    private static final String GRD_DATE = "GRDDate";
    private static final String LMP_DATE = "LMPDate";
    private static final String PWGRD_DATE = "PWGRDDate";
    private static final String AFT_CNT_WAGE_ENR = "AFTCntWageEnr";
    private static final String BRF_CNT_WAGE_ENR = "BRFCntWageEnr";
    private static final String BRF_ACRE_ORCHARDS = "BRFAcreOrchards";
    private static final String BRF_VAL_ORCHARDS = "BRFValOrchards";
    private static final String AFT_ACRE_ORCHARDS = "AFTAcreOrchards";
    private static final String AFT_VAL_ORCHARDS = "AFTValOrchards";
    private static final String BRF_VAL_EMPLOYMENT = "BRFValEmployment";
    private static final String AFT_VAL_EMPLOYMENT = "AFTValEmployment";
    private static final String BRF_VAL_REMITTANCES = "BRFValRemittances";
    private static final String EXTRA_CHRONICALLY_ILL_DISABLED_PERSON_BECAUSE_EBOLA = "ExtraChronicallyIllDisabledPersonBecauseEbola";
    private static final String BRF_CNT_CATTLE = "BRFCntCattle";
    private static final String BRF_VAL_CATTLE = "BRFValCattle";
    private static final String DISTRICT_NAME = "DistrictName";
    private static final String UPAZILLA_NAME = "UpazillaName";
    private static final String UNIT_NAME = "UnitName";
    public static final String VILLAGE_NAME = "VillageName";
    private static final String HH_MEM_ID = "HHMemID";
    private static final String MEM_NAME = "MemName";
    private static final String MEM_SEX = "MemSex";
    private static final String HH_RELATION = "HHRelation";
    private static final String DISABLED = "Disabled";
    private static final String MEM_AGE = "MemAge";
    private static final String MARITAL_STATUS = "MaritalStatus";
    private static final String CONTACT_NO = "ContactNo";
    private static final String MEM_OTHER_ID = "MemOtherID";
    private static final String MEM_NAME_FIRST = "MemName_First";
    private static final String MEM_NAME_MIDDLE = "MemName_Middle";
    private static final String MEM_NAME_LAST = "MemName_Last";
    private static final String REGISTRATION_ID = "RegistrationID";
    private static final String PERSON_NAME = "PersonName";
    private static final String SEX = "SEX";
    private static final String HH_SIZE = "HHSize";
    private static final String LATITUDE = "Latitude";
    private static final String LONGITUDE = "Longitude";
    private static final String AG_LAND = "AGLand";
    private static final String V_STATUS = "VStatus";
    private static final String M_STATUS = "MStatus";
    private static final String VSLA_GROUP = "VSLAGroup";
    private static final String GPS_LONG_SWAP = "GPSLongSwap";
    private static final String HH_HEAD_CAT = "HHHeadCat";
    private static final String LT_2_YRS_M = "LT2yrsM";
    private static final String LT_2_YRS_F = "LT2yrsF";
    private static final String M_2_TO_5_YRS = "M2to5yrs";
    private static final String F_2_TO_5_YRS = "F2to5yrs";
    private static final String M_6_TO_12_YRS = "M6to12yrs";
    private static final String AFT_CNT_CATTLE = "AFTCntCattle";
    private static final String F_6_TO_12_YRS = "F6to12yrs";
    private static final String M_13_TO_17_YRS = "M13to17yrs";
    private static final String BRF_CNT_OTHER = "BRFCntOther";
    private static final String BRF_VAL_OTHER = "BRFValOther";
    private static final String AFT_CNT_OTHER = "AFTCntOther";
    private static final String AFT_VAL_OTHER = "AFTValOther";
    private static final String BRF_ACRE_CULTIVABLE = "BRFAcreCultivable";
    private static final String BRF_VAL_CULTIVABLE = "BRFValCultivable";
    private static final String F_13_TO_17_YRS = "F13to17yrs";
    private static final String ORPHN_LT_18_YRS_M = "Orphn_LT18yrsM";
    public static final String MONTH_LABEL = "MonthLabel";
    public static final String USA_START_DATE = "UsaStartDate";
    public static final String USA_END_DATE = "UsaEndDate";
    private static final String ORPHN_LT_18_YRS_F = "Orphn_LT18yrsF";
    private static final String ADLT_18_TO_59_M = "Adlt_18to59M";
    private static final String ADLT_18_TO_59_F = "Adlt_18to59F";
    private static final String ELD_60_P_M = "Eld_60pM";
    private static final String ELD_60_P_F = "Eld_60pF";
    private static final String PLW = "PLW";
    private static final String CHRONICALLY_ILL = "ChronicallyIll";
    private static final String LIVING_DECEASED_CONTRACT_EBOLA = "LivingDeceasedContractEbola";
    public static final String REG_N_CT_JSON_A = "reg_n_ct";
    public static final String C_11_CT_PR = "C11_CT_PR";
    public static final String C_21_CT_PR = "C21_CT_PR";
    public static final String C_31_CT_PR = "C31_CT_PR";
    public static final String C_32_CT_PR = "C32_CT_PR";
    public static final String C_33_CT_PR = "C33_CT_PR";
    public static final String C_34_CT_PR = "C34_CT_PR";
    public static final String C_35_CT_PR = "C35_CT_PR";
    public static final String C_36_CT_PR = "C36_CT_PR";
    public static final String C_37_CT_PR = "C37_CT_PR";
    public static final String C_38_CT_PR = "C38_CT_PR";
    private static final String EXTRA_ELDERLY_PERSON_BECAUSE_EBOLA = "ExtraElderlyPersonBecauseEbola";
    private static final String EXTRA_CHILD_BECAUSE_EBOLA = "ExtraChildBecauseEbola";
    private static final String AFT_VAL_CATTLE = "AFTValCattle";
    private static final String BRF_CNT_SHEEP_GOATS = "BRFCntSheepGoats";
    private static final String BRF_VAL_SHEEP_GOATS = "BRFValSheepGoats";
    public static final String MULTIPLE_SRV = "MultipleSrv";
    public static final String DISTRIBUTION_EXT_TABLE_JSON_A = "distribution_ext_table";
    public static final String VO_ITM_SPEC = "VOItmSpec";
    public static final String VO_ITM_UNIT = "VOItmUnit";
    public static final String VO_REF_NUMBER = "VORefNumber";
    public static final String VO_ITM_COST = "VOItmCost";
    public static final String DEFAULT_FOOD_DAYS = "DefaultFoodDays";
    public static final String DEFAULT_N_FOOD_DAYS = "DefaultNFoodDays";
    public static final String DEFAULT_CASH_DAYS = "DefaultCashDays";
    public static final String DEFAULT_VO_DAYS = "DefaultVODays";
    public static final String SRV_SPECIFIC = "SrvSpecific";

    public static final String DIST_FLAG = "DistFlag";
    public static final String SRV_OP_MONTH_CODE = "SrvOpMonthCode";
    public static final String VO_ITM_MEAS_TABLE_JSON_A = "vo_itm_meas_table";
    public static final String LUP_GPS_TABLE_JSON_A = "lup_gps_table";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_JSON_A = "gps_sub_group_attributes";
    public static final String GPS_LOCATION_ATTRIBUTES_JSON_A = "gps_location_attributes";


    private static final String TAG = Parser.class.getSimpleName();
    public static final String COUNTRY_NO = "CountryNo";
    public static final String COUNTRIE_NO = "countrie_no";


    /**
     * This method desirizie the JSon data and insert in the  sqlite data base
     *
     * @param jsonString A String which is Json format
     * @param sqlH       SqLite Handler  Object reference
     */

    public static void RegistrationNHHParser(String jsonString, SQLiteHandler sqlH) {
        String AdmCountryCode, DistrictName, UpazillaName, UnitName, VillageName, RegNAddLookupCode, RegistrationID, RegNDate, PersonName, sex, HHSize, Latitude, Longitude, AGLand, VStatus, MStatus, EntryBy, EntryDate, VSLAGroup, GPSLongSwap;

        String LTp2Hectres, LT3mFoodStock, NoMajorCommonLiveStock, ReceiveNoFormalWages, NoIGA, RelyPiecework, HHHeadCat, LT2yrsM, LT2yrsF, M2to5yrs, F2to5yrs, M6to12yrs, F6to12yrs, M13to17yrs, F13to17yrs, Orphn_LT18yrsM, Orphn_LT18yrsF, Adlt_18to59M, Adlt_18to59F, Eld_60pM, Eld_60pF;
        String plw;
        String ChronicallyIll, LivingDeceasedContractEbola, ExtraChildBecauseEbola, ExtraElderlyPersonBecauseEbola, ExtraChronicallyIllDisabledPersonBecauseEbola, BRFCntCattle, BRFValCattle, AFTCntCattle, AFTValCattle, BRFCntSheepGoats, BRFValSheepGoats, AFTCntSheepGoats, AFTValSheepGoats, BRFCntPoultry, BRFValPoultry, AFTCntPoultry, AFTValPoultry, BRFCntOther, BRFValOther, AFTCntOther, AFTValOther, BRFAcreCultivable, BRFValCultivable, AFTAcreCultivable, AFTValCultivable, BRFAcreNonCultivable;
        String BRFValNonCultivable, AFTAcreNonCultivable, AFTValNonCultivable, BRFAcreOrchards, BRFValOrchards, AFTAcreOrchards, AFTValOrchards, BRFValCrop, AFTValCrop, BRFValLivestock, AFTValLivestock, BRFValSmallBusiness, AFTValSmallBusiness, BRFValEmployment, AFTValEmployment, BRFValRemittances, AFTValRemittances, BRFCntWageEnr, AFTCntWageEnr, WRank;


        try {


            int size;

            /**
             * The total string Convert into JSON object
             * */

            JSONObject jObj = new JSONObject(jsonString);


            // Adding existing registration data into local database
            if (!jObj.isNull(REGISTRATION_JSON_A)) {

                JSONArray registration = jObj.getJSONArray(REGISTRATION_JSON_A);


                // Adding data into Registration Table
                size = registration.length();
                for (int i = 0; i < size; i++) {


                    JSONObject reg = registration.getJSONObject(i);

                    AdmCountryCode = reg.getString(ADM_COUNTRY_CODE);
                    DistrictName = reg.getString(DISTRICT_NAME);
                    UpazillaName = reg.getString(UPAZILLA_NAME);
                    UnitName = reg.getString(UNIT_NAME);
                    VillageName = reg.getString(VILLAGE_NAME);
                    RegistrationID = reg.getString(REGISTRATION_ID);
                    RegNDate = reg.getString(REG_N_DATE);
                    PersonName = reg.getString(PERSON_NAME);
                    sex = reg.getString(SEX);
                    HHSize = reg.getString(HH_SIZE);
                    Latitude = reg.getString(LATITUDE);
                    Longitude = reg.getString(LONGITUDE);
                    AGLand = reg.getString(AG_LAND);
                    VStatus = reg.getString(V_STATUS);
                    MStatus = reg.getString(M_STATUS);
                    EntryBy = reg.getString(ENTRY_BY);
                    EntryDate = reg.getString(ENTRY_DATE);
                    VSLAGroup = reg.getString(VSLA_GROUP);
                    GPSLongSwap = reg.getString(GPS_LONG_SWAP);
                    RegNAddLookupCode = reg.getString("RegNAddLookupCode");
                    LTp2Hectres = reg.getString("LTp2Hectres");
                    LT3mFoodStock = reg.getString("LT3mFoodStock");
                    NoMajorCommonLiveStock = reg.getString("NoMajorCommonLiveStock");
                    ReceiveNoFormalWages = reg.getString("ReceiveNoFormalWages");
                    NoIGA = reg.getString("NoIGA");
                    RelyPiecework = reg.getString("RelyPiecework");

                    HHHeadCat = reg.getString(HH_HEAD_CAT);
                    LT2yrsM = reg.getString(LT_2_YRS_M);
                    LT2yrsF = reg.getString(LT_2_YRS_F);
                    M2to5yrs = reg.getString(M_2_TO_5_YRS);
                    F2to5yrs = reg.getString(F_2_TO_5_YRS);
                    M6to12yrs = reg.getString(M_6_TO_12_YRS);
                    F6to12yrs = reg.getString(F_6_TO_12_YRS);
                    M13to17yrs = reg.getString(M_13_TO_17_YRS);
                    F13to17yrs = reg.getString(F_13_TO_17_YRS);
                    Orphn_LT18yrsM = reg.getString(ORPHN_LT_18_YRS_M);
                    Orphn_LT18yrsF = reg.getString(ORPHN_LT_18_YRS_F);
                    Adlt_18to59M = reg.getString(ADLT_18_TO_59_M);
                    Adlt_18to59F = reg.getString(ADLT_18_TO_59_F);
                    Eld_60pM = reg.getString(ELD_60_P_M);
                    Eld_60pF = reg.getString(ELD_60_P_F);
                    plw = reg.getString(PLW);
                    ChronicallyIll = reg.getString(CHRONICALLY_ILL);
                    LivingDeceasedContractEbola = reg.getString(LIVING_DECEASED_CONTRACT_EBOLA);
                    ExtraChildBecauseEbola = reg.getString(EXTRA_CHILD_BECAUSE_EBOLA);
                    ExtraElderlyPersonBecauseEbola = reg.getString(EXTRA_ELDERLY_PERSON_BECAUSE_EBOLA);
                    ExtraChronicallyIllDisabledPersonBecauseEbola = reg.getString(EXTRA_CHRONICALLY_ILL_DISABLED_PERSON_BECAUSE_EBOLA);
                    BRFCntCattle = reg.getString(BRF_CNT_CATTLE);
                    BRFValCattle = reg.getString(BRF_VAL_CATTLE);
                    AFTCntCattle = reg.getString(AFT_CNT_CATTLE);
                    AFTValCattle = reg.getString(AFT_VAL_CATTLE);
                    BRFCntSheepGoats = reg.getString(BRF_CNT_SHEEP_GOATS);
                    BRFValSheepGoats = reg.getString(BRF_VAL_SHEEP_GOATS);
                    AFTCntSheepGoats = reg.getString("AFTCntSheepGoats");
                    AFTValSheepGoats = reg.getString("AFTValSheepGoats");
                    BRFCntPoultry = reg.getString("BRFCntPoultry");
                    BRFValPoultry = reg.getString("BRFValPoultry");
                    AFTCntPoultry = reg.getString("AFTCntPoultry");
                    AFTValPoultry = reg.getString("AFTValPoultry");
                    BRFCntOther = reg.getString(BRF_CNT_OTHER);
                    BRFValOther = reg.getString(BRF_VAL_OTHER);
                    AFTCntOther = reg.getString(AFT_CNT_OTHER);
                    AFTValOther = reg.getString(AFT_VAL_OTHER);
                    BRFAcreCultivable = reg.getString(BRF_ACRE_CULTIVABLE);
                    BRFValCultivable = reg.getString(BRF_VAL_CULTIVABLE);
                    AFTAcreCultivable = reg.getString("AFTAcreCultivable");
                    AFTValCultivable = reg.getString("AFTValCultivable");
                    BRFAcreNonCultivable = reg.getString("BRFAcreNonCultivable");
                    BRFValNonCultivable = reg.getString("BRFValNonCultivable");
                    AFTAcreNonCultivable = reg.getString("AFTAcreNonCultivable");
                    AFTValNonCultivable = reg.getString("AFTValNonCultivable");
                    BRFAcreOrchards = reg.getString(BRF_ACRE_ORCHARDS);
                    BRFValOrchards = reg.getString(BRF_VAL_ORCHARDS);
                    AFTAcreOrchards = reg.getString(AFT_ACRE_ORCHARDS);
                    AFTValOrchards = reg.getString(AFT_VAL_ORCHARDS);
                    BRFValCrop = reg.getString("BRFValCrop");
                    AFTValCrop = reg.getString("AFTValCrop");
                    BRFValLivestock = reg.getString("BRFValLivestock");
                    AFTValLivestock = reg.getString("AFTValLivestock");
                    BRFValSmallBusiness = reg.getString("BRFValSmallBusiness");
                    AFTValSmallBusiness = reg.getString("AFTValSmallBusiness");
                    BRFValEmployment = reg.getString(BRF_VAL_EMPLOYMENT);
                    AFTValEmployment = reg.getString(AFT_VAL_EMPLOYMENT);
                    BRFValRemittances = reg.getString(BRF_VAL_REMITTANCES);
                    AFTValRemittances = reg.getString("AFTValRemittances");
                    BRFCntWageEnr = reg.getString(BRF_CNT_WAGE_ENR);
                    AFTCntWageEnr = reg.getString(AFT_CNT_WAGE_ENR);
                    WRank = reg.getString("WRank");


                    String Sync = "1";


                    sqlH.addRegistrationFromOnline(AdmCountryCode, DistrictName, UpazillaName, UnitName, VillageName, RegistrationID, RegNDate, PersonName, SEX, HHSize, Latitude, Longitude, AGLand, VStatus, MStatus, EntryBy, EntryDate, VSLAGroup, GPSLongSwap, RegNAddLookupCode, HHHeadCat, LT2yrsM, LT2yrsF, M2to5yrs, F2to5yrs
                            , M6to12yrs, F6to12yrs, M13to17yrs, F13to17yrs, Orphn_LT18yrsM, Orphn_LT18yrsF, Adlt_18to59M, Adlt_18to59F, Eld_60pM, Eld_60pF, PLW, ChronicallyIll, LivingDeceasedContractEbola, ExtraChildBecauseEbola, ExtraElderlyPersonBecauseEbola, ExtraChronicallyIllDisabledPersonBecauseEbola
                            , BRFCntCattle, BRFValCattle, AFTCntCattle, AFTValCattle, BRFCntSheepGoats, BRFValSheepGoats, AFTCntSheepGoats, AFTValSheepGoats, BRFCntPoultry, BRFValPoultry, AFTCntPoultry, AFTValPoultry, BRFCntOther, BRFValOther, AFTCntOther, AFTValOther, BRFAcreCultivable
                            , BRFValCultivable, AFTAcreCultivable, AFTValCultivable, BRFAcreNonCultivable, BRFValNonCultivable, AFTAcreNonCultivable, AFTValNonCultivable, BRFAcreOrchards, BRFValOrchards, AFTAcreOrchards, AFTValOrchards, BRFValCrop, AFTValCrop, BRFValLivestock, AFTValLivestock, BRFValSmallBusiness
                            , AFTValSmallBusiness, BRFValEmployment, AFTValEmployment, BRFValRemittances, AFTValRemittances, BRFCntWageEnr, AFTCntWageEnr, Sync, WRank
                            , LTp2Hectres, LT3mFoodStock, NoMajorCommonLiveStock, ReceiveNoFormalWages, NoIGA, RelyPiecework);


                }

                Log.d(TAG, "Total recode in Registration Table" + size);
            }


        } catch (Exception e) {
            Log.d(TAG, "Expetion : " + e);
            e.printStackTrace();
        }
    }

    /**
     * @param services_table The Json Array
     * @param sqlH           Database helper
     */

    public static void SrvTableParser(JSONArray services_table, SQLiteHandler sqlH) {

        String AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, hhID, MemID, ProgCode, SrvCode, OpCode, OpMonthCode, SrvSL, SrvCenterCode, SrvDT, SrvStatus, DistStatus, DistDT, fdpCode, /*GrpCode,*/ WD, DistFlag;


        int size;


        size = services_table.length();
        for (int i = 0; i < size; i++) {
            try {
                JSONObject service = services_table.getJSONObject(i);
                AdmCountryCode = service.getString(ADM_COUNTRY_CODE);
                AdmDonorCode = service.getString(ADM_DONOR_CODE);
                AdmAwardCode = service.getString(ADM_AWARD_CODE);
                LayR1ListCode = service.getString(LAY_R_1_LIST_CODE);
                LayR2ListCode = service.getString(LAY_R_2_LIST_CODE);
                LayR3ListCode = service.getString(LAY_R_3_LIST_CODE);
                LayR4ListCode = service.getString(LAY_R_4_LIST_CODE);
                hhID = service.getString(HHID);
                MemID = service.getString(MEM_ID);
                ProgCode = service.getString(PROG_CODE);
                SrvCode = service.getString(SRV_CODE);
                OpCode = service.getString(OP_CODE);
                OpMonthCode = service.getString(OP_MONTH_CODE);
                SrvSL = service.getString(SRV_SL);
                SrvCenterCode = service.getString(SRV_CENTER_CODE);
                SrvDT = service.getString(SRV_DT);
                SrvStatus = service.getString(SRV_STATUS);
                DistStatus = service.getString(DIST_STATUS);
                DistDT = service.getString(DIST_DT);
                fdpCode = service.getString(FDP_CODE);

                WD = service.getString("WD");
                DistFlag = service.getString("DistFlag");

                sqlH.addServiceFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode,
                        LayR2ListCode, LayR3ListCode, LayR4ListCode, hhID, MemID, ProgCode, SrvCode,
                        OpCode, OpMonthCode, SrvSL, SrvCenterCode, SrvDT, SrvStatus, DistStatus,
                        DistDT, fdpCode, WD, DistFlag/*, GrpCode, "1"*/);


              /*  Log.d(TAG, "In Service Table- AdmCountryCode :" + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " hhID : " + hhID + " MemID : " + MemID + " ProgCode : " + ProgCode + " SrvCode : " + SrvCode + " OpCode : " + OpCode + " OpMonthCode : " + OpMonthCode +
                        " SrvSL : " + SrvSL + "SrvDT: " + SrvDT + " SrvStatus : " + SrvStatus + " WD :" + WD);*/
            } catch (Exception e) {
                Log.d(TAG, "Exception : " + e);
                e.printStackTrace();
            }
            Log.d(TAG, "Total Record in Service table " + size);

        }


    }




    public static void RegNMemProGrpParser(String jsonString, SQLiteHandler sqlH) {


        try {


            int size;


            String AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, hhID, MemID, ProgCode, SrvCode, GrpCode, Active, GrpLayR1ListCode, GrpLayR2ListCode, GrpLayR3ListCode;

            /**             * The total string Convert into JSON object              */

            JSONObject jObj = new JSONObject(jsonString);


            // Adding existing members data into local database
            if (!jObj.isNull("reg_n_mem_prog_grp")) {

                JSONArray reg_n_mem_prog_grps = jObj.getJSONArray("reg_n_mem_prog_grp");


                // Adding data into Registration Table
                size = reg_n_mem_prog_grps.length();

                for (int i = 0; i < size; i++) {

                    JSONObject reg_n_mem_prog_grp = reg_n_mem_prog_grps.getJSONObject(i);


                    AdmCountryCode = reg_n_mem_prog_grp.getString("AdmCountryCode");
                    AdmDonorCode = reg_n_mem_prog_grp.getString("AdmDonorCode");
                    AdmAwardCode = reg_n_mem_prog_grp.getString("AdmAwardCode");
                    LayR1ListCode = reg_n_mem_prog_grp.getString("LayR1ListCode");
                    LayR2ListCode = reg_n_mem_prog_grp.getString("LayR2ListCode");
                    LayR3ListCode = reg_n_mem_prog_grp.getString("LayR3ListCode");
                    LayR4ListCode = reg_n_mem_prog_grp.getString("LayR4ListCode");
                    hhID = reg_n_mem_prog_grp.getString(HHID);
                    MemID = reg_n_mem_prog_grp.getString("MemID");
                    ProgCode = reg_n_mem_prog_grp.getString(PROG_CODE);
                    SrvCode = reg_n_mem_prog_grp.getString(SRV_CODE);
                    GrpCode = reg_n_mem_prog_grp.getString("GrpCode");
                    Active = reg_n_mem_prog_grp.getString("Active");
                    GrpLayR1ListCode = reg_n_mem_prog_grp.getString("GrpLayR1ListCode");
                    GrpLayR2ListCode = reg_n_mem_prog_grp.getString("GrpLayR2ListCode");
                    GrpLayR3ListCode = reg_n_mem_prog_grp.getString("GrpLayR3ListCode");


                    sqlH.addRegNmemProgGroupFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, hhID, MemID, ProgCode, SrvCode, GrpCode, Active, GrpLayR1ListCode, GrpLayR2ListCode, GrpLayR3ListCode);


                }
            }


        } catch (Exception e) {
            Log.d(TAG, "Expetion : " + e);
            e.printStackTrace();
        }

    }




    public static void GpsLocationContentParser(String jsonString, SQLiteHandler sqlH) {
        try {


            int size;
            String AdmCountryCode, GrpCode, SubGrpCode, LocationCode, ContentCode, ImageFileString, Remarks, EntryBy, EntryDate;


            /**
             * The total string Convert into JSON object
             * */

            JSONObject jObj = new JSONObject(jsonString);


            // Adding existing members data into local database
            if (!jObj.isNull("gps_location_content")) {

                JSONArray gps_location_contents = jObj.getJSONArray("gps_location_content");


                // Adding data into Registration Table
                size = gps_location_contents.length();

                for (int i = 0; i < size; i++) {

                    JSONObject gps_location_content = gps_location_contents.getJSONObject(i);


                    AdmCountryCode = gps_location_content.getString("AdmCountryCode");
                    GrpCode = gps_location_content.getString("GrpCode");
                    SubGrpCode = gps_location_content.getString("SubGrpCode");
                    LocationCode = gps_location_content.getString("LocationCode");
                    ContentCode = gps_location_content.getString("ContentCode");
                    ImageFileString = gps_location_content.getString("ImageFileString");
                    Remarks = gps_location_content.getString("Remarks");

                    byte[] imageByteArray;

                    if (ImageFileString.length() > 10) {
                        String tem = removeNewLineFromImage(ImageFileString);
                        String base64String = removeSlashFromImage(tem);


                        imageByteArray = Base64.decode(base64String, Base64.DEFAULT);

                    } else {
                        imageByteArray = null;
                    }

                    EntryBy = "";
                    EntryDate = "";

                    sqlH.insertIntoGPSLocationContentTable(AdmCountryCode, GrpCode, SubGrpCode, LocationCode, ContentCode, imageByteArray, Remarks, EntryBy, EntryDate);





                }
            }


        } catch (Exception e) {
            Log.d(TAG, "Expetion : " + e);
            e.printStackTrace();
        }

    }


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






    public static void staff_master_DataParser(JSONArray reg_m_assign_prog_srvs, SQLiteHandler sqlH) {

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

           /*     Log.d("NIR_192",
                        "in DTA table  StfCode :" + dtStfCoe +
                                " AdmCountryCode :" + admCountryCode +
                                " DTBasic :" + dtBasicCol +
                                " btnSave :" + dtBtnSave +
                                " EntryBy :" + entryBy +
                                " UsaEntryDate :" + usaEntryDate
                );*/


                sqlH.addIntoDtEnuTable(dtStfCoe, admCountryCode, dtBasicCol, dtBtnSave, entryBy, usaEntryDate);

                //  Log.d(TAG, "DT Enu Table index " + i);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }


    public static void DTBasicParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic, DTTitle, DTSubTitle, DTDescription, DTAutoScroll, DTAutoScrollText, DTActive, DTCategory, DTGeoListLevel, DTOPMode, DTShortName;


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

                sqlH.addIntoDTBasic(DTBasic, DTTitle, DTSubTitle, DTDescription, DTAutoScroll, DTAutoScrollText, DTActive, DTCategory, DTGeoListLevel, DTOPMode, DTShortName);

//                Log.d(TAG, " DTBasic Table");

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


                sqlH.addIntoGroupDetails(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCode, OrgCode, StfCode, LandSizeUnderIrrigation, IrrigationSystemUsed, FundSupport, ActiveStatus, RepName, RepPhoneNumber, FormationDate, TypeOfGroup, Status, EntryBy, EntryDate, ProjectNo, ProjectTitle, LayR1Code, LayR2Code, LayR3Code);


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


                sqlH.insertIntoAdmCountryAward(AdmCountryCode, AdmDonorCode, AdmAwardCode, AwardRefNumber, AwardStartDate, AwardEndDate, AwardShortName, AwardStatus);


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

    public static List<VillageItem> villageParser(JSONArray jsonArrayData) {

        int size = jsonArrayData.length();

        List<VillageItem> villageLists = new ArrayList<>();


        String GeoLayRName, AdmCountryCode, LayRCode, LayR4ListName;
        // ArrayList<AdmCountryDataModel> arrayList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                GeoLayRName = jsonObject.getString("GeoLayRName");
                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                LayRCode = jsonObject.getString("LayRCode");
                LayR4ListName = jsonObject.getString("LayR4ListName");


                VillageItem villageItem = new VillageItem(AdmCountryCode, GeoLayRName, LayR4ListName, LayRCode);

                villageLists.add(villageItem);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }

        }
        return villageLists;
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
