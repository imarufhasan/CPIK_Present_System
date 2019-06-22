package com.bayazid.cpik_present_system.Std_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bayazid.cpik_present_system.Date_set;
import com.bayazid.cpik_present_system.R;

import java.util.ArrayList;

public class StdDoc_CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Std_Data_set> Std_Data_set ;

    public StdDoc_CustomAdapter(Context c, ArrayList<Std_Data_set> Std_Data_set) {
        this.c = c;
        this.Std_Data_set = Std_Data_set;
    }

    @Override
    public int getCount() {
        return Std_Data_set.size();
    }

    @Override
    public Object getItem(int position) {
        return Std_Data_set.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView STD_Name= (TextView) convertView.findViewById(R.id.std_full_name);
        TextView STD_Roll= (TextView) convertView.findViewById(R.id.std_roll);
        TextView STD_Reg_Num= (TextView) convertView.findViewById(R.id.std_reg_number);

        final Std_Data_set s= (Std_Data_set) this.getItem(position);

        STD_Name.setText(s.getFirst_Name());
        STD_Roll.setText(s.getCollege_Roll());
        STD_Reg_Num.setText(s.getReg_Number());

        //ONITECLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,s.getFirst_Name(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
