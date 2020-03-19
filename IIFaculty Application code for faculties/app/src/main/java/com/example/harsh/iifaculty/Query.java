package com.example.harsh.iifaculty;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Query extends AppCompatActivity {
    JSONPARSER_MANIP jsonp=new JSONPARSER_MANIP();
    JSONArray array=null;
    ProgressDialog pDialog=null;
    int cnt=0;
    String grno=null,question=null,std=null,id=null;
    public static String Tag_array="query";
    public static String Tag_id="Q_Id";
    public static String Tag_Gr="S_GR_Number";
    public static String Tag_question="Q_Question";
    Button btn;
    ListView lv;
    String url="https://primatial-alibi.000webhostapp.com/ii/student_view_by_standard_for_query.php";
    Spinner sp;
    ArrayAdapter<CharSequence> adapter1=null;
    ArrayList<HashMap<String, String>> querylist=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        getSupportActionBar().hide();
        sp=(Spinner)findViewById(R.id.std);
        adapter1= ArrayAdapter.createFromResource(Query.this,R.array.standard_detail,R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter1);
        btn=(Button)findViewById(R.id.btn);
        lv=(ListView)findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String id=((TextView)view.findViewById(R.id.id1)).getText().toString();
                Intent in=new Intent(Query.this,query_answer.class);
                in.putExtra("id",id);
                startActivity(in);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                std=sp.getSelectedItem().toString();
                new Select().execute();
            }
            class Select extends AsyncTask<String,String,String>
            {
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(Query.this);
                    pDialog.setMessage("Please wait ....");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
                protected String doInBackground(String... arg0)
                {
                    try
                    {
                        querylist=  new ArrayList<HashMap<String, String>>();
                        List<NameValuePair> params=new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("std",std));
                        JSONObject obj=jsonp.makeHttpRequest(url,"GET",params);
                        int ans=obj.getInt("success");
                        if(ans==1)
                        {
                            cnt=1;
                            array=obj.getJSONArray(Tag_array);

                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject c=array.getJSONObject(i);
                                grno=c.getString(Tag_Gr);
                                id=c.getString(Tag_id);
                                question=c.getString(Tag_question);
                                HashMap<String,String>map=new HashMap<String,String>();
                                map.put(Tag_Gr,grno);
                                map.put(Tag_id,id);
                                map.put(Tag_question,question);
                                querylist.add(map);
                            }
                        }
                        else
                        {
                            cnt=0;
                        }
                    }
                    catch (Exception e)
                    {

                    }
                    return null;
                }
                protected void onPostExecute(String file_url)
                {
                    pDialog.dismiss();
                    if(cnt==0)
                    {
                        Toast.makeText(Query.this, "No query found.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        ListAdapter adapter = new SimpleAdapter(Query.this,querylist,
                                R.layout.row_query,
                                new String[] {Tag_Gr,Tag_question,Tag_id}, new int[] {
                                R.id.sgr,R.id.question,R.id.id1});
                        Log.e("data is-",adapter.toString());
                        lv.setAdapter(adapter);
                    }
                }
            }
        });
    }
}
