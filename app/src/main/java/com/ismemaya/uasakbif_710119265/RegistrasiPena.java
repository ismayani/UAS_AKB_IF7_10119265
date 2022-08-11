package com.ismemaya.uasakbif_710119265;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrasiPena extends AppCompatActivity {

    private FirebaseAuth mauth;
    private EditText email,password;
    private Button register_btn;
    private TextView login_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_pena);

        mauth=FirebaseAuth.getInstance();
        email=findViewById(R.id.emailPR);
        password=findViewById(R.id.passwordPR);
        register_btn=findViewById(R.id.register_btn);
        login_text=findViewById(R.id.login_text);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registers();

            }
        });
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrasiPena.this,LoginPena.class));
            }
        });
    }

    private void Registers() {

        String user= email.getText().toString().trim();
        String pass= password.getText().toString().trim();
        if (user.isEmpty()){
            email.setError("Email tidak boleh kosong");

        }if (pass.isEmpty()){
            password.setError("Password tidak boleh kosong");
        }
        else{
            mauth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegistrasiPena.this, "User registered successfully!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(RegistrasiPena.this,MainActivity.class));

                    }
                    else
                    {
                        Toast.makeText(RegistrasiPena.this, "Registration Failed!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}