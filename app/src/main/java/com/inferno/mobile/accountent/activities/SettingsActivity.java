package com.inferno.mobile.accountent.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.SettingsFragmentBinding;
import com.inferno.mobile.accountent.models.LogoutResponse;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        ContextUtils.changeLang(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SettingsFragmentBinding binding = SettingsFragmentBinding.inflate(getLayoutInflater());
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setContentView(binding.getRoot());
        binding.darkModeSwitch.setChecked(ContextUtils.isDark(this));

        int flagId = R.drawable.ic_flag_of_syria;
        if (ContextUtils.getLanguage(this).equals("English"))
            flagId = R.drawable.ic_flag_of_the_united_states;
        binding.langFlag.setImageResource(flagId);

        binding.darkModeSwitch.setOnCheckedChangeListener((l, is) -> {
            if (is)
                ContextUtils.saveDarkTheme(this);
            else ContextUtils.saveLightTheme(this);

            if (ContextUtils.isDark(this)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            getDelegate().applyDayNight();
        });
        binding.langFlag.setOnClickListener(l -> {
            if (ContextUtils.getLanguage(this).equals("Arabic"))
                ContextUtils.saveLanguage(this, "English");
            else ContextUtils.saveLanguage(this, "Arabic");
            Intent intent = new Intent(this, SettingsActivity.class);
            finish();
            startActivity(intent);
        });

        if (!ContextUtils.isUserExists(this))
            binding.logoutButton.setVisibility(View.GONE);
        else binding.logoutButton.setVisibility(View.VISIBLE);

        binding.logoutButton.setOnClickListener(l ->
                viewModel.logout(ContextUtils.getToken(this))
                        .observe(this, this::logout));

    }

    private void logout(LogoutResponse response) {
        if (response == null) return;
        Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        if (response.getStatusCode() == 200) {
            ContextUtils.deleteUser(this);
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
