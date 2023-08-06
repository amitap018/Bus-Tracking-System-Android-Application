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

public class AdminVerify extends AppCompatActivity {

    EditText text1,text2;
    Button btn1;
    String email,pass;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verify);

        text1= findViewById(R.id.txtmail);
        text2= findViewById(R.id.txtpass);
        btn1= findViewById(R.id.buttonlogin);
        auth= FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(AdminVerify.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                email= text1.getText().toString();
                pass= text2.getText().toString();
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            Intent intent = new Intent(AdminVerify.this,admin_menu.class);
                            startActivity(intent);
                            text1.getText().clear();
                            text2.getText().clear();
                        }
                        else {
                            Toast.makeText(AdminVerify.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
    @Override
    public void onBackPressed(){

        Intent intent = new Intent(AdminVerify.this, Mainscreen.class);
        startActivity(intent);

    }
}