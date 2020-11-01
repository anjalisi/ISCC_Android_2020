package com.example.iscc.ui.todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iscc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNotes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNotes extends Fragment {
    private FirebaseAuth mAuth;
    Button addNewNote, cancel;
    EditText title, desc, date;
    DatabaseReference reference;
    Integer doesNum= new Random().nextInt();
    String keydoes=Integer.toString(doesNum);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_notes, container, false);
        mAuth= FirebaseAuth.getInstance();
        addNewNote= view.findViewById(R.id.createNew);
        cancel= view.findViewById(R.id.cancel);
        title= view.findViewById(R.id.atitle);
        desc= view.findViewById(R.id.adescription);
        date= view.findViewById(R.id.datedone);

        //Setting up the listeners
        cancel.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_todolist, null));
        addNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                title.onEditorAction(EditorInfo.IME_ACTION_DONE);
                FirebaseUser firebaseUser= mAuth.getCurrentUser();
                String userId= firebaseUser.getUid();

                reference= FirebaseDatabase.getInstance().getReference("Students").child(userId).child("NotesAdded").child("Note"+doesNum);

                //Insert data to database
                //reference= FirebaseDatabase.getInstance().getReference().child("NotesAdded").child("Note"+doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String t=title.getText().toString().trim();
                        String d=desc.getText().toString().trim();
                        String dt=date.getText().toString().trim();

                        //Adding password check
                        if(t.length()<1){
                            title.setError("Title can not be empty");
                            title.requestFocus();
                            return;
                        }
                        if(d.length()<1){
                            desc.setError("Description can not be empty");
                            desc.requestFocus();
                            return;
                        }
                        if(dt.length()<1){
                            date.setError("Target can not be empty");
                            date.requestFocus();
                            return;
                        }
                        dataSnapshot.getRef().child("title").setValue(t);
                        dataSnapshot.getRef().child("description").setValue(d);
                        dataSnapshot.getRef().child("dateDone").setValue(dt);
                        dataSnapshot.getRef().child("keydoes").setValue(keydoes);

//                        Fragment navhost = getSupportFragmentManager().findFragmentById(R.id.fragment2);
//                        NavController c = NavHostFragment.findNavController(navhost);
//                        c.navigate(R.id.firstFragment);
                        addNewNote.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_todolist, null));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getContext(),"Click again to see all notes", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNotes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNotes.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNotes newInstance(String param1, String param2) {
        AddNotes fragment = new AddNotes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

}