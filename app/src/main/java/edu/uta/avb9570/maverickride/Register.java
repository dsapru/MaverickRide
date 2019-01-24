package edu.uta.avb9570.maverickride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity{
    Button regbutton;
    RadioGroup usertypegroup;
    RadioButton usertypebutton;
    RadioGroup userrolegroup;
    RadioButton userrolebutton;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText utaid = (EditText) findViewById(R.id.utaidlogin);
        final EditText firstname = (EditText) findViewById(R.id.firstname);
        final EditText lastname = (EditText) findViewById(R.id.lastname);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.passwordlogin);
        final EditText confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText dob = (EditText) findViewById(R.id.dob);
        final EditText country = (EditText) findViewById(R.id.country);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText state = (EditText) findViewById(R.id.state);
        final EditText pin = (EditText) findViewById(R.id.pin);
        final EditText dlnumber = (EditText) findViewById(R.id.dlnumber);
        final EditText dlexp = (EditText) findViewById(R.id.dlexp);
        /*final EditText aacm = (EditText) findViewById(R.id.aacm);
        final EditText usertype = (EditText) findViewById(R.id.usertype);
        final EditText userrole = (EditText) findViewById(R.id.userrole);*/

        regbutton=(Button)findViewById(R.id.updateprofile);
        db = new DatabaseHelper(this);
        regbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String utaid1 = utaid.getText().toString();
                String firstname1 = firstname.getText().toString();
                String lastname1 = lastname.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String confirmpassword1 = confirmpassword.getText().toString();
                String phone1 = phone.getText().toString();
                String dob1 = dob.getText().toString();
                String country1 = country.getText().toString();
                String address1 = address.getText().toString();
                String city1 = city.getText().toString();
                String state1 = state.getText().toString();
                String pin1 = pin.getText().toString();
                String dlnumber1 = dlnumber.getText().toString();
                String dlexp1 = dlexp.getText().toString();

                usertypegroup = (RadioGroup)findViewById(R.id.usertype);
                int usertypeselected = usertypegroup.getCheckedRadioButtonId();
                usertypebutton = (RadioButton)findViewById(usertypeselected);
                String usertype = (String)usertypebutton.getText();

                userrolegroup = (RadioGroup)findViewById(R.id.userrole);
                int userroleselected = userrolegroup.getCheckedRadioButtonId();
                userrolebutton = (RadioButton)findViewById(userroleselected);
                String userrole = (String)userrolebutton.getText();

                boolean aacm = ((CheckBox)findViewById(R.id.aacm)).isChecked();
                String aacm1;
                if(aacm)
                    aacm1 = "yes";
                else
                    aacm1 = "no";

                if (utaid1.equals("")){
                    Toast.makeText(getApplicationContext(), "UTA ID cannot be blank", Toast.LENGTH_LONG).show();
                }
                else if(!password1.equals(confirmpassword1)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                }
                else{
                    Boolean checkutaid = db.checkutaid(utaid1);
                    if(checkutaid==true){
                        Boolean register = db.register(utaid1, firstname1, lastname1, email1, password1, phone1, dob1,country1, address1 ,city1 ,state1 ,pin1 ,dlnumber1 ,dlexp1 ,aacm1 ,usertype ,userrole, "yes");
                        if(register == true){
                            Toast.makeText(getApplicationContext(), "Registered succesfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this, Login.class));
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "uta id already exists", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }

}
