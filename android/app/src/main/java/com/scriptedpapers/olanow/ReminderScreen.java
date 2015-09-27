package com.scriptedpapers.olanow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.scriptedpapers.olanow.adapter.ReminderListAdapter;
import com.scriptedpapers.olanow.data.Reminder;

import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ReminderScreen extends AppCompatActivity {

    @InjectView(R.id.reminderListView)
    ListView reminderListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_screen);

        ButterKnife.inject(this);

        ArrayList<Reminder> reminderslist = new ArrayList<Reminder>();

        Reminder reminder = new Reminder();

        reminder.setReminderName("Name");
        reminder.setReminderDate(new Date());

        reminderslist.add(reminder);

        reminderListView.setAdapter(new ReminderListAdapter(ReminderScreen.this, reminderslist));
     }


}
