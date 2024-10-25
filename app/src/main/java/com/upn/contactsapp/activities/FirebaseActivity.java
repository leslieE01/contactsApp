package com.upn.contactsapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upn.contactsapp.R;
import com.upn.contactsapp.entities.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        Button button = findViewById(R.id.buttonFirebase);
        EditText editText = findViewById(R.id.nombreEText);

        //LEER
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference conctactRef = database.getReference("N00290319").child("contacts"); //N1

        List<Contact>contacts= new ArrayList<>();
        conctactRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    Contact c = child.getValue(Contact.class);
                    contacts.add(c);
                    Log.i("MAIN_APP", c.uuid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//REGISTRAR

//        button.setOnClickListener(v -> {
//
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef = database.getReference("N00290319"); //N1
//            DatabaseReference table=  myRef.child("contacts");//N2
//
//
//            Contact c1 = new Contact("Leslie","123456");
//            c1.uuid = UUID.randomUUID().toString();
//
//            Contact c2 = new Contact("Miguel","123456");
//            c2.uuid = UUID.randomUUID().toString();;
//
//            Contact c3 = new Contact("San","123456");
//            c3.uuid = UUID.randomUUID().toString();;
//
//            Contact c4 = new Contact("Henry","123450");
//            c4.uuid = UUID.randomUUID().toString();
//
//            Contact c5 = new Contact("Keny","13250");
//            c5.uuid = UUID.randomUUID().toString();
//
//
//            //myRef.child(String.valueOf(c1.uuid)).setValue(c1);
//            // myRef.child(String.valueOf(c2.uuid)).setValue(c2);
//            //myRef.child(String.valueOf(c3.uuid)).setValue(c3);
//
//             // myRef.child(String.valueOf(c4.uuid)).setValue(c4);
//            //myRef.child(String.valueOf(c5.uuid)).setValue(c5);
//            //table.setValue(c1);
//
//            String name = editText.getText().toString();
//            Contact c7 = new Contact(name,"13250");
//            c7.uuid = UUID.randomUUID().toString();
//            table.child(c7.uuid).setValue(c7);
//
//            editText.setText("");
//
//
//
//
//            Toast.makeText(FirebaseActivity.this, "Información enviada", Toast.LENGTH_SHORT).show();
//
//
//        });
    }
}
