package com.example.tanmayjha.vitcabs.Entity.BookACab;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tanmayjha.vitcabs.Control.Font.MontserratEditText;
import com.example.tanmayjha.vitcabs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookACabFragment extends Fragment {
    Button priceCardButton;
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
        priceCardButton=(Button)view.findViewById(R.id.button_price_tag);
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

}
