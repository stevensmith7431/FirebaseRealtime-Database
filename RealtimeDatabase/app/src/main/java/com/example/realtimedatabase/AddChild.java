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

public class AddChild extends AppCompatActivity {

    EditText keys1;
    EditText keys2;
    TextView textView;
    Button save;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        keys1 = findViewById(R.id.edittext1id);
        keys2 = findViewById(R.id.edittext2id);
        textView = findViewById(R.id.textid);
        save = findViewById(R.id.buttonid);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s_key = keys1.getText().toString();
                String s_value = keys2.getText().toString();

              //  reference.push().setValue(s_value)
                reference.child(s_key).setValue(s_value)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(AddChild.this, "Write Success", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            Toast.makeText(AddChild.this, "Failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
