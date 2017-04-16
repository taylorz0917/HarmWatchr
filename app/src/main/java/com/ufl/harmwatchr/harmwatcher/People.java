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

    //Parent parentData;
    //int children;

    private static final String TAG = "People";

    /* Firebase initialization  */
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;



    String setParentIDChild1;
    String setParentIDChild2;
    String setParentIDChild3;

    /* Global variables to be populated
    when creating Child objects */
    String firstNameChild1;
    String lastNameChild1;
    String idStringChild1;
    String ageStringChild1;
    int    ageInMonthsChild1;
    int avgHeartRateChild1;

    String firstNameChild2;
    String lastNameChild2;
    String idStringChild2;
    String ageStringChild2;
    int    ageInMonthsChild2;
    int avgHeartRateChild2;

    String firstNameChild3;
    String lastNameChild3;
    String idStringChild3;
    String ageStringChild3;
    int    ageInMonthsChild3;
    int avgHeartRateChild3;

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

        //for loop of people being watched by user... generate activity_peopleLink button
        // add ability to click more people buttons


        final Button activity_peopleLink1 = (Button) findViewById(R.id.Person1);
        final Button activity_peopleLink2 = (Button) findViewById(R.id.Person2);
        final Button activity_peopleLink3 = (Button) findViewById(R.id.Person3);



        final Button activity_signoutLink = (Button) findViewById(R.id.signout);
        final Button activity_addLink = (Button) findViewById(R.id.AddPerson);

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

                //Parent parentData = dataSnapshot.child("Users").child(userID).getValue(Parent.class);
                System.out.println("User: " + userID + " Data: " + parentData.getFirstName() + " " + parentData.getLastName() +" "+ parentData.getNumChildren()+" "+ parentData.getPhone());
                System.out.println("User: " + userID + " Emergency Contact info: " + parentData.getEmergencyContactFirstName() + " " + parentData.getEmergencyContactLastName() +" "+ parentData.getEmergencyContactPhone());
                int children = parentData.getNumChildren();
                Child child1Data, child2Data, child3Data;
                if(children > 0) {
                    switch(children){
                        case 1:
                            //parentData.setChild1ID();
                            child1Data = dataSnapshot.child("Children").child(parentData.getChild1ID()).getValue(Child.class);
                            firstNameChild1 = child1Data.getChildFirstName();
                            lastNameChild1 = child1Data.getChildLastName();
                            child1Data.setParentId(userID);
                            idStringChild1 = child1Data.getParentId();
                            ageStringChild1 = child1Data.getChildAgeInMonths();
                            ageInMonthsChild1 = Integer.parseInt(ageStringChild1);
                            activity_peopleLink1.setText(firstNameChild1);
                            activity_peopleLink2.setVisibility(View.GONE);
                            activity_peopleLink3.setVisibility(View.GONE);

                            System.out.println(child1Data.getChildFirstName() + " "+ child1Data.getChildLastName() + " " + child1Data.getChildAgeInMonths() + " " + child1Data.getParentId());
                            break;

                        case 2:
                            child1Data = dataSnapshot.child("Children").child(parentData.getChild1ID()).getValue(Child.class);
                            firstNameChild1 = child1Data.getChildFirstName();
                            lastNameChild1 = child1Data.getChildLastName();
                            child1Data.setParentId(userID);
                            idStringChild1 = child1Data.getParentId();
                            ageStringChild1 = child1Data.getChildAgeInMonths();
                            ageInMonthsChild1 = Integer.parseInt(ageStringChild1);
                            activity_peopleLink1.setText(firstNameChild1);


                            child2Data = dataSnapshot.child("Children").child(parentData.getChild2ID()).getValue(Child.class);
                            firstNameChild2 = child2Data.getChildFirstName();
                            lastNameChild2 = child2Data.getChildLastName();
                            child2Data.setParentId(userID);
                            idStringChild2 = child2Data.getParentId();
                            ageStringChild2 = child2Data.getChildAgeInMonths();
                            ageInMonthsChild2 = Integer.parseInt(ageStringChild1);
                            activity_peopleLink2.setText(firstNameChild2);
                            activity_peopleLink3.setVisibility(View.GONE);

                            System.out.println(child1Data.getChildFirstName() + " "+ child1Data.getChildLastName() + " " + child1Data.getChildAgeInMonths() + " " + child1Data.getParentId());
                            System.out.println(child2Data.getChildFirstName() + " "+ child2Data.getChildLastName() + " " + child2Data.getChildAgeInMonths() + " " + child2Data.getParentId());
                            break;
                        case 3:
                            child1Data = dataSnapshot.child("Children").child(parentData.getChild1ID()).getValue(Child.class);
                            firstNameChild1 = child1Data.getChildFirstName();
                            lastNameChild1 = child1Data.getChildLastName();
                            child1Data.setParentId(userID);
                            idStringChild1 = child1Data.getParentId();
                            ageStringChild1 = child1Data.getChildAgeInMonths();
                            ageInMonthsChild1 = Integer.parseInt(ageStringChild1);
                            activity_peopleLink1.setText(firstNameChild1);

                            child2Data = dataSnapshot.child("Children").child(parentData.getChild2ID()).getValue(Child.class);
                            firstNameChild2 = child2Data.getChildFirstName();
                            lastNameChild2 = child2Data.getChildLastName();
                            child2Data.setParentId(userID);
                            idStringChild2 = child2Data.getParentId();
                            ageStringChild2 = child2Data.getChildAgeInMonths();
                            ageInMonthsChild2 = Integer.parseInt(ageStringChild1);
                            activity_peopleLink2.setText(firstNameChild2);

                            child3Data = dataSnapshot.child("Children").child(parentData.getChild3ID()).getValue(Child.class);
                            firstNameChild3 = child3Data.getChildFirstName();
                            lastNameChild3 = child3Data.getChildLastName();
                            child3Data.setParentId(userID);
                            idStringChild3 = child3Data.getParentId();
                            ageStringChild3 = child3Data.getChildAgeInMonths();
                            ageInMonthsChild3 = Integer.parseInt(ageStringChild1);
                            activity_peopleLink3.setText(firstNameChild3);
                            activity_addLink.setVisibility(View.GONE);

                            System.out.println(child1Data.getChildFirstName() + " "+ child1Data.getChildLastName() + " " + child1Data.getChildAgeInMonths() + " " + child1Data.getParentId());
                            System.out.println(child2Data.getChildFirstName() + " "+ child2Data.getChildLastName() + " " + child2Data.getChildAgeInMonths() + " " + child2Data.getParentId());
                            System.out.println(child3Data.getChildFirstName() + " "+ child3Data.getChildLastName() + " " + child3Data.getChildAgeInMonths() + " " + child3Data.getParentId());
                            break;
                    }
                } else {
                    activity_peopleLink1.setVisibility(View.GONE);
                    activity_peopleLink2.setVisibility(View.GONE);
                    activity_peopleLink3.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.print("Failed to read.");
            }
        });






        //If Person 1 is chosen -> Check their status.
        activity_peopleLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                /*CALL HEART RATE/PARSE JSON METHOD */
                boolean childInDanger;
                try {
                    childInDanger = heartRateData(firstNameChild1, lastNameChild1, ageInMonthsChild1, idStringChild1);
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

        //If Person 1 is chosen -> Check their status.
        activity_peopleLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                /*CALL HEART RATE/PARSE JSON METHOD */
                boolean childInDanger;
                try {
                    childInDanger = heartRateData(firstNameChild2, lastNameChild2, ageInMonthsChild2, idStringChild2);
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

        //If Person 1 is chosen -> Check their status.
        activity_peopleLink3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                /*CALL HEART RATE/PARSE JSON METHOD */
                boolean childInDanger;
                try {
                    childInDanger = heartRateData(firstNameChild3, lastNameChild3, ageInMonthsChild3, idStringChild3);
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
                //myRef.child("Users").child(userID).child("numChildren");
                //String childString = String.valueOf(children);
                Intent activity_peopleIntent = new Intent(People.this, Add.class);
                //activity_peopleIntent.putExtra("numChildren", );
                People.this.startActivity(activity_peopleIntent);
            }

        });

        //If Sign Out is chosen.
        activity_signoutLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Open login view
                mAuth.signOut();
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
        inDanger = compareAverageHeartRate(averageHeartRate, age);

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
