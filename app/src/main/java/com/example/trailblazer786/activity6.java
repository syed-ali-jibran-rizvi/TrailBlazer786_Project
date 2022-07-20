package com.example.trailblazer786;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 class activity6 extends AppCompatActivity {
    EditText EMAIL,PASSWORD;
    Button LOGIN;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        EMAIL=findViewById(R.id.Email);
        PASSWORD=findViewById(R.id.PASSWORD);
        LOGIN=findViewById(R.id.buttonLogin);
        DB=new DBhelper(this);
        LOGIN.setOnClickListener(view -> {
            String em=EMAIL.getText().toString();
            String ps=PASSWORD.getText().toString();
            if(em.equals("")||ps.equals("")){
                Toast.makeText(activity6.this,"EMPTY DETAILS",Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean checkemailpass=DB.checkemailPassword(em,ps);
                if(checkemailpass){
                    Toast.makeText(activity6.this,"SIGN-IN SUCCESSFULL",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity6.this, activity3.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(activity6.this,"INCORRECT CREDENTIALS",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    public void register(View view)
    {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }
}