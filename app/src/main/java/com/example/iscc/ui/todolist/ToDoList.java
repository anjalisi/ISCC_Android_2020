package com.example.iscc.ui.todolist;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iscc.NavigationDrawer;
import com.example.iscc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ToDoList extends Fragment {
    private FirebaseAuth mAuth;
    Button addNote;
    private ToDoListViewModel mViewModel;
    DatabaseReference reference;
    RecyclerView notes;
    AdapterToDo adapterToDo;
    ArrayList<ToDoMain> toDoMains;
    TextView title, description, dateDone;

    public static ToDoList newInstance() {
        return new ToDoList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.to_do_list_fragment, container, false);

        mAuth= FirebaseAuth.getInstance();
        addNote= view.findViewById(R.id.addNote);
        notes= view.findViewById(R.id.notes);
        title= view.findViewById(R.id.title);
        description= view.findViewById(R.id.description);
        dateDone= view.findViewById(R.id.dateDone);
        //working with data
        notes.setLayoutManager(new LinearLayoutManager(getContext()));
        toDoMains= new ArrayList<>();
        FirebaseUser firebaseUser= mAuth.getCurrentUser();
        String userId= firebaseUser.getUid();

        //get data from firebase
        reference= FirebaseDatabase.getInstance().getReference("Students").child(userId).child("NotesAdded");
        //reference= FirebaseDatabase.getInstance().getReference().child("NotesAdded");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Set code to retrieve data
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    ToDoMain p= dataSnapshot1.getValue(ToDoMain.class);
                    toDoMains.add(p);
                }
                adapterToDo= new AdapterToDo(getContext(), toDoMains);
                notes.setAdapter(adapterToDo);
                adapterToDo.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Code to show error
                Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
            }
        });

        //Making a new note
        addNote.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_newNote, null));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ToDoListViewModel.class);
        // TODO: Use the ViewModel
    }

}