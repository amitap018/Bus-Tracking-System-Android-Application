package com.amit018.maps2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Mainscreen extends AppCompatActivity {
 Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        btn1 = findViewById(R.id.studentlog);
        btn2 = findViewById(R.id.adminlog);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainscreen.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          Intent intent = new Intent(Mainscreen.this,AdminVerify.class);
          startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed(){
        moveTaskToBack(true);

    }
}