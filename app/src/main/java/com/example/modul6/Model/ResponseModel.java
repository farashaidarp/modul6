package com.example.modul6.Model;

import java.util.List;

public class ResponseModel {//mengambil kode dan pesan
    private int kode;
    private String pesan;
    private List<com.example.modul6.Model.DataModel> data;//disesuakian dengan api library

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<com.example.modul6.Model.DataModel> getData() {
        return data;
    }

    public void setData(List<com.example.modul6.Model.DataModel> data) {
        this.data = data;
    }
}
