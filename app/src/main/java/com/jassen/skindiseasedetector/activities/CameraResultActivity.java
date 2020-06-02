package com.jassen.skindiseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.jassen.skindiseasedetector.R;

import static com.jassen.skindiseasedetector.activities.MainActivity.REQUEST_IMAGE_CAPTURE;

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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            imageResult = (Bitmap) extras.get("data");
            ImageView imageView = findViewById(R.id.camera_result_imageView);
            imageView.setImageBitmap(imageResult);
        }
    }

    public void goRetakeImage(View view) {
        dispatchTakePictureIntent();
    }
}
