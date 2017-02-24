package com.ufl.harmwatchr.harmwatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class People extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        final Button activity_peopleLink = (Button) findViewById(R.id.Person1);
        final Button activity_loginLink = (Button) findViewById(R.id.signout);
        final Button activity_addLink = (Button) findViewById(R.id.AddPerson);

        //If Person is chosen -> Check their status.
        activity_peopleLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent activity_peopleIntent = new Intent(People.this, Status.class);
                People.this.startActivity(activity_peopleIntent);
            }

        });

        //If Add is chosen.
        activity_addLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Open add view
                Intent activity_peopleIntent = new Intent(People.this, Add.class);
                People.this.startActivity(activity_peopleIntent);
            }

        });

        //If Sign Out is chosen.
        activity_addLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Open login view
                Intent activity_peopleIntent = new Intent(People.this, Login.class);
                People.this.startActivity(activity_peopleIntent);
            }

        });
    }
}
