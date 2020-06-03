package com.jassen.skindiseasedetector.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jassen.skindiseasedetector.R;
import com.jassen.skindiseasedetector.adapters.HistoryAdapter;
import com.jassen.skindiseasedetector.models.History;

import java.util.ArrayList;

import static com.jassen.skindiseasedetector.ApplicationConstants.HISTORY_REF;

public class HistoryActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        myRef.child(HISTORY_REF).child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<History> list = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    list.add(dsp.getValue(History.class));
                }
                HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this, R.layout.history_item, list);
                ListView lv = findViewById(R.id.history_listView);
                lv.setDivider(null);
                lv.setAdapter(historyAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
