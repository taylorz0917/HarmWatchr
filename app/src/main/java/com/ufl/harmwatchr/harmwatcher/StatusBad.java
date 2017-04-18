package com.ufl.harmwatchr.harmwatcher;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
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

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.content.Context;


import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

import java.util.Locale;

public class StatusBad extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0 ;
    String phoneNo;
    String message;
    String personNotificationMsg;
    String alertMessage;
    private TextView statusMsg;

    private float longitude, latitude;

    private String ecPhoneNumber, userID, firstName, lastName, carYear, carMake, carModel, carColor,
            licensePlate;

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

        final String geoLocation = message;
        System.out.println(geoLocation);

        setContentView(R.layout.activity_status_bad);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Parent parentData = dataSnapshot.child("Users").child(userID).getValue(Parent.class);
                ecPhoneNumber = parentData.getEmergencyContactPhone();
                firstName = parentData.getFirstName();
                lastName = parentData.getLastName();
                carYear = parentData.getCarYear();
                carMake = parentData.getCarMake();
                carModel = parentData.getCarModel();
                carColor = parentData.getCarColor();
                licensePlate = parentData.getLicensePlate();

                System.out.println("Emergency contact # :"+ecPhoneNumber);

                personNotificationMsg = firstName + ", your child may be in danger - check on them!";

                alertMessage = firstName + " " +  lastName + "'s child is experiencing an abnormally high heart rate at location: " +
                        geoLocation + " in a " + carColor + " " + carYear + " " +
                        carMake + " " + carModel + " with license plate: " + licensePlate + ". Please take action.";

                System.out.println(personNotificationMsg);
                System.out.println(alertMessage);

                addNotification(personNotificationMsg);
                sendSMSMessageToEmergencyContact(alertMessage);
                sendSMSMessageToEMS(alertMessage);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }





        });





        final Button activity_peopleLink = (Button) findViewById(R.id.back);
        final Button locate = (Button) findViewById(R.id.location);
        final Button call = (Button) findViewById(R.id.call);
        final Button ems = (Button) findViewById(R.id.ems);
        statusMsg = (TextView) findViewById(R.id.status);
        statusMsg.setText("Elevated Heart Rate Detected");


        //If Back is pressed. (DONE)
        activity_peopleLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Open people view
                Intent activity_peopleIntent = new Intent(StatusBad.this, People.class);
                startActivity(activity_peopleIntent);
            }

        });

        //If GetLocation is pressed. (In Progress.)
        locate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //input latitude, longitude from database
                Intent locate_Intent = new Intent(Intent.ACTION_VIEW);
                locate_Intent.setData(Uri.parse("geo:"+geoLocation));
                startActivity(locate_Intent);
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

    private void addNotification(String alertMsg) {

        System.out.println(alertMsg);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_full_open_on_phone)
                        .setContentTitle("HarmwatchER ALERT!")
                        .setContentText(alertMsg);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    private void sendSMSMessageToEmergencyContact(String alertMsg) {
        phoneNo = "6505551212";
        message = alertMsg;

        System.out.println(message);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }


    /* SHOULD IMPLEMENT ON TIMER FOR 10 MINUTES AFTER INITIAL HEART RATE "INCIDENT" READING
    IF NO RESPONSE TAKEN.
     */
    private void sendSMSMessageToEMS(String alertMsg) {
        phoneNo = "911";
        message = alertMsg;

        System.out.println(message);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }


}
