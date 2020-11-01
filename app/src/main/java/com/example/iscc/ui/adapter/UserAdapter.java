package com.example.iscc.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iscc.MessageActivity;
import com.example.iscc.R;
import com.example.iscc.ui.users.Chat;
import com.example.iscc.ui.users.TeacherUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<TeacherUser> mUsers;
    String theLastMsg;
private boolean ischat;
    public UserAdapter(Context context, List<TeacherUser> mUsers, boolean ischat){
        this.context=context;
        this.mUsers=mUsers;
        this.ischat= ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);

        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TeacherUser user= mUsers.get(position);
        holder.username.setText(user.getUsername());

        if(user.getImageURL().equals("default"))
            holder.profile_image.setImageResource(R.drawable.teacher);
        else
            Glide.with(context).load(user.getImageURL()).into(holder.profile_image);

        if(ischat){
            lastMessage(user.getId(), holder.last_message);
        }else{
            holder.last_message.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profile_image;
        private TextView last_message;

        public ViewHolder(View itemView){
            super(itemView);

            last_message= itemView.findViewById(R.id.last_message);
            username= itemView.findViewById(R.id.username);
            profile_image= itemView.findViewById(R.id.profile_image);
        }
    }

    private void lastMessage(final String userid, final TextView last_msg){
        theLastMsg="default";
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Chat chat= snapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid) ||
                            chat.getReceiver().equals(userid) && chat.getSender().equals(firebaseUser.getUid())){
                        theLastMsg  = chat.getMessage();
                    }
                }
                switch (theLastMsg){
                    case "default":
                        last_msg.setText("");
                    break;
                    default:
                        last_msg.setText(theLastMsg);
                        break;
                }
                theLastMsg="default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
