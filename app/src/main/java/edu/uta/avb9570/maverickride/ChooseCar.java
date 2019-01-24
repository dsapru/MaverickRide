package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Iterator;
import edu.uta.avb9570.maverickride.Session;
import edu.uta.avb9570.maverickride.ReserveRental;

public class ChooseCar extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    Button continuebtn;
    RadioGroup cargroup;
    RadioButton carbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_car);
        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());
        String capacity = session.getcapacity();
        ArrayList<String> cars = new ArrayList<String>();
        cars = db.searchcars(capacity);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup2);
        rg.removeAllViews();
        rg.setOrientation(RadioGroup.VERTICAL);
        for (int i = 0; i < cars.size(); i++) {
            RadioButton rb = new RadioButton(this);
            rb.setId(i);
            rb.setText(cars.get(i));
            rg.addView(rb);
        }
        continuebtn = (Button) findViewById(R.id.continuebtn);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargroup = (RadioGroup)findViewById(R.id.radioGroup2);
                int carselected = cargroup.getCheckedRadioButtonId();
                carbtn = (RadioButton)findViewById(carselected);
                String selectedcar = (String)carbtn.getText();
                String[] rentaldetails = selectedcar.split("\t\t\t\t\t");
                session.setcarname(rentaldetails[0]);
                session.setcapacity(rentaldetails[1]);
                session.setweekdayrate(rentaldetails[2]);
                session.setweekendrate(rentaldetails[3]);
                session.setweekrate(rentaldetails[4]);
                startActivity(new Intent(ChooseCar.this, SelectExtras.class));
            }
        });
    }
}