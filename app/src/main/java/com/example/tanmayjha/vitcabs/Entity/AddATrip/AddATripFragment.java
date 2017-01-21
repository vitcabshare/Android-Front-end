package com.example.tanmayjha.vitcabs.Entity.AddATrip;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tanmayjha.vitcabs.Boundary.Interface.FragmentChangeListener;
import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.Entity.ShowAllTravellers.ShowAllTravellers;
import com.example.tanmayjha.vitcabs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Locale;

import static android.R.attr.button;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddATripFragment extends Fragment implements View.OnClickListener{


    private Calendar myCalendar;
    MontserratEditText dateEditText,timeEditText,flightNoEditText;
    DatePickerDialog.OnDateSetListener dateSetListener;
    TextInputLayout dateLayout,timeLayout,flightNoLayout;
    Button resetButton,doneButton;
    Switch lastNameSwitch,emailSwitch,phoneNoSwitch;
    String lastName,phoneNo,email;
    AccountInformation accountInformation;
    Spinner fromLocationSpinner,toLocationSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_a_trip, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        dateEditText=(MontserratEditText)view.findViewById(R.id.add_a_trip_date);
        timeEditText=(MontserratEditText)view.findViewById(R.id.add_a_trip_time);
        resetButton=(Button)view.findViewById(R.id.add_a_trip_button_reset);
        doneButton=(Button)view.findViewById(R.id.add_a_trip_button_done);
        flightNoEditText=(MontserratEditText)view.findViewById(R.id.add_a_trip_flight_no_or_train_no);
        flightNoLayout=(TextInputLayout)view.findViewById(R.id.input_add_a_trip_flight_no_or_train_no);
        dateLayout=(TextInputLayout)view.findViewById(R.id.input_add_a_trip_date);
        timeLayout=(TextInputLayout)view.findViewById(R.id.input_add_a_trip_time);
        lastNameSwitch=(Switch)view.findViewById(R.id.add_a_trip_switch_last_name);
        emailSwitch=(Switch)view.findViewById(R.id.add_a_trip_switch_email);

        fromLocationSpinner=(Spinner)view.findViewById(R.id.add_a_trip_from_location);
        toLocationSpinner=(Spinner)view.findViewById(R.id.add_a_trip_to_location);

        if(!accountInformation.getLastName().trim().isEmpty()) {
            emailSwitch.setText("Show my email(" + accountInformation.getEmail() + ")to other?");
            lastNameSwitch.setText("Show my last name(" + accountInformation.getLastName() + ")?");
        }
        resetButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
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

        dateEditText.setOnClickListener(new View.OnClickListener() {
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

        timeEditText.setOnClickListener(new View.OnClickListener() {

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
                    timeEditText.setText( selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    });
    }

    //TODO:Add constraint for text inputlayout
    //TODO:Add last name, email and phone no in xml view

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateEditText.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_a_trip_button_reset: {
                dateEditText.setText("");
                timeEditText.setText("");
                flightNoEditText.setText("");
                break;
            }
            case R.id.add_a_trip_button_done:
            {
                if(dateEditText.getText().toString().isEmpty()||timeEditText.getText().toString().isEmpty()||fromLocationSpinner.getSelectedItem().toString().isEmpty()||toLocationSpinner.getSelectedItem().toString().isEmpty())
                {   if(dateEditText.getText().toString().isEmpty())
                    {
                        dateLayout.setErrorEnabled(true);
                        dateLayout.setError("Enter date first");
                        if(dateEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }
                    }
                    else {
                        dateLayout.setErrorEnabled(false);
                    }
                    if(timeEditText.getText().toString().isEmpty())
                    {
                        timeLayout.setErrorEnabled(true);
                        timeLayout.setError("Enter time first");
                        if(timeEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }
                    }
                    else
                    {
                        timeLayout.setErrorEnabled(false);
                    }

                    if(fromLocationSpinner.getSelectedItem().toString().equals("Choose your location"))
                    {
                        Toast.makeText(getActivity(),"Enter your journey's from location",Toast.LENGTH_SHORT).show();
                    }
                    if(toLocationSpinner.getSelectedItem().toString().equals("Choose your location"))
                    {
                        Toast.makeText(getActivity(),"Enter your journey's to location",Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Are you sure!? Or you wanna check again?");

                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(getActivity(),"Request Created. You will shortly get a call",Toast.LENGTH_LONG).show();
                                    ShowAllTravellers showAllTravellers=new ShowAllTravellers();
                                    FragmentChangeListener fc=(FragmentChangeListener)getActivity();
                                    fc.replaceFragmentFromFragments(showAllTravellers,"All Trips");
                                }
                            });

                    alertDialogBuilder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.setCancelable(true);
                    alertDialog.show();
                    break;
                }
            }
        }
    }
}
