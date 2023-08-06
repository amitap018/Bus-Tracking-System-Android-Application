package com.amit018.maps2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    EditText textt1,textt2,textt3,textt4,textt5,etemail,password;
    Button btn1;

    TextInputLayout til_email;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://focus-nucleus-351210-default-rtdb.firebaseio.com/");
    boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textt1 = findViewById(R.id.studentname);
        textt2 = findViewById(R.id.prn);
        textt3 = findViewById(R.id.department);
        textt4 = findViewById(R.id.year);
        textt5 = findViewById(R.id.busallocated);
        btn1 = findViewById(R.id.nextpage);
        etemail=findViewById(R.id.email);
        password=findViewById(R.id.password);
        til_email=findViewById(R.id.outlinedTextField7);

    //    studentdb= FirebaseDatabase.getInstance().getReference().child("Students");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
insertStudentData();
            }
        });
    }

    private  void insertStudentData(){
        String studName = textt1.getText().toString();
        String studPRN = textt2.getText().toString();
        String no = textt2.getText().toString();
        String studDepartment = textt3.getText().toString();
        String studyear = textt4.getText().toString();
        String studBus = textt5.getText().toString();
        String email1=etemail.getText().toString();
        String pass1= password.getText().toString();
        if(studName.isEmpty()||studPRN.isEmpty()||studDepartment.isEmpty()||studyear.isEmpty()||studBus.isEmpty()||email1.isEmpty()||pass1.isEmpty()){
            Toast.makeText(this, "Enter all Details", Toast.LENGTH_SHORT).show();
        }else if(!isEmailValid(email1)){
            til_email.setError("Invalid Email");
        }
        else {

            databaseReference.child("Students").child(studPRN).child("studname").setValue(studName);
            databaseReference.child("Students").child(studPRN).child("no").setValue(no);
            databaseReference.child("Students").child(studPRN).child("studDepartment").setValue(studDepartment);
            databaseReference.child("Students").child(studPRN).child("studyear").setValue(studyear);
            databaseReference.child("Students").child(studPRN).child("studBus").setValue(studBus);
            databaseReference.child("Students").child(studPRN).child("email1").setValue(email1);
            databaseReference.child("Students").child(studPRN).child("pass1").setValue(pass1);








            textt1.getText().clear();
            textt2.getText().clear();
            textt3.getText().clear();
            textt4.getText().clear();
            textt5.getText().clear();
            etemail.getText().clear();
            password.getText().clear();


            /*
            StudentDetails studentDetails=new StudentDetails(studName,studPRN,studDepartment,studyear,studBus,email1,pass1);
            databaseReference.child("Students")
                    .child(studentDetails.prn)
                    .setValue(studentDetails);

             */

            Toast.makeText(this, "Stuent Registerd", Toast.LENGTH_SHORT).show();



        }


       // Students students=new Students(studName,studPRN,studDepartment,studyear,email1,pass1,studBus);

       // studentdb.push().setValue(students);
      //  Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed(){

        Intent intent = new Intent(MainActivity2.this, admin_menu.class);
        startActivity(intent);
        finish();

    }
}