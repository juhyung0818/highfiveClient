package com.practice.practiceapp;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;

public class FaceBookLoginWidget {
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    FacebookCallback<LoginResult> callback;
    AccessToken accessToken;
    Profile profile;

    public FaceBookLoginWidget() {
        this.callbackManager = CallbackManager.Factory.create();
    }

    public void loginPage() { // login 액티비티에서 초기 셋팅 해주는 메소드
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {         }
            @Override
            public void onCancel() {         }
            @Override
            public void onError(FacebookException e) {      }
        };

        // 접근 토큰 추적기
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {    }
        };
        accessTokenTracker.startTracking();

        // 프로필 추적기
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {      }
        };
        profileTracker.startTracking();
    }

    public void checkPage() { // 그 외에 로그인 되어있는지 확인할 때 쓰이는 메소드
        accessToken = AccessToken.getCurrentAccessToken();
    }

    public Profile getCurProfile () {
        this.profile = Profile.getCurrentProfile();

        return this.profile;
    }

    public AccessToken getCurAccessToken() {
        this.accessToken = AccessToken.getCurrentAccessToken();
        return this.accessToken;
    }
}
/** facebookSDKinitialize는 각 액티비티에서 해주기*/
