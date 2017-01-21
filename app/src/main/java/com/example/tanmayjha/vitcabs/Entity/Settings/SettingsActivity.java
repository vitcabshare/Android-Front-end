package com.example.tanmayjha.vitcabs.Entity.Settings;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.R;

public class SettingsActivity extends AppCompatActivity {

    Switch lastNameSwitch,emailSwitch,phoneNoSwitch;
    AccountInformation accountInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        lastNameSwitch=(Switch)findViewById(R.id.add_a_trip_switch_last_name);
        emailSwitch=(Switch)findViewById(R.id.add_a_trip_switch_email);

        if(!accountInformation.getLastName().trim().isEmpty()) {
            emailSwitch.setText("Show my email(" + accountInformation.getEmail() + ")to other?");
            lastNameSwitch.setText("Show my last name(" + accountInformation.getLastName() + ")?");
        }

    }

}
