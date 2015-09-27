package com.scriptedpapers.olanow.data;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by mahes on 27/9/15.
 */
public class Reminder {

    public static final String REMINDER_ID="reminderId";
    public static final String REMINDER_NAME="reminderName";
    public static final String REMINDER_DATE="reminderDate";

    @DatabaseField(unique = true)
    String reminderId;
    @DatabaseField
    String reminderName;
    @DatabaseField
    long reminderDate;

    public String getReminderId() {
        return reminderId;
    }

    public String getReminderName() {
        return reminderName;
    }

    public long getReminderDate() {
        return reminderDate;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public void setReminderDate(long reminderDate) {
        this.reminderDate = reminderDate;
    }
}
