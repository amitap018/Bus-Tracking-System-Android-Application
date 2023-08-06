package com.amit018.maps2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin_menu extends AppCompatActivity {
    EditText e1;
    Button b1, b2, b3, b4;
    Button btn_ok, btn_close, btn_ok1, btn_close1,btn_track_admin;
    EditText enter_prn, enter_prn1;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://focus-nucleus-351210-default-rtdb.firebaseio.com/");


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);



        b1 = findViewById(R.id.btn_view);
        b2 = findViewById(R.id.btn_insert);
        b3 = findViewById(R.id.btn_delete);
        btn_track_admin=findViewById(R.id.btn_track_admin);


        btn_track_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin_menu.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_menu.this, MainActivity2.class);
                startActivity(intent);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog2();
                /*
                String prn=e1.getText().toString();
                if(prn.isEmpty()) {
                    Toast.makeText(admin_menu.this, "Please enter PRN", Toast.LENGTH_SHORT).show();
                }else{
                    try {

                    databaseReference.child("Students").child(e1.getText().toString().trim())
                            .removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Log.d("---->", error.getMessage())
                            });

                        DatabaseReference databa = FirebaseDatabase.getInstance().getReference("Students").child(prn);
                        Task<Void> mTask = databa.removeValue();
                        Toast.makeText(admin_menu.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e1.getText().clear();
                        Toast.makeText(admin_menu.this, "Student does not exists", Toast.LENGTH_SHORT).show();
                    }
                }


                */

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                /*
                String prn = e1.getText().toString();

                databaseReference.child("Students")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.hasChild(prn)) {
                                    String pn = snapshot.child(prn).child("no").getValue(String.class);
                                    String nam = snapshot.child(prn).child("studname").getValue(String.class);
                                    String dep = snapshot.child(prn).child("studDepartment").getValue(String.class);
                                    String year = snapshot.child(prn).child("studyear").getValue(String.class);
                                    String bus = snapshot.child(prn).child("studBus").getValue(String.class);

                                    String mail = snapshot.child(prn).child("email1").getValue(String.class);

                                    Intent intent = new Intent(admin_menu.this, viewpersonly.class);
                                    intent.putExtra("studname", nam);
                                    intent.putExtra("studDepartment", dep);
                                    intent.putExtra("studyear", year);
                                    intent.putExtra("studBus", bus);
                                    intent.putExtra("no", pn);
                                    intent.putExtra("email1", mail);

                                    startActivity(intent);
                                }else{
                                    Toast.makeText(admin_menu.this, "Student does not exists", Toast.LENGTH_SHORT).show();
                                }
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                 */
            }
        });
    }



    private void showDialog2() {
        AlertDialog.Builder alert;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }
        else{
            alert = new AlertDialog.Builder(this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.delete,null);

        enter_prn1 = view.findViewById(R.id.enter_dprn);
        btn_ok1 = view.findViewById(R.id.btn_dok);
        btn_close1 = view.findViewById(R.id.btn_dclose);

        alert.setView(view);
        alert.setCancelable(false);

        btn_ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prn1 = enter_prn1.getText().toString();
                if(prn1.isEmpty()) {
                    Toast.makeText(admin_menu.this, "Please enter PRN", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("Students")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if (snapshot.hasChild(prn1)) {

                                        DatabaseReference databa = FirebaseDatabase.getInstance().getReference("Students").child(prn1);
                                        Task<Void> mTask = databa.removeValue();
                                        Toast.makeText(admin_menu.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(admin_menu.this, "Record does not exists", Toast.LENGTH_SHORT).show();
                                    }

                    /*
                    databaseReference.child("Students").child(e1.getText().toString().trim())
                            .removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Log.d("---->", error.getMessage());



                            });
                     */





                                    //Toast.makeText(admin_menu.this, "Student does not exists", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }

                            });
                }
            }
        });
        final AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

        btn_close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }




    private void showDialog() {
        AlertDialog.Builder alert;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alert = new AlertDialog.Builder(admin_menu.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            alert = new AlertDialog.Builder(admin_menu.this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.data, null);

        enter_prn = view.findViewById(R.id.enter_prn);
        btn_ok = view.findViewById(R.id.btn_ok);
        btn_close = view.findViewById(R.id.btn_close);

        alert.setView(view);
        alert.setCancelable(false);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prn = enter_prn.getText().toString();
                databaseReference.child("Students")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.hasChild(prn)) {
                                    String pn = snapshot.child(prn).child("no").getValue(String.class);
                                    String nam = snapshot.child(prn).child("studname").getValue(String.class);
                                    String dep = snapshot.child(prn).child("studDepartment").getValue(String.class);
                                    String year = snapshot.child(prn).child("studyear").getValue(String.class);
                                    String bus = snapshot.child(prn).child("studBus").getValue(String.class);

                                    String mail = snapshot.child(prn).child("email1").getValue(String.class);

                                    Intent intent = new Intent(admin_menu.this, viewpersonly.class);
                                    intent.putExtra("studname", nam);
                                    intent.putExtra("studDepartment", dep);
                                    intent.putExtra("studyear", year);
                                    intent.putExtra("studBus", bus);
                                    intent.putExtra("no", pn);
                                    intent.putExtra("email1", mail);

                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(admin_menu.this, "Student does not exists", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });
        final AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    int count=0;
    @Override
    public void onBackPressed(){
        count++;
        if(count==2){
            Intent intent = new Intent(admin_menu.this, AdminVerify.class);
            startActivity(intent);
            finish();
            count=0;
        }
        Toast.makeText(this, "Press back again to logout", Toast.LENGTH_SHORT).show();


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
                Intent intent=new Intent(admin_menu.this,AdminVerify.class);
                startActivity(intent);
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteData(String Prn) {

            DatabaseReference databa = FirebaseDatabase.getInstance().getReference("Students").child(Prn);
            Task<Void> mTask = databa.removeValue();

    }

}