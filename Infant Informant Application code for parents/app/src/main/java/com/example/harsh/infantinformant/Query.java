package com.example.harsh.infantinformant;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Query extends AppCompatActivity {
    Json_manipulate jsonp=new Json_manipulate();
    Json_manipulate jsonp1=new Json_manipulate();
    SharedPreferences ses = null;
    Button btn;
    EditText ed;
    ListView lv;
    int cnt;
    String urlinsert = "https://primatial-alibi.000webhostapp.com/ii/insert_query.php";
    String urlwatch="https://primatial-alibi.000webhostapp.com/ii/query.php";
    String Tag_array = "query";
    String Tag_question = "Q_Question";
    String Tag_answer = "Q_Answer";
    String Tag_isactive= "isActive";
    String sgr=null,std=null,question=null,answer=null,status=null;
    ProgressDialog pDialog=null;
    JSONArray array=null;
    ArrayList<HashMap<String, String>> querylist=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        getSupportActionBar().hide();
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        sgr = ses.getString("dg", null);
        std = ses.getString("std",null);
        btn = (Button) findViewById(R.id.btn);
        ed = (EditText) findViewById(R.id.ed);
        lv = (ListView) findViewById(R.id.lv);
        new Status().execute();
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                question = ed.getText().toString();
                if(ed.equals(""))
                {
                    Toast.makeText(Query.this,"Please enter query",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new Select().execute();
                }
            }
        });
    }
    class Select extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Query.this);
            pDialog.setMessage("Please wait ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... arg0)
        {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("sgr",sgr));
            params.add(new BasicNameValuePair("std",std));
            params.add(new BasicNameValuePair("question",question));
            JSONObject c=jsonp.makeHttpRequest(urlinsert,"POST",params);
            try
            {
                int ans=c.getInt("success");
                if(ans==1)
                {
                    cnt=1;
                }
                else
                {
                    cnt=0;
                }
            }
            catch (Exception e)
            {
                Log.e("Ex is:",e+"");
            }
            return null;
        }
        protected void onPostExecute(String file_url)
        {

            pDialog.dismiss();
            if(cnt==1)
            {
                ed.setText("");
                Toast.makeText(Query.this, "Query is Submitted", Toast.LENGTH_LONG).show();
                new Query.Status().execute();
            }
            else
            {
                Toast.makeText(Query.this, "Query is not Submitted", Toast.LENGTH_LONG).show();
            }
        }
    }
    class  Status extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Query.this);
            pDialog.setMessage("Please wait ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... arg0)
        {
            try
            {
                querylist=  new ArrayList<HashMap<String, String>>();
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("sgr",sgr));
                JSONObject obj=jsonp1.makeHttpRequest(urlwatch,"GET",params);
                int ans=obj.getInt("success");
                Log.e("ans",ans+"");
                if(ans==1)
                {
                    cnt=1;
                    Log.e("---->","Reach Here..");
                    array=obj.getJSONArray(Tag_array);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject c=array.getJSONObject(i);
                        question=c.getString(Tag_question);
                        answer=c.getString(Tag_answer);
                        status=c.getString(Tag_isactive);
                        HashMap<String,String>map=new HashMap<String,String>();
                        map.put(Tag_question,question);
                        map.put(Tag_answer,answer);
                        map.put(Tag_isactive,status);
                        if(status.equals("0"))
                        {
                            answer = "Pending";
                        }
                        map.put(Tag_answer,answer);
                        /*if(lviewreason.equals("null"))
                        {
                            notavailable="";
                            map.put(Tag_Reason,notavailable);
                        }
                        else
                        {
                            map.put(Tag_Reason, "Reason                   :" + lviewreason);
                        }*/
                        querylist.add(map);
                    }
                }
                else
                {
                    cnt=0;
                }
            }
            catch (Exception e)
            {
                Log.e("Ex is:",e+"");
            }
            return null;
        }
        protected void onPostExecute(String file_url)
        {
            pDialog.dismiss();
            if(cnt==0)
            {
                Toast.makeText(Query.this, "You don't have any discussion", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ListAdapter adapter = new SimpleAdapter(Query.this,querylist,
                        R.layout.row_query,
                        new String[] {Tag_question,Tag_answer}, new int[] {
                        R.id.question,R.id.answer});
                Log.e("data is-",adapter.toString());
                lv.setAdapter(adapter);
            }
        }
    }
}
