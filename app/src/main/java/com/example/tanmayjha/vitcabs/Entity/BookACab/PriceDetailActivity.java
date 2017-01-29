package com.example.tanmayjha.vitcabs.Entity.BookACab;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tanmayjha.vitcabs.Boundary.Interface.FragmentChangeListener;
import com.example.tanmayjha.vitcabs.Entity.Navigation.HomeActivity;
import com.example.tanmayjha.vitcabs.R;

public class PriceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_detail);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:onBackPressed();
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
