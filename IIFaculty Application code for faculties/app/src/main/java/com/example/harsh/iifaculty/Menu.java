package com.example.harsh.iifaculty;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    GridView gv;
    DatabaseHandler db;
    SharedPreferences ses=null;
    AlertDialog alertDialog;
    String menudata[]=new String[]{"MY PROFILE","HOMEWORK","ATTENDANCE","   LEAVES","    QUERY","   LOGOUT"};
    private static final int REQUEST_WRITE_STORAGE_REQUEST_CODE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
        db=new DatabaseHandler(Menu.this);
        if(ses.getString("id",null)==null)
        {
            Toast.makeText(this, "Your Session is Terminated", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Menu.this,login.class);
            startActivity(i);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        requestAppPermissions();
        gv=(GridView)findViewById(R.id.gv1);
        gv.setAdapter(new ImageAd(this,menudata));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String s=((TextView)view.findViewById(R.id.tv1)).getText().toString();
                if(s.equalsIgnoreCase("MY PROFILE"))
                {
                    Intent in= new Intent(Menu.this,Profile.class);
                    startActivity(in);
                }
                if(s.equalsIgnoreCase("HOMEWORK"))
                {
                    Intent in= new Intent(Menu.this,Homework.class);
                    startActivity(in);
                }
                if(s.equalsIgnoreCase("ATTENDANCE"))
                {
                    Intent in= new Intent(Menu.this,Attendance.class);
                    startActivity(in);
                }
                if(s.equalsIgnoreCase("   LEAVES"))
                {
                    Intent in= new Intent(Menu.this,Leave.class);
                    startActivity(in);
                }
                if(s.equalsIgnoreCase("    QUERY"))
                {
                    Intent in= new Intent(Menu.this,Query.class);
                    startActivity(in);
                }
                if(s.equalsIgnoreCase("   LOGOUT"))
                {
                    new AlertDialog.Builder(Menu.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Exit App")
                            .setMessage("Are you sure you want to Log Out?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    ses = getSharedPreferences(login.session_name, MODE_PRIVATE);
                                    SharedPreferences.Editor obj = ses.edit();
                                    obj.clear();
                                    obj.commit();
                                    Toast.makeText(Menu.this, "removed data session", Toast.LENGTH_LONG).show();
                                    db.deleteAll();
                                    finishAffinity();
                                    System.exit(0);
                                }

                            })

                            .show();

                }
            }
        });

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

    class ImageAd extends BaseAdapter {
        Context context;
        String detail[];

        ImageAd(Context context, String detail[]) {
            this.detail = detail;
            this.context = context;
        }


        @Override
        public int getCount() {
            return detail.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View grid;
            if (view == null) {
                grid = new View(context);
                grid = inflater.inflate(R.layout.row, null);
                ImageView iv = (ImageView) grid.findViewById(R.id.iv1);
                TextView tv = (TextView) grid.findViewById(R.id.tv1);
                tv.setText(detail[i]);
                String str = detail[i];
                if (str.equalsIgnoreCase("MY PROFILE")) {
                    iv.setImageResource(R.drawable.usericon);
                }
                if (str.equalsIgnoreCase("HOMEWORK")) {
                    iv.setImageResource(R.drawable.homeworkicon);
                }
                if (str.equalsIgnoreCase("ATTENDANCE")) {
                    iv.setImageResource(R.drawable.attendanceicon);
                }
                if (str.equalsIgnoreCase("   LEAVES")) {
                    iv.setImageResource(R.drawable.leaveicon);
                }
                if (str.equalsIgnoreCase("    QUERY")) {
                    iv.setImageResource(R.drawable.queryicon);
                }
                if (str.equalsIgnoreCase("   LOGOUT")) {
                    iv.setImageResource(R.drawable.logouticon);
                }
            } else {
                grid = (View) view;
            }
            return grid;
        }
    }
    public void onBackPressed() {
        new AlertDialog.Builder(Menu.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to close the II Faculty?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finishAffinity();
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
