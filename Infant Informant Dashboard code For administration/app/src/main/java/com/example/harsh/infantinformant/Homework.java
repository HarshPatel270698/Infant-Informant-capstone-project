package com.example.harsh.infantinformant;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Homework extends AppCompatActivity
{

    String gr=null,std=null,hdate=null,hdes=null;
    SharedPreferences ses=null;
    String Tag_array="homework";
    ListView lv=null;
    String Tag_H_Date="H_Date";
    String Tag_H_Description="H_Description";
    String url="https://primatial-alibi.000webhostapp.com/ii/homework.php";
    Json_manipulate jsonp=new Json_manipulate();
    JSONArray  array=null;
    int cnt=0;
    ProgressDialog pDialog=null;
    ArrayList<HashMap<String, String>> hlist=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        lv=(ListView)findViewById(R.id.hlv);
        getSupportActionBar().hide();
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        gr = ses.getString("dg", null);
        std=ses.getString("std",null);
        new Select().execute();
    }
    class Select extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Homework.this);
            pDialog.setMessage("Please wait ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... arg0)
        {
            try
            {
                hlist=  new ArrayList<HashMap<String, String>>();
                List<NameValuePair>params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("std",std));
                JSONObject obj=jsonp.makeHttpRequest(url,"GET",params);
                int ans=obj.getInt("success");
                if(ans==1)
                {
                    cnt=1;
                    array=obj.getJSONArray(Tag_array);
                    for(int i=0;i<array.length();i++)
                    {
                            JSONObject c=array.getJSONObject(i);
                            hdate=c.getString(Tag_H_Date);
                            hdes=c.getString(Tag_H_Description);
                            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdfSource.parse(hdate);
                            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                            String strDate = sdfDestination.format(date);
                            Log.e("Date is:",strDate);
                            HashMap<String,String>map=new HashMap<String,String>();
                            map.put(Tag_H_Date,strDate);
                            map.put(Tag_H_Description,hdes);
                            hlist.add(map);
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
            // dismiss the dialog once done
            pDialog.dismiss();
            if(cnt==0)
            {
                Toast.makeText(Homework.this, "No Home for Today", Toast.LENGTH_SHORT).show();
            }
            else
            {
                    ListAdapter adapter = new SimpleAdapter(Homework.this,hlist,
                        R.layout.row_home_work,
                        new String[] {Tag_H_Date,Tag_H_Description}, new int[] {
                        R.id.rhda,R.id.rhdetail});
                Log.e("data is-",adapter.toString());
                lv.setAdapter(adapter);
            }
        }
    }
}
