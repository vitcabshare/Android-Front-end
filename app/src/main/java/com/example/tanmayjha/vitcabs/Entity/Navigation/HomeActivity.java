package com.example.tanmayjha.vitcabs.Entity.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tanmayjha.vitcabs.Boundary.Interface.FragmentChangeListener;
import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Entity.AboutUs.AboutUsFragment;
import com.example.tanmayjha.vitcabs.Entity.AddATrip.AddATripFragment;
import com.example.tanmayjha.vitcabs.Entity.BookACab.BookACabFragment;
import com.example.tanmayjha.vitcabs.Entity.LogIn.LoginActivity;
import com.example.tanmayjha.vitcabs.Entity.PoolRequest.PoolRequestFragment;
import com.example.tanmayjha.vitcabs.Entity.Settings.SettingsActivity;
import com.example.tanmayjha.vitcabs.Entity.ShowAllTravellers.ShowAllTravellers;
import com.example.tanmayjha.vitcabs.Entity.Welcome.WelcomeFragment;
import com.example.tanmayjha.vitcabs.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.OnConnectionFailedListener,FragmentChangeListener {

    String personName="User";
    GoogleApiClient mGoogleApiClient;
    String TAG=HomeActivity.class.getSimpleName();
    String personPhotoUrl;
    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
    AccountInformation accountInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new ShowAllTravellers(),"All Travellers");

        Intent fromLogin=getIntent();
        personName=accountInformation.getFullName();
        personPhotoUrl=accountInformation.getUrl();
        Log.v("Person's name",personName);
        View hView =  navigationView.getHeaderView(0);
        TextView name=(TextView)hView.findViewById(R.id.person_name);
        CircleImageView personImage=(CircleImageView)hView.findViewById(R.id.person_image);
        name.setText(personName);
        Glide.with(getApplicationContext()).load(personPhotoUrl).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(personImage);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        String title="Welcome";
        getSupportActionBar().setTitle(title);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        OptionalPendingResult<GoogleSignInResult> opr =
                Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

        if (opr.isDone()) {
            // Users cached credentials are valid, GoogleSignInResult containing ID token
            // is available immediately. This likely means the current ID token is already
            // fresh and can be sent to your server.
            GoogleSignInResult result = opr.get();
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently and get a valid
            // ID token. Cross-device single sign on will occur in this branch.
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                }
            });
        }

        // Timeline code starts here onwards.!
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Fragment f =getSupportFragmentManager().findFragmentById(R.id.container);
            if(f instanceof WelcomeFragment)
            {
                finishAffinity();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_a_trip) {
            replaceFragment(new AddATripFragment(),"Add a Trip");
        } else if (id == R.id.nav_pool_request) {
            replaceFragment(new PoolRequestFragment(),"Pool Request");
        } else if (id == R.id.nav_show_all_trip) {
            replaceFragment(new ShowAllTravellers(),"All Travellers");
        } else if (id == R.id.nav_book_a_cab) {
            replaceFragment(new BookACabFragment(),"Book A Cab");
        } else if (id == R.id.nav_about){
            replaceFragment(new AboutUsFragment(),"About Us Fragment");
        }
        else if (id == R.id.nav_log_out){

            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                        }
                    }
            );
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        //TODO: Sol
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment,String title)
    {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void replaceFragmentFromFragments(android.support.v4.app.Fragment fragment,String title) {
        //FragmentManager fragmentManager = getSupportFragmentManager();;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
        getSupportActionBar().setTitle(title);
    }


    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(this);
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(this);
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
