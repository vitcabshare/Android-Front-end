package com.example.tanmayjha.vitcabs.Entity.BookACab;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tanmayjha.vitcabs.Boundary.Interface.FragmentChangeListener;
import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.Entity.ShowAllTravellers.ShowAllTravellers;
import com.example.tanmayjha.vitcabs.Entity.Welcome.WelcomeFragment;
import com.example.tanmayjha.vitcabs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookACabFragment extends Fragment {
    Button priceCardButton,doneButton;
    TextInputLayout nameLayout,contactLayout,emailLayout,noOfPeopleLayout,dateLayout,timeLayout,pickUpLayout,anyOtherInfoLayout;
    MontserratEditText nameEditText,contactEditText,emailEditText,noOfPeopleEditText,dateOfTravelEditText,timeOfTravelEditText,pickUpLocationEditText,anyOtherInfoEditText;
    AccountInformation accountInformation;
    Spinner fromLocation,toLocation;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener dateSetListener;
    public BookACabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_acab, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        priceCardButton=(Button)view.findViewById(R.id.book_a_cab_button_price_tag);
        doneButton=(Button)view.findViewById(R.id.book_a_cab_button_done); 
        
        nameEditText=(MontserratEditText)view.findViewById(R.id.book_a_cab_name);
        contactEditText=(MontserratEditText)view.findViewById(R.id.book_a_cab_contact_no);
        emailEditText=(MontserratEditText)view.findViewById(R.id.book_a_cab_email);
        noOfPeopleEditText=(MontserratEditText)view.findViewById(R.id.book_a_cab_no_of_people);
        dateOfTravelEditText=(MontserratEditText)view.findViewById(R.id.book_a_cab_date);
        timeOfTravelEditText=(MontserratEditText)view.findViewById(R.id.book_a_cab_time);
        pickUpLocationEditText=(MontserratEditText)view.findViewById(R.id.book_a_cab_pick_up_location);
        anyOtherInfoEditText=(MontserratEditText)view.findViewById(R.id.book_a_cab_any_other_information);

        nameLayout=(TextInputLayout)view.findViewById(R.id.book_a_cab_input_name);
        contactLayout=(TextInputLayout)view.findViewById(R.id.book_a_cab_input_contact_no);
        emailLayout=(TextInputLayout)view.findViewById(R.id.book_a_cab_input_email);
        noOfPeopleLayout=(TextInputLayout)view.findViewById(R.id.book_a_cab_input_no_of_people);
        dateLayout=(TextInputLayout)view.findViewById(R.id.book_a_cab_input_date);
        timeLayout=(TextInputLayout)view.findViewById(R.id.book_a_cab_input_time);
        pickUpLayout=(TextInputLayout)view.findViewById(R.id.book_a_cab_input_pick_up_location);
        anyOtherInfoLayout=(TextInputLayout)view.findViewById(R.id.book_a_cab_input_any_other_information);

        fromLocation=(Spinner)view.findViewById(R.id.book_a_cab_spinner_from_location);
        toLocation=(Spinner)view.findViewById(R.id.book_a_cab_spinner_to_location);
        
        nameEditText.setText(accountInformation.getFullName());
        emailEditText.setText(accountInformation.getEmail());

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

        dateOfTravelEditText.setOnClickListener(new View.OnClickListener() {
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

        timeOfTravelEditText.setOnClickListener(new View.OnClickListener() {

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
                        timeOfTravelEditText.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEditText.getText().toString().isEmpty()||contactEditText.getText().toString().isEmpty()||emailEditText.getText().toString().isEmpty()||noOfPeopleEditText.getText().toString().isEmpty()||dateOfTravelEditText.getText().toString().isEmpty()||timeOfTravelEditText.getText().toString().isEmpty()||pickUpLocationEditText.getText().toString().isEmpty()||anyOtherInfoEditText.getText().toString().isEmpty()||fromLocation.getSelectedItem().toString().isEmpty()||toLocation.getSelectedItem().toString().isEmpty())
                {
                    if(nameEditText.getText().toString().isEmpty())
                    {
                        nameLayout.setErrorEnabled(true);
                        nameLayout.setError("Enter Name first");
                        if(nameEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }

                    }
                    else
                    {
                        nameLayout.setErrorEnabled(false);

                    }
                    if(contactEditText.getText().toString().isEmpty())
                    {
                        contactLayout.setErrorEnabled(true);
                        contactLayout.setError("Enter Contact details first");
                        if(contactEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }

                    }
                    else
                    {
                        contactLayout.setErrorEnabled(false);
                    }
                    if(emailEditText.getText().toString().isEmpty())
                    {
                        emailLayout.setErrorEnabled(true);
                        emailLayout.setError("Enter email first");
                        if(emailEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }

                    }
                    else
                    {
                        emailLayout.setErrorEnabled(false);

                    }
                    if(noOfPeopleEditText.getText().toString().isEmpty())
                    {
                        noOfPeopleLayout.setErrorEnabled(true);
                        noOfPeopleLayout.setError("Enter no_of_people first");
                        if(noOfPeopleEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }
                    }
                    else
                    {
                        noOfPeopleLayout.setErrorEnabled(false);
                    }
                    if(dateOfTravelEditText.getText().toString().isEmpty())
                    {
                        dateLayout.setErrorEnabled(true);
                        dateLayout.setError("Enter date first");
                        if(dateOfTravelEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }

                    }
                    else
                    {
                        dateLayout.setErrorEnabled(false);
                    }
                    if(timeOfTravelEditText.getText().toString().isEmpty())
                    {
                        timeLayout.setErrorEnabled(true);
                        timeLayout.setError("Enter time first");
                        if(timeOfTravelEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }

                    }
                    else
                    {
                        timeLayout.setErrorEnabled(false);
                    }
                    if(pickUpLocationEditText.getText().toString().isEmpty())
                    {
                        pickUpLayout.setErrorEnabled(true);
                        pickUpLayout.setError("Enter pick up location first");
                        if(pickUpLocationEditText.requestFocus())
                        {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }
                    }
                    else
                    {
                        pickUpLayout.setErrorEnabled(false);
                    }
                    if(fromLocation.getSelectedItem().toString().equals("Choose your location"))
                    {
                        Toast.makeText(getActivity(),"Enter from location of your trip",Toast.LENGTH_SHORT).show();
                    }
                    if(toLocation.getSelectedItem().toString().equals("Choose your location"))
                    {
                        Toast.makeText(getActivity(),"Enter to location of your trip",Toast.LENGTH_SHORT).show();
                    }
                }
                else {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Are you sure!? Or you wanna check again?");

                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(getActivity(),"Request Created",Toast.LENGTH_LONG).show();
                                    WelcomeFragment welcomeFragment=new WelcomeFragment();
                                    FragmentChangeListener fc=(FragmentChangeListener)getActivity();
                                    fc.replaceFragmentFromFragments(welcomeFragment,"Welcome");
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
                }
            }
        });
        
        
        priceCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Price Tag")
                        .setMessage("\n" +
                                "Car Type\tChennai Airport\tBangalore Airport\tPondicherry\n" +
                                "Non AC\tAC\tNon AC\tAC\tNon AC\tAC\n" +
                                "Indica / Figo\t2070\t2270\t4099\t4299\t2600\t2900\n" +
                                "Swift Desire / Etios\t2300\t2500\t4400\t4700\t2900\t3300\n" +
                                "Tavera\t3100\t3400\t6099\t6499\t3999\t4199\n" +
                                "Innova\t3499\t3799\t8199\t8499\t4599\t4999\n" +
                                "Traveller\t4999\t5299\t13399\t13899\t7999\t8699\n" +
                                "OK\n")
                        .show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateOfTravelEditText.setText(sdf.format(myCalendar.getTime()));
    }


}
