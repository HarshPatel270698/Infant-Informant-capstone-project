package com.example.harsh.iifaculty;

//import android.app.Activity;
import android.app.ProgressDialog;
//import android.content.SharedPreferences;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Attendance extends AppCompatActivity
{
    String grno=null,fname=null,mname=null,lname=null,standard=null,systemdate=null,systemtime=null;
    //SharedPreferences ses = null;
    int cnt=0;
    public static String Tag_array="standard_data";
    public static String Tag_Gr="S_GR_Number";
    public static String Tag_F_Name="S_First_Name";
    public static String Tag_M_Name="S_Middle_Name";
    public static String Tag_L_Name="S_Last_Name";
    ProgressDialog pDialog=null;
    Button btnwatch,btnsubmit;
    ListView alv=null;
    String url="https://primatial-alibi.000webhostapp.com/ii/student_view_by_standard_for_attendance.php";
    String url1="https://primatial-alibi.000webhostapp.com/ii/insert_attendnce.php";
    JSONPARSER_MANIP jsonp=new JSONPARSER_MANIP();
    JSONArray array=null;
    Spinner sp;
    StringBuffer sb=new StringBuffer();
    public static ArrayList<String>final_data=new ArrayList<>();
    ArrayAdapter<CharSequence> adapter=null;
    ArrayList<HashMap<String, String>> attendancelist=null;
    Att_listView_Adapter ladapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        getSupportActionBar().hide();

        sp=(Spinner)findViewById(R.id.asp);
        adapter= ArrayAdapter.createFromResource(Attendance.this,R.array.standard_detail,R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        btnwatch=(Button)findViewById(R.id.btnwatch);
        btnsubmit=(Button)findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {



                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                systemdate=dateFormat.format(date);
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                Date time = new Date();
                systemtime=timeFormat.format(time);
                for(int i=0;i<final_data.size();i++)
                {
                    sb.append(final_data.get(i).toString()+",");
                    Log.e("---->",final_data.get(i).toString());
                }
               new Solo().execute();

            }
            class Solo extends AsyncTask<String,String,String>
            {
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(Attendance.this);
                    pDialog.setMessage("Please wait ....");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
                protected String doInBackground(String... arg0)
                {
                    List<NameValuePair>params=new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("date",systemdate));
                    params.add(new BasicNameValuePair("time",systemtime));
                    params.add(new BasicNameValuePair("standard",standard));
                    params.add(new BasicNameValuePair("present",sb+""));
                    JSONObject c=jsonp.makeHttpRequest(url1,"POST",params);
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
                    // dismiss the dialog once done
                    pDialog.dismiss();
                    Log.e("Result",cnt+"");
                    if (cnt == 1)
                    {
                        Toast.makeText(Attendance.this, "Attendance is Submitted", Toast.LENGTH_SHORT).show();
                        sb=new StringBuffer("");
                        Intent i=new Intent(Attendance.this,Menu.class);
                        startActivity(i);
                    }
                    else

                        {
                        Toast.makeText(Attendance.this, "Attendance is not Submitted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            });
        alv=(ListView)findViewById(R.id.alv);
        btnwatch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                standard=sp.getSelectedItem().toString();
                    new Select().execute();
            }
            class Select extends AsyncTask<String,String,String>
            {
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(Attendance.this);
                    pDialog.setMessage("Please wait ....");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
                protected String doInBackground(String... arg0)
                {
                    try
                    {
                        attendancelist=  new ArrayList<HashMap<String, String>>();
                        List<NameValuePair> params=new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("standard",standard));
                        JSONObject obj=jsonp.makeHttpRequest(url,"GET",params);
                        int ans=obj.getInt("success");
                        if(ans==1)
                        {
                            cnt=1;
                            array=obj.getJSONArray(Tag_array);

                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject c=array.getJSONObject(i);
                                grno=c.getString(Tag_Gr);
                                fname=c.getString(Tag_F_Name);
                                mname=c.getString(Tag_M_Name);
                                lname=c.getString(Tag_L_Name);
                                HashMap<String,String>map=new HashMap<String,String>();
                                map.put(Tag_Gr,grno);
                                map.put(Tag_F_Name,fname);
                                map.put(Tag_M_Name,mname);
                                map.put(Tag_L_Name,lname);
                                attendancelist.add(map);
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

                    ladapter=new Att_listView_Adapter(Attendance.this,attendancelist);
                            alv.setAdapter(ladapter);

                    }
                }
        });

    }


}
