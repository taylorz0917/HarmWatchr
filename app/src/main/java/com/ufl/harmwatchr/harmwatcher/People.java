package com.ufl.harmwatchr.harmwatcher;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;
import org.json.*;
import java.util.*;
import android.os.*;

public class People extends AppCompatActivity  {

    private static final String TAG = "People";

    /* Firebase initialization  */
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;


    /* Global variables to be populated when creating Child objects */
    String firstName;
    String lastName;
    String idString;
    String ageString;
    int    ageInMonths;
    int avgHeartRate;
    double    latitude;
    double    longitude;


    /*Global Parent and Child Objects */
    //Parent parentData;
    //Child child1Data;
    //Child child2Data;
    //Child child3Data;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Parent parentData = dataSnapshot.child("Users").child(userID).getValue(Parent.class);
                idString = parentData.getChild1ID();

                System.out.println(parentData.getChild1ID());
                System.out.println(idString);
                try {
                    if (parentData.getNumChildren() > 0) {
                        Child child1Data = dataSnapshot.child("Children").child(parentData.getChild1ID()).getValue(Child.class);
                        firstName = child1Data.getChildFirstName();
                        lastName = child1Data.getChildLastName();
                        ageString = child1Data.getChildAgeInMonths();
                        ageInMonths = Integer.parseInt(ageString);



                        System.out.println("Child info: " + firstName + " " + lastName);

                    }
                } catch (NumberFormatException n) {
                    n.printStackTrace();
                }

                if(parentData!=null) {
                    System.out.println("User: " + userID + " Data: " + parentData.getFirstName() + " " + parentData.getLastName() +" "+ parentData.getNumChildren()+" "+ parentData.getPhone());
                    System.out.println("User: " + userID + " Emergency Contact info: " + parentData.getEmergencyContactFirstName() + " " + parentData.getEmergencyContactLastName() +" "+ parentData.getEmergencyContactPhone());
                    switch(parentData.getNumChildren()){
                        case 0:
                            System.out.println("No children yet.");
                            break;
                        case 1:
                            System.out.println("Child1ID: "+parentData.getChild1ID());
                            break;
                        case 2:
                            System.out.println("Child1ID: "+ idString +"Child2ID: "+parentData.getChild2ID());
                            break;
                        case 3:
                            System.out.println("Child1ID: "+parentData.getChild1ID()+"Child2ID: "+parentData.getChild2ID()+"Child3ID: "+parentData.getChild3ID());
                            break;
                    }
                }
                else
                    System.out.println("Null parent data.");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.print("Failed to read.");
            }
        });


        //for loop of people being watched by user... generate activity_peopleLink button
        // add ability to click more people buttons
        final Button activity_peopleLink = (Button) findViewById(R.id.Person1);
        final Button activity_signoutLink = (Button) findViewById(R.id.signout);
        final Button activity_addLink = (Button) findViewById(R.id.AddPerson);



        //If Person is chosen -> Check their status.
        activity_peopleLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                /*CALL HEART RATE/PARSE JSON METHOD */
                boolean childInDanger;
                try {
                    childInDanger = heartRateData(firstName, lastName, ageInMonths, idString);
                    String message = (latitude)+","+(longitude);
                    if (!childInDanger) {
                        Intent activity_peopleIntent_statusGood = new Intent(People.this, StatusGood.class);
                        activity_peopleIntent_statusGood.putExtra("message",message);
                        People.this.startActivity(activity_peopleIntent_statusGood);
                    } else {
                        Intent activity_peopleIntent_statusBad = new Intent(People.this, StatusBad.class);
                        activity_peopleIntent_statusBad.putExtra("message",message);
                        People.this.startActivity(activity_peopleIntent_statusBad);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JSONException j) {
                    j.printStackTrace();
                } catch (InterruptedException ix) {
                    ix.printStackTrace();
                } catch (ExecutionException exe) {
                    exe.printStackTrace();
                }
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
        activity_signoutLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Open login view
                Intent activity_peopleIntent = new Intent(People.this, Login.class);
                People.this.startActivity(activity_peopleIntent);
            }

        });

    }

    /* Make HTTP call to retrieve JSON heart rate data, parse,  and pass to average heart rate calculator.
     * Compare average heart rate to safe upper limit in the heartRate HashMap and make determination
      * if child is in danger or not in danger.*/
    public boolean heartRateData(String fName, String lName, int age, String idString) throws
            MalformedURLException, IOException, JSONException, InterruptedException, ExecutionException {


        String jsonDataString;
        boolean inDanger = false;
        System.out.println("Your child: " + fName + " " + lName + " " + idString + " could be in danger.");

        String urlBase = "https://quarkbackend.com/getfile/brianroytman/";
        String urlAdd = fName + "-" + lName + "-" + idString;
        String urlAddLowerCase = urlAdd.toLowerCase();
        String urlFinal = urlBase + urlAddLowerCase;
        System.out.println(urlFinal);

        //String urlFinal = "https://quarkbackend.com/getfile/brianroytman/john-roytman-6xl39csokp2kvrfzntq-fq";
        //URL url = new URL(urlFinal);

        JSONAsyncTask grabJson = new JSONAsyncTask();
        jsonDataString = grabJson.execute(urlFinal).get();

        System.out.println(jsonDataString);

        JSONObject parentObj = new JSONObject(jsonDataString);
        System.out.println(parentObj);

        latitude = parentObj.getDouble("latitude");
        longitude = parentObj.getDouble("longitude");

        System.out.println("latitude: " + latitude);
        System.out.println("longitude: " + longitude);

        JSONArray parentArray = parentObj.getJSONArray("heart_rate_data");

        int[] heartRateHolder = new int[12];
        for (int i = 0; i < parentArray.length(); i++) {
            //JSONArray parentArray = parentObj.getJSONArray("heart_rate_data");
            JSONObject finalObj = parentArray.getJSONObject(i);
            int finalHeartRate = finalObj.getInt("heart_rate");
            heartRateHolder[i] = finalHeartRate;
            System.out.println("Heart Rate: " + finalHeartRate);
        }

        int averageHeartRate = calcAvgHeartRate(heartRateHolder);
        System.out.println("Average Heart Rate: " + averageHeartRate);
        inDanger = compareAverageHeartRate(averageHeartRate, age );

        return inDanger;
    }

    /* Calculate average heart rate per 2 minute cycle (12 readings from JSON, 1 reading every 10 sec) */
    public static int calcAvgHeartRate(int heartRateHolder[]) {
        int hrAvg;
        int sum = 0;

        for (int i = 0; i < heartRateHolder.length; i++) {
            sum += heartRateHolder[i];
        }

        hrAvg = sum / heartRateHolder.length;
        System.out.println("Average heart rate: " + hrAvg);

        return hrAvg;

    }

    public boolean compareAverageHeartRate(int avgHr, int childAgeInMo) {
        boolean isHrDangerous = false;

        HashMap<Integer, Integer> heartRateScale = new HashMap<Integer, Integer>();

		/* IMPORT CHILD'S AGE FROM FIREBASE */
        int childAgeInMonths = childAgeInMo;
        //populate HashMap with heart rate levels for kids from age 0 months (newborn) up to 72 months/ 6 years old
        //for (int i = 0; i <= childAgeInMonths ; i++) {

        /* Newborns to 3 months */
        if (childAgeInMonths <= 3) {
            heartRateScale.put(childAgeInMonths, 150);
        }

        /* 3 months to 36 months (3 years) */
        else if (childAgeInMonths > 3 && childAgeInMonths <= 36) {
            heartRateScale.put(childAgeInMonths, 120);
        }


        /* 3 years to 6 years and so forth...*/
        else {
            heartRateScale.put(childAgeInMonths, 110);
            }
       // }

        /* If the child's average heart rate is greater than the upper limit + 20%, consider this dangerous */
        if ( avgHr > heartRateScale.get(childAgeInMo) * 1.20 ) {
            isHrDangerous = true;
        }
        else {
            isHrDangerous = false;
        }

        System.out.println("Average Heart Rate: " + avgHr + isHrDangerous);
        return isHrDangerous;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onStop() {
        super.onStop();

        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }


}
