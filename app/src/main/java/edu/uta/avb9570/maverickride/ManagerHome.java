package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ManagerHome extends AppCompatActivity {
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        session = new Session(getApplicationContext());

        Button addrental = (Button)findViewById(R.id.addrental);
        addrental.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ManagerHome.this, AddRental.class));
            }
        });

        Button deleterental = (Button)findViewById(R.id.deleterental);
        deleterental.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ManagerHome.this, DeleteRental.class));
            }
        });

        Button viewbookings = (Button)findViewById(R.id.viewbookings);
        viewbookings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ManagerHome.this, ViewBookings.class));
            }
        });

        Button viewavailablecars = (Button)findViewById(R.id.viewavailablecarsbtn);
        viewavailablecars.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ManagerHome.this, ViewAvailableCars.class));
            }
        });

        Button updateprofile = (Button)findViewById(R.id.updatemanagerprofile);
        updateprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ManagerHome.this, UpdateProfile.class));
            }
        });

        Button logout = (Button)findViewById(R.id.managerlogout);
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
                startActivity(new Intent(ManagerHome.this, Login.class));
            }
        });
    }
}
