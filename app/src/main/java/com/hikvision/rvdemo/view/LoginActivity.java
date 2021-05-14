package com.hikvision.rvdemo.view;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.hikvision.rvdemo.R;

public class LoginActivity extends FragmentActivity {

    private NavHostFragment navHostFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.loginFragment, LoginFragment.newInstance())
//                    .commitNow();
//        }
//        navHostFragment =
//                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    }

}
