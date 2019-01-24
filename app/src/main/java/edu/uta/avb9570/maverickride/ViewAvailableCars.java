package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewAvailableCars extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    Button searchcar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_available_cars);

        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());

        searchcar = (Button) findViewById(R.id.searchcar);
        searchcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAvailableCars.this, ViewAvailableCars1.class));
            }
        });
    }
}
