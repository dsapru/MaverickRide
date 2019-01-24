package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class SelectExtras extends AppCompatActivity {
    DatabaseHelper db;
    Button addextrascontinuebtn;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_extras);

        addextrascontinuebtn = (Button) findViewById(R.id.addextrascontinue);
        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());

        String carname = session.getcarname();
        ArrayList<String> extrasrates = new ArrayList<String>();
        extrasrates = db.getextrasrates(carname);

        for(int i =0; i < extrasrates.size(); i++){
            System.out.println(i+" "+extrasrates.get(i));
        }

        CheckBox gps = (CheckBox)findViewById(R.id.gps);
        String gpstext = "GPS [ $"+extrasrates.get(0)+"/day ]";
        gps.setText(gpstext);

        CheckBox onstar = (CheckBox)findViewById(R.id.onstar);
        String onstartext = "OnStar [ $"+extrasrates.get(1)+"/day ]";
        onstar.setText(onstartext);

        CheckBox siriusxm = (CheckBox)findViewById(R.id.siriusxm);
        String siriusxmtext = "SiriusXM [ $"+extrasrates.get(2)+"/day ]";
        siriusxm.setText(siriusxmtext);



        addextrascontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carname = session.getcarname();
                ArrayList<String> extrasrates = new ArrayList<String>();
                extrasrates = db.getextrasrates(carname);
                boolean gps = ((CheckBox)findViewById(R.id.gps)).isChecked();
                String gps1;
                if(gps)
                    session.setgpsrate(extrasrates.get(0));
                else
                    session.setgpsrate("0");

                boolean onstar = ((CheckBox)findViewById(R.id.onstar)).isChecked();
                String onstar1;
                if(onstar)
                    session.setonstarrate(extrasrates.get(1));
                else
                    session.setonstarrate("0");

                boolean siriusxm = ((CheckBox)findViewById(R.id.siriusxm)).isChecked();
                String siriusxm1;
                if(siriusxm)
                    session.setsiriusxmrate(extrasrates.get(2));
                else
                    session.setsiriusxmrate("0");
                startActivity(new Intent(SelectExtras.this, ReservationSummary.class));
            }
        });
    }
}