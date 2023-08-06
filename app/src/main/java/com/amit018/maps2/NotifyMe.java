package com.amit018.maps2;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.maps.MapsInitializer;

public class NotifyMe extends AppCompatActivity {

    Button btn_float;

    protected void onCreate(Bundle savedInsatncesState){
        super.onCreate(savedInsatncesState);
        setContentView(R.layout.activity_maps);
     btn_float=findViewById(R.id.floatingBtn);

     btn_float.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             NotificationCompat.Builder builder=new NotificationCompat.Builder(
                     NotifyMe.this
             )
                     .setSmallIcon(R.drawable.bus1)
                     .setContentTitle("New Notification")
                     .setContentText("Your bus will arrive soon")
                     .setAutoCancel(true);

             NotificationManager notificationManager=(NotificationManager)getSystemService(
                     Context.NOTIFICATION_SERVICE
             );
             notificationManager.notify(0,builder.build());
         }
     });


    }
}
