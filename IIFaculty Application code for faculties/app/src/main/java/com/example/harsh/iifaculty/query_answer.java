package com.example.harsh.iifaculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class query_answer extends AppCompatActivity {
    String url="https://primatial-alibi.000webhostapp.com/ii/query_response.php";
    String response=null,id=null;
    JSONPARSER_MANIP jsonp=new JSONPARSER_MANIP();
    int cnt=0;
    ProgressDialog pDialog=null;
    Button btn;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_answer);
        getSupportActionBar().hide();
        Intent in = getIntent();
        id = in.getStringExtra("id");
        btn=(Button)findViewById(R.id.btn);
        ed=(EditText)findViewById(R.id.EditText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response = ed.getText().toString();
                if (response.equals(""))
                {
                    ed.setError("Please enter response");
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
                    pDialog = new ProgressDialog(query_answer.this);
                    pDialog.setMessage("Please wait ....");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
                protected String doInBackground(String... arg0)
                {
                    List<NameValuePair> params=new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("id",id));
                    params.add(new BasicNameValuePair("response",response));
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
                        Toast.makeText(query_answer.this, "Response is submitted Successfully", Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(query_answer.this,Menu.class);
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(query_answer.this, "Response is not submitted Successfully retry!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
