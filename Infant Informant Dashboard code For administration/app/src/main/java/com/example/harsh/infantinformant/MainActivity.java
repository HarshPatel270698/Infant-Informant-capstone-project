package com.example.harsh.infantinformant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    long ms=0,s_time=500;
    DatabaseHandler db=null;
    ArrayList<HashMap<String, String>> studentlist=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
           getSupportActionBar().hide();
           db=new DatabaseHandler(this);
            setContentView(R.layout.activity_main);
            Thread th=new Thread()
            {
                public void run()
                {
                    try
                    {
                        while(ms<s_time)
                        {
                            ms=ms+100;
                            Thread.sleep(300);
                        }
                    }
                    catch (Exception e)
                    {

                    }
                    finally
                    {
                        Cursor c=db.getAllData();
                        int ans=c.getCount();
                        Log.e("No of Rows",ans+"");
                        if(ans==0)
                        {
                            Intent i=new Intent(MainActivity.this,login.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Intent i=new Intent(MainActivity.this,Menu.class);
                            startActivity(i);
                            finish();
                        }

                    }
                }
            };
            th.start();
    }
}
