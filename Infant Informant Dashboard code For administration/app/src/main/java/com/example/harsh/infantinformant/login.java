package com.example.harsh.infantinformant;
        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.nfc.Tag;
        import android.os.AsyncTask;
        import android.se.omapi.Session;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.iid.FirebaseInstanceId;
        import com.google.firebase.iid.InstanceIdResult;

        import org.json.JSONArray;
        import org.json.JSONObject;
        import java.util.ArrayList;
        import java.util.HashMap;
public class login extends AppCompatActivity {
    Button btnlogin;
    public static String session_name = "ii";
    JsonParser jsonp = new JsonParser();
    String ugrno = null, dgrno = null, upass = null, dpass = null, std = null;
    int flag = 0;
    SharedPreferences ses = null;
    EditText edgrno, edpass;
    String url = "https://primatial-alibi.000webhostapp.com/ii/login.php";
    String Tag_array = "student";
    String Tag_grno = "S_GR_Number";
    String Tag_password = "password";
    String TagStandard = "Standard";
    JSONArray array = null;
    ProgressDialog pDialog = null;
    DatabaseHandler db = null;
    ArrayList<HashMap<String, String>> studentlist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.login);
        ses = getSharedPreferences(session_name, MODE_PRIVATE);
        db = new DatabaseHandler(this);
        edgrno = (EditText) findViewById(R.id.ledgrno);
        edpass = (EditText) findViewById(R.id.ledpassword);
        btnlogin = (Button) findViewById(R.id.lbtnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ugrno = edgrno.getText().toString();
                upass = edpass.getText().toString();
                edpass.setText("");
                edgrno.setText("");
                if (ugrno.equals("")) {
                    edgrno.setError("Pls Enter GR. Number");
                } else if (upass.equals("")) {
                    edpass.setError("Pls Enter Your Password");
                } else {
                    new Select().execute();
                }
            }

            class Select extends AsyncTask<String, String, String> {
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(login.this);
                    pDialog.setMessage("Please wait ....");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }

                protected String doInBackground(String... arg0) {
                    try {
                        Log.e("Uname is:", ugrno);
                        JSONObject obj = jsonp.getJSONFromUrl(url);
                        Log.e("Response is", obj + "");
                        array = obj.getJSONArray(Tag_array);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject c = array.getJSONObject(i);
                            dgrno = c.getString(Tag_grno);
                            dpass = c.getString(Tag_password);
                            std = c.getString(TagStandard);
                            if ((dgrno.equals(ugrno)) && (dpass.equals(upass))) {
                                flag = 1;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        Log.e("Ex is:", e + "");
                    }
                    return null;
                }

                protected void onPostExecute(String file_url) {
                    // dismiss the dialog once done
                    pDialog.dismiss();
                    if (flag == 1) {
                        // code for session
                        SharedPreferences.Editor obj = ses.edit();
                        obj.putString("dg", dgrno);
                        obj.putString("std", std);
                        obj.commit();
                        Toast.makeText(login.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                        db.insertData(dgrno);
                        Intent i = new Intent(login.this, Menu.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(login.this, "Invalid User Name and Password", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }
        });
    }

    public void onBackPressed() {

        new AlertDialog.Builder(login.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to close the INFANT INFORMANT?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
