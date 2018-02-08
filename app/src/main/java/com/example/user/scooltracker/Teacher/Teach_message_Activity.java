package com.example.user.scooltracker.Teacher;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.scooltracker.Pojo.Students_list_Pojo;
import com.example.user.scooltracker.R;
import com.example.user.scooltracker.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teach_message_Activity extends AppCompatActivity {

    Spinner msgTypeSpin,studentsSpin;
    String [] msgType={"General Message","Message For Students"};
    ArrayList<String>arrayStudentsList=new ArrayList<>();
    Button submitBtn;
    EditText edtTitle,edtMsg;
    String student;
    String message_type="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_message_);

        msgTypeSpin=findViewById(R.id.TM_spin_type);
        studentsSpin=findViewById(R.id.TM_spin_stude);
        submitBtn=findViewById(R.id.TM_btn_msg);
        edtTitle=findViewById(R.id.TM_edt_title);
        edtMsg=findViewById(R.id.TM_edt_msg);

        ArrayAdapter <String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,msgType);
        msgTypeSpin.setAdapter(adapter);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtTitle.getText().toString().isEmpty()){
                    Toast.makeText(Teach_message_Activity.this, "Title Field Is Blank!", Toast.LENGTH_SHORT).show();
                }else if (edtMsg.getText().toString().isEmpty()){
                    Toast.makeText(Teach_message_Activity.this, "Message Field Is Blank!", Toast.LENGTH_SHORT).show();
                }else {
                    SendMeassage sent=new SendMeassage();
                    sent.execute();
                }
            }
        });
        studentsSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                int ii=position+1;
                student= String.valueOf(ii);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        msgTypeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                if (position==0){
                    message_type="general";
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    studentsSpin.setVisibility(View.INVISIBLE);
                }else if (position==1){
                    message_type="single";
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    studentsSpin.setVisibility(View.VISIBLE);
                    arrayStudentsList.clear();
                    StudentsList students=new StudentsList();
                    students.execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private class StudentsList extends AsyncTask{

        String action="student_message";
        String userId="1";
        String classa="7";
        String div="7";
        @Override
        protected Object doInBackground(Object[] objects) {

            new RetrofitHelper(Teach_message_Activity.this).getApi().getStudeList(action,userId,classa,div).enqueue(
                    new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response.body().toString());
                                String status=jsonObject.getString("status");
                                JSONArray  jsonArray=jsonObject.getJSONArray("students");
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    String id=jsonObject1.getString("id");
                                    String name=jsonObject1.getString("name");
//                                    Students_list_Pojo pojo=new Students_list_Pojo();
//                                    pojo.setId(id);
//                                    pojo.setName(name);
                                    arrayStudentsList.add(name);
                                    String[] arr = arrayStudentsList.toArray(new String[arrayStudentsList.size()]);
                                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Teach_message_Activity.this,
                                            android.R.layout.simple_spinner_item,arr);
                                    studentsSpin.setAdapter(adapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {

                        }
                    }
            );
            return null;
        }
    }
    private class SendMeassage extends AsyncTask{
        String action="send_message";
        String title=edtTitle.getText().toString();
        String message=edtMsg.getText().toString();
        String user_id="1";
        String classa="7";
        String division="7";

        @Override
        protected Object doInBackground(Object[] objects) {
            new RetrofitHelper(Teach_message_Activity.this).getApi().sentMessage(action,user_id,message_type,
                    title,message,student,classa,division).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        String status=jsonObject.getString("message");
                        Toast.makeText(Teach_message_Activity.this, status, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {

                }
            });
            return null;
        }
    }
}
