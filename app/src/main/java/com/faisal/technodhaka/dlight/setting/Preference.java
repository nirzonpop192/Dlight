package com.faisal.technodhaka.dlight.setting;

import android.content.Context;
import android.content.SharedPreferences;

import com.faisal.technodhaka.dlight.fragments.BaseActivity;
import com.faisal.technodhaka.dlight.utils.UtilClass;

/**
 * Created by TD-Android on 7/16/2017.
 */

public class Preference {

    public static boolean getSyncMode(Context context) {
        SharedPreferences settings = context.getSharedPreferences(BaseActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);

        return syncMode;
    }
}
