package com.example.harsh.infantinformant;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class event_detail extends AppCompatActivity
{
    String title=null,detail=null,image1=null,image2=null,image3=null,eid=null;
    String url="https://primatial-alibi.000webhostapp.com/ii/event.php";
    String Tag_array="event";
    String Tag_Eid="E_Id";
    String Tag_Title="E_Title";
    String Tag_Details="E_Details";
    TextView tv,ed;
    String Tag_Image1="Image1";
    String Tag_Image2="Image2";
    String Tag_Image3="Image3";
    ImageView iv1,iv2,iv3;
    Bundle b;
    JSONArray array = null;
    JsonParser jsonp = new JsonParser();
    ProgressDialog pDialog = null;
    ArrayList<HashMap<String, String>> eventlist = null;
    ImageLoader imageLoader = new ImageLoader(this);
    //-------------------------
    private static final int REQUEST_WRITE_STORAGE_REQUEST_CODE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        getSupportActionBar().hide();
        //-----------------
        requestAppPermissions();
        //-------------------
        tv=(TextView)findViewById(R.id.textView);
        ed=(TextView)findViewById(R.id.ed);
        iv1=(ImageView)findViewById(R.id.iv1);
        iv2=(ImageView)findViewById(R.id.iv2);
        iv3=(ImageView)findViewById(R.id.iv3);
        b=getIntent().getExtras();
        eid=b.getString("eid");
        new event_detail.Select().execute();
    }
    //-------------------------------------------
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

    //--------------------


    class Select extends AsyncTask<String, String, String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(event_detail.this);
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
            JSONObject obj = jsonp.getJSONFromUrl(url);
            array = obj.getJSONArray(Tag_array);
            for(int i=0;i<array.length();i++)
            {
                JSONObject c=array.getJSONObject(i);
                if(eid.equals(c.getString(Tag_Eid)))
                {
                    title=c.getString(Tag_Title);
                    detail=c.getString(Tag_Details);
                    image1=c.getString(Tag_Image1);
                    image2=c.getString(Tag_Image2);
                    image3=c.getString(Tag_Image3);
                }
            }

        }
        catch (Exception e) {
            Log.e("Ex is:", e + "");
        }
        return null;
    }
        protected void onPostExecute(String file_url)
        {
            // dismiss the dialog once done
            pDialog.dismiss();
            tv.setText(title);
            ed.setText(detail);
            imageLoader.DisplayImage("https://primatial-alibi.000webhostapp.com/ii/event_folder/"+image1, iv1);
            imageLoader.DisplayImage("https://primatial-alibi.000webhostapp.com/ii/event_folder/"+image2, iv2);
            imageLoader.DisplayImage("https://primatial-alibi.000webhostapp.com/ii/event_folder/"+image3, iv3);
        }
    }
}
