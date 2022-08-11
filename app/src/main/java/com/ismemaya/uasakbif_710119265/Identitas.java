package com.ismemaya.uasakbif_710119265;

// 10119265_IsmayaniSetyaningrum_IF7

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class Identitas extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth mauth;
    private Button logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identitas);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navmenu);
        bottomNavigationView.setSelectedItemId(R.id.nav_identitas);
        logout_btn=findViewById(R.id.logout_btn);
        mauth=FirebaseAuth.getInstance();
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proses_logout();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_identitas:
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
    }

    private void proses_logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Identitas.this,LoginPena.class));
    }
    public void onBackPressed(){
        Toast.makeText(Identitas.this, "Please Click the Home Button", Toast.LENGTH_LONG).show();
    }
}