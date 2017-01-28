package com.example.tanmayjha.vitcabs.Entity.AddATrip;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddATripFragment extends Fragment implements View.OnClickListener{

    private Calendar myCalendar;
    MontserratEditText dateEditText,timeEditText,flightNoEditText;
    DatePickerDialog.OnDateSetListener dateSetListener;
    TextInputLayout dateLayout,timeLayout,flightNoLayout;
    Button resetButton,doneButton;
    String lastName,phoneNo,email;
    AccountInformation accountInformation;
    Spinner fromLocationSpinner,toLocationSpinner;
    TextView fromLocationError,toLocationError;
    Boolean flag;
    private GoogleMap mMap;
    SupportMapFragment mMapFragment;
    Marker VITVelloreFromMarker,VITChennaiFromMarker,chennaiAirportFromMarker,bangaloreAirportFromMarker,chennaiRailwayStationFromMarker,katpadiRailwayStationFromMarker,pondicherryFromMarker,kodaikanalFromMarker,VITVelloreToMarker,VITChennaiToMarker,chennaiAirportToMarker,bangaloreAirportToMarker,chennaiRailwayStationToMarker,katpadiRailwayStationToMarker,pondicherryToMarker,kodaikanalToMarker,fromMarker,toMarker;
    Polyline line;

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
        mMapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);

        dateEditText=(MontserratEditText)view.findViewById(R.id.add_a_trip_date);
        timeEditText=(MontserratEditText)view.findViewById(R.id.add_a_trip_time);
        resetButton=(Button)view.findViewById(R.id.add_a_trip_button_reset);
        doneButton=(Button)view.findViewById(R.id.add_a_trip_button_done);
        flightNoEditText=(MontserratEditText)view.findViewById(R.id.add_a_trip_flight_no_or_train_no);
        flightNoLayout=(TextInputLayout)view.findViewById(R.id.input_add_a_trip_flight_no_or_train_no);
        dateLayout=(TextInputLayout)view.findViewById(R.id.input_add_a_trip_date);
        timeLayout=(TextInputLayout)view.findViewById(R.id.input_add_a_trip_time);

        fromLocationSpinner=(Spinner)view.findViewById(R.id.add_a_trip_from_location);
        toLocationSpinner=(Spinner)view.findViewById(R.id.add_a_trip_to_location);

        fromLocationError=(TextView)view.findViewById(R.id.add_a_trip_spinner_from_location_error);
        toLocationError=(TextView)view.findViewById(R.id.add_a_trip_spinner_to_location_error);

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

        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.v("Map Fragment","check");
                mMap=googleMap;
                LatLng coordinate = new LatLng(12.969129,79.155787);
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate,6);
                googleMap.animateCamera(yourLocation);
            }
        });

        fromLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                if (line != null) {
                    line.remove();
                    //toMarker=null;
                }

                if (id == 1) {
                    VITVelloreFromMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(12.9693, 79.1559))
                            .title("VIT Vellore"));
                    LatLng coordinate = new LatLng(12.9693, 79.1559);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    fromMarker=VITVelloreFromMarker;
                } else {
                    if(VITVelloreFromMarker!=null)
                    VITVelloreFromMarker.remove();
                }
                if (id == 2) {
                    VITChennaiFromMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(12.842222, 80.154167))
                            .title("VIT Chennai"));
                    LatLng coordinate = new LatLng(12.842222, 80.154167);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    fromMarker=VITChennaiFromMarker;
                } else {
                    if(VITChennaiFromMarker!=null)
                    VITChennaiFromMarker.remove();
                }
                if (id == 3) {
                    chennaiAirportFromMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(12.9941, 80.1709))
                            .title("Chennai Airport"));
                    LatLng coordinate = new LatLng(12.9941, 80.1709);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    fromMarker=chennaiAirportFromMarker;
                } else {
                    if(chennaiAirportFromMarker!=null)
                    chennaiAirportFromMarker.remove();
                }
                if (id == 4) {
                    bangaloreAirportFromMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(13.1986, 77.7066))
                            .title("Bangalore Airport"));
                    LatLng coordinate = new LatLng(13.1986, 77.7066);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    fromMarker=bangaloreAirportFromMarker;
                } else {
                    if(bangaloreAirportFromMarker!=null)
                        bangaloreAirportFromMarker.remove();
                }
                if (id == 5) {
                    chennaiRailwayStationFromMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(13.0823, 80.2754))
                            .title("Chennai Railway Station"));
                    LatLng coordinate = new LatLng(13.0823, 80.2754);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    fromMarker=chennaiRailwayStationFromMarker;
                } else {
                    if(chennaiRailwayStationFromMarker!=null)
                    chennaiRailwayStationFromMarker.remove();
                }
                if (id == 6) {
                    katpadiRailwayStationFromMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(12.9722, 79.1384))
                            .title("Katpadi Railway Station"));
                    LatLng coordinate = new LatLng(12.9722, 79.1384);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    fromMarker=katpadiRailwayStationFromMarker;
                } else {
                    if(katpadiRailwayStationFromMarker!=null)
                    katpadiRailwayStationFromMarker.remove();
                }
                if (id == 7) {
                    pondicherryFromMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(11.9139, 79.8145))
                            .title("Pondicherry"));
                    LatLng coordinate = new LatLng(11.9139, 79.8145);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    fromMarker=pondicherryFromMarker;
                } else {
                    if(pondicherryFromMarker!=null)
                    pondicherryFromMarker.remove();
                }if (id == 8) {
                        kodaikanalFromMarker=mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(10.2381, 77.4892))
                                .title("Kodaikanal"));
                        LatLng coordinate = new LatLng(10.2381, 77.4892);
                        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                        mMap.animateCamera(yourLocation);
                    fromMarker=kodaikanalFromMarker;
                    }
                else {
                    if(kodaikanalFromMarker!=null)
                    kodaikanalFromMarker.remove();

                }

                if(toMarker!=null)
                {
                    LatLng fromlatLng=fromMarker.getPosition();
                    double fromLatitude=fromlatLng.latitude;
                    double fromLongitude=fromlatLng.longitude;

                    LatLng tolatlng=toMarker.getPosition();
                    double toLatitude=tolatlng.latitude;
                    double toLogitude=tolatlng.longitude;

                    line=mMap.addPolyline(new PolylineOptions().add(new LatLng(fromLatitude,
                                            fromLongitude),
                                    new LatLng(toLatitude,toLogitude))
                                    .width(5).color(Color.RED));
//                    if(fromMarker!=null)
//                        fromMarker=null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        toLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                if (line != null) {
                    line.remove();
                    //toMarker=null;
                }

                if (id == 1) {
                    VITVelloreToMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(12.9693, 79.1559))
                            .title("VIT Vellore"));
                    LatLng coordinate = new LatLng(12.9693, 79.1559);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    toMarker=VITVelloreToMarker;
                } else {
                    if(VITVelloreToMarker!=null)
                        VITVelloreToMarker.remove();
                }
                if (id == 2) {
                    VITChennaiToMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(12.842222, 80.154167))
                            .title("VIT Chennai"));
                    LatLng coordinate = new LatLng(12.842222, 80.154167);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    toMarker=VITChennaiToMarker;
                } else {
                    if(VITChennaiToMarker!=null)
                        VITChennaiToMarker.remove();
                }
                if (id == 3) {
                    chennaiAirportToMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(12.9941, 80.1709))
                            .title("Chennai Airport"));
                    LatLng coordinate = new LatLng(12.9941, 80.1709);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    toMarker=chennaiAirportToMarker;
                } else {
                    if(chennaiAirportToMarker!=null)
                        chennaiAirportToMarker.remove();
                }
                if (id == 4) {
                    bangaloreAirportToMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(13.1986, 77.7066))
                            .title("Bangalore Airport"));
                    LatLng coordinate = new LatLng(13.1986, 77.7066);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    toMarker=bangaloreAirportToMarker;
                } else {
                    if(bangaloreAirportToMarker!=null)
                        bangaloreAirportToMarker.remove();
                }
                if (id == 5) {
                    chennaiRailwayStationToMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(13.0823, 80.2754))
                            .title("Chennai Railway Station"));
                    LatLng coordinate = new LatLng(13.0823, 80.2754);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    toMarker=chennaiRailwayStationToMarker;
                } else {
                    if(chennaiRailwayStationToMarker!=null)
                        chennaiRailwayStationToMarker.remove();
                }
                if (id == 6) {
                    katpadiRailwayStationToMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(12.9722, 79.1384))
                            .title("Katpadi Railway Station"));
                    LatLng coordinate = new LatLng(12.9722, 79.1384);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    toMarker=katpadiRailwayStationToMarker;
                } else {
                    if(katpadiRailwayStationToMarker!=null)
                        katpadiRailwayStationToMarker.remove();
                }
                if (id == 7) {
                    pondicherryToMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(11.9139, 79.8145))
                            .title("Pondicherry"));
                    LatLng coordinate = new LatLng(11.9139, 79.8145);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    toMarker=pondicherryToMarker;
                } else {
                    if(pondicherryToMarker!=null)
                        pondicherryToMarker.remove();
                }if (id == 8) {
                    kodaikanalToMarker=mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.2381, 77.4892))
                            .title("Kodaikanal"));
                    LatLng coordinate = new LatLng(10.2381, 77.4892);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                    mMap.animateCamera(yourLocation);
                    toMarker=kodaikanalToMarker;
                }
                else {
                    if(kodaikanalToMarker!=null)
                        kodaikanalToMarker.remove();
                }


                if(fromMarker!=null)
                {
                    LatLng fromlatLng=fromMarker.getPosition();
                    double fromLatitude=fromlatLng.latitude;
                    double fromLongitude=fromlatLng.longitude;

                    LatLng tolatlng=toMarker.getPosition();
                    double toLatitude=tolatlng.latitude;
                    double toLogitude=tolatlng.longitude;

                    line=mMap.addPolyline(new PolylineOptions().add(new LatLng(fromLatitude,
                                    fromLongitude),
                            new LatLng(toLatitude,toLogitude))
                            .width(5).color(Color.RED));

//                    if(toMarker!=null)
//                        toMarker=null;
                }
                else{
                    if(line!=null) {
                        line.remove();
                        //fromMarker=null;
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                fromLocationSpinner.setSelection(0);
                toLocationSpinner.setSelection(0);
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
                        fromLocationError.setVisibility(View.VISIBLE);
                        toLocationError.setVisibility(View.INVISIBLE);
                        flag=true;
                    }
                    else {
                        if(!flag) {
                            fromLocationError.setVisibility(View.GONE);
                            toLocationError.setVisibility(View.GONE);
                        }
                        else
                            flag=false;
                    }
                    if(toLocationSpinner.getSelectedItem().toString().equals("Choose your location"))
                    {
                        toLocationError.setVisibility(View.VISIBLE);
                        fromLocationError.setVisibility(View.INVISIBLE);

                    }
                    else {
                        if(!flag) {
                            toLocationError.setVisibility(View.GONE);
                            fromLocationError.setVisibility(View.GONE);
                        }
                        else
                            flag=false;
                    }
                    if(toLocationSpinner.getSelectedItem().toString().equals("Choose your location") && fromLocationSpinner.getSelectedItem().toString().equals("Choose your location"))
                    {
                        toLocationError.setVisibility(View.VISIBLE);
                        fromLocationError.setVisibility(View.VISIBLE);
                    }
                    break;
                }
                else {

                    /*
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Are you sure!? Or you wanna check again?");

                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(getActivity(),"Request Created.",Toast.LENGTH_LONG).show();
                                    ShowAllTravellersTabHolder showAllTravellers=new ShowAllTravellersTabHolder();
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
                    */

                    Intent intent=new Intent(getActivity(),AddATripResultActivity.class);
                    intent.putExtra("date",dateEditText.getText().toString());
                    intent.putExtra("time",timeEditText.getText().toString());
                    intent.putExtra("flighno",flightNoEditText.getText().toString());
                    intent.putExtra("fromLocation",fromLocationSpinner.getSelectedItem().toString());
                    intent.putExtra("toLocation",toLocationSpinner.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        }
    }
}
