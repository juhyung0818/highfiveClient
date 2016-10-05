package com.practice.practiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;


public class Home extends GoogleLoginBase {

    String loginFrom;

    ViewPager viewPager;
    PagerAdapter adapter;
    Toolbar toolbar;
    TextView toolbar_title;
    TabLayout tabLayout;

    FaceBookLoginWidget fbLoginWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle homeBundle = getIntent().getExtras();

        loginFrom = homeBundle.get("LOGINFROM").toString();

        if(loginFrom.equals("FACEBOOK")) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            fbLoginWidget = new FaceBookLoginWidget();
            fbLoginWidget.checkPage();
        }
        setContentView(R.layout.activity_home);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);

        // 여기서 부터는 탭 구현 부분
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.main_icon_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.main_icon_give_un));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.main_icon_get_un));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.main_icon_etc_un));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        toolbar_title.setText("메인");
                        tab.setIcon(R.drawable.main_icon_home);
                        break;
                    case 1:
                        toolbar_title.setText("GIVE HELP");
                        tab.setIcon(R.drawable.main_icon_give);
                        break;
                    case 2:
                        toolbar_title.setText("GET HELP");
                        tab.setIcon(R.drawable.main_icon_get);
                        break;
                    case 3:
                        toolbar_title.setText("기타");
                        tab.setIcon(R.drawable.main_icon_etc);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.main_icon_home_un);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.main_icon_give_un);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.main_icon_get_un);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.main_icon_etc_un);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    void handleGoogleSignInResult(GoogleSignInResult result) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
