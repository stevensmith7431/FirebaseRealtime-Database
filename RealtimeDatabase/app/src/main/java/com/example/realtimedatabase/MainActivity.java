package com.example.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText keys1;
    EditText keys2;
    TextView textView;
    Button save;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keys1 = findViewById(R.id.edittext1id);
        keys2 = findViewById(R.id.edittext2id);
        textView = findViewById(R.id.textid);
        save = findViewById(R.id.buttonid);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("key");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  String s_key = keys1.getText().toString();
                String s_value = keys2.getText().toString();

              //  reference = database.getReference(s_key);

                reference.setValue(s_value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(MainActivity.this, "Write Success", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String val = dataSnapshot.getValue(String.class);

                textView.setText(val);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
