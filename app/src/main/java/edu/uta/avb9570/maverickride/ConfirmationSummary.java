package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationSummary extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    Button myreservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_summary);

        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());

        Integer rentalid = db.getlastrentalid();

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

        bookingidres.setText(Integer.toString(rentalid));
        if(session.getuserrole().equalsIgnoreCase("user")){
            utaidres.setText(session.getusename());
        }
        else{
            utaidres.setText(session.getaddrentalutaid());
        }

        nameres.setText(session.getfirstname());
        carnameres.setText(session.getcarname());
        basepriceres.setText(session.getbaseprice());
        taxappliedres.setText(session.gettaxapplied());
        discountres.setText(session.getdiscount());
        startdateres.setText(session.getstartdate());
        starttimeres.setText(session.getstarttime());
        enddateres.setText(session.getenddate());
        endtimeres.setText(session.getendtime());
        totalamtres.setText(session.gettotalamt());

        myreservations = (Button) findViewById(R.id.myreservations);
        myreservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.getuserrole().equalsIgnoreCase("user")){
                    startActivity(new Intent(ConfirmationSummary.this, MyReservations.class));
                }
                else{
                    startActivity(new Intent(ConfirmationSummary.this, DeleteRental.class));
                }

            }
        });
    }
}