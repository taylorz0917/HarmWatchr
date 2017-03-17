package com.ufl.harmwatchr.harmwatcher;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button activity_registerLink = (Button) findViewById(R.id.register_submit);
        final EditText first_name = (EditText) findViewById(R.id.first_name);
        final EditText last_name = (EditText) findViewById(R.id.last_name);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText age = (EditText) findViewById(R.id.age);
        final EditText phone = (EditText) findViewById(R.id.phone);

        activity_registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    Add user to DB (In Progress.)
                    add first name
                    add last name
                    add email
                    add username
                    add password
                    add age
                    add phone
                    Take to Add view
                */
                //user directed to Add page to add a child
                Intent activity_registerIntent = new Intent(Register.this, Add.class);
                Register.this.startActivity(activity_registerIntent);
            }

        });


    }
}
