package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ViewBookings extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    Button viewbookings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());

        viewbookings = (Button) findViewById(R.id.viewbookingsondatebtn);
        viewbookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText viewbookingsdate = (EditText) findViewById(R.id.viewbookingsdate);
                String viewbookingsdate1 = viewbookingsdate.getText().toString();
                session.setviewbookingsdate(viewbookingsdate1);
                startActivity(new Intent(ViewBookings.this, DeleteRental.class));
            }
        });
    }
}
