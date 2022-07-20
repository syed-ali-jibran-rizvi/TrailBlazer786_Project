package com.example.trailblazer786;

import static com.google.firebase.auth.PhoneAuthProvider.getInstance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class activity15 extends AppCompatActivity
{
    EditText t2;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity15);


        t2=(EditText)findViewById(R.id.t2);
        b2=(Button)findViewById(R.id.b2);




        b2.setOnClickListener(view -> {

            if(t2.getText().toString().isEmpty())
                Toast.makeText(getApplicationContext(),"Blank Field can not be processed",Toast.LENGTH_LONG).show();
            else if(Integer.valueOf(t2.getText().toString())!=Integer.valueOf(mainactivity.RandNum))
                Toast.makeText(getApplicationContext(),"INvalid OTP",Toast.LENGTH_LONG).show();
            else
            {
                Intent intent=new Intent(activity15.this,activity6.class);
                startActivity(intent);
            }

        });
    }






}