package com.faisal.technodhaka.dlight.database;

import com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity;

import com.faisal.technodhaka.dlight.data_model.DTSurveyTableDataModel;
import com.faisal.technodhaka.dlight.data_model.DynamicDataIndexDataModel;
import com.faisal.technodhaka.dlight.data_model.adapters.AssignDataModel;
import com.faisal.technodhaka.dlight.utils.UtilClass;


import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.COMMUNITY_GROUP;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.COMMUNITY_GROUP_IG;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.COMMUNITY_GROUP_LG;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.COMMUNITY_GROUP_MG;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.COMMUNITY_GROUP_PG;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.COMMUNITY_GROUP_WE;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.DISTRIBUTION_POINT;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.GEO_LAYER_1;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.GEO_LAYER_2;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.GEO_LAYER_3;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.GEO_LAYER_4;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.GEO_LAYER_ADDRESS;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.LOOKUP_LIST;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.ORGANIZATION_LIST;
import static com.faisal.technodhaka.dlight.activity.sub.DTResponseRecordingActivity.SERVICE_SITE;
import static com.faisal.technodhaka.dlight.database.SQLiteHandler.*;

/**
 * @author FAISAL MOHAMMAD on 1/17/2016.
 *         <p>
 *         This class provided  all the local queries
 *         </p>
 */
public class SQLiteQuery {

    private static final String YES = "Y";
    private static final String TAG = SQLiteQuery.class.getSimpleName();
    public static final int NO_LIMIT = -5;

    public static String getUpzillaJoinQuery(String countryCode, String layR1Code) {
        return " JOIN " + SELECTED_VILLAGE_TABLE +
                " ON " + GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".CountryCode " +
                " AND " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE + " = " + SELECTED_VILLAGE_TABLE + ".DistrictCode " +
                " AND " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UpazillaCode " +
                " WHERE " + GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + countryCode + "' " +
                " AND " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE + "= '" + layR1Code + "' " +
                " GROUP BY " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL +
                ", " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_NAME_COL;
    }

    public static String getLayR3List_sql(String countryCode, String layR1Code, String layR2Code) {
        return " JOIN " + SELECTED_VILLAGE_TABLE +
                " ON " + GEO_LAY_R3_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".CountryCode " +
                " AND " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R1_LIST_CODE + " = " + SELECTED_VILLAGE_TABLE + ".DistrictCode " +
                " AND " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UpazillaCode " +
                " AND " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UnitCode " +
                " WHERE " + GEO_LAY_R3_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + countryCode + "' AND "
                + GEO_LAY_R3_LIST_TABLE + "." + LAY_R1_LIST_CODE + "='" + layR1Code + "' AND " +
                GEO_LAY_R3_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + "='" + layR2Code + "' " +
                " GROUP BY " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + ", " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_NAME;
    }

    public static String getVillageJoinQuery(String countryCode, String layR1Code, String layR2Code, String layR3Code) {
        return " JOIN " + SELECTED_VILLAGE_TABLE +
                " ON " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".CountryCode " +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R1_LIST_CODE + " = " + SELECTED_VILLAGE_TABLE + ".DistrictCode " +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UpazillaCode " +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UnitCode " +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".VillageCode " +

                " WHERE " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + countryCode + "' AND "
                + GEO_LAY_R4_LIST_TABLE + "." + LAY_R1_LIST_CODE + "='" + layR1Code + "' AND " +
                GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + "='" + layR2Code + "' AND " +
                GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + "='" + layR3Code + "' " +
                " GROUP BY " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL + ", " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL;
    }

    public static String getDistrictJoinQuery(String countryCode) {
        return " JOIN " + SELECTED_VILLAGE_TABLE +
                " ON " + GEO_LAY_R1_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".CountryCode " +
                " AND " + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".DistrictCode " +
                " WHERE " + GEO_LAY_R1_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + countryCode + "' GROUP BY " + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL + ", " + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + SQLiteHandler.LAY_R_LIST_NAME_COL;

    }


    public static String getLUP_TAParticipantCategories_sql(String cCode, String taGroup) {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + " , " + TA_GROUP_COL
                + " , " + PART_CAT_CODE_COL
                + " , " + PART_CAT_TITLE_COL
                + " FROM " + LUP_TA_PATICIPANT_CAT_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + TA_GROUP_COL + " = '" + taGroup + "' ";
    }


    public static String editTaParticipantsListTable_sql(String cCode, String eventCode, String partId, String atdnDate) {
        return ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + EVENT_CODE_COL + " = '" + eventCode + "'" +
                " AND " + PART_ID_COL + " = '" + partId + "' " +
                " AND " + ATDN_DATE_COL + " = '" + atdnDate + "' ";
    }


    public static String getSavePermissionSelectQuery(String tableName, String columnName, String country, String district, String upzella, String unit, String village) {
        return " SELECT " + columnName +
                // " SELECT "+SQLiteHandler.BTN_SAVE_COL+
                //" FROM "+SQLiteHandler.STAFF_GEO_INFO_ACCESS_TABLE+
                " FROM " + tableName +
                " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + country + "' " +
                " AND " + LAY_R_LIST_CODE_COL + " = '" + district + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzella + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unit + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + village + "' ";
    }


    private static String dbo_Get_OpMonthEndDate(String cCode, String donorCode, String awardCode, String opCode, String opMonthCode) {
        return " Select date(substr(" + END_DATE_COL + ",1,4)||'-'|| substr(" + END_DATE_COL + ",6,2)||'-'|| substr(" + END_DATE_COL + ",9,2))" +
                "from " + OP_MONTH_TABLE + " where "
                + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + "= '" + donorCode + "'" +
                " AND " + ADM_AWARD_CODE_COL + "= '" + awardCode + "' " +
                " AND " + OPERATION_CODE_COL + "='" + opCode + "'" +
                " AND " + OP_MONTH_CODE_COL + "='" + opMonthCode + "'";
    }


    private static String dbo_Get_OpMonthStartDate(String cCode, String donorCode, String awardCode, String opCode, String opMonthCode) {
        return " Select date(substr(" + START_DATE_COL + ",1,4)||'-'|| substr(" + START_DATE_COL + ",6,2)||'-'|| substr(" + START_DATE_COL + ",9,2))" +
                "from " + OP_MONTH_TABLE + " where "
                + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + "= '" + donorCode + "'" +
                " AND " + ADM_AWARD_CODE_COL + "= '" + awardCode + "' " +
                " AND " + OPERATION_CODE_COL + "='" + opCode + "'" +
                " AND " + OP_MONTH_CODE_COL + "='" + opMonthCode + "'";
    }


    public static String dbo_Get_dayDifference(String cCode, String donorCode, String awardCode,
                                               String opMonthCode, String tableName,
                                               String columnName, boolean syncMode) {
        String tem = "";
        if (syncMode) {
            tem = "CAST ( ( (" +
                    "SELECT julianday(date(substr(" + END_DATE_COL + ", 1, 4) || '-' || substr(" + END_DATE_COL + ", 6, 2) || '-' || substr(" + END_DATE_COL + ", 9, 2) ) ) AS d " +
                    "        FROM " + OP_MONTH_TABLE +
                    "        WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                    " AND " + "" + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                    " AND " + "" + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                    " AND " + "" + OPERATION_CODE_COL + " = '2' " +
                    " AND " + "" + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'" +
                    "  )" +
                    "   - (" +
                    "    SELECT julianday(date(substr(" + columnName + ", 7, 4) || '-' || substr(" + columnName + ", 1, 2) || '-' || substr(" + columnName + ", 4, 2) ) ) AS dd " +
                    "  FROM " + tableName + " " +
                    "  WHERE " + ADM_COUNTRY_CODE_COL + " = regAss." + ADM_COUNTRY_CODE_COL + " AND" +
                    " " + LAY_R1_LIST_CODE_COL + " = regAss." + LAY_R1_LIST_CODE_COL + " AND " +
                    " " + LAY_R2_LIST_CODE_COL + " = regAss." + LAY_R2_LIST_CODE_COL + " AND " +
                    " " + LAY_R3_LIST_CODE_COL + " = regAss." + LAY_R3_LIST_CODE_COL + " AND" +
                    " " + LAY_R4_LIST_CODE_COL + " = regAss." + LAY_R4_LIST_CODE_COL + " AND" +
                    " " + HHID_COL + " = regAss." + HHID_COL + " AND" +
                    " " + MEM_ID_COL + " = regAss." + MEM_ID_COL + "" +
                    "  )" +
                    "  ) AS INTEGER) AS daydiffernce";
        } else {
            tem = "CAST ( ( (" +
                    "SELECT julianday(date(substr(" + END_DATE_COL + ", 1, 4) || '-' || substr(" + END_DATE_COL + ", 6, 2) || '-' || substr(" + END_DATE_COL + ", 9, 2) ) ) AS d " +
                    "        FROM " + OP_MONTH_TABLE +
                    "        WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                    " AND " + "" + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                    " AND " + "" + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                    " AND " + "" + OPERATION_CODE_COL + " = '2' " +
                    " AND " + "" + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'" +
                    "  )" +
                    "   - (" +
                    "    SELECT julianday(date(substr(" + columnName + ", 1, 4) || '-' || substr(" + columnName + ", 6, 2) || '-' || substr(" + columnName + ", 9, 2) ) ) AS dd " +
                    "  FROM " + tableName + " " +
                    "  WHERE " + ADM_COUNTRY_CODE_COL + " = regAss." + ADM_COUNTRY_CODE_COL + " AND" +
                    " " + LAY_R1_LIST_CODE_COL + " = regAss." + LAY_R1_LIST_CODE_COL + " AND " +
                    " " + LAY_R2_LIST_CODE_COL + " = regAss." + LAY_R2_LIST_CODE_COL + " AND " +
                    " " + LAY_R3_LIST_CODE_COL + " = regAss." + LAY_R3_LIST_CODE_COL + " AND" +
                    " " + LAY_R4_LIST_CODE_COL + " = regAss." + LAY_R4_LIST_CODE_COL + " AND" +
                    " " + HHID_COL + " = regAss." + HHID_COL + " AND" +
                    " " + MEM_ID_COL + " = regAss." + MEM_ID_COL + "" +
                    "  )" +
                    "  ) AS INTEGER) AS daydiffernce";
        }
        return tem;
    }


    public static String getAddressQuery(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode) {
        return " WHERE "
                + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' AND "
                + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' AND "
                + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' AND "
                + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' AND "
                + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'";

        /**
         * " SELECT "+SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL+" , "+ SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL
         +" FROM "+SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
         */
    }


    public static String getVillageNameWHERECondition(String countryCode, String districtCode, String upazillaCode, String unitCode, String villageCode) {
        return " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' AND "
                + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' AND "
                + LAY_R2_LIST_CODE_COL + " = '" + upazillaCode + "' AND "
                + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' AND "
                + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'  ";
    }

    public static String getProgramsNames_WHERE_Condition(String awardCode, String donorCode) {
        return " WHERE " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'";

    }

    /**
     * getDistProgramsNames_WHERE_Condition() is For Distribution table
     */
    public static String getDistProgramsNames_WHERE_Condition(String awardCode, String donorCode, String columnName) {
        return " WHERE " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + columnName + "='" + 1 + "'";

    }

    public static String getServiceMonths_WHERE_Service_Open_Condition(String countryCode) {
        return " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + STATUS + " = '" + "A" + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' "
                + " ORDER BY OpMonthID   DESC   LIMIT 1 ";
    }

    public static String getServiceMonths_WHERE_Service_Close_Condition(String countryCode) {
        return " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + STATUS + " = '" + "C" + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' "
                + " ORDER BY OpMonthID   DESC   LIMIT 1 "
                ;


    }


    public static String getDistributionMonths_WHERE_Condition(String countryCode) {
        return " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + STATUS + " = '" + "A" + "' "
                + " AND " + OPERATION_CODE_COL + " = '3' "
                + " ORDER BY OpMonthID   DESC  LIMIT 1 "
                + "        ";
    }

    public static String getCriteriaNames_WHERE_Condition(String awardCode, String donorCode, String programCode) {
        return " WHERE " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + "='" + programCode + "'";

    }

    public static String getFDPNames_Where_Condition(String idCountry, String laryR2) {
        return " WHERE " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = '" + idCountry + "'"
                + " AND " + SQLiteHandler.FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R1_LIST_CODE_COL + " || " +
                SQLiteHandler.FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R2_LIST_CODE_COL + " = '" + laryR2 + "' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_NEW_COL + " = '1' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_SAVE_COL + " = '1' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_DEL_COL + " = '1' "
                + "  GROUP BY " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.FDP_NAME_COL
                + "  ORDER BY " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.FDP_NAME_COL;
    }


    public static String getGraduationDefaultActiveReason_Select_Query(String prrogramCode, String serviceCode) {
        return "SELECT " + SQLiteHandler.GRD_CODE_COL + " FROM " + SQLiteHandler.REG_N_LUP_GRADUATION_TABLE +
                " WHERE  " + ADM_PROG_CODE_COL + " = '" + prrogramCode + "'"
                + " AND " + ADM_SRV_CODE_COL + " = '" + serviceCode + "'"
                + " AND " + SQLiteHandler.DEFAULT_CAT_ACTIVE_COL + " = '" + YES + "'  ";
    }


// havet use it


    public static String get_RegNAssProgSrvRegistrationDateRangeSelectQuery(String cCode) {
        return "SELECT " + USA_START_DATE_COL + " , " + USA_END_DATE_COL + " FROM " + OP_MONTH_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + OPERATION_CODE_COL + " = '" + REGISTRATION_OP_CODE + "' " +
                " AND " + STATUS + " = '" + ACTIVE + "' ";

    }

/*    public static String getTotalListOfMemberRAssignedSummary_sql(String countryCode, String districtCode, String upzellaCode, String unionCode, String villageCode, String donorCode, String awardCord, String programCode, String srvCode) {


        String getMemberName;
        if (countryCode.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;
        return "SELECT " + "rgAss." + LAY_R_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R2_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R3_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R4_LIST_CODE_COL
                + " || '' || " + "rgAss." + HHID_COL
                + " || '' || " + "rgAss." + HH_MEM_ID + " AS NewID "
                + ", " + getMemberName + "  AS memberName"
                + " , " + "rgAss." + REG_N_DAT_COL + " AS regDate "
                // + " || '' || " + "memGrp." + GROUP_CODE_COL
                + " , memGrp." + GROUP_CODE_COL
                + " , CASE WHEN cg." + GROUP_NAME_COL+" IS NULL  THEN '' ELSE cg." + GROUP_NAME_COL+ " END AS grpName "
                + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS rgAss "
                + " INNER JOIN " + REGISTRATION_MEMBER_TABLE
                + " ON " + "rgAss." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + "rgAss." + LAY_R_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R_LIST_NAME_COL +
                " AND " + "rgAss." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_NAME_COL +
                " AND " + "rgAss." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_NAME +
                " AND " + "rgAss." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_NAME_COL +
                " AND " + "rgAss." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                " AND " + "rgAss." + HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " " +
                      //   " JOIN "+SERVICE_MASTER_TABLE+" ON "+REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_PROG_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_PROG_CODE_COL+
                 //" AND " +REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_SRV_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_SRV_CODE_COL+

                " LEFT JOIN " + REG_N_MEM_PROG_GRP_TABLE + " AS memGrp "
                + " ON  memGrp." + ADM_COUNTRY_CODE_COL + " = " + " rgAss." + ADM_COUNTRY_CODE_COL
                + " AND memGrp." + LAY_R_LIST_CODE_COL + " = " + " rgAss." + LAY_R_LIST_CODE_COL
                + " AND memGrp." + LAY_R2_LIST_CODE_COL + " = " + " rgAss." + LAY_R2_LIST_CODE_COL
                + " AND memGrp." + LAY_R3_LIST_CODE_COL + " = " + " rgAss." + LAY_R3_LIST_CODE_COL
                + " AND memGrp." + LAY_R4_LIST_CODE_COL + " = " + " rgAss." + LAY_R4_LIST_CODE_COL
                + " AND memGrp." + ADM_DONOR_CODE_COL + " = " + " rgAss." + ADM_DONOR_CODE_COL
                + " AND memGrp." + ADM_AWARD_CODE_COL + " = " + " rgAss." + ADM_AWARD_CODE_COL
                + " AND memGrp." + HHID_COL + " = " + " rgAss." + HHID_COL
                + " AND memGrp." + HH_MEM_ID + " = " + " rgAss." + HH_MEM_ID
                + " AND memGrp." + ADM_PROG_CODE_COL + " = " + " rgAss." + ADM_PROG_CODE_COL
                + " AND memGrp." + ADM_SRV_CODE_COL + " = " + " rgAss." + ADM_SRV_CODE_COL +

                " LEFT JOIN " + COMMUNITY_GROUP_TABLE + " AS cg "
                + " ON  cg." + ADM_COUNTRY_CODE_COL + " = " + " rgAss." + ADM_COUNTRY_CODE_COL
                + " AND cg." + ADM_AWARD_CODE_COL + " = " + " rgAss." + ADM_AWARD_CODE_COL
                + " AND cg." + ADM_DONOR_CODE_COL + " = " + " rgAss." + ADM_DONOR_CODE_COL
                + " AND cg." + ADM_PROG_CODE_COL + " = " + " rgAss." + ADM_PROG_CODE_COL
                + " AND cg." + GROUP_CODE_COL + " = " + " memGrp." + GROUP_CODE_COL+

            //    + " AND cg." + LAY_R1_CODE_COL + " = " + " memGrp." + LAY_R1_CODE_COL
              //  + " AND cg." + GRP_LAY_R2_LIST_CODE_COL + " = " + " memGrp." + GRP_LAY_R2_LIST_CODE_COL
            //    + " AND cg." + GRP_LAY_R3_LIST_CODE_COL + " = " + " memGrp." + GRP_LAY_R3_LIST_CODE_COL +


                " WHERE " + "rgAss." + ADM_COUNTRY_CODE_COL + "= '" + countryCode + "'" +
                " AND " + "rgAss." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + "rgAss." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + "rgAss." + ADM_PROG_CODE_COL + " = '" + programCode + "'" +
                " AND " + "rgAss." + ADM_SRV_CODE_COL + " = '" + srvCode + "'" +
                " AND " + "rgAss." + LAY_R_LIST_CODE_COL + " = '" + districtCode + "'" +
                " AND " + "rgAss." + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "'" +
                " AND " + "rgAss." + LAY_R3_LIST_CODE_COL + " = '" + unionCode + "'" +
                " AND " + "rgAss." + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'" +


                //  +
                " GROUP BY NewID" +//+ REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+
                " ORDER BY " + "rgAss." + HHID_COL +
                " , " + "rgAss." + HH_MEM_ID + "  ASC ";

        *//*return "SELECT " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HH_MEM_ID + " AS NewID "
                + ", " + getMemberName + "  AS memberName"
                + " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_DAT_COL + " AS regDate"
                + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE
                + " ON " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R_LIST_NAME_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_NAME_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_NAME +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_NAME_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " " +
                      //   " JOIN "+SERVICE_MASTER_TABLE+" ON "+REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_PROG_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_PROG_CODE_COL+
                 //" AND " +REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_SRV_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_SRV_CODE_COL+
                " WHERE " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + countryCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_PROG_CODE_COL + " = '" + programCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_SRV_CODE_COL + " = '" + srvCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL + " = '" + districtCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = '" + unionCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'" +
                //  +
                " GROUP BY NewID" +//+ REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+
                " ORDER BY " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HH_MEM_ID + "  ASC ";*//*


    }*/


    public static String get_ProgramMultipleSrv_SelectQuery(String donorCode, String awardCode, String programCode) {

        return "SELECT " + MULTIPLE_SERVICE_FLAG_COL + " FROM " + ADM_PROGRAM_MASTER_TABLE
                + " WHERE " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' ";
    }

    public static String get_SrvCriteriaList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode, String distFlag) {
        return " SELECT " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria , " +
                SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS IdCriteria ,  " +
                "( SELECT COUNT (*) FROM " + SERVICE_TABLE +
                " WHERE " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL
                + " AND " + SERVICE_TABLE + "." + SRV_STATUS_COL + " = 'O'"

                + " ) AS Count " +

                " FROM " + SERVICE_TABLE + " JOIN " + SRV_CENTER_TABLE
                + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +

                " JOIN " + OP_MONTH_TABLE + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + " " +

                " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " AND " +

                SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL +

                " WHERE  " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'"
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SERVICE_TABLE + "." + PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + SERVICE_TABLE + "." + DIST_FLAG_COL + " = '" + distFlag + "' "
                + " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL;
    }


    public static String getSrvExtendedItemSummaryList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode) {


        return " SELECT " + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL

                + " || '-' || " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL
                + " ||' '||  " + VOUCHER_ITEM__MEAS_TABLE + "." + MEASE_TITLE_COL + " AS item , "
                + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " AS voucherID ,  " +
                " sum( " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_UNIT_COL + ") AS unitCount " +
                " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                "  INNER JOIN " + VOUCHER_ITEM_TABLE
                + " ON " + VOUCHER_ITEM_TABLE + "." + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8) " +
                "  INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + SERVICE_EXTENDED_TABLE
                + " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL


                + " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'"
                + " AND " + SERVICE_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL;

    }


    public static String getTotal_Service_Itemize_AttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode/*, String srvCode*/, String vouItSpec) {
        return " SELECT " + SERVICE_EXTENDED_TABLE + "." + LAY_R1_LIST_CODE_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + LAY_R2_LIST_CODE_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + LAY_R3_LIST_CODE_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + LAY_R4_LIST_CODE_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + HHID_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " AS NewID , "
                +
                /** HERE COUNT THE SERVICE */
                SERVICE_EXTENDED_TABLE + "." + VOUCHER_UNIT_COL + "  "
                + " ," + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + UNITE_COST_COL + " AS cost "
                + " FROM " + SERVICE_EXTENDED_TABLE +
                " JOIN " + SRV_CENTER_TABLE + " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " JOIN " + OP_MONTH_TABLE + " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + "  " +
                " JOIN " + SERVICE_MASTER_TABLE
                + " ON " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + "  " +
                " INNER JOIN " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL +
                " WHERE " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = '" + prgCode + "'" +

                " AND " + SERVICE_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = '" + vouItSpec + "'  " +
                " GROUP BY " + SERVICE_EXTENDED_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " , " + SERVICE_EXTENDED_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " , " + SERVICE_EXTENDED_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " , " + SERVICE_EXTENDED_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " , " + SERVICE_EXTENDED_TABLE + "." + HHID_COL
                + " , " + SERVICE_EXTENDED_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID;


    }


    public static String getServiceDelete_WhereCondition(String cCode, String donorCode, String awardCode, String distId, String upId, String unId, String villId, String hhId, String memId, String progCode, String srvCode, String opMonthCode, String slNo) {
        return ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + LAY_R1_LIST_CODE_COL + " = '" + distId + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upId + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unId + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + villId + "' " +
                " AND " + HHID_COL + " = '" + hhId + "' " +
                " AND " + MEM_ID_COL + " = '" + memId + "' " +
                " AND " + PROG_CODE_COL + " = '" + progCode + "' " +
                " AND " + SRV_CODE_COL + " = '" + srvCode + "' " +
                " AND " + OPERATION_CODE_COL + " = '2' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND " + SERVICE_TABLE_SERVICE_SL_COL + " = '" + slNo + "' ";


    }


    public static String getServiceDateRange_selectQuery(String cCode, String srvOpMonthCode) {
        return "SELECT  " + SQLiteHandler.OPERATION_CODE_COL
                + " , " + OP_MONTH_CODE_COL
                + " , " + START_DATE_COL
                + " , " + END_DATE_COL
                + " , " + MONTH_LABEL_COL
                + " FROM " + OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + STATUS + "= 'A'"
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2'"
                + " AND " + OP_MONTH_CODE_COL + " = '" + srvOpMonthCode + "'";
    }


    public static String checkAdmCountryProgramsVoucherFlag_sql(String cCode, String donorCode, String awardCode, String progCode) {
        return "SELECT " + VOUCHER_FLAG + " FROM " + ADM_COUNTRY_PROGRAM_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "'";
    }


    public static String checkAdmCountryProgramsNoneFoodFlag_sql(String cCode, String donorCode, String awardCode, String progCode, String srvCode) {
        return "SELECT " + SQLiteHandler.NON_FOOD_FLAG + " FROM " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "'"
                + " AND " + ADM_SRV_CODE_COL + " = '" + srvCode + "'"
                ;
    }

    public static String get_VOItmUnitMeas(String measRCode) {
        return " SELECT " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL
                + " FROM " + VOUCHER_ITEM__MEAS_TABLE
                + " WHERE " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = " + measRCode;

    }


    public static String getDistExtentedItemName(final String cCode, final String donorCode, final String awardCode, final String opMCode, final String programCode) {
        return " SELECT " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " AS voucherID "
                + " , " + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL
                + " || '-' || " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL
                + " ||' '||  " + VOUCHER_ITEM__MEAS_TABLE + "." + MEASE_TITLE_COL + " AS item "
                + " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE
                + "  INNER JOIN " + VOUCHER_ITEM_TABLE
                + " ON " + VOUCHER_ITEM_TABLE + "." + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8) "
                + "  INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + SERVICE_EXTENDED_TABLE
                + " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL +
                " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + SERVICE_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL;
    }


    public static String loadOrganization_sql(final String cCode, final String donorCode, final String awardCode) {
        return "SELECT progOR." + ORG_N_CODE_COL +
                " ,  pOrg." + ORGANIZATION_NAME + " " +
                "                                FROM " + PROGRAM_ORGANIZATION_ROLE_TABLE + " AS progOR "
                + "                               INNER JOIN " +
                "                                " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS pOrg " +
                "                               ON progOR." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + " = pOrg." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + "  " +
                "                                WHERE (progOR." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "')" +
                "                                AND (progOR." + ADM_DONOR_CODE_COL + " = '" + donorCode + "') " +
                "                                AND (progOR." + ADM_AWARD_CODE_COL + " = '" + awardCode + "') " +
                "                                AND (progOR." + IMP_Y_N_COL + " = 'Y')" +
                " GROUP BY progOR." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL +
                "                                ORDER BY pOrg." + ORGANIZATION_NAME;

    }

    public static String editMemberIn_RegNmemProgGroup_where_sql(String cCode, String donorCode, String awardCode, String disttCode, String upCode, String unCode, String vCode, String hhID, String memID
            , String progCode, String srvCode) {
        return ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + "= '" + donorCode + "'"
                + " AND " + ADM_AWARD_CODE_COL + "= '" + awardCode + "'"
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + "= '" + disttCode + "'"
                + " AND " + LAY_R2_LIST_CODE_COL + "= '" + upCode + "'"
                + " AND " + LAY_R3_LIST_CODE_COL + "= '" + unCode + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + "= '" + vCode + "'"
                + " AND " + HHID_COL + "= '" + hhID + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + "=  '" + memID + "'"
                + " AND " + PROG_CODE_COL + "=  '" + progCode + "'"
                + " AND " + SRV_CODE_COL + "=  '" + srvCode + "'";
    }

    public static String getCommunityGroupList_sql(final String cCode, final String donorCode, final String awardCode, final String progCode, final String groupName) {
        return "SELECT " +
                " cg." + GROUP_NAME_COL

                + " , cg." + GROUP_CODE_COL
                //  + " , cg." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " , un." + LAY_R3_LIST_NAME
                + " , un." + LAY_R3_LIST_CODE_COL
                + " , un." + LAY_R2_LIST_CODE_COL
                + " , un." + LAY_R1_LIST_CODE_COL

                + " , " + " cgc." + GROUP_CAT_CODE_COL
                + " , " + " cgc." + GROUP_CAT_NAME_COL
                + " , " + " cgc." + GROUP_CAT_SHORT_NAME_COL
                + " , " + " pm." + PROGRAM_SHORT_NAME_COL
                + " , " + " pm." + ADM_PROG_CODE_COL
                + " , " + " pm." + ADM_PROGRAM_MASTER_PROGRAM_NAME_COL
                + " , " + " don." + DONOR_NAME_COL + "|| '-' || awd." + AWARD_SHORT_NAME_COL + " AS awardName "
                + " , " + " cgc." + ADM_AWARD_CODE_COL

                + " , " + " grpDetail." + ORG_CODE_COL
                + " , " + " org." + ORGANIZATION_NAME
                + " , " + " grpDetail." + STAFF_CODE_COL
                + " , " + " staff." + STAFF_NAME_COL

                + " , " + " grpDetail." + LAND_SIZE_UNDER_IRRIGATION_COL
                + " , " + " grpDetail." + IRRIGATION_SYSTEM_USED_COL
                + " , " + " grpDetail." + FUND_SUPPORT_COL
                + " , " + " grpDetail." + ACTIVE_STATUS_COL
                + " , " + " grpDetail." + REP_NAME_COL
                + " , " + " grpDetail." + REP_PHONE_NUMBER_COL
                + " , " + " grpDetail." + FORMATION_DATE_COL
                + " , " + " grpDetail." + TYPE_OF_GROUP
                + " , " + " grpDetail." + STATUS
                + " , " + " grpDetail." + PROJECT_NO_COL
                + " , " + " grpDetail." + PROJECT_TITLE

                + " FROM " + COMMUNITY_GROUP_CATEGORY_TABLE + " AS cgc "

                + " INNER JOIN "
                + COMMUNITY_GROUP_TABLE + " AS cg "
                + " ON cgc." + ADM_COUNTRY_CODE_COL + " =     cg." + ADM_COUNTRY_CODE_COL
                + " AND cgc." + ADM_DONOR_CODE_COL + " =      cg." + ADM_DONOR_CODE_COL
                + " AND cgc." + ADM_AWARD_CODE_COL + " =      cg." + ADM_AWARD_CODE_COL
                + " AND cgc." + ADM_PROG_CODE_COL + " =    cg." + ADM_PROG_CODE_COL
                + " AND cgc." + GROUP_CAT_CODE_COL + " =  cg." + GROUP_CAT_CODE_COL

                + " INNER JOIN " +
                ADM_PROGRAM_MASTER_TABLE + " AS pm "
                + " ON cgc." + ADM_DONOR_CODE_COL + " = pm." + ADM_DONOR_CODE_COL
                + " AND cgc." + ADM_AWARD_CODE_COL + " = pm." + ADM_AWARD_CODE_COL
                + " AND cgc." + ADM_PROG_CODE_COL + " = pm." + ADM_PROG_CODE_COL
                + " LEFT JOIN " +
                GEO_LAY_R3_LIST_TABLE + " AS un"
                + " ON un." + ADM_COUNTRY_CODE_COL + " = cgc." + ADM_COUNTRY_CODE_COL
                + " AND un." + LAY_R1_LIST_CODE_COL + " = cg." + LAY_R1_CODE_COL
                + " AND un." + LAY_R2_LIST_CODE_COL + " = cg." + GRP_LAY_R2_LIST_CODE_COL
                + " AND un." + LAY_R3_LIST_CODE_COL + " = cg." + GRP_LAY_R3_LIST_CODE_COL
                + " INNER JOIN " +
                ADM_COUNTRY_AWARD_TABLE + " AS awd "
                + " ON awd." + ADM_COUNTRY_CODE_COL + " = cgc." + ADM_COUNTRY_CODE_COL
                + " AND awd." + ADM_DONOR_CODE_COL + " = cgc." + ADM_DONOR_CODE_COL
                + " AND awd." + ADM_AWARD_CODE_COL + " = cgc." + ADM_AWARD_CODE_COL

                + " INNER JOIN "
                + ADM_DONOR_TABLE + " AS don "
                + " ON " + "  don." + ADM_DONOR_CODE_COL + " = cgc." + ADM_DONOR_CODE_COL
                + " LEFT JOIN " + COMMUNITY_GRP_DETAIL_TABLE + " AS grpDetail "
                + " ON "
                + " grpDetail." + ADM_COUNTRY_CODE_COL + " = cgc." + ADM_COUNTRY_CODE_COL
                + " AND grpDetail." + ADM_DONOR_CODE_COL + " = cgc." + ADM_DONOR_CODE_COL
                + " AND grpDetail." + ADM_AWARD_CODE_COL + " = cgc." + ADM_AWARD_CODE_COL
                + " AND grpDetail." + ADM_PROG_CODE_COL + " = pm." + ADM_PROG_CODE_COL
                + " AND grpDetail." + GROUP_CODE_COL + " = cg." + GROUP_CODE_COL
                + " AND grpDetail." + LAY_R1_CODE_COL + " = cg." + LAY_R1_CODE_COL
                + " AND grpDetail." + GRP_LAY_R2_LIST_CODE_COL + " = cg." + GRP_LAY_R2_LIST_CODE_COL
                + " AND grpDetail." + GRP_LAY_R3_LIST_CODE_COL + " = cg." + GRP_LAY_R3_LIST_CODE_COL


                + " LEFT JOIN " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS org "
                + " ON org." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + " = grpDetail." + ORG_CODE_COL

                + " LEFT JOIN " + STAFF_MASTER_TABLE + " AS staff "
                + " ON staff." + STAFF_ID_COL + " = " + " grpDetail." + STAFF_CODE_COL
                + " AND staff." + STAFF_COUNTRY_CODE + " = " + " cgc." + ADM_COUNTRY_CODE_COL
                + " AND staff." + ORG_N_CODE_COL + " = " + " grpDetail." + ORG_CODE_COL

                + " INNER JOIN " + SELECTED_VILLAGE_TABLE + " AS selV "
                + " ON cg." + LAY_R1_CODE_COL + " = selV.DistrictCode "
                + " AND cg." + GRP_LAY_R2_LIST_CODE_COL + " = selV.UpazillaCode "
                + " AND cg." + GRP_LAY_R3_LIST_CODE_COL + " = selV.UnitCode "
                + "   WHERE cgc." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND cgc." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND cgc." + ADM_AWARD_CODE_COL + " ='" + awardCode + "' "
                + " AND cgc." + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND cg." + GROUP_NAME_COL + " LIKE '%" + groupName + "%' "
                + " GROUP BY "
                + " cg." + ADM_COUNTRY_CODE_COL
                + ", cg." + ADM_DONOR_CODE_COL
                + ", cg." + ADM_AWARD_CODE_COL
                + ", cg." + ADM_PROG_CODE_COL
                + ", cg." + GROUP_CAT_CODE_COL
                + ",  cg." + GROUP_CODE_COL
                + ", cg." + LAY_R1_CODE_COL
                + ", cg." + GRP_LAY_R2_LIST_CODE_COL
                + ", cg." + GRP_LAY_R3_LIST_CODE_COL;

    }


    public static String getServiceExtedVoucherSummaryDataList_sql(String cCode, String discode, String upCode, String unCode, String vCode, String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode) {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + "  , " + ADM_DONOR_CODE_COL + " , " + ADM_AWARD_CODE_COL
                + " , " + LAY_R1_LIST_CODE_COL
                + " , " + LAY_R2_LIST_CODE_COL
                + " , " + LAY_R3_LIST_CODE_COL
                + " , " + LAY_R4_LIST_CODE_COL
                + " , " + PROG_CODE_COL
                + " , " + SRV_CODE_COL
                + " , " + OP_MONTH_CODE_COL
                + " , " + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL + " AS ItemName "
                + " , " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL + " ||' '|| " + MEASE_TITLE_COL + " AS measerment " +
                " , " + VOUCHER_REFERENCE_NUMBER_COL
                + " , " + VOUCHER_UNIT_COL
                + " , " + VOUCHER_ITEM_SPEC_COL
                + " FROM  " + SERVICE_EXTENDED_TABLE
                + " INNER JOIN " + VOUCHER_ITEM_TABLE
                + " ON " + VOUCHER_ITEM_TABLE + "." + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = substr(" + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8)"
                + " INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = substr(" + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8,3)  "
                + " WHERE  " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND  " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND  " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND  " + LAY_R1_LIST_CODE_COL + " = '" + discode + "' " +
                " AND  " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND  " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND  " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND  " + PROG_CODE_COL + " = '" + programCode + "' " +
                " AND  " + SRV_CODE_COL + " = '" + serviceCode + "' " +
                " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND  " + LAY_R1_LIST_CODE_COL + " || " + LAY_R2_LIST_CODE_COL
                + " || " + LAY_R3_LIST_CODE_COL + " || " + LAY_R4_LIST_CODE_COL
                + " || " + HHID_COL + " || " + MEM_ID_COL + " = '" + memId + "' ";
    }

    /**
     * @param cCode             country
     * @param donorCode         donor
     * @param awardCode         award
     * @param foodFlagTypeQuery an and condition which will be dynamically setted
     * @return Them method only return a query that return program name and service name  concretely and  their code
     */
    public static String loadServiceRecodeCriteria(String cCode, String donorCode, String awardCode, String foodFlagTypeQuery) {
        return " SELECT "
                + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " ||  '' || " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId "
                + ", " + ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria"
                + " FROM " + ADM_COUNTRY_PROGRAM_TABLE
                + " INNER JOIN " + ADM_PROGRAM_MASTER_TABLE
                + " ON " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL
                + " INNER JOIN " + SERVICE_MASTER_TABLE + " ON "
                + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL

                + " WHERE " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + foodFlagTypeQuery
                + " ORDER BY Criteria ";
    }

    public static String layR4ListServicePage_sql(String cCode) {
        return "SELECT "
                + " geo4Table." + LAY_R1_LIST_CODE_COL +
                " || " + " geo4Table." + LAY_R2_LIST_CODE_COL +
                " || " + " geo4Table." + LAY_R3_LIST_CODE_COL +
                " || " + " geo4Table." + LAY_R4_LIST_CODE_COL

                + ", " + " geo4Table." + LAY_R4_LIST_NAME_COL
                + " FROM " + GEO_LAY_R4_LIST_TABLE + " AS geo4Table "
                + " INNER JOIN " + STAFF_GEO_INFO_ACCESS_TABLE + " AS geoAccess " +
                " ON " + " geo4Table." + ADM_COUNTRY_CODE_COL + " = " + "geoAccess." + ADM_COUNTRY_CODE_COL +
                " AND " + " geo4Table." + LAY_R1_LIST_CODE_COL +
                " || " + " geo4Table." + LAY_R2_LIST_CODE_COL +
                " || " + " geo4Table." + LAY_R3_LIST_CODE_COL +
                " || " + " geo4Table." + LAY_R4_LIST_CODE_COL + " = " + "geoAccess." + LAY_R_LIST_CODE_COL +
                " WHERE " + " geo4Table." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " GROUP BY " + " geo4Table." + LAY_R4_LIST_CODE_COL
                + ", " + " geo4Table." + LAY_R4_LIST_NAME_COL
                + " ORDER BY " + " geo4Table." + LAY_R4_LIST_NAME_COL;
    }

//    public static String getHouseHoldData_sql(String hhID, String c_code, String layR1Code, String layR2Code, String layR3Code, String layR4Code) {
//        return "SELECT "
//                + REG_N_HH_TABLE + "." + ID_COL + ", "
//                + REG_N_HH_TABLE + "." + REG_DATE_COL + ", "
//
//                + REG_N_HH_TABLE + "." + COUNTRY_CODE + ", "
//                + REG_N_HH_TABLE + "." + LAY_R_LIST_NAME_COL + " AS R_District, "
//                + REG_N_HH_TABLE + "." + LAY_R2_LIST_NAME_COL + " AS R_Upazilla, "
//                + REG_N_HH_TABLE + "." + LAY_R3_LIST_NAME + " AS R_Union, "
//                + REG_N_HH_TABLE + "." + LAY_R4_LIST_NAME_COL + " AS R_Village, "
//
//                + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_NAME + " AS country_name, "
//                + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_NAME_COL + ", "
//                + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_NAME_COL + ","
//                + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_NAME + ", "
//                + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL + ","
//
//                + REG_N_HH_TABLE + "." + PID_COL + ","
//                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + ","
//                + "(" + " CASE WHEN " + SEX_COL + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
//                + REG_N_HH_TABLE + "." + HH_SIZE + ","
//                + REG_N_HH_TABLE + "." + LATITUDE_COL + ","
//                + REG_N_HH_TABLE + "." + LONGITUDE_COL + ","
//                + REG_N_HH_TABLE + "." + AG_LAND + ","
//                + "(" + " CASE WHEN " + V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
//                + "(" + " CASE WHEN " + M_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS MStatus" + ","
//                + REG_N_HH_TABLE + "." + ENTRY_BY + ","
//                + REG_N_HH_TABLE + "." + ENTRY_DATE
//                + " FROM " + REG_N_HH_TABLE
//                + " LEFT JOIN " + GEO_LAY_R1_LIST_TABLE
//                + " ON " + REG_N_HH_TABLE + "." + LAY_R_LIST_NAME_COL + "=" + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL
//                + " LEFT JOIN " + GEO_LAY_R2_LIST_TABLE + " ON " + REG_N_HH_TABLE + "." + LAY_R2_LIST_NAME_COL + "=" + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
//                + " LEFT JOIN " + GEO_LAY_R3_LIST_TABLE + " ON " + REG_N_HH_TABLE + "." + LAY_R3_LIST_NAME + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
//                + " LEFT JOIN " + GEO_LAY_R4_LIST_TABLE + " ON " + REG_N_HH_TABLE + "." + LAY_R4_LIST_NAME_COL + "=" + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL
//                + " LEFT JOIN " + COUNTRY_TABLE + " ON " + REG_N_HH_TABLE + "." + COUNTRY_CODE + "=" + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE
//
//                + " WHERE " + PID_COL + "='" + hhID
//                + "' AND " + REG_N_HH_TABLE + "." + COUNTRY_CODE + "='" + c_code
//                + "' AND R_District='" + layR1Code + "'"
//                + " AND R_Upazilla='" + layR2Code + "'"
//                + " AND R_Union='" + layR3Code + "'"
//                + " AND R_Village='" + layR4Code
//
//                + "' ORDER BY " + REG_N_HH_TABLE + "." + ID_COL + " DESC";
//    }

    /***
     * @return and condition query
     */
    public static String srvTable_And_sql(String cCode, String donorCode, String awardCode, String layR1Code, String lay2Code, String layR3Code, String lay4Code, String hhid, String memid, String progCode, String srvCode, String opCode, String opMonthCode, String srvDate, String srvSerial, String distFlag) {
        return ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + LAY_R1_LIST_CODE_COL + " = '" + layR1Code + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + lay2Code + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + layR3Code + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + lay4Code + "' "
                + " AND " + HHID_COL + " = '" + hhid + "' "
                + " AND " + MEM_ID_COL + " = '" + memid + "' "
                + " AND " + PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + SRV_CODE_COL + " = '" + srvCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + opCode + "' "
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SERVICE_TABLE_SERVICE_DT_COL + " = '" + srvDate + "' "
                + " AND " + SERVICE_TABLE_SERVICE_SL_COL + " = '" + srvSerial + "' "
                + " AND " + DIST_FLAG_COL + " = '" + distFlag + "' ";
    }

    public static String selectSrvTable_sql(String cCode, String donorCode, String awardCode, String layR1Code, String lay2Code, String layR3Code, String lay4Code, String hhid, String memid, String progCode, String srvCode, String opCode, String opMonthCode, String srvDate, String srvSerial, String distFlag) {
        return "SELECT * FROM " + SERVICE_TABLE + " WHERE " + srvTable_And_sql(cCode, donorCode, awardCode, layR1Code, lay2Code, layR3Code, lay4Code, hhid, memid, progCode, srvCode, opCode, opMonthCode, srvDate, srvSerial, distFlag);
    }

    /**
     * Only fore the Service recording Activity to Specifies the Service Center
     *
     * @param opMode operation Mode of Device
     * @return query
     */
    public static String loadServiceCenter_sql(int opMode) {
        String criteria = "";
        switch (opMode) {
            case UtilClass.SERVICE_OPERATION_MODE:
                criteria = "SELECT  CASE " + FDP_CODE_COL + " WHEN  'null'  THEN '000'  ELSE " + FDP_CODE_COL + " END " + " || '' || " + SERVICE_CENTER_CODE_COL + " , " +
                        SERVICE_CENTER_NAME_COL + " FROM " + SRV_CENTER_TABLE
                        + " WHERE " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL + " || '' || "
                        + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + " IN ( SELECT "
                        + SELECTED_SERVICE_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL + " || '' || "
                        + SELECTED_SERVICE_CENTER_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " from " + SELECTED_SERVICE_CENTER_TABLE + ")"
                        + " AND " + FDP_CODE_COL + " != 'null' "
                        + " GROUP BY " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL;


                break;
            default:
                criteria = "SELECT  CASE " + FDP_CODE_COL + " WHEN  'null'  THEN '000'  ELSE " + FDP_CODE_COL + " END " + " || '' || " + SERVICE_CENTER_CODE_COL + " , " +
                        SERVICE_CENTER_NAME_COL + " FROM " + SRV_CENTER_TABLE;
                break;
        }
        return criteria;
    }

    /**
     * this method only use for dynamic response
     *
     * @param cCode         Country Code
     * @param resLupText    response look up text
     * @param lup_TableName look up table Name
     * @return dynamic query
     */
    public static String loadDynamicSpinnerListLoader_sql(String cCode, String resLupText,
                                                          String lup_TableName,
                                                          DynamicDataIndexDataModel dyBasic) {
        String udf = "";

        switch (resLupText) {

            case GEO_LAYER_3:

                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL
                        + ", " + LAY_R3_LIST_NAME
                        + " FROM " + GEO_LAY_R3_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'";


                break;
            case GEO_LAYER_2:
                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL
                        + ", " + LAY_R2_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R2_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + "= '" + cCode + "'";


                break;

            case GEO_LAYER_1:

                udf = "SELECT " + LAY_R_LIST_CODE_COL
                        + ", " + LAY_R_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R1_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'";


                break;

            case GEO_LAYER_4:

                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL
                        + ", " + LAY_R4_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R4_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'";


                break;

            case GEO_LAYER_ADDRESS:

                udf = "SELECT " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL + " || '' || " + REGN_ADDRESS_LOOKUP_CODE_COL
                        + ", " + REGN_ADDRESS_LOOKUP_NAME_COL
                        + " FROM " + LUP_REGN_ADDRESS_LOOKUP_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + "='" + cCode + "'";


                break;

            case SERVICE_SITE:

                udf = "SELECT " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL
                        + ", " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_NAME_COL
                        + " FROM " + SRV_CENTER_TABLE
                        + " WHERE " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'";


                break;

            case DISTRIBUTION_POINT:

                udf = "SELECT " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + ", " + FDP_MASTER_TABLE + "." + FDP_NAME_COL
                        + " FROM " + FDP_MASTER_TABLE
                        + " WHERE " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE + "='" + cCode + "'";


                break;


            case LOOKUP_LIST:

                udf = "SELECT " + DT_LUP_TABLE + "." + LIST_CODE_COL
                        + ", " + DT_LUP_TABLE + "." + LIST_NAME_COL
                        + " FROM " + DT_LUP_TABLE
                        + " WHERE " + DT_LUP_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
                        + " AND " + DT_LUP_TABLE + "." + TABLE_NAME_COL + "= '" + lup_TableName + "'";


                break;


            case COMMUNITY_GROUP:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'");

                break;


            case COMMUNITY_GROUP_PG:


                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '001' " // Producer group= 001
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;
            case COMMUNITY_GROUP_IG:
                udf = loadCommunityGroup(
                        " INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                                + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                                + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                                + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '003' " // Irrigation Group= 003
                                + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;

            case COMMUNITY_GROUP_MG:
                udf = loadCommunityGroup(
                        " INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                                + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                                + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                                + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '002' " // Marketing Group= 002
                                + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;

            case COMMUNITY_GROUP_WE:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '004' " // WeVSL= 004
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;

            case COMMUNITY_GROUP_LG:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '005' " // Livestock Group= 005
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;

            case ORGANIZATION_LIST:

                udf = "SELECT  progOR." + ORG_N_CODE_COL
                        + ", pOrg." + ORGANIZATION_NAME + " " +
                        "                                FROM " + PROGRAM_ORGANIZATION_ROLE_TABLE + " AS progOR "
                        + "                               INNER JOIN " +
                        "                                " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS pOrg " +
                        "                               ON progOR." + ORG_N_CODE_COL + " = pOrg." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + "  " +
                        "                                WHERE (progOR." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "')" +
                        " GROUP BY pOrg." + ORGANIZATION_NAME;

                break;

            case DTResponseRecordingActivity.COMMNITY_ANIMAL:

                udf = "SELECT " + ANIMAL_CODE_COL +
                        " , " + ANIMAL_TYPE_COL
                        + " FROM " + LUP_COMMUNITY_ANIMAL_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;

            case "Community Lead Position":

                udf = "SELECT " + LEAD_CODE_COL +
                        " , " + LEAD_POSITION_COL
                        + " FROM " + LUP_COMMUNITY_LEAD_POSITION_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;

            case "Commnity Loan Source":

                udf = "SELECT " + LOAN_CODE_COL +
                        " , " + LOAN_SOURCE_COL
                        + " FROM " + LUP_COMMUNITY_LOAN_SOURCE_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;


            case "Commnity Fund Source":

                udf = "SELECT " + FUND_CODE_COL +
                        " , " + FUND_SOURCE_COL
                        + " FROM " + LUP_COMMUNITY_FUND_SOURCE_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;
            case "Community Irrigation System":
                udf = "SELECT " + IRRI_SYS_CODE_COL +
                        " , " + IRRI_SYS_NAME_COL
                        + " FROM " + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;


            case "Prog Group Crop List":
                udf = "SELECT " + CROP_CODE_COL +
                        " , " + CROP_NAME_COL
                        + " FROM " + LUP_PROG_GROUP_CROP_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;


        }


        return udf;

    }


    public static String loadDynamicSpinnerValueInReport_sql(String cCode, String resLupText, String value, DTSurveyTableDataModel dataModel) {
        String udf = "";

        switch (resLupText) {

            case GEO_LAYER_3:

                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL
                        + ", " + LAY_R3_LIST_NAME
                        + " FROM " + GEO_LAY_R3_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                        + " AND " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " = '" + value + "'"

                ;


                break;
            case GEO_LAYER_2:
                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL
                        + ", " + LAY_R2_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R2_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + "= '" + cCode + "'"
                        + " AND " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + "= '" + value + "'";


                break;

            case GEO_LAYER_1:

                udf = "SELECT " + LAY_R_LIST_CODE_COL
                        + ", " + LAY_R_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R1_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                        + " AND " + LAY_R_LIST_CODE_COL + " = '" + value + "'";


                break;

            case GEO_LAYER_4:

                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL
                        + ", " + LAY_R4_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R4_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                        + " AND " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL + " = '" + value + "'";


                break;

            case GEO_LAYER_ADDRESS:

                udf = "SELECT " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL + " || '' || " + REGN_ADDRESS_LOOKUP_CODE_COL
                        + ", " + REGN_ADDRESS_LOOKUP_NAME_COL
                        + " FROM " + LUP_REGN_ADDRESS_LOOKUP_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL + " || '' || " + REGN_ADDRESS_LOOKUP_CODE_COL + "='" + value + "'";


                break;

            case COMMUNITY_GROUP:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"

                        + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                        + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                        + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                        + " commGrp." + GROUP_CODE_COL + " || '' || "
                        + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"

                );

                break;


            case COMMUNITY_GROUP_PG:


                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '001' " // Producer group= 001
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                        + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                        + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                        + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                        + " commGrp." + GROUP_CODE_COL + " || '' || "
                        + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;
            case COMMUNITY_GROUP_IG:
                udf = loadCommunityGroup(
                        " INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                                + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                                + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                                + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '003' " // Irrigation Group= 003
                                + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                                + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                                + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                                + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                                + " commGrp." + GROUP_CODE_COL + " || '' || "
                                + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                                + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                                + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;

            case COMMUNITY_GROUP_MG:
                udf = loadCommunityGroup(
                        " INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                                + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                                + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                                + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '002' " // Marketing Group= 002
                                + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                                + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                                + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                                + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                                + " commGrp." + GROUP_CODE_COL + " || '' || "
                                + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                                + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                                + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;

            case COMMUNITY_GROUP_WE:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '004' " // WeVSL= 004
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                        + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                        + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                        + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                        + " commGrp." + GROUP_CODE_COL + " || '' || "
                        + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;

            case COMMUNITY_GROUP_LG:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '005' " // Livestock Group= 005
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                        + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                        + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                        + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                        + " commGrp." + GROUP_CODE_COL + " || '' || "
                        + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;


            case SERVICE_SITE:

                udf = "SELECT " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL
                        + ", " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_NAME_COL
                        + " FROM " + SRV_CENTER_TABLE
                        + " WHERE " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + cCode + "'"
                        + " AND " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL + "= '" + value + "'";


                break;

            case DISTRIBUTION_POINT:

                udf = "SELECT " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + ", " + FDP_MASTER_TABLE + "." + FDP_NAME_COL
                        + " FROM " + FDP_MASTER_TABLE
                        + " WHERE " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE + "= '" + cCode + "'"
                        + " AND " + FDP_MASTER_TABLE + "." + FDP_CODE_COL + "= '" + value + "'";


                break;


            case LOOKUP_LIST:

                udf = "SELECT " + DT_LUP_TABLE + "." + LIST_CODE_COL
                        + ", " + DT_LUP_TABLE + "." + LIST_NAME_COL
                        + " FROM " + DT_LUP_TABLE
                        + " WHERE " + DT_LUP_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
                        + " AND " + DT_LUP_TABLE + "." + LIST_CODE_COL + "= '" + value + "'";


                break;


            case ORGANIZATION_LIST:

                udf = "SELECT  progOR." + ORG_N_CODE_COL
                        + ", pOrg." + ORGANIZATION_NAME + " "
                        + " FROM " + PROGRAM_ORGANIZATION_ROLE_TABLE + " AS progOR "
                        + " INNER JOIN " + " " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS pOrg "
                        + " ON progOR." + ORG_N_CODE_COL + " = pOrg." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL
                        + " WHERE progOR." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND progOR." + ORG_N_CODE_COL + " = '" + value + "' "
                        + " GROUP BY pOrg." + ORGANIZATION_NAME;

                break;

            case DTResponseRecordingActivity.COMMNITY_ANIMAL:

                udf = "SELECT " + ANIMAL_CODE_COL +
                        " , " + ANIMAL_TYPE_COL
                        + " FROM " + LUP_COMMUNITY_ANIMAL_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + ANIMAL_CODE_COL + " = '" + value + "' ";


                break;

            case DTResponseRecordingActivity.COMMNITY_LEAD_POSITION:

                udf = "SELECT " + LEAD_CODE_COL +
                        " , " + LEAD_POSITION_COL
                        + " FROM " + LUP_COMMUNITY_LEAD_POSITION_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + LEAD_CODE_COL + " = '" + value + "' ";


                break;

            case "Commnity Loan Source":

                udf = "SELECT " + LOAN_CODE_COL +
                        " , " + LOAN_SOURCE_COL
                        + " FROM " + LUP_COMMUNITY_LOAN_SOURCE_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + LOAN_CODE_COL + " = '" + value + "' ";


                break;


            case "Commnity Fund Source":

                udf = "SELECT " + FUND_CODE_COL +
                        " , " + FUND_SOURCE_COL
                        + " FROM " + LUP_COMMUNITY_FUND_SOURCE_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + FUND_CODE_COL + " = '" + value + "' ";


                break;
            case "Community Irrigation System":
                udf = "SELECT " + IRRI_SYS_CODE_COL +
                        " , " + IRRI_SYS_NAME_COL
                        + " FROM " + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + IRRI_SYS_CODE_COL + " = '" + value + "' ";


                break;


            case "Prog Group Crop List":
                udf = "SELECT " + CROP_CODE_COL +
                        " , " + CROP_NAME_COL
                        + " FROM " + LUP_PROG_GROUP_CROP_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + CROP_CODE_COL + " = '" + value + "' ";


                break;


        }


        return udf;

    }

    private static String loadCommunityGroup(String whereCondition) {
        return " SELECT DISTINCT "

                + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                + " commGrp." + GROUP_CODE_COL + " || '' || "
                + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                + " commGrp." + GRP_LAY_R3_LIST_CODE_COL

                + " , award." + AWARD_SHORT_COL + " || '-' ||"
                + " counAward." + AWARD_SHORT_NAME_COL + " || '-' ||"
                + " admProg." + PROGRAM_SHORT_NAME_COL + " || '-' ||"
                + " commGrp." + GROUP_NAME_COL
                + " FROM " + COMMUNITY_GROUP_TABLE + " AS commGrp "

                + " LEFT JOIN " + ADM_AWARD_TABLE + " AS award "
                + " ON award." + ADM_DONOR_CODE_COL + " = commGrp." + ADM_DONOR_CODE_COL
                + " AND award." + AWARD_CODE_COL + " = commGrp." + ADM_AWARD_CODE_COL

                + " INNER JOIN " + ADM_COUNTRY_AWARD_TABLE + " AS counAward "

                + " ON counAward." + ADM_DONOR_CODE_COL + " = commGrp." + ADM_DONOR_CODE_COL
                + " AND counAward." + ADM_AWARD_CODE_COL + " = commGrp." + ADM_AWARD_CODE_COL

                + " INNER JOIN " + ADM_PROGRAM_MASTER_TABLE + " AS admProg "

                + " ON admProg." + ADM_DONOR_CODE_COL + " = commGrp." + ADM_DONOR_CODE_COL
                + " AND admProg." + ADM_AWARD_CODE_COL + " = commGrp." + ADM_AWARD_CODE_COL
                + " AND admProg." + ADM_PROG_CODE_COL + " = commGrp." + ADM_PROG_CODE_COL

                // where condition would be dy namic
                //     + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                + whereCondition
                + " GROUP BY commGrp." + ADM_DONOR_CODE_COL
                + " , commGrp." + ADM_AWARD_CODE_COL
                + " , commGrp." + ADM_PROG_CODE_COL
                + " , commGrp." + GROUP_CODE_COL
                + " , commGrp." + LAY_R1_CODE_COL
                + " , commGrp." + GRP_LAY_R2_LIST_CODE_COL
                + " , commGrp." + GRP_LAY_R3_LIST_CODE_COL
                + " ORDER BY award." + AWARD_SHORT_COL + " || '-' ||"
                + " counAward." + AWARD_SHORT_NAME_COL + " || '-' ||"
                + " admProg." + PROGRAM_SHORT_NAME_COL + " || '-' ||"
                + " commGrp." + GROUP_NAME_COL;
    }

    public static String loadDtMonth_sql(String cCode, String opCode, String opMonthCode,
                                         String donorCode, String awardCode) {

        String criteria = "";
        if (opMonthCode.length() > 1)
            criteria = " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' ";

        return "SELECT " + OP_MONTH_CODE_COL + " AS OpMonthID, "
                + MONTH_LABEL_COL + " FROM " + OP_MONTH_TABLE
                + " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + STATUS + " = '" + "A" + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + opCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + criteria
                + " ORDER BY OpMonthID   DESC ";
    }


    public static String loadCountry_sql(int appOpMode, boolean multipleCountryAccessUserFlag) {
        String sql = "";
        switch (appOpMode) {
            case UtilClass.REGISTRATION_OPERATION_MODE:
            case UtilClass.TRANING_n_ACTIVITY_OPERATION_MODE:
                sql = " INNER JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE + " ON "
                        + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + " = "
                        + SELECTED_VILLAGE_TABLE + ".CountryCode";


                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE:
                sql = " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + " = "
                        + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL;

                break;
            case UtilClass.SERVICE_OPERATION_MODE:
                sql = " INNER JOIN " + SELECTED_SERVICE_CENTER_TABLE + " ON "
                        + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + " = "
                        + SELECTED_SERVICE_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL;


                break;

            case UtilClass.OTHER_OPERATION_MODE:
                /**                 * check  user  has access in multiple countries                 */
                if (multipleCountryAccessUserFlag) {
                    sql = " INNER JOIN " + SELECTED_COUNTRY_TABLE + " ON "
                            + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + " = "
                            + SELECTED_COUNTRY_TABLE + ".CountryCode";


                } else {


                    sql = " INNER JOIN " + SQLiteHandler.STAFF_GEO_INFO_ACCESS_TABLE + " AS staffAcces ON "
                            + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_COUNTRY_CODE + " = " + "staffAcces." + SQLiteHandler.ADM_COUNTRY_CODE_COL
                            + " WHERE (" + SQLiteHandler.BTN_NEW_COL + " = '1' " +
                            " OR " + SQLiteHandler.BTN_SAVE_COL + " = 1" +
                            " OR " + SQLiteHandler.BTN_DEL_COL + " = 1 ) GROUP BY " + " staffAcces." + SQLiteHandler.ADM_COUNTRY_CODE_COL;


                }

                break;
        }
        return sql;
    }

    public static String showNearBy_gpsSubGroup_sql(String cCode) {
        return "SELECT DISTINCT gpsSubGrp." + GROUP_CODE_COL + " || '' || gpsSubGrp." + SUB_GROUP_CODE_COL
                + " , gpsGrp." + GROUP_NAME_COL + "|| '-' || gpsSubGrp." + SUB_GROUP_NAME_COL
                + " FROM " + GPS_LOCATION_TABLE + " AS gpsLoc " + " INNER JOIN " + GPS_SUB_GROUP_TABLE + " AS gpsSubGrp "
                + " ON gpsLoc." + GROUP_CODE_COL + " = gpsSubGrp." + GROUP_CODE_COL
                + " AND  gpsLoc." + SUB_GROUP_CODE_COL + " = gpsSubGrp." + SUB_GROUP_CODE_COL
                + " INNER JOIN " + GPS_GROUP_TABLE + " AS gpsGrp "
                + " ON gpsLoc." + GROUP_CODE_COL + " = gpsGrp." + GROUP_CODE_COL
                + " WHERE gpsLoc." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " ORDER BY gpsSubGrp." + SUB_GROUP_NAME_COL;


    }

    public static String getDynamicTableIndexList_sql(String cCode, String dtTitleSearch,
                                                      String staffId, int number) {
        String condition = "";
        if (number != NO_LIMIT)
            condition = " LIMIT 5 OFFSET " + number;

        return "SELECT dtB." + DT_TITLE_COL + "  " +
                " , dtCPgr." + DT_BASIC_COL + " AS dtBasicCode  " +
                " , donor." + DONOR_NAME_COL + " || '-' || award." + AWARD_SHORT_NAME_COL + " AS awardName  " +
                " , dtCPgr." + ADM_DONOR_CODE_COL + " || '' || dtCPgr." + ADM_AWARD_CODE_COL + " AS awardCode  " +
                " , prg." + PROGRAM_SHORT_NAME_COL + "  " +
                " , dtCPgr." + ADM_PROG_CODE_COL + "  " +
                " , dtCPgr." + PROG_ACTIVITY_TITLE_COL +
                " , dtCPgr." + ADM_COUNTRY_CODE_COL +
                " , dtB." + DT_OP_MODE_COL +

                " , dtCPgr." + ADM_DONOR_CODE_COL +
                " , dtCPgr." + PROG_ACTIVITY_CODE_COL +
                " , dtB." + DT_SHORT_NAME_COL +
                " , dtB." + ENTRY_BY +
                " , dtB." + ENTRY_DATE
                + "  FROM " +
                DT_COUNTRY_PROGRAM_TABLE + " AS dtCPgr  " +
                " LEFT JOIN " + DT_BASIC_TABLE + "  AS dtB  " +
                " ON dtB." + DT_BASIC_COL + " = dtCpgr." + DT_BASIC_COL + "   " +
                " LEFT JOIN " +
                ADM_COUNTRY_AWARD_TABLE + " as award  ON " +
                "  award." + ADM_COUNTRY_CODE_COL + " = dtCpgr." + ADM_COUNTRY_CODE_COL + "  " +
                " AND  award." + ADM_DONOR_CODE_COL + " = dtCpgr." + ADM_DONOR_CODE_COL + "  " +
                " AND award." + ADM_AWARD_CODE_COL + "= dtCpgr." + ADM_AWARD_CODE_COL + "  " +
                " LEFT JOIN " +
                ADM_DONOR_TABLE + " AS donor  " +
                " ON donor." + ADM_DONOR_CODE_COL + " = dtCpgr." + ADM_DONOR_CODE_COL + "  " +
                " LEFT JOIN " +
                ADM_PROGRAM_MASTER_TABLE + " AS prg  " +
                " ON prg." + ADM_DONOR_CODE_COL + " = dtCpgr." + ADM_DONOR_CODE_COL + "  " +
                " AND prg." + ADM_AWARD_CODE_COL + " = dtCpgr." + ADM_AWARD_CODE_COL + "  " +
                " AND prg." + ADM_PROG_CODE_COL + " = dtCpgr." + ADM_PROG_CODE_COL + "  " +
                "  INNER JOIN " + DT_ENU_TABLE + " AS dtEnu ON " +
                " dtEnu." + DT_BASIC_COL + " = dtCPgr." + DT_BASIC_COL +
                " WHERE dtCPgr." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND dtEnu." + DT_STF_CODE_COL + " = '" + staffId + "' "
                + " AND dtB." + DT_TITLE_COL + " LIKE '%" + dtTitleSearch + "%' "
                + condition;

    }

    /**
     * @param cCode      country Code
     * @param grpCode    group Code
     * @param subGrpCode Sub group Code
     * @return location name
     */
    public static String loadLocationLoader_sql(String cCode, String grpCode, String subGrpCode) {
        return "SELECT " + LOCATION_CODE_COL + "," + LOCATION_NAME_COL + " FROM " +
                GPS_LOCATION_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + GROUP_CODE_COL + "='" + grpCode + "' " +
                " AND " + SUB_GROUP_CODE_COL + "='" + subGrpCode + "'";
    }

    /**
     * the sql used only Map Activity
     *
     * @param cCode country code
     * @return location name, groupCode + subGroupCode + locationCode
     */
    public static String loadLocationLoader_sql(String cCode) {
        return "SELECT " + GROUP_CODE_COL + " || '' ||" + SUB_GROUP_CODE_COL + " || '' ||" + LOCATION_CODE_COL
                + "," + LOCATION_NAME_COL
                + " FROM " + GPS_LOCATION_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " ORDER BY " + LOCATION_NAME_COL + " ASC ";
    }

    /**
     * this method sql to load Service Name and code to Criteria spinner in
     *
     * @param awardCode   award Code
     * @param donorCode   donor Code
     * @param programCode program Code
     * @return srvCode & srvName
     */
    public static String loadCriteria_sql(String awardCode, String donorCode, String programCode) {
        return "SELECT " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId" + " , " +
                SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria" +
                " FROM " + ADM_COUNTRY_PROGRAM_TABLE + " JOIN " + SERVICE_MASTER_TABLE +
                " ON " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " " +
                " WHERE " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + "='" + programCode + "'"
                + " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + PROG_FLAG + "= '1'"
                + " GROUP BY " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL;

    }

    /**
     * @return layRlist Code and name
     */
    public static String loadLayR4List_sql() {
        return "SELECT  v." +
                ADM_COUNTRY_CODE_COL + " || '' || v." +
                MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || v." + LAY_R2_LIST_CODE_COL + " || '' || v." + LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL
                + ", v." + LAY_R4_LIST_NAME_COL + " FROM " + GEO_LAY_R4_LIST_TABLE + " AS v " +
                "  INNER JOIN " + SELECTED_VILLAGE_TABLE + " as S "
                + " ON S.CountryCode "

                + " ||''|| S.DistrictCode "
                + " ||''|| S.UpazillaCode "
                + " ||''|| S.UnitCode "
                + " ||''|| S.VillageCode "

                + "  = v." + ADM_COUNTRY_CODE_COL
                + " ||''|| v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " ||''|| v." + LAY_R2_LIST_CODE_COL
                + " ||''|| v." + LAY_R3_LIST_CODE_COL
                + " ||''|| v." + LAY_R4_LIST_CODE_COL + " " +
                " GROUP BY  v." + ADM_COUNTRY_CODE_COL
                + " ||''|| v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " ||''|| v." + LAY_R2_LIST_CODE_COL
                + " ||''|| v." + LAY_R3_LIST_CODE_COL
                + " ||''|| v." + LAY_R4_LIST_CODE_COL + " ";
    }


    public static String getSelectedVillageList_sql() {
        return "SELECT " + GEO_LAY_R_CODE_COL
                + " , " + VILLAGE_NAME_COL + " FROM " + SELECTED_VILLAGE_TABLE
                + " GROUP BY " + GEO_LAY_R_CODE_COL;
    }

    public static String getSelectedCountryCodeFromSelectedVillage_sql() {
        return "SELECT CountryCode "
                + " FROM " + SELECTED_VILLAGE_TABLE
                + " GROUP BY  CountryCode ";
    }


    public static String getSelectedCountryCodeFromSelectedFDP_sql() {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + " FROM " + SELECTED_FDP_TABLE
                + " GROUP BY   " + ADM_COUNTRY_CODE_COL;
    }


    public static String getSelectedCountryCodeFromSelectedCenter_sql() {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + " FROM " + SELECTED_SERVICE_CENTER_TABLE
                + " GROUP BY   " + ADM_COUNTRY_CODE_COL;
    }

    public static String loadGroupLoader_sql(String cCode, String donorCode, String awardCode, String progCode, String grpCateCode) {
        return " SELECT  " + GROUP_CODE_COL + " , " + GROUP_NAME_COL
                + " FROM " + COMMUNITY_GROUP_TABLE
                + " INNER JOIN " + SELECTED_VILLAGE_TABLE
                + " ON " + LAY_R1_CODE_COL + " = DistrictCode "
                + " AND " + GRP_LAY_R2_LIST_CODE_COL + " = UpazillaCode "
                + " AND " + GRP_LAY_R3_LIST_CODE_COL + " = UnitCode "
                + " WHERE " + COMMUNITY_GROUP_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + GROUP_CAT_CODE_COL + " = '" + grpCateCode + "' "
                + " GROUP BY  " + GROUP_CODE_COL
                + " ORDER BY  " + GROUP_NAME_COL;
    }

    public static String loadProgram_sql(String cCode) {
        return "SELECT " +
                ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " || '' || "
                + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL + " || '' || "
                + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " AS criteriaId" + " , " +
                ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " AS Criteria" +
                " FROM " + ADM_PROGRAM_MASTER_TABLE

                + " INNER JOIN " + ADM_COUNTRY_PROGRAM_TABLE
                + " ON " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " =  " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL

                + " WHERE " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "

                + " GROUP BY " + ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL
                + " ORDER BY Criteria ";
    }


    public static String loadProgram_sql(String awardCode, String donorCode) {
        return "SELECT " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " , " +
                ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL +
                " FROM " + ADM_COUNTRY_PROGRAM_TABLE + " JOIN " + ADM_PROGRAM_MASTER_TABLE + " ON " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL +
                " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " " +

                " WHERE " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "= '" + awardCode + "'"
                + " AND " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "= '" + donorCode + "'"

                + " GROUP BY " + ADM_COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL;
    }

    public static String getLocationList_sql(String cCode, String searchLocName) {
        return "SELECT " + GPS_LOCATION_TABLE + "." + GROUP_CODE_COL + " || '' || " + GPS_LOCATION_TABLE + "." + SUB_GROUP_CODE_COL + " || '' || " + GPS_LOCATION_TABLE + "." + SQLiteHandler.LOCATION_CODE_COL
                + " , " + GPS_LOCATION_TABLE + "." + LOCATION_NAME_COL

                + ", CASE WHEN  ifnull(length(" + GPS_LOCATION_TABLE + "." + LATITUDE_COL + "), 0) = 0  THEN 'N' ELSE 'Y' END AS dataExit "
                + " , " + GPS_GROUP_TABLE + "." + GROUP_NAME_COL
                + " FROM " + GPS_LOCATION_TABLE
                + " LEFT JOIN " + GPS_GROUP_TABLE
                + " ON " + GPS_GROUP_TABLE + "." + GROUP_CODE_COL + " = " + GPS_LOCATION_TABLE + "." + GROUP_CODE_COL
                + " WHERE " + GPS_LOCATION_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + GPS_LOCATION_TABLE + "." + LOCATION_NAME_COL + " LIKE '%" + searchLocName + "%' "
                + " ORDER BY " + GPS_LOCATION_TABLE + "." + LOCATION_NAME_COL + " ASC ";
    }

    public static String getSingleDynamicQuestion_sql(String dtBasicCode, int index) {
        // this query didn't maintain the sequence
       /*  "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " LIMIT 1 OFFSET " + String.valueOf(index);*/

        return "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " AND " + QSEQ_SCOL + "= " + index;/*+
                " LIMIT 1 OFFSET " + String.valueOf(index);*/

    }

    public static String dtSurveyTableDataModels_sql(int surveyNum, String dtBasic) {
        return "SELECT dtSrv." + DT_BASIC_COL +
                " , dtSrv." + "CountryCode" +
                " , dtSrv." + "DonorCode" +
                " , dtSrv." + "AwardCode" +
                " , dtSrv." + "ProgramCode" +
                " , dtSrv." + DT_ENU_ID_COL +
                " , dtSrv." + DTQ_CODE_COL +
                " , dtSrv." + DTA_CODE_COL +
                " , dtSrv." + DT_R_SEQ_COL +
                " , dtSrv." + DTA_VALUE_COL +
                " , dtSrv." + PROG_ACTIVITY_CODE_COL +
                " , dtSrv." + DTTIME_STRING_COL +
                " , dtSrv." + OP_MODE_COL +
                " , dtSrv." + OP_MONTH_CODE_COL +
                " , dtSrv." + DATA_TYPE_COL +
                " , dtSrv." + DTQ_TEXT_COL +
                " , dtSrv." + DT_SURVEY_NUM +
                " , dtSrv." + U_FILE_COL +
                " , dtSrv." + RESPONSE_VALUE_CONTROL_COL +
                " , dtSrv." + QRES_LUP_TEXT_COL +
                " , dtSrv." + DTA_LABEL_COL +
                " , dtan." + DTA_LABEL_COL +

                " FROM " + DT_SURVEY_TABLE + " AS dtSrv " +
                " left join " + DT_A_TABLE + " AS dtan ON " +
                " dtSrv." + DT_BASIC_COL + " =  dtan." + DT_BASIC_COL +
                " AND  dtSrv." + DTQ_CODE_COL + " =  dtan." + DTQ_CODE_COL +
                " AND  dtSrv." + DTA_CODE_COL + " =  dtan." + DTA_CODE_COL +
                " AND  dtSrv." + DTA_VALUE_COL + " =  dtan." + DTA_VALUE_COL +

                " WHERE dtSrv." + DT_SURVEY_NUM + " = " + surveyNum +
                " AND dtSrv." + DT_BASIC_COL + " = '" + dtBasic + "' ";
    }


    public static String getDTA_Table_sql(String dtBasic, String dtQCode) {
        return "SELECT * FROM " + DT_A_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasic + "'" +
                " AND " + DTQ_CODE_COL + "= '" + dtQCode + "'";
    }


    public static String loadCountry_sql(String operationModeName) {
        String criteria = "";

        switch (operationModeName) {
            case UtilClass.REGISTRATION_OPERATION_MODE_NAME:
                criteria = " INNER JOIN " + SELECTED_VILLAGE_TABLE + " ON "
                        + COUNTRY_TABLE + "." + ADM_COUNTRY_CODE_COL + " = "
                        + SELECTED_VILLAGE_TABLE + ".CountryCode";


                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE_NAME:
                criteria = " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + COUNTRY_TABLE + "." + ADM_COUNTRY_CODE_COL + " = "
                        + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL;

                break;
            case UtilClass.SERVICE_OPERATION_MODE_NAME:
                criteria = " INNER JOIN " + SELECTED_SERVICE_CENTER_TABLE + " ON "
                        + COUNTRY_TABLE + "." + ADM_COUNTRY_CODE_COL + " = "
                        + SELECTED_SERVICE_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL;


                break;
        }

        return criteria;
    }

    public static String loadLayR3List_sql(String cCode) {
        return "SELECT  ut." + LAY_R1_LIST_CODE_COL + " || '' || " + "ut." + LAY_R2_LIST_CODE_COL + " || '' || " + " ut." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " AS layCode "
                + ", " + "ut." + LAY_R3_LIST_NAME + " AS layName "
                + " FROM " + GEO_LAY_R3_LIST_TABLE + " AS ut"
                + " INNER JOIN " + SELECTED_VILLAGE_TABLE + " AS geo "
                + " ON geo.CountryCode " + " = " + "ut." + ADM_COUNTRY_CODE_COL
                + " AND geo. DistrictCode" + " = " + "ut." + LAY_R1_LIST_CODE_COL
                + " AND   geo. UpazillaCode" + " = ut." + LAY_R2_LIST_CODE_COL
                + " AND  geo. UnitCode" + " = ut." + LAY_R3_LIST_CODE_COL
                + " WHERE " + "ut." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"

                + " GROUP BY layCode, layName";
    }


    public static String editLiberiaMemberData_sql(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID, String str_hhMemID) {
        return ADM_COUNTRY_CODE_COL + " = '" + str_c_code + "' "
                + " AND " + LAY_R1_LIST_CODE_COL + " = '" + str_district + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + str_upazilla + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + str_union + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + str_village + "' "
                + " AND " + HHID_COL + " = '" + hhID + "' "
                + " AND " + HH_MEM_ID + " = '" + str_hhMemID + "' ";
    }

    public static String loadCriteriaForDistributionSummary(String cCode, String donorCode, String awardCord, String progCode) {
        return "SELECT " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS IdCriteria ,  " +
                SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria  " +

                " FROM " + SERVICE_TABLE + " JOIN " + SRV_CENTER_TABLE
                + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +

                " JOIN " + OP_MONTH_TABLE
                + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL + " AND " +
                SERVICE_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + " " +
                " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL +
                " WHERE  " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "' " +
                " AND " + SERVICE_TABLE + "." + PROG_CODE_COL + " = '" + progCode + "' " +
                " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL;
    }


    public static String getServiceDetailsForMember_sql(String cCode, String donorCode, String awardCord,
                                                        String districCode, String upCode, String unCode,
                                                        String vCode, String hhId, String mmId, String opCode,
                                                        String opMCode, String prgCode, String srvCode) {
        return " SELECT " + SERVICE_TABLE_SERVICE_SL_COL
                + " , " + SERVICE_TABLE_SERVICE_DT_COL

                + " , " + SRV_STATUS_COL +
                " FROM  " + SERVICE_TABLE +
                " WHERE  " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " And   " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " And   " + ADM_AWARD_CODE_COL + " = '" + awardCord + "' " +
                " And  " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + districCode + "' " +
                " And   " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " And   " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " And   " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                "  And  " + HHID_COL + " = '" + hhId + "' " +
                "  And  " + MEM_ID_COL + " = '" + mmId + "' " +
                " And   " + PROG_CODE_COL + " ='" + prgCode + "' " +
                "  And  " + SRV_CODE_COL + " ='" + srvCode + "' " +
                "  And  " + OPERATION_CODE_COL + " = '2' " +
                "  And  " + OP_MONTH_CODE_COL + "   ='" + opMCode + "' ";
    }

    public static String updateSrvMaxDate_sql(String cCode, String donorCode, String awardCode,
                                              String distCode, String upCode, String unCode, String vCode,
                                              String hhId, String memId, String progCode, String srvCode,
                                              String srvMaxDate) {
        return SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + LAY_R1_LIST_CODE_COL + " = '" + distCode + "'"
                + " AND  " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "'"
                + " AND  " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "'"
                + " AND  " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "'"
                + " AND  " + HHID_COL + " = '" + hhId + "'"
                + " AND  " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + PROG_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SRV_CODE_COL + " = '" + srvCode + "'";
    }

    public static String getLocationSpecificLatLong_sql(String cCode, String groupCode, String subGroupCode, String locationCode) {
        return " SELECT ( CASE WHEN " + LATITUDE_COL + "='ISNULL' Then "
                + LATITUDE_COL + "='' ElSE " + LATITUDE_COL + " END ) AS '" + LATITUDE_COL + "' ," +
                "(CASE WHEN " + LONGITUDE_COL + "='ISNULL'Then " + LONGITUDE_COL + " = '' ElSE " + LONGITUDE_COL + " END ) AS '" + LONGITUDE_COL + "'  " +
                " FROM " + GPS_LOCATION_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " ='" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " ='" + subGroupCode + "' "
                + " AND " + LOCATION_CODE_COL + " ='" + locationCode + "'";
    }


    public static String editMalawiMemberData_sql(String cCode, String layR1Code, String layR2Code,
                                                  String layR3Code, String layR4Code, String hhID,
                                                  String memId) {
        return ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " and " + LAY_R1_LIST_CODE_COL + " = '" + layR1Code + "'" +
                " and " + LAY_R2_LIST_CODE_COL + " = '" + layR2Code + "'" +
                " and " + LAY_R3_LIST_CODE_COL + " = '" + layR3Code + "'" +
                " and " + LAY_R4_LIST_CODE_COL + " = '" + layR4Code + "'" +
                " and " + HHID_COL + " = '" + hhID + "'" +
                " and " + HH_MEM_ID + " = '" + memId + "'";
    }


    public static String getSubGroupSpecificLatLongCoordinates_sql(String cCode, String grpCode,
                                                                   String subGrpCode) {
        return " SELECT "
                + LOCATION_CODE_COL
                + " , " + LOCATION_NAME_COL
                + " , " + LATITUDE_COL

                + " , " + LONGITUDE_COL
                + " FROM " + GPS_LOCATION_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " ='" + grpCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " ='" + subGrpCode + "' "
                + " AND (" + LATITUDE_COL + " != '' " + " OR  " + LONGITUDE_COL + " != '' )";
    }

    /**
     * @param cCode     country code
     * @param staffCode staff id
     * @return query String
     */
    public static String loadLayR2List_sql(String cCode, String staffCode) {
        return " Select DISTINCT  " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL + " || " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " AS code "
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
                + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + FDP_CODE_COL +

                " WHERE " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = '" + cCode + "'"
                + " AND " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_CODE + " = '" + staffCode + "'"
                + " AND " + STAFF_FDP_ACCESS_TABLE + "." + BTN_NEW_COL + " = '1'"
                + " ORDER BY  " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL;
    }

    public static String getSrvExtVoucherList_sql(String cCode, String layR1code,
                                                  String layR2Code, String lay3Code,
                                                  String lay4Code, String hhId,
                                                  String memId, String donorCode,
                                                  String awardCode, String prgCode,
                                                  String srvCode, String opMonthCode) {
        return "Select (Select " + ITEM_NAME_COL + " from " + VOUCHER_ITEM_TABLE +

                " where " + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + ITEM_CODE_COL + " = substr(VOCPI." + VOUCHER_ITEM_SPEC_COL + ",0,8))" +
                " ||'-'|| (Select " + UNITE_MEAS_COL + " ||' '|| " + MEASE_TITLE_COL + " from " + VOUCHER_ITEM__MEAS_TABLE +

                " where " + MEAS_R_CODE_COL + " = VOCPI." + MEAS_R_CODE_COL + " ) as item " +
                " ,   (case when SrET." + VOUCHER_ITEM_SPEC_COL + " is null then 'False' else 'True' end ) as checkedItem " +
                " , SrET." + VOUCHER_UNIT_COL + " AS  " + VOUCHER_UNIT_COL +
                " , VOCPI." + VOUCHER_ITEM_SPEC_COL + " AS " + VOUCHER_ITEM_SPEC_COL +
                " from " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + " as VOCPI" +
                " left join  " + SERVICE_EXTENDED_TABLE + " as  SrET" +
                "       on " +
                " SrET." + ADM_COUNTRY_CODE_COL + " = VOCPI." + ADM_COUNTRY_CODE_COL +

                " and  SrET." + ADM_AWARD_CODE_COL + " = VOCPI." + ADM_AWARD_CODE_COL
                + " and        SrET." + ADM_DONOR_CODE_COL + " = VOCPI." + ADM_DONOR_CODE_COL +
                " and        SrET." + VOUCHER_ITEM_SPEC_COL + " = VOCPI." + VOUCHER_ITEM_SPEC_COL +
                " and        SrET." + LAY_R1_LIST_CODE_COL + " = '" + layR1code + "'" +
                " and SrET." + LAY_R2_LIST_CODE_COL + " = '" + layR2Code + "'" +
                " and SrET." + LAY_R3_LIST_CODE_COL + " = '" + lay3Code + "'" +
                " and SrET." + LAY_R4_LIST_CODE_COL + " = '" + lay4Code + "'" +
                " and SrET." + HHID_COL + " = '" + hhId + "'" +
                " and SrET." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'" +
                " and SrET." + PROG_CODE_COL + " = '" + prgCode + "'" +
                " and SrET." + SRV_CODE_COL + " = '" + srvCode + "'" +
                " and SrET." + OPERATION_CODE_COL + " = '2'" +      // opcode 2 mean s service
                " and SrET." + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'" +
                " where VOCPI." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " and VOCPI." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " and VOCPI." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                " and VOCPI." + ADM_SRV_CODE_COL + " = '" + srvCode + "'";
    }

    public static String loadDtBasic_sql(String cCode, String staffId) {

        return "SELECT dtB." + DT_BASIC_COL + "  " +
                " , dtB." + DT_TITLE_COL + " " +

                "  FROM " + DT_COUNTRY_PROGRAM_TABLE + " AS dtCPgr  " +
                " LEFT JOIN " + DT_BASIC_TABLE + "  AS dtB  " +
                " ON dtB." + DT_BASIC_COL + " = dtCpgr." + DT_BASIC_COL + "   " +

                "  INNER JOIN " + DT_ENU_TABLE + " AS dtEnu ON " +
                " dtEnu." + DT_BASIC_COL + " = dtCPgr." + DT_BASIC_COL +
                " WHERE dtCPgr." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND dtEnu." + DT_STF_CODE_COL + " = '" + staffId + "' ";

    }
}//end of class
