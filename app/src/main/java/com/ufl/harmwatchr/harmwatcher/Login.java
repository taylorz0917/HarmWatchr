package com.ufl.harmwatchr.harmwatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Click login button --> Open People
        final EditText email = (EditText) findViewById(R.id.username);
        final EditText pword = (EditText) findViewById(R.id.Password);
        final Button activity_peopleLink = (Button) findViewById(R.id.Login);
        final Button activity_registerLink = (Button) findViewById(R.id.Register);

        activity_peopleLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Verify login is in DB (In Progress.)

                if(email.getText().toString().equals("admin"/*firebase call to login*/) && pword.getText().toString().equals("admin"/*firebase call to password*/)){
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent activity_peopleIntent = new Intent(Login.this, People.class);
                    Login.this.startActivity(activity_peopleIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }

        });

        //take user to register page
        activity_registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent activity_registerIntent = new Intent(Login.this, Register.class);
                Login.this.startActivity(activity_registerIntent);

            }

        });

    }
}
