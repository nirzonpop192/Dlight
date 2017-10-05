package com.faisal.technodhaka.dlight.activity;

/**
 * Activity for login validation and collect existing data from online
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.faisal.technodhaka.dlight.R;
import com.faisal.technodhaka.dlight.controller.AppConfig;
import com.faisal.technodhaka.dlight.controller.AppController;
import com.faisal.technodhaka.dlight.data_model.AdmCountryDataModel;


import com.faisal.technodhaka.dlight.data_model.VillageItem;
import com.faisal.technodhaka.dlight.fragments.BaseActivity;
import com.faisal.technodhaka.dlight.database.SQLiteHandler;
import com.faisal.technodhaka.dlight.network.ConnectionDetector;
import com.faisal.technodhaka.dlight.parse.Parser;
import com.faisal.technodhaka.dlight.utils.UtilClass;
import com.faisal.technodhaka.dlight.views.notifications.AlertDialogManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.faisal.technodhaka.dlight.utils.UtilClass.DISTRIBUTION_OPERATION_MODE_NAME;
import static com.faisal.technodhaka.dlight.utils.UtilClass.OTHER_OPERATION_MODE_NAME;
import static com.faisal.technodhaka.dlight.utils.UtilClass.REGISTRATION_OPERATION_MODE_NAME;
import static com.faisal.technodhaka.dlight.utils.UtilClass.SERVICE_OPERATION_MODE_NAME;
import static com.faisal.technodhaka.dlight.utils.UtilClass.TRANING_N_ACTIVITY_OPERATION_MODE_NAME;

//import java.util.logging.Handler;


public class LoginActivity extends BaseActivity {
    // LogCat tag
    private static final String TAG = LoginActivity.class.getSimpleName();

    public static final String ALL_DATA = "all_data";
    public static final String DYNAMIC_TABLE = "dynamic_table";
    public static final String ENU_TABLE = "enu_table";


    /**
     * function to verify login details & select 2 village
     */

    List<AdmCountryDataModel> countryNameList = new ArrayList<AdmCountryDataModel>();
    Dialog mdialog;
    ArrayList<VillageItem> aL_itemsSelected = new ArrayList<VillageItem>();
    ArrayList<AdmCountryDataModel> aCountryL_itemsSelected = new ArrayList<AdmCountryDataModel>();
    ArrayList<VillageItem> selectedVillageList = new ArrayList<VillageItem>();
    ArrayList<AdmCountryDataModel> selectedCountryList = new ArrayList<AdmCountryDataModel>();


    String[] countryNameStringArray;


    // Login Button
    private Button btnLogin;
    // User hhName Input box
    private EditText inputUsername;
    //password input box
    private EditText inputPassword;
    //progress mdialog wigedt
    private ProgressDialog barPDialog; //Bar Progress Dialog
    //progress handler
    private Handler barPDialogHandler;
    //sqlLite Database handler
    private SQLiteHandler db;
    // connection Detector
    ConnectionDetector cd;
    // flag for connectivity
    Boolean isInternetAvailable = false;
    //alert Dialog Manager
    AlertDialogManager alert;
    //Application configuration
    private AppConfig ac;
    //progress mdialog
    private ProgressDialog pDialog;
    // size  = 0, int type
//    private int size = 0;
    //mContext
    private final Context mContext = LoginActivity.this;
    //exit button
    private Button btnExit ,btnClean;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    //    private Button btnClean;
    String strCountryMode = "";


    private TextView tvDeviceId;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout._1_activity_login);
        barPDialogHandler = new Handler();


        viewReference();
        /**
         * Initialize Button and Input Boxes
         */


        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = settings.edit(); //2


        pDialog = new ProgressDialog(this);                                                         // Progress mdialog
        pDialog.setCancelable(false);


        db = new SQLiteHandler(getApplicationContext());                                             // SQLite database handler


        cd = new ConnectionDetector(getApplicationContext());                                       // connectivity manager


        setListener();
        createDeviceIDFile();




    }


    private void hideProgressBar() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    /**
     * @param msg text massage
     */
    private void startProgressBar(String msg) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
    }


    private boolean importDataBase() {
        boolean flag = false;

        try {
            String path = Environment.getExternalStorageDirectory().getPath() + "/" + SQLiteHandler.DATABASE_NAME;


            File newDb = new File(path);
            if (newDb.exists()) {
                /***
                 *  here isValidLocalLogin() only use for to create PCI.db file in in
                 *  package database folder . other wise the import db operation fails
                 */
                db.isValidLocalLogin("", "");

                flag = db.importDatabase(path, LoginActivity.this);
                File file = new File(path);                                                         // delete
                file.delete();

                db.reCreateSurveyTable();
            } else flag = false;


        } catch (IOException e) {
            e.printStackTrace();
        }
        String startDate = "";
        try {
            startDate = getDateTime(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**
         *
         * if database is imported than save a flag for not to sync with online
         */
        if (flag) {
            editor.putBoolean(UtilClass.SYNC_MODE_KEY, false);
            editor.putString(UtilClass.IMPORT_DATE_TIME_KEY, startDate);
        } else
            editor.putBoolean(UtilClass.SYNC_MODE_KEY, true);
        editor.commit();
        return flag;
    }


    private void viewReference() {
        inputUsername = (EditText) findViewById(R.id.user_name);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnClean = (Button) findViewById(R.id.btnClean);
//        tvDeviceId = (TextView) findViewById(R.id.tv_deviceId);

    }

    private void createDeviceIDFile() {
        String macAddress = "";
        macAddress = UtilClass.getDeviceId(LoginActivity.this);
        try {

            FileOutputStream fOut = openFileOutput("EMI.txt", MODE_WORLD_READABLE);
            fOut.write(macAddress.getBytes());
            fOut.close();


            File sd = Environment.getExternalStorageDirectory();                                    // get the internal root directories

            if (sd.canWrite()) {
                File currentDB = new File("/data/data/" + getPackageName() + "/files/EMI.txt");
                File backupDB = new File(sd, "EMI.txt");

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();

                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void setListener() {

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Exit Application?");
                builder.setMessage("Click yes to exit!");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                        finish();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog exitDailog = builder.create();
                exitDailog.show();


            }
        });
       /* btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.selectUploadSyntextRowCount() > 0) {

                    showAlert("There are records not yet Synced. Clean attempt denied");
                } else {


                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete Database?");
                    builder.setMessage("Sure to delete database?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            try {
                                dialog.dismiss();
                                editor.putBoolean(UtilClass.SYNC_MODE_KEY, true);
                                editor.commit();
                                db.deleteUsersWithSelected_LayR4_FDP_Srv_Country();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog exitDailog = builder.create();
                    exitDailog.show();


                }
            }
        });
*/
        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String user_name = "";
                String password = "";


                user_name = inputUsername.getText().toString().trim();
                password = inputPassword.getText().toString().trim();
                // Check for empty data in the form
                if (user_name.trim().length() > 0 && password.trim().length() > 0) {

                    if (db.isValidLocalLogin(user_name, password)) {

                        gotoHomePage();

                    } else {
                        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);
                        if (syncMode) {
                            /**
                             * This block determine is Internet available
                             */
                            isInternetAvailable = cd.isConnectingToInternet();
                            if (isInternetAvailable) {
                                /***
                                 * This if  block determine is there any un-synchronized  data exits in local device
                                 */
                                if (db.selectUploadSyntextRowCount() > 0) {
                                    /**
                                     * This block check the user is country admin or not
                                     * if the the user is country admin or admin
                                     * than the app will be unlocked . but will remain for previous user
                                     */
                                    if (db.isValidAdminLocalLogin(user_name, password)) {
                                        gotoHomePage();
                                    } else {
                                        showAlert(getResources().getString(R.string.unsyn_msg));
                                    }

                                } else {

                                    pDialog = new ProgressDialog(mContext);
                                    pDialog.setCancelable(false);
                                    pDialog.setMessage("Downloading  data .");
                                    pDialog.show();

                                    JSONArray jaary = new JSONArray();
                                    checkLogin(user_name, password, jaary, "4"); // checking online

                                    editor.putInt(UtilClass.OPERATION_MODE, UtilClass.OTHER_OPERATION_MODE);
                                    editor.commit();
                                }


                            } else
                                showAlert("Check your internet connectivity!!");
                        } else {

                            if (db.isValidAdminLocalLogin(user_name, password)) {
                                gotoHomePage();
                            } else {
                                HashMap<String, String> user = db.getUserDetails();
                                showAlert("This device should be operated offline by " + user.get("name"));
                            }


                        }

                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

            }

        });
    }




    private void gotoHomePage() {
        setLogin(true);        // login success

        // Getting local User information
        HashMap<String, String> user = db.getUserDetails();
        setUserName(user.get("name"));                                                              // Setting Username into session
        setStaffID(user.get("code"));                                                               // Setting StaffCode into session
        setUserID(user.get("username"));
        setUserPassword(user.get("password"));

        setUserCountryCode(user.get("c_code"));                                                     // Setting Country Code into session

        /**
         *  Launch main activity
         *  */
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }


    public List<AdmCountryDataModel> insertCountryNameListToSArray() {
        int i;
        countryNameStringArray = new String[countryNameList.size()];

        for (i = 0; i < countryNameList.size(); ++i) {

            AdmCountryDataModel countryItem = countryNameList.get(i);
            countryNameStringArray[i] = countryItem.getAdmCountryName();

        }


        return countryNameList;

    }


    private void getCountryAlert(final String user_name, final String password, final int operationMode) {
        aCountryL_itemsSelected = (ArrayList<AdmCountryDataModel>) insertCountryNameListToSArray();
        if (countryNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select  A Country ");
            builder.setSingleChoiceItems(countryNameStringArray, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    strCountryMode = "";
                    strCountryMode = countryNameStringArray[which];
                }
            });

            builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /**
                     * Clean the table
                     */
                    selectedVillageList.clear();
                    selectedCountryList.clear();

                    if (!strCountryMode.equals("")) {

                        for (int i = 0; i < countryNameStringArray.length; i++) {
                            /**
                             * store the selected country in selectedCountryList
                             */
                            if (countryNameStringArray[i].equals(strCountryMode)) {
                                selectedCountryList.add(aCountryL_itemsSelected.get(i));
                            }
                        }
                        switch (operationMode) {


                            case UtilClass.OTHER_OPERATION_MODE:
                                /**
                                 * only need the json array for put parameter
                                 */

                                JSONArray jaary = new JSONArray();
                                /**
                                 * only for multiple Country Access user
                                 * value will be insert
                                 */
                                db.insertSelectedCountry(selectedCountryList.get(0).getAdmCountryCode(), selectedCountryList.get(0).getAdmCountryName());

                                jaary = UtilClass.jsonConverter(selectedCountryList.get(0).getAdmCountryCode());
                                Log.d(TAG, "jeson to string :" + jaary.toString());
                                /** for Other  MOde*/
                                checkLogin(user_name, password, jaary, "4"); // checking online

                                editor.putInt(UtilClass.OPERATION_MODE, UtilClass.OTHER_OPERATION_MODE);
                                editor.commit();


                                break;
                        }
                    } else {
                        mdialog.dismiss();
                        hideDialog();
                    }

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    aL_itemsSelected.clear();
                    selectedCountryList.clear();
                    dialog.dismiss();
                }
            });
            mdialog = builder.create();
            mdialog.show();
        }
    }


    private void refreshTheActivity() {
        finish();
        startActivity(getIntent());
    }


    /**
     * function to verify login details in mysql db
     */
    public void checkLogin(final String user_name, final String password, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, ALL_DATA);


                String errorResult = response.substring(9, 14);                                 // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY


                boolean error = !errorResult.equals("false");
                if (!error) {


                    downLoadDynamicData(user_name, password, selectedVilJArry, operationMode);


                } else {
                    // Error in login. Invalid UserName or Password
                    hideDialog();
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                    if (pDialog.isShowing())
                        pDialog.dismiss();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_valid_user");
                params.put("user_name", user_name);
                params.put("password", password);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);
                Log.d("TAG1", "params:" + params.toString());
                return params;
            }

        };
        Log.d("TAG1", "strReq:" + strReq.toString());

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public void downLoadDynamicData(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_ass_prog";
        Log.d(TAG, "operationMode: " + operationMode);

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {                                               //IN THIS STRING RESPONSE WRITE THE JSON DATA

                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, DYNAMIC_TABLE);

                Log.d(TAG, " After Loading Dynamic Table in txt last stap :7");


                String errorResult = response.substring(9, 14);                                     //DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY
                boolean error = !errorResult.equals("false");                                       // If Json String  get False than it return false

                if (!error) {


                    downLoadEnuTable(user_Name, pass_word);                                        // IF GET NO ERROR  THAN GOTO THE MAIN ACTIVITY

                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_dynamic_table");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);          // Adding request to request queue
    }


    public void downLoadEnuTable(final String user_Name, final String pass_word) {
        // Tag used to cancel the request
        String tag_string_req = "enu";

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK_ENU, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                AppController.getInstance().getRequestQueue().getCache().clear();  // clear catch
                writeJSONToTextFile(response, ENU_TABLE);

                Log.d(TAG, " After Loading Dynamic Table in txt last stap :7");


                hideDialog();


                /**
                 *  DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY
                 */

                String errorResult = response.substring(9, 14);


                boolean error = !errorResult.equals("false");                                       //If Json String  get False than it return false

                if (!error) {                                                                       // IF GET NO ERROR  THAN GOTO THE MAIN ACTIVITY
                    /**
                     *  Launch main activity
                     */

                    setLogin(true);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    setUserID(user_Name);
                    setUserPassword(pass_word);
                    editor.putBoolean(IS_APP_FIRST_RUN, true);
                    editor.commit();
                    finish();
                    startActivity(intent);
                    // login success

                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
