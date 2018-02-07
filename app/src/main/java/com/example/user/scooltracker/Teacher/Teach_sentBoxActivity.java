package com.example.user.scooltracker.Teacher;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.user.scooltracker.Adapters.TSB_adapter;
import com.example.user.scooltracker.R;
import com.example.user.scooltracker.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teach_sentBoxActivity extends AppCompatActivity {

    ExpandableListView expandable;
    TSB_adapter listAdapter;

    List<String> listDataHeader= new ArrayList<String>();
    List<String> listDataMsg= new ArrayList<String>();
    HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_sent_box);
        expandable=findViewById(R.id.TSB_expandable);


        LoadSentBox sentBox=new LoadSentBox();
        sentBox.execute();


    }
    private  class  LoadSentBox extends AsyncTask{

        String action="send_box";
        String user_id="1";
        @Override
        protected Object doInBackground(Object[] objects) {
            new RetrofitHelper(Teach_sentBoxActivity.this).getApi().getSentBox(action,user_id).enqueue(
                    new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response.body().toString());
                                String status=jsonObject.getString("status");
                                JSONArray jsonArray=jsonObject.getJSONArray("message");
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    String id=jsonObject1.getString("id");
                                    String title=jsonObject1.getString("title");
                                    String to=jsonObject1.getString("to");
                                    String message=jsonObject1.getString("message");

                                    listDataHeader.add(title);
                                    listDataMsg.add(message);

                                    listDataChild.put(String.valueOf(listDataHeader),listDataMsg);

                                    Log.e("loggg", "onResponse: "+listDataChild.toString() );

                                    listAdapter = new TSB_adapter(Teach_sentBoxActivity.this, listDataHeader, listDataChild);
                                    expandable.setAdapter(listAdapter);
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
}
