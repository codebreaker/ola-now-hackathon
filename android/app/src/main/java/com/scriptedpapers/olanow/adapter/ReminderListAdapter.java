package com.scriptedpapers.olanow.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.scriptedpapers.olanow.data.Reminder;
import com.scriptedpapers.olanow.data.SuggestionItem;
import com.scriptedpapers.olanow.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mahes on 26/9/15.
 */
public class ReminderListAdapter extends ArrayAdapter<Reminder> {

    ArrayList<Reminder> reminderlistList;
    Context context;


    public ReminderListAdapter(Context context, ArrayList<Reminder> reminderlistList) {
        super(context, 0, reminderlistList);

        this.context = context;
        this.reminderlistList = reminderlistList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater mInflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = mInflater.inflate(R.layout.lyt_reminder_list_item, null);

        Reminder reminder = reminderlistList.get(position);

        ViewHolder holder = new ViewHolder(convertView);

        holder.reminderName.setText(reminder.getReminderName());

        SimpleDateFormat df = new SimpleDateFormat("yyyy MMMM, dd hh:mm a ");
        String formattedDate = df.format(reminder.getReminderDate());

        holder.reminderTime.setText(formattedDate);

        holder.deleteReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    static class ViewHolder {



        @InjectView(R.id.reminderName)
        TextView reminderName;
        @InjectView(R.id.reminderTime)
        TextView reminderTime;
        @InjectView(R.id.deleteReminder)
        TextView deleteReminder;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
