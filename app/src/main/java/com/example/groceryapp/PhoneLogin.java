package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneLogin extends AppCompatActivity {

    EditText phone,otp;
    Button generateOtp,verifyOtp;
    FirebaseAuth firebaseAuth ;
    String verificationId;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        phone = findViewById(R.id.phone);
        otp = findViewById(R.id.otp);
        generateOtp = findViewById(R.id.generateOtp);
        verifyOtp = findViewById(R.id.verifyOtp);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        generateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    Toast.makeText(PhoneLogin.this, "Enter valid phone number ", Toast.LENGTH_SHORT).show();
                } else {
                    String number = phone.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    senderVerificationCode(number);
                }

            }
        });

        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(otp.getText().toString())) {
                    Toast.makeText(PhoneLogin.this, "Wrong otp entered ", Toast.LENGTH_SHORT).show();
                } else {

                    VerificationCode(otp.getText().toString());
                }

            }
        });

    }
        private void VerificationCode(String code) {

            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
            signinbyCredentials(credential);
        }

        private void signinbyCredentials(PhoneAuthCredential credential) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(PhoneLogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PhoneLogin.this,MainActivity.class));
                            }
                        }
                    });
        }

        private void senderVerificationCode(String phoneNumber) {
            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber("+91"+phoneNumber)       // Phone number to verify
                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                            .setActivity(this)                 // Activity (for callback binding)
                            .setCallbacks(mcallbacks)          // OnVerificationStateChangedCallbacks
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        }

        private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                final String code = phoneAuthCredential.getSmsCode();
                if (code!=null)
                {
                    VerificationCode(code);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(PhoneLogin.this, "Verification failled", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken token) {

                super.onCodeSent(s,token);
                verificationId = s;
                Toast.makeText(PhoneLogin.this, "OTP Code sent", Toast.LENGTH_SHORT).show();
                verifyOtp.setEnabled(true);
                progressBar.setVisibility(View.INVISIBLE);
            }
        };


}