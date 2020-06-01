package com.jassen.skindiseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jassen.skindiseasedetector.R;

public class CameraResultActivity extends AppCompatActivity {
    Bundle bundle;
    Bitmap imageResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_result);
        bundle = getIntent().getExtras();
        imageResult = (Bitmap) bundle.get("data");
        ImageView imageView = findViewById(R.id.camera_result_imageView);
        imageView.setImageBitmap(imageResult);
    }

    public void goProcess(View view) {
        Intent intent = new Intent(this,ProcessingActivity.class);
        intent.putExtra("data" , imageResult);
        startActivity(intent);
    }
}
