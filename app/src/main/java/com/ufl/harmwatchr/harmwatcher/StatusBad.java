package com.ufl.harmwatchr.harmwatcher;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.Manifest;
import android.support.v4.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class StatusBad extends AppCompatActivity {

    private float longitude, latitude;

    private String ecPhoneNumber, userID;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");

        if(message != null) {
            String[] tokens = message.split(" ");
            longitude = Float.parseFloat(tokens[0]);
            latitude = Float.parseFloat(tokens[1]);

            System.out.println("latitude: "+latitude+" longitude: "+longitude);
        }
        setContentView(R.layout.activity_status_bad);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Parent parentData = dataSnapshot.child("Users").child(userID).getValue(Parent.class);
                ecPhoneNumber = parentData.getEmergencyContactPhone();

                System.out.println("Emergency contact # :"+ecPhoneNumber);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                String uri = String.format(Locale.getDefault(),"geo:%f,%f",longitude, latitude);
                Intent locate_Intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                StatusBad.this.startActivity(locate_Intent);
            }
        });


        //If Call is pressed.
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+ecPhoneNumber));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                } catch (SecurityException secEx){
                    Log.e("Calling a Phone Number", "Security Exception", secEx);
                }
            }

        });


        //If ems is pressed...or not detected after 10 min since alert
       ems.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:911"));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                } catch (SecurityException secEx){
                    Log.e("Calling a Phone Number", "Security Exception", secEx);
                }
            }

        });


    }

}
