package com.example.iscc.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscc.FileInfoModel;
import com.example.iscc.R;
import com.example.iscc.ViewPdf;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {
    private Context context;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    private List<FileInfoModel> list;

    public EbookAdapter(Context context, List<FileInfoModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_pdf, parent, false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EbookViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getFilename());
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userId = firebaseUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Students").child(userId).child("myuploads");

        holder.bookmark.setText(list.get(position).getStar() > 0 ? "Starred" : "Star");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ViewPdf.class);
                i.putExtra("filename", list.get(position).getFilename());
                i.putExtra("fileurl", list.get(position).getFileurl());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        if (holder.bookmark.equals("Starred")) {
            holder.star.setImageResource(R.drawable.booksss_24);
        }
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = reference.child("pdf" + list.get(position).getKeydoes());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (list.get(position).getStar() == 0) {
                            dataSnapshot.getRef().child("star").setValue(1);
                            holder.bookmark.setText("Starred");


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
//        holder.star.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(list.get(position).getStar() == 0){
//                    list.get(position).setStar(1);
//
//                }
//
//            }
//        });
        holder.ebookdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getFileurl()));
                context.startActivity(intent);
            }
        });
        holder.bookclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ViewPdf.class);
                i.putExtra("filename", list.get(position).getFilename());
                i.putExtra("fileurl", list.get(position).getFileurl());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private TextView title, bookmark;
        private ImageView star;
        private ImageView ebookdownload;
        RelativeLayout bookclick;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookclick = itemView.findViewById(R.id.bookclick);
            title = itemView.findViewById(R.id.header);
            bookmark = itemView.findViewById(R.id.textbookmark);
            star = itemView.findViewById(R.id.star);
            ebookdownload = itemView.findViewById(R.id.downloadebook);


        }
    }
}
