package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EnableUser extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_user);

        Button enableuser = (Button) findViewById(R.id.enableuserbyadminbtn);
        db = new DatabaseHelper(this);
        enableuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText enableuserbyadminutaid = (EditText) findViewById(R.id.enableuserbyadminutaid);
                String enableuserbyadminutaid1 = enableuserbyadminutaid.getText().toString();
                Boolean enableuser = db.enableuser(enableuserbyadminutaid1);
                if(enableuser == true){
                    Toast.makeText(getApplicationContext(), "User enabled succesfully!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "User enable failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}