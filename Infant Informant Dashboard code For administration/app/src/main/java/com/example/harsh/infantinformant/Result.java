package com.example.harsh.infantinformant;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Result extends AppCompatActivity {
    String gr=null,examname=null,mathsmark=null,mathsgrade=null,gujaratimark=null,gujaratigrade=null,totalmarks=null,totalgrade=null,rank=null;
    SharedPreferences ses=null;
    String Tag_array="result_data";
    ListView lv=null;
    String Tag_Exam_Name="Exam";
    String Tag_Maths_Mark="Maths";
    String Tag_Maths_Grade="Maths_Grade";
    String Tag_Gujarati_Mark="Gujarati";
    String Tag_Gujarati_Grade="Gujarati_Grade";
    String Tag_Total_Mark="Total";
    String Tag_Total_Grade="Total_Grade";
    String Tag_Rank="Rank";
    String url="https://primatial-alibi.000webhostapp.com/ii/result.php";
    Json_manipulate jsonp=new Json_manipulate();
    JSONArray array=null;
    int cnt=0;
    ProgressDialog pDialog=null;
    ArrayList<HashMap<String, String>> resultlist=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        lv=(ListView)findViewById(R.id.rlv);
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        gr = ses.getString("dg", null);
        new Result.Select().execute();
    }
    class Select extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Result.this);
            pDialog.setMessage("Please wait ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... arg0)
        {
            try
            {
                resultlist=  new ArrayList<HashMap<String, String>>();
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("gr",gr));
                JSONObject obj=jsonp.makeHttpRequest(url,"GET",params);
                int ans=obj.getInt("success");
                if(ans==1)
                {
                    cnt=1;
                    array=obj.getJSONArray(Tag_array);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject c=array.getJSONObject(i);
                        examname=c.getString(Tag_Exam_Name);
                        mathsmark=c.getString(Tag_Maths_Mark);
                        mathsgrade=c.getString(Tag_Maths_Grade);
                        gujaratimark=c.getString(Tag_Gujarati_Mark);
                        gujaratigrade=c.getString(Tag_Gujarati_Grade);
                        totalmarks=c.getString(Tag_Total_Mark);
                        totalgrade=c.getString(Tag_Total_Grade);
                        rank=c.getString(Tag_Rank);
                        HashMap<String,String>map=new HashMap<String,String>();
                        map.put(Tag_Exam_Name,examname);
                        map.put(Tag_Maths_Mark,mathsmark);
                        map.put(Tag_Maths_Grade,mathsgrade);
                        map.put(Tag_Gujarati_Mark,gujaratimark);
                        map.put(Tag_Gujarati_Grade,gujaratigrade);
                        map.put(Tag_Total_Mark,totalmarks);
                        map.put(Tag_Total_Grade,totalgrade);
                        map.put(Tag_Rank,rank);
                        resultlist.add(map);
                    }
                }
                else
                {
                    cnt=0;
                }
            }
            catch (Exception e)
            {

            }
            return null;
        }
        protected void onPostExecute(String file_url)
        {
            // dismiss the dialog once done
            pDialog.dismiss();
            if(cnt==0)
            {
                Toast.makeText(Result.this, "You don't have any result", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ListAdapter adapter = new SimpleAdapter(Result.this,resultlist,
                        R.layout.row_result,
                        new String[] {Tag_Exam_Name,Tag_Maths_Mark,Tag_Maths_Grade,Tag_Gujarati_Mark,Tag_Gujarati_Grade,Tag_Total_Mark,Tag_Total_Grade,Tag_Rank}, new int[] {
                        R.id.examname,R.id.mathsmark,R.id.mathsgrade,R.id.gujaratimark,R.id.gujaratigrade,R.id.totalmark,R.id.totalgrade,R.id.rank});
                lv.setAdapter(adapter);
            }
        }
    }
}
