package com.jassen.skindiseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.jassen.skindiseasedetector.R;

public class ProcessingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        Bundle bundle = getIntent().getExtras();
        Bitmap imageResult = (Bitmap) bundle.get("data");
        Intent intent = new Intent(this,ProcessResultActivity.class);
        intent.putExtra("data" , imageResult);
        startActivity(intent);
    }
}
