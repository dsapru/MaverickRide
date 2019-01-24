package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Session session;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(getApplicationContext());
        //login
        Button login = (Button)findViewById(R.id.login);
        db = new DatabaseHelper(this);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final EditText utaidlogin = (EditText) findViewById(R.id.utaidlogin);
                final EditText password = (EditText) findViewById(R.id.passwordlogin);
                final String utaidlogin1 = utaidlogin.getText().toString();
                final String password1 = password.getText().toString();
                if(utaidlogin1.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "UTA ID cannot be blank", Toast.LENGTH_LONG).show();
                }
                else if(password1.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Password cannot be blank", Toast.LENGTH_LONG).show();
                }
                else{
                    //uta id does not exist
                    Boolean checkutaid = db.checkutaid(utaidlogin1);
                    if(checkutaid==true){
                        Toast.makeText(getApplicationContext(), "UTA ID does not exist", Toast.LENGTH_LONG).show();
                    }
                    else{
                        //uta id exists
                        Boolean login = db.login(utaidlogin1, password1);
                        if(login == true){
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            session.setusename(utaidlogin1);
                            String userrole = db.getuserrole(utaidlogin1);
                            session.setuserrole(userrole);
                            String firstname = db.getfirstname(utaidlogin1);
                            session.setfirstname(firstname);
                            String aacm = db.getaacm(utaidlogin1);
                            session.setaacm(aacm);
                            if(userrole.equalsIgnoreCase("user")){
                                startActivity(new Intent(Login.this, UserHome.class));
                            }
                            else if(userrole.equalsIgnoreCase("manager")){
                                startActivity(new Intent(Login.this, ManagerHome.class));
                            }
                            else if(userrole.equalsIgnoreCase("admin")){
                                startActivity(new Intent(Login.this, AdminHome.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Application failed!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
        });
        //register
        Button register = (Button)findViewById(R.id.updateprofile);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        Button forgotpassword = (Button)findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Login.this, ForgotPassword.class));
            }
        });
    }




}
