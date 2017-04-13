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

public class People extends AppCompatActivity {

    private static final String TAG = "People";

    /* Firebase initialization stuff */
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;

    String firstName;
    String lastName;
    String idString;
    boolean inDanger;

    /*Global Parent and Child Objects */
    //Parent parentData;
    //Child child1Data;
    //Child child2Data;
    //Child child3Data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                // ...
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Parent parentData = dataSnapshot.child("Users").child(userID).getValue(Parent.class);
                System.out.println(parentData.getChild1ID());

                if(parentData.getNumChildren() > 0) {
                    Child child1Data = dataSnapshot.child("Children").child(parentData.getChild1ID()).getValue(Child.class);
                    firstName = child1Data.getChildFirstName();
                    lastName  = child1Data.getChildLastName();
                    idString  = parentData.getChild1ID();
                    System.out.println("Child info: "+ firstName +  " " + lastName);

                    /* Call Heart Rate Data method when you click Person 1 */


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
                            System.out.println("Child1ID: "+parentData.getChild1ID()+"Child2ID: "+parentData.getChild2ID());
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
                //Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });


        //for loop of people being watched by user... generate activity_peopleLink button
        final Button activity_peopleLink = (Button) findViewById(R.id.Person1);

        final Button activity_signoutLink = (Button) findViewById(R.id.signout);
        final Button activity_addLink = (Button) findViewById(R.id.AddPerson);

        //If Person is chosen -> Check their status.
        activity_peopleLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                /*CALL HEART RATE/PARSE JSON METHOD */
                boolean childInDanger;
                childInDanger = heartRateData(firstName, lastName, idString);

                if (!childInDanger) {
                    Intent activity_peopleIntent = new Intent(People.this, StatusGood.class);
                    People.this.startActivity(activity_peopleIntent);
                }

                else {
                    Intent activity_peopleIntent = new Intent(People.this, StatusBad.class);
                    People.this.startActivity(activity_peopleIntent);
                }

                // If inDanger is true...send to StatusBad.java page and send first text

                /*Conditional statement to determine if Intent to StatusGood.Good or StatusGood.Bad */

                //Intent activity_peopleIntent = new Intent(People.this, StatusGood.class);
                //People.this.startActivity(activity_peopleIntent);
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

    public boolean heartRateData(String fName, String lName, String id) {
        System.out.println("Your child: " + fName + " " + lName + " " + id + " could be in danger.");
        String url = fName + "-" + lName + "-" + id;

        inDanger = true;
        return inDanger;
    }


    /*private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            //Child child1 = new Child();
            //child1.setChildFirstName(ds.child(userID).getValue(Child.class).getChildFirstName());
            //child1.setChildLastName(ds.child(userID).getValue(Child.class).getChildLastName());

            //Log.e(TAG, "showUID: " + userID+ "Child firstName: "+ child1.getChildFirstName() + "Child lastName: "+ child1.getChildLastName());
            //Log.d(TAG, "showData: name: " + child1.getChildFirstName());
            //Log.d(TAG, "showData: email: " + child1.getChildLastName());


            UserInformation uInfo = new UserInformation();
            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName()); //set the name
            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail()); //set the email
            uInfo.setPhone_num(ds.child(userID).getValue(UserInformation.class).getPhone_num()); //set the phone_num

            //display all the information
            Log.d(TAG, "showData: name: " + uInfo.getName());
            Log.d(TAG, "showData: email: " + uInfo.getEmail());
            Log.d(TAG, "showData: phone_num: " + uInfo.getPhone_num());
            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getName());
            array.add(uInfo.getEmail());
            array.add(uInfo.getPhone_num());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);


        }
    */
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
