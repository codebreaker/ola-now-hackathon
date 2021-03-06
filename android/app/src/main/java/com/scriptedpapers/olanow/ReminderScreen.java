package com.scriptedpapers.olanow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.scriptedpapers.olanow.adapter.ReminderListAdapter;
import com.scriptedpapers.olanow.data.Reminder;
import com.scriptedpapers.olanow.database.DatabaseHelper;
import com.scriptedpapers.olanow.utils.SetReminder;
import com.scriptedpapers.olanow.utils.SetReminderDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ReminderScreen extends AppCompatActivity {

    @InjectView(R.id.reminderListView)
    ListView reminderListView;

    @InjectView(R.id.addReminderButton)
    TextView addReminderButton;

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

        updateView();



        addReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetReminderDialog dialog = new SetReminderDialog(ReminderScreen.this);
                dialog.show();
            }
        });

     }

    public void updateView() {

        List<Reminder> reminderslist = DatabaseHelper.getAllReminders();
        reminderListView.setAdapter(new ReminderListAdapter(ReminderScreen.this, reminderslist));
    }


}
