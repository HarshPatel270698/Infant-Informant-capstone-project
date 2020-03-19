package com.example.harsh.iifaculty;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Leave extends AppCompatActivity
{
    String url="https://primatial-alibi.000webhostapp.com/ii/pending_leave_for_faculty.php";
    public static String Tag_array="leave_detail";
    public static String Tag_L_id="L_Id";
    public static String Tag_Gr="S_GR_Number";
    public static String Tag_Start="L_start";
    public static String Tag_End="L_end";
    public static String Tag_Reason="L_Reason";
    String start=null,gr=null,end=null,reason=null,lid=null;
    int cnt=0;
    ListView lv=null;
   public static Button accept,decline;
    JsonParser jsonp=new JsonParser();
    JSONArray array=null;
    ProgressDialog pDialog=null;
    ListViewAdapter adapter;
    ArrayList<HashMap<String, String>> arraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        getSupportActionBar().hide();
        lv=(ListView)findViewById(R.id.lv);

        new Leave.Select().execute();
    }

    class Select extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Leave.this);
            pDialog.setMessage("Please wait ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... arg0)
        {
            try
            {
                JSONObject obj=jsonp.getJSONFromUrl(url);
                arraylist = new ArrayList<HashMap<String, String>>();
                int ans=obj.getInt("success");
                if(ans==1)
                {
                    cnt=1;
                    array=obj.getJSONArray(Tag_array);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject c=array.getJSONObject(i);
                        gr=c.getString(Tag_Gr);
                        lid=c.getString(Tag_L_id);
                        start=c.getString(Tag_Start);
                        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdfSource.parse(start);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        String start = sdfDestination.format(date);
                        end=c.getString(Tag_End);
                        SimpleDateFormat sdfSource1 = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = sdfSource1.parse(end);
                        SimpleDateFormat sdfDestination1 = new SimpleDateFormat("dd-MM-yyyy");
                        String end = sdfDestination1.format(date1);
                        reason=c.getString(Tag_Reason);
                        HashMap<String,String>map=new HashMap<String,String>();
                        map.put(Tag_L_id,lid);
                        map.put(Tag_Gr,gr);
                        map.put(Tag_Start,start);
                        map.put(Tag_End,end);
                        map.put(Tag_Reason,reason);
                        arraylist.add(map);
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
                Toast.makeText(Leave.this, "No Leaves Request found.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                adapter = new ListViewAdapter(Leave.this, arraylist);
                lv.setAdapter(adapter);
            }
        }
    }
}


