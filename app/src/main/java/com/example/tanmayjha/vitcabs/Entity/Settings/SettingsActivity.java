package com.example.tanmayjha.vitcabs.Entity.Settings;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Entity.Navigation.HomeActivity;
import com.example.tanmayjha.vitcabs.Entity.PhoneNoVerfication.PhoneNoActivity;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        lastNameSwitch=(Switch)findViewById(R.id.settings_switch_last_name);
        emailSwitch=(Switch)findViewById(R.id.settings_switch_email);
        phoneNoSwitch=(Switch)findViewById(R.id.settings_switch_phone_no);

        if(!accountInformation.getLastName().trim().isEmpty()) {
            emailSwitch.setText("Show my email(" + accountInformation.getEmail() + ")to other?");
            lastNameSwitch.setText("Show my last name(" + accountInformation.getLastName() + ")?");
            phoneNoSwitch.setText("Show my phone no("+accountInformation.getPhoneNo()+")?");
        }

        emailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    accountInformation.setEmailEnabled(true);
                }
                else
                {
                    accountInformation.setEmailEnabled(false);
                }
            }
        });

        lastNameSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    accountInformation.setLastNameEnabled(true);
                }
                else
                {
                    accountInformation.setLastNameEnabled(false);
                }
            }
        });

        phoneNoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    accountInformation.setPhoneNoEnabled(true);
                }
                else
                {
                    accountInformation.setPhoneNoEnabled(false);
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

