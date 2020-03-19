package com.example.harsh.infantinformant;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Profile extends AppCompatActivity
{
    ImageView iv1;
    JsonParser jsonp = new JsonParser();
    TextView tgr,tname,tcontact,tdob,tblood,taddress,tstd;
    SharedPreferences ses = null;
    StringBuffer name=null;
    String gr=null,contact=null,dob=null,blood=null,address=null,std=null,dateofbirth=null,image=null;
    String url = "https://primatial-alibi.000webhostapp.com/ii/login.php";
    String Tag_array = "student";
    String Tag_grno = "S_GR_Number";
    String Tag_F_Name = "S_First_Name";
    String Tag_M_Name = "S_Middle_Name";
    String Tag_L_Name = "S_Last_Name";
    String Tag_Contact_No= "S_Contact_Number";
    String Tag_DOB= "S_DATE_Of_Birth";
    String Tag_Blood_Gtoup= "S_Blood_Group";
    String Tag_Address= "S_Address";
    String Tag_std="Standard";
    String Tag_image="Image";
    JSONArray array = null;
    ProgressDialog pDialog = null;
    ArrayList<HashMap<String, String>> studentlist = null;
    ImageLoader imageLoader = new ImageLoader(this);
    private static final int REQUEST_WRITE_STORAGE_REQUEST_CODE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        if(ses.getString("dg",null)==null)
        {
            Toast.makeText(this, "Your Session is Terminated", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Profile.this,login.class);
            startActivity(i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        requestAppPermissions();
        tgr=(TextView)findViewById(R.id.ptvgr);
        tname=(TextView)findViewById(R.id.ptvname);
        tcontact=(TextView)findViewById(R.id.ptvcontact);
        tblood=(TextView)findViewById(R.id.ptvblood);
        tdob=(TextView)findViewById(R.id.ptvdob);
        taddress=(TextView)findViewById(R.id.ptvadress);
        tstd=(TextView)findViewById(R.id.ptvstd);
        iv1=(ImageView)findViewById(R.id.piv1);
        gr = ses.getString("dg", null);
        new Select().execute();

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
                pDialog = new ProgressDialog(Profile.this);
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
                    name=new StringBuffer();
                    JSONObject obj = jsonp.getJSONFromUrl(url);
                    array = obj.getJSONArray(Tag_array);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject c=array.getJSONObject(i);
                        if(gr.equals(c.getString(Tag_grno)))
                        {
                                name.append(c.getString(Tag_F_Name)+" "+c.getString(Tag_M_Name)+" "+c.getString(Tag_L_Name));
                                contact=c.getString(Tag_Contact_No);
                                dob=c.getString(Tag_DOB);
                                SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = sdfSource.parse(dob);
                                SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                                dateofbirth = sdfDestination.format(date);
                                blood=c.getString(Tag_Blood_Gtoup);
                                address=c.getString(Tag_Address);
                                std=c.getString(Tag_std);
                                image=c.getString(Tag_image);
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
                imageLoader.DisplayImage("https://primatial-alibi.000webhostapp.com/ii/profile_pictures/"+image, iv1);
                tgr.setText(gr);
                tname.setText(name);
                tcontact.setText(contact);
                tdob.setText(dateofbirth);
                tblood.setText(blood);
                taddress.setText(address);
                tstd.setText(std);
            }
        }

}