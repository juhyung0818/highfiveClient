package com.practice.practiceapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;


/**
 * 로그인 연동 */
public class Login extends GoogleLoginBase {

    FaceBookLoginWidget fbLoginWidget;
    LoginButton facebookButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 페이스북 sdk 초기화 (필수) 및 연동 코드
        FacebookSdk.sdkInitialize(getApplicationContext());

        // 화면 표시
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onStart() {
        super.onStart();

        //-------------------------------Google---------------------------------

        SignInButton googleButton = (SignInButton) findViewById(R.id.sign_in_button);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });
        //--------------------------------facebook------------------------------
        facebookButton = (LoginButton)findViewById(R.id.login_button);

        fbLoginWidget = new FaceBookLoginWidget();
        fbLoginWidget.loginPage();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 로그인이 되어있을 경우 화면을 보여주지 않고 넘어가기 위함
        if(mGoogleApiClient.isConnected() == false && fbLoginWidget.getCurAccessToken() != null) {
            handleFacebookSignInResult(fbLoginWidget);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        fbLoginWidget.accessTokenTracker.stopTracking();
        fbLoginWidget.profileTracker.stopTracking();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);

        if(FacebookSdk.isFacebookRequestCode(requestCode)){
            //Facebook login
            fbLoginWidget.callbackManager.onActivityResult(requestCode, responseCode, intent);
            handleFacebookSignInResult(fbLoginWidget);
        }
    }

    @Override
    void handleGoogleSignInResult(GoogleSignInResult result) {
        GoogleSignInAccount acct = result.getSignInAccount();

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            mGoogleApiClient.connect();
            gNextActivity(acct);
        }
    }

    private void handleFacebookSignInResult(FaceBookLoginWidget fbLoginWidget) {
        AccessToken accessToken = fbLoginWidget.getCurAccessToken();
        if (accessToken != null) {
            // 로그인 된 경우
            fbNextActivity(fbLoginWidget.getCurProfile());
        }
    }

    private void fbNextActivity(Profile profile){
        if(profile != null){ // 로그인 되어있으면 실행
            Intent main = new Intent(Login.this, Home.class);
            main.putExtra("NAME", profile.getFirstName());
            main.putExtra("SURNAME", profile.getLastName());
            main.putExtra("LOGINFROM", "FACEBOOK");

            startActivity(main);
        }
    }

    private void gNextActivity(GoogleSignInAccount acct){
        if(acct != null){ // 로그인 되어있으면 실행
            Intent main = new Intent(Login.this, Home.class);
            main.putExtra("NAME", acct.getGivenName());
            main.putExtra("SURNAME", acct.getFamilyName());
            main.putExtra("LOGINFROM", "GOOGLE");

            startActivity(main);
        }
    }
}