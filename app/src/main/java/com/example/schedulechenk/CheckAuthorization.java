package com.example.schedulechenk;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

public class CheckAuthorization extends AppCompatActivity {


    private static final String PREFERENCES_2_NAME = "AccountData";
    private static final String PREFERENCES_2_HASVISITED = "state";
    private static final String PREFERENCES_2_KEY_FIO = "FIO";
    private static final String PREFERENCES_2_KEY_GROUP = "Group";



            /*  SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.clear();
                e.apply(); - чистануть файл


                prefs.contains("my_int");-возвращает true false*/



    public boolean checkingUserAuthorization(Context context) {

        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_2_NAME,MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();

        e.putBoolean(PREFERENCES_2_HASVISITED, false);
        e.apply();

        return sp.contains("FIO");
    }


    private void exitFromAccount(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_2_NAME,MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.clear();
        e.apply();
    }

  /*  private void  deleteUserAuthData(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean(PREFERENCES_KEY_NAME, false);
        e.apply();
    }*/
}

