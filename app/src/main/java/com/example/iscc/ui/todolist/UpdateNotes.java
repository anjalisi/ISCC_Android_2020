package com.example.iscc.ui.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iscc.FirstPage;
import com.example.iscc.NavigationDrawer;
import com.example.iscc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateNotes extends AppCompatActivity {
    EditText title, desc, date;
    Button btnDelete;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);
        btnDelete = findViewById(R.id.delete);
        //btnUpdate = findViewById(R.id.saveUpdate);
        title = findViewById(R.id.atitle);
        desc = findViewById(R.id.adescription);
        date = findViewById(R.id.datedone);

        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("description"));
        date.setText(getIntent().getStringExtra("dateDone"));

        final String keykeydoes = getIntent().getStringExtra("keydoes");
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser= mAuth.getCurrentUser();
        String userId= firebaseUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Students").child(userId).child("NotesAdded").child("Note" + keykeydoes);
//
//        final String key=getIntent().getStringExtra("keydoes");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //Navigation.findNavController(view).navigate(R.id.nav_todolist);
                            Intent a= new Intent(getApplicationContext(), NavigationDrawer.class);
                            Toast.makeText(getApplicationContext(),"Deleted Note!", Toast.LENGTH_SHORT).show();
                            startActivity(a);
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Can not delete note", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        dataSnapshot.getRef().child("title").setValue(title.getText().toString().trim());
//                        dataSnapshot.getRef().child("description").setValue(desc.getText().toString().trim());
//                        dataSnapshot.getRef().child("dateDone").setValue(date.getText().toString().trim());
//                        dataSnapshot.getRef().child("keydoes").setValue(keykeydoes);
//                        //Navigation.findNavController(view).navigate(R.id.nav_home);
//                        Intent a= new Intent(getApplicationContext(), NavigationDrawer.class);
//                        Toast.makeText(getApplicationContext(),"Updated Note!", Toast.LENGTH_SHORT).show();
//                        startActivity(a);
//                        finish();
//                    }
//
//                    //
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//            }
//        });

    }

}