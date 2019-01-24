package edu.uta.avb9570.maverickride;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).apply();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }

    public void setfirstname(String firstname) {
        prefs.edit().putString("firstname", firstname).apply();
    }

    public String getfirstname() {
        String firstname = prefs.getString("firstname","");
        return firstname;
    }

    public void setaacm(String aacm) {
        prefs.edit().putString("aacm", aacm).apply();
    }

    public String getaacm() {
        String aacm = prefs.getString("aacm","");
        return aacm;
    }

    public void setuserrole(String userrole){
        prefs.edit().putString("userrole", userrole).apply();
    }

    public String getuserrole(){
        String userrole = prefs.getString("userrole", "");
        return userrole;
    }

    public void setstartdate(String startdate){
        prefs.edit().putString("startdate", startdate).apply();
    }

    public String getstartdate(){
        String startdate = prefs.getString("startdate", "");
        return startdate;
    }

    public void setstarttime(String starttime){
        prefs.edit().putString("starttime", starttime).apply();
    }

    public String getstarttime(){
        String starttime = prefs.getString("starttime", "");
        return starttime;
    }

    public void setenddate(String enddate){
        prefs.edit().putString("enddate", enddate).apply();
    }

    public String getenddate(){
        String enddate = prefs.getString("enddate", "");
        return enddate;
    }

    public void setendtime(String endtime){
        prefs.edit().putString("endtime", endtime).apply();
    }

    public String getendtime(){
        String endtime = prefs.getString("endtime", "");
        return endtime;
    }

    public void setcapacity(String capacity){
        prefs.edit().putString("capacity", capacity).apply();
    }

    public String getcapacity(){
        String capacity = prefs.getString("capacity", "");
        return capacity;
    }

    public void setcarname(String carname){
        prefs.edit().putString("carname", carname).apply();
    }

    public String getcarname(){
        String carname = prefs.getString("carname", "");
        return carname;
    }

    public void setweekdayrate(String weekdayrate){
        prefs.edit().putString("weekdayrate", weekdayrate).apply();
    }

    public String getweekdayrate(){
        String weekdayrate = prefs.getString("weekdayrate", "");
        return weekdayrate;
    }

    public void setweekendrate(String weekendrate){
        prefs.edit().putString("weekendrate", weekendrate).apply();
    }

    public String getweekendrate(){
        String weekendrate = prefs.getString("weekendrate", "");
        return weekendrate;
    }

    public void setweekrate(String weekrate){
        prefs.edit().putString("weekrate", weekrate).apply();
    }

    public String getweekrate(){
        String weekrate = prefs.getString("weekrate", "");
        return weekrate;
    }

    public void setgpsrate(String gpsrate){
        prefs.edit().putString("gpsrate", gpsrate).apply();
    }

    public String getgpsrate(){
        String gpsrate = prefs.getString("gpsrate", "");
        return gpsrate;
    }

    public void setonstarrate(String onstarrate){
        prefs.edit().putString("onstarrate", onstarrate).apply();
    }

    public String getonstarrate(){
        String onstarrate = prefs.getString("onstarrate", "");
        return onstarrate;
    }

    public void setsiriusxmrate(String siriusxmrate){
        prefs.edit().putString("siriusxmrate", siriusxmrate).apply();
    }

    public String getsiriusxmrate(){
        String siriusxmrate = prefs.getString("siriusxmrate", "");
        return siriusxmrate;
    }

    public void setbaseprice(String baseprice){
        prefs.edit().putString("baseprice", baseprice).apply();
    }

    public String getbaseprice(){
        String baseprice = prefs.getString("baseprice", "");
        return baseprice;
    }

    public void settaxapplied(String taxapplied){
        prefs.edit().putString("taxapplied", taxapplied).apply();
    }

    public String gettaxapplied(){
        String taxapplied = prefs.getString("taxapplied", "");
        return taxapplied;
    }

    public void setdiscount(String discount){
        prefs.edit().putString("discount", discount).apply();
    }

    public String getdiscount(){
        String discount = prefs.getString("discount", "");
        return discount;
    }

    public void settotalamt(String totalamt){
        prefs.edit().putString("totalamt", totalamt).apply();
    }

    public String gettotalamt(){
        String totalamt = prefs.getString("totalamt", "");
        return totalamt;
    }

    public void setrentalid(String rentalid){
        prefs.edit().putString("rentalid", rentalid).apply();
    }

    public String getrentalid(){
        String rentalid = prefs.getString("rentalid", "");
        return rentalid;
    }

    public void setaddrentalutaid(String addrentalutaid){
        prefs.edit().putString("addrentalutaid", addrentalutaid).apply();
    }

    public String getaddrentalutaid(){
        String addrentalutaid = prefs.getString("addrentalutaid", "");
        return addrentalutaid;
    }

    public void setviewbookingsdate(String viewbookingsdate){
        prefs.edit().putString("viewbookingsdate", viewbookingsdate).apply();
    }

    public String getviewbookingsdate(){
        String viewbookingsdate = prefs.getString("viewbookingsdate", "");
        return viewbookingsdate;
    }

    public void setedituserutaid(String edituserutaid){
        prefs.edit().putString("edituserutaid", edituserutaid).apply();
    }

    public String getedituserutaid(){
        String edituserutaid = prefs.getString("edituserutaid", "");
        return edituserutaid;
    }
}
