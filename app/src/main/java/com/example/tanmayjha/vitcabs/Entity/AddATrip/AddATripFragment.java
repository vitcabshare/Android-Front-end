package com.example.tanmayjha.vitcabs.Entity.AddATrip;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanmayjha.vitcabs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddATripFragment extends Fragment {


    public AddATripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_a_trip, container, false);
    }

}
