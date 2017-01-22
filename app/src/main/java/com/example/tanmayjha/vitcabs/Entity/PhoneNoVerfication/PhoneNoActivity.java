package com.example.tanmayjha.vitcabs.Entity.PhoneNoVerfication;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.Control.Font.MontserratTextView;
import com.example.tanmayjha.vitcabs.R;

public class PhoneNoActivity extends AppCompatActivity {
    TextInputLayout phoneNoLayout;
    MontserratEditText phoneNo;
    MontserratTextView firstName,lastName,email;
    AccountInformation accountInformation;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_no);
        getActionBar().setTitle("Verify Profile");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        phoneNoLayout=(TextInputLayout)findViewById(R.id.verify_profile_phone_no_layout);
        phoneNo=(MontserratEditText)findViewById(R.id.verify_profile_phone_no);
        firstName=(MontserratTextView)findViewById(R.id.phone_no_first_name);
        lastName=(MontserratTextView)findViewById(R.id.phone_no_last_name);
        email=(MontserratTextView)findViewById(R.id.phone_no_email);
        next=(Button)findViewById(R.id.verify_profile_phone_button_next);

        firstName.setText("First Name: "+accountInformation.getFirstName());
        lastName.setText("Last Name: "+accountInformation.getLastName());
        email.setText("Email: "+accountInformation.getEmail());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phoneNo.getText().toString().trim().isEmpty())
                {
                    phoneNoLayout.setErrorEnabled(true);
                    phoneNoLayout.setError("Enter the text first");
                    if(phoneNo.requestFocus())
                    {
                        //not sure what is the use of below code
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }
                else {
                    phoneNoLayout.setErrorEnabled(false);
                    accountInformation.setPhoneNo(phoneNo.getText().toString());
                    startActivity(new Intent(getApplicationContext(),VerifyCodeActivity.class));
                }
            }
        });
    }
}
