package com.example.techwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import static com.example.techwork.MainActivity.count;

public class Register_Workshop extends AppCompatActivity {

    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__workshop);
        bt=findViewById(R.id.btn);
    }
    public void regist()
    {
     if(count==0)
     {
         Toast.makeText(this,"Log in to register",Toast.LENGTH_SHORT).show();
     }
     else
     {

     }
    }
}
