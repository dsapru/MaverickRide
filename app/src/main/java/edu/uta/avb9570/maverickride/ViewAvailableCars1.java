package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class ViewAvailableCars1 extends AppCompatActivity {

    Session session;
    DatabaseHelper db;
    RadioGroup cargroup;
    RadioButton carbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_available_cars1);

        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());
        String capacity = "1";
        ArrayList<String> cars = new ArrayList<String>();
        cars = db.searchcars(capacity);
        RadioGroup rg = (RadioGroup) findViewById(R.id.viewavailablecarsradiogrp);
        rg.removeAllViews();
        rg.setOrientation(RadioGroup.VERTICAL);
        for (int i = 0; i < cars.size(); i++) {
            RadioButton rb = new RadioButton(this);
            rb.setId(i);
            rb.setText(cars.get(i));
            rg.addView(rb);
        }

        Button managerhome = (Button)findViewById(R.id.managerhome);
        managerhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ViewAvailableCars1.this, ManagerHome.class));
            }
        });
    }
}
