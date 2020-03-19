package com.example.harsh.iifaculty;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class leave_reason extends AppCompatActivity {
    String url="https://primatial-alibi.000webhostapp.com/ii/decline_leave.php";
    String reason=null,lid=null;
    JSONPARSER_MANIP jsonp=new JSONPARSER_MANIP();
    int cnt=0;
    ProgressDialog pDialog=null;
    EditText ed;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_reason);
        getSupportActionBar().hide();
        Intent in = getIntent();
        lid = in.getStringExtra("lid");
        btn=(Button)findViewById(R.id.submit);
        ed=(EditText)findViewById(R.id.EditText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reason = ed.getText().toString();
                if (reason.equals(""))
                {
                    ed.setError("Please reason to decline leave");
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
                    pDialog = new ProgressDialog(leave_reason.this);
                    pDialog.setMessage("Please wait ....");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
                protected String doInBackground(String... arg0)
                {
                    List<NameValuePair> params=new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("lid",lid));
                    params.add(new BasicNameValuePair("reason",reason));
                    JSONObject c=jsonp.makeHttpRequest(url,"GET",params);
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
                        Toast.makeText(leave_reason.this, "Leave is Declined Successfully", Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(leave_reason.this,Menu.class);
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(leave_reason.this, "Leave is not declined successfully retry!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
