package com.ismemaya.uasakbif_710119265;

// 10119265_IsmayaniSetyaningrum_IF7

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PenaDatabase extends SQLiteOpenHelper {

    public static final String database_name = "Pena";
    public static final int database_version = 4;
    public static final String tabel_pena = "tabel_pena";
    public static final String id_pena = "id_pena";
    public static final String judul = "judul";
    public static final String ttb = "ttb";
    public static final String kategori = "kategori";
    public static final String isi = "isi";
    public static final String created = "created";

    private SQLiteDatabase db;

    public PenaDatabase(@Nullable Context context) {
        super(context, database_name, null, database_version);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tabel_pena + "("
                + id_pena + " integer primary key autoincrement,"
                + judul + " text not null, "
                + ttb + " text not null, "
                + kategori + " text not null, "
                + isi + " text not null, "
                + created + " timestamp default (datetime('now', 'localtime')))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tabel_pena );
    }
    public void insertPena(ContentValues values){
        db.insert(tabel_pena, null, values);
    }

    public void updatePena(ContentValues values, long id){
        db.update(tabel_pena, values, id_pena + "=" + id, null);
    }
    public void deletePena(long  id){
        db.delete(tabel_pena, id_pena + "=" + id, null);
    }
    public Cursor getAllPena(){
        return db.query(tabel_pena, null ,null, null, null, null, judul + " ASC");
    }
    public Cursor getPena(long id) {
        return db.rawQuery("select * from "+ tabel_pena + " where " + id_pena + "=" + id, null);
    }

}
