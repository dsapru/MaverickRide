package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.uta.avb9570.maverickride.Session;

public class ReserveRental extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_rental);
        Button searchcar = (Button)findViewById(R.id.searchcar);
        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());
        searchcar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final EditText startdate = (EditText) findViewById(R.id.startdate);
                final EditText starttime = (EditText) findViewById(R.id.starttime);
                final EditText enddate = (EditText) findViewById(R.id.enddate);
                final EditText endtime = (EditText) findViewById(R.id.endtime);
                final EditText capacity = (EditText) findViewById(R.id.occupants);

                final String startdate1 = startdate.getText().toString();
                final String starttime1 = starttime.getText().toString();
                final String enddate1 = enddate.getText().toString();
                final String endtime1 = endtime.getText().toString();
                final String capacity1 = capacity.getText().toString();

                if(startdate1.equalsIgnoreCase("") || starttime1.equalsIgnoreCase("") || enddate1.equalsIgnoreCase("") || endtime1.equalsIgnoreCase("") || capacity1.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Fields cannot be left blank", Toast.LENGTH_LONG).show();
                }
                else{
                    session.setstartdate(startdate1);
                    session.setstarttime(starttime1);
                    session.setenddate(enddate1);
                    session.setendtime(endtime1);
                    session.setcapacity(capacity1);
                    startActivity(new Intent(ReserveRental.this, ChooseCar.class));
                }
            }
        });
    }
}
