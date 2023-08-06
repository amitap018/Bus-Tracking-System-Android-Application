package com.amit018.maps2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://focus-nucleus-351210-default-rtdb.firebaseio.com/");
     EditText txt1,txt2;
     Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.text2);
        btn1 = findViewById(R.id.login);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String prn=txt1.getText().toString();
                final String password=txt2.getText().toString();
               
                if(prn.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter your Email And Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(prn)) {
                                final String getPassword = snapshot.child(prn).child("pass1").getValue(String.class);


                                assert getPassword != null;
                                if (getPassword.equals(password)) {
                                            String pn = snapshot.child(prn).child("no").getValue(String.class);
                                            String nam = snapshot.child(prn).child("studname").getValue(String.class);
                                            String dep = snapshot.child(prn).child("studDepartment").getValue(String.class);
                                            String year = snapshot.child(prn).child("studyear").getValue(String.class);
                                            String bus = snapshot.child(prn).child("studBus").getValue(String.class);

                                            String mail = snapshot.child(prn).child("email1").getValue(String.class);

                                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                            intent.putExtra("studname", nam);
                                            intent.putExtra("studDepartment", dep);
                                            intent.putExtra("studyear", year);
                                            intent.putExtra("studBus", bus);
                                            intent.putExtra("no", pn);
                                            intent.putExtra("email1", mail);

                                            startActivity(intent);
                                            txt1.getText().clear();
                                            txt2.getText().clear();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                        }

                            }else{
                                Toast.makeText(MainActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }
        });
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(MainActivity.this, Mainscreen.class);
        startActivity(intent);


    }



}