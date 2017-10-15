package com.faisal.technodhaka.dlight.database;

/**
 * This class is the Base Handler of all SQL operation
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;


import com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity;
import com.faisal.technodhaka.dlight.data_model.DTQResModeDataModel;
import com.faisal.technodhaka.dlight.data_model.DTResponseTableDataModel;
import com.faisal.technodhaka.dlight.data_model.DTSurveyTableDataModel;
import com.faisal.technodhaka.dlight.data_model.DT_ATableDataModel;

import com.faisal.technodhaka.dlight.data_model.DynamicDataIndexDataModel;

import com.faisal.technodhaka.dlight.data_model.LayRCodes;


import com.faisal.technodhaka.dlight.data_model.TemOpMonth;
import com.faisal.technodhaka.dlight.data_model.VillageItem;
import com.faisal.technodhaka.dlight.data_model.adapters.AssignDataModel;
import com.faisal.technodhaka.dlight.data_model.adapters.DTQTableDataModel;
import com.faisal.technodhaka.dlight.data_model.adapters.TaSummary;
import com.faisal.technodhaka.dlight.manager.SyncDatabase;
import com.faisal.technodhaka.dlight.utils.FileUtils;
import com.faisal.technodhaka.dlight.utils.UtilClass;


import com.faisal.technodhaka.dlight.views.helper.LocationHelper;
import com.faisal.technodhaka.dlight.views.helper.SpinnerHelper;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "Elite.db";

    public static final String EXTERNAL_DATABASE_NAME = "Elite_ex";
    // Android meta data table
    public static final String SQLITE_SEQUENCE = "SQLITE_SEQUENCE";
    public static final String TABLE_NAME = "NAME";
    public static final String SQL_QUERY_SYNTAX = "SqlQuery";
    public static final String UPLOAD_SYNTAX_TABLE = "UploadSyntax";
    public static final String UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE = "UploadPhysicalTableSyntax";
    public static final String FOOD_FLAG = "FoodFlag";
    public static final String PROG_FLAG = "ProgFlag";
    public static final String NON_FOOD_FLAG = "NFoodFlag";
    public static final String CASH_FLAG = "CashFlag";
    public static final String VOUCHER_FLAG = "VOFlag";
    public static final String IS_SELECTED_FLAG_COL = "IsSelectedFlag";
    public static final String ACTIVE = "A";
    public static final int REGISTRATION_OP_CODE = 1;
    public static final String ASSIGN_SUMMARY_PROGRAM_DETAILS = "assignSummaryProgramDetails";
    public static final String FDP_LAY_R2 = "FdpLayR2";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
    public static final String DISTRICT_CODE_COL = "DistrictCode";
    public static final String UPAZILLA_CODE_COL = "UpazillaCode";
    public static final String UNIT_CODE_COL = "UnitCode";
    public static final String VILLAGE_CODE_COL = "VillageCode";
    public static final String VILLAGE_NAME_COL = "VillageName";


    private String next_id = "";
    private static final int ID_LENGTH = 5;

    // Login table name
    // todo: rename table Name
    public static final String LOGIN_TABLE = "UsrLogIn";
    public static final String STAFF_MASTER_TABLE = "StaffMaster";
    public static final String STAFF_ID_COL = "StfCode";
    public static final String STAFF_NAME_COL = "StfName";
    public static final String STAFF_STATUS_COL = "StfStatus";
    public static final String STAFF_CATEGORY_COL = "StfCategory";
    public static final String STAFF_ADMIN_ROLE_COL = "StfAdminRole";


    public static final String COUNTRY_TABLE = "AdmCountry";
    public static final String VALID_DATE_RANGE = "ValidDateRange";
    public static final String GEO_LAY_R_MASTER_TABLE = "GeoLayRMaster";
    public static final String GEO_LAY_R1_LIST_TABLE = "GeoLayR1List";
    public static final String GEO_LAY_R2_LIST_TABLE = "GeoLayR2List";
    public static final String GEO_LAY_R3_LIST_TABLE = "GeoLayR3List";
    public static final String GEO_LAY_R4_LIST_TABLE = "GeoLayR4List";
    public static final String VILLAGE_TABLE_FOR_ASSIGN = "VillageForQuery"; // THIS KEY USE FOR QUERY IN ASSIGN
    //    public static final String REG_N_HH_TABLE = "RegNHHTable";
//    public static final String REGISTRATION_MEMBER_TABLE = "RegNHHMem";
    public static final String LUP_REG_NHH_RELATION_TABLE = "LUP_RegNHHRelation";


    public static final String SERVICE_TABLE = "SrvTable";
    public static final String ADM_COUNTRY_AWARD_TABLE = "AdmCountryAward";
    public static final String ADM_AWARD_TABLE = "AdmAward";
    public static final String ADM_DONOR_TABLE = "AdmDonor";
    public static final String ADM_PROGRAM_MASTER_TABLE = "AdmProgramMaster";
    public static final String SERVICE_MASTER_TABLE = "AdmServiceMaster";
    //    public static final String REG_N_ASSIGN_PROG_SRV_TABLE = "RegNAssignProgSrv";
    public static final String GPS_GROUP_TABLE = "GPSGroupTable";
    public static final String GPS_SUB_GROUP_TABLE = "GPSSubGroupTable";
    public static final String GPS_LOCATION_TABLE = "GPSLocationTable";
    public static final String OP_MONTH_TABLE = "AdmOpMonthTable";
    public static final String ADM_COUNTRY_PROGRAM_TABLE = "AdmCountryProgram";
    /**
     * temporary only use for Service  mode
     */
    public static final String TEMPORARY_COUNTRY_PROGRAM_TABLE = "TemCountryProgram";
    public static final String TEMPORARY_OP_MONTH_TABLE = "TemOpMonthTable";

    public static final String SRV_CENTER_TABLE = "SrvCenterTable";

    public static final String STAFF_GEO_INFO_ACCESS_TABLE = "StaffGeoInfoAccess";
    public static final String STAFF_SRV_CENTER_ACCESS_TABLE = "StaffSrvCenterAccess";

    public static final String LUP_REGNH_HEAD_CATEGORY_TABLE = "LUP_RegNHHHeadCategory";
    // public static final String LIBERIA_REGISTRATION_TABLE = "Liberia_Registration";

    public static final String REG_N_LUP_GRADUATION_TABLE = "RegNLUP_Graduation";

    public static final String REPORT_TEMPLATE_TABLE = "RptTemplateTable";
    public static final String LUP_REGN_CARD_PRINT_REASON_TABLE = "LUP_RegNCardPrintReason";
//    public static final String MEMBER_CARD_PRINT_TABLE = "RegNMemCardPrintTable";

    public static final String VOUCHER_ITEM_TABLE = "VOItmTable";
    public static final String VOUCHER_ITEM__MEAS_TABLE = "VOItmMeas";
    public static final String VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE = "VOCountryProgItm";
    public static final String SERVICE_EXTENDED_TABLE = "SrvExtendedTable";
    //    public static final String DISTRIBUTION_EXTENDED_TABLE = "DistExtendedTable";
    public static final String SELECTED_VILLAGE_TABLE = "SelectedVillage";
    public static final String SELECTED_COUNTRY_TABLE = "SelectedCountry";
    public static final String SELECTED_FDP_TABLE = "SelectedFDP";
    public static final String SELECTED_SERVICE_CENTER_TABLE = "SelectedCenter";
    public static final String COMMUNITY_GROUP_TABLE = "CommunityGroup";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_TABLE = "GPSSubGroupAttributes";
    public static final String LUP_GPS_TABLE = "GPSLUPList";
    public static final String LUP_COMMUNITY_ANIMAL_TABLE = "LUP_CommnityAnimalList";
    public static final String LUP_PROG_GROUP_CROP_TABLE = "LUP_ProgGroupCropList";
    public static final String LUP_COMMUNITY_LOAN_SOURCE_TABLE = "LUP_CommnityLoanSource";
    public static final String LUP_COMMUNITY_FUND_SOURCE_TABLE = "LUP_CommnityFundSource";
    public static final String LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE = "LUP_CommunityIrrigationSystem";


    public static final String COMMUNITY_GROUP_CATEGORY_TABLE = "CommunityGroupCategory";
    public static final String DTQRES_MODE_TABLE = "DTQResMode";
    public static final String GPS_LOCATION_CONTENT_TABLE = "GPSLocationContent";


    public static final String LUP_COMMUNITY_LEAD_POSITION_TABLE = "LUP_CommunityLeadPosition";

    public static final String GPS_LOCATION_ATTRIBUTES_TABLE = "GPSLocationAttributes";


    public static final String LAST_SYNC_TYRACE_TABLE = "LastSyncTrace";

    public static final String DTA_SKIP_TABLE = "DTASkipTable";

    public static final String LUP_TA_PATICIPANT_CAT_TABLE = "LUP_TAParticipantCat";


    public static final String LUP_REGN_ADDRESS_LOOKUP_TABLE = "LUP_RegNAddLookup";
    public static final String COMMUNITY_GRP_DETAIL_TABLE = "CommunityGrpDetail";
    public static final String DTGEO_LIST_LEVEL_TABLE = "DTGeoListLevel";
    public static final String PROGRAM_ORGANIZATION_ROLE_TABLE = "ProgOrgNRole";
    public static final String PROGRAM_ORGANIZATION_NAME_TABLE = "ProgOrgN";

    public static final String DT_RESPONSE_TABLE = "DTResponseTable";
    public static final String DT_SURVEY_TABLE = "DTSurveyTable";
    public static final String DT_CATEGORY_TABLE = "DTCategory";
    public static final String DT_COUNTRY_PROGRAM_TABLE = "DTCountryProgram";
    public static final String DT_ENU_TABLE = "DTEnuTable";
    public static final String DTGEO_LIST_LEVEL_COL = "DTGeoListLevel";
    public static final String DTQRES_MODE_COL = "DTQResMode";
    public static final String DTQ_TABLE = "DTQTable";
    public static final String DT_TABLE_DEFINITION_TABLE = "DTTableDefinition";
    public static final String DTTABLE_LIST_CATEGORY_TABLE = "DTTableListCategory";

    public static final String LIST_CODE_COL = "ListCode";
    public static final String LIST_NAME_COL = "ListName";
    public static final String DT_LUP_TABLE = "DTLUP";


    public static final String DT_A_TABLE = "DTATable";
    public static final String DT_BASIC_TABLE = "DTBasic";
    public static final String DT_BASIC_COL = "DTBasic";
    public static final String COUNTRY_CODE_COL = "CountryCode";
    public static final String PROGRAM_CODE_COL = "ProgramCode";
    public static final String DTQ_CODE_COL = "DTQCode";
    public static final String DTQ_TEXT_COL = "DTQText";
    public static final String DTA_CODE_COL = "DTACode";
    public static final String DTA_LABEL_COL = "DTALabel";
    public static final String SKIP_CODE_COL = "SkipCode";
    public static final String DTA_CODE_COMB_N_COL = "DTACodeCombN";

    public static final String DT_SURVEY_NUM = "DTSurveyNumber";

    public static final String DT_SEQ_COL = "DTSeq";
    public static final String DTA_SHORT_COL = "DTAShort";
    public static final String DT_SCORE_CODE_COL = "DTScoreCode";
    public static final String DTSKIP_DTQ_CODE_COL = "DTSkipDTQCode";


    public static final String DTA_COMPARE_CODE_COL = "DTACompareCode";
    public static final String DT_TITLE_COL = "DTTitle";
    public static final String DT_SUB_TITLE_COL = "DTSubTitle";
    public static final String DT_DESCRIPTION_COL = "DTDescription";
    public static final String DT_AUTO_SCROLL_COL = "DTAutoScroll";
    public static final String DTAUTO_SCROLL_TEXT = "DTAutoScrollText";
    public static final String DT_ACTIVE_COL = "DTActive";
    public static final String DT_CATEGORY_COL = "DTCategory";
    public static final String DT_GEO_LIST_LEVEL_COL = "DTGeoListLevel";
    public static final String DT_OP_MODE_COL = "DTOPMode";
    public static final String DT_SHORT_NAME_COL = "DTShortName";

    public static final String DT_STF_CODE_COL = "StfCode";
    public static final String DT_ADM_COUNTRY_CODE_COL = "AdmCountryCode";
    //    public static final String DT_BASIC_COL = "DTBasic";
    public static final String DT_BTN_SAVE_COL = "btnSave";
    public static final String DT_ENTRY_BY_COL = "EntryBy";
    public static final String DT_USA_ENTRY_DATE_COL = "EntryDate";
    //  training Activity
    public static final String EVENT_CODE_COL = "EventCode";
    public static final String EVENT_NAME_COL = "EventName";
    public static final String TA_GROUP_COL = "TAGroup";

    public static final String PART_CAT_TITLE_COL = "PartCatTitle";

    public static final String PART_ID_COL = "PartID";

    public static final String PART_CAT_CODE_COL = "PartCatCode";

    public static final String ATDN_DATE_COL = "AtdnDate";


    public static final String FREQUENCY_COL = "Frequency";
    public static final String PROG_ACTIVITY_CODE_COL = "ProgActivityCode";
    public static final String PROG_ACTIVITY_TITLE_COL = "ProgActivityTitle";
    public static final String REF_IDENTIFIER_COL = "RefIdentifier";
    public static final String RPT_FREQUENCY_COL = "RptFrequency";
    public static final String GEO_LEVEL_COL = "GeoLevel";
    public static final String GEO_LEVEL_NAME_COL = "GeoLevelName";
    public static final String LIST_UDF_NAME_COL = "ListUDFName";
    public static final String QRES_MODE_COL = "QResMode";
    public static final String QRES_LUP_TEXT_COL = "QResLupText";
    public static final String LOOK_UP_UDF_NAME_COL = "LookUpUDFName";
    public static final String RESPONSE_VALUE_CONTROL_COL = "ResponseValueControl";
    public static final String QTEXT_COL = "QText";
    public static final String ALLOW_NULL_COL = "AllowNull";
    public static final String LUP_TABLE_NAME = "LUPTableName";

    public static final String PORTABLE_DEVICE_ID_COL = "PortableDeviceID";

    public static final String QSEQ_SCOL = "QSeq";
    public static final String DT_ENU_ID_COL = "DTEnuID";
    public static final String OP_MODE_COL = "OpMode";
    public static final String DTTIME_STRING_COL = "DTTimeString";
    public static final String DT_R_SEQ_COL = "DTRSeq";
    public static final String DTA_VALUE_COL = "DTAValue";
    public static final String TABLE_NAME_COL = "TableName";
    public static final String FIELD_NAME_COL = "FieldName";
    public static final String FIELD_DEFINITION_COL = "FieldDefinition";
    public static final String FIELD_SHORT_NAME_COL = "FieldShortName";
    public static final String VALUE_UDF_COL = "ValueUDF";
    public static final String LUPTABLE_NAME_COL = "LUPTableName";
    public static final String ADMIN_ONLY_COL = "AdminOnly";
    public static final String TABLE_GROUP_CODE_COL = "TableGroupCode";
    public static final String USE_ADMIN_ONLY_COL = "UseAdminOnly";
    public static final String USE_REPORT_COL = "UseReport";
    public static final String USE_TRANSACTION_COL = "UseTransaction";
    public static final String USE_LUP_COL = "UseLUP";
    public static final String USE_REGISTRATION_COL = "UseRegistration";
    public static final String USE_SERVICE_COL = "UseService";
    public static final String USE_DISTRIBUTION_COL = "UseDistribution";
    public static final String USE_INVENTORY_COL = "UseInventory";
    public static final String USE_GIS_COL = "UseGIS";
    public static final String TD_SP_NAME_COL = "TDspName";
    public static final String T_CONTENTS_COL = "TContents";
    public static final String SHOW_HIDE_COL = "ShowHide";
    public static final String MAX_VALUE_COL = "MaxValue";
    public static final String MIN_VALUE_COL = "MinValue";
    public static final String MARK_ON_GRID_COL = "MarkOnGrid";
    public static final String LAND_SIZE_UNDER_IRRIGATION_COL = "LandSizeUnderIrrigation";
    public static final String IRRIGATION_SYSTEM_USED_COL = "IrrigationSystemUsed";
    public static final String FUND_SUPPORT_COL = "FundSupport";
    public static final String REP_NAME_COL = "RepName";
    public static final String REP_PHONE_NUMBER_COL = "RepPhoneNumber";
    public static final String FORMATION_DATE_COL = "FormationDate";
    public static final String PROJECT_NO_COL = "ProjectNo";
    public static final String PROJECT_TITLE = "ProjectTitle";


    public static final String LUP_SRV_OPTION_LIST_TABLE = "LUP_SrvOptionList";
    public static final String LUP_GPS_LIST_TABLE = "GPSLUPList";

    public static final String LUP_VALUE_CODE_COL = "LupValueCode";
    public static final String LUP_VALUE_TEXT_COL = "LupValueText";


    /**
     * *************************************************************************
     * COLUMN NAME DEFINE FROM HERE
     * **************************************************************************
     */

    // Login Table Columns names
    public static final String USER_ID = "UsrID";
    public static final String COUNTRY_CODE = "CountryCode";
    public static final String FDP_MASTER_COUNTRY_CODE = "AdmCountryCode";
    public static final String STAFF_FDP_ACCESS_COUNTRY_CODE = "AdmCountryCode";
    public static final String DATE_RANGE_COUNTRY_CODE = "CountryCode";
    public static final String LAYER_LAVLE_COUNTRY_CODE = "AdmCountryCode";
    public static final String COUNTRY_COUNTRY_CODE = "AdmCountryCode";
    public static final String STAFF_COUNTRY_CODE = "OrigAdmCountryCode";
    public static final String STAFF_CODE = "StfCode";
    public static final String USER_LOGIN_NAME = "UsrLogInName";
    public static final String USER_LOGIN_PW = "UsrLogInPW";
    public static final String USER_FIRST_NAME = "UsrFirstName";
    public static final String USER_LAST_NAME = "UsrLastName";
    public static final String USER_EMAIL = "UsrEmail";
    public static final String USER_EMAIL_VERIFICATION = "UsrEmailVerification";
    public static final String USER_STATUS = "UsrStatus";


    // COUNTRY
    public static final String ID_COL = "ID";
    public static final String COUNTRY_COUNTRY_NAME = "AdmCountryName";

    // Valid Date Range
    public static final String GEO_LAY_R_CODE_COL = "GeoLayRCode";
    public static final String GEO_LAY_R_NAME_COL = "GeoLayRName";

    // Layer Label
    public static final String DATE_START = "StartDate";
    public static final String DATE_END = "EndDate";

    // DISTRICT_JSON_A
    public static final String MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL = "LayR1ListCode";
    public static final String FDP_MASTER_LAY_R1_LIST_CODE_COL = "LayR1Code";
    public static final String LAY_R_LIST_CODE_COL = "LayRListCode";
    public static final String LAY_R_LIST_NAME_COL = "LayRListName";

    // Registration by Shuvo
    public static final String LAY_R1_LIST_CODE = "LayR1ListCode";

    // Upazilla
    public static final String FDP_MASTER_LAY_R2_LIST_CODE_COL = "LayR2Code";
    public static final String LAY_R1_LIST_CODE_COL = "LayR1ListCode";
    public static final String LAY_R2_LIST_CODE_COL = "LayR2ListCode";
    public static final String LAY_R2_LIST_NAME_COL = "LayR2ListName";

    // UNIT
    public static final String LAY_R3_LIST_CODE_COL = "LayR3ListCode";
    public static final String LAY_R3_LIST_NAME = "LayR3ListName";

    // Village
    public static final String LAY_R4_LIST_CODE_COL = "LayR4ListCode";
    public static final String LAY_R4_LIST_NAME_COL = "LayR4ListName";
    public static final String HOUSE_HOLD_TARGET = "HHCount";


    //-------------- REGISTRATION-------
    public static final String ADM_COUNTRY_CODE_COL = "AdmCountryCode";


    public static final String REGN_ADDRESS_LOOKUP_CODE_COL = "RegNAddLookupCode";
    public static final String REGN_ADDRESS_LOOKUP_NAME_COL = "RegNAddLookup";

    //public static final String PHOTO_COL            = "Photo";

    public static final String LATITUDE_COL = "Latd";
    public static final String LONGITUDE_COL = "Long";

    public static final String ENTRY_BY = "EntryBy";
    public static final String ENTRY_DATE = "EntryDate";
    public static final String FREEZE_POINT_COL = "FreezePoint";
    public static final String PIN_NUMBER_COL = "PinNumber";
    public static final String RELATION_CODE = "HHRelationCode";
    public static final String RELATION_NAME = "RelationName";

    public static final String SYNC_COL = "SyncStatus";


    public static final String LAST_SYNC_TIME_COL = "LastSyncTime";


    // Registration Member

    public static final String HHID_COL = "HHID";
    public static final String MEM_ID_COL = "MemID";
    public static final String REG_N_ASSIGN_PROG_SRV_HH_MEM_ID = "MemID";
    public static final String HH_MEM_ID = "HHMemID";


    /**
     * ADDED BY POP COLUMN FOR SERVICE TABLE
     */

    public static final String PUBLISHER_ID_COL = "PublisherID";
    public static final String PUBLISHER_NAME_COL = "PublisherName";
    public static final String SUBSCRIBER_M_CODE_COL = "SubscriberMCode";
    public static final String DEVICE_TYPE_ID_COL = "DeviceTypeID";
    public static final String M_CODE_COL = "MCode";
    public static final String M_LABLE_COL = "MLabel";
    public static final String M_ID_COL = "MID";
    public static final String DEVICE_ROLE_ID_COL = "DeviceRoleID";
    public static final String OFF_MODE_COL = "OFFMode";
    public static final String ON_MODE_COL = "ONMode";
    public static final String OPERATION_MODE_COL = "OperationMode";

    public static final String ADM_DONOR_CODE_COL = "AdmDonorCode";
    public static final String DONOR_CODE_COL = "DonorCode";
    public static final String ADM_AWARD_CODE_COL = "AdmAwardCode";
    public static final String AWARD_CODE_COL = "AwardCode";
    public static final String ADM_PROG_CODE_COL = "AdmProgCode";
    public static final String PROG_CODE_COL = "ProgCode";
    public static final String ADM_SRV_CODE_COL = "AdmSrvCode";
    public static final String SRV_CODE_COL = "SrvCode";
    public static final String OPERATION_CODE_COL = "OpCode";
    public static final String OP_MONTH_CODE_COL = "OpMonthCode";

    public static final String SERVICE_TABLE_SERVICE_SL_COL = "SrvSL";

    public static final String SERVICE_TABLE_SERVICE_DT_COL = "SrvDT";

    public static final String SRV_STATUS_COL = "SrvStatus";
    public static final String WORK_DAY_COL = "WD";


    // ADDED BY POP COLUMN FOR COUNTRY_AWARD TABLE
    /**
     * {@link #ADM_COUNTRY_AWARD_TABLE 's column }
     */

    public static final String AWARD_REF_N_COL = "AwardRefNumber";
    public static final String AWARD_START_DATE_COL = "AwardStartDate";
    public static final String AWARD_END_DATE_COL = "AwardEndDate";
    public static final String AWARD_SHORT_COL = "AwardShort";
    public static final String AWARD_SHORT_NAME_COL = "AwardShortName";
    public static final String AWARD_NAME_COL = "AwardName";
    public static final String AWARD_STATUS_COL = "AwardStatus";

    // ADDED BY POP COLUMN FOR DONOR TABLE
    public static final String DONOR_NAME_COL = "AdmDonorName";

    // ADDED BY POP COLUMN FOR PROGRAM TABLE

    public static final String PROGRAM_NAME_COL = "ProgramName";
    public static final String ADM_PROGRAM_MASTER_PROGRAM_NAME_COL = "ProgName";
    public static final String PROGRAM_SHORT_NAME_COL = "ProgShortName";
    public static final String MULTIPLE_SERVICE_FLAG_COL = "MultipleSrv";
    public static final String DEFAULT_FOOD_DAYS_COL = "DefaultFoodDays";
    public static final String DEFAULT_NO_FOOD_DAYS_COL = "DefaultNFoodDays";
    public static final String DEFAULT_CASH_DAYS_COL = "DefaultCashDays";
    public static final String DEFAULT_VOUCHAR_DAYS_COL = "DefaultVODays";
    public static final String SERVICE_SPECIFIC_FLAG_COL = "SrvSpecific";

    // ADDED BY POP COLUMN FOR PROGRAM TABLE

    public static final String SERVICE_NAME_COL = "ServiceName";
    public static final String SERVICE_MASTER_SERVICE_NAME_COL = "AdmSrvName";
    public static final String SERVICE_SHORT_NAME_COL = "ServiceShortName";
    public static final String SERVICE_MASTER_SERVICE_SHORT_NAME_COL = "AdmSrvShortName";

    // ADDED BY POP COLUMN FOR REG_N_ASSIGN_PROG_SRV_TABLE  TABLE


    public static final String GRD_CODE_COL = "GRDCode";


    // ADDED BY POP COLUMN FOR GRADUATION  TABLE

    public static final String GRD_TITLE_COL = "GRDTitle";
    public static final String DEFAULT_CAT_ACTIVE_COL = "DefaultCatActive";
    public static final String DEFAULT_CAT_EXIT_COL = "DefaultCatExit";


    // ADDED BY POP COLUMN FOR GPS GROUP TABLE

    public static final String GROUP_CODE_COL = "GrpCode";
    public static final String GROUP_NAME_COL = "GrpName";
    public static final String DESCRIPTION_COL = "Description";
    public static final String LAY_R1_CODE_COL = "LayR1Code";
    public static final String GRP_LAY_R2_LIST_CODE_COL = "LayR2Code";
    public static final String GRP_LAY_R3_LIST_CODE_COL = "LayR3Code";
    public static final String REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL = "GrpLayR1ListCode";
    public static final String REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL = "GrpLayR2ListCode";
    public static final String REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL = "GrpLayR3ListCode";


    public static final String PRIME_Y_N_COL = "PrimeYN";
    public static final String SUB_Y_N_COL = "SubYN";
    public static final String TECH_Y_N_COL = "TechYN";
    public static final String IMP_Y_N_COL = "ImpYN";
    public static final String LONG_Y_N_COL = "LogYN";
    public static final String OTH_Y_N_COL = "OthYN";
    public static final String ORGANIZATION_NAME = "OrgNName";
    public static final String ORGANIZATION_SHORT_NAME = "OrgNShortName";
    // for device information

    public static final String SELECTED_OPERATION_MODE_TABLE = "SelectedOperationMode";
    public static final String SELECTED_OPERATION_MODE_CODE_COL = "OperationModeCode";
    public static final String SELECTED_OPERATION_MODE_NAME_COL = "OperationModeName";


    // ADDED BY POP COLUMN FOR GPS SUB GROUP TABLE

    public static final String SUB_GROUP_CODE_COL = "SubGrpCode";
    public static final String SUB_GROUP_NAME_COL = "SubGrpName";

    // ADDED BY POP COLUMN FOR GPS LOCATION  TABLE

    public static final String LOCATION_CODE_COL = "LocationCode";
    public static final String LOCATION_NAME_COL = "LocationName";

    // ADDED BY POP COLUMN FOR GPS LOCATION  TABLE


    public static final String MONTH_LABEL_COL = "MonthLabel";
    public static final String START_DATE_COL = "StartDate";
    public static final String END_DATE_COL = "EndDate";

    public static final String USA_START_DATE_COL = "usaStartDate";
    public static final String USA_END_DATE_COL = "usaEndDate";
    public static final String STATUS = "Status";


    public static final String SERVICE_CENTER_CODE_COL = "SrvCenterCode";
    public static final String SERVICE_CENTER_NAME_COL = "SrvCenterName";


    // special condition constant
    public static final String SERVICE_SUMMARY_CRITERIA_QUERY = "serviceSummaryCriteria";


    // ADDED BY POP COLUMN FOR STAFF_GEO_INFO_ACCESS_TABLE


    public static final String STAFF_CODE_COL = "StfCode";
    public static final String LAYR_LIST_CODE_COL = "LayRListCode";
    public static final String BTN_NEW_COL = "btnNew";
    public static final String BTN_SAVE_COL = "btnSave";
    public static final String BTN_DEL_COL = "btnDel";
    public static final String BTN_PEPR_COL = "btnPepr";

    public static final String BTN_APRV_COL = "btnAprv";
    public static final String BTN_REVW_COL = "btnRevw";
    public static final String BTN_VRFY_COL = "btnVrfy";
    public static final String BTN_DTRAN_COL = "btnDTran";


    // ADDED BY POP COLUMN FOR HOUSE_HOLD_CATEGORIES


    public static final String HH_HEAD_CAT_CODE_COL = "HHHeadCatCode";
    public static final String VOUCHER_ITEM_CATEGORY_CODE_COL = "CatCode";
    public static final String CATEGORY_NAME_COL = "CatName";
    public static final String DT_CATEGORY_TABLE_CATEGORY_NAME_COL = "CategoryName";


    // ADDED BY POP COLUMN FOR HOUSE_HOLD_CATEGORIES


    public static final String REPORT_LABLE_COL = "RptLabel";
    public static final String REPORT_CODE_COL = "RptCode";

    // ADDED BY POP COLUMN FOR LUP_REGN_CARD_PRINT_REASON_TABLE

    public static final String CARD_PRINT_REASON_TITLE_COL = "ReasonTitle";
    public static final String CARD_PRINT_REASON_CODE_COL = "ReasonCode";


    public static final String STAFF_FDP_ACCESS_TABLE = "StaffFDPAccess";
    public static final String FDP_MASTER_TABLE = "FDPMaster";
    public static final String FDP_CODE_COL = "FDPCode";
    public static final String FDP_NAME_COL = "FDPName";
    public static final String FDA_CATEGORIES_CODE_COL = "FDPCatCode";
    public static final String WH_CODE_COL = "WHCode";


    // RegN_vul Column
    public static final String Disabled_YN_COL = "DisabledYN";

    // LUP_SrvOptionList TABLE COLUMN
    public static final String LUP_OPTION_CODE_COL = "LUPOptionCode";
    public static final String LUP_OPTION_NAME_COL = "LUPOptionName";


    public static final String DISTRIBUTION_STATUS_COL = "DistStatus";

    public static final String DIST_DT_COL = "DistDT";

    public static final String DIST_FLAG_COL = "DistFlag";

    public static final String DIST_OP_MONTH_CODE_COL = "DisOpMonthCode";
    public static final String ORG_CODE_COL = "OrgCode";
    public static final String PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL = "OrgNCode";
    public static final String ORG_N_CODE_COL = "OrgNCode";
    public static final String ORG_N_DESG_N_CODE_COL = "OrgNDesgNCode";


    // Voucher item table column
    public static final String ITEM_CODE_COL = "ItmCode";
    public static final String ITEM_NAME_COL = "ItmName";


    // Voucher item  meas table column
    public static final String MEAS_R_CODE_COL = "MeasRCode";
    public static final String UNITE_MEAS_COL = "UnitMeas";
    public static final String MEASE_TITLE_COL = "MeasTitle";


    public static final String VOUCHER_ITEM_SPEC_COL = "VOItmSpec";
    public static final String UNITE_COST_COL = "UnitCost";
    public static final String ACTIVE_STATUS_COL = "ActiveStatus";
    public static final String ACTIVE_COL = "Active";
    public static final String CURRENCY_COL = "Currency";
    public static final String VOUCHER_UNIT_COL = "VOItmUnit";
    public static final String VOUCHER_REFERENCE_NUMBER_COL = "VORefNumber";
    public static final String VOUCHER_ITEM_COST_COL = "VOItmCost";

    public static final String ATTRIBUTE_CODE_COL = "AttributeCode";
    public static final String ATTRIBUTE_TITLE_COL = "AttributeTitle";
    public static final String ATTRIBUTE_VALUE_COL = "AttributeValue";
    public static final String ATTRIBUTE_PHOTO_COL = "AttPhoto";
    public static final String DATA_TYPE_COL = "DataType";
    public static final String U_FILE_COL = "UFILE";
    public static final String COMPLETENESS_COL = "Completeness";


    public static final String LUP_GPS_TABLE_LOOK_UP_CODE_COL = "LupValueCode";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_CODE_COL = "LookUp";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_NAME_COL = "LupValueText";

    /**
     * For getListAndID() methods when the table name already use
     */
    public static final String CUSTOM_QUERY = "CustomQuery";

    public static final String CROP_CODE_COL = "CropCode";
    public static final String LOAN_SOURCE_COL = "LoanSource";

    public static final String ANIMAL_CODE_COL = "AnimalCode";
    public static final String LEAD_CODE_COL = "LeadCode";
    /**
     * For Group Community table
     */
    public static final String GROUP_CAT_CODE_COL = "GrpCatCode";
    /**
     * For Group Community Categories table
     */
    public static final String GROUP_CAT_NAME_COL = "GrpCatName";
    public static final String GROUP_CAT_SHORT_NAME_COL = "GrpCatShortName";


    /**
     * LUP_PROG_GROUP_CROP_TABLE FOR COLMN
     */
    public static final String CROP_NAME_COL = "CropList";
    public static final String CROP_CAT_COL = "CropCatCode";


    /**
     * LUP_COMMUNITY_ANIMAL_TABLE FOR COLMN
     */
    public static final String ANIMAL_TYPE_COL = "AnimalType";

    /**
     * LUP_COMMUNITY_LOAN_SOURCE_TABLE FOR COLMN
     */
    public static final String LOAN_CODE_COL = "LoanCode";

    /**
     * {@link #LUP_COMMUNITY_LEAD_POSITION_TABLE 's column }
     */
    public static final String LEAD_POSITION_COL = "LeadPosition";

    /**
     * {@link #LUP_COMMUNITY_FUND_SOURCE_TABLE 's column }
     */
    public static final String FUND_CODE_COL = "FundCode";
    public static final String FUND_SOURCE_COL = "FundSource";


    /**
     * {@link #LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE 's column }
     */
    public static final String IRRI_SYS_CODE_COL = "IrriSysCode";
    public static final String IRRI_SYS_NAME_COL = "IrriSysName";

    public static final String CONTENT_CODE_COL = "ContentCode";
    public static final String IMAGE_FILE_COL = "ImageFile";
    public static final String REMARKES_COL = "Remarks";


    public static final String WEALTH_RANKING_COL = "WealthRanking";
    public static final String MEMBER_EXT_GROUP_COL = "MemberExtGroup";
    public static final String ORPHAN_CHILDREN_COL = "OrphanedChildren";
    public static final String CHILD_HEADED_COL = "ChildHeaded";
    public static final String ELDERLY_HEADED_COL = "ElderlyHeaded";
    public static final String CHRONICALLY_ILL_COL = "ChronicallyIll";
    public static final String CROP_FAILURE_COL = "CropFailure";
    public static final String FEMALE_HEADED_COL = "FemaleHeaded";
    public static final String CHILDREN_REC_SUPP_FEED_N_COL = "ChildrenRecSuppFeedN";


    public static final String AG_INVC_COL = "AGOINVC";
    public static final String AG_NASFAM_COL = "AGONASFAM";
    public static final String AG_CU_COL = "AGOCU";
    public static final String AG_OTHER_COL = "AGOOther";
    public static final String AG_L_S_GOAT_COL = "LSGoat";
    public static final String AG_L_S_CHICKEN_COL = "LSChicken";
    public static final String AG_L_S_PIGION_COL = "LSPigeon";
    public static final String AG_L_S_OTHER_COL = "LSOther";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db, int version) {
        dropTableFromExportedDatabase(db);
        db.execSQL(Schema.sqlCreateUploadTable());
        db.execSQL(Schema.sqlCreateUploadPhysicalTable());
    }

    /**
     * this method is for only Exported data base
     *
     * @param context the invoking activity or MainActivity
     * @param version by default ertar value 1 thakbe
     */
    public SQLiteHandler(Context context, int version) {
        super(context, EXTERNAL_DATABASE_NAME, null, version);
        SQLiteDatabase external = this.getWritableDatabase();
        onCreate(external, version);

    }


    /**
     * ei method original data base theke data niye exported data base Upload syntax gulo insert korabe
     *
     * @param context the invoking activity or MainActivity
     */
    public void insertIntoExportDataBase(Context context) {
        List<dataUploadDB> list = new ArrayList<>();

        String path = "/data/data/" + context.getPackageName() + "/databases/";

        SQLiteDatabase extralDatabase, originalDatabase;
        extralDatabase = SQLiteDatabase.openDatabase(path + EXTERNAL_DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        originalDatabase = SQLiteDatabase.openDatabase(path + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);


        extralDatabase.delete(UPLOAD_SYNTAX_TABLE, null, null);

        String sql = "SELECT  * FROM " + UPLOAD_SYNTAX_TABLE +// " WHERE " + SYNC_COL + "=0 "
                " ORDER BY " + ID_COL + " ASC ";
        Cursor cursor = originalDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                dataUploadDB data = new dataUploadDB();
                data._id = cursor.getString(cursor.getColumnIndex(ID_COL));
                data._syntax = cursor.getString(cursor.getColumnIndex(SQL_QUERY_SYNTAX));
                list.add(data);

            } while (cursor.moveToNext());
            cursor.close();
        }

        //  originalDatabase.close();


        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put(SQL_QUERY_SYNTAX, list.get(i)._syntax);
                values.put(SYNC_COL, "0");


                extralDatabase.insert(UPLOAD_SYNTAX_TABLE, null, values);

            }
        }


        List<dataUploadDB> list_1 = new ArrayList<>();

        extralDatabase.delete(UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE, null, null);

        String sql_1 = "SELECT  " + SQL_QUERY_SYNTAX
                + " , " + DT_R_SEQ_COL
                + " FROM " + UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE;

        Cursor cursor_1 = originalDatabase.rawQuery(sql_1, null);
        if (cursor_1.moveToFirst()) {
            do {
                dataUploadDB data = new dataUploadDB();
//                data._id = cursor.getString(cursor.getColumnIndex(ID_COL));
                data._syntax = cursor_1.getString(0);
                data._sqn = cursor_1.getInt(1);
                list_1.add(data);

            } while (cursor_1.moveToNext());
            cursor_1.close();
        }

        originalDatabase.close();


        if (list_1.size() > 0) {
            for (int i = 0; i < list_1.size(); i++) {
                ContentValues values = new ContentValues();
                values.put(SQL_QUERY_SYNTAX, list_1.get(i)._syntax);
                values.put(DT_R_SEQ_COL, list_1.get(i)._sqn);
                values.put(SYNC_COL, "0");
                values.put(PORTABLE_DEVICE_ID_COL, UtilClass.getDeviceId(context));


                extralDatabase.insert(UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE, null, values);

            }
        }


        extralDatabase.close();

    }

    /**
     * Copies the database file at the specified location over the current
     * internal application database.
     */
    public boolean importDatabase(String scrDBPath, Context context) throws IOException {

        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        close();
        File newDb = new File(scrDBPath);
        File packageDb = new File("/data/data/" + context.getPackageName() + "/databases/"
                + DATABASE_NAME);

//        File oldDbpath = new File("/data/data/" + context.getPackageName() + "/databases/");


        if (newDb.exists()) {
            // // TODO: 6/29/2017  create directories  for old directories
            if (!packageDb.exists()) {
                new SQLiteHandler(context);
            }


            FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(packageDb));             // Access the copied database so SQLiteHelper will cache it and mark


            getWritableDatabase().close();                                                          // it as created.
            return true;
        }
        return false;
    }

    public void reCreateSurveyTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_SURVEY_TABLE);
        db.execSQL(Schema.createTableDTSurveyTable());
        db.close();
    }


    private void dropTableFromExportedDatabase(SQLiteDatabase db) {

        db.execSQL(DROP_TABLE_IF_EXISTS + LOGIN_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + COUNTRY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VALID_DATE_RANGE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R1_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R2_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R3_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R4_LIST_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REG_NHH_RELATION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_COUNTRY_AWARD_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_DONOR_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_PROGRAM_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_MASTER_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_GROUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + OP_MONTH_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_COUNTRY_PROGRAM_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_GEO_INFO_ACCESS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REGNH_HEAD_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_LUP_GRADUATION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REPORT_TEMPLATE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REGN_CARD_PRINT_REASON_TABLE);

        // db.execSQL(DROP_TABLE_IF_EXISTS + UPLOAD_SYNTAX_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + FDP_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_FDP_ACCESS_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + SRV_CENTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM__MEAS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_EXTENDED_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_VILLAGE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_ATTRIBUTES_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_ATTRIBUTES_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_ANIMAL_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_PROG_GROUP_CROP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LOAN_SOURCE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LEAD_POSITION_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_CONTENT_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GRP_DETAIL_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_NAME_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_ROLE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_A_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_BASIC_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_COUNTRY_PROGRAM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_COL);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTQRES_MODE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTQ_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_RESPONSE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_SURVEY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_TABLE_DEFINITION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTTABLE_LIST_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_ENU_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_LUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTA_SKIP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TEMPORARY_COUNTRY_PROGRAM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TEMPORARY_OP_MONTH_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_COUNTRY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_AWARD_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_OPERATION_MODE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_SRV_CENTER_ACCESS_TABLE);

        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_TA_PATICIPANT_CAT_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_FDP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_SERVICE_CENTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LAST_SYNC_TYRACE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_SRV_OPTION_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REGN_ADDRESS_LOOKUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_FUND_SOURCE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE);

    }

    // Creating Tables

    /**
     * this method create tables in the db
     *
     * @param db sqlite data base reference
     *           invoked by  {@link #refreshDatabase(SQLiteDatabase)}
     *           <p/>
     *           {@link #deleteUsers(SQLiteDatabase)}
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Schema.sqlCreateUploadTable());
        db.execSQL(Schema.sqlCreateUploadPhysicalTable());
        db.execSQL(Schema.sqlCreateUserLoginTable());
        db.execSQL(Schema.sqlCreateStaffMasterTable());
        db.execSQL(Schema.sqlCreateCountry());
        db.execSQL(Schema.sqlCreateLayerLabel());
        db.execSQL(Schema.sqlCreateDistrict());
        db.execSQL(Schema.sqlCreateUpazilla());
        db.execSQL(Schema.sqlCreateUnit());
        db.execSQL(Schema.sqlCreateVillage());
        db.execSQL(Schema.sqlCreateDateRange());

        db.execSQL(Schema.sqlCreateRegRelation());

        db.execSQL(Schema.sqlCreateCountryAwardTable());
        db.execSQL(Schema.sqlCreateAdmAwardTable());
        db.execSQL(Schema.sqlCreateDonorTable());
        db.execSQL(Schema.sqlCreateProgramMasterTable());
        db.execSQL(Schema.sqlCreateServiceMasterTable());

        db.execSQL(Schema.sqlCreateGpsGroupTable());
        db.execSQL(Schema.sqlCreateGpsSubGroupTable());

        db.execSQL(Schema.sqlCreateOpMonthTable());
        db.execSQL(Schema.sqlCreateADM_CountryProgram());

        db.execSQL(Schema.sqlCreateServiceCenterTable());

        db.execSQL(Schema.sqlCreateStaffGeoInfoAccessTable());
        db.execSQL(Schema.sqlCreateStaffSrvCenterAccessTable());
        db.execSQL(Schema.sqlCreateHouseHoldCategoryTable());
        db.execSQL(Schema.sqlCreateGraduationTable());
        db.execSQL(Schema.sqlCreateCardTypeTable());
        db.execSQL(Schema.sqlCreateCardPrintReasonTable());


        db.execSQL(Schema.sqlCreateLUP_SrvOptionList());

        db.execSQL(Schema.sqlCreateVoucherItem_Table());
        db.execSQL(Schema.sqlCreateVoucherItemMeas_Table());
        db.execSQL(Schema.sqlCreateVoucherCountryProgItem_Table());
        db.execSQL(Schema.sqlCreateServiceExtended_Table());

        db.execSQL(Schema.sqlCreateSelectedFDP_Table());
        db.execSQL(Schema.sqlCreateSelectedServiceCenter_Table());
        db.execSQL(Schema.sqlCreateCommunityGroup_Table());
        db.execSQL(Schema.sqlCreateGPSSubGroupAttributes_Table());
        db.execSQL(Schema.sqlCreateLUP_GPS_Table());
        db.execSQL(Schema.sqlCreateGPSLocationAttributes_Table());

        db.execSQL(Schema.sqlCreateLUP_CommunityAnimalList_Table());
        db.execSQL(Schema.sqlCreateLUP_ProgramGroupCrop_Table());
        db.execSQL(Schema.sqlCreateLUP_CommunityLoanSource_Table());
        db.execSQL(Schema.sqlCreateLUP_CommunityLeadPosition_Table());

        db.execSQL(Schema.sqlCreateCommunityGroupCategoryes_Table());
        db.execSQL(Schema.sqlCreate_Gps_Location_Content_Table());

        db.execSQL(Schema.createTableLastSyncTime());

        db.execSQL(Schema.createTableLUP_RegNAddLookup());
        db.execSQL(Schema.createTableCommunityGrpDetail());
        db.execSQL(Schema.createTableProgOrgNRole());
        db.execSQL(Schema.createTableProgOrgN());
        db.execSQL(Schema.sqlCreateLUP_GpsList());
        db.execSQL(Schema.createTableDTATable()); /**         * for Dynamic Module         */
        db.execSQL(Schema.createTableDTBasic());
        db.execSQL(Schema.createTableDTCategory());
        db.execSQL(Schema.createTableDTCountryProgram());
        db.execSQL(Schema.createTableDTGeoListLevel());
        db.execSQL(Schema.createTableDTQResMode());
        db.execSQL(Schema.createTableDTQTable());
        db.execSQL(Schema.createTableDTResponseTable());
        db.execSQL(Schema.createTableDTSurveyTable());
        db.execSQL(Schema.createDTEnuTable());
        db.execSQL(Schema.createTableDTTableDefinition());
        db.execSQL(Schema.createTaleDTTableListCategory());
        db.execSQL(Schema.createTaleDT_LUP_Table());
        db.execSQL(Schema.createTableDTASkipTable());
        /**  training & activity */

        db.execSQL(Schema.crateLUP_TAParticipantCat());

        db.execSQL(Schema.sqlCreateLUP_CommunityFundSource_Table());
        db.execSQL(Schema.sqlCreateLUP_CommunityIrrigationSystem_Table());

        /** * temporary  table */
        db.execSQL(Schema.sqlCreateTemporary_CountryProgram());
        db.execSQL(Schema.sqlCreateTemporary_OpMonthTable());
        db.execSQL(Schema.sqlCreateSelectedVillage_Table());
        db.execSQL(Schema.sqlCreateSelectedCountry());
        db.execSQL(Schema.sqlCreateOperationModeTable());                                           // device information


        Log.d(TAG, "  Create All Table ");


    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        refreshDatabase(db);
    }

    public void clearUploadSyntaxTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(UPLOAD_SYNTAX_TABLE, null, null);
    }

    /**
     * Drop All the Table to alter the Any table column in table.
     * after droping all the table it called {@link #onCreate(SQLiteDatabase)} method to create tables
     *
     * @param db database
     */
    private void refreshDatabase(SQLiteDatabase db) {

        //SQLiteDatabase db = this.getWritableDatabase();

//        Log.d(TAG, "Dropping all table..");

        // Drop older table if existed
        try {

            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM__MEAS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LOGIN_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COUNTRY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VALID_DATE_RANGE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R1_LIST_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R2_LIST_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R3_LIST_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R4_LIST_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REG_NHH_RELATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_COUNTRY_AWARD_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_DONOR_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_PROGRAM_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_MASTER_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + OP_MONTH_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_COUNTRY_PROGRAM_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_GEO_INFO_ACCESS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REGNH_HEAD_CATEGORY_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_LUP_GRADUATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REPORT_TEMPLATE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REGN_CARD_PRINT_REASON_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + UPLOAD_SYNTAX_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + FDP_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_FDP_ACCESS_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + SRV_CENTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R_MASTER_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_EXTENDED_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_VILLAGE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_ATTRIBUTES_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_ATTRIBUTES_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_ANIMAL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_PROG_GROUP_CROP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LOAN_SOURCE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LEAD_POSITION_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_CONTENT_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GRP_DETAIL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_NAME_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_ROLE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_LIST_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_A_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_BASIC_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_COUNTRY_PROGRAM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_COL);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTQRES_MODE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTQ_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_RESPONSE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_SURVEY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_TABLE_DEFINITION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTTABLE_LIST_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_ENU_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_LUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTA_SKIP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TEMPORARY_COUNTRY_PROGRAM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TEMPORARY_OP_MONTH_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_COUNTRY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_AWARD_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_OPERATION_MODE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_SRV_CENTER_ACCESS_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_TA_PATICIPANT_CAT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_FUND_SOURCE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE);


//            Log.d(TAG, "All table Dropped.");
        } catch (Exception e) {
//            Log.d(TAG, "Error: " + e.getMessage());
        }


        // Create tables again
        onCreate(db);
    }


    /**
     * Delete selected Village TABLE,selected FDp TABLE,selected Service TABLE,selected Country TABLE
     * and invoking {@link #deleteUsers(SQLiteDatabase)} method
     */
    public void deleteUsersWithSelected_LayR4_FDP_Srv_Country() {
        SQLiteDatabase db = this.getWritableDatabase();
        deleteUsers(db);


        db.delete(SELECTED_VILLAGE_TABLE, null, null);

        db.delete(SELECTED_FDP_TABLE, null, null);

        db.delete(SELECTED_SERVICE_CENTER_TABLE, null, null);

        db.delete(SELECTED_COUNTRY_TABLE, null, null);

        db.close();

    }

    /**
     * Re crate database Delete all tables and create them again
     * todo optimize code
     */
    public void deleteUsers(SQLiteDatabase db) {

        // SQLiteDatabase db = this.getWritableDatabase();

//        Log.d(TAG, "Deleting all user data..");

        try {
            // Delete All Rows
            db.delete(DT_A_TABLE, null, null);
            db.delete(DT_BASIC_TABLE, null, null);
            db.delete(DT_CATEGORY_TABLE, null, null);
            db.delete(DT_COUNTRY_PROGRAM_TABLE, null, null);
            db.delete(DTGEO_LIST_LEVEL_TABLE, null, null);
            db.delete(DTQRES_MODE_TABLE, null, null);
            db.delete(DTQ_TABLE, null, null);
            db.delete(DT_RESPONSE_TABLE, null, null);
            db.delete(DT_SURVEY_TABLE, null, null);
            db.delete(DT_TABLE_DEFINITION_TABLE, null, null);
            db.delete(DTTABLE_LIST_CATEGORY_TABLE, null, null);
            db.delete(DT_LUP_TABLE, null, null);
            db.delete(DT_ENU_TABLE, null, null);
            db.delete(LOGIN_TABLE, null, null);
            db.delete(COUNTRY_TABLE, null, null);
            db.delete(VALID_DATE_RANGE, null, null);
            db.delete(GEO_LAY_R1_LIST_TABLE, null, null);
            db.delete(GEO_LAY_R2_LIST_TABLE, null, null);
            db.delete(GEO_LAY_R3_LIST_TABLE, null, null);
            db.delete(GEO_LAY_R4_LIST_TABLE, null, null);

            db.delete(LUP_REG_NHH_RELATION_TABLE, null, null);
            db.delete(SERVICE_TABLE, null, null);
            db.delete(ADM_COUNTRY_AWARD_TABLE, null, null);
            db.delete(ADM_DONOR_TABLE, null, null);
            db.delete(ADM_PROGRAM_MASTER_TABLE, null, null);
            db.delete(SERVICE_MASTER_TABLE, null, null);

            db.delete(GPS_GROUP_TABLE, null, null);
            db.delete(GPS_SUB_GROUP_TABLE, null, null);
            db.delete(GPS_LOCATION_TABLE, null, null);
            db.delete(OP_MONTH_TABLE, null, null);
            db.delete(ADM_COUNTRY_PROGRAM_TABLE, null, null);

            db.delete(STAFF_GEO_INFO_ACCESS_TABLE, null, null);
            db.delete(STAFF_SRV_CENTER_ACCESS_TABLE, null, null);
            db.delete(LUP_REGNH_HEAD_CATEGORY_TABLE, null, null);

            db.delete(REG_N_LUP_GRADUATION_TABLE, null, null);
            db.delete(REPORT_TEMPLATE_TABLE, null, null);
            db.delete(LUP_REGN_CARD_PRINT_REASON_TABLE, null, null);

            db.delete(UPLOAD_SYNTAX_TABLE, null, null);
            db.delete(FDP_MASTER_TABLE, null, null);
            db.delete(STAFF_FDP_ACCESS_TABLE, null, null);

            db.delete(SRV_CENTER_TABLE, null, null);
            db.delete(GEO_LAY_R_MASTER_TABLE, null, null);
            db.delete(VOUCHER_ITEM_TABLE, null, null);
            db.delete(VOUCHER_ITEM__MEAS_TABLE, null, null);
            db.delete(VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE, null, null);
            db.delete(SERVICE_EXTENDED_TABLE, null, null);

            db.delete(GPS_SUB_GROUP_ATTRIBUTES_TABLE, null, null);
            db.delete(LUP_GPS_TABLE, null, null);
            db.delete(GPS_LOCATION_ATTRIBUTES_TABLE, null, null);

            db.delete(COMMUNITY_GROUP_TABLE, null, null);

            db.delete(LUP_COMMUNITY_ANIMAL_TABLE, null, null);
            db.delete(LUP_PROG_GROUP_CROP_TABLE, null, null);
            db.delete(LUP_COMMUNITY_LOAN_SOURCE_TABLE, null, null);
            db.delete(LUP_COMMUNITY_LEAD_POSITION_TABLE, null, null);
            db.delete(LUP_COMMUNITY_FUND_SOURCE_TABLE, null, null);
            db.delete(LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE, null, null);


            db.delete(COMMUNITY_GROUP_CATEGORY_TABLE, null, null);
            db.delete(GPS_LOCATION_CONTENT_TABLE, null, null);

            db.delete(COMMUNITY_GRP_DETAIL_TABLE, null, null);
            db.delete(PROGRAM_ORGANIZATION_NAME_TABLE, null, null);
            db.delete(PROGRAM_ORGANIZATION_ROLE_TABLE, null, null);
            db.delete(STAFF_MASTER_TABLE, null, null);
            db.delete(LUP_GPS_LIST_TABLE, null, null);
            db.delete(ADM_AWARD_TABLE, null, null);
            db.delete(SELECTED_OPERATION_MODE_TABLE, null, null);

            db.delete(LUP_TA_PATICIPANT_CAT_TABLE, null, null);


//            Log.d(TAG, "All User data Deleted.");
        } catch (Exception e) {
//            Log.d(TAG, "Error: " + e.getMessage());
        }

        //db.close();

        onCreate(db);
    }


    public void insertIntoLupGpsList(String grpCode, String subGrpCode, String attbuteCode, String lup_valueCode, String lup_value_text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(ATTRIBUTE_CODE_COL, attbuteCode);
        values.put(LUP_VALUE_CODE_COL, lup_valueCode);
        values.put(LUP_VALUE_TEXT_COL, lup_value_text);


        db.insert(LUP_GPS_LIST_TABLE, null, values);
        db.close();


    }


    public void insertIntoStaffMasterTable(String staffId, String cCode, String staffName, String orgCode, String orgNDesgCode
            , String staffStatus, String staffCat, String userName, String userPass, String staffAdimRole) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(STAFF_ID_COL, staffId);
        values.put(STAFF_COUNTRY_CODE, cCode);
        values.put(STAFF_NAME_COL, staffName);
        values.put(ORG_N_CODE_COL, orgCode);
        values.put(ORG_N_DESG_N_CODE_COL, orgNDesgCode);
        values.put(STAFF_STATUS_COL, staffStatus);
        values.put(STAFF_CATEGORY_COL, staffCat);
        values.put(USER_LOGIN_NAME, userName);
        values.put(USER_LOGIN_PW, userPass);
        values.put(STAFF_ADMIN_ROLE_COL, staffAdimRole);

        db.insert(STAFF_MASTER_TABLE, null, values);
        db.close();

    }

    public static final String TYPE_OF_GROUP = "TypeOfGroup";


    /**
     * @param cCode             Country Code
     * @param donorCode         Donor Code
     * @param awardCode         Award Code
     * @param progCode          Program Code
     * @param groupCatCode      group Categories Code
     * @param groupCatName      group Categories Name
     * @param groupCatShortName group Categories Short Name
     */

    public void addCommunityGroupCategoryFromOnline(String cCode, String donorCode, String awardCode, String progCode, String groupCatCode, String groupCatName
            , String groupCatShortName) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(GROUP_CAT_CODE_COL, groupCatCode);
        values.put(GROUP_CAT_NAME_COL, groupCatName);
        values.put(GROUP_CAT_SHORT_NAME_COL, groupCatShortName);


        db.insert(COMMUNITY_GROUP_CATEGORY_TABLE, null, values);
        db.close();
    }


    public void addLUP_AnimalTypeFromOnline(String cCode, String donorCode, String awardCode, String progCode, String animalCode, String animalType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(ANIMAL_CODE_COL, animalCode);
        values.put(ANIMAL_TYPE_COL, animalType);


        db.insert(LUP_COMMUNITY_ANIMAL_TABLE, null, values);
        db.close();


    }


    /**
     * lupProgGrpCrop data insert from only online
     *
     * @param cCode       Country Code
     * @param donorCode   Donor Code
     * @param awardCode   Award Code
     * @param progCode    program code
     * @param cropCode    crop code
     * @param corpName    crop Name
     * @param cropCatCode crop Categories  Code
     */

    public void addLUP_ProgramGroupCrop(String cCode, String donorCode, String awardCode, String progCode, String cropCode, String corpName, String cropCatCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(CROP_CODE_COL, cropCode);
        values.put(CROP_NAME_COL, corpName);
        values.put(CROP_CAT_COL, cropCatCode);


        //  long id =
        db.insert(LUP_PROG_GROUP_CROP_TABLE, null, values);
        db.close();


    }


    public void addLUP_CommunityLoanSource(String cCode, String donorCode, String awardCode,
                                           String progCode, String loanCode, String loanSource) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(LOAN_CODE_COL, loanCode);
        values.put(LOAN_SOURCE_COL, loanSource);

        db.insert(LUP_COMMUNITY_LOAN_SOURCE_TABLE, null, values);
        db.close();


    }

    public void addLUP_CommunityFundSource(String cCode, String donorCode, String awardCode,
                                           String progCode, String fundCode, String fundSource) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(FUND_CODE_COL, fundCode);
        values.put(FUND_SOURCE_COL, fundSource);

        db.insert(LUP_COMMUNITY_FUND_SOURCE_TABLE, null, values);
        db.close();


    }


    public void addLUP_CommunityIrrigationSystem(String cCode, String donorCode, String awardCode,
                                                 String progCode, String irriSysCode, String irriSystemName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(IRRI_SYS_CODE_COL, irriSysCode);
        values.put(IRRI_SYS_NAME_COL, irriSystemName);

        db.insert(LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE, null, values);
        db.close();


    }

    // add LUP_CommunityLoanSource list

    public void addLUP_CommunityLeadPostition(String cCode, String donorCode, String awardCode,
                                              String progCode, String leadCode, String leadPosition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(LEAD_CODE_COL, leadCode);
        values.put(LEAD_POSITION_COL, leadPosition);


        db.insert(LUP_COMMUNITY_LEAD_POSITION_TABLE, null, values);
        db.close();


    }


    // add service Center

    public void addServiceCenter(String cCode, String srvCenCode, String srvCenName, String fdpCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(SERVICE_CENTER_CODE_COL, srvCenCode);
        values.put(SERVICE_CENTER_NAME_COL, srvCenName);
        values.put(FDP_CODE_COL, fdpCode);

        db.insert(SRV_CENTER_TABLE, null, values);
        db.close();


    }

    public void insertAdmCountryProgram(String cCode, String donorCode, String awardCode, String programCode, String servCode, String progFlag, String food, String nonFood, String cash, String voucher,
                                        String defaultFoodDays, String defaultNoFoodDays, String defaultCashDays, String defaultVoucharDays, String srvSpecific) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(ADM_SRV_CODE_COL, servCode);
        values.put(PROG_FLAG, progFlag);
        values.put(FOOD_FLAG, food);
        values.put(NON_FOOD_FLAG, nonFood);
        values.put(CASH_FLAG, cash);
        values.put(VOUCHER_FLAG, voucher);
        values.put(DEFAULT_FOOD_DAYS_COL, defaultFoodDays);
        values.put(DEFAULT_NO_FOOD_DAYS_COL, defaultNoFoodDays);
        values.put(DEFAULT_CASH_DAYS_COL, defaultCashDays);
        values.put(DEFAULT_VOUCHAR_DAYS_COL, defaultVoucharDays);
        values.put(SERVICE_SPECIFIC_FLAG_COL, srvSpecific);
        // Inserting Row
        db.insert(ADM_COUNTRY_PROGRAM_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public void addRegNLupGraduation(String programCode, String serviceCode, String grdCode, String grdTitle,
                                     String defaultCatActive, String defaultCatExit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(ADM_SRV_CODE_COL, serviceCode);
        values.put(GRD_CODE_COL, grdCode);
        values.put(GRD_TITLE_COL, grdTitle);
        values.put(DEFAULT_CAT_ACTIVE_COL, defaultCatActive);
        values.put(DEFAULT_CAT_EXIT_COL, defaultCatExit);


        db.insert(REG_N_LUP_GRADUATION_TABLE, null, values);
        db.close();


    }


    /**
     * @since : 2015-09-18
     */
    public void addLupRegNHHCategory(String cCode, String hhCatCode, String hhCategoryName) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(HH_HEAD_CAT_CODE_COL, hhCatCode);
        values.put(CATEGORY_NAME_COL, hhCategoryName);

        db.insert(LUP_REGNH_HEAD_CATEGORY_TABLE, null, values);                                     // Insert
        db.close();


    }


    public void addStaffGeoAccessInfo(String staffCode, String cCode, String donorCode, String awardCode, String layrListCode/*, String districtCode, String upzellaCode, String unitCode, String vCode*/, String btnNew, String btnSave, String btnDel, String btnpepr, String btnAprv, String btnRevw, String btnVrfy, String btnDtrain) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_CODE, staffCode);
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);

        values.put(LAY_R_LIST_CODE_COL, layrListCode);

        // the permission of user action
        values.put(BTN_NEW_COL, btnNew);
        values.put(BTN_SAVE_COL, btnSave);
        values.put(BTN_DEL_COL, btnDel);
        values.put(BTN_PEPR_COL, btnpepr);
        values.put(BTN_APRV_COL, btnAprv);
        values.put(BTN_REVW_COL, btnRevw);
        values.put(BTN_VRFY_COL, btnVrfy);
        values.put(BTN_DTRAN_COL, btnDtrain);


        // Inserting Row
        db.insert(STAFF_GEO_INFO_ACCESS_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public void addStaffSrvCenterAccess(String staffCode, String cCode, String srvCenterCode, String btnNew, String btnSave, String btnDel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_CODE, staffCode);
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);

        values.put(BTN_NEW_COL, btnNew);
        values.put(BTN_SAVE_COL, btnSave);
        values.put(BTN_DEL_COL, btnDel);

        // Inserting Row
        db.insert(STAFF_SRV_CENTER_ACCESS_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public void addOpMonthFromOnline(String cCode, String donorCode, String awardCode, String opCode, String opMonthCode, String mLable, String sDate, String eDate, String usasDate, String usaeDate, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(MONTH_LABEL_COL, mLable);
        values.put(START_DATE_COL, sDate);
        values.put(END_DATE_COL, eDate);
        values.put(USA_START_DATE_COL, usasDate);
        values.put(USA_END_DATE_COL, usaeDate);
        values.put(STATUS, status);


        db.insert(OP_MONTH_TABLE, null, values);                                                     // Inserting Row
        db.close();                                                                                 // Closing database connection


    }

    // add location

    public void addGpsLocation(String cCode, String grpCode, String subGrpCode, String localCode, String localName, String lat, String lng) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(LOCATION_CODE_COL, localCode);
        values.put(LOCATION_NAME_COL, localName);
        values.put(LATITUDE_COL, lat);
        values.put(LONGITUDE_COL, lng);


        // Inserting Row
        db.insert(GPS_LOCATION_TABLE, null, values);
        db.close();                                                                                 // Closing database connection


    }

    // add gps sub group
    public void addGpsSubGroup(String grpCode, String subGrpCode, String subGrpName, String description) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(SUB_GROUP_NAME_COL, subGrpName);
        values.put(DESCRIPTION_COL, description);


        // Inserting Row
//        long id =
        db.insert(GPS_SUB_GROUP_TABLE, null, values);
        db.close(); // Closing database connection

        //  Log.d(TAG, "New Group inserted into GPS_SUB_GROUP_TABLE: " + id);

    }


    // add gps Fdp
    public void addStaffFDPAccess(String staffCode, String countryCode, String fdpCode, String btnNew, String btnSave, String btnDel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_CODE, staffCode);
        values.put(STAFF_FDP_ACCESS_COUNTRY_CODE, countryCode);
        values.put(FDP_CODE_COL, fdpCode);
        values.put(BTN_NEW_COL, btnNew);
        values.put(BTN_SAVE_COL, btnSave);
        values.put(BTN_DEL_COL, btnDel);


        // Inserting Row
        long id = db.insert(STAFF_FDP_ACCESS_TABLE, null, values);
        db.close(); // Closing database connection

        //Log.d(TAG, "New Group inserted into " + STAFF_FDP_ACCESS_TABLE + ": " + id);

    }

    // add gps Fdp
    public void addFDPMaster(String countryCode, String fdpCode, String fdpName, String fdpCat, String whCode, String layer1, String layer2) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FDP_MASTER_COUNTRY_CODE, countryCode);
        values.put(FDP_CODE_COL, fdpCode);

        values.put(FDP_NAME_COL, fdpName);
        values.put(FDA_CATEGORIES_CODE_COL, fdpName);
        values.put(WH_CODE_COL, whCode);
        values.put(FDP_MASTER_LAY_R1_LIST_CODE_COL, layer1);
        values.put(FDP_MASTER_LAY_R2_LIST_CODE_COL, layer2);


        // Inserting Row
        long id = db.insert(FDP_MASTER_TABLE, null, values);
        db.close(); // Closing database connection


    }


    /**
     * @param query sql syntax
     * @return insert id
     */
    public long insertIntoUploadTable(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQL_QUERY_SYNTAX, query);
        values.put(SYNC_COL, "0");
        long id = db.insert(UPLOAD_SYNTAX_TABLE, null, values);

        db.close();
        return id;

    }


    public long insertIntoUploadPhysicalTable(String query, int sequence) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQL_QUERY_SYNTAX, query);
        values.put(DT_R_SEQ_COL, sequence);
        values.put(SYNC_COL, "0");
        long id = db.insert(UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE, null, values);

        db.close();
        return id;

    }


    public int uploadStatusFlagOfUploadTable(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SYNC_COL, 1);
        return db.update(UPLOAD_SYNTAX_TABLE, values, ID_COL + " = ? ", new String[]{String.valueOf(id)});

    }

    /**
     * Used in Synchronize data in MainActivity
     * <p/>
     * for sql Query data
     */
    public ArrayList<dataUploadDB> getUploadSyntaxData() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<dataUploadDB> allData = new ArrayList<dataUploadDB>();


        String selectQuery = "SELECT  * FROM " + UPLOAD_SYNTAX_TABLE + " WHERE " + SYNC_COL + "=0 "
                + " ORDER BY " + ID_COL + " ASC ";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                dataUploadDB data = new dataUploadDB();
                data._id = cursor.getString(cursor.getColumnIndex(ID_COL));
                data._syntax = new String(cursor.getBlob(cursor.getColumnIndex(SQL_QUERY_SYNTAX)));

                allData.add(data);

            } while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return allData;
    }


    public String getAwardGraduation(String cCode, String donorCode, String awardCode) {
        String grdDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + AWARD_END_DATE_COL +
                " FROM " + ADM_COUNTRY_AWARD_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' ";


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                grdDate = cursor.getString(0);
            }

            cursor.close();
            db.close();
        }


        return grdDate;
    }


    public void addInLupSrvOptionListFromOnline(String countryCode, String progCode, String srvCode, String optionCode, String optionName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(PROG_CODE_COL, progCode);
        values.put(SRV_CODE_COL, srvCode);
        values.put(LUP_OPTION_CODE_COL, optionCode);
        values.put(LUP_OPTION_NAME_COL, optionName);

        db.insert(LUP_SRV_OPTION_LIST_TABLE, null, values);

        db.close();

    }

    public void addVoucherItemTableFromOnline(String catCode, String itemCode, String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VOUCHER_ITEM_CATEGORY_CODE_COL, catCode);
        values.put(ITEM_CODE_COL, itemCode);
        values.put(ITEM_NAME_COL, itemName);


        db.insert(VOUCHER_ITEM_TABLE, null, values);

        db.close();

    }

    public void addVoucherItemMeasFromOnline(String measRCode, String uniteMeas, String measeTitle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEAS_R_CODE_COL, measRCode);
        values.put(UNITE_MEAS_COL, uniteMeas);
        values.put(MEASE_TITLE_COL, measeTitle);


        db.insert(VOUCHER_ITEM__MEAS_TABLE, null, values);

        db.close();

    }


    public void addVoucherCountryProgItemFromOnline(String countryCode, String donorCode, String awardCode,
                                                    String progCode, String srvCode, String catCode,
                                                    String itemCode, String mearCode, String itemSpec,
                                                    String uniteCost, String active, String currency
    ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(ADM_SRV_CODE_COL, srvCode);
        values.put(VOUCHER_ITEM_CATEGORY_CODE_COL, catCode);
        values.put(ITEM_CODE_COL, itemCode);
        values.put(MEAS_R_CODE_COL, mearCode);
        values.put(VOUCHER_ITEM_SPEC_COL, itemSpec);
        values.put(UNITE_COST_COL, uniteCost);
        values.put(ACTIVE_STATUS_COL, active);
        values.put(CURRENCY_COL, currency);


        db.insert(VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE, null, values);

        db.close();

    }


    public void addGPS_SubGroupAttributesFromOnline(String groupCode, String subGroupCode, String attributeCode, String attributeTitle, String dataType, String lupTableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, groupCode);
        values.put(SUB_GROUP_CODE_COL, subGroupCode);
        values.put(ATTRIBUTE_CODE_COL, attributeCode);
        values.put(ATTRIBUTE_TITLE_COL, attributeTitle);
        values.put(DATA_TYPE_COL, dataType);
        values.put(GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_CODE_COL, lupTableName);

        db.insert(GPS_SUB_GROUP_ATTRIBUTES_TABLE, null, values);

        db.close();

    }


    public void addLUP_GPS_TableFromOnline(String groupCode, String subGroupCode, String attributeCode, String lookUpCode, String lookUpName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, groupCode);
        values.put(SUB_GROUP_CODE_COL, subGroupCode);
        values.put(ATTRIBUTE_CODE_COL, attributeCode);
        values.put(LUP_GPS_TABLE_LOOK_UP_CODE_COL, lookUpCode);
        values.put(GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_NAME_COL, lookUpName);

        db.insert(LUP_GPS_TABLE, null, values);

        db.close();

    }


    public void addGPSLocationAttributesFromOnline(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode, String attributeValue, String photo) {
        String entryBy = "";
        String entryDate = "";
        addGPSLocationAttributes(cCode, groupCode, subGroupCode, locationCode, attributeCode, attributeValue, photo, entryBy, entryDate);
    }


    public void addGPSLocationAttributes(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode, String attributeValue, String photo, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean dataExists = false;

        String where = ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' "
                + " AND " + LOCATION_CODE_COL + " = '" + locationCode + "' "
                + " AND " + ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";

        String sql = "SELECT * FROM " + GPS_LOCATION_ATTRIBUTES_TABLE
                + " WHERE " + where;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {

            dataExists = true;

        }

        if (cursor != null)
            cursor.close();

        ContentValues values = new ContentValues();

        values.put(ATTRIBUTE_VALUE_COL, attributeValue);
        values.put(ATTRIBUTE_PHOTO_COL, photo);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);


        if (dataExists) {
            db.update(GPS_LOCATION_ATTRIBUTES_TABLE, values, where, null);
        } else {
            values.put(ADM_COUNTRY_CODE_COL, cCode);
            values.put(GROUP_CODE_COL, groupCode);
            values.put(SUB_GROUP_CODE_COL, subGroupCode);
            values.put(LOCATION_CODE_COL, locationCode);
            values.put(ATTRIBUTE_CODE_COL, attributeCode);

            long res = db.insert(GPS_LOCATION_ATTRIBUTES_TABLE, null, values);
            if (res == -1)
                Log.e(TAG, "Error for inserting into GPS_LOCATION_ATTRIBUTES_TABLE");

        }

        db.close();

    }


    public void addCommunityGroup(String cCode, String donorCode, String awardCode, String progCode, String grpCode,
                                  String grpName, String grpCatCode, String layR1Code, String layR2Code, String layR3Code, String srvCenterCode
            , String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(GROUP_NAME_COL, grpName);
        values.put(GROUP_CAT_CODE_COL, grpCatCode);
        values.put(LAY_R1_CODE_COL, layR1Code);
        values.put(GRP_LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(GRP_LAY_R3_LIST_CODE_COL, layR3Code);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long i = db.insert(COMMUNITY_GROUP_TABLE, null, values);

        db.close();

    }


    /**
     * Group Details
     *
     * @param cCode              Country Code
     * @param donorCode          donor Code
     * @param awardCode          award Code
     * @param progCode           program Code
     * @param grpCode            Group Code
     * @param ogrCode            organization Code
     * @param staffCode          staff Code
     * @param landSizeUnder      land Size Under
     * @param irrigationSaysUsed iirigration SysUsed
     * @param fundSupport        fund Support
     * @param active             active
     * @param reapName           reap Name
     * @param reapPhone          reap Phone
     * @param formation          formation
     * @param typeOfGrp          type of Grp
     * @param status             status
     * @param entryBy            entry By
     * @param entryDate          entry Date
     * @param projecftNo         project No
     * @param projectTitle       ProjectTitle
     * @param layR1Code          grouplayR1ListCode
     * @param layR2Code          grouplayR2ListCode
     * @param layR3Code          grouplayR3ListCode
     * @see Schema#createTableCommunityGrpDetail() table Schema
     */

    public void addIntoGroupDetails(String cCode, String donorCode, String awardCode, String progCode, String grpCode, String ogrCode, String staffCode, String landSizeUnder, String irrigationSaysUsed, String fundSupport, String active, String reapName, String reapPhone, String formation, String typeOfGrp, String status, String entryBy, String entryDate, String projecftNo, String projectTitle, String layR1Code, String layR2Code, String layR3Code) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(ORG_CODE_COL, ogrCode);
        values.put(LAY_R1_CODE_COL, layR1Code);
        values.put(GRP_LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(GRP_LAY_R3_LIST_CODE_COL, layR3Code);
        values.put(STAFF_CODE_COL, staffCode);
        values.put(LAND_SIZE_UNDER_IRRIGATION_COL, landSizeUnder);
        values.put(IRRIGATION_SYSTEM_USED_COL, irrigationSaysUsed);
        values.put(FUND_SUPPORT_COL, fundSupport);
        values.put(ACTIVE_STATUS_COL, active);
        values.put(REP_NAME_COL, reapName);
        values.put(REP_PHONE_NUMBER_COL, reapPhone);
        values.put(FORMATION_DATE_COL, formation);
        values.put(TYPE_OF_GROUP, typeOfGrp);
        values.put(STATUS, status);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(PROJECT_NO_COL, projecftNo);
        values.put(PROJECT_TITLE, projectTitle);
        db.insert(COMMUNITY_GRP_DETAIL_TABLE, null, values);

        db.close();

    }


    private boolean isAlpha(String nameORid) {
        return nameORid.matches("[a-zA-Z]+");
    }


    public int getNextDTResponseSequence(String dtBasic, String cCode, String donorCode, String awardCode, String prgCode, String entryBy) {
        int nextDTRSeq;
        SQLiteDatabase db = this.getReadableDatabase();
        int tem = -1;
        String sql = " Select " + DT_R_SEQ_COL + " from " + DT_RESPONSE_TABLE + " " +
                " WHERE " +
                " " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + prgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + entryBy + "' " +
                " GROUP By " + DT_R_SEQ_COL + " " +
                " order by " + DT_R_SEQ_COL + " DESC " +
                " limit 1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tem = cursor.getInt(cursor.getColumnIndex(DT_R_SEQ_COL));
            }
            cursor.close();
            db.close();

        }

        if (tem > 0) {
            nextDTRSeq = tem + 1;
        } else
            nextDTRSeq = 1;
        return nextDTRSeq;

    }

    public int getSurveyNumber(String dtBasic, String cCode, String donorCode, String awardCode, String prgCode, String entryBy) {
        int surveyNumber;
        ArrayList<Integer> surveyList = getSurveyList(dtBasic);


        if (surveyList.size() > 0) {
            surveyNumber = UtilClass.getMaxNumberFromList(surveyList.toArray(new Integer[surveyList.size()]));
        } else
            surveyNumber = 1;
        return surveyNumber;

    }

    public int getSurveyTotalNumber(String dtBasic) {
        int totalSurveyNumber = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT count(*)  AS surveyNo FROM " +
                "(SELECT " + DT_R_SEQ_COL + " " +
                " FROM " + DT_SURVEY_TABLE + " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' Group BY " + DT_R_SEQ_COL +
                " ) tbl";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {
            totalSurveyNumber = Integer.parseInt(cursor.getString(0));
            cursor.close();
        }
        return totalSurveyNumber;
    }

    public ArrayList<Integer> getSurveyList(String dtBasic) {
        SQLiteDatabase db = this.getReadableDatabase();
        int tem = -1;
        String sql = " Select DISTINCT " + DT_SURVEY_NUM + " FROM " + DT_SURVEY_TABLE + " " +
                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Integer> surveyList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    tem = cursor.getInt(cursor.getColumnIndex(DT_SURVEY_NUM));
                    if (tem >= 0) {
                        surveyList.add(tem);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        return surveyList;

    }

    public void addIntoDtEnuTable(String dtStfCoe, String admCountryCode, String dtBasicCol, String dtBtnSave, String entryBy,
                                  String usaEntryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_STF_CODE_COL, dtStfCoe);
        values.put(DT_ADM_COUNTRY_CODE_COL, admCountryCode);
        values.put(DT_BASIC_COL, dtBasicCol);
        values.put(DT_BTN_SAVE_COL, dtBtnSave);
        values.put(DT_ENTRY_BY_COL, entryBy);
        values.put(DT_USA_ENTRY_DATE_COL, usaEntryDate);

        db.insert(DT_ENU_TABLE, null, values);
        db.close();
    }


    /**
     * this method get single  question  of Dynamic table  with respect to Dt Basic Code and
     * sequence index (ascending order ) one by one .
     *
     * @param dtBasicCode Dynamic basic Code
     * @param index       question index
     * @return {@link DTQTableDataModel }
     */
    public DTQTableDataModel getSingleDynamicQuestion(String dtBasicCode, int index) {
        DTQTableDataModel singleQus = new DTQTableDataModel();

        SQLiteDatabase db = this.getReadableDatabase();

        // this query didn't maintain the sequence
        String sql = SQLiteQuery.getSingleDynamicQuestion_sql(dtBasicCode, index);


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {

            singleQus.setDtBasicCode(cursor.getString(0));
            singleQus.setDtQCode(cursor.getString(1));
            singleQus.setqText(cursor.getString(2));
            singleQus.setqResModeCode(cursor.getString(3));
            singleQus.setqSeq(cursor.getString(4));
            singleQus.setAllowNullFlag(cursor.getString(5));
            singleQus.setLup_TableName(cursor.getString(6));

            cursor.close();
            db.close();
        }
        return singleQus;
    }

    /**
     *  ei method ta aage sob sequence number gulo nibe  er por seta arrayte rakhbe
     * @param dtBasicCode Dynamic Basic Code
     * @return list of sequence of particular Dynamic Survey
     */
    public List<String> getDTQuestionSequence(String dtBasicCode) {

        List<String> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // this query didn't maintain the sequence
        String sql = "SELECT " + QSEQ_SCOL + " FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String qSequence = cursor.getString(cursor.getColumnIndex(QSEQ_SCOL));

                list.add(qSequence);
            } while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return list;
    }


    public DTQTableDataModel getSingleDynamicQuestion(String dtBasicCode, String dtqCode) {
        DTQTableDataModel singleQus = new DTQTableDataModel();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " AND " + DTQ_CODE_COL + "= '" + dtqCode + "'";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                singleQus.setDtBasicCode(cursor.getString(0));
                singleQus.setDtQCode(cursor.getString(1));
                singleQus.setqText(cursor.getString(2));
                singleQus.setqResModeCode(cursor.getString(3));
                singleQus.setqSeq(cursor.getString(4));
                singleQus.setAllowNullFlag(cursor.getString(5));
                singleQus.setLup_TableName(cursor.getString(6));
            }
            cursor.close();
            db.close();
        }
        return singleQus;
    }


    /**
     * invoking
     *
     * @param qResMode question Response Mode  ques's ans type
     * @return ans repose mode
     * @see DTResponseRecordingActivity#loadDT_QResMode(String)
     */

    public DTQResModeDataModel getDT_QResMode(String qResMode) {
        DTQResModeDataModel responseMode = new DTQResModeDataModel();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQRES_MODE_TABLE +
                " WHERE " + QRES_MODE_COL + "= '" + qResMode + "'";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                responseMode.setDtQResMode(cursor.getString(0));
                responseMode.setDtQResLupText(cursor.getString(1));
                responseMode.setDtDataType(cursor.getString(2).trim());
                responseMode.setDtLookUpUDFName(cursor.getString(3));
                responseMode.setDtResponseValueControl(cursor.getString(4).trim());

            }
            cursor.close();
            db.close();
        }
/*        Log.d("responseTest", " ResMode:" + responseMode.getDtQResMode()
                + " \tResponseValueControl" + responseMode.getDtResponseValueControl()
                + "  setDtQResLupText:"
                + responseMode.getDtQResLupText());*/
        return responseMode;
    }

    /**
     * <p>The DTA Table store the Default Value of All Dynamic View's default value </p>
     *
     * @param dtBasic Basic Code
     * @param dtQCode Question Code
     * @return list of the default View's answer
     */

    public List<DT_ATableDataModel> getDTA_Table(String dtBasic, String dtQCode) {

        List<DT_ATableDataModel> listDTA = new ArrayList<DT_ATableDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getDTA_Table_sql(dtBasic, dtQCode);


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                DT_ATableDataModel dta = new DT_ATableDataModel();

                dta.setDTBasic(cursor.getString(0));
                dta.setDt_QCode(cursor.getString(1));
                dta.setDt_ACode(cursor.getString(2));
                dta.setDt_ALabel(cursor.getString(3));
                dta.setDt_AValue(cursor.getString(4));
                dta.setDt_Seq(cursor.getString(5));
                dta.setDt_AShort(cursor.getString(6));
                dta.setDt_ScoreCode(cursor.getString(7));
                dta.setDt_SkipDTQCode(cursor.getString(8));
                dta.setDt_ACompareCode(cursor.getString(9));
                dta.setShowHide(cursor.getString(10));
                dta.setMaxValue(cursor.getString(11));
                dta.setMinValue(cursor.getString(12));
                dta.setDataType(cursor.getString(13));
                dta.setMarkOnGrid(cursor.getString(14));

                listDTA.add(dta);


            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }


        return listDTA;

    }

    public ArrayList<DTQTableDataModel> getDynamicQuestionList(String dtBasicCode) {
        ArrayList<DTQTableDataModel> list = new ArrayList<DTQTableDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " ORDER BY " + QSEQ_SCOL;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                DTQTableDataModel data = new DTQTableDataModel();

                data.setDtBasicCode(cursor.getString(0));
                data.setDtQCode(cursor.getString(1));
                data.setqText(cursor.getString(2));
                data.setqResModeCode(cursor.getString(3));
                data.setqSeq(cursor.getString(4));
                data.setAllowNullFlag(cursor.getString(5));

                list.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;

    }

    /**
     * this method get the list of Dynamic Data or Survey Subject  index
     *
     * @param cCode         Country Code
     * @param dtTitleSearch Search Key
     * @return DT index list
     */

    public ArrayList<DynamicDataIndexDataModel> getDynamicTableIndexList(final String cCode,
                                                                         String dtTitleSearch,
                                                                         final String staffId,
                                                                         int number) {

        ArrayList<DynamicDataIndexDataModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getDynamicTableIndexList_sql(cCode, dtTitleSearch, staffId, number);


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                DynamicDataIndexDataModel data = new DynamicDataIndexDataModel();
                data.setDtTittle(cursor.getString(cursor.getColumnIndex("dtTitle")));
                data.setDtBasicCode(cursor.getString(cursor.getColumnIndex("dtBasicCode")));
                data.setAwardName(cursor.getString(cursor.getColumnIndex("awardName")));
                data.setAwardCode(cursor.getString(cursor.getColumnIndex("awardCode")));
                data.setProgramName(cursor.getString(cursor.getColumnIndex("prgShortName")));
                data.setProgramCode(cursor.getString(cursor.getColumnIndex("progCode")));

                data.setPrgActivityTitle(cursor.getString(cursor.getColumnIndex("prgActive")));
                data.setcCode(cursor.getString(cursor.getColumnIndex("cCode")));
                data.setOpMode(cursor.getString(cursor.getColumnIndex("dtOpMode")));
                data.setDonorCode(cursor.getString(cursor.getColumnIndex("donoCode")));
                data.setProgramActivityCode(cursor.getString(cursor.getColumnIndex("prgActiveCode")));
                data.setDtShortName(cursor.getString(cursor.getColumnIndex("dtShortName")));
                data.setEntryBy(cursor.getString(cursor.getColumnIndex("entry")));
                data.setFreezePointFlag(cursor.getString(cursor.getColumnIndex("freezPoint")));
                String tem = "";
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(cursor.getString(cursor.getColumnIndex("entryDate")));
                    tem = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
                } catch (ParseException e) {

                }
                data.setEntryDate(tem);


                list.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;


    }

    public int getDynamicTableTotalNumber(final String cCode, String dtTitleSearch,
                                          final String staffId) {
        ArrayList<DynamicDataIndexDataModel> list = getDynamicTableIndexList(cCode, dtTitleSearch,
                staffId, SQLiteQuery.NO_LIMIT);

        return list.size();
    }

    /**
     * @param cCode     countryCode
     * @param donarCode donarCode
     * @param awardCode awardCode
     * @param orgCode   organizationCode
     * @param primeyn   primeyn
     * @param subyn     subyn
     * @param techyn    techyn
     * @param impyn     impyn
     * @param logyn     logyn
     * @param othyn     othyn
     * @see Schema#createTableProgOrgNRole()   the table Schema
     */
    public void insertIntoProgOrgNRole(String cCode, String donarCode, String awardCode, String orgCode, String primeyn, String subyn, String techyn, String impyn, String logyn, String othyn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donarCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ORG_N_CODE_COL, orgCode);
        values.put(PRIME_Y_N_COL, primeyn);
        values.put(SUB_Y_N_COL, subyn);
        values.put(TECH_Y_N_COL, techyn);
        values.put(IMP_Y_N_COL, impyn);
        values.put(LONG_Y_N_COL, logyn);
        values.put(OTH_Y_N_COL, othyn);
        db.insert(PROGRAM_ORGANIZATION_ROLE_TABLE, null, values);


    }


    public long insertIntoProgOrgN(String OrgNCode, String orgNName, String orgNShortName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL, OrgNCode);
        values.put(ORGANIZATION_NAME, orgNName);
        values.put(ORGANIZATION_SHORT_NAME, orgNShortName);
        long id = db.insert(PROGRAM_ORGANIZATION_NAME_TABLE, null, values);
//        Log.d(TAG, "NEW Insert into " + PROGRAM_ORGANIZATION_NAME_TABLE + " Table: " + id);
        return id;
    }


    // add gps group
    public void addGpsGroup(String grpCode, String grpName, String description) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, grpCode); // GROUP code
        values.put(GROUP_NAME_COL, grpName); // GROUP name
        values.put(DESCRIPTION_COL, description); // GROUP description


        // Inserting Row
        long id = db.insert(GPS_GROUP_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New Group inserted into GPS_GROUP_TABLE: " + id);

    }

    // add program master record
    public void addServiceMaster(String pCode, String serviceCode, String serviceN, String srvShortN) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_PROG_CODE_COL, pCode);
        values.put(ADM_SRV_CODE_COL, serviceCode);
        values.put(SERVICE_MASTER_SERVICE_NAME_COL, serviceN);
        values.put(SERVICE_MASTER_SERVICE_SHORT_NAME_COL, srvShortN);
        //values.put(PROGRAM_SHORT_NAME_COL, pShortN);


        // Inserting Row
        long id = db.insert(SERVICE_MASTER_TABLE, null, values);
        db.close();                                                                                     // Closing database connection


    }

    // add program master record
    public void addAdmProgramMaster(String pCode, String awardCode, String donorCode, String pName, String pShortN, String multipleSrv) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_PROG_CODE_COL, pCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_PROGRAM_MASTER_PROGRAM_NAME_COL, pName);
        values.put(PROGRAM_SHORT_NAME_COL, pShortN);
        values.put(MULTIPLE_SERVICE_FLAG_COL, multipleSrv);


        // Inserting Row
        db.insert(ADM_PROGRAM_MASTER_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public void insertIntoAdmAward(String donorCode, String awardCode, String awardName, String awardShortName) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(AWARD_NAME_COL, awardName);
        values.put(AWARD_SHORT_COL, awardShortName);


        // Inserting Row
        db.insert(ADM_AWARD_TABLE, null, values);
        db.close();


    }

    public void insertIntoAdmCountryAward(String cCode, String donorCode, String awardCode, String awardRef, String awardStartD, String awardEndD, String awardShortN, String awardStatus) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);                                                    // country code
        values.put(ADM_DONOR_CODE_COL, donorCode);                                                      // donor code
        values.put(ADM_AWARD_CODE_COL, awardCode);                                                      //  award code
        values.put(AWARD_REF_N_COL, awardRef);                                                      // award reference code
        values.put(AWARD_START_DATE_COL, awardStartD);                                              // awardStartDate
        values.put(AWARD_END_DATE_COL, awardEndD);                                                  // awardEndDate
        values.put(AWARD_SHORT_NAME_COL, awardShortN);                                              // AwardShort Name
        values.put(AWARD_STATUS_COL, awardStatus);                                                  // AwardStatus

        // Inserting Row
        db.insert(ADM_COUNTRY_AWARD_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public void addDonorName(String donorCode, String donorName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_DONOR_CODE_COL, donorCode); // donor code
        values.put(DONOR_NAME_COL, donorName); // donor Name


        // Inserting Row
        long id = db.insert(ADM_DONOR_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New DONOR name  inserted into DONOR_TABLE: " + id);

    }


    /**
     * Getting list of any table with ID - Value pair
     * returns list of labels
     */
    public List<SpinnerHelper> getListAndID(String table_name, String criteria, String cCode, boolean countryLoad) {

        List<SpinnerHelper> listItem = new ArrayList<SpinnerHelper>();

        String selectQuery = "";
        String selectLabel = "Select ";

        switch (table_name) {


            case COUNTRY_TABLE:
                selectQuery = "SELECT DISTINCT " + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + ", " + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_NAME + " FROM " + table_name + criteria;
                selectLabel = "Select Country";

                break;

            case GEO_LAY_R1_LIST_TABLE:
                selectQuery = "SELECT " + table_name + "." + LAY_R_LIST_CODE_COL + ", " + table_name + "." + LAY_R_LIST_NAME_COL + " FROM " + table_name + criteria;
                //selectLabel += getLayerLabel(cCode, "1"); show select Country
                selectLabel = "Select " + getLayerLabel(cCode, "1");

                break;

            case GEO_LAY_R2_LIST_TABLE:
                selectQuery = "SELECT " + table_name + "." + LAY_R2_LIST_CODE_COL + ", " + table_name + "." + LAY_R2_LIST_NAME_COL + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "2");

                break;

            case GEO_LAY_R3_LIST_TABLE:
                selectQuery = "SELECT " + table_name + "." + LAY_R3_LIST_CODE_COL + ", " + table_name + "." + LAY_R3_LIST_NAME + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "3");

                break;

            case GEO_LAY_R4_LIST_TABLE:
                selectQuery = "SELECT " + table_name + "." + LAY_R4_LIST_CODE_COL + ", " + table_name + "." + LAY_R4_LIST_NAME_COL + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "4");

                break;

            case LUP_REG_NHH_RELATION_TABLE:
                selectQuery = "SELECT " + RELATION_CODE + "," + RELATION_NAME + " FROM " + table_name + criteria;
                selectLabel = "Select Relation";
                //listItem.add("Select Village");
                break;
            case ADM_COUNTRY_AWARD_TABLE:
                selectQuery = "SELECT " + ADM_COUNTRY_AWARD_TABLE + "." + ADM_DONOR_CODE_COL + " || '' ||  " + ADM_COUNTRY_AWARD_TABLE + "." + ADM_AWARD_CODE_COL + " AS AwardCode" + " , " +
                        ADM_DONOR_TABLE + "." + DONOR_NAME_COL + " || '-' ||  " + ADM_COUNTRY_AWARD_TABLE + "." + AWARD_SHORT_NAME_COL + " AS AwardName" +
                        " FROM " + table_name + " JOIN " + ADM_DONOR_TABLE + " ON " + ADM_COUNTRY_AWARD_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_DONOR_TABLE + "." + ADM_DONOR_CODE_COL +
                        /* CHANGE*/    criteria + "GROUP BY " + ADM_DONOR_TABLE + "." + DONOR_NAME_COL + " || '-' ||  " + ADM_COUNTRY_AWARD_TABLE + "." + AWARD_SHORT_NAME_COL + " ORDER BY AwardName ";
                selectLabel = "Select Award";
                break;
            // Criteria for service
            case ADM_PROGRAM_MASTER_TABLE:
                selectQuery = "SELECT " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' ||  " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId" + " , " +
                        ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria" +
                        " FROM " + table_name + " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                        criteria + " GROUP BY " + ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " ORDER BY Criteria ";
                selectLabel = "Select Criteria";
                break;
            case GPS_GROUP_TABLE:
                selectQuery = "SELECT DISTINCT " + GROUP_CODE_COL + "," + GROUP_NAME_COL + " FROM " + table_name + criteria;

                selectLabel = "Select Group";
                break;
            case GPS_SUB_GROUP_TABLE:
                selectQuery = "SELECT DISTINCT " + SUB_GROUP_CODE_COL + " , " + SUB_GROUP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select sub Group";
                break;
            case GPS_LOCATION_TABLE:
                selectQuery = "SELECT " + LOCATION_CODE_COL + "," + LOCATION_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select location";
                break;
            // for Program spinner Assigne & gradution
            case ADM_COUNTRY_PROGRAM_TABLE:
                selectQuery = "SELECT " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " , " +
                        ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL +
                        " FROM " + table_name + " JOIN " + ADM_PROGRAM_MASTER_TABLE + " ON " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                        " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL +
                        " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " " +
                        criteria + " GROUP BY " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL;//+" GROUP BY "+ADM_PROGRAM_MASTER_TABLE +"."+PROGRAM_SHORT_NAME_COL +" || '-' ||  " +SERVICE_MASTER_TABLE+"."+SERVICE_SHORT_NAME_COL+" ORDER BY Criteria ";
                selectLabel = "Select Program";
                break;

            case SERVICE_MASTER_TABLE:
                selectQuery = "SELECT " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId" + " , " +
                        SERVICE_MASTER_TABLE + "." + SERVICE_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria" +
                        " FROM " + ADM_COUNTRY_PROGRAM_TABLE + " JOIN " + table_name +
                        " ON " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " AND " +
                        SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " " +
                        criteria + " GROUP BY " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL;

                selectLabel = "Select Criteria";
                break;

            case VILLAGE_TABLE_FOR_ASSIGN:
                selectQuery = "SELECT  v." +
                        ADM_COUNTRY_CODE_COL + " || '' || v." +
                        MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || v." + LAY_R2_LIST_CODE_COL + " || '' || v." + LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL
                        + ", v." + LAY_R4_LIST_NAME_COL + " FROM " + GEO_LAY_R4_LIST_TABLE + criteria;
                selectLabel += getLayerLabel(cCode, "4");
                //listItem.add("Select Village");
                break;
            /** FOR SERVICE SUMMMARY OP_MONTH LOAD */

            case OP_MONTH_TABLE:
                selectQuery = "SELECT " + ADM_COUNTRY_CODE_COL + " || '' || " + ADM_DONOR_CODE_COL + " || '' || " + ADM_AWARD_CODE_COL + " || '' || " + OP_MONTH_CODE_COL + " AS OpMonthID, " + MONTH_LABEL_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Month";

                break;
            case SERVICE_SUMMARY_CRITERIA_QUERY:

                selectQuery = " SELECT " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS IdCriteria ,  " +
                        SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria  " +

                        "FROM " + SERVICE_TABLE + " JOIN " + SRV_CENTER_TABLE
                        + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +

                        " JOIN " + OP_MONTH_TABLE
                        + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + " " +
                        " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_TABLE + "." + ADM_PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " AND " +       // "\t   INNER JOIN AdmServiceMaster ON SrvTable.ProgCode = AdmServiceMaster.AdmProgCode AND \n" +
                        SERVICE_TABLE + "." + ADM_SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL +
                        criteria +
                        " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL;
                selectLabel = "Select Criteria";

                break;


            case LUP_REGNH_HEAD_CATEGORY_TABLE:
                selectQuery = "SELECT " + HH_HEAD_CAT_CODE_COL + " , " + CATEGORY_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select House Hold Type";
                break;


            case SRV_CENTER_TABLE:
                selectQuery = "SELECT " + SERVICE_CENTER_CODE_COL + " , " +
                        SERVICE_CENTER_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Service Center ";
                break;
            case REPORT_TEMPLATE_TABLE: //@date:2015-11-04
                selectQuery = "SELECT " + REPORT_CODE_COL + " , " +
                        REPORT_LABLE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Card Type ";
                break;


            case STAFF_FDP_ACCESS_TABLE:
                selectQuery = "SELECT " + FDP_MASTER_TABLE + "." + FDP_CODE_COL + " AS " + FDP_CODE_COL + " , "
                        + FDP_MASTER_TABLE + "." + FDP_NAME_COL + " AS " + FDP_NAME_COL +
                        " FROM " + table_name + " INNER JOIN "
                        + FDP_MASTER_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + FDP_CODE_COL

                        + criteria;
                break;
            case LUP_SRV_OPTION_LIST_TABLE:
                selectQuery = " SELECT " + LUP_OPTION_CODE_COL + " , " + LUP_OPTION_NAME_COL
                        + " FROM " + LUP_SRV_OPTION_LIST_TABLE + " " + criteria;
                selectLabel = "Select Service";
                break;
            case ASSIGN_SUMMARY_PROGRAM_DETAILS:
                selectQuery = "SELECT " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL
                        + " || '' || " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL
                        + " || '' || " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                        + " , " +
                        ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL +
                        " FROM " + ADM_COUNTRY_PROGRAM_TABLE + " JOIN " + ADM_PROGRAM_MASTER_TABLE + " ON " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                        " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL +
                        " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " " +
                        criteria + " GROUP BY " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL;//+" GROUP BY "+ADM_PROGRAM_MASTER_TABLE +"."+PROGRAM_SHORT_NAME_COL +" || '-' ||  " +SERVICE_MASTER_TABLE+"."+SERVICE_SHORT_NAME_COL+" ORDER BY Criteria ";
                selectLabel = "Select Program";
                break;
            case FDP_LAY_R2:
                selectQuery = " Select DISTINCT  " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL + " || " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " AS code "
                        + " , " + GEO_LAY_R2_LIST_TABLE + " ." + LAY_R2_LIST_NAME_COL + " AS Name "
                        + " FROM  " + STAFF_FDP_ACCESS_TABLE
                        + "  INNER JOIN         " + FDP_MASTER_TABLE
                        + "   ON         " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + FDP_MASTER_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND         " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + "   INNER JOIN    " + GEO_LAY_R2_LIST_TABLE
                        + "   ON    " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE + " = " + GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R1_LIST_CODE_COL + " = " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R2_LIST_CODE_COL + " = " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL

                        + " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + FDP_CODE_COL


                        + criteria
                ;

                selectLabel = "Select ";
                break;
            case VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE:
                selectQuery = criteria;
                selectLabel = "Select Item";
                break;

            case CUSTOM_QUERY:
                selectQuery = criteria;
                selectLabel = "Select ";
                break;
            case LUP_COMMUNITY_ANIMAL_TABLE:
                selectQuery = "SELECT  " + ANIMAL_CODE_COL + " , " + ANIMAL_TYPE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Animal Type";

                break;
            case LUP_PROG_GROUP_CROP_TABLE:
                selectQuery = "SELECT  " + CROP_CODE_COL + " , " + CROP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Crop Type";

                break;


            case LUP_COMMUNITY_LOAN_SOURCE_TABLE:
                selectQuery = "SELECT  " + LOAN_CODE_COL + " , " + LOAN_SOURCE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Loan Source";

                break;

            case LUP_COMMUNITY_LEAD_POSITION_TABLE:
                selectQuery = "SELECT  " + LEAD_CODE_COL + " , " + LEAD_POSITION_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Loan Source";

                break;

            case COMMUNITY_GROUP_CATEGORY_TABLE:
                selectQuery = "SELECT  " + GROUP_CAT_CODE_COL + " , " + GROUP_CAT_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select ";

                break;

            case COMMUNITY_GROUP_TABLE:
                selectQuery = "SELECT  " + GROUP_CODE_COL + " , " + GROUP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select";

                break;
            case LUP_REGN_ADDRESS_LOOKUP_TABLE:
                selectQuery = "SELECT " + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " , " + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL
                        + " FROM " + table_name + criteria;
                selectLabel = " Select";
                break;


        }

        //selectQuery = "SELECT * FROM " + table_name ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (selectLabel == null) selectLabel = "Select..";
        int position = 0;

        if (!countryLoad) { // all spinner show the select except load country of Main Activity
            listItem.add(new SpinnerHelper(position, "00", selectLabel));
            position++;
        }


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                listItem.add(new SpinnerHelper(position, cursor.getString(0), cursor.getString(1)));
//                Log.d(TAG, " table name :" + table_name + " :- " + cursor.getColumnName(0) + " : " + cursor.getString(0) + "  " + cursor.getColumnName(1) + " : " + cursor.getString(1));
                position++;
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning list item
        return listItem;
    }


    public long addLUP_RegNAddLookup(String countryCode, String addressLookupCode, String addressLookup, String districtCode, String upozillaCode, String unitCode, String villageCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressLookupCode);
        values.put(REGN_ADDRESS_LOOKUP_NAME_COL, addressLookup);
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, districtCode);
        values.put(LAY_R2_LIST_CODE_COL, upozillaCode);
        values.put(LAY_R3_LIST_CODE_COL, unitCode);
        values.put(LAY_R4_LIST_CODE_COL, villageCode);

        long id = db.insert(LUP_REGN_ADDRESS_LOOKUP_TABLE, null, values);
        db.close();
        return id;
    }


    /**
     * Storing Country info into database
     */

    public void addCountry(String code, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_COUNTRY_CODE, code); // Country code
        values.put(COUNTRY_COUNTRY_NAME, name); // Country name


        db.insert(COUNTRY_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New Country inserted: " + id);
    }

    public void insertSelectedCountry(String code, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, code);
        values.put(COUNTRY_COUNTRY_NAME, name);


        db.insert(SELECTED_COUNTRY_TABLE, null, values);
        db.close();


    }


    /**
     * Storing Country info into database
     */

    public void addValidDateRange(String code, String sdate, String edate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATE_RANGE_COUNTRY_CODE, code); // Country code
        values.put(DATE_START, sdate); // start date
        values.put(DATE_END, edate); // end date

        // Inserting Row
        long id = db.insert(VALID_DATE_RANGE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New date range inserted: " + id);
    }


    /**
     * Storing Layer Label info into database
     */

    public void addGeoLayRMaster(String c_code, String l_code, String l_name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LAYER_LAVLE_COUNTRY_CODE, c_code);                                               // Country code
        values.put(GEO_LAY_R_CODE_COL, l_code);                                                         // Layer code
        values.put(GEO_LAY_R_NAME_COL, l_name);                                                         // Layer name


        db.insert(GEO_LAY_R_MASTER_TABLE, null, values);                                            // Inserting Row
        db.close();                                                                                 // Closing database connection


    }


    /**
     * Storing District details into database
     */
    public void addGeoLayR1List(String country, String GeoLayRCode, String code, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country);                                                   // country code
        values.put(GEO_LAY_R_CODE_COL, GeoLayRCode);                                                // Layer code
        values.put(LAY_R_LIST_CODE_COL, code);                                                      // district code
        values.put(LAY_R_LIST_NAME_COL, name);                                                      // district name


        db.insert(GEO_LAY_R1_LIST_TABLE, null, values);                                                 // Inserting Row
        db.close();                                                                                 // Closing database connection


    }


    // Storing Card Reason  details into database
    // @date: 2015-11-05

    public void addLupRegNCardPrintReason(String reason_code, String reason_title) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CARD_PRINT_REASON_CODE_COL, reason_code);
        values.put(CARD_PRINT_REASON_TITLE_COL, reason_title);


        db.insert(LUP_REGN_CARD_PRINT_REASON_TABLE, null, values);                                           // Inserting Row
        db.close();                                                                                 // Closing database connection


    }


    /**
     * Storing Upazilla details into database
     */
    public void addGeoLayR2List(String country, String GeoLayRCode, String dcode, String upcode, String upname) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country);                                                  // country code
        values.put(GEO_LAY_R_CODE_COL, GeoLayRCode);                                                // Layer code
        values.put(LAY_R1_LIST_CODE_COL, dcode);                                                    // district code
        values.put(LAY_R2_LIST_CODE_COL, upcode);                                                   // upazilla code
        values.put(LAY_R2_LIST_NAME_COL, upname);                                                       // upazilla name

        // Inserting Row
        long id = db.insert(GEO_LAY_R2_LIST_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New UPAZILLA_ inserted into Upazilla: " + id);
    }


    /**
     * Storing Unit details into database
     */
    public void addGeoLayR3List(String country, String GeoLayRCode, String dcode, String upcode, String ucode, String uname) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country);                                                  // country code
        values.put(GEO_LAY_R_CODE_COL, GeoLayRCode);                                                    // Layer code
        values.put(LAY_R1_LIST_CODE_COL, dcode);                                                     //  district code
        values.put(LAY_R2_LIST_CODE_COL, upcode);                                                   // upazilla code
        values.put(LAY_R3_LIST_CODE_COL, ucode);                                                    // unit code
        values.put(LAY_R3_LIST_NAME, uname);                                                        // unit name


        db.insert(GEO_LAY_R3_LIST_TABLE, null, values);                                              // Inserting Row
        db.close();                                                                                 // Closing database connection


    }

    public void addSelectedVillage(String country, String dcode, String upcode, String ucode, String vcode, String layrCode, String vname, String addressCode) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("CountryCode", country);
        values.put("DistrictCode", dcode);
        values.put("UpazillaCode", upcode);
        values.put("UnitCode", ucode);
        values.put("VillageCode", vcode);
        values.put(GEO_LAY_R_CODE_COL, layrCode);
        values.put("VillageName", vname);
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressCode);

        db.insert(SELECTED_VILLAGE_TABLE, null, values);
        db.close();


    }


    /**
     * Storing Village details into database
     */
    public void addGeoLayR4List(String country, String GeoLayRCode, String dcode, String upcode, String ucode, String vcode, String vname, String hhtarget) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country);                                                  // country code
        values.put(GEO_LAY_R_CODE_COL, GeoLayRCode);                                                // Layer code
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, dcode);                                     //  district code
        values.put(LAY_R2_LIST_CODE_COL, upcode);                                                   // upazilla code
        values.put(LAY_R3_LIST_CODE_COL, ucode);                                                    // unit code
        values.put(LAY_R4_LIST_CODE_COL, vcode);                                                    // Village code
        values.put(LAY_R4_LIST_NAME_COL, vname);                                                    // Village name
        values.put(HOUSE_HOLD_TARGET, hhtarget);                                                    // Village 's house hold target


        db.insert(GEO_LAY_R4_LIST_TABLE, null, values);                                             // Inserting Row
        db.close();                                                                                 // Closing database connection


    }

    // Storing Relation details into database

    public void addLupRegNHHRelation(String rel_code, String rel_name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RELATION_CODE, rel_code);                                                        // relation code
        values.put(RELATION_NAME, rel_name);                                                        // relation value


        db.insert(LUP_REG_NHH_RELATION_TABLE, null, values);                                        // Inserting Row
        db.close();                                                                                 // Closing database connection


    }


    public void addRptTemplate(String country_code, String cardType_lable, String cardType_code) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, country_code);
        values.put(REPORT_LABLE_COL, cardType_lable);
        values.put(REPORT_CODE_COL, cardType_code);


        db.insert(REPORT_TEMPLATE_TABLE, null, values);                                             // Inserting Row
        db.close();                                                                                 // Closing database connection


    }


    /**
     * @param cCode country code
     * @return Dt Response Month Name
     */
    public String getDtResponseMonthName(String cCode, String opCode, String opMonthCode,
                                         String donorCode, String awardCode) {
        String monthName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.loadDtMonth_sql(cCode, opCode, opMonthCode, donorCode, awardCode);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            monthName = cursor.getString(cursor.getColumnIndex(MONTH_LABEL_COL));

        }
        if (cursor != null)
            cursor.close();
        db.close();
        return monthName;

    }


    // Check Local Login
    public boolean isValidLocalLogin(final String user, final String pass) {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + LOGIN_TABLE
                + " WHERE " + USER_LOGIN_NAME + " = " + "'" + user + "' AND " + USER_LOGIN_PW + " = " + "'" + pass + "'";
        try {

            final Cursor cursor = db1.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.close();
                    return true;
                } else {
                    cursor.close();
                    return false;
                }

            }

        } catch (Exception e) {
//            Log.d(TAG, "isValidLocalLogin() Method: " + e.getMessage());

        } finally {
            // close database connection

            db1.close();
        }
        return false;
    }

    /**
     * this method check is the user is admin
     *
     * @param admin_user admin_user
     * @param admin_pass password
     * @return
     */

    public boolean isValidAdminLocalLogin(final String admin_user, final String admin_pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + STAFF_MASTER_TABLE
                + " WHERE " + USER_LOGIN_NAME + " = " + "'" + admin_user + "' "
                + " AND " + USER_LOGIN_PW + " = " + "'" + admin_pass + "' "
                + " AND " + STAFF_ADMIN_ROLE_COL + " IN ('A' ,'C') ";

        try {

            Cursor c = db.rawQuery(selectQuery, null);
            if (c != null) {
                if (c.getCount() > 0) {
                    return true;
                } else {
                    return false;
                }

            }
            c.close();
        } catch (Exception e) {
//            Log.d(TAG, "isValidLocalLogin() Method: " + e.getMessage());

        } finally {

            // close database connection
            db.close();
        }
        return false;
    }


    /**
     * Storing user details in database
     */
    public void addUser(String user_id, String country_code, String login_name, String login_pw, String first_name, String last_name, String email, String email_verification, String user_status, String entry_by, String entry_date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user_id);
        values.put(COUNTRY_CODE, country_code);
        values.put(USER_LOGIN_NAME, login_name);
        values.put(USER_LOGIN_PW, login_pw);
        values.put(USER_FIRST_NAME, first_name);
        values.put(USER_LAST_NAME, last_name);
        values.put(USER_EMAIL, email);
        values.put(USER_EMAIL_VERIFICATION, email_verification);
        values.put(USER_STATUS, user_status);
        values.put(ENTRY_BY, entry_by);
        values.put(ENTRY_DATE, entry_date);

        // Inserting Row
        long id = db.insert(LOGIN_TABLE, null, values);
        db.close(); // Closing database connection
        Log.d("MOR_12", "New user inserted into User Login: " + id);
    }


    public int getDeviceOperationModeCode() {
        int deviceOperationModeCode = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + SELECTED_OPERATION_MODE_CODE_COL + " FROM " + SELECTED_OPERATION_MODE_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            deviceOperationModeCode = cursor.getInt(cursor.getColumnIndex(SELECTED_OPERATION_MODE_CODE_COL));
            cursor.close();
        }

        db.close();
        return deviceOperationModeCode;
    }


    /**
     * Getting Layer Label
     */
    public String getLayerLabel(String c_code, String l_code) {
        String layerName = "";
        String selectQuery = "SELECT  " + GEO_LAY_R_NAME_COL + " FROM " + GEO_LAY_R_MASTER_TABLE + " WHERE " + LAYER_LAVLE_COUNTRY_CODE + "='" + c_code + "' AND " + GEO_LAY_R_CODE_COL + "='" + l_code + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            layerName = cursor.getString(0);
        }
        cursor.close();
        db.close();

        return layerName;
    }


    /**
     * Getting user data from database [for future use]
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        String selectQuery = "SELECT  * FROM " + LOGIN_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("code", cursor.getString(0));      // UsrID as StaffCode
            user.put("c_code", cursor.getString(1));    // CountryCode as Country Code
            user.put("username", cursor.getString(2));    // userid
            user.put("password", cursor.getString(3));    // password

            user.put("name", cursor.getString(4));      // UsrFirstName as name
            user.put("status", cursor.getString(8));    // UsrStatus as user status
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }


    /**
     * Getting Operation Startig date & End date data from database
     * [ For Graduation date]
     * Faisal Mohammad
     *
     * @since : 2015-10-01
     */
    public HashMap<String, String> getGRDDateRange(String cCode) {
        HashMap<String, String> dateRange = new HashMap<String, String>();

        String selectQuery = "SELECT  " + START_DATE_COL + " , " + END_DATE_COL + " FROM " + OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' AND " +
                OPERATION_CODE_COL + " = '1' AND " + STATUS + " = 'A'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            dateRange.put("c_code", cursor.getString(1));    // CountryCode as Country Code
            dateRange.put("sdate", cursor.getString(2));      // Start Date
            dateRange.put("edate", cursor.getString(3));    // End Date
        } else {
            dateRange.put("c_code", null);                  // CountryCode as Country Code
            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }
        cursor.close();
        db.close();

        return dateRange;
    }


    /**
     * This method invoking Form
     *
     * @param cCode   Country Code
     * @param opMonth Op Month Code
     * @return A  Hash Map of startDate & end Date
     * @see {@link }
     * This method  return Date the Range of Dt
     */

    public HashMap<String, String> getDynamicTableDateRange(String cCode, String opMonth) {
        HashMap<String, String> dateRange = new HashMap<String, String>();


        String sql = "SELECT  "
                + "  " + START_DATE_COL
                + " , " + END_DATE_COL
                + " FROM " + OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + STATUS + "= 'A'"
                + " AND " + OPERATION_CODE_COL + " = '5'"
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonth + "'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            dateRange.put("sdate", cursor.getString(0));      // Start Date
            dateRange.put("edate", cursor.getString(1));    // End Date

            cursor.close();
            db.close();
        } else {

            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }


        return dateRange;
    }

    /**
     * Getting user data from database [for future use]
     * review the method
     */
    public HashMap<String, String> getDateRange(String cCode) {
        HashMap<String, String> dateRange = new HashMap<String, String>();


        String sql = "SELECT  " + ADM_COUNTRY_CODE_COL
                + " , " + START_DATE_COL +
                " , " + END_DATE_COL
                + " FROM " + OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + STATUS + "= 'A'"
                + " AND " + OPERATION_CODE_COL + " = '1' " +
                "  ORDER BY " + OP_MONTH_CODE_COL + "   DESC   LIMIT 1 ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            dateRange.put("c_code", cursor.getString(0));    // CountryCode as Country Code
            dateRange.put("sdate", cursor.getString(1));      // Start Date
            dateRange.put("edate", cursor.getString(2));    // End Date
        } else {
            dateRange.put("c_code", null);                  // CountryCode as Country Code
            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }
        cursor.close();
        db.close();

        return dateRange;
    }


    public int selectUploadSyntextRowCount() {
        Cursor cursor;
        String count = "0";

        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT COUNT( " + UPLOAD_SYNTAX_TABLE + "." + SYNC_COL + ")" + " FROM " + UPLOAD_SYNTAX_TABLE + " WHERE " + UPLOAD_SYNTAX_TABLE + "." + SYNC_COL + " = " + 0;
        cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getString(0);
            }
            cursor.close();
        }

        db.close();
        return Integer.valueOf(count);
    }


    public long insertIntoLastSyncTraceStatus(String userId, String userName, String lastSyncTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USER_ID, userId);
        values.put(USER_LOGIN_NAME, userName);
        values.put(LAST_SYNC_TIME_COL, lastSyncTime);

        return db.insert(LAST_SYNC_TYRACE_TABLE, null, values);


    }

    public String getLastSyncStatus() {
        String query = "SELECT " + LAST_SYNC_TIME_COL + " FROM " + LAST_SYNC_TYRACE_TABLE + " ORDER BY " + ID_COL + " DESC LIMIT " + 1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String date = "";
        if (cursor.moveToFirst()) {
            date = cursor.getString(cursor.getColumnIndex(LAST_SYNC_TIME_COL));
        }
        cursor.close();
        db.close();
        return date;
    }

    public void addIntoDTATable(String dtBasic, String dtqCode, String dtaCode, String dtaLabel, String dtaValue,
                                long dtSeq, String dtaShort, String dtScoreCode, String dtSkipDTQCode, String dtaCompareCode, String showHide,
                                String maxValue, String minValue, String dataType, String markOnGrid, String entryBy, String entryDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DT_BASIC_COL, dtBasic);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(DTA_CODE_COL, dtaCode);
        values.put(DTA_LABEL_COL, dtaLabel);
        values.put(DTA_VALUE_COL, dtaValue);
        values.put(DT_SEQ_COL, dtSeq);
        values.put(DTA_SHORT_COL, dtaShort);
        values.put(DT_SCORE_CODE_COL, dtScoreCode);
        values.put(DTSKIP_DTQ_CODE_COL, dtSkipDTQCode);
        values.put(DTA_COMPARE_CODE_COL, dtaCompareCode);
        values.put(SHOW_HIDE_COL, showHide);
        values.put(MAX_VALUE_COL, maxValue);
        values.put(MIN_VALUE_COL, minValue);
        values.put(DATA_TYPE_COL, dataType);
        values.put(MARK_ON_GRID_COL, markOnGrid);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(DT_A_TABLE, null, values);
        db.close();
    }


    public void addIntoDTBasic(String dtBasic, String dtTitle, String dtSubTitle,
                               String dtDescription, String dtAutoScroll, String dtAutoScrollText,
                               String dtActive, String dtCategory, String dtGeoListLevel,
                               String dtOpMode, String dtShortName, String entryBy, String entryDate,
                               String freezPoint,String pinNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(DT_TITLE_COL, dtTitle);
        values.put(DT_SUB_TITLE_COL, dtSubTitle);
        values.put(DT_DESCRIPTION_COL, dtDescription);
        values.put(DT_AUTO_SCROLL_COL, dtAutoScroll);
        values.put(DTAUTO_SCROLL_TEXT, dtAutoScrollText);
        values.put(DT_ACTIVE_COL, dtActive);
        values.put(DT_CATEGORY_COL, dtCategory);
        values.put(DT_GEO_LIST_LEVEL_COL, dtGeoListLevel);
        values.put(DT_OP_MODE_COL, dtOpMode);
        values.put(DT_SHORT_NAME_COL, dtShortName);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(FREEZE_POINT_COL, freezPoint);
        values.put(PIN_NUMBER_COL, pinNumber);

        db.insert(DT_BASIC_TABLE, null, values);
        db.close();
    }

    public void addIntoDTCategory(String dtCategory, String categoryName, String frequency, String entryBy,
                                  String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_CATEGORY_COL, dtCategory);
        values.put(DT_CATEGORY_TABLE_CATEGORY_NAME_COL, categoryName);
        values.put(FREQUENCY_COL, frequency);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(DT_CATEGORY_TABLE, null, values);
        db.close();
    }

    public void addIntoDTCountryProgram(String countryCode, String donorCode, String awardCode, String programCode,
                                        String progActivityCode, String progActivityTitle, String dtBasic, String refIdentifier,
                                        String status, String rftFrequency, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(PROG_ACTIVITY_TITLE_COL, progActivityTitle);
        values.put(DT_BASIC_COL, dtBasic);
        values.put(REF_IDENTIFIER_COL, refIdentifier);
        values.put(STATUS, status);
        values.put(RPT_FREQUENCY_COL, rftFrequency);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(DT_COUNTRY_PROGRAM_TABLE, null, values);
        db.close();
    }

    public void addIntoDTGeoListLevel(String geoLevel, String geoLevelName, String listUDFName, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(GEO_LEVEL_COL, geoLevel);
        values.put(GEO_LEVEL_NAME_COL, geoLevelName);
        values.put(LIST_UDF_NAME_COL, listUDFName);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(DTGEO_LIST_LEVEL_TABLE, null, values);
        db.close();
    }

    public void addIntoDTLUP(String cCode, String tableName, String listCode, String listName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(TABLE_NAME_COL, tableName);
        values.put(LIST_CODE_COL, listCode);
        values.put(LIST_NAME_COL, listName);


        db.insert(DT_LUP_TABLE, null, values);
        db.close();
    }

    public void addIntoDTASkipTable(String dtBasicCode, String dtQCode, String skipCode, String dtaCodeComB, String dtSkipDTQCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasicCode);
        values.put(DTQ_CODE_COL, dtQCode);
        values.put(SKIP_CODE_COL, skipCode);
        values.put(DTA_CODE_COMB_N_COL, dtaCodeComB);
        values.put(DTSKIP_DTQ_CODE_COL, dtSkipDTQCode);


        db.insert(DTA_SKIP_TABLE, null, values);
        db.close();
    }

    public void addIntoDTQResMode(String qResMode, String qResLupText, String dataType, String lookUpUDFName, String responseValueControl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(QRES_MODE_COL, qResMode);
        values.put(QRES_LUP_TEXT_COL, qResLupText);
        values.put(DATA_TYPE_COL, dataType);
        values.put(LOOK_UP_UDF_NAME_COL, lookUpUDFName);
        values.put(RESPONSE_VALUE_CONTROL_COL, responseValueControl);

        db.insert(DTQRES_MODE_TABLE, null, values);
        db.close();
    }

    public void addIntoDTQTable(String dtBasic, String dtqCode, String qText, String qResMode, String qSeq, String allowNull, String lub_tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(QTEXT_COL, qText);
        values.put(QRES_MODE_COL, qResMode);
        values.put(QSEQ_SCOL, qSeq);
        values.put(ALLOW_NULL_COL, allowNull);
        values.put(LUP_TABLE_NAME, lub_tableName);

        long id = db.insert(DTQ_TABLE, null, values);
        db.close();
    }

    /**
     * This method insert the data into {@link #DT_RESPONSE_TABLE } .
     * if the data is unsync Data then it invoke the {@link #insertIntoUploadTable(String)}
     * the query generate from {@link SQLServerSyntaxGenerator#insertIntoDTResponseTable()} }
     *
     * @param dtBasic          dt Basic Code
     * @param countryCode      countryCode
     * @param donorCode        donor Code
     * @param awardCode        award Code
     * @param programCode      program Code
     * @param dtEnuId          dt EnuId
     * @param dtqCode          dt Question  Code
     * @param dtaCode          dt Answer  Code
     * @param dtrSeq           dtr Sequence
     * @param dtaValue         dta Value
     * @param progActivityCode program Activity Code
     * @param dttTimeString    dtt Time String
     * @param opMode           op Mode
     * @param opMonthCode      op Month Code
     * @param dataType         data Type
     * @param imageString      image base64 String fromat
     * @param unSync           unSync
     */
    public long addIntoDTResponseTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                       String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                       String progActivityCode, String dttTimeString, String opMode, String opMonthCode, String dataType, String imageString, boolean unSync) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(DT_ENU_ID_COL, dtEnuId);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(DTA_CODE_COL, dtaCode);
        values.put(DT_R_SEQ_COL, dtrSeq);
        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DATA_TYPE_COL, dataType);
        values.put(U_FILE_COL, imageString);

        long row = -1;
        db.insert(DT_RESPONSE_TABLE, null, values);
        db.close();
        // upload syntax section if the data is unsync
        if (unSync) {
            SQLServerSyntaxGenerator mSyntaxGenerator = new SQLServerSyntaxGenerator();
            mSyntaxGenerator.setDTBasic(dtBasic);
            mSyntaxGenerator.setAdmCountryCode(countryCode);
            mSyntaxGenerator.setAdmDonorCode(donorCode);
            mSyntaxGenerator.setAdmAwardCode(awardCode);
            mSyntaxGenerator.setAdmProgCode(programCode);
            mSyntaxGenerator.setDTEnuID(dtEnuId);
            mSyntaxGenerator.setDTQCode(dtqCode);
            mSyntaxGenerator.setDTACode(dtaCode);
            mSyntaxGenerator.setDTRSeq(String.valueOf(dtrSeq));
            mSyntaxGenerator.setDTAValue(dtaValue);
            mSyntaxGenerator.setProgActivityCode(progActivityCode);
            mSyntaxGenerator.setDTTimeString(dttTimeString);
            mSyntaxGenerator.setOpMode(opMode);
            mSyntaxGenerator.setOpMonthCode(opMonthCode);
            mSyntaxGenerator.setDataType(dataType);
            mSyntaxGenerator.setCompleteness("Y");

            mSyntaxGenerator.setUFILE(imageString);
            row = insertIntoUploadTable(mSyntaxGenerator.insertIntoDTResponseTable());


        }
        return row;
    }


    public void updateIntoDTResponseTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                          String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                          String progActivityCode, String dttTimeString, String opMode, String opMonthCode, String dataType, String imageString) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;

        ContentValues values = new ContentValues();

        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DATA_TYPE_COL, dataType);
        values.put(U_FILE_COL, imageString);


        int id = db.update(DT_RESPONSE_TABLE, values, where, null);

        db.close();     // close the db


        SQLServerSyntaxGenerator mSyntaxGenerator = new SQLServerSyntaxGenerator();
        mSyntaxGenerator.setDTBasic(dtBasic);
        mSyntaxGenerator.setAdmCountryCode(countryCode);
        mSyntaxGenerator.setAdmDonorCode(donorCode);
        mSyntaxGenerator.setAdmAwardCode(awardCode);
        mSyntaxGenerator.setAdmProgCode(programCode);
        mSyntaxGenerator.setDTEnuID(dtEnuId);
        mSyntaxGenerator.setDTQCode(dtqCode);
        mSyntaxGenerator.setDTACode(dtaCode);
        mSyntaxGenerator.setDTRSeq(String.valueOf(dtrSeq));
        mSyntaxGenerator.setDTAValue(dtaValue);
        mSyntaxGenerator.setProgActivityCode(progActivityCode);
        mSyntaxGenerator.setDTTimeString(dttTimeString);
        mSyntaxGenerator.setOpMode(opMode);
        mSyntaxGenerator.setOpMonthCode(opMonthCode);
        mSyntaxGenerator.setDataType(dataType);
        mSyntaxGenerator.setCompleteness("Y");
        mSyntaxGenerator.setUFILE(imageString);
        insertIntoUploadTable(mSyntaxGenerator.updateIntoDTResponseTable());

        Log.d(TAG, " no of row :" + id);

    }

    public void updateCompleteStatusDTResponseTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                    String dtEnuId, String dtrSeq, String opMonthCode) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;

        ContentValues values = new ContentValues();

        values.put(COMPLETENESS_COL, "Y");
        int id = db.update(DT_RESPONSE_TABLE, values, where, null);


        String where_sryv = DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;

        int id_1 = db.update(DT_SURVEY_TABLE, values, where_sryv, null);
        db.close();     // close the db


    }

    public void deleteInCompelteData() {


        SQLiteDatabase db = this.getReadableDatabase();
        List<DTResponseTableDataModel> list = new ArrayList<>();
        String sql_srv = " SELECT  " + DT_BASIC_COL
                + " , " + COUNTRY_CODE_COL
                + " , " + DONOR_CODE_COL
                + " , " + AWARD_CODE_COL
                + " , " + PROGRAM_CODE_COL
                + " , " + DT_ENU_ID_COL
                + "  , " + DT_R_SEQ_COL
                + "  , " + DT_SURVEY_NUM
                + "  , " + OP_MONTH_CODE_COL
                + " FROM " + DT_SURVEY_TABLE
                + " WHERE " + COMPLETENESS_COL + " != 'Y' "
                + " OR " + COMPLETENESS_COL + " is null "
                + " GROUP BY " + DT_R_SEQ_COL;

        Cursor cursor = db.rawQuery(sql_srv, null);

        if (cursor != null && cursor.moveToFirst()) {

            do {
                DTResponseTableDataModel dtResponse = new DTResponseTableDataModel();
                dtResponse.setDtBasic(cursor.getString(0));
                dtResponse.setCountryCode(cursor.getString(1));
                dtResponse.setDonorCode(cursor.getString(2));
                dtResponse.setAwardCode(cursor.getString(3));
                dtResponse.setProgramCode(cursor.getString(4));
                dtResponse.setDtEnuId(cursor.getString(5));
                dtResponse.setDtrSeq(String.valueOf(cursor.getInt(6)));
                dtResponse.setSrvNumber(cursor.getInt(7));
                dtResponse.setOpMonthCode(cursor.getString(8));

                list.add(dtResponse);
            } while (cursor.moveToNext());

            cursor.close();
        }

        for (DTResponseTableDataModel deleteResponse : list) {


            deleteFromDTSurveyTable(deleteResponse.getDtBasic(), deleteResponse.getCountryCode()
                    , deleteResponse.getDonorCode(), deleteResponse.getAwardCode(), deleteResponse.getProgramCode()
                    , deleteResponse.getDtEnuId(), Integer.parseInt(deleteResponse.getDtrSeq()), "5"
                    , deleteResponse.getOpMonthCode(), deleteResponse.getSrvNumber());


            // don't delete the logcat
            Log.e(TAG, " DtBasic: " + deleteResponse.getDtBasic()
                    + " CountryCode :" + deleteResponse.getCountryCode()
                    + " DonorCode :" + deleteResponse.getDonorCode()
                    + " AwardCode :" + deleteResponse.getAwardCode()
                    + " ProgramCode :" + deleteResponse.getProgramCode()
                    + " DtEnuId :" + deleteResponse.getDtEnuId()
                    + " DtrSeq: " + Integer.parseInt(deleteResponse.getDtrSeq())
                    + " Opmode :" + "5"
                    + " OpMonthCode : " + deleteResponse.getOpMonthCode()
                    + " SrvNumber :" + deleteResponse.getSrvNumber());


        }


    }


    public void addIntoDTSurveyTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                     String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                     String progActivityCode, String dttTimeString, String opMode,
                                     String opMonthCode, String dataType, String dtqText,
                                     int surveyNumber, String image
            , String resposeController, String qResLupText, String dtaLabel, long uploadSyntextID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DONOR_CODE_COL, donorCode);

        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, programCode);
        values.put(DT_ENU_ID_COL, dtEnuId);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(DTA_CODE_COL, dtaCode);
        values.put(DT_R_SEQ_COL, dtrSeq);
        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DTQ_TEXT_COL, dtqText);
        values.put(DT_SURVEY_NUM, surveyNumber);
        values.put(DATA_TYPE_COL, dataType);
        values.put(U_FILE_COL, image);
        values.put(RESPONSE_VALUE_CONTROL_COL, resposeController);
        values.put(QRES_LUP_TEXT_COL, qResLupText);
        values.put(DTA_LABEL_COL, dtaLabel);
        values.put(ID_COL, uploadSyntextID);


        db.insert(DT_SURVEY_TABLE, null, values);
        db.close();
//        Log.d("DT_survey", " row: " + row);
    }

    public void updateIntoDTSurveyTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                        String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                        String progActivityCode, String dttTimeString, String opMode, String opMonthCode, String dataType, String dtqText, int surveyNumber) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq +
                " AND " + DT_SURVEY_NUM + " = " + surveyNumber;

        ContentValues values = new ContentValues();

        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DTQ_TEXT_COL, dtqText);
        values.put(DATA_TYPE_COL, dataType);


        int id = db.update(DT_SURVEY_TABLE, values, where, null);

        db.close();
    }

    /**
     * @since data craft
     */

    public void deleteFromDTSurveyTable(String DTBasic, String AdmCountryCode, String AdmDonorCode, String AdmAwardCode, String AdmProgCode, String DTEnuID, int DTRSeq, String OpMode, String OpMonthCode, int surveyNum) {
        SQLServerSyntaxGenerator syntaxGenerator = new SQLServerSyntaxGenerator();

        syntaxGenerator.setDTBasic(DTBasic);
        syntaxGenerator.setAdmCountryCode(AdmCountryCode);
        syntaxGenerator.setAdmDonorCode(AdmDonorCode);
        syntaxGenerator.setAdmAwardCode(AdmAwardCode);
        syntaxGenerator.setAdmProgCode(AdmProgCode);
        syntaxGenerator.setDTEnuID(DTEnuID);
        syntaxGenerator.setOpMode(OpMode);
        syntaxGenerator.setOpMonthCode(OpMonthCode);
        syntaxGenerator.setDTRSeq(String.valueOf(DTRSeq));

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + DTBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + AdmDonorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + AdmAwardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + AdmProgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + DTEnuID + "' " +
                " AND " + DT_R_SEQ_COL + " = " + DTRSeq +
                " AND " + OP_MODE_COL + " = '" + OpMode + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + OpMonthCode + "' ";
        String where2 = DT_BASIC_COL + " = '" + DTBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + AdmDonorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + AdmAwardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + AdmProgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + DTEnuID + "' " +
                " AND " + DT_R_SEQ_COL + " = " + DTRSeq +
                " AND " + OP_MODE_COL + " = '" + OpMode + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + OpMonthCode + "' " +
                " AND " + DT_SURVEY_NUM + " = " + surveyNum;

        db.delete(DT_RESPONSE_TABLE, where, null);
        db.delete(DT_SURVEY_TABLE, where2, null);


        db.close();
        /**
         * insert into uploadTable Syntax
         */

        insertIntoUploadTable(syntaxGenerator.deleteFromDTResponseTable(false));
    }

    /**
     * this method check either data exits or not
     *
     * @param dtBasic     - dynamic table  basic code
     * @param countryCode - country code
     * @param donorCode   - donor Code
     * @param awardCode   - award code
     * @param programCode - program code
     * @param dtEnuId     - staff id or entry by code
     * @param dtqCode     - dynamic table question
     * @param dtaCode     - dynamic table
     * @return either data exist or not
     */
    public boolean isDataExitsInDTAResponse_Table(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                  String dtEnuId, String dtqCode, String dtaCode, int dtRSeq) {

        boolean exitsStatus = false;
        DTResponseTableDataModel mDta = getDTResponseTableData(dtBasic, countryCode, donorCode, awardCode, programCode, dtEnuId, dtqCode, dtaCode, dtRSeq);

        if (mDta != null && mDta.getDtaValue() != null)
            exitsStatus = true;
        else
            exitsStatus = false;

        return exitsStatus;
    }

    /**
     * delete data from DTResponseTable for unfinished data entry
     */

    public void deleteFromDTResponseTable(String DTBasic, String AdmCountryCode, String AdmDonorCode, String AdmAwardCode, String AdmProgCode, String DTEnuID, int DTRSeq, String OpMode, String OpMonthCode) {
        SQLServerSyntaxGenerator syntaxGenerator = new SQLServerSyntaxGenerator();

        syntaxGenerator.setDTBasic(DTBasic);
        syntaxGenerator.setAdmCountryCode(AdmCountryCode);
        syntaxGenerator.setAdmDonorCode(AdmDonorCode);
        syntaxGenerator.setAdmAwardCode(AdmAwardCode);
        syntaxGenerator.setAdmProgCode(AdmProgCode);
        syntaxGenerator.setDTEnuID(DTEnuID);
        syntaxGenerator.setOpMode(OpMode);
        syntaxGenerator.setOpMonthCode(OpMonthCode);
        syntaxGenerator.setDTRSeq(String.valueOf(DTRSeq));

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + DTBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + AdmDonorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + AdmAwardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + AdmProgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + DTEnuID + "' " +
                " AND " + DT_R_SEQ_COL + " = " + DTRSeq +
                " AND " + OP_MODE_COL + " = '" + OpMode + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + OpMonthCode + "' ";

        String where2 = DT_BASIC_COL + " = '" + DTBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + AdmDonorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + AdmAwardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + AdmProgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + DTEnuID + "' " +
                " AND " + DT_R_SEQ_COL + " = " + DTRSeq +
                " AND " + OP_MODE_COL + " = '" + OpMode + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + OpMonthCode + "' ";

        String sql = "SELECT " + ID_COL + " FROM " + DT_SURVEY_TABLE +
                " where " + where2;
        int id = -1;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                id = cursor.getInt(cursor.getColumnIndex(ID_COL));
                db.delete(UPLOAD_SYNTAX_TABLE, ID_COL + " = " + id, null);
            } while (cursor.moveToNext());
            cursor.close();
        }

        /**
         * delete from response table
         */
        db.delete(DT_RESPONSE_TABLE, where, null);
        /**
         * delete from Dt survey table
         */
        db.delete(DT_SURVEY_TABLE, where2, null);

        db.close();
        /**
         * insert into uploadTable Syntax
         */

        insertIntoUploadTable(syntaxGenerator.deleteFromDTResponseTable(true));
        insertIntoUploadTable(syntaxGenerator.deleteFromDTResponseTable(true));
    }


    /**
     * @param dtBasic     - dynamic table  basic code
     * @param countryCode - country code
     * @param donorCode   - donor Code
     * @param awardCode   - award code
     * @param programCode - program code
     * @param dtEnuId     - staff id or entry by code
     * @param dtqCode     - dynamic table question
     * @param dtaCode     - dynamic table
     * @param dtrSeq      - dynamic Response Sequence
     * @return DTResponse Object only need {@link DTResponseTableDataModel#getDtaValue()}
     */

    public DTResponseTableDataModel getDTResponseTableData(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                           String dtEnuId, String dtqCode, String dtaCode, int dtrSeq) {
        SQLiteDatabase db = this.getReadableDatabase();
        DTResponseTableDataModel dtResponse = null;
        String sql = "SELECT * FROM " + DT_RESPONSE_TABLE + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                dtResponse = new DTResponseTableDataModel();

                dtResponse.setDtaValue(cursor.getString(9));
                dtResponse.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                dtResponse.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
                dtResponse.setDtaValue(cursor.getString(cursor.getColumnIndex(DTA_VALUE_COL)));
               /* dtResponse.setCountryCode(cursor.getString(1));
                dtResponse.setDonorCode(cursor.getString(2));
                dtResponse.setAwardCode(cursor.getString(3));
                dtResponse.setProgramCode(cursor.getString(4));
                dtResponse.setDtEnuId(cursor.getString(5));
               */


            }
            cursor.close();
            db.close();
        }
        return dtResponse;
    }


    public int getDTResponseCount(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                  String dtEnuId, int dtrSeq, String opMothCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        int responseCount = 0;
        String sql = "SELECT *  FROM " + DT_RESPONSE_TABLE + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + opMothCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                responseCount = cursor.getCount();

            }
            cursor.close();
            db.close();
        }
        return responseCount;
    }


    public DTResponseTableDataModel getRadioDTResponseTableData(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                                String dtEnuId, String dtqCode, int dtrSeq) {
        SQLiteDatabase db = this.getReadableDatabase();
        DTResponseTableDataModel dtResponse = null;
        String sql = "SELECT * FROM " + DT_RESPONSE_TABLE + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                dtResponse = new DTResponseTableDataModel();
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
                dtResponse.setDtaValue(cursor.getString(9));
                dtResponse.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                dtResponse.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));


            }
            cursor.close();
            db.close();
        }
        return dtResponse;
    }

    public List<DTResponseTableDataModel> getCheckBoxesDTResponseTableData(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                                           String dtEnuId, String dtqCode, int dtrSeq) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<DTResponseTableDataModel> list = new ArrayList<DTResponseTableDataModel>();

        String sql = "SELECT " +
                "  dtRes." + DTA_CODE_COL +
                " , dtRes." + DTA_VALUE_COL +
                " , dtRes." + DT_BASIC_COL +
                " , dtRes." + DTQ_CODE_COL +

                " , dtan." + DTA_LABEL_COL +
                " FROM " + DT_RESPONSE_TABLE + " AS dtRes " +

                " left join " + DT_A_TABLE + " AS dtan ON " +
                " dtRes." + DT_BASIC_COL + " =  dtan." + DT_BASIC_COL +
                " AND  dtRes." + DTQ_CODE_COL + " =  dtan." + DTQ_CODE_COL +
                " AND  dtRes." + DTA_CODE_COL + " =  dtan." + DTA_CODE_COL +
                " AND  dtRes." + DTA_VALUE_COL + " =  dtan." + DTA_VALUE_COL +

                " WHERE dtRes." + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND dtRes." + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND dtRes." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND dtRes." + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND dtRes." + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND dtRes." + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND dtRes." + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND dtRes." + DT_R_SEQ_COL + " = " + dtrSeq;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                DTResponseTableDataModel dtResponse = new DTResponseTableDataModel();
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));

                dtResponse.setDtaValue(cursor.getString(cursor.getColumnIndex(DTA_VALUE_COL)));
                dtResponse.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                dtResponse.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
                dtResponse.setDtALabel(cursor.getString(cursor.getColumnIndex(DTA_LABEL_COL)));


                list.add(dtResponse);

            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return list;
    }

    public String getDTSkipDTQCde(String dtBasicCode, String dtQCode, String dtaCodeComB) {
        String dtSkipDTQCode = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + DTSKIP_DTQ_CODE_COL + " FROM " + DTA_SKIP_TABLE + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasicCode + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtQCode + "' " +
                " AND " + DTA_CODE_COMB_N_COL + " = '" + dtaCodeComB + "' ";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            dtSkipDTQCode = cursor.getString(cursor.getColumnIndex(DTSKIP_DTQ_CODE_COL));

            cursor.close();
        }

        db.close();
        return dtSkipDTQCode;
    }


    public ArrayList<DTSurveyTableDataModel> dtSurveyTableDataModels(int surveyNum, String dtBasic, String cCode, String donorCode, String awardCode, String prgCode, String entryBy) {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<DTSurveyTableDataModel> dtSurveyTableDataList = new ArrayList<>();

        String sql = SQLiteQuery.dtSurveyTableDataModels_sql(surveyNum, dtBasic);

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int count = 0;
                do {
                    DTSurveyTableDataModel dtSurveyTableData = new DTSurveyTableDataModel();

                    dtSurveyTableData.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                    dtSurveyTableData.setCountryCode(cursor.getString(cursor.getColumnIndex("CountryCode")));
                    dtSurveyTableData.setDonorCode(cursor.getString(cursor.getColumnIndex("DonorCode")));
                    dtSurveyTableData.setAwardCode(cursor.getString(cursor.getColumnIndex("AwardCode")));
                    dtSurveyTableData.setProgramCode(cursor.getString(cursor.getColumnIndex("ProgramCode")));
                    dtSurveyTableData.setDtEnuId(cursor.getString(cursor.getColumnIndex(DT_ENU_ID_COL)));
                    dtSurveyTableData.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                    dtSurveyTableData.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
                    dtSurveyTableData.setDtrSeq(cursor.getInt(cursor.getColumnIndex(DT_R_SEQ_COL)));
                    dtSurveyTableData.setDtaValue(cursor.getString(cursor.getColumnIndex(DTA_VALUE_COL)));
                    dtSurveyTableData.setProgActivityCode(cursor.getString(cursor.getColumnIndex(PROG_ACTIVITY_CODE_COL)));
                    dtSurveyTableData.setDttTimeString(cursor.getString(cursor.getColumnIndex(DTTIME_STRING_COL)));
                    dtSurveyTableData.setOpMode(cursor.getString(cursor.getColumnIndex(OP_MODE_COL)));
                    dtSurveyTableData.setOpMonthCode(cursor.getString(cursor.getColumnIndex(OP_MONTH_CODE_COL)));
                    dtSurveyTableData.setDataType(cursor.getString(cursor.getColumnIndex(DATA_TYPE_COL)));
                    dtSurveyTableData.setDtqText(cursor.getString(cursor.getColumnIndex(DTQ_TEXT_COL)));
                    dtSurveyTableData.setDtSurveyNumber(cursor.getInt(cursor.getColumnIndex(DT_SURVEY_NUM)));

                    dtSurveyTableData.setDtPhoto(cursor.getString(cursor.getColumnIndex(U_FILE_COL)));
                    dtSurveyTableData.setDtResController(cursor.getString(cursor.getColumnIndex(RESPONSE_VALUE_CONTROL_COL)));

                    dtSurveyTableData.setDtQResLupText(cursor.getString(cursor.getColumnIndex(QRES_LUP_TEXT_COL)));
                    dtSurveyTableData.setDtALabel(cursor.getString(cursor.getColumnIndex(DTA_LABEL_COL)));

                    /***
                     *  add to the list
                     */
                    dtSurveyTableDataList.add(dtSurveyTableData);
                    count++;
                } while (cursor.moveToNext());
            }
//            Log.e("TEST_GEO", test);
            cursor.close();
            db.close();
        }
        return dtSurveyTableDataList;
    }


    public void addIntoDTTableDefinition(String tableName, String fieldName, String fieldDefinition, String fieldShortName,
                                         String valueUdf, String lupTableName, String adminOnly, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE_NAME_COL, tableName);
        values.put(FIELD_NAME_COL, fieldName);
        values.put(FIELD_DEFINITION_COL, fieldDefinition);
        values.put(FIELD_SHORT_NAME_COL, fieldShortName);
        values.put(VALUE_UDF_COL, valueUdf);
        values.put(LUPTABLE_NAME_COL, lupTableName);
        values.put(ADMIN_ONLY_COL, adminOnly);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long id = db.insert(DT_TABLE_DEFINITION_TABLE, null, values);
        db.close();
    }

    public void addIntoDTTableListCategory(String tableName, String tableGroupCode, String useAdminOnly, String useReport,
                                           String useTransection, String useLup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE_NAME_COL, tableName);
        values.put(TABLE_GROUP_CODE_COL, tableGroupCode);
        values.put(USE_ADMIN_ONLY_COL, useAdminOnly);
        values.put(USE_REPORT_COL, useReport);
        values.put(USE_TRANSACTION_COL, useTransection);
        values.put(USE_LUP_COL, useLup);

        db.insert(DTTABLE_LIST_CATEGORY_TABLE, null, values);
        db.close();
    }


    public List<TemOpMonth> getOpMonthList(String cCode) {
        SQLiteDatabase db = this.getReadableDatabase();


        List<TemOpMonth> list = new ArrayList<TemOpMonth>();

        String sql = "SELECT " + OP_MONTH_CODE_COL
                + ", " + MONTH_LABEL_COL
                + " FROM " + TEMPORARY_OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '2' "
                + " AND " + STATUS + " = 'A' ";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {


                TemOpMonth opData = new TemOpMonth();
                opData.setOpMonthCode(cursor.getString(cursor.getColumnIndex(OP_MONTH_CODE_COL)));
                opData.setOpMonthLable(cursor.getString(cursor.getColumnIndex(MONTH_LABEL_COL)));


                list.add(opData);


            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return list;

    }


    public boolean isMultipleCountryAccessUser() {
        Cursor cursor;
        String count = "0";
        SQLiteDatabase db = this.getReadableDatabase();

        String query = " SELECT * FROM " + SELECTED_COUNTRY_TABLE;
//        Log.d(TAG, query);
        cursor = db.rawQuery(query, null);

//        Log.d(TAG, " " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

}
