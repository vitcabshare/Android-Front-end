package com.example.tanmayjha.vitcabs.Entity.PoolRequest;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanmayjha.vitcabs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoolRequestFragment extends Fragment {
    com.melnykov.fab.FloatingActionButton clearAllFab;

    public PoolRequestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pool_request, container, false);
    }

    public void onStart(){
        super.onStart();
        View view=getView();
        clearAllFab = (com.melnykov.fab.FloatingActionButton)view.findViewById(R.id.clear_all_fab);
        clearAllFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are you sure you want to clear all?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}
