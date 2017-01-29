package com.example.tanmayjha.vitcabs.Entity.ShowAllTravellers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanmayjha.vitcabs.Boundary.Interface.FragmentChangeListener;
import com.example.tanmayjha.vitcabs.Entity.AddATrip.AddATripFragment;
import com.example.tanmayjha.vitcabs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowAllTravellersTabHolder extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    com.melnykov.fab.FloatingActionButton floatingActionButton;

    public ShowAllTravellersTabHolder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_all_travellers, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        viewPager = (ViewPager) view.findViewById(R.id.show_all_travellers_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.show_all_travellers_tabs);
        viewPager.setAdapter(new TravellerAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(3);
        floatingActionButton=(com.melnykov.fab.FloatingActionButton)view.findViewById(R.id.show_all_cabs_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentChangeListener fragmentChangeListener=(FragmentChangeListener)getActivity();
                fragmentChangeListener.replaceFragmentFromFragments(new AddATripFragment(),"Add A Trip");
            }
        });
    }

    class TravellerAdapter extends FragmentStatePagerAdapter {

        public TravellerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0: return new ShowAllSameDateTraveller();
                case 1: return new ShowCustomDateTimeTravellers();
                case 2: return new ShowSameFlightTrainTravellers();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Same Date";
                case 1: return "All Traveller";
                case 2: return "Same Flight";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
