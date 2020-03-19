package com.example.harsh.iifaculty;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.se.omapi.Session;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class login extends AppCompatActivity {
    Button btnlogin;
    public static String session_name="ii";
    JsonParser jsonp = new JsonParser();
    String uidno=null,didno=null,upass=null,dpass=null;
    int flag=0,isactive;
    SharedPreferences ses=null;
    EditText eidno,eidpass;
    String url="https://primatial-alibi.000webhostapp.com/ii/facultylogin.php";
    String Tag_array="faculty";
    String Tag_idno="F_Id";
    String Tag_password="F_Password";
    String Tag_isactive="isActive";
    JSONArray array=null;
    ProgressDialog pDialog=null;
    DatabaseHandler db=null;
    ArrayList<HashMap<String, String>> facultylist=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        ses=getSharedPreferences(session_name,MODE_PRIVATE);
        db=new DatabaseHandler(this);
        eidno=(EditText)findViewById(R.id.idno);
        eidpass=(EditText)findViewById(R.id.password);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                uidno=eidno.getText().toString();
                upass=eidpass.getText().toString();
                eidpass.setText("");
                eidno.setText("");
                if(uidno.equals(""))
                {
                    eidno.setError("Pls Enter GR. Number");
                }
                else if(upass.equals(""))
                {
                    eidpass.setError("Pls Enter Your Password");
                }
                else
                {
                    new Select().execute();
                }
            }
            class Select extends AsyncTask<String,String,String>
            {
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(login.this);
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
                        Log.e("Uname is:",uidno);
                        JSONObject obj=jsonp.getJSONFromUrl(url);

                        Log.e("Response is",obj+"");
                        array=obj.getJSONArray(Tag_array);
                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject c=array.getJSONObject(i);
                            didno=c.getString(Tag_idno);
                            dpass=c.getString(Tag_password);
                            isactive=c.getInt(Tag_isactive);
                            if((didno.equals(uidno)) &&(dpass.equals(upass))&&(isactive==1))
                            {
                                flag=1;
                                break;
                            }
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
                    if(flag==1)
                    {
                        // code for session
                        SharedPreferences.Editor obj=ses.edit();
                        obj.putString("id",didno);
                        obj.commit();

                        Toast.makeText(login.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                        db.insertData(didno);
                        Intent i=new Intent(login.this,Menu.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(login.this, "Invalid User Name and Password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
