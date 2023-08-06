package com.amit018.maps2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
TextView t1,t2,t3,t4,t5;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1=findViewById(R.id.studname11);
        t2=findViewById(R.id.prn11);
        t3=findViewById(R.id.year11);
        t4=findViewById(R.id.dept11);
        t5=findViewById(R.id.busno11);
        btn=findViewById(R.id.track_btn);

        showData();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(LoginActivity.this,MapsActivity.class);
            startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item0:
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
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

    int count=0;
    @Override
    public void onBackPressed(){
        count++;
        if(count==2){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            count=0;
        }
        Toast.makeText(this, "Press back again to logout", Toast.LENGTH_SHORT).show();


    }


}