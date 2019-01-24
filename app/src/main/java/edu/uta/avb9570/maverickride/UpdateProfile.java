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

import java.util.ArrayList;

public class UpdateProfile extends AppCompatActivity {
    DatabaseHelper db;
    Session session;
    Button update;
    RadioGroup usertypegroup;
    RadioButton usertypebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        final EditText firstname = (EditText) findViewById(R.id.firstname);
        final EditText lastname = (EditText) findViewById(R.id.lastname);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.passwordlogin);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText dob = (EditText) findViewById(R.id.dob);
        final EditText country = (EditText) findViewById(R.id.country);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText state = (EditText) findViewById(R.id.state);
        final EditText pin = (EditText) findViewById(R.id.pin);
        final EditText dlexp = (EditText) findViewById(R.id.dlexp);

        update = (Button) findViewById(R.id.updateprofile);
        db = new DatabaseHelper(this);
        session = new Session(getApplicationContext());

        ArrayList<String> profile2 = new ArrayList<String>();

        if(session.getedituserutaid().equalsIgnoreCase("")){
            profile2 = db.getprofile(session.getusename());
            System.out.println("uta id for update own profile= "+session.getusename());
        }
        else{
            profile2 = db.getprofile(session.getedituserutaid());
            System.out.println("uta id for edit user = "+session.getedituserutaid());
        }
        String profile1 = profile2.get(0);
        String[] profile = profile1.split("\t\t\t");

        firstname.setText(profile[0]);
        lastname.setText(profile[1]);
        email.setText(profile[2]);
        password.setText(profile[3]);
        phone.setText(profile[4]);
        dob.setText(profile[5]);
        country.setText(profile[6]);
        address.setText(profile[7]);
        city.setText(profile[8]);
        state.setText(profile[9]);
        pin.setText(profile[10]);
        dlexp.setText(profile[11]);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname1 = firstname.getText().toString();
                String lastname1 = lastname.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String phone1 = phone.getText().toString();
                String dob1 = dob.getText().toString();
                String country1 = country.getText().toString();
                String address1 = address.getText().toString();
                String city1 = city.getText().toString();
                String state1 = state.getText().toString();
                String pin1 = pin.getText().toString();
                String dlexp1 = dlexp.getText().toString();

                boolean aacm = ((CheckBox)findViewById(R.id.aacm)).isChecked();
                String aacm1;
                if(aacm)
                    aacm1 = "yes";
                else
                    aacm1 = "no";

                usertypegroup = (RadioGroup)findViewById(R.id.usertype);
                int usertypeselected = usertypegroup.getCheckedRadioButtonId();
                usertypebutton = (RadioButton)findViewById(usertypeselected);
                String usertype = (String)usertypebutton.getText();

                Boolean update;
                if(session.getedituserutaid().equalsIgnoreCase("")){
                    update = db.updateprofile(session.getusename(), firstname1, lastname1, email1, password1, phone1, dob1,country1, address1 ,city1 ,state1 ,pin1 ,dlexp1 ,aacm1 ,usertype);

                }
                else{
                    update = db.updateprofile(session.getedituserutaid(), firstname1, lastname1, email1, password1, phone1, dob1,country1, address1 ,city1 ,state1 ,pin1 ,dlexp1 ,aacm1 ,usertype);
                    session.setedituserutaid("");
                }


                if(update == true){
                    Toast.makeText(getApplicationContext(), "Profile updated succesfully", Toast.LENGTH_LONG).show();
                    if(session.getuserrole().equalsIgnoreCase("user"))
                        startActivity(new Intent(UpdateProfile.this, UserHome.class));
                    else if(session.getuserrole().equalsIgnoreCase("manager"))
                        startActivity(new Intent(UpdateProfile.this, ManagerHome.class));
                    else if(session.getuserrole().equalsIgnoreCase("admin"))
                        startActivity(new Intent(UpdateProfile.this, AdminHome.class));
                    else
                        Toast.makeText(getApplicationContext(), "Update profile failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}