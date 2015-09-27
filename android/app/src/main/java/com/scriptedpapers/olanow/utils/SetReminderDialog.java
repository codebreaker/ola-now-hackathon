package com.scriptedpapers.olanow.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.scriptedpapers.olanow.R;
import com.scriptedpapers.olanow.ReminderScreen;
import com.scriptedpapers.olanow.data.Reminder;
import com.scriptedpapers.olanow.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;

public class SetReminderDialog extends Dialog {

	AppCompatActivity activityObject;

    static  TextView setDateTextView;
    static TextView setTimeTextView;

    Button saveLocationButton;
    Button closeLocationButton;

    Date selectedDate;
    static Calendar selectedCalendar;

	private EditText edtReminderName;

	public SetReminderDialog(final AppCompatActivity activityObject) {

		super(activityObject);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.activityObject = activityObject;

        selectedDate = new Date((new Date()).getTime() + 600000);
        selectedCalendar = Calendar.getInstance();
        selectedCalendar.setTime(selectedDate);


		initializeDialog();
	}

	void initializeDialog() {

		setContentView(R.layout.dlg_set_reminder);

		setDateTextView = (TextView) findViewById(R.id.setDateTextView);
		setTimeTextView = (TextView) findViewById(R.id.setTimeTextView);


        saveLocationButton = (Button) findViewById(R.id.saveLocationButton);
        closeLocationButton = (Button) findViewById(R.id.closeLocationButton);

		edtReminderName =(EditText)findViewById(R.id.ReminderName);

        saveLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Primary ID from DB;

                int id = 0;

				id = DatabaseHelper.getMaxReminderId()+1;

                SetReminder setReminder = new SetReminder(activityObject);
                setReminder.setAlarm("Event", id, selectedCalendar.getTime().getTime());

				Reminder newReminder = new Reminder();
				newReminder.setReminderId(id);
				if(edtReminderName.getText() == null || edtReminderName.getText().toString().length() == 0) {
					newReminder.setReminderName("Reminder " + id);
				} else {
					newReminder.setReminderName(edtReminderName.getText().toString());
				}
				newReminder.setReminderDate(selectedCalendar.getTime().getTime());
				DatabaseHelper.insertReminder(newReminder);

                if (activityObject instanceof ReminderScreen) {
                    ((ReminderScreen)activityObject).updateView();
                }
                dismiss();
            }
        });


        closeLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

		setTimeTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTime();
			}
		});


		setDateTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setDate();
			}
		});
        updateDate();
		
	}

	public void setTime() {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(activityObject.getSupportFragmentManager(), "timePicker");

	}

	public static class TimePickerFragment extends DialogFragment
			implements TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			int hour = selectedCalendar.get(Calendar.HOUR_OF_DAY);
			int minute = selectedCalendar.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            selectedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            selectedCalendar.set(Calendar.MINUTE, minute);
			selectedCalendar.set(Calendar.SECOND, 0);
            updateDate();
		}
	}

	public void setDate() {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(activityObject.getSupportFragmentManager(), "datePicker");

	}

	public static class DatePickerFragment extends DialogFragment
			implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			// Create a new instance of TimePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, selectedCalendar.get(Calendar.YEAR), selectedCalendar.get(Calendar.MONTH),
                    selectedCalendar.get(Calendar.DAY_OF_MONTH));
		}



		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            selectedCalendar.set(Calendar.YEAR, year);
            selectedCalendar.set(Calendar.MONTH, monthOfYear);
            selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
		}
	}

    static void updateDate() {

        SimpleDateFormat tf = new SimpleDateFormat("hh:mm a ");
        String formattedTime = tf.format(selectedCalendar.getTime());



        SimpleDateFormat df = new SimpleDateFormat("yyyy MMMM, dd");
        String formattedDate = df.format(selectedCalendar.getTime());

        setDateTextView.setText(formattedDate);
        setTimeTextView.setText(formattedTime);
    }
	
}
