package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MyReservations extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    Button details;
    Button cancel;
    RadioGroup rentalsgrp;
    RadioButton rentalsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());
        ArrayList<String> rentals = new ArrayList<String>();
        rentals = db.getmyreservations(session.getusename());
        RadioGroup rg = (RadioGroup) findViewById(R.id.myresradiogrp);
        rg.removeAllViews();
        rg.setOrientation(RadioGroup.VERTICAL);
        for (int i = 0; i < rentals.size(); i++) {
            RadioButton rb = new RadioButton(this);
            rb.setId(i);
            rb.setText(rentals.get(i));
            rg.addView(rb);
        }

        details = (Button) findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rentalsgrp = (RadioGroup)findViewById(R.id.myresradiogrp);
                int rentalselected = rentalsgrp.getCheckedRadioButtonId();
                rentalsbtn = (RadioButton)findViewById(rentalselected);
                String selectedrental = (String)rentalsbtn.getText();
                String[] rentaldetails = selectedrental.split("\t\t\t");
                session.setrentalid(rentaldetails[0]);
                startActivity(new Intent(MyReservations.this, ReservationDetails.class));
            }
        });

        cancel = (Button) findViewById(R.id.cancelrental);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rentalsgrp = (RadioGroup)findViewById(R.id.myresradiogrp);
                int rentalselected = rentalsgrp.getCheckedRadioButtonId();
                rentalsbtn = (RadioButton)findViewById(rentalselected);
                String selectedrental = (String)rentalsbtn.getText();
                String[] rentaldetails = selectedrental.split("\t\t\t");
                session.setrentalid(rentaldetails[0]);
                boolean deletesucces = db.deleterental(rentaldetails[0]);
                if(deletesucces){

                    Toast.makeText(getApplicationContext(), "Rental deleted successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MyReservations.this, MyReservations.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Delete rental failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button userhome = (Button)findViewById(R.id.userhome);
        userhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MyReservations.this, UserHome.class));
            }
        });
    }
}
