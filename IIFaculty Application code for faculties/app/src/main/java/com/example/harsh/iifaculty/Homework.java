package com.example.harsh.iifaculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class Homework extends AppCompatActivity
{
    String hdate=null,htime=null,des=null,std=null,fid=null;
    SharedPreferences ses = null;
    String url="https://primatial-alibi.000webhostapp.com/ii/insert_homework.php";
    JSONPARSER_MANIP jsonp=new JSONPARSER_MANIP();
    Spinner sp;
    EditText ed;
    int cnt=0;
    ProgressDialog pDialog=null;
    ArrayAdapter<CharSequence> adapter=null;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        getSupportActionBar().hide();
        sp=(Spinner)findViewById(R.id.hsp);
        btn=(Button)findViewById(R.id.button);
        ed=(EditText)findViewById(R.id.heddes);
        ses=getSharedPreferences(login.session_name,MODE_PRIVATE);
        fid = ses.getString("id", null);
        adapter=ArrayAdapter.createFromResource(Homework.this,R.array.standard_detail,R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        hdate=dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date time = new Date();
        htime=timeFormat.format(time);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                des = ed.getText().toString();
                std=sp.getSelectedItem().toString();

                if (des.equals(""))
                {
                    ed.setError("Please enter homework detail");
                }
                else {

                    new Select().execute();
                }
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
                        List<NameValuePair>params=new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("hdate",hdate));
                        params.add(new BasicNameValuePair("htime",htime));
                        params.add(new BasicNameValuePair("hdes",des));
                        params.add(new BasicNameValuePair("std",std));
                        params.add(new BasicNameValuePair("fid",fid));
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
                        // dismiss the dialog once done
                        pDialog.dismiss();

                        if(cnt==1)
                        {
                            ed.setText("");
                            Toast.makeText(Homework.this, "Homework is Submitted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Homework.this, "Homework is not Submitted", Toast.LENGTH_SHORT).show();
                        }

                    }
            }
        });
    }
}
