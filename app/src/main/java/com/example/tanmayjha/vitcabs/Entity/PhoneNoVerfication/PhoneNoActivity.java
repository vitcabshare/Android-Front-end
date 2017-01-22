package com.example.tanmayjha.vitcabs.Entity.PhoneNoVerfication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.Control.Font.MontserratTextView;
import com.example.tanmayjha.vitcabs.Entity.LogIn.LoginActivity;
import com.example.tanmayjha.vitcabs.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class PhoneNoActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    TextInputLayout phoneNoLayout;
    MontserratEditText phoneNo;
    MontserratTextView firstName,lastName,email;
    AccountInformation accountInformation;
    GoogleApiClient mGoogleApiClient;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_no);
        getSupportActionBar().setTitle("Verify Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        OptionalPendingResult<GoogleSignInResult> opr =
                Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

        if (opr.isDone()) {
            // Users cached credentials are valid, GoogleSignInResult containing ID token
            // is available immediately. This likely means the current ID token is already
            // fresh and can be sent to your server.
            GoogleSignInResult result = opr.get();
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently and get a valid
            // ID token. Cross-device single sign on will occur in this branch.
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                }
            });
        }

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
                    phoneNoLayout.setError("Enter the phone no first");
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                            }
                        }
                );
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(this);
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(this);
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
