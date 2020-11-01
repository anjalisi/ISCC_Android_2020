package com.example.iscc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTeacher extends AppCompatActivity {

    EditText email, password;
    ProgressBar progressBar;
    Button loginBtn;
    TextView signup;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        auth= FirebaseAuth.getInstance();

        email= findViewById(R.id.emailID);
        password= findViewById(R.id.password);
        loginBtn= findViewById(R.id.loginButton);
        progressBar= findViewById(R.id.progBar);
        signup= findViewById(R.id.signupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupp= new Intent(getApplicationContext(), SignupTeacher.class);
                startActivity(signupp);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.onEditorAction(EditorInfo.IME_ACTION_DONE);
                String pass= password.getText().toString().trim();
                String email_txt= email.getText().toString().trim();
                if(pass.isEmpty())
                {
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }
                //Adding password check
                if(pass.length()<6){
                    password.setError("Minimum length of password should be 6");
                    password.requestFocus();
                    return;
                }
                if(email_txt.isEmpty())
                {
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                //Adding email checks
                if(!Patterns.EMAIL_ADDRESS.matcher(email_txt).matches()){
                    email.setError("Enter a valid email");
                    email.requestFocus();
                    return;
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(email_txt, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){

                                        Intent intent= new Intent(getApplicationContext(), NavigationDrawer.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}