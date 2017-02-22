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
        setContentView(R.layout.activity_status_good);

        final Button activity_peopleLink = (Button) findViewById(R.id.back);
        final Button call = (Button) findViewById(R.id.call);
        final Button ems = (Button) findViewById(R.id.ems);

        activity_peopleLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent activity_peopleIntent = new Intent(Status.this, People.class);
                Status.this.startActivity(activity_peopleIntent);
            }

        });
    }
}
