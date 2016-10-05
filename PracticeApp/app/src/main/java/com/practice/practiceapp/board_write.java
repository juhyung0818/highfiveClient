package com.practice.practiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class board_write extends GoogleLoginBase {

    Bundle loginBundle;

    FaceBookLoginWidget fbLoginWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBundle = getIntent().getExtras();
        String loginFrom = loginBundle.get("LOGINFROM").toString();
        if(loginFrom.equals("FACEBOOK")) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            fbLoginWidget = new FaceBookLoginWidget();
            fbLoginWidget.checkPage();
        }
        setContentView(R.layout.activity_board_write);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    void handleGoogleSignInResult(GoogleSignInResult result) {
        // 여기서는 무쓸모
    }
}
