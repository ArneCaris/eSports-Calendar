package calendar.esports;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MatchNotificationService extends Service {

    public static int MY_NOTIFICATION_ID;
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String gameIcon           = intent.getStringExtra("gameIcon");
        Serializable serializable = intent.getSerializableExtra("match");
        if(serializable instanceof Match) {
            Match match           = (Match) serializable;
            MY_NOTIFICATION_ID    = match.getId();

            Log.d("matchName", "onReceive Match: " + match.getName());
            Log.d("gameIcon", "onReceive: " + gameIcon);
            sendNotification("Match Start", gameIcon, match);
        }

        return START_NOT_STICKY;
    }

    private void sendNotification(String match_start, String gameIcon, Match match) {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        String timeOfEvent = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.ENGLISH).format(match.getBegin_at());
        String defLogo = "game_logo1" ;

        if (gameIcon.equals("csgo")) {
            defLogo = defLogo.replace("1", "2");
        } else if (gameIcon.equals("lol")) {
            defLogo = defLogo.replace("1", "5");
        } else if (gameIcon.equals("ow")) {
            defLogo = defLogo.replace("1", "4");
        } else if (gameIcon.equals("dota2")) {
            defLogo = defLogo.replace("1", "3");
        }

        int gameIdentifier = this.getResources().getIdentifier(defLogo, "drawable",
                this.getPackageName());

        String message = ("You've set a notification for " + match.getName() + "\n" + "Match starts at: "
                + timeOfEvent);

//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification myNotification = new NotificationCompat.Builder(this)
                .setContentTitle("\uD83D\uDD14 " + match.getLeague().getName().toString())
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                //                            .setDefaults(Notification.DEFAULT_SOUND)
                //                            .setAutoCancel(true)
                .setSmallIcon(gameIdentifier)
                .build();

        startForeground(1, myNotification);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
