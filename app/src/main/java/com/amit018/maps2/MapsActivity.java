package com.amit018.maps2;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.amit018.maps2.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , LocationListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private DatabaseReference reference;
    LocationManager manager;
    private  final  int MIN_TIME =1000;
    private final int MIN_DISTANCE=10;
    Marker marker;
    Button btn_float;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        btn_float=findViewById(R.id.floatingBtn);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("My Notification","My Notification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }





        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        reference =FirebaseDatabase.getInstance().getReference().child("GPS");
        manager= (LocationManager) getSystemService(LOCATION_SERVICE);
     //   FirebaseDatabase.getInstance().getReference().setValue("Bus Tracking App");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLocationUpdates();
        readChanges();
    }

    private void readChanges() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    try {
                        MyLocation location=snapshot.getValue(MyLocation.class);
                        if(location != null){
                            LatLng liveLoc = new LatLng(location.getLatitude(),location.getLongitude());
                            marker.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
                         //   mMap.setMinZoomPreference(15);
                         //   mMap.getUiSettings().setAllGesturesEnabled(true);
                        //    mMap.getUiSettings().setZoomControlsEnabled(true);
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(liveLoc));
                        }
                    }catch (Exception e){
                        Toast.makeText(MapsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }


                if(snapshot.exists()){
                    try {
                        MyLocation loc=snapshot.getValue(MyLocation.class);
                        if(loc!= null){
                            double latt=loc.getLatitude();
                            double longg=loc.getLongitude();
                            /*
                            if(latt==17.0647117 && longg==74.28309054){

                            }

                             */
                        }
                    }catch (Exception e){
                        Toast.makeText(MapsActivity.this, "No Location", Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getLocationUpdates() {
        if (manager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                } else if (manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                } else {
                    // Toast.makeText(this, "Please Enable Location", Toast.LENGTH_SHORT).show();
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        }
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==101){
            if(grantResults.length > 0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                getLocationUpdates();
            }else{
                Toast.makeText(this, "Location Permission required", Toast.LENGTH_SHORT).show();
            }
        }
    }





    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
          LatLng rit = new LatLng(17.06372809301601, 74.28329664094046);
          marker = mMap.addMarker(new MarkerOptions().position(rit).title("Rajarambapu Institute Of Technology"));
           mMap.setMinZoomPreference(12);
           mMap.getUiSettings().setAllGesturesEnabled(true);
           mMap.getUiSettings().setZoomControlsEnabled(true);
           mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(rit));




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    try {
                        MyLocation loc = snapshot.getValue(MyLocation.class);
                        if (loc != null) {
                            double latt = loc.getLatitude();
                            double longg = loc.getLongitude();

                            if ((latt > 17.062 && latt<17.064) && (longg >74.28  && longg <74.29)) {
                                NotificationCompat.Builder builder=new NotificationCompat.Builder(MapsActivity.this,"My Notification");
                                builder.setContentTitle("New Notification");
                                builder.setContentText("Your bus is at Rajarambapu Institute of technology. ");
                                builder.setSmallIcon(R.drawable.bus1);

                                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MapsActivity.this);
                                managerCompat.notify(1,builder.build());
                                builder.setAutoCancel(true);
                            }

                            if ((latt >= 16.84300 && latt<=16.84400) && (longg >=74.63100  && longg <=74.63200)) {
                                NotificationCompat.Builder builder=new NotificationCompat.Builder(MapsActivity.this,"My Notification");
                                builder.setContentTitle("New Notification");
                                builder.setContentText("Your bus is at SS Colony. ");
                                builder.setSmallIcon(R.drawable.bus1);

                                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MapsActivity.this);
                                managerCompat.notify(1,builder.build());
                                builder.setAutoCancel(true);
                            }
                            if ((latt >16.8310 && latt<16.8312) && (longg > 74.644 && longg<74.646)) {
                                NotificationCompat.Builder builder=new NotificationCompat.Builder(MapsActivity.this,"My Notification");
                                builder.setContentTitle("New Notification");
                                builder.setContentText("Your bus is at Mission Hospital Chowk ");
                                builder.setSmallIcon(R.drawable.bus1);

                                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MapsActivity.this);
                                managerCompat.notify(1,builder.build());
                                builder.setAutoCancel(true);
                            }
                            if ((latt >16.8354 && latt<16.8365) && (longg > 74.629 && longg<74.632)) {
                                NotificationCompat.Builder builder=new NotificationCompat.Builder(MapsActivity.this,"My Notification");
                                builder.setContentTitle("New Notification");
                                builder.setContentText("Your bus is at Sevasadan Hospital");
                                builder.setSmallIcon(R.drawable.bus1);

                                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MapsActivity.this);
                                managerCompat.notify(1,builder.build());
                                builder.setAutoCancel(true);
                            }
                            if ((latt >16.845 && latt<16.847) && (longg > 74.602 && longg<74.604)) {
                                NotificationCompat.Builder builder=new NotificationCompat.Builder(MapsActivity.this,"My Notification");
                                builder.setContentTitle("New Notification");
                                builder.setContentText("Your bus is at Walchand College of Engineering");
                                builder.setSmallIcon(R.drawable.bus1);

                                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MapsActivity.this);
                                managerCompat.notify(1,builder.build());
                                builder.setAutoCancel(true);
                            }
                            if((latt >16.8482 && latt<16.8485) && (longg > 74.595 && longg<74.597)) {
                                NotificationCompat.Builder builder=new NotificationCompat.Builder(MapsActivity.this,"My Notification");
                                builder.setContentTitle("New Notification");
                                builder.setContentText("Your bus is at Vishrambag Chowk ");
                                builder.setSmallIcon(R.drawable.bus1);

                                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MapsActivity.this);
                                managerCompat.notify(1,builder.build());
                                builder.setAutoCancel(true);

                            }
                            if((latt >=16.853 && latt<16.854) && (longg >=  74.564 && longg<74.566)) {
                                NotificationCompat.Builder builder=new NotificationCompat.Builder(MapsActivity.this,"My Notification");
                                builder.setContentTitle("New Notification");
                                builder.setContentText("Your bus is at Sangli Bus Stand ");
                                builder.setSmallIcon(R.drawable.bus1);

                                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MapsActivity.this);
                                managerCompat.notify(1,builder.build());
                                builder.setAutoCancel(true);

                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(MapsActivity.this, "No Location", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(location!= null){
            saveLocation(location);
        }else{
           // Toast.makeText(this, "Turn on location", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveLocation(Location location) {
        reference.setValue(location);
    }
}