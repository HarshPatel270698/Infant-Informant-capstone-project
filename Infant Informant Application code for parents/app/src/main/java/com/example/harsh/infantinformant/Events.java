package com.example.harsh.infantinformant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class Events extends AppCompatActivity {
    int cnt=0;
    String etitle=null,edate=null,etime=null,edid=null;
    ListView elv=null;
    public static String session_name="event";
    SharedPreferences ses=null;
    String url="https://primatial-alibi.000webhostapp.com/ii/event.php";
    String Tag_array="event";
    String Tag_Title="E_Title";
    String Tag_Date="E_Date";
    String Tag_Time="E_Time";
    String Tag_eid="E_Id";
    JsonParser jsonp=new JsonParser();
    JSONArray array=null;
    ProgressDialog pDialog=null;
    ArrayList<HashMap<String, String>> eventlist=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getSupportActionBar().hide();
        elv=(ListView)findViewById(R.id.elv);
        elv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String eid=((TextView)view.findViewById(R.id.reid)).getText().toString();
                Intent in=new Intent(Events.this,event_detail.class);
                in.putExtra("eid",eid);
                startActivity(in);
            }
        });
        new Events.Select().execute();
    }
    class Select extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Events.this);
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
                eventlist=  new ArrayList<HashMap<String, String>>();
                int ans=obj.getInt("success");
                if(ans==1)
                {
                    cnt=1;
                    array=obj.getJSONArray(Tag_array);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject c=array.getJSONObject(i);
                        etitle=c.getString(Tag_Title);
                        edate=c.getString(Tag_Date);
                        etime=c.getString(Tag_Time);
                        edid=c.getString(Tag_eid);
                        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdfSource.parse(edate);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        String strDate = sdfDestination.format(date);
                        Log.e("Date is:",strDate);
                        HashMap<String,String>map=new HashMap<String,String>();
                        map.put(Tag_Title,etitle);
                        map.put(Tag_Date,strDate);
                        map.put(Tag_Time,etime);
                        map.put(Tag_eid,edid);
                        eventlist.add(map);
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
                Toast.makeText(Events.this, "No Events found.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                        ListAdapter adapter = new SimpleAdapter(Events.this,eventlist,
                        R.layout.row_event,
                        new String[] {Tag_Title,Tag_Date,Tag_Time,Tag_eid}, new int[] {
                        R.id.rename,R.id.redate,R.id.retime,R.id.reid});
                         elv.setAdapter(adapter);


            }
        }
    }
}
