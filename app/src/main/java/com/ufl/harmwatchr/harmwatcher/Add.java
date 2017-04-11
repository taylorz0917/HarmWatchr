package com.ufl.harmwatchr.harmwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Button activity_peopleLink = (Button) findViewById(R.id.submit);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText age = (EditText) findViewById(R.id.age);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final Button activity_backButton = (Button) findViewById(R.id.back);

        activity_peopleLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    Add child to DB (In Progress.)
                    add name
                    add age
                    add phone
                    Take to People view
                */

                Intent activity_peopleIntent = new Intent(Add.this, People.class);
                Add.this.startActivity(activity_peopleIntent);
            }

        });

        activity_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    Add child to DB (In Progress.)
                    add name
                    add age
                    add phone
                    Take to People view
                */

                Intent activity_peopleIntent = new Intent(Add.this, People.class);
                Add.this.startActivity(activity_peopleIntent);
            }

        });

    }
}
