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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupTeacher extends AppCompatActivity {
    ProgressBar progressBar;
    EditText email, password, username;
    Button register;
    TextView login;
    private FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teacher);

        progressBar= findViewById(R.id.progBar);
        email= findViewById(R.id.emailID);
        password= findViewById(R.id.password);
        username= findViewById(R.id.teacherName);
        register= findViewById(R.id.signupButton);
        login= findViewById(R.id.loginBtn);

        mAuth= FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.onEditorAction(EditorInfo.IME_ACTION_DONE);
                String username_txt= username.getText().toString().trim();
                String pass= password.getText().toString().trim();
                String email_txt= email.getText().toString().trim();

                if(username_txt.isEmpty())
                {
                    username.setError("Name is required");
                    username.requestFocus();
                    return;
                }
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

                register(username_txt, pass, email_txt);
            }
        });
    }
    private void register(final String username, String password, String email){
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseUser= mAuth.getCurrentUser();
                            String userId= firebaseUser.getUid();

                            reference= FirebaseDatabase.getInstance().getReference("Students").child(userId);

                            HashMap<String, String> hashMap= new HashMap<>();
                            hashMap.put("id", userId);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");
                            hashMap.put("useris","teacher");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent= new Intent(getApplicationContext(), NavigationDrawer.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}