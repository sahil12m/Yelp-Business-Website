package com.example.initialscreen;

public class BookItem {

    private int bsrno;
    private String bname="";
    private String bdate;
    private String btime;
    private String bemail;

    public BookItem(int sNo, String busName, String date, String time, String email ) {
        bsrno = sNo;
        bname = busName;
        bdate = date;
        btime = time;
        bemail = email;
    }

    public int getsrno() {
        return bsrno;
    }

    public String getbname() {
        return bname;
    }

    public String getbdate() {
        return bdate;
    }

    public String getbtime() {
        return btime;
    }

    public String getbemail() {
        return bemail;
    }
}
