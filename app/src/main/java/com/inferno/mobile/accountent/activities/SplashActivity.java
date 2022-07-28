package com.inferno.mobile.accountent.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.SplashActivityBinding;
import com.inferno.mobile.accountent.utils.ContextUtils;
import com.inferno.mobile.billprogressbarlib.BillProgressBar;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    private SplashActivityBinding binding;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = SplashActivityBinding.inflate(getLayoutInflater());
        Animation textAnimation =
                AnimationUtils.loadAnimation(this, R.anim.splash_layout_text_animation_start);
        binding.welcomeText.setVisibility(View.VISIBLE);
        binding.welcomeText.setAnimation(textAnimation);
        new Handler().postDelayed(()->{
            runOnUiThread(()->{
                binding.progress.setVisibility(View.VISIBLE);
                binding.progress.startAnimation();
                new Handler().postDelayed(()->{
                    binding.progress.stopAnimation();
                    new Handler().postDelayed(()->{
                        Intent intent = new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    },1000);
                },2000);

            });
        },1000);

        setContentView(binding.getRoot());
    }
}
