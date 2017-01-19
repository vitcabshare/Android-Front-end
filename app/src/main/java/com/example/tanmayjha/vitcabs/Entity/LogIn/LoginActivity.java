package com.example.tanmayjha.vitcabs.Entity.LogIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.tanmayjha.vitcabs.Entity.Navigation.HomeActivity;
import com.example.tanmayjha.vitcabs.R;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    TextView skipThisView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        skipThisView=(TextView)findViewById(R.id.skip_this);
        skipThisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

    }

}
