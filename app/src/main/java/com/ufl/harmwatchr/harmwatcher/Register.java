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

//import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity{

    private EditText email;
    private EditText password;
    //private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        //progressDialog = new ProgressDialog(this);

        final Button buttonRegister = (Button) findViewById(R.id.register_submit);
        final Button back = (Button) findViewById(R.id.back);
        final TextView register = (TextView) findViewById(R.id.status);
        final EditText first_name = (EditText) findViewById(R.id.first_name);
        final EditText last_name = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        final EditText phone = (EditText) findViewById(R.id.phone);

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registration Unsuccessful - Please Try Again", Toast.LENGTH_SHORT).show();

                        } else{
                            Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent activity_peopleIntent = new Intent(Register.this, People.class);
                            Register.this.startActivity(activity_peopleIntent);

                        }
                    }
                });
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //Successful
                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    //unsuccessful
                    Toast.makeText(Register.this, "Registration Unsuccessful - Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        };

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

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);

        }
    }

}
