package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button resetpassword = (Button)findViewById(R.id.resetpasswordbtn);
        resetpassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                final EditText forgotpasswordemail = (EditText) findViewById(R.id.forgotpasswordemail);
                final String forgotpasswordemail1 = forgotpasswordemail.getText().toString();
                if(forgotpasswordemail1.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Email cannot be blank", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "New password sent", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgotPassword.this, Login.class));
                }

            }
        });
    }
}
