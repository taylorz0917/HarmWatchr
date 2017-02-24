package com.ufl.harmwatchr.harmwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Status extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //If status is Good: (In Progress.)
        setContentView(R.layout.activity_status_good);
        //Else:
        //setContentView(R.layout.activity_status_bad);
        //Only add ems button if view = status_bad.
        final Button ems = (Button) findViewById(R.id.ems);

        //These buttons are in both views
        final Button activity_peopleLink = (Button) findViewById(R.id.back);
        final Button locate = (Button) findViewById(R.id.location);
        final Button call = (Button) findViewById(R.id.call);



        //If Back is pressed. (DONE)
        activity_peopleLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Open people view
                Intent activity_peopleIntent = new Intent(Status.this, People.class);
                Status.this.startActivity(activity_peopleIntent);
            }

        });

        //If GetLocation is pressed. (In Progress.)
        locate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Do Location stuff
            }

        });

        //If Call is pressed. (In Progress.)
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Do Call stuff
            }

        });

        //If EMS is pressed. (In Progress.)
        ems.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Do EMS stuff
            }

        });


    }
}
