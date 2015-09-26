package com.scriptedpapers.olanow.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.CalendarContract;

import com.scriptedpapers.olanow.data.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mahes on 26/9/15.
 */
public class CalendarUtils {

    public static final String[] CALENDAR_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
    };

    public static final String[] EVENT_PROJECTION = new String[] {
            BaseColumns._ID,                                        // 0
            CalendarContract.Events.TITLE,                          // 1
            CalendarContract.Events.DTSTART,                        // 2
            CalendarContract.Events.EVENT_LOCATION                  // 3
    };

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;


    // The indices for the projection array above.
    private static final int PROJECTION_EVENT_NAME_INDEX = 1;
    private static final int PROJECTION_EVENT_DATE_INDEX = 2;
    private static final int PROJECTION_EVENT_LOCATION_INDEX = 3;

    public static ArrayList<Event> getCalendarEvent(Context ctxt, boolean today) {

        ArrayList<Event> eventList = new ArrayList<>();

        String[] selectionArgs = {""};


        Cursor cur = null;
        ContentResolver cr = ctxt.getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = null;

        selectionArgs[0] = "";


        cur = cr.query(uri, CALENDAR_PROJECTION, null, null, null);

        while (cur.moveToNext()) {

            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);

           if(accountName.equals(displayName)) {




               selection = CalendarContract.Events.DTSTART + " >= ? AND " + CalendarContract.Events.DTSTART + "<= ? AND " + CalendarContract.Events.CALENDAR_ID + "= ?";

               if(today) {
                   selectionArgs = new String[]{Long.toString(getStartOfDay(new Date()).getTime()), Long.toString(getEndOfDay(new Date()).getTime()), Long.toString(calID)};
               } else {
                   selectionArgs = new String[] { Long.toString(getStartOfDay(getTomorrowDate()).getTime()), Long.toString(getEndOfDay(getTomorrowDate()).getTime()), Long.toString(calID) };
               }

               Cursor curEvent = ctxt.getContentResolver().query(CalendarContract.Events.CONTENT_URI, EVENT_PROJECTION, selection, selectionArgs, null);

               while (curEvent.moveToNext()) {

                   String eventName = curEvent.getString(PROJECTION_EVENT_NAME_INDEX);
                   String eventStartTime = curEvent.getString(PROJECTION_EVENT_DATE_INDEX);
                   String eventLocation = curEvent.getString(PROJECTION_EVENT_LOCATION_INDEX);

                   Event event = new Event();

                   event.setEventName(eventName);
                   event.setEventDate(new Date(Long.parseLong(eventStartTime)));
                   event.setEventLocation(eventLocation);
                   event.setRegisteredEmailId(accountName);

                   eventList.add(event);
               }

           }

        }

        return eventList;
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getTomorrowDate() {

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }
}
