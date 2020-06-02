package com.jassen.skindiseasedetector.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.jassen.skindiseasedetector.R;
import com.jassen.skindiseasedetector.models.Disease;
import com.jassen.skindiseasedetector.retrofit.CallApi;
import com.jassen.skindiseasedetector.retrofit.SddRetrofit;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.jassen.skindiseasedetector.ApplicationConstants.CATEGORY_PREF;

public class ProcessingActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        mAuth = FirebaseAuth.getInstance();
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final Bitmap imageResult = (Bitmap) bundle.get("data");
        assert imageResult != null;
        SddRetrofit sddRetrofit = new SddRetrofit();
        sddRetrofit.processImage(convertBitmapToBase64(imageResult), new CallApi<String>() {
            @Override
            public void onSuccess(final String result) {
                final Intent intent = new Intent(ProcessingActivity.this, ProcessResultActivity.class);
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child(CATEGORY_PREF).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        ArrayList<Disease> list = new ArrayList<Disease>();
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            list.add(dsp.getValue(Disease.class));

                        }
                        intent.putExtra("data", imageResult);
                        Disease disease = list.get(Integer.parseInt(result));
                        intent.putExtra("disease", new Gson().toJson(disease));
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onFailed(String message) {
                Toast.makeText(ProcessingActivity.this, message,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public String convertBitmapToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
