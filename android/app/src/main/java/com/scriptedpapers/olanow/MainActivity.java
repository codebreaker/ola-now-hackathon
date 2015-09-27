package com.scriptedpapers.olanow;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scriptedpapers.olanow.adapter.SuggestionListAdapter;
import com.scriptedpapers.olanow.data.Event;
import com.scriptedpapers.olanow.data.Reminder;
import com.scriptedpapers.olanow.data.SuggestionItem;
import com.scriptedpapers.olanow.database.DatabaseHelper;
import com.scriptedpapers.olanow.utils.CalendarUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.listView)
    ListView listView;

    SuggestionListAdapter suggestionListAdapter;

    ArrayList<SuggestionItem> suggestionList;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.left_drawer)
    ListView   mDrawerList;

    @InjectView(R.id.content_frame)
    FrameLayout contentFrame;

    ActionBarDrawerToggle mDrawerToggle;

    ImageView menuImageView;

    @InjectView(R.id.mainContentView)
    RelativeLayout mainContentView;


    public String[] screenTitles = { "Banner", "Settings", "Reminder"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);


        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.lyt_header_view, listView, false);
        menuImageView = (ImageView) header.findViewById(R.id.menuImageView);
        listView.addHeaderView(header, null, false);

        setMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();

        suggestionList = new ArrayList<SuggestionItem>();

        SuggestionItem todayTab = new SuggestionItem();
        todayTab.setToday();

        suggestionList.add(todayTab);

        ArrayList<Event> todayEventList =  CalendarUtils.getCalendarEvent(MainActivity.this, true);

        for(int i = 0; i< todayEventList.size(); i++) {

            SuggestionItem eventTab = new SuggestionItem();
            eventTab.setEvent(todayEventList.get(i));
            suggestionList.add(eventTab);

        }

        SuggestionItem messageTab = new SuggestionItem();
        messageTab.setIsMessageTrainTicket(true);

        suggestionList.add(messageTab);



        List<Reminder> reminderList = DatabaseHelper.getTodaysReminders(true);

        for(int i = 0; i < reminderList.size(); i++) {

            SuggestionItem reminderTab = new SuggestionItem();


            reminderTab.setReminder(reminderList.get(i));
            suggestionList.add(reminderTab);
        }



        SuggestionItem tomorrowTab = new SuggestionItem();
        tomorrowTab.setTomorrow();

        suggestionList.add(tomorrowTab);

        todayEventList =  CalendarUtils.getCalendarEvent(MainActivity.this, false);

        for(int i = 0; i< todayEventList.size(); i++) {

            SuggestionItem eventTab = new SuggestionItem();
            eventTab.setEvent(todayEventList.get(i));
            suggestionList.add(eventTab);

        }


       reminderList = DatabaseHelper.getTodaysReminders(false);

        for(int i = 0; i < reminderList.size(); i++) {

            SuggestionItem reminderTab = new SuggestionItem();


            reminderTab.setReminder(reminderList.get(i));
            suggestionList.add(reminderTab);
        }

        suggestionListAdapter = new SuggestionListAdapter(MainActivity.this, suggestionList);
        listView.setAdapter(suggestionListAdapter);
    }

    void setMenu() {



        mDrawerList.setAdapter(new NavigationDrawerAdapter(MainActivity.this));

        mDrawerList.bringToFront();
        mDrawerLayout.requestLayout();

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, 0, 0)
        {
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                float moveFactor = (mDrawerList.getWidth() * slideOffset);

                mainContentView.setTranslationX(moveFactor);


            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    class NavigationDrawerAdapter extends ArrayAdapter<String> {

        private Activity activityObject;

        public NavigationDrawerAdapter(Activity activityObject) {
            super(activityObject, R.layout.navigation_drawer_list_item, screenTitles);
            this.activityObject = activityObject;
        }

        class ViewHolder {
            TextView menuName;
            RelativeLayout imageLayout;
            LinearLayout buttonLayout;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = null;

            if (convertView == null) {
                LayoutInflater inflator = activityObject.getLayoutInflater();
                view = inflator.inflate(R.layout.navigation_drawer_list_item, null);
            } else
                view = convertView;

            final ViewHolder viewHolder = new ViewHolder();

            viewHolder.imageLayout = (RelativeLayout) view.findViewById(R.id.imageLayout);
            viewHolder.buttonLayout = (LinearLayout) view.findViewById(R.id.buttonLayout);

            viewHolder.menuName = (TextView) view.findViewById(R.id.menuName);

            viewHolder.menuName.setText(screenTitles[position]);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeDrawer();

                    if (position == 1)
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));

                    if (position == 2)
                        startActivity(new Intent(MainActivity.this, ReminderScreen.class));
                }
            });

            if(position == 0) {
                viewHolder.imageLayout.setVisibility(View.VISIBLE);
                viewHolder.buttonLayout.setVisibility(View.GONE);
            } else {
                viewHolder.imageLayout.setVisibility(View.GONE);
                viewHolder.buttonLayout.setVisibility(View.VISIBLE);
            }

            view.setTag(viewHolder);

            return view;
        }
    }

    public void openDrawer(){
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public void closeDrawer(){
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

}
