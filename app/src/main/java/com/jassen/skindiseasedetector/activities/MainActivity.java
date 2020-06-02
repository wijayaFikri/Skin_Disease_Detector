package com.jassen.skindiseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jassen.skindiseasedetector.R;
import com.jassen.skindiseasedetector.models.Disease;

import java.util.ArrayList;
import java.util.List;

import static com.jassen.skindiseasedetector.ApplicationConstants.MY_PREF;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkFirstLogin();
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
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Intent intent = new Intent(mContext, CameraResultActivity.class);
            intent.putExtra("data", (Bitmap) extras.get("data"));
            startActivity(intent);
        }
    }

    public void checkFirstLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        int status = sharedPreferences.getInt("FIRST_LOGIN", 0);
        if (status == 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            try {
                editor.putInt("FIRST_LOGIN", 1);
                editor.apply();
            } catch (Exception e) {
                e.getStackTrace();
            }
            Intent intent = new Intent(this, IntroToApp.class);
            startActivity(intent);
        }
    }

    public void goTakePicture(View view) {
        dispatchTakePictureIntent();
    }
}
