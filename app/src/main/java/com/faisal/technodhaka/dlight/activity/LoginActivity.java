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
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.faisal.technodhaka.dlight.R;
import com.faisal.technodhaka.dlight.controller.AppConfig;
import com.faisal.technodhaka.dlight.controller.AppController;


import com.faisal.technodhaka.dlight.fragments.BaseActivity;
import com.faisal.technodhaka.dlight.database.SQLiteHandler;
import com.faisal.technodhaka.dlight.network.ConnectionDetector;
import com.faisal.technodhaka.dlight.utils.UtilClass;
import com.faisal.technodhaka.dlight.views.notifications.AlertDialogManager;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//import java.util.logging.Handler;


public class LoginActivity extends BaseActivity {
    // LogCat tag
    private static final String TAG = LoginActivity.class.getSimpleName();

    public static final String ALL_DATA = "all_data";
    public static final String DYNAMIC_TABLE = "dynamic_table";
    public static final String ENU_TABLE = "enu_table";


    int imageA[] = {R.drawable.cap_01, R.drawable.cap_02, R.drawable.cap_03, R.drawable.cap_04,
            R.drawable.cap_05, R.drawable.cap_06, R.drawable.cap_07, R.drawable.cap_08, R.drawable.cap_09, R.drawable.cap_10};// new int[10];
    int imgNumber[] = {856, 780, 985, 451, 811, 312, 337, 318, 145, 327};

    // Login Button
    private Button btnLogin;


    private EditText edtPinNumaber, edtImgNumber;


    private SQLiteHandler db;
    // connection Detector
    ConnectionDetector cd;
    // flag for connectivity
    Boolean isInternetAvailable = false;
    //alert Dialog Manager
    AlertDialogManager alert;

    private AppConfig ac;                                                                             //Application configuration
    //progress mDialog
    private ProgressDialog pDialog;
    // size  = 0, int type
//    private int size = 0;
    //mContext
    private final Context mContext = LoginActivity.this;
    //exit button
    private Button/* btnExit,*/ btnClean;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    private Handler barPDialogHandler;
    private ImageView iViewCapture;

    // create random object
    private Random randomObject = new Random();
    int randomNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout._1_activity_login);
        barPDialogHandler = new Handler();

//        imageA[0] = R.drawable.cap_01;
        viewReference();
        /**
         * Initialize Button and Input Boxes
         */


        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = settings.edit(); //2


        pDialog = new ProgressDialog(this);                                                         // Progress mDialog
        pDialog.setCancelable(false);

        db = new SQLiteHandler(getApplicationContext());                                             // SQLite database handler

        cd = new ConnectionDetector(getApplicationContext());                                       // connectivity manager

        setListener();
        createDeviceIDFile();
        iAmNotRobotProcess();

    }

    private void iAmNotRobotProcess() {
        randomNumber = randomObject.nextInt(9);
        iViewCapture.setImageResource(imageA[randomNumber]);
    }


    private void viewReference() {

        iViewCapture = (ImageView) findViewById(R.id.iv_imageCapture);
        btnLogin = (Button) findViewById(R.id.btnLogin);
//        btnExit = (Button) findViewById(R.id.btnExit);
        btnClean = (Button) findViewById(R.id.btnClean);
        edtPinNumaber = (EditText) findViewById(R.id.edtPinNumber);
        edtImgNumber = (EditText) findViewById(R.id.edtImg);


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


        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     if (db.selectUploadSyntextRowCount() > 0) {
                    //Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                    showAlert("There are records not yet Synced. Clean attempt denied");
                } else {*/


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
//            }
        });

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                String pinNumber;

                pinNumber = edtPinNumaber.getText().toString().trim();

                // Check for empty data in the form
                if (pinNumber.trim().length() > 0) {

                    if (false/*db.isValidLocalLogin(pinNumber, password)*/) {

                        gotoHomePage();

                    } else {
                        /**
                         * online & off line process
                         */
                        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);
                        if (syncMode) {

                            if (imgNumber[randomNumber] == Integer.parseInt(edtImgNumber.getText().toString())) {
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
                                        if (false/*db.isValidAdminLocalLogin(user_name, password)*/) {
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
                                        checkLogin(pinNumber); // checking online

                                        editor.putInt(UtilClass.OPERATION_MODE, UtilClass.OTHER_OPERATION_MODE);
                                        editor.commit();
                                    }


                                } else
                                    showAlert("Check your internet connectivity!!");
                            } else {
                                showAlert("Ding Ding !");
                            }

                        } else {

                            if (false/*db.isValidAdminLocalLogin(user_name, password)*/) {
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

    }


    /**
     * function to verify login details in mysql db
     */
    public void checkLogin(final String pinNumber) {
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


                    downLoadDynamicData(pinNumber);


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
                params.put("pin_number", pinNumber);

                Log.d("TAG1", "params:" + params.toString());
                return params;
            }

        };
        Log.d("TAG1", "strReq:" + strReq.toString());

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public void downLoadDynamicData(final String pin_number) {
        // Tag used to cancel the request
        String tag_string_req = "req_ass_prog";


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


                    downLoadEnuTable(pin_number);                                                   // IF GET NO ERROR  THAN GOTO THE MAIN ACTIVITY

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
                // hide the mDialog
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
                params.put("pin_number", pin_number);


                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);          // Adding request to request queue
    }


    public void downLoadEnuTable(final String pin_number) {
        // Tag used to cancel the request
        String tag_string_req = "enu";

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                AppController.getInstance().getRequestQueue().getCache().clear();  // clear catch
                writeJSONToTextFile(response, ENU_TABLE);

                Log.d(TAG, " After Loading Dynamic Table in txt last stap :7");


                hideDialog();


                String errorResult = response.substring(9, 14);


                boolean error = !errorResult.equals("false");                                       //If Json String  get False than it return false

                if (!error) {                                                                       // IF GET NO ERROR  THAN GOTO THE MAIN ACTIVITY
                    /**
                     *  Launch main activity
                     */

                    setLogin(true);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    setPinNumber(pin_number);
                    setIsAppFisrtRun(true);

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
                // hide the mDialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        }) {


            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_enu_table");
                params.put("pin_number", pin_number);


                return params;
            }


        };

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
