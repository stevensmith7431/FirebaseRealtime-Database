package com.example.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Insert extends AppCompatActivity {

    EditText name,mobile,age,city;
    Button save;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        name = findViewById(R.id.edittext1id);
        mobile = findViewById(R.id.edittext2id);
        age = findViewById(R.id.edittext3id);
        city = findViewById(R.id.edittext4id);
        save = findViewById(R.id.buttonid);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s_name = name.getText().toString();
                String s_mobile = mobile.getText().toString();
                String s_age = age.getText().toString();
                String s_city = city.getText().toString();

                Map<String,String> map = new HashMap<>();

                map.put("name",s_name);
                map.put("mobile",s_mobile);
                map.put("age",s_age);
                map.put("city",s_city);

                reference.child("users").setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){

                                    Toast.makeText(Insert.this, "Success", Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    Toast.makeText(Insert.this, "Error", Toast.LENGTH_SHORT).show();
                                    
                                }
                            }
                        });
            }
        });
    }
}
