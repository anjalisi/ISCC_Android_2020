package com.example.iscc.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.navigation.Navigation;

import com.example.iscc.DashboardTeachers;
import com.example.iscc.R;
import com.example.iscc.UploadDocs;

import com.example.iscc.ui.allbooks.EbookActivity;

public class HomeFragment extends Fragment {
    LinearLayout toDoList, chat, ebooks, bookmark;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //navigate to other fragments

        ebooks= root.findViewById(R.id.ebooks);
        ebooks.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_listbook    , null));
//        ebooks.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(getContext(), EbookActivity.class));
//                    }
//                }
//        );
        chat= root.findViewById(R.id.chat);
        chat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getContext(), DashboardTeachers.class));
                    }
                }
        );
        bookmark= root.findViewById(R.id.bookMark);
        bookmark.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_bookmark, null));
        toDoList= root.findViewById(R.id.toDoList);
        toDoList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_todolist, null));

        return root;
    }
}