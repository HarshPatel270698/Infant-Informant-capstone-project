package com.example.harsh.infantinformant;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
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

public class fees extends AppCompatActivity {
    String gr=null,fdate=null,famount=null,foption=null,tno=null;
    SharedPreferences ses=null;
    String Tag_array="fees";
    ListView lv=null;
    String Tag_F_Date="fdate";
    String Tag_F_Amount="famount";
    String Tag_Fee_option="foption";
    String Tag_T_No="T_no";
    String url="https://primatial-alibi.000webhostapp.com/ii/fees.php";
    Json_manipulate jsonp=new Json_manipulate();
    JSONArray array=null;
    int cnt=0;
    ProgressDialog pDialog=null;
    ArrayList<HashMap<String, String>> flist=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);
        getSupportActionBar().hide();
        lv=(ListView)findViewById(R.id.flv);
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        gr = ses.getString("dg", null);
        new Select().execute();
    }
    class Select extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(fees.this);
            pDialog.setMessage("Please wait ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... arg0)
        {
            try
            {
                flist=  new ArrayList<HashMap<String, String>>();
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
                        fdate=c.getString(Tag_F_Date);
                        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdfSource.parse(fdate);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        String FDate = sdfDestination.format(date);
                        famount=c.getString(Tag_F_Amount);
                        foption=c.getString(Tag_Fee_option);
                        tno=c.getString(Tag_T_No);
                        HashMap<String,String>map=new HashMap<String,String>();
                        map.put(Tag_F_Date,FDate);
                        map.put(Tag_F_Amount,famount);
                        map.put(Tag_Fee_option,foption);
                        map.put(Tag_T_No,tno);
                        flist.add(map);
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
                Toast.makeText(fees.this, "You don't have any update", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ListAdapter adapter = new SimpleAdapter(fees.this,flist,
                        R.layout.row_fees,
                        new String[] {Tag_F_Date,Tag_F_Amount,Tag_Fee_option,Tag_T_No}, new int[] {
                        R.id.rfda,R.id.rfamount,R.id.rfoption,R.id.rftno});
                lv.setAdapter(adapter);
            }
        }
    }
}
