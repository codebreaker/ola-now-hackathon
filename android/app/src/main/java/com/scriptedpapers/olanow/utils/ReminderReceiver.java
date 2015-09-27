package com.scriptedpapers.olanow.utils;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.view.WindowManager;
import android.widget.Toast;

import com.scriptedpapers.olanow.MainActivity;
import com.scriptedpapers.olanow.R;

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

			NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(mContext)
					.setSmallIcon(R.drawable.ola_logo) // notification icon
					.setContentTitle("OLA Reminder!") // title for notification
					.setContentText("You have a Reminder to Book a Cab Now.") // message for notification
					.setAutoCancel(true); // clear notification after click
			Intent intent = new Intent(mContext, MainActivity.class);
			PendingIntent pi = PendingIntent.getActivity(mContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
			mBuilder.setContentIntent(pi);
			NotificationManager mNotificationManager =
					(NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
			mNotificationManager.notify(0, mBuilder.build());




			}
		}
	}
	
	

