package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditUser extends AppCompatActivity {
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        session = new Session(getApplicationContext());

        Button edituser = (Button)findViewById(R.id.edituserbyadminbtn);
        edituser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final EditText edituserbyadminutaid = (EditText) findViewById(R.id.edituserbyadminutaid);
                String edituserbyadminutaid1 = edituserbyadminutaid.getText().toString();
                session.setedituserutaid(edituserbyadminutaid1);
                startActivity(new Intent(EditUser.this, UpdateProfile.class));
            }
        });

        Button adminhome = (Button)findViewById(R.id.adminhome);
        adminhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(EditUser.this, AdminHome.class));
            }
        });
    }
}
