package com.example.harsh.infantinformant;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.squareup.timessquare.CalendarPickerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Attendance extends AppCompatActivity
{
    int cnt=0;
    SharedPreferences ses=null;
    String grno=null;
    String aid=null,date=null,time=null,std=null,present=null;
    String url="https://primatial-alibi.000webhostapp.com/ii/attendance.php";
    String Tag_array="attendance";
    String Tag_aid="A_Id";
    String Tag_Date="Date";
    String Tag_Time="Time";
    String Tag_std="Standard";
    String Tag_present="Present";
    JsonParser jsonp=new JsonParser();
    JSONArray array=null;
    ProgressDialog pDialog=null;
    StringBuffer pdate=null;
    String final_array[]=null;
    private CalendarPickerView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        getSupportActionBar().hide();
        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.YEAR, 2019); // set the year
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);


        Calendar c1 = Calendar.getInstance();   // this takes current date
        c1.add(Calendar.DAY_OF_MONTH,1);

        calendar.init(c.getTime(),c1.getTime());
        ses=getSharedPreferences(login.session_name,MODE_PRIVATE);
        grno=ses.getString("dg",null);
        pdate=new StringBuffer();
        new Attendance.Select().execute();
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
                JSONObject obj=jsonp.getJSONFromUrl(url);

                int ans=obj.getInt("success");
                if(ans==1)
                {
                    cnt=1;
                    array=obj.getJSONArray(Tag_array);

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject c=array.getJSONObject(i);
                        aid=c.getString(Tag_aid);
                        date=c.getString(Tag_Date);
                        time=c.getString(Tag_Time);
                        std=c.getString(Tag_std);
                        present=c.getString(Tag_present);
                        if(present.contains(grno))
                        {
                            pdate.append(date+",");
                        }

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
            String finalmonth=null;
            pDialog.dismiss();
            Log.e("Date is:-",pdate+"");


            Calendar now = Calendar.getInstance();
            int month=now.get(Calendar.MONTH)+1;



            Log.e("Month is:-",month+"");
            if(month<10)
            {
                finalmonth="0"+month;
            }
            Log.e("final month",finalmonth);
            /*String temp=pdate+"";
            pdate=new StringBuffer();
           String temp_array[]=temp.split(",");

           for(int i=0;i<temp_array.length;i++)
           {
               if (temp_array[i].contains(finalmonth))
               {
                   pdate.append(temp_array[i]+",");
               }
               else
               {
                   Log.e("Not match","Not");
               }
           }*/
            String temp=pdate+"";
             final_array=temp.split(",");
            calendar.highlightDates(getHolidays());



        }
    }
    private ArrayList<Date> getHolidays()
    {
        Date date[]=new Date[final_array.length];
        ArrayList<Date> holidays = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        try
        {
            for(int i=0;i<final_array.length;i++)
            {
                date[i]=sdf.parse(final_array[i]);
                Log.e("date",date[i]+"");
                holidays.add(date[i]);

            }
        }
        catch (Exception e)
        {
            Log.e("---Ex is:-",e+"");
        }
        return holidays;
    }
}
