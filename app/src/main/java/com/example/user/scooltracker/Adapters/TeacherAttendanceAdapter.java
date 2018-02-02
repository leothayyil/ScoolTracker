package com.example.user.scooltracker.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.scooltracker.Pojo.Students_TeaAtt_Pojo;
import com.example.user.scooltracker.R;
import com.example.user.scooltracker.Teacher.Teach_attendance_Activity;

import java.util.ArrayList;

/**
 * Created by USER on 02-02-2018.
 */

public class TeacherAttendanceAdapter extends RecyclerView.Adapter<TeacherAttendanceAdapter.MyViewHolder>{

    Context context;
    ArrayList<Students_TeaAtt_Pojo>arrayList=new ArrayList<>();
    public TeacherAttendanceAdapter(Teach_attendance_Activity teach_attendance_activity,
                                    ArrayList<Students_TeaAtt_Pojo> students_arrayList) {
        this.context=teach_attendance_activity;
        this.arrayList=students_arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_attenndance,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Students_TeaAtt_Pojo pojo=arrayList.get(position);

        holder.name.setText(pojo.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.model_TA_name);
        }
    }
}
