package com.example.schedulechenk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {

    Dialog firstLaunchDialog;

    private static final String PREFERENCES_NAME = "check_launch";

    Button authButton;
    Button questionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        questionButton = findViewById(R.id.authBtn);

        authButton.setOnClickListener(this);
        questionButton.setOnClickListener(this);

        //проверка на первый вход в приложение
        checkFirstLaunch();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.authBtn:
                    Intent intent = new Intent(EnterActivity.this, Daily.class);
                    startActivity(intent);
                break;
        }
    }


    private void checkFirstLaunch() {
        SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);

        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {

            firstLaunchDialogShow();

            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();
        }

    }


    private void firstLaunchDialogShow(){
        Button okDialogButton;

        firstLaunchDialog = new Dialog(EnterActivity.this);

        firstLaunchDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        firstLaunchDialog.setContentView(R.layout.dialog_first_launch);

        firstLaunchDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        firstLaunchDialog.setCancelable(false); //запрет использования кнопки назад


        okDialogButton = firstLaunchDialog.findViewById(R.id.okButton);

        okDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstLaunchDialog.dismiss();
            }
        });

        firstLaunchDialog.show();
    }


}