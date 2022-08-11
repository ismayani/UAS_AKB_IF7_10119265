package com.ismemaya.uasakbif_710119265;

// 10119265_IsmayaniSetyaningrum_IF7

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;

public class AddEditPena extends AppCompatActivity {

    private EditText eJudul, eTtb, eKategori, eIsi;
    private Button btnSimpan, btnBatal;
    private PenaDatabase database;
    private long id;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_pena);
        intent = getIntent();
        id = intent.getLongExtra(PenaDatabase.id_pena, 0);

        if (intent.hasExtra(PenaDatabase.id_pena)) {
            setTitle("Edit Catatan");
        }else{
            setTitle("Tambah Catatan");
        }
        eJudul = findViewById(R.id.eJudul);
        eTtb = findViewById(R.id.eTtb);
        eKategori = findViewById(R.id.eKategori);
        eIsi = findViewById(R.id.eIsi);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnBatal = findViewById(R.id.btnBatal);

        database = new PenaDatabase(this);
// set ttl
        eTtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar getCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEditPena.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar setCalendar = Calendar.getInstance();
                        setCalendar.set(Calendar.YEAR, year);
                        setCalendar.set(Calendar.MONTH, month);
                        setCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String setCurrentDate = DateFormat.getDateInstance().format(setCalendar.getTime());
                        eTtb.setText(setCurrentDate);

                    }
                }, getCalendar.get(Calendar.YEAR), getCalendar.get(Calendar.MONTH), getCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        // set btnsimpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesSimpan();

            }
        });

        //set btnbatal
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getPena();
    }
    private void getPena() {
        Cursor cursor = database.getPena(id);
        if (cursor.moveToFirst()) {
            String judul = cursor.getString(cursor.getColumnIndexOrThrow(PenaDatabase.judul));
            String ttb = cursor.getString(cursor.getColumnIndexOrThrow(PenaDatabase.ttb));
            String kategori = cursor.getString(cursor.getColumnIndexOrThrow(PenaDatabase.kategori));
            String isi = cursor.getString(cursor.getColumnIndexOrThrow(PenaDatabase.isi));

            eJudul.setText(judul);
            eTtb.setText(ttb);
            eKategori.setText(kategori);
            eIsi.setText(isi);
        }
    }


    private void prosesSimpan() {
        String judul = eJudul.getText().toString().trim();
        String ttb = eTtb.getText().toString().trim();
        String kategori = eKategori.getText().toString().trim();
        String isi = eIsi.getText().toString().trim();

        if (judul.isEmpty()){
            eJudul.setError("Judul tidak boleh kosong");
        } else if (ttb.isEmpty()){
            eTtb.setError("Tanggal dibuat tidak boleh kosong");
        } else if (kategori.isEmpty()){
            eTtb.setError("Kategori tidak boleh kosong");
        } else if (isi.isEmpty()) {
            eIsi.setError("Isi catatan tidak boleh kosong");
        }else {
            ContentValues values = new ContentValues();
            values.put(PenaDatabase.judul, judul);
            values.put(PenaDatabase.ttb, ttb);
            values.put(PenaDatabase.kategori, kategori);
            values.put(PenaDatabase.isi, isi);
            if (intent.hasExtra(PenaDatabase.id_pena)){
                database.updatePena(values, id);
            }else {
                database.insertPena(values);
            }
            finish();
        }
    }
}
