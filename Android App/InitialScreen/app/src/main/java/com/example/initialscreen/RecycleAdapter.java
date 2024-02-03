package com.example.initialscreen;


import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<TabItem> bbusreslist;
    private OnItemClickListenerXX onIteminterface;


    public RecycleAdapter(Context context, ArrayList<TabItem> exampleList, OnItemClickListenerXX onIteminterface) {
        Log.d("SN", "construct");
        mContext = context;
        bbusreslist = exampleList;
        this.onIteminterface = onIteminterface;

    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.tablerows, parent, false);
        return new ExampleViewHolder(v, onIteminterface);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        TabItem currItem = bbusreslist.get(position);

        Log.d("srnoprev00", "working");
        int srno = currItem.getsrno();
        Log.d("srnoafter", String.valueOf(srno));
        String imageUrl = currItem.getImageUrl();
        String busname = currItem.getbusname();
        String ratings = currItem.getratings();
        String dist = currItem.getdist();
        Log.d("SA", "ck1");
        holder.busTextViewsrno.setText(Integer.toString(srno));
        Log.d("SA", "ck2");
        if(imageUrl != null || imageUrl != "") {
            Picasso.get()
                    .load(imageUrl)
                    .resize(40, 40)
                    .centerCrop()
                    .into(holder.busImageView);
        }
        //Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.busImageView);
        Log.d("SA", "ck3");
        holder.busTextViewbusname.setText(busname);
        holder.busTextViewratings.setText(ratings);
        holder.busTextViewdist.setText(dist);
        Log.d("SA", "ck4");
//        holder.mTextViewCreator.setText(creatorName);
//        holder.mTextViewLikes.setText("Likes: " + likeCount);
          Log.d("SN", "onBind");
    }

    @Override
    public int getItemCount() {
        return bbusreslist.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView busImageView;
        public TextView busTextViewsrno;
        public TextView busTextViewbusname;
        public TextView busTextViewratings;
        public TextView busTextViewdist;

        public ExampleViewHolder(View itemView,OnItemClickListenerXX onIteminterface) {
            super(itemView);
            busImageView = itemView.findViewById(R.id.imageView);
            busTextViewsrno = itemView.findViewById(R.id.textView2);
            busTextViewbusname = itemView.findViewById(R.id.textView7);
            busTextViewratings = itemView.findViewById(R.id.textView5);
            busTextViewdist = itemView.findViewById(R.id.textView6);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onIteminterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Log.d("SA onclick adapter","");
                            onIteminterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
