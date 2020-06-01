package com.jassen.skindiseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.jassen.skindiseasedetector.R;

public class ProcessResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_result);
        Bundle bundle = getIntent().getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        ImageView imageView = findViewById(R.id.process_result_imageView);
        imageView.setImageBitmap(bitmap);
    }
}
