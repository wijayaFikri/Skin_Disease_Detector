package com.jassen.skindiseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jassen.skindiseasedetector.R;
import com.jassen.skindiseasedetector.models.Disease;

public class ProcessResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_result);
        Bundle bundle = getIntent().getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        ImageView imageView = findViewById(R.id.process_result_imageView);
        imageView.setImageBitmap(bitmap);
        Disease disease = new Gson().fromJson(bundle.get("disease").toString(),Disease.class);
        TextView resultTv = findViewById(R.id.process_result_textView);
        resultTv.setText(disease.getName());
        TextView symptomsTv = findViewById(R.id.symptoms_textView);
        symptomsTv.setText(disease.getSymptoms());
        TextView cureMethodTv = findViewById(R.id.cure_method_textView);
        cureMethodTv.setText(disease.getCureMethod());
    }

    public void finishProcess(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
