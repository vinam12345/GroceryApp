package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegistrationActivity extends AppCompatActivity {

    Button login,register;
    ScrollView container;
    TextInputLayout uname,uemail,upass;
    String name,email,pass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        uname = findViewById(R.id.uname);
        uemail = findViewById(R.id.uemail);
        upass = findViewById(R.id.upassword);
        login=findViewById(R.id.login);
        container=findViewById(R.id.container);
        register=findViewById(R.id.register);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.enter_from_left);
        container.setAnimation(animation);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();
                /*if (name.isEmpty()|| email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser();
                }*/
                /*Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);*/
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registerUser() {
        name = uname.getEditText().toString();
        email = uemail.getEditText().toString();
        pass = upass.getEditText().toString();
       /* sharedPreferences = getApplicationContext().getSharedPreferences("user_details",0);
        editor = sharedPreferences.edit();*/
        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(RegistrationActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            updateUser();
                            /*startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                            finish();*/
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUser() {
        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        auth.getCurrentUser().updateProfile(changeRequest);
        auth.signOut();
        openLogin();
    }

    private void openLogin() {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
        finish();
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser!=null)
        {
            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            finish();
        }
    }
}