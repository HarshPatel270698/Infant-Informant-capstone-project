package com.example.harsh.infantinformant;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
public class Leave extends AppCompatActivity
{
    Json_manipulate jsonp=new Json_manipulate();
    Json_manipulate jsonp1=new Json_manipulate();
    SharedPreferences ses = null;
    Button btn,b2,btnsave;
    EditText ed;
    ListView lv;
    int cnt;
    DatePickerDialog datePickerDialog;
    String lsshow="Select From Date";
    String leaveendshow="Select Till The Date";
    String url = "https://primatial-alibi.000webhostapp.com/ii/insert_leave.php";
    String urlwatch="https://primatial-alibi.000webhostapp.com/ii/leave.php";
    String Tag_array = "leave_detail";
    String Tag_L_Start = "L_start";
    String Tag_L_End = "L_end";
    String Tag_accepted_declined= "L_Accepted_Or_Declined";
    String Tag_Reason= "Reason";
    String lend=null,lreason=null,sgr=null,lstart=null,leshow=null,notavailable=null;
    String lviewstart=null,lviewend=null,lviewstatus=null,lviewreason=null,statusline=null;
    ProgressDialog pDialog=null;
    JSONArray array=null;
    ArrayList<HashMap<String, String>> leavelist=null;
    Date cdate=null;
    Date fdate=null;
    long diff=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        getSupportActionBar().hide();
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        sgr = ses.getString("dg", null);
        btn = (Button) findViewById(R.id.dpk);
        b2 = (Button) findViewById(R.id.dpke);
        btnsave = (Button) findViewById(R.id.button);
        ed = (EditText) findViewById(R.id.editText);
        lv = (ListView) findViewById(R.id.lv);
        btn.setText(lsshow);
        b2.setText(leaveendshow);
        new Status().execute();
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Leave.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                lsshow = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                                lstart= year+"-"+(monthOfYear+1)+"-"+dayOfMonth;

                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                cdate = new Date();
                                try {
                                    fdate = new SimpleDateFormat("yyyy-MM-dd").parse(lstart);
                                    diff = (cdate.getTime()) -(fdate.getTime());
                                }
                                catch (Exception e)
                                {

                                }

                                Toast.makeText(Leave.this, lsshow, Toast.LENGTH_SHORT).show();
                                btn.setText("From :- " + lsshow);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn.getText().toString().equals("Select From Date")) {
                    Toast.makeText(Leave.this, "Please select leave start date", Toast.LENGTH_SHORT).show();
                } else {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = new DatePickerDialog(Leave.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth)
                                {
                                    leshow = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                    lend= year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                                    Toast.makeText(Leave.this, leshow, Toast.LENGTH_SHORT).show();
                                    b2.setText("To :- " + leshow);
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() -diff);
                    datePickerDialog.show();
                }
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                lreason = ed.getText().toString();
                if(btn.getText().toString().equals("Select From Date")||b2.getText().toString().equals("Select Till The Date")||lreason.equals(""))
                {
                    Toast.makeText(Leave.this,"Please enter all details",Toast.LENGTH_SHORT).show();
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
            pDialog = new ProgressDialog(Leave.this);
            pDialog.setMessage("Please wait ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... arg0)
        {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("sgr",sgr));
            params.add(new BasicNameValuePair("lreason",lreason));
            params.add(new BasicNameValuePair("lstart",lstart));
            params.add(new BasicNameValuePair("lend",lend));
            JSONObject c=jsonp.makeHttpRequest(url,"POST",params);
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
                btn.setText("Select From Date");
                b2.setText("Select Till The Date");
                Toast.makeText(Leave.this, "Leave is Submitted", Toast.LENGTH_LONG).show();
                new Leave.Status().execute();
            }
            else
            {
                Toast.makeText(Leave.this, "Leave is not Submitted", Toast.LENGTH_LONG).show();
            }
        }

    }
    class  Status extends AsyncTask<String,String,String>
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
            leavelist=  new ArrayList<HashMap<String, String>>();
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
                    lviewstart=c.getString(Tag_L_Start);
                    SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdfSource.parse(lviewstart);
                    SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                    String lviewstart = sdfDestination.format(date);
                    lviewend=c.getString(Tag_L_End);
                    SimpleDateFormat sdfSource1 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = sdfSource.parse(lviewend);
                    SimpleDateFormat sdfDestination1 = new SimpleDateFormat("dd-MM-yyyy");
                    String lviewend = sdfDestination.format(date1);
                    lviewstatus=c.getString(Tag_accepted_declined);
                    lviewreason=c.getString(Tag_Reason);
                    HashMap<String,String>map=new HashMap<String,String>();
                    map.put(Tag_L_Start,lviewstart);
                    map.put(Tag_L_End,lviewend);
                    if(lviewstatus.equals("0"))
                    {
                        statusline = "Pending";
                    }
                    if(lviewstatus.equals("-1"))
                    {
                        statusline="Declined";
                    }
                    if(lviewstatus.equals("1"))
                    {
                        statusline="Approved";
                    }
                    map.put(Tag_accepted_declined,statusline);
                    if(lviewreason.equals("null"))
                    {
                        notavailable="";
                        map.put(Tag_Reason,notavailable);
                    }
                    else
                        {
                        map.put(Tag_Reason, "Reason                   :" + lviewreason);
                    }
                    leavelist.add(map);
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
            Toast.makeText(Leave.this, "You don't have any update", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ListAdapter adapter = new SimpleAdapter(Leave.this,leavelist,
                    R.layout.row_leave,
                    new String[] {Tag_L_Start,Tag_L_End,Tag_accepted_declined,Tag_Reason}, new int[] {
                    R.id.lstart,R.id.lend,R.id.lstatus,R.id.tv3});
            Log.e("data is-",adapter.toString());
            lv.setAdapter(adapter);
        }
    }
}
}
