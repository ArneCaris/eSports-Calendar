package calendar.esports;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Date;
import java.util.Random;

public class AlarmNotificationService extends IntentService {
    //    public static int MY_NOTIFICATION_ID = 1;
    static Random random = new Random();
    public static int MY_NOTIFICATION_ID = random.nextInt(9999 - 1000) + 1000;


    private NotificationManager notificationManager;

    private String title;
    private String content;

    public AlarmNotificationService() {
        super("AlarmNotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification("Match Start");
    }

    private void sendNotification(String msg) {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification myNotification = new NotificationCompat.Builder(this)
                        .setContentTitle(msg)
                        .setContentText("Match starts")
                        .setTicker("Notification!")
//                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.lol)
                        .build();
        notificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), myNotification);
    }
}
