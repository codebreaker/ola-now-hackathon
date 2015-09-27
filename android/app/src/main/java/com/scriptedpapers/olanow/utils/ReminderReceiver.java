package com.scriptedpapers.olanow.utils;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.view.WindowManager;
import android.widget.Toast;

/*
 * Receiver Class to Receive Reminder Pending intent
 */
public class ReminderReceiver extends BroadcastReceiver {
	
	public static String eventId;
	
	private static volatile PowerManager.WakeLock lockStatic=null;

	@Override
	public void onReceive(final Context mContext, Intent mIntent) {

		// Check Whether Pending Intent has needed data and validate it
		if (mIntent.hasExtra(SetReminder.REMINDER_ID)) {

				Toast.makeText(mContext, "Reminder Called", Toast.LENGTH_SHORT)
						.show();

				




			}
		}
	}
	
	

