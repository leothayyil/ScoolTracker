package com.example.user.scooltracker.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.scooltracker.Pojo.MsgsListPojo;
import com.example.user.scooltracker.R;
import com.example.user.scooltracker.Teacher.Teach_MsgDetails_Activity;

import java.util.ArrayList;

/**
 * Created by USER on 03-02-2018.
 */

public class TMD_Adapter extends RecyclerView.Adapter<TMD_Adapter.MyViewHolder> {
    Context context;
    ArrayList<MsgsListPojo> msgsList=new ArrayList<>();
    public TMD_Adapter(Teach_MsgDetails_Activity context, ArrayList<MsgsListPojo> msgsList) {
     this.context=   context;
     this.msgsList=msgsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_tmd,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MsgsListPojo pojo=msgsList.get(position);
        holder.title.setText(pojo.getTitle());
        holder.msg.setText(pojo.getMsg());

    }

    @Override
    public int getItemCount() {
        return msgsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,msg;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.TMD_model_title);
            msg=itemView.findViewById(R.id.TMD_model_msg);
        }
    }
}
