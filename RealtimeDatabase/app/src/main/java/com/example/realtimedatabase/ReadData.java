package com.example.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

public class ReadData extends AppCompatActivity {

    TextView textView;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        textView = findViewById(R.id.textid);
        database = FirebaseDatabase.getInstance();

        database.getReference("users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String,String> map = (Map)dataSnapshot.getValue();

                        StringBuilder builder = new StringBuilder();

                        builder.append(map.get("name"));
                        builder.append(map.get("mobile"));
                        builder.append(map.get("age"));
                        builder.append(map.get("city"));

                        textView.setText(builder.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
