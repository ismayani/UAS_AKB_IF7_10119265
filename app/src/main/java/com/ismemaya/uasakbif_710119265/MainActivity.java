package com.ismemaya.uasakbif_710119265;

// 10119265_IsmayaniSetyaningrum_IF7

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPena;
    private PenaAdapter penaAdapter;
    private FloatingActionButton fabAddPena;
    private PenaDatabase database;
    private LinearLayout linearEditPena, linearDeletePena;
    private Button btnBatalPopup;
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth=FirebaseAuth.getInstance();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navmenu);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_identitas:
                        startActivity(new Intent(getApplicationContext()
                                ,Identitas.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_info:
                        startActivity(new Intent(getApplicationContext()
                                ,Info.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        database = new PenaDatabase(this);

        rvPena = findViewById(R.id.rvPena);
        fabAddPena = findViewById(R.id.fabAddPena);

        rvPena.setLayoutManager(new LinearLayoutManager(this));
        penaAdapter = new PenaAdapter(this, database.getAllPena());
        rvPena.setAdapter(penaAdapter);
        penaAdapter.swapCursor(database.getAllPena());

        fabAddPena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddEditPena.class));
            }
        });

        penaAdapter.setOnClickListenerPena(new PenaAdapter.onClickListenerPena() {
            @Override
            public void onItemClikPena(long id) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View view = inflater.inflate(R.layout.popup, null);
                linearEditPena = view.findViewById(R.id.linearEditPena);
                linearDeletePena = view.findViewById(R.id.linearDeletePena);
                btnBatalPopup = view.findViewById(R.id.btnBatalPopup);

                Dialog popup = new Dialog( MainActivity.this);
                popup.setContentView(view);
                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popup.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        linearEditPena.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                Intent editPena = new Intent(MainActivity.this, AddEditPena.class);
                                editPena.putExtra(PenaDatabase.id_pena, id);
                                startActivity(editPena);
                                popup.dismiss();
                            }
                        });
                        linearDeletePena.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Konfirmasi");
                                builder.setMessage("Apakah anda yakin ingin menghapus data");
                                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        database.deletePena(id);
                                        popup.dismiss();
                                        penaAdapter.swapCursor(database.getAllPena());
                                    }
                                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        popup.dismiss();
                                    }
                                });
                                AlertDialog popupKonfirmasi = builder.create();
                                popupKonfirmasi.show();

                            }
                        });
                        btnBatalPopup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popup.dismiss();
                            }
                        });
                    }
                });
                popup.show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        penaAdapter.swapCursor(database.getAllPena());
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser=mauth.getCurrentUser();
        if (currentUser==null){
            startActivity(new Intent(MainActivity.this,LoginPena.class));
        }
    }

    public void onBackPressed(){
        Toast.makeText(MainActivity.this, "Please Click the Home Button", Toast.LENGTH_LONG).show();
    }
}