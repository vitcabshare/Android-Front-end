package com.example.tanmayjha.vitcabs.Entity.LogIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanmayjha.vitcabs.Control.Constants.AccountInformation;
import com.example.tanmayjha.vitcabs.Entity.Navigation.HomeActivity;
import com.example.tanmayjha.vitcabs.Entity.PhoneNoVerfication.PhoneNoActivity;
import com.example.tanmayjha.vitcabs.R;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    String TAG=LoginActivity.class.getSimpleName();
    public static GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SignInButton gmailSignInButton;
    private LoginButton facebookLoginButton;
    private static final int RC_SIGN_IN = 007;
    AccessToken accessToken;
    AccountInformation accountInformation;
    String firstName;
    String[] parts;
    private CallbackManager callbackManager;

    TextView skipThisView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LoginManager.getInstance().logOut();
        facebookLoginButton=(LoginButton)findViewById(R.id.facebook_login);
        skipThisView=(TextView)findViewById(R.id.skip_this);
        skipThisView.setOnClickListener(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();
//        facebookLoginButton.setReadPermissions("email");
        List permissions = new ArrayList();
        permissions.add("email");
        facebookLoginButton.setReadPermissions(permissions);

//        facebookLoginButton.logInWithReadPermissions(this, Arrays.asList("public_profile"));


        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken=loginResult.getAccessToken();
                loginResult.getRecentlyGrantedPermissions();
                Toast.makeText(getApplicationContext(),loginResult.getAccessToken().getUserId(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Successful login",Toast.LENGTH_SHORT).show();
                accountInformation.setLoginUsingFacebook(true);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"login Cancelled",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(),"Sorry, but an error came",Toast.LENGTH_SHORT).show();

            }
        });

        gmailSignInButton = (SignInButton) findViewById(R.id.gmail_login);
        gmailSignInButton.setOnClickListener(this);
        //Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        gmailSignInButton.setSize(SignInButton.SIZE_STANDARD);
        gmailSignInButton.setScopes(gso.getScopeArray());
    }


    private void signIn() {
        //Maybe here signInIntent will get intent data from mGoogleApiClient
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        //Starting the intent prompts the user to select a Google account to sign in with.
        // If you requested scopes beyond profile, email, and openid,
        // the user is also prompted to grant access to the requested resources.
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "HandleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            //Signed in succesfilly, show authenticated UI
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e(TAG, "display name:" + acct.getDisplayName());
            String personName = acct.getDisplayName();
            String personPhotoUrl;
            if(acct.getPhotoUrl()!=null)
            {
                accountInformation.setUrl(acct.getPhotoUrl().toString());
            }
            else
            {
                accountInformation.setUrl("https://developers.google.com/experts/img/user/user-default.png");
            }
            String personLastName=acct.getFamilyName();
            Log.v("Last Name",personLastName);
            parts=acct.getDisplayName().trim().split(" ");
            accountInformation.setFirstName(parts[0]);
            accountInformation.setEmail(acct.getEmail());
            accountInformation.setLastName(acct.getFamilyName());
            Intent toMainActivity=new Intent(this, PhoneNoActivity.class);
            startActivity(toMainActivity);
        }
    }

    // onActivityResult() is called whenever user returns from Google Login UI.
    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            accountInformation.setLoginUsingGmail(true);
        }
        else {
            if(accountInformation.isLoginUsingFacebook()) {
                /*
            accountInformation.setFirstName(Profile.getCurrentProfile().getFirstName());
            accountInformation.setLastName(Profile.getCurrentProfile().getLastName());
            accountInformation.setEmail(Profile.getCurrentProfile().get);*/
                accountInformation.setLoginUsingGmail(false);
                callbackManager.onActivityResult(requestCode, resultCode, data);
                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    final JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                final JSONObject jsonObject = response.getJSONObject();
                                String nombre = "";
                                String email = "";
                                String id = "";
                                try {
                                    nombre = jsonObject.getString("name");
                                    email =  jsonObject.getString("email");
                                    parts=nombre.trim().split(" ");
                                    accountInformation.setFirstName(parts[0]);
                                    accountInformation.setLastName(parts[1]);
                                    accountInformation.setEmail(email);
                                    Toast.makeText(getApplicationContext(),Profile.getCurrentProfile().getFirstName(),Toast.LENGTH_SHORT).show();
                                    accountInformation.setUrl(Profile.getCurrentProfile().getProfilePictureUri(400,400).toString());
//                                    if (jsonObject.has("picture")) {
//                                        String profilePicUrl = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url");
//                                        Bitmap profilePic = BitmapFactory.decodeStream(profilePicUrl.openConnection().getInputStream());
//                                        // set profilePic bitmap to imageview
//                                    }

                                    //accountInformation.setUrl("https://graph.facebook.com/" + userID + "/picture?type=large");
                                    Intent toMainActivity=new Intent(getApplicationContext(), PhoneNoActivity.class);
                                    startActivity(toMainActivity);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    private void showProgressDialog(){
        if(mProgressDialog==null){
            mProgressDialog=new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog(){
        if(mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.hide();
        }
    }

    @Override
    public void onClick(View v){
        int id=v.getId();
        switch (id)
        {
            case R.id.gmail_login:
                signIn();
                break;
            case R.id.skip_this:
                Intent intent=new Intent(this,HomeActivity.class);
                intent.putExtra("personName","Guest");
                intent.putExtra("personPhotoUrl","");
                startActivity(intent);
        }
    }
}
