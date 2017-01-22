package com.example.tanmayjha.vitcabs.Entity.PhoneNoVerfication;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.Entity.Navigation.HomeActivity;
import com.example.tanmayjha.vitcabs.R;

public class VerifyCodeActivity extends AppCompatActivity {
    TextInputLayout code_layout;
    MontserratEditText code;
    Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
    }
    @Override
    public void onStart()
    {
        super.onStart();
        code_layout=(TextInputLayout)findViewById(R.id.verify_code_layout);
        code=(MontserratEditText)findViewById(R.id.verify_code);
        verify=(Button)findViewById(R.id.verify_code_verify);
        getSupportActionBar().setTitle("Verification");
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(code.getText().toString().trim().isEmpty())
                {
                    code_layout.setErrorEnabled(true);
                    code_layout.setError("Enter the code first");
                    if(code.requestFocus())
                    {
                        //not sure what is the use of below code
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }
                else {
                    code_layout.setErrorEnabled(false);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }
        });
    }
}
