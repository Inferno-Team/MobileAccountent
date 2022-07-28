package com.inferno.mobile.accountent.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.ActivityMainBinding;
import com.inferno.mobile.accountent.activities.SettingsActivity;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        ContextUtils.changeLang(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (ContextUtils.isDark(this)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        getDelegate().applyDayNight();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.settings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        if (hostFragment != null) {
            if (hostFragment.getChildFragmentManager().getBackStackEntryCount() > 1) {
                super.onBackPressed();
            } else {
                finishAffinity();
            }
        }else
            Toast.makeText(this, "customer back null pointer", Toast.LENGTH_SHORT).show();
    }
}