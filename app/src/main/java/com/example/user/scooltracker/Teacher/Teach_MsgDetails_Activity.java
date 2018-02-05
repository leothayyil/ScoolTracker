package com.example.user.scooltracker.Teacher;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.user.scooltracker.Adapters.TMD_Adapter;
import com.example.user.scooltracker.Pojo.MsgsListPojo;
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

public class Teach_MsgDetails_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView title,msg;
    ArrayList <MsgsListPojo>msgsList=new ArrayList<>();
    String TAG="loggg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach__msg_details_);

        recyclerView=findViewById(R.id.TMD_recycler);
        title=findViewById(R.id.TMD_tv_title);
        msg=findViewById(R.id.TMD_tv_msg);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MsgDetails msgDetails=new MsgDetails();
        msgDetails.execute();
    }
    private  class  MsgDetails extends AsyncTask{
String action="reply_details";
String user_id="1";
String message_id="1";

        @Override
        protected Object doInBackground(Object[] objects) {
            new RetrofitHelper(Teach_MsgDetails_Activity.this).getApi().replyDetails(action,user_id,message_id)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response.body().toString());
                                String mainTitle=jsonObject.getString("tilte");
                                String message=jsonObject.getString("message");
                                title.setText(mainTitle);
                                msg.setText(message);
                                JSONArray jsonArray=jsonObject.getJSONArray("replies");
                                for (int i=0;i<jsonArray.length(); i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    String title=jsonObject1.getString("title");
                                    String reply=jsonObject1.getString("reply");
                                    MsgsListPojo pojo=new MsgsListPojo();
                                    pojo.setTitle(title);
                                    pojo.setMsg(reply);
                                    msgsList.add(pojo);

                                    TMD_Adapter adapter=new TMD_Adapter(Teach_MsgDetails_Activity.this,msgsList);
                                    recyclerView.setAdapter(adapter);

                                }
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
