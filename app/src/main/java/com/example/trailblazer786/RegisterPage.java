package com.example.trailblazer786;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
EditText name,email,password,profession,institute;
Button register;
DBhelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        name=findViewById(R.id.FULLNAME);
        email=findViewById(R.id.EMAIL);
        password=findViewById(R.id.PASSWORD);
        profession=findViewById(R.id.Profession);
        institute=findViewById(R.id.INSTITUTE);
        register=findViewById(R.id.buttonRegister);
        DB=new DBhelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT=name.getText().toString();
                String emailTXT=email.getText().toString();
                String passwordTXT=password.getText().toString();
                String professionTXT=profession.getText().toString();
                String instituteTXT=institute.getText().toString();
                Boolean checkem=DB.checkemail(emailTXT);
                if(!checkem) {
                    boolean checkinsertdata = DB.insertuserdata(nameTXT, emailTXT, passwordTXT, instituteTXT, professionTXT);
                    if (checkinsertdata) {
                        Toast.makeText(RegisterPage.this, "REGISTERED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterPage.this, "REGISTRATION FAILED! TRY AFTER SOME TIME", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegisterPage.this, "EMAIL ALREADY REGISTERED", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void login(View view)
    {
        Intent intent = new Intent(this, mainactivity.class);
        startActivity(intent);
    }
}