package com.example.tanmayjha.vitcabs.Entity.AddATrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Control.Font.MontserratTextView;
import com.example.tanmayjha.vitcabs.Entity.Navigation.HomeActivity;
import com.example.tanmayjha.vitcabs.R;

public class AddATripResultActivity extends AppCompatActivity {

    MontserratTextView from,to,date,time,flightNo,emailVisibility,phoneNoVisibility,lastNameVisibility;
    AccountInformation accountInformation;
    Button backButton,confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_atrip_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        from = (MontserratTextView) findViewById(R.id.add_a_trip_result_from);
        to = (MontserratTextView) findViewById(R.id.add_a_trip_result_to);
        date = (MontserratTextView) findViewById(R.id.add_a_trip_result_date_of_travel);
        time = (MontserratTextView) findViewById(R.id.add_a_trip_result_time_of_travel);
        flightNo = (MontserratTextView) findViewById(R.id.add_a_trip_result_flight_no);
        emailVisibility = (MontserratTextView) findViewById(R.id.add_a_trip_result_email_visibility);
        phoneNoVisibility = (MontserratTextView) findViewById(R.id.add_a_trip_result_phone_no_visibility);
        lastNameVisibility = (MontserratTextView) findViewById(R.id.add_a_trip_result_last_name_visibility);

        backButton=(Button)findViewById(R.id.add_a_trip_result_button_back);
        confirmButton=(Button)findViewById(R.id.add_a_trip_result_button_confirm);

        from.setText("From: " + getIntent().getStringExtra("fromLocation"));
        to.setText("To: " + getIntent().getStringExtra("toLocation"));
        date.setText("Date: " + getIntent().getStringExtra("date"));
        time.setText("Time: " + getIntent().getStringExtra("time"));
        flightNo.setText("Flight/Train no: " + getIntent().getStringExtra("flighno"));
        if (accountInformation.isEmailEnabled()) {
            emailVisibility.setText("Email(" + accountInformation.getEmail() + ") will be visible to others");
        }
        else
            emailVisibility.setText("Email(" + accountInformation.getEmail() + ") will not be visible to others");
        if(accountInformation.isPhoneNoEnabled())
        {
            phoneNoVisibility.setText("Phone no("+accountInformation.getPhoneNo()+") will be visible to others.");
        }
        else {
            phoneNoVisibility.setText("Phone no("+accountInformation.getPhoneNo()+") will not be visible to others.");
        }
        if(accountInformation.isLastNameEnabled())
        {
            lastNameVisibility.setText("Last Name("+accountInformation.getLastName()+") will be visible to others");
        }
        else{
            lastNameVisibility.setText("Last Name("+accountInformation.getLastName()+") will not be visible to others");
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Request Created.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                onBackPressed();
//                Intent intent = new Intent(this, HomeActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
