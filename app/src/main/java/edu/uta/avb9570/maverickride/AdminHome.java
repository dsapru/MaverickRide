package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminHome extends AppCompatActivity {
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        session = new Session(getApplicationContext());

        Button edituser = (Button)findViewById(R.id.edituser);
        edituser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AdminHome.this, EditUser.class));
            }
        });

        Button enableuser = (Button)findViewById(R.id.enableuser);
        enableuser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AdminHome.this, EnableUser.class));
            }
        });

        Button revokeuser = (Button)findViewById(R.id.revokeuser);
        revokeuser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AdminHome.this, RevokeUser.class));
            }
        });

        Button updateprofile = (Button)findViewById(R.id.updateprofileadminbtn);
        updateprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                session.setedituserutaid("");
                startActivity(new Intent(AdminHome.this, UpdateProfile.class));
            }
        });

        Button logout = (Button)findViewById(R.id.adminlogout);
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
                startActivity(new Intent(AdminHome.this, Login.class));
            }
        });
    }
}
