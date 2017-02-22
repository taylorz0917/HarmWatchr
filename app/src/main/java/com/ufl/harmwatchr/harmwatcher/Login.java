package com.ufl.harmwatchr.harmwatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Click login button -> Open People
        final Button activity_peopleLink = (Button) findViewById(R.id.Login);

        activity_peopleLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent activity_peopleIntent = new Intent(Login.this, People.class);
                Login.this.startActivity(activity_peopleIntent);
            }

        });

    }
}
