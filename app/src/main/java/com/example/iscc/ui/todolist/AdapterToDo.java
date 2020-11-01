package com.example.iscc.ui.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscc.R;

import java.util.ArrayList;

public class AdapterToDo extends RecyclerView.Adapter<AdapterToDo.MyViewHolder> {

    Context context;
    ArrayList<ToDoMain> toDoMains;

    public AdapterToDo(Context c, ArrayList<ToDoMain> p){
        context=c;
        toDoMains=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
        holder.title.setText(toDoMains.get(i).getTitle());
        holder.description.setText(toDoMains.get(i).getDescription());
        holder.dateDone.setText(toDoMains.get(i).getDateDone());

        final String getTitle= toDoMains.get(i).getTitle();
        final String getDesc= toDoMains.get(i).getDescription();
        final String getDate= toDoMains.get(i).getDateDone();
        final String getKey= toDoMains.get(i).getKeydoes();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(context, UpdateNotes.class);
                a.putExtra("title", getTitle);
                a.putExtra("description", getDesc);
                a.putExtra("dateDone", getDate);
                a.putExtra("keydoes", getKey);
                context.startActivity(a);
            }
        });

    }

    @Override
    public int getItemCount() {
        return toDoMains.size();
    }

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView title, description, dateDone,keydoes;
            public MyViewHolder(@Nullable View itemView){
                super(itemView);
                title= itemView.findViewById(R.id.title);
                description= itemView.findViewById(R.id.description);
                dateDone= itemView.findViewById(R.id.dateDone);
            }
        }
}
