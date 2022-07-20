package com.example.trailblazer786;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaSession;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class mainactivity extends AppCompatActivity
{
    CountryCodePicker ccp;
    EditText t1,t2;
    Button b1,b2;
    FirebaseAuth mAuth;
    String verificationID;

    static Random random=new Random();
    static int RandNum=random.nextInt(999999);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        t1=(EditText)findViewById(R.id.t1);
        t2=(EditText)findViewById(R.id.verifyotp);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.buttonverify);
        mAuth=FirebaseAuth.getInstance();

        b2.setOnClickListener(view -> {
            if(TextUtils.isEmpty(t2.getText().toString()))
            {
                Toast.makeText(mainactivity.this,"INCORRECT OTP entered",Toast.LENGTH_LONG).show();
            }
            else
            {
                verifycode(t2.getText().toString());
            }
        });
        b1.setOnClickListener(view ->{
            if(TextUtils.isEmpty(t1.getText().toString()))
            {
                Toast.makeText(mainactivity.this,"Enter Valid Phone Number",Toast.LENGTH_LONG).show();
            }
            else
            {
                String number=t1.getText().toString();
                sendverificationcode(number);
            }

        } );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
     @Override

    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential)
            {
                final String code= credential.getSmsCode();
                if(code!=null)
                {
                    verifycode(code);
                }
    }

    @Override
    public void onVerificationFailed(@NonNull FirebaseException e) {
         Toast.makeText(mainactivity.this,"Verification Failed",Toast.LENGTH_LONG).show();

        // Show a message and update the UI
    }

    @Override
    public void onCodeSent(@NonNull String s,
            @NonNull PhoneAuthProvider.ForceResendingToken token) {
        super.onCodeSent(s, token);
        verificationID=s;
    }
};
    private void sendverificationcode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifycode(String code)
    {
        PhoneAuthCredential  Credential=PhoneAuthProvider.getCredential(verificationID,code);
        signinbyCredentials(Credential);
    }

    private void signinbyCredentials(PhoneAuthCredential credential)
    {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(mainactivity.this, "SUCCESFUL", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(mainactivity.this, activity3.class));
                }
            }

        });

    }
}