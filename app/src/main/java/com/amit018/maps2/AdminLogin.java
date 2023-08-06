package com.amit018.maps2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends AppCompatActivity {
   EditText text1, text2;
   Button btn1,btn2;
   String email,pass;
   FirebaseAuth auth;
   FirebaseDatabase db;
   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        text1= findViewById(R.id.txt1);
        text2= findViewById(R.id.txt2);
        btn1= findViewById(R.id.btn_l);
        btn2= findViewById(R.id.btnal);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        progressDialog= new ProgressDialog(AdminLogin.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("We are creating your account");

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminLogin.this,AdminVerify.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();;
                email= text1.getText().toString();
                pass= text2.getText().toString();
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            users user = new users(email,pass);
                            String id = task.getResult().getUser().getUid();
                            db.getReference().child("users").child(id).setValue(user);
                            Toast.makeText(AdminLogin.this, "User created successfully", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(AdminLogin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}