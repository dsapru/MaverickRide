package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservationSummary extends AppCompatActivity {
    DatabaseHelper db;
    Button reservationsummarycontinuebtn;
    Session session;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static long getDayCount(String start, String end) {
        long diff;
        if (start.equals("")) {
            diff = 0;
            return diff;
        } else {
            diff = -1;
            try {
                Date dateStart = simpleDateFormat.parse(start);
                Date dateEnd = simpleDateFormat.parse(end);
                //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
                diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
            } catch (Exception e) {
                //handle the exception according to your own situation
            }
        }
        return diff;
    }

    public static int getNumberOfWeekEnds(String start, String end) {
        int WeekEnds = 0;
        try {
            Date dateStart = simpleDateFormat.parse(start);
            Date dateEnd = simpleDateFormat.parse(end);

            Calendar c1 = Calendar.getInstance();
            c1.clear();
            c1.setTime(dateStart);
            Calendar c2 = Calendar.getInstance();
            c2.clear();
            c2.setTime(dateEnd);


            while (c2.after(c1)) {
                if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                    WeekEnds++;
                else {
                }
                c1.add(Calendar.DATE, 1);

            }

            if (c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                WeekEnds++;
            }


        } catch (Exception e) {

        }
        //System.out.println(WeekEnds);
        return WeekEnds;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary);

        reservationsummarycontinuebtn = (Button) findViewById(R.id.reservationsummarycontinue);
        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());

        TextView utaidres = (TextView) findViewById(R.id.utaidres);
        TextView nameres = (TextView) findViewById(R.id.nameres);
        TextView carnameres = (TextView) findViewById(R.id.carnameres);
        TextView basepriceres = (TextView) findViewById(R.id.basepriceres);
        TextView taxappliedres = (TextView) findViewById(R.id.taxappliedres);
        TextView discountres = (TextView) findViewById(R.id.discountres);
        TextView startdateres = (TextView) findViewById(R.id.startdateres);
        TextView starttimeres = (TextView) findViewById(R.id.starttimeres);
        TextView enddateres = (TextView) findViewById(R.id.enddateres);
        TextView endtimeres = (TextView) findViewById(R.id.endtimeres);
        TextView totalamtres = (TextView) findViewById(R.id.totalamtres);

        if(session.getuserrole().equalsIgnoreCase("user")){
            utaidres.setText(session.getusename());
        }
        else{
            utaidres.setText(session.getaddrentalutaid());
        }
        nameres.setText(session.getfirstname());
        carnameres.setText(session.getcarname());

        Long dayCount = getDayCount(session.getstartdate(), session.getenddate());
        int daysCount = (int) (dayCount + 1);
        float weekprice = 0;
        if (daysCount >= 7) {
            int weekCount = daysCount / 7;
            int remainingDays = daysCount % 7;
            weekprice = (weekCount * Float.parseFloat(session.getweekrate()));
        } else {
            int weekCount = 0;
            int remainingDays = 0;
        }
        int noofweekends = getNumberOfWeekEnds(session.getstartdate(), session.getenddate());
        float weekendprice = noofweekends * Float.parseFloat(session.getweekendrate());
        int numberofweekdays = daysCount - noofweekends;
        float weekdayprice = numberofweekdays * Float.parseFloat(session.getweekdayrate());
        float baseprice = weekprice + weekendprice + weekdayprice;

        basepriceres.setText(Float.toString(baseprice));
        session.setbaseprice(Float.toString(baseprice));

        float taxapplied = baseprice * (float) 0.08;
        taxappliedres.setText(Float.toString(taxapplied));
        session.settaxapplied(Float.toString(taxapplied));

        float finalamt = baseprice + (daysCount * Float.parseFloat(session.getgpsrate())) + (daysCount * Float.parseFloat(session.getonstarrate()) + (daysCount * Float.parseFloat(session.getsiriusxmrate())));


        float discount = 0;
        if (session.getaacm().equalsIgnoreCase("yes")) {
            discount = finalamt * (float) 0.1;
        } else {
            discount = 0;
        }
        float totalamt = finalamt - discount + taxapplied;
        totalamtres.setText(Float.toString(totalamt));
        session.settotalamt(Float.toString(totalamt));
        discountres.setText(Float.toString(discount));
        session.setdiscount(Float.toString(discount));

        startdateres.setText(session.getstartdate());
        starttimeres.setText(session.getstarttime());
        enddateres.setText(session.getenddate());
        endtimeres.setText(session.getendtime());

        reservationsummarycontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.getuserrole().equalsIgnoreCase("manager")){
                    Boolean confirmrental = db.confirmrental(session.getaddrentalutaid(), session.getfirstname(), session.getcarname(), session.getbaseprice(), session.gettaxapplied(), session.getdiscount(), session.getstartdate(),session.getstarttime(), session.getenddate() ,session.getendtime() ,session.gettotalamt());
                    if(confirmrental == true){
                        Toast.makeText(getApplicationContext(), "Rental added successfully for "+session.getaddrentalutaid(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ReservationSummary.this, ManagerHome.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Add rental failed", Toast.LENGTH_LONG).show();
                    }
                }
                else if(session.getuserrole().equalsIgnoreCase("user")){
                    Boolean confirmrental = db.confirmrental(session.getusename(), session.getfirstname(), session.getcarname(), session.getbaseprice(), session.gettaxapplied(), session.getdiscount(), session.getstartdate(),session.getstarttime(), session.getenddate() ,session.getendtime() ,session.gettotalamt());
                    if(confirmrental == true){
                        Toast.makeText(getApplicationContext(), "Rental confirmed", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ReservationSummary.this, ConfirmationSummary.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Rental confirmation failed", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed to confirm rental", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}