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


    public static String getDynamicTableIndexList_sql(String cCode, String dtTitleSearch,
                                                      String staffId, int number) {
        String condition = "";
        if (number != NO_LIMIT)
            condition = " LIMIT 5 OFFSET " + number;

        return "SELECT dtB." + DT_TITLE_COL + "  AS dtTitle" +
                " , dtCPgr." + DT_BASIC_COL + " AS dtBasicCode  " +
                " , donor." + DONOR_NAME_COL + " || '-' || award." + AWARD_SHORT_NAME_COL + " AS awardName  " +
                " , dtCPgr." + ADM_DONOR_CODE_COL + " || '' || dtCPgr." + ADM_AWARD_CODE_COL + " AS awardCode  " +
                " , prg." + PROGRAM_SHORT_NAME_COL + "  AS prgShortName" +
                " , dtCPgr." + ADM_PROG_CODE_COL + " AS progCode " +
                " , dtCPgr." + PROG_ACTIVITY_TITLE_COL +" AS prgActive "+
                " , dtCPgr." + ADM_COUNTRY_CODE_COL + " AS cCode "+
                " , dtB." + DT_OP_MODE_COL +" AS dtOpMode "+

                " , dtCPgr." + ADM_DONOR_CODE_COL +" AS donoCode "+
                " , dtCPgr." + PROG_ACTIVITY_CODE_COL + " AS prgActiveCode "+
                " , dtB." + DT_SHORT_NAME_COL +" AS dtShortName "+
                " , dtB." + ENTRY_BY +" AS entry "+
                " , dtB." + ENTRY_DATE+ " AS entryDate "+
                " , dtB." + FREEZE_POINT_COL+" AS freezPoint "
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
              //  + " AND dtB." + DT_TITLE_COL + " LIKE '%" + dtTitleSearch + "%' "
                + condition;

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








    /**
     * ## this query didn't maintain the sequence
     * @param dtBasicCode dynamic Basic code
     * @param qSequence question sequence No
     * @return sql query string
     */
    public static String getSingleDynamicQuestion_sql(String dtBasicCode, int qSequence) {
        // this query didn't maintain the sequence
       /*  "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " LIMIT 1 OFFSET " + String.valueOf(index);*/

        return "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " AND " + QSEQ_SCOL + "= " + qSequence;

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
