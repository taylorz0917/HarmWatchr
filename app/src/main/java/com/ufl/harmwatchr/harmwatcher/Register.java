package com.ufl.harmwatchr.harmwatcher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password, first_name, last_name, phone, ecFirst, ecLast, ecNumber;
    private ProgressDialog progressDialog;

    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        progressDialog = new ProgressDialog(this);

        final Button buttonRegister = (Button) findViewById(R.id.register_submit);
        final Button back = (Button) findViewById(R.id.back);
        final TextView register = (TextView) findViewById(R.id.status);
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        ecFirst = (EditText) findViewById(R.id.emergencyContactFName);
        ecLast = (EditText) findViewById(R.id.emergencyContactLName);
        ecNumber = (EditText) findViewById(R.id.emergencyContactPhone);


        buttonRegister.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Verify login is in DB (In Progress.)

                //If login is correct:  --> Open People view
                Intent activity_loginIntent = new Intent(Register.this, Login.class);
                Register.this.startActivity(activity_loginIntent);

                //Else:  --> Reject login, do nothing.
            }

        });

    }

    private void registerUser() {
        final String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        final String last = last_name.getText().toString().trim();
        final String first = first_name.getText().toString().trim();
        final String ph = phone.getText().toString().trim();
        final String ecPhoneNumber = ecNumber.getText().toString().trim();
        final String ecFName = ecFirst.getText().toString().trim();
        final String ecLName = ecLast.getText().toString().trim();

        if(TextUtils.isEmpty(userEmail)) {
            //email is empty, stop function from further execution
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)) {
            //password is empty, stop function from further execution
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(ph) || TextUtils.isEmpty(first) || TextUtils.isEmpty(last)){
            Toast.makeText(this, "Insufficient information", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                    FirebaseUser user = mAuth.getCurrentUser();
                    userID = user.getUid();

                    myRef.child("Users").child(userID).child("email").setValue(userEmail);
                    myRef.child("Users").child(userID).child("firstName").setValue(first);
                    myRef.child("Users").child(userID).child("lastName").setValue(last);
                    myRef.child("Users").child(userID).child("phone").setValue(ph);
                    myRef.child("Users").child(userID).child("emergencyContactFirstName").setValue(ecFName);
                    myRef.child("Users").child(userID).child("emergencyContactLastName").setValue(ecLName);
                    myRef.child("Users").child(userID).child("emergencyContactPhone").setValue(ecPhoneNumber);
                    myRef.child("Users").child(userID).child("numChildren").setValue(0);

                    Intent activity_peopleIntent = new Intent(Register.this, People.class);
                    Register.this.startActivity(activity_peopleIntent);
                }
                else {
                    Toast.makeText(Register.this, "Registration Unsuccessful - Please Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        registerUser();
    }
}
