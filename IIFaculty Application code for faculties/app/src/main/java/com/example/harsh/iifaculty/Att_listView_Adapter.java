package com.example.harsh.iifaculty;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Att_listView_Adapter extends BaseAdapter
{


    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;

    HashMap<String, String> resultp = new HashMap<String, String>();

    public Att_listView_Adapter(Context context,
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
// Declare Variables
        final TextView tfname,tlname,tmname,tgrno;
        final CheckBox cb;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.row_attendance, parent, false);
        resultp = data.get(position);


        tgrno = (TextView) itemView.findViewById(R.id.sgr);
        tfname = (TextView) itemView.findViewById(R.id.fname);
        tmname = (TextView) itemView.findViewById(R.id.mname);
        tlname = (TextView) itemView.findViewById(R.id.lname);

        cb=(CheckBox)itemView.findViewById(R.id.checkbox);

// Capture position and set results to the TextViews
        tgrno.setText(resultp.get(Attendance.Tag_Gr));
        tfname.setText(resultp.get(Attendance.Tag_F_Name));
        tmname.setText(resultp.get(Attendance.Tag_M_Name));
        tlname.setText(resultp.get(Attendance.Tag_L_Name));
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(cb.isChecked())
                {
                    Attendance.final_data.add(tgrno.getText().toString());
                }
                else
                {
                    Attendance.final_data.remove(tgrno.getText().toString());
                }
            }
        });


      /*  itemView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {

                if(cb.isChecked())
                {
                    Toast.makeText(context, tgrno.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });*/
        return itemView;
    }
}

