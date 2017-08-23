package com.faisal.technodhaka.dlight.activity;

/**
 * Activity for presenting the Dashboard of the application
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.faisal.technodhaka.dlight.R;
import com.faisal.technodhaka.dlight.controller.AppController;
import com.faisal.technodhaka.dlight.data_model.adapters.DistributionSaveDataModel;
import com.faisal.technodhaka.dlight.fragments.BaseActivity;
import com.faisal.technodhaka.dlight.fragments.ChartFragment;
import com.faisal.technodhaka.dlight.fragments.HomeFragment;
import com.faisal.technodhaka.dlight.fragments.MoviesFragment;
import com.faisal.technodhaka.dlight.fragments.NotificationsFragment;
import com.faisal.technodhaka.dlight.fragments.PhotosFragment;
import com.faisal.technodhaka.dlight.fragments.SettingsFragment;
import com.faisal.technodhaka.dlight.manager.SQLiteHandler;
import com.faisal.technodhaka.dlight.manager.SyncDatabase;
import com.faisal.technodhaka.dlight.network.ConnectionDetector;
import com.faisal.technodhaka.dlight.parse.Parser;
import com.faisal.technodhaka.dlight.utils.CircleTransform;
import com.faisal.technodhaka.dlight.utils.FileUtils;
import com.faisal.technodhaka.dlight.utils.KEY;
import com.faisal.technodhaka.dlight.utils.UtilClass;
import com.faisal.technodhaka.dlight.version.VersionStateChangeReceiver;
import com.faisal.technodhaka.dlight.views.helper.SpinnerHelper;
import com.faisal.technodhaka.dlight.views.notifications.ADNotificationManager;
import com.faisal.technodhaka.dlight.views.notifications.AlertDialogManager;
import com.faisal.technodhaka.dlight.views.notifications.CustomToast;
import com.faisal.technodhaka.dlight.views.spinner.SpinnerLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends BaseActivity  {

    public static final String LIBERIA_COUNTRY_CODE = "0004";
    public static final int TOTAL_NO_OF_TABLE = 88;
    private static ProgressDialog pDialog;

    private final String TAG = MainActivity.class.getSimpleName();


    private SQLiteHandler db;
    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    public static Activity main_activity;
    private View my_view;

    AlertDialogManager alert;


    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
//    private Button btnDynamicData;

    //    private Button btnNewReg;
//    private Button btnSummaryRep;
    ///private Button btnViewRec;
    private Button btnSyncRec;
    private SQLiteHandler sqlH;
    private Spinner spCountry;
    private String idCountry;
    private String strCountry;
    private static final String Y = "YES";
    private static final String N = "NO";


    private ProgressDialog progressDialog;

    private TextView tvLastSync, tvSyncRequired, tvOperationMode, tvDeviceId;
    private Context mContext;

    private int progressIncremental;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName1, txtWebsite;
    private Toolbar toolbar;
    //    private FloatingActionButton fab;
    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "https://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_CHART = "chart";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        fab = (FloatingActionButton) findViewById(R.id.fab);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);

        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

/*        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

        main_activity = this;
        mContext = this;
        spCountry = (Spinner) findViewById(R.id.spDCountry);// add the spinner
        sqlH = new SQLiteHandler(this); //  it should be other wise it will show null point Exception


        db = new SQLiteHandler(getApplicationContext());                                            // SqLite database handler


        cd = new ConnectionDetector(getApplicationContext());                                        // connection manager

        viewReference();                                                                            // find View by ID for all Views


        SharedPreferences settings;

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFirstRun = settings.getBoolean(IS_APP_FIRST_RUN, false);

       /* showOperationModelLabel(settings);*/
        txtName.setText(getUserName());
        tvLastSync.setText(db.getLastSyncStatus());


        String deviceId = UtilClass.getDeviceId(mContext);                                      // get mac address

        tvDeviceId.setText(deviceId);


        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });


        buttonSetListener();
        // when the MainActivity run for first time  The JSon Data inject to the
        // SQLite database from text file
        if (isFirstRun) {
            SharedPreferences.Editor editor;
            settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            editor = settings.edit();
            new Inject_All_DataIntoSQLite().execute();
            editor.putBoolean(IS_APP_FIRST_RUN, false);
            editor.apply();
        }
        loadCountry();
 /*       setAllButtonDisabled();
        viewAccessController();*/
        //showOperationModelLabel(settings);


//       String versionName= VersionUtils.getVersionName(getApplicationContext());                  delete it

        callBroadCastReceiverToCheck();


    }                                                                                               // end of onCreate

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        txtName.setText(getUserName());
        txtWebsite.setText("www.androidhive.info");

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

        // showing dot next to notifications label
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {

        selectNavMenu();                                                                              // selecting appropriate nav menu item

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
//            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
//        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // photos
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;
            case 2:
                // movies fragment
                MoviesFragment moviesFragment = new MoviesFragment();
                return moviesFragment;
            case 3:
                // notifications fragment
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                return notificationsFragment;

            case 4:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;

            case 5:
                // settings fragment
                ChartFragment charFragment = new ChartFragment();
                return charFragment;
            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;

               /*     case R.id.nav_chart:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_CHART;
                        break;*/

                    case R.id.nav_about_us:

                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));        // launch new intent instead of loading fragment
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:

                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));   // launch new intent instead of loading fragment
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_dynamic:

                        Intent iDynamicData = new Intent(getApplicationContext(), DynamicTable.class); // launch new intent instead of loading fragment
                        iDynamicData.putExtra(KEY.COUNTRY_ID, idCountry);
                        startActivity(iDynamicData);
                        return true;

                    case R.id.nav_chart:

                        Intent iChartActivity = new Intent(getApplicationContext(), ChartActivity.class); // launch new intent instead of loading fragment
                        iChartActivity.putExtra(KEY.COUNTRY_ID, idCountry);
                        startActivity(iChartActivity);
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_logout) {
//            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
//            return true;
//        }

        // user is in notifications fragment
        // and selected 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        // user is in notifications fragment
        // and selected 'Clear All'
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // show or hide the fab
   /* private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }*/


    private void callBroadCastReceiverToCheck() {
        Intent registerBroadcast = new Intent(this, VersionStateChangeReceiver.class);
        sendBroadcast(registerBroadcast);
    }

    void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();

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

    /**
     * this method bring the database front Internal memory
     */
    public void newBackupDBFromApp() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            //   File data = Environment.getDataDirectory();

            String dbBy = getStaffID();
            String dbByName = getUserName();
            String backupDate = getDateTime();
            String backupdbName = "G_path_" + dbBy + "-" + dbByName + "_" + backupDate + ".db";

            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + getPackageName() + "/databases/pci";
                String backupDBPath = backupdbName;
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    Toast.makeText(getApplicationContext(), "Import Successful! " + backupDB.getAbsolutePath(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Backup Failed!", Toast.LENGTH_SHORT)
                    .show();

        }
    }


    /**
     * <p>
     * This method control the button view with respect to operation
     * </p>
     */

    private void viewAccessController() {
        // get operation mode from shared and preference
//        int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);

        String operationModeName = sqlH.getDeviceOperationModeName();
        switch (operationModeName) {
            case UtilClass.REGISTRATION_OPERATION_MODE_NAME:

            case UtilClass.DISTRIBUTION_OPERATION_MODE_NAME:

            case UtilClass.SERVICE_OPERATION_MODE_NAME:

            case UtilClass.TRANING_N_ACTIVITY_OPERATION_MODE_NAME:
            case UtilClass.OTHER_OPERATION_MODE_NAME:
//                btnDynamicData.setEnabled(true);


                break;


        }
    }


    private void buttonSetListener() {


        btnSyncRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                boolean syncProcessOnGoing = settings.getBoolean(UtilClass.PROCESS_ON_GOING_KEY, false);
                if (!syncProcessOnGoing)
                    synchronizationProcess(btnSyncRec);
            }
        });


//        btnDynamicData.setOnClickListener(this);


    }

    /**
     * <p>
     * set all button disable first expect syn & summary report button
     * </p>
     */
    private void setAllButtonDisabled() {


//        btnDynamicData.setEnabled(false);


    }

    private void viewReference() {


        txtName = (TextView) findViewById(R.id.user_name);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        //
        tvLastSync = (TextView) findViewById(R.id.tv_last_sync);
        tvSyncRequired = (TextView) findViewById(R.id.tv_sync_required);

        btnSyncRec = (Button) findViewById(R.id.btnSyncRecord);

        tvLastSync = (TextView) findViewById(R.id.tv_last_sync);

//        btnDynamicData = (Button) findViewById(R.id.btnDynamicData);
        tvOperationMode = (TextView) findViewById(R.id.tv_operation_mode);

        tvDeviceId = (TextView) findViewById(R.id.tv_deviceId);


        if (db.selectUploadSyntextRowCount() > 0) {
            tvSyncRequired.setText(Y);
        } else {
            tvSyncRequired.setText(N);
        }

    }

/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnSyncRecord:




                break;


 *//*           case R.id.btnDynamicData:
                finish();
                Intent iDynamicData = new Intent(getApplicationContext(), DynamicTable.class);
                iDynamicData.putExtra(KEY.COUNTRY_ID, idCountry);
                startActivity(iDynamicData);
                break;*//*

        }

    }*/

    private void synchronizeData(View v) {
        final AppController globalVariable = (AppController) getApplicationContext();
        globalVariable.setMainViewContext(v);
        MainTask main_task = new MainTask();
        main_task.execute();
    }

    private void synchronizationProcess(View v) {
        /**
         * check the on line mode is on or not
         */
        SharedPreferences settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);
        if (syncMode) {
            my_view = v;
            isInternetPresent = cd.isConnectingToInternet();

            if (isInternetPresent) {
                synchronizeData(my_view);

                /**
                 * save the synchronize time
                 */
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                Date now = new Date();
                String SyncDate = date.format(now);
                db.insertIntoLastSyncTraceStatus(getUserID(), getUserName(), SyncDate);

                tvSyncRequired.setText(N);
                if (db.getLastSyncStatus().equals("")) {
                    tvLastSync.setText("N/A");
                } else {
                    tvLastSync.setText(db.getLastSyncStatus());
                }

            } else {

                showAlert("Check your internet connectivity!!");
            }

        } else
            showAlert("Device is not configure for internet connectivity !!");
    }

    private class MainTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                //doSomeTasks();
            } catch (Exception e) {
                Log.e("Async task", "Task Failed " + e);
            }
            return "Success";
        }

        @Override
        protected void onPreExecute() {
            SyncDatabase sync = new SyncDatabase(getApplicationContext(), main_activity);
            sync.startTask();
        }


        @Override
        protected void onPostExecute(String result) {
            //finalizeSync();
            Log.d(TAG, "Task Finish");
            //SyncDatabase.pDialog.dismiss();
        }


    } // end Asynchronous class MainTask


    /**
     * LOAD :: COUNTRY
     */
    private void loadCountry() {

        int operationMode = sqlH.getDeviceOperationModeCode();


        SpinnerLoader.loadCountryLoader(mContext, sqlH, spCountry, operationMode, idCountry, strCountry);
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCountry = ((SpinnerHelper) spCountry.getSelectedItem()).getValue();
                idCountry = ((SpinnerHelper) spCountry.getSelectedItem()).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    private class Inject_DynamicTableIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Inject_EnuTableIntoSQLite().execute();

        }

        @Override
        protected Void doInBackground(Void... params) {


            /**
             * Read JSON DATA  from the text file
             * */
            String retrieveData = readDataFromFile(LoginActivity.DYNAMIC_TABLE);

            try {

                /**                 * The total string Convert into JSON object                 * */

                JSONObject jObj = new JSONObject(retrieveData);

                publishProgress(++progressIncremental);

                if (!jObj.isNull("D_T_answer")) {
                    Parser.DTA_Parser(jObj.getJSONArray("D_T_answer"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_basic")) {
                    Parser.DTBasicParser(jObj.getJSONArray("D_T_basic"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_category")) {
                    Parser.DTCategoryParser(jObj.getJSONArray("D_T_category"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_CountryProgram")) {
                    Parser.DTCountryProgramParser(jObj.getJSONArray("D_T_CountryProgram"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_GeoListLevel")) {
                    Parser.DTGeoListLevelParser(jObj.getJSONArray("D_T_GeoListLevel"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_QTable")) {
                    Parser.DTQTableParser(jObj.getJSONArray("D_T_QTable"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_QResMode")) {
                    Parser.DTQResModeParser(jObj.getJSONArray("D_T_QResMode"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_ResponseTable")) {
                    Parser.DTResponseTableParser(jObj.getJSONArray("D_T_ResponseTable"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_TableDefinition")) {
                    Parser.DTTableDefinitionParser(jObj.getJSONArray("D_T_TableDefinition"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_TableListCategory")) {
                    Parser.DTTableListCategoryParser(jObj.getJSONArray("D_T_TableListCategory"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_LUP")) {
                    Parser.DTLUPParser(jObj.getJSONArray("D_T_LUP"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("DTA_Skip_Table")) {
                    Parser.DTA_Skip_TableParser(jObj.getJSONArray("DTA_Skip_Table"), db);
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }


    private class Inject_EnuTableIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideDialog();
            loadCountry();

            // set the user name
            txtName.setText(getUserName());
        }

        @Override
        protected Void doInBackground(Void... params) {


            /**             * Read JSON DATA  from the text file             **/
            String retrieveData = readDataFromFile(LoginActivity.ENU_TABLE);

            try {


                /**                 * The total string Convert into JSON object                 * */

                JSONObject jObj = new JSONObject(retrieveData);
                publishProgress(++progressIncremental);

                if (!jObj.isNull("enu_table")) {
                    Parser.DTEnu_Parser(jObj.getJSONArray("enu_table"), db);
                }

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }


    private class Inject_All_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /**
             * Read JSON DATA  from the text file
             * */
            String retrieveData = readDataFromFile(LoginActivity.ALL_DATA);

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

                setUserName(first_name); // Setting User hhName into session
                setStaffID(user_id); // Setting Staff ID to use when sync data


                setUserCountryCode(country_code); // Setting Country code


                db.addUser(user_id, country_code, login_name, login_pw, first_name, last_name, email, email_verification, user_status, entry_by, entry_date);
//                Log.d("MOR_12",user_id +  country_code +  login_name +  login_pw +  first_name +  last_name +  email +  email_verification +  user_status +  entry_by +  entry_date);


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


                if (!jObj.isNull(Parser.STAFF_FDP_ACCESS_JSON_A)) {
                    JSONArray staff_fdp_accesses = jObj.getJSONArray(Parser.STAFF_FDP_ACCESS_JSON_A);
                    size = staff_fdp_accesses.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject staff_fdp_access = staff_fdp_accesses.getJSONObject(i);

                        String StfCode = staff_fdp_access.getString(Parser.STF_CODE);
                        String AdmCountryCode = staff_fdp_access.getString(Parser.ADM_COUNTRY_CODE);
                        String FDPCode = staff_fdp_access.getString(Parser.FDP_CODE);
                        String btnNew = staff_fdp_access.getString(Parser.BTN_NEW);
                        String btnSave = staff_fdp_access.getString(Parser.BTN_SAVE);
                        String btnDel = staff_fdp_access.getString(Parser.BTN_DEL);


                        db.addStaffFDPAccess(StfCode, AdmCountryCode, FDPCode, btnNew, btnSave, btnDel);


                    }
                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull(Parser.FDP_MASTER_JSON_A)) {
                    JSONArray fdp_masters = jObj.getJSONArray(Parser.FDP_MASTER_JSON_A);
                    size = fdp_masters.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject fdp_master = fdp_masters.getJSONObject(i);

                        String AdmCountryCode = fdp_master.getString(Parser.ADM_COUNTRY_CODE);
                        String FDPCode = fdp_master.getString(Parser.FDP_CODE);
                        String FDPName = fdp_master.getString(Parser.FDP_NAME);
                        String FDPCatCode = fdp_master.getString(Parser.FDP_CAT_CODE);
                        String WHCode = fdp_master.getString(Parser.WH_CODE);
                        String LayR1Code = fdp_master.getString(Parser.LAY_R_1_CODE);
                        String LayR2Code = fdp_master.getString(Parser.LAY_R_2_CODE);


                        db.addFDPMaster(AdmCountryCode, FDPCode, FDPName, FDPCatCode, WHCode, LayR1Code, LayR2Code);


                    }
                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull("distbasic_table")) {
                    JSONArray distbasic_table = jObj.getJSONArray("distbasic_table");
                    size = distbasic_table.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject distbasic = distbasic_table.getJSONObject(i);
                        // DistributionSaveDataModel data = new DistributionSaveDataModel();

                        String AdmCountryCode = distbasic.getString(Parser.ADM_COUNTRY_CODE);
                        String AdmDonorCode = distbasic.getString(Parser.ADM_DONOR_CODE);
                        String AdmAwardCode = distbasic.getString(Parser.ADM_AWARD_CODE);
                        String ProgCode = distbasic.getString(Parser.PROG_CODE);
                        String OpCode = distbasic.getString("OpCode");
                        String SrvOpMonthCode = distbasic.getString("SrvOpMonthCode");
                        String DisOpMonthCode = distbasic.getString("DisOpMonthCode");
                        String FDPCode = distbasic.getString(Parser.FDP_CODE);
                        String DistFlag = distbasic.getString("DistFlag");
                        ///   String FoodFlag = distbasic.getString("FoodFlag");
                        String OrgCode = distbasic.getString("OrgCode");
                        String Distributor = distbasic.getString("Distributor");
                        String DistributionDate = distbasic.getString("DistributionDate");
                        String DeliveryDate = distbasic.getString("DeliveryDate");
                        String Status = distbasic.getString("Status");
                        String PreparedBy = distbasic.getString("PreparedBy");
                        String VerifiedBy = distbasic.getString("VerifiedBy");
                        String ApproveBy = distbasic.getString("ApproveBy");


                        //data.setDistStatus(distribution_ext_tableData.getString(DIST_STATUS);

                        db.addInDistributionNPlaneTable(AdmCountryCode, AdmDonorCode, AdmAwardCode, ProgCode,
                                OpCode, SrvOpMonthCode, DisOpMonthCode, FDPCode, DistFlag, OrgCode, Distributor,
                                DistributionDate, DeliveryDate, Status, PreparedBy, VerifiedBy, ApproveBy);


                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(Parser.LUP_SRV_OPTION_LIST)) {
                    JSONArray lup_srv_option_listDatas = jObj.getJSONArray(Parser.LUP_SRV_OPTION_LIST);
                    size = lup_srv_option_listDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_srv_option_listData = lup_srv_option_listDatas.getJSONObject(i);

                        String countryCode = lup_srv_option_listData.getString(Parser.ADM_COUNTRY_CODE);

                        String programCode = lup_srv_option_listData.getString(Parser.PROG_CODE);
                        String serviceCode = lup_srv_option_listData.getString(Parser.SRV_CODE);
                        String LUPOptionCode = lup_srv_option_listData.getString(Parser.LUP_OPTION_CODE);
                        String LUPOptionName = lup_srv_option_listData.getString(Parser.LUP_OPTION_NAME);


                        db.addInLupSrvOptionListFromOnline(countryCode, programCode, serviceCode, LUPOptionCode, LUPOptionName);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }

                publishProgress(++progressIncremental);


                if (!jObj.isNull("vo_itm_table")) {
                    JSONArray vo_itm_tableDatas = jObj.getJSONArray("vo_itm_table");
                    size = vo_itm_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject vo_itm_tableData = vo_itm_tableDatas.getJSONObject(i);
                        //AGR_DataModel data = new AGR_DataModel();
                        String CatCode = vo_itm_tableData.getString("CatCode");
                        String ItmCode = vo_itm_tableData.getString("ItmCode");
                        String ItmName = vo_itm_tableData.getString("ItmName");


                        db.addVoucherItemTableFromOnline(CatCode, ItmCode, ItmName);

//                        Log.d(TAG, "In Voucher item table : CatCode : " + CatCode + " ItmCode : " + ItmCode + " ItmName : " + ItmName);

                    }
                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull(Parser.VO_ITM_MEAS_TABLE_JSON_A)) {
                    JSONArray vo_itm_meas_tableDatas = jObj.getJSONArray(Parser.VO_ITM_MEAS_TABLE_JSON_A);
                    size = vo_itm_meas_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject vo_itm_meas_tableData = vo_itm_meas_tableDatas.getJSONObject(i);
                        //AGR_DataModel data = new AGR_DataModel();
                        String MeasRCode = vo_itm_meas_tableData.getString("MeasRCode");
                        String UnitMeas = vo_itm_meas_tableData.getString("UnitMeas");
                        String MeasTitle = vo_itm_meas_tableData.getString("MeasTitle");


                        db.addVoucherItemMeasFromOnline(MeasRCode, UnitMeas, MeasTitle);

//                        Log.d(TAG, "In Voucher item table : MeasRCode : " + MeasRCode + " UnitMeas : " + UnitMeas + " MeasTitle : " + MeasTitle);

                    }
                }

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
                        //AGR_DataModel data = new AGR_DataModel();
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
                       /* Log.d("NIR2", "addLUP_GPS_TableFromOnline : GrpCode : " + GrpCode + " SubGrpCode : "
                                + SubGrpCode + " AttributeCode : " + AttributeCode
                                + " LookUpCode : " + LookUpCode + " LookUpName : " + LookUpName
                        );*/

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
                     /*   Log.d("NIR2", "addGPS_SubGroupAttributesFromOnline : AdmCountryCode : " + AdmCountryCode
                                + " GrpCode : " + GrpCode + " SubGrpCode : " + SubGrpCode
                                + " LocationCode : " + LocationCode + " AttributeCode : " + AttributeCode

                                + " AttributeValue : " + AttributeValue
                        );*/

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

/*
                        Log.d("InTest", "AdmCountryCode:" + AdmCountryCode + "RegNAddLookupCode" + RegNAddLookupCode
                                + "RegNAddLookup: " + RegNAddLookup + "LayR1ListCode : " + LayR1ListCode + "LayR2ListCode:" + LayR2ListCode
                                + " LayR3ListCode: " + LayR3ListCode + "LayR4ListCode:" + LayR4ListCode);*/
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
//                        Log.d(TAG, "OrgNName:" + OrgNName + "OrgNShortName:" + OrgNShortName);
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
            showProgressDialog();
            progressIncremental = 0;
        }

        @Override
        protected void onPostExecute(Void string) {

            new Inject_DynamicTableIntoSQLite().execute();

        }
    }

    private void hideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Read JSON DATA Insert into SQLite
     */
    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(TOTAL_NO_OF_TABLE);
        progressDialog.setMessage("Retrieving...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


}
