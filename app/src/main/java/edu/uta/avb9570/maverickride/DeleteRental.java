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

public class DeleteRental extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    Button details;
    Button cancel;
    RadioGroup rentalsgrp;
    RadioButton rentalsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_rental);

        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());
        if(session.getviewbookingsdate().equalsIgnoreCase("")){
            System.out.println("bookings date blank block is set");
            ArrayList<String> rentals = new ArrayList<String>();
            rentals = db.getallreservations();
            RadioGroup rg = (RadioGroup) findViewById(R.id.deleterentalradiogrp);
            rg.removeAllViews();
            rg.setOrientation(RadioGroup.VERTICAL);
            for (int i = 0; i < rentals.size(); i++) {
                RadioButton rb = new RadioButton(this);
                rb.setId(i);
                rb.setText(rentals.get(i));
                rg.addView(rb);
            }
        }
        else{
            System.out.println("bookings date not blank block is set");
            ArrayList<String> rentals2 = new ArrayList<String>();
            rentals2 = db.viewbookingsondate(session.getviewbookingsdate());
            RadioGroup rg1 = (RadioGroup) findViewById(R.id.deleterentalradiogrp);
            rg1.removeAllViews();
            rg1.setOrientation(RadioGroup.VERTICAL);
            for (int i = 0; i < rentals2.size(); i++) {
                RadioButton rb = new RadioButton(this);
                rb.setId(i);
                rb.setText(rentals2.get(i));
                rg1.addView(rb);
            }
            session.setviewbookingsdate("");
        }

        details = (Button) findViewById(R.id.deleterentaldetails);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rentalsgrp = (RadioGroup)findViewById(R.id.deleterentalradiogrp);
                int rentalselected = rentalsgrp.getCheckedRadioButtonId();
                rentalsbtn = (RadioButton)findViewById(rentalselected);
                String selectedrental = (String)rentalsbtn.getText();
                String[] rentaldetails = selectedrental.split("\t\t\t");
                session.setrentalid(rentaldetails[0]);
                startActivity(new Intent(DeleteRental.this, ReservationDetails.class));
            }
        });

        cancel = (Button) findViewById(R.id.deleterental);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rentalsgrp = (RadioGroup)findViewById(R.id.deleterentalradiogrp);
                int rentalselected = rentalsgrp.getCheckedRadioButtonId();
                rentalsbtn = (RadioButton)findViewById(rentalselected);
                String selectedrental = (String)rentalsbtn.getText();
                String[] rentaldetails = selectedrental.split("\t\t\t");
                session.setrentalid(rentaldetails[0]);
                boolean deletesucces = db.deleterental(rentaldetails[0]);
                if(deletesucces){

                    Toast.makeText(getApplicationContext(), "Rental deleted successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DeleteRental.this, DeleteRental.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Delete rental failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button managerhome = (Button)findViewById(R.id.managerhome);
        managerhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(DeleteRental.this, ManagerHome.class));
            }
        });
    }
}
