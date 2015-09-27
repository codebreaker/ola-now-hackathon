package com.scriptedpapers.olanow.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.scriptedpapers.olanow.R;
import com.scriptedpapers.olanow.data.Reminder;
import com.scriptedpapers.olanow.utils.CalendarUtils;
import com.squareup.okhttp.internal.Util;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by jagadeeshwarank on 27/9/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "olanow.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper databaseHelper = null;
    private Dao<Reminder, Integer> reminderDao = null;
    private static Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        this.context=context;
    }

    public static DatabaseHelper getHelper(Context activityObject) {

        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(activityObject, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public Dao<Reminder, Integer> getReminderDao() throws SQLException {

        if (reminderDao == null) {
            reminderDao = getDao(Reminder.class);
        }
        return reminderDao;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Reminder.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Reminder.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public static List<Reminder> getAllReminders(){
        try {
            long current = System.currentTimeMillis();
            Dao<Reminder, Integer> reminderDao = DatabaseHelper.getHelper(context).getReminderDao();
            List<Reminder> selectedRegion = reminderDao.queryBuilder().where().
                    ge(Reminder.REMINDER_DATE, current).query();

            return selectedRegion;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Reminder getReminderById(String reminderId){
        try {
            Dao<Reminder, Integer> reminderDao = DatabaseHelper.getHelper(context).getReminderDao();
            List<Reminder> selectedRegion = reminderDao.queryBuilder().where().
                    ge(Reminder.REMINDER_ID, reminderId).query();
            if(selectedRegion.size()>0)
            return selectedRegion.get(0);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteReminderById(int reminderId){
        try {
            Dao<Reminder, Integer> reminderDao = DatabaseHelper.getHelper(context).getReminderDao();
            DeleteBuilder<Reminder, Integer> deleteBuilder = reminderDao.deleteBuilder();
            deleteBuilder.where().eq(Reminder.REMINDER_ID, reminderId);
            deleteBuilder.delete();
            return true;
        } catch (Exception exception) {

        }
        return false;
    }

    public static boolean insertReminder(Reminder reminder){
        try {
            Dao<Reminder, Integer> reminderDao = DatabaseHelper.getHelper(context).getReminderDao();
            reminderDao.createOrUpdate(reminder);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getMaxReminderId(){
        try {
            Dao<Reminder, Integer> reminderDao = DatabaseHelper.getHelper(context).getReminderDao();

            List<Reminder> selectedZone = reminderDao.queryBuilder().query();

            QueryBuilder<Reminder, Integer> qb = reminderDao.queryBuilder();

            qb.selectRaw(" MAX(reminderId)");

            // the results will contain 2 string values for the min and max

            GenericRawResults<String[]> results = reminderDao.queryRaw(qb.prepareStatementString());

            String[] values = results.getFirstResult();
            int maxPriority = 0;
            if (values != null && values.length != 0) {
                String value = values[0];
                if (value != null)
                    maxPriority = Integer.parseInt(value);
            }
            return maxPriority;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static List<Reminder> getTodaysReminders(boolean isToday){


        Date startDate;
        Date endDate;
        if(isToday) {
            startDate = new Date();
            endDate = CalendarUtils.getEndOfDay(new Date());
        } else {
            startDate = CalendarUtils.getStartOfDay(CalendarUtils.getTomorrowDate());
            endDate = CalendarUtils.getEndOfDay(CalendarUtils.getTomorrowDate());
        }

        try {
            long start = startDate.getTime();
            long end = endDate.getTime();
            Dao<Reminder, Integer> reminderDao = DatabaseHelper.getHelper(context).getReminderDao();
            List<Reminder> selectedRegion = reminderDao.queryBuilder().where().
                    ge(Reminder.REMINDER_DATE, start).and().lt(Reminder.REMINDER_DATE, end).query();

            return selectedRegion;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }
}
