package calendar.esports;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmNotificationService extends IntentService {
    public final int MY_NOTIFICATION_ID=1;
    private NotificationManager notificationManager;

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
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.lol)
                        .build();

        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
    }
}
