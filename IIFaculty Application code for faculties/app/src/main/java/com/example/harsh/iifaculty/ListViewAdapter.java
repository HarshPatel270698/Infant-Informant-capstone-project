package com.example.harsh.iifaculty;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
public class ListViewAdapter extends BaseAdapter
{
    String url="https://primatial-alibi.000webhostapp.com/ii/accept_leave.php";
    int cnt;
    JSONPARSER_MANIP jsonp=new JSONPARSER_MANIP();
    ProgressDialog pDialog=null;
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final TextView tlid,tgrno,tstart,tend,treason;
        Button btnaccept,btndec;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_leave, parent, false);
        resultp = data.get(position);
        tlid=(TextView) itemView.findViewById(R.id.lid);
        tgrno = (TextView) itemView.findViewById(R.id.Lgr);
        tstart = (TextView) itemView.findViewById(R.id.lstart);
        tend = (TextView) itemView.findViewById(R.id.lend);
        treason = (TextView) itemView.findViewById(R.id.lreason);
        btnaccept= (Button) itemView.findViewById(R.id.approve);
        btndec = (Button) itemView.findViewById(R.id.decline);
        tlid.setText(resultp.get(Leave.Tag_L_id));
        tgrno.setText(resultp.get(Leave.Tag_Gr));
        tstart.setText(resultp.get(Leave.Tag_Start));
        tend.setText(resultp.get(Leave.Tag_End));
        treason.setText(resultp.get(Leave.Tag_Reason));
        btnaccept.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new Select().execute();
            }
            class Select extends AsyncTask<String,String,String>
            {
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(context);
                    pDialog.setMessage("Please wait ....");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
                protected String doInBackground(String... arg0) {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    String lid = tlid.getText().toString();
                    params.add(new BasicNameValuePair("lid", lid));
                    JSONObject c = jsonp.makeHttpRequest(url, "GET", params);
                    try {
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
                    String grno = tgrno.getText().toString();
                    if(cnt==1)
                    {

                        Toast.makeText(context, "Leave is approved successfully for Gr Number="+grno, Toast.LENGTH_SHORT).show();

                        Intent in=new Intent(context,Menu.class);

                        context.startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(context, "Leave is not approved for Gr Number="+grno, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btndec.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String lid=tlid.getText().toString();
                //Toast.makeText(context, lid+"", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(context,leave_reason.class);
                in.putExtra("lid",lid);
                context.startActivity(in);
            }
        });
        return itemView;
    }
}
