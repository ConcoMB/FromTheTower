package com.concolabs.fromthetower.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.concolabs.fromthetower.R;
import com.concolabs.fromthetower.fragment.FttFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new FttFragment())
                    .commit();
    }
}
