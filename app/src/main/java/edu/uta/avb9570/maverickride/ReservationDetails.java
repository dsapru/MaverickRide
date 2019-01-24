package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReservationDetails extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());

        TextView bookingidres = (TextView) findViewById(R.id.bookingidres);
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

        String rentalid = session.getrentalid();
        ArrayList<String> rentaldetails = new ArrayList<String>();
        rentaldetails = db.getrentaldetails(rentalid);
        String rentaldetails1 = rentaldetails.get(0);
        String[] rentaldetails2 = rentaldetails1.split("\t\t\t");

        bookingidres.setText(rentaldetails2[0]);
        utaidres.setText(rentaldetails2[1]);
        nameres.setText(rentaldetails2[2]);
        carnameres.setText(rentaldetails2[3]);
        basepriceres.setText(rentaldetails2[4]);
        taxappliedres.setText(rentaldetails2[5]);
        discountres.setText(rentaldetails2[6]);
        startdateres.setText(rentaldetails2[7]);
        starttimeres.setText(rentaldetails2[8]);
        enddateres.setText(rentaldetails2[9]);
        endtimeres.setText(rentaldetails2[10]);
        totalamtres.setText(rentaldetails2[11]);

        Button myreservations = (Button)findViewById(R.id.myreservations);
        myreservations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(session.getuserrole().equalsIgnoreCase("user")){
                    startActivity(new Intent(ReservationDetails.this, MyReservations.class));
                }
                else{
                    startActivity(new Intent(ReservationDetails.this, DeleteRental.class));
                }

            }
        });
    }
}
