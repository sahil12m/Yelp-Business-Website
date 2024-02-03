package com.example.initialscreen;

public class TabItem {
    private int bsrno;
    private String bImageUrl;
    private String bbusname;
    private String bratings;
    private String bdist;

    public TabItem(int srno, String imageUrl, String busname, String ratings, String dist) {
        bsrno = srno;
        bImageUrl = imageUrl;
        bbusname = busname;
        bratings = ratings;
        bdist = dist;
    }

    public int getsrno() {
        return bsrno;
    }

    public String getImageUrl() {
        return bImageUrl;
    }

    public String getbusname() {
        return bbusname;
    }

    public String getratings() {
        return bratings;
    }

    public String getdist() {
        return bdist;
    }
}
