package com.ufl.harmwatchr.harmwatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.support.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EmailPassword";

    //Click login button --> Open People
    EditText email;
    EditText pword;
    Button activity_peopleLink;
    Button activity_registerLink;


    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Click login button --> Open People
        email = (EditText) findViewById(R.id.username);
        pword = (EditText) findViewById(R.id.Password);
        activity_peopleLink = (Button) findViewById(R.id.Login);
        activity_registerLink = (Button) findViewById(R.id.Register);

        // Buttons
        findViewById(R.id.Login).setOnClickListener(this);
        findViewById(R.id.Register).setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                //updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]


    }
    private void signIn(String email, String pword) {
        Log.d(TAG, "signIn:" + email);
        /*
        if (!validateForm()) {
            return;
        }
        */
        //showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, pword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent activity_peopleIntent = new Intent(Login.this, People.class);
                            Login.this.startActivity(activity_peopleIntent);


                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.

                        }
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Login.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        /*
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }
                        */
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

        //activity_peopleLink.setOnClickListener(new View.OnClickListener(){
          //  @Override
            //public void onClick(View v){
                //Verify login is in DB (In Progress.)

              //  if(email.getText().toString().equals("admin") && pword.getText().toString().equals("admin"/*firebase call to password*/)){
                //    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                  //  Intent activity_peopleIntent = new Intent(Login.this, People.class);
                    //Login.this.startActivity(activity_peopleIntent);
                //}
                //else {
                  //  Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
             //   }
            //}

        //});

        //take user to register page
        /*
        activity_registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent activity_registerIntent = new Intent(Login.this, Register.class);
                Login.this.startActivity(activity_registerIntent);

            }

        });
        */

    @Override
    public void onClick(View v) {
        int i = v.getId();

        //Intent activity_registerIntent = new Intent(Login.this, Register.class);
        //Login.this.startActivity(activity_registerIntent);

        if (i == R.id.Login) {
            signIn(email.getText().toString(), pword.getText().toString());
        }
        else {
            Intent activity_registerIntent = new Intent(Login.this, Register.class);
            Login.this.startActivity(activity_registerIntent);
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]

}

