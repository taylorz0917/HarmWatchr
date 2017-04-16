package com.ufl.harmwatchr.harmwatcher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class Add extends AppCompatActivity {

    private EditText fname, lname, age;
    private ProgressDialog progressDialog;

    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private String userID;
    private String userSameId;
    private FirebaseAuth.AuthStateListener mAuthListener;
    int numChildren;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NumberFormatException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        progressDialog = new ProgressDialog(this);

        final Button activity_peopleLink = (Button) findViewById(R.id.submit);
        fname = (EditText) findViewById(R.id.first_name);
        age = (EditText) findViewById(R.id.age);
        lname = (EditText) findViewById(R.id.last_name);
        final Button activity_backButton = (Button) findViewById(R.id.back);

        //Bundle bundle = getIntent().getExtras();
        //String numChildString = bundle.getString("numChildren");
        //System.out.println("Number of Children as string: " + numChildString);

        //numChildren = Integer.parseInt(numChildString);
        //System.out.println("numChildren: " + numChildren);

        final FirebaseUser userSame = mAuth.getCurrentUser();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userSameId = userSame.getUid();
                Parent parentDataFresh = dataSnapshot.child("Users").child(userSameId).getValue(Parent.class);
                numChildren = parentDataFresh.getNumChildren();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.print("Failed to read.");
            }
        });


        activity_peopleLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerChild(numChildren);

                Intent activity_peopleIntent = new Intent(Add.this, People.class);
                Add.this.startActivity(activity_peopleIntent);
            }

        });

        activity_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent activity_peopleIntent = new Intent(Add.this, People.class);
                Add.this.startActivity(activity_peopleIntent);
            }

        });

    }


    private void registerChild(int numChildren) {
        final String first = fname.getText().toString().trim();
        final String last = lname.getText().toString().trim();
        final String ageInMonths = age.getText().toString().trim();


        if(TextUtils.isEmpty(ageInMonths) || TextUtils.isEmpty(first) || TextUtils.isEmpty(last)){
            Toast.makeText(this, "Insufficient information", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering Child...");
        progressDialog.show();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null) {
            Parent updateParent = new Parent();
            String uniqueID = UUID.randomUUID().toString();
            myRef.child("Children").child(uniqueID).child("firstName").setValue(first);
            myRef.child("Children").child(uniqueID).child("lastName").setValue(last);
            myRef.child("Children").child(uniqueID).child("ageInMonths").setValue(ageInMonths);

            userID = user.getUid();
            myRef.child("Children").child(uniqueID).child("parentID").setValue(userID);


            System.out.println("Number of Children: " + numChildren);
            if(numChildren == 0) {
                myRef.child("Users").child(userID).child("child1ID").setValue(uniqueID);
                myRef.child("Users").child(userID).child("numChildren").setValue(1);
                //updateParent.setChild1ID(uniqueID);


            }

            else if (numChildren == 1) {
                myRef.child("Users").child(userID).child("child2ID").setValue(uniqueID);
                myRef.child("Users").child(userID).child("numChildren").setValue(2);

            }

            else {
                myRef.child("Users").child(userID).child("child3ID").setValue(uniqueID);
                myRef.child("Users").child(userID).child("numChildren").setValue(3);
            }

        }
        else{
            System.out.println("User is not signed in.");
        }

    }
}