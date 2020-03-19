package com.example.harsh.infantinformant;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Timetable extends AppCompatActivity
{
    SharedPreferences ses=null;
    String sgr=null,image1=null;
    String url="https://primatial-alibi.000webhostapp.com/ii/view_time_table.php";
    String Tag_array="timetable";
    //String Tag_Month="Month";
    String Tag_IMAGE_name="IMAGE_name";
    ImageView iv1;
    JSONArray array = null;
    Json_manipulate jsonp=new Json_manipulate();
    ProgressDialog pDialog = null;
    ArrayList<HashMap<String, String>> Timetablelist = null;
    ImageLoader imageLoader = new ImageLoader(this);
    private static final int REQUEST_WRITE_STORAGE_REQUEST_CODE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        getSupportActionBar().hide();
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        sgr= ses.getString("dg", null);
        requestAppPermissions();
        iv1=(ImageView)findViewById(R.id.iv1);
        new Timetable.Select().execute();
    }
    private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, REQUEST_WRITE_STORAGE_REQUEST_CODE); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }



    class Select extends AsyncTask<String, String, String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Timetable.this);
            pDialog.setMessage("Please wait ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0)
        {
            try
            {
                ArrayList<NameValuePair>params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("sgr",sgr));
                JSONObject obj = jsonp.makeHttpRequest(url,"GET",params);
                array = obj.getJSONArray(Tag_array);
                for(int i=0;i<array.length();i++)
                {
                    JSONObject c=array.getJSONObject(i);

                        //String s=c.getString(Tag_Month);
                        image1=c.getString(Tag_IMAGE_name);
                        Log.e("Image Name:",image1);
                        //Log.e("Month",s);

                }

            }
            catch (Exception e) {
                Log.e("Ex is:", e + "");
            }
            return null;
        }
        protected void onPostExecute(String url)
        {
            pDialog.dismiss();
            imageLoader.DisplayImage("https://primatial-alibi.000webhostapp.com/ii/timetable_folder/"+image1, iv1);
        }
    }
}
