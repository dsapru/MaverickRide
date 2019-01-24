package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserHome extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        session = new Session(getApplicationContext());
        db = new DatabaseHelper(this);

        Button reserverental = (Button)findViewById(R.id.reserverental);
        reserverental.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userenabled = db.getuserenabled(session.getusename());
                if(userenabled.equalsIgnoreCase("no")){
                    Toast.makeText(getApplicationContext(), "User has been revoked", Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(new Intent(UserHome.this, ReserveRental.class));
                }

            }
        });

        Button myreservations = (Button)findViewById(R.id.myreservations);
        myreservations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(UserHome.this, MyReservations.class));
            }
        });

        Button updateprofile = (Button)findViewById(R.id.updateprofile);
        updateprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(UserHome.this, UpdateProfile.class));
            }
        });

        Button logout = (Button)findViewById(R.id.userlogout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                session.setusename("");
                session.setrentalid("");
                session.setdiscount("");
                session.settotalamt("");
                session.settaxapplied("");
                session.setbaseprice("");
                session.setaacm("");
                session.setuserrole("");
                Toast.makeText(getApplicationContext(), "Successfully logged out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(UserHome.this, Login.class));
            }
        });
    }
}
