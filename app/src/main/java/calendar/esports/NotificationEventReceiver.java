package calendar.esports;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationEventReceiver extends WakefulBroadcastReceiver {

    private static final String ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE";
    private static final String ACTION_DELETE_NOTIFICATION = "ACTION_DELETE_NOTIFICATION";

//    public static void setupAlarm(Context context, Match match) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        PendingIntent alarmIntent = getStartPendingIntent(context);
//        long sec = getTriggerAt(match.getBegin_at().toString());
//        long now = System.currentTimeMillis();
//        alarmManager.set(AlarmManager.RTC_WAKEUP,
//                now + 5000,
//                alarmIntent);
//    }
//
//    private static long getTriggerAt(String begin_at) {
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
//        Date date = null;
//        try {
//            date = sdf.parse(begin_at);
//            Long timeInMilliseconds = date.getTime();
//            Log.d("getMilis", "getMilis: " + timeInMilliseconds);
//            return timeInMilliseconds;
//        }
//        catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//        Intent serviceIntent = null;
//        if (ACTION_START_NOTIFICATION_SERVICE.equals(action)) {
//            Log.i(getClass().getSimpleName(), "onReceive from alarm, starting notification service");
//            serviceIntent = AlarmNotificationService.createIntentStartNotification(context);
//        } else if (ACTION_DELETE_NOTIFICATION.equals(action)) {
//            Log.i(getClass().getSimpleName(), "onReceive delete notification action, starting notification service to handle delete");
//            serviceIntent = AlarmNotificationService.createIntentDeleteNotification(context);
//        }
//
//        if (serviceIntent != null) {
//            startWakefulService(context, serviceIntent);
//        }
    }

//    private static PendingIntent getStartPendingIntent(Context context) {
//        Intent intent = new Intent(context, NotificationEventReceiver.class);
//        intent.setAction(ACTION_START_NOTIFICATION_SERVICE);
//        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//    }
//
//    public static PendingIntent getDeleteIntent(Context context) {
//        Intent intent = new Intent(context, NotificationEventReceiver.class);
//        intent.setAction(ACTION_DELETE_NOTIFICATION);
//        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//    }
}
