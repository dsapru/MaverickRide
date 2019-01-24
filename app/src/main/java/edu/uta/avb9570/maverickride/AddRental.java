package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRental extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rental);

        Button addrentalcontinue = (Button)findViewById(R.id.addrentalcontinue);
        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());

        addrentalcontinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final EditText addrentalutaid = (EditText) findViewById(R.id.addrentalutaid);
                final String addrentalutaid1 = addrentalutaid.getText().toString();

                if(addrentalutaid1.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Fields cannot be left blank", Toast.LENGTH_LONG).show();
                }
                else{
                    session.setaddrentalutaid(addrentalutaid1);
                    startActivity(new Intent(AddRental.this, ReserveRental.class));
                }
            }
        });
    }
}
