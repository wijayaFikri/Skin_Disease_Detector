package com.jassen.skindiseasedetector.activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;
import com.jassen.skindiseasedetector.R;

public class IntroToApp extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWizardMode(Boolean.TRUE);
        int green = ResourcesCompat.getColor(getResources(), R.color.colorSlideGreen, null);
        int amber = ResourcesCompat.getColor(getResources(), R.color.colorSlideAmber, null);
        int purple = ResourcesCompat.getColor(getResources(), R.color.colorSlidePurple, null);
        int cyan = ResourcesCompat.getColor(getResources(), R.color.colorSlideCyan, null);
        String welcomeDescription = "Welcome to skin disease detector application," +
                " here you can detect what happening to your skin";
        String takePictureDescription = "First step , take the picture of your skin";
        String appProcessDescription = "The application will process your picture and detect what happen to your skin";
        String startDescription = "Let's get started and find out what happen to your skin";
        addSlide(AppIntroFragment.newInstance("Welcome", welcomeDescription, R.drawable.welcome, green));
        addSlide(AppIntroFragment.newInstance("Take Picture", takePictureDescription, R.drawable.take_picture, amber));
        addSlide(AppIntroFragment.newInstance("Process", appProcessDescription, R.drawable.data_process, purple));
        addSlide(AppIntroFragment.newInstance("Let's Start", startDescription, R.drawable.lets_start, cyan));
        setTransformer(new AppIntroPageTransformerType.Parallax());
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
