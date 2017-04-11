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
//import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        final Button buttonRegister = (Button) findViewById(R.id.register_submit);
        final Button back = (Button) findViewById(R.id.back);
        final TextView register = (TextView) findViewById(R.id.status);
        final EditText first_name = (EditText) findViewById(R.id.first_name);
        final EditText last_name = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        final EditText phone = (EditText) findViewById(R.id.phone);


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
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

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

        progressDialog .setMessage("Registering User...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
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
