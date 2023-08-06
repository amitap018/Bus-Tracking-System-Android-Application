package com.amit018.maps2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class viewpersonly extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpersonly);
        t1=findViewById(R.id.studname11);
        t2=findViewById(R.id.prn11);
        t3=findViewById(R.id.year11);
        t4=findViewById(R.id.dept11);
        t5=findViewById(R.id.busno11);


        showData();

    }


    private void showData() {
        Intent intent=getIntent();
        String Stud_name=intent.getStringExtra("studname");
        String Stud_prn=intent.getStringExtra("no");
        String Stud_year=intent.getStringExtra("studyear");
        String Stud_dept=intent.getStringExtra("studDepartment");
        String Stud_bus=intent.getStringExtra("studBus");

        t1.setText(Stud_name);
        t2.setText(Stud_prn);
        t3.setText(Stud_year);
        t4.setText(Stud_dept);
        t5.setText(Stud_bus);
    }
    @Override
    public void onBackPressed(){

        Intent intent = new Intent(viewpersonly.this, admin_menu.class);
        startActivity(intent);
      //  intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        finish();
      //  onDestroy();

    }
}