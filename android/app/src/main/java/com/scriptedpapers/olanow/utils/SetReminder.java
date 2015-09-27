package com.scriptedpapers.olanow.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class SetReminder {
	private Context mContext;
	private AlarmManager mAlarmManager;

	public static final String REMINDER_ID ="ReminderId";
	
	/*
	 * Constructor 
	 */
	public SetReminder(Context context) {
		mContext = context;
		mAlarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
	}

	/*
	 * Schedule an Alarm with a Pending Intent
	 */
	public void setAlarm(String eventId, int reminderId, long date) {

		Intent intent = new Intent(mContext, ReminderReceiver.class);
		intent.putExtra(REMINDER_ID, reminderId);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				mContext.getApplicationContext(), reminderId, intent, 0);

		mAlarmManager.set(AlarmManager.RTC_WAKEUP, date, pendingIntent);
	}

	/*
	 * Delete the Schedule the Alarm matching the intent.
	 */
	public void cancelAlarm(int reminderIntentId) {
		Intent intent = new Intent(mContext, ReminderReceiver.class);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				mContext.getApplicationContext(), reminderIntentId, intent, 0);
		mAlarmManager.cancel(pendingIntent);
	}

}
