package com.ufl.harmwatchr.harmwatcher;

/**
 * Created by brianroytman on 4/13/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.Manifest;
import android.support.v4.app.ActivityCompat;

public class StatusBad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_status_bad);
        //^^If status is Good: (In Progress.)

        /*Else:
            setContentView(R.layout.activity_status_bad);
            Only add ems button if view = status_bad.
        */


        final Button activity_peopleLink = (Button) findViewById(R.id.back);
        final Button locate = (Button) findViewById(R.id.location);
        final Button call = (Button) findViewById(R.id.call);
        final Button ems = (Button) findViewById(R.id.ems);


        //If Back is pressed. (DONE)
        activity_peopleLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Open people view
                Intent activity_peopleIntent = new Intent(StatusBad.this, People.class);
                StatusBad.this.startActivity(activity_peopleIntent);
            }

        });

        //If GetLocation is pressed. (In Progress.)
        locate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //input latitude, longitude from database
                String uri = String.format("geo:%f,%f",59.915494, 30.409456);
                Intent locate_Intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                StatusBad.this.startActivity(locate_Intent);
            }
        });



        //If Call is pressed...call Emergency Contact? (In Progress.)
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Retreive watchee's phone number
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:9543193505"));

                if (ActivityCompat.checkSelfPermission(StatusBad.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                StatusBad.this.startActivity(callIntent);
            }

        });

        //If ems is pressed...or not detected after 10 min since alert
       ems.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent emsIntent = new Intent(Intent.ACTION_DIAL);
                emsIntent.setData(Uri.parse("tel:911"));

                if (ActivityCompat.checkSelfPermission(StatusBad.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(emsIntent);

            }

        });


    }

}
