package com.example.iscc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    Animation logoAnim;
    ImageView logo;
    //adding a new screen after splash
    private static int SPLASH_SCREEN = 1800;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Making the splash appear in full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        logo = (ImageView) findViewById(R.id.logoImg);
        //Adding splash fade in animation
        logoAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.logo_animation);
        logo.setAnimation(logoAnim);

        firebaseAuth= FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            //Logged in
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent login = new Intent(getApplicationContext(), NavigationDrawer.class);
                    startActivity(login);
                    finish();
                }
            }, SPLASH_SCREEN);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent login = new Intent(MainActivity.this, FirstPage.class);
                    startActivity(login);
                    finish();
                }
            }, SPLASH_SCREEN);
        }
    }
}