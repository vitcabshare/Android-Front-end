package com.example.tanmayjha.vitcabs.Entity.ShowAllTravellers;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowCustomDateTimeTravellers extends Fragment {

    private Calendar myCalendar;
    TextInputLayout dateLayout,timeLayout;
    MontserratEditText date,time;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Button searchButton;

    public ShowCustomDateTimeTravellers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_custom_date_time_travellers, container, false);
    }

    @Override
    public void onStart()
    {
        View view=getView();
        super.onStart();
        dateLayout=(TextInputLayout)view.findViewById(R.id.show_all_cab_input_date);
        timeLayout=(TextInputLayout)view.findViewById(R.id.show_all_cab_input_time);
        date=(MontserratEditText)view.findViewById(R.id.show_all_cab_date);
        time=(MontserratEditText)view.findViewById(R.id.show_all_cab_time);
        searchButton=(Button)view.findViewById(R.id.show_all_cab_search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.getText().toString().trim().isEmpty()||time.getText().toString().trim().isEmpty())
                {
                    if(date.getText().toString().isEmpty())
                    {
                        dateLayout.setErrorEnabled(true);
                        dateLayout.setError("Enter date first");
                        if(date.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }
                    }
                    else {
                        dateLayout.setErrorEnabled(false);
                    }
                    if (time.getText().toString().isEmpty())
                    {
                        timeLayout.setErrorEnabled(true);
                        timeLayout.setError("Enter date first");
                        if(time.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }

                    }
                    else
                    {
                        timeLayout.setErrorEnabled(false);
                    }
                }
                else {
                    dateLayout.setErrorEnabled(false);
                    timeLayout.setErrorEnabled(false);
                    Toast.makeText(getActivity(),"Loading",Toast.LENGTH_SHORT).show();
                }
            }
        });

        myCalendar = Calendar.getInstance();
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                //See the function's year,monthOfYear and dayOfMonth are what selected by user.
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //Above three lines of code are responsible of setting the date on calendar instance
                updateLabel();
                //updateLabel updates the date on text obtained from instance of myCalendar
            }
        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), dateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                //this will create the picker which contains the Calendar object as myCalendar
                // i guess with day, month and year in accordance to
                //the date month year obtained from system calendar
            }
        });

        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(sdf.format(myCalendar.getTime()));
    }

}
