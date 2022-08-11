package com.ismemaya.uasakbif_710119265;

// 10119265_IsmayaniSetyaningrum_IF7

import java.util.ArrayList;

public class InfoObject {
    int imgObject;
    String txInfo;

    public InfoObject(int ivObject, String txTips) {
        this.imgObject = ivObject;
        this.txInfo = txTips;
    }

    public static ArrayList<InfoObject> createData() {
        ArrayList<InfoObject> justObject = new ArrayList<>();
        justObject.add(new InfoObject(R.drawable.tampil,"Tulis catatan harian-mu dengan Klik Button Tambah "));
        justObject.add(new InfoObject(R.drawable.calendar,"Beri Tanggal pada Catatan dengan Double Klik pada Textfield"));
        justObject.add(new InfoObject(R.drawable.editdelete,"Edit dan Hapus Catatanmu dengan Klik Judul Catatan"));
        justObject.add(new InfoObject(R.drawable.catatan,"Lihat List Catatan-mu pada Beranda"));
        return justObject;
    }
}
