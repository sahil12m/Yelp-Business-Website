package com.example.initialscreen;


import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<BookItem> booklist;
    private OnItemClickListenerXX onIteminterface;


    public BookingsAdapter(Context context, ArrayList<BookItem> exampleList) {
        Log.d("SN", "construct");
        mContext = context;
        booklist = exampleList;

    }
    public ArrayList<BookItem> getData() {
        return booklist;
    }

    public void removeItem(int position) {
        booklist.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.bookingstable, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        BookItem currItem = booklist.get(position);
        Log.d("SAA size", String.valueOf(booklist.size()));

        Log.d("srnoprev00", "working");
        int srno = position+1;
        Log.d("srnoafter", String.valueOf(srno));
        String bname = currItem.getbname();
//        Log.d("SS ", bname);
        String bdate = currItem.getbdate();
        String btime = currItem.getbtime();
        String bemail = currItem.getbemail();
        Log.d("SA", "ck1");
        holder.busTextViewsrno.setText(Integer.toString(srno));
        Log.d("SA", "ck2");
        holder.busTextViewbname.setText(bname);
        //Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.busImageView);
        Log.d("SA", "ck3");
        holder.busTextViewbdate.setText(bdate);
        holder.busTextViewbtime.setText(btime);
        holder.busTextViewbemail.setText(bemail);
        Log.d("SA", "ck4");
//        holder.mTextViewCreator.setText(creatorName);
//        holder.mTextViewLikes.setText("Likes: " + likeCount);
        Log.d("SN", "onBind");
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView busTextViewbemail;
        public TextView busTextViewsrno;
        public TextView busTextViewbname;
        public TextView busTextViewbdate;
        public TextView busTextViewbtime;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            busTextViewbname = itemView.findViewById(R.id.name);
            busTextViewsrno = itemView.findViewById(R.id.count);
            busTextViewbdate = itemView.findViewById(R.id.date);
            busTextViewbtime = itemView.findViewById(R.id.time);
            busTextViewbemail = itemView.findViewById(R.id.email);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onIteminterface != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            Log.d("SA onclick adapter","");
//                            onIteminterface.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }

    }
}
