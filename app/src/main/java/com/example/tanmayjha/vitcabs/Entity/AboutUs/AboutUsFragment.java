package com.example.tanmayjha.vitcabs.Entity.AboutUs;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {
    TextInputLayout feedbackLayout;
    MontserratEditText feedbackEditText;
    Button submit;
    Spinner feedbackSpinnerOption;
    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart(){
        super.onStart();
        View view=getView();
        feedbackLayout=(TextInputLayout)view.findViewById(R.id.input_feedback);
        feedbackEditText=(MontserratEditText)view.findViewById(R.id.feedback);
        feedbackSpinnerOption=(Spinner)view.findViewById(R.id.spinner_feedback_option);
        submit=(Button)view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedbackEditText.getText().toString().trim().isEmpty())
                {
                    // Whether the error functionality is enabled or not in this layout.
                    // Enabling this functionality before setting an error message via
                    // setError(CharSequence), will mean that this layout will not
                    // change size when an error is displayed.
                    // But i don't know why its not working here.
                    feedbackLayout.setErrorEnabled(true);
                    feedbackLayout.setError("Enter the text first");
                    //feedback.requestFocus will give focus to Edit Text.
                    // Like the | will start blinking on it. Though keyboard
                    // will not show only by this single code.
                    if(feedbackEditText.requestFocus())
                    {
                        //not sure what is the use of below code
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }
                else
                {
                    //This removes the error idk why and wtf is happening
                    //According to docs, it should be used to specify that
                    //the layout hasn't got any space to display error.
                    //So, it would expand view on its own. However, here is
                    //is functioning to remove error.
                    feedbackLayout.setErrorEnabled(false);
                    String subject=String.valueOf(feedbackSpinnerOption.getSelectedItem());
                    Intent intentEmail = new Intent(Intent.ACTION_SEND);
                    String message=feedbackEditText.getText().toString();
                    intentEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"tanmay.jha1@gmail.com"});
                    intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intentEmail.putExtra(Intent.EXTRA_TEXT, message);
                    intentEmail.setType("message/rfc822");
                    startActivity(Intent.createChooser(intentEmail, "Choose an Email client :"));
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

}
