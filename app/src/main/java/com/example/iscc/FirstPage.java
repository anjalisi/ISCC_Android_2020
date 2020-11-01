package com.example.iscc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {

    Button loginStudent, loginTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        loginStudent= findViewById(R.id.loginStudent);
        loginTeacher= findViewById(R.id.loginTeacher);

        loginStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupp= new Intent(getApplicationContext(), LoginStudent.class);
                startActivity(signupp);
            }
        });

        loginTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupp= new Intent(getApplicationContext(), LoginTeacher.class);
                startActivity(signupp);
            }
        });
    }
}