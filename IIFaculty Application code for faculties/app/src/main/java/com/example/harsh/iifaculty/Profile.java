package com.example.harsh.iifaculty;

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
import java.util.ArrayList;
import java.util.HashMap;

public class Profile extends AppCompatActivity {
    ImageView iv1;
    JsonParser jsonp = new JsonParser();
    TextView tid,tname,tcontact,tdob,tmail,taddress;
    SharedPreferences ses = null;
    StringBuffer name=null;
    String id=null,contact=null,dob=null,mail=null,address=null,image=null;
    String url = "https://primatial-alibi.000webhostapp.com/ii/facultylogin.php";
    String Tag_array = "faculty";
    String Tag_idno = "F_Id";
    String Tag_F_Name = "F_First_Name";
    String Tag_M_Name = "F_Middle_Name";
    String Tag_L_Name = "F_Last_Name";
    String Tag_Contact_No= "F_Contact_Number";
    String Tag_DOB= "F_DATE_Of_Birth";
    String Tag_Mail= "F_Email";
    String Tag_Address= "F_Address";
    String Tag_Image="F_Image";
    JSONArray array = null;
    ProgressDialog pDialog = null;
    ArrayList<HashMap<String, String>> studentlist = null;
    ImageLoader imageLoader= new ImageLoader(this);
    private static final int REQUEST_WRITE_STORAGE_REQUEST_CODE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        if(ses.getString("id",null)==null)
        {
            Toast.makeText(this, "Your Session is Terminated", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Profile.this,login.class);
            startActivity(i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        requestAppPermissions();
        tid=(TextView)findViewById(R.id.ptvid);
        tname=(TextView)findViewById(R.id.ptvname);
        tcontact=(TextView)findViewById(R.id.ptvcontact);
        tmail=(TextView)findViewById(R.id.ptvmail);
        tdob=(TextView)findViewById(R.id.ptvdob);
        taddress=(TextView)findViewById(R.id.ptvadress);
        id = ses.getString("id", null);
        iv1=(ImageView)findViewById(R.id.piv1);
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
                    if(id.equals(c.getString(Tag_idno)))
                    {
                        name.append(c.getString(Tag_F_Name)+" "+c.getString(Tag_M_Name)+" "+c.getString(Tag_L_Name));
                        contact=c.getString(Tag_Contact_No);
                        dob=c.getString(Tag_DOB);
                        mail=c.getString(Tag_Mail);
                        address=c.getString(Tag_Address);
                        image=c.getString(Tag_Image);
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
            tid.setText(id);
            tname.setText(name);
            tcontact.setText(contact);
            tdob.setText(dob);
            tmail.setText(mail);
            taddress.setText(address);
        }
    }
}
