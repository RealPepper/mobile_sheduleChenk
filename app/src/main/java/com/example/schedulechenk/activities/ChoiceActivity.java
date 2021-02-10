package com.example.schedulechenk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.schedulechenk.adapters.ComplexAdapter;
import com.example.schedulechenk.databinding.ActivityChoiseComplexBinding;

import com.example.schedulechenk.R;
import com.example.schedulechenk.models.ComplexModel;
import com.example.schedulechenk.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    //initialization
    private static final String PREFERENCES_NAME = "check_launch";

    Button authButton;
    Button questionButton;

    Dialog firstLaunchDialog;

    private ActivityChoiseComplexBinding activityChoiseComplexBinding;
    private List<ComplexModel> complexModels = new ArrayList<>();
    private ComplexAdapter complexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise_complex);

        questionButton = findViewById(R.id.authBtn);

        questionButton.setOnClickListener(this);

        //проверка на первый вход в приложение
        //checkFirstLaunch();

        activityChoiseComplexBinding = DataBindingUtil.setContentView(this, R.layout.activity_choise_complex);
        complexModels = new Parser().getComplexWeb();
        doInitialization();
    }

    //initialization choice complex recycler
    private void doInitialization() {

        activityChoiseComplexBinding.complexListRecyclerView.setHasFixedSize(true);
        complexAdapter = new ComplexAdapter(complexModels);
        activityChoiseComplexBinding.complexListRecyclerView.setAdapter(complexAdapter);

        complexAdapter.notifyDataSetChanged();
    }
    //////////////////////////////

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.authBtn:
                    Intent intent = new Intent(EnterActivity.this, Daily.class);
                    startActivity(intent);
                break;*/
        }
    }


    private void checkFirstLaunch() {
        SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {

            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();
        }

    }


}