package com.scriptedpapers.olanow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.scriptedpapers.olanow.adapter.SuggestionListAdapter;
import com.scriptedpapers.olanow.data.Event;
import com.scriptedpapers.olanow.data.SuggestionItem;
import com.scriptedpapers.olanow.utils.CalendarUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.listView)
    ListView listView;

    SuggestionListAdapter suggestionListAdapter;

    ArrayList<SuggestionItem> suggestionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

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

        CalendarUtils.getCalendarEvent(MainActivity.this, true);


        suggestionListAdapter = new SuggestionListAdapter(MainActivity.this, suggestionList);
        listView.setAdapter(suggestionListAdapter);
    }


}
