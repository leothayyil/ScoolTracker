package com.example.user.scooltracker.Teacher;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.scooltracker.Adapters.TeacherAttendanceAdapter;
import com.example.user.scooltracker.Pojo.Class_Pojo;
import com.example.user.scooltracker.Pojo.Students_TeaAtt_Pojo;
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

import static com.example.user.scooltracker.LoginActivity.MY_PREFS_NAME;

public class Teach_attendance_Activity extends AppCompatActivity {

    String actionDivision="division";
    String actionClass="class";
    String actionAttendance="mark_attendance";
    ArrayList<Students_TeaAtt_Pojo>students_arrayList=new ArrayList<>();
    SharedPreferences preferences;
    RecyclerView recycAttendance;
    Button submit;
    TextView tv_classa,tv_name,tv_divi;
    TeacherAttendanceAdapter adapter;
    CardView studentsList;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_attendance_);
        recycAttendance=findViewById(R.id.recycAttendanceTeach);
        tv_classa=findViewById(R.id.TA_tv_class);
        submit=findViewById(R.id.TA_btn_submit);
        tv_name=findViewById(R.id.TA_tv_name);
        tv_divi=findViewById(R.id.TA_tv_div);
        studentsList=findViewById(R.id.TA_card_studentsList);
        dialog=new ProgressDialog(this);
        studentsList.setVisibility(View.GONE);
        dialog.setTitle("Getting students");
        dialog.show();
        recycAttendance.setHasFixedSize(true);
        recycAttendance.setLayoutManager(new LinearLayoutManager(this));


        preferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        String restoredTexts=preferences.getString("name","0");
        if (restoredTexts !=null){
            String namee = preferences.getString("name", "0");
            String classa = preferences.getString("class", "0");
            String divi=preferences.getString("division","0");
            String userIdd=preferences.getString("user_id","0");

            tv_name.setText("Name : "+namee);
            GetAttendance attendance=new GetAttendance(actionAttendance,userIdd,classa,divi);
            attendance.execute();
        }
    }
    private  class  GetAttendance extends AsyncTask{

        String action,userId,classa,div;
        public GetAttendance(String actionAttendance, String userIdd, String classa, String divi) {
            this.action=actionAttendance;
            this.userId=userIdd;
            this.classa=classa;
            this.div=divi;
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            new RetrofitHelper(Teach_attendance_Activity.this).getApi().getAttendance(actionAttendance,
                    userId,classa,div).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        String status = jsonObject.getString("status");
                        String classa = jsonObject.getString("class");
                        String division=jsonObject.getString("division");

                        tv_classa.setText("Class : "+classa+"/"+division);
                        tv_divi.setText(classa+"/"+division);

                        JSONArray jsonArray = jsonObject.getJSONArray("students");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            int id = jsonObject1.getInt("id");
                            String name=jsonObject1.getString("name");

                            Students_TeaAtt_Pojo pojo=new Students_TeaAtt_Pojo();
                            pojo.setId(id);
                            pojo.setName(name);
                            students_arrayList.add(pojo);
                            studentsList.setVisibility(View.VISIBLE);
                            dialog.dismiss();
                        }
                        adapter=new TeacherAttendanceAdapter(Teach_attendance_Activity.this,students_arrayList);
                        recycAttendance.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        dialog.dismiss();
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