package com.example.user.scooltracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.user.scooltracker.Teacher.Teach_attendance_Activity;
import com.example.user.scooltracker.Teacher.Teach_message_Activity;
import com.example.user.scooltracker.Teacher.Teach_sentBoxActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout prof_lin,markAtt_lin,newMsg_line,msgLog_linea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prof_lin=findViewById(R.id.MA_prof_line);
        markAtt_lin=findViewById(R.id.MA_markAtt_linea);
        newMsg_line=findViewById(R.id.MA_newMsg_line);
        msgLog_linea=findViewById(R.id.MA_msg_log_linea);
        prof_lin.setOnClickListener(this);
        markAtt_lin.setOnClickListener(this);
        newMsg_line.setOnClickListener(this);
        msgLog_linea.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.MA_prof_line:

                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.MA_markAtt_linea:
                Intent intent1=new Intent(MainActivity.this,Teach_attendance_Activity.class);
                startActivity(intent1);
                break;
            case R.id.MA_newMsg_line:
                Intent intent2=new Intent(MainActivity.this,Teach_message_Activity.class);
                startActivity(intent2);
                break;
            case R.id.MA_msg_log_linea:
                Intent intent3=new Intent(MainActivity.this,Teach_sentBoxActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
