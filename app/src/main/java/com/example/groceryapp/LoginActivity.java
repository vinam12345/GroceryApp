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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {


    Button login, signup, seller, forget,btnphone;
    FirebaseAuth auth;
    ScrollView container;
    Animation animation;
    TextInputLayout uname,password;
    String email,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        auth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        container = findViewById(R.id.container);
        seller = findViewById(R.id.seller);
        forget = findViewById(R.id.forgetLogin);
        uname = findViewById(R.id.uname);
        password = findViewById(R.id.password);
        btnphone = findViewById(R.id.btnphone);


        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enetr_from_right);
        container.setAnimation(animation);

        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,PhoneLogin.class));

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = uname.getEditText().toString();
                pass = password.getEditText().toString();
                if (email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else {

                    loginUser();
                }
                /*Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);*/
            }
        });

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(getApplicationContext(), SellerDashboardActivity.class);
                startActivity(intent);*/
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForgotPassword();
            }
        });


    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Login Successfullyyy", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Something went wrong"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser!=null)
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
    private void openDialogForgotPassword() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.forget_password_layout, null);
//        ElasticButton btn_save=(ElasticButton)view.findViewById(R.id.btn_forgetSubmit);

        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();

        Button submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Amazing!!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}