package edu.uta.avb9570.maverickride;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RevokeUser extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revoke_user);

        Button revokeuser = (Button) findViewById(R.id.revokeuserbyadminbtn);
        db = new DatabaseHelper(this);
        revokeuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText revokeuserbyadminutaid = (EditText) findViewById(R.id.revokeuserbyadminutaid);
                String revokeuserbyadminutaid1 = revokeuserbyadminutaid.getText().toString();
                Boolean revokeuser = db.revokeuser(revokeuserbyadminutaid1);
                if(revokeuser == true){
                    Toast.makeText(getApplicationContext(), "User revoked succesfully!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "User revoke failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
