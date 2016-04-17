package com.mac.isaac.threadsinorientationchangeapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    MainFragment myfragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            myfragment = new MainFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, myfragment, "fragment_main")
                    .commit();
        } else {
            myfragment = (MainFragment) fragmentManager.findFragmentByTag("fragment_main");
        }
    }

    @Override
    public void sendProgress(int i) {
    }
}
