package com.faisal.technodhaka.dlight.controller;

/**
 * This class is to define basic Application configuration
 * the link of the web server  *
 *
 * @author Faisal Mohammad
 * @version 7.0.0
 * @since 1.0
 */

public class AppConfig {





    /*********************************************************************
     * apk download link from Azure
     ********************************************************************/
    public static final String NEW_APK_DOWNLOAD_LINK = "windows.net/gpath-apk/G-path_OFF_20.apk";
    /********************************************************************
     *
     **********************************************************************/

    /***********************************************************************
     * Localhost
     ***********************************************************************/

    public static final String API_LINK = "http://192.168.157.1/dlite/api/";
    public static final String API_LINK_ENU = "http://192.168.157.1/dlite/api/index.php?enu";
    public static final String API_LINK_VER = "http://192.168.157.1/dlite/api/index.php?ver";
    public static final String URL_PROFILE_IMG = "http://192.168.157.1/dlite/api/n/0001.png";


    /***********************************************************************
     * Live
     ***********************************************************************/
//    public static final String API_LINK = "http://103.56.209.132:8080/dlite/api/";
//    public static final String API_LINK_ENU = "http://103.56.209.132:8080/dlite/api/index.php?enu";
//    public static final String API_LINK_VER = "http://103.56.209.132:8080/dlite/api/index.php?ver";
    /**
     * IMAGE
     */
//    public static final String URL_PROFILE_IMG = " http://103.56.209.132:8080/dlite/api/n/0001.png";
    /**********************************************************************
     *    end
     ***********************************************************************/

    /***********************************************************************
     *   Localhost out side ngrok
     ***********************************************************************/
    //  public static final String API_LINK = "http://83cb7db6.ngrok.io/api/";
    /***********************************************************************
     * end of Localhost out side ngrok
     ***********************************************************************/

    // Application developments  Environment
    public static Boolean DEV_ENVIRONMENT = true; // false / true

    /**
     * new version apk name apk
     */
    public static final String DOWNLOADED_APK_NAME = "Gpath.apk";







}
