
package calendar.esports;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.android.exoplayer2.C;

import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AlarmNotificationService extends IntentService {
    private final String CHANNEL_ID = "estream_notifications";
    public static int MY_NOTIFICATION_ID;
    public String channelName;
    //    public static int MY_NOTIFICATION_ID;// = NotificationID.getID();
    private NotificationManager notificationManager;

    public AlarmNotificationService() {
        super("AlarmNotificationService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String gameIcon           = intent.getStringExtra("gameIcon");
        Serializable serializable = intent.getSerializableExtra("match");
        if(serializable instanceof Match) {
            Match match           = (Match) serializable;
            MY_NOTIFICATION_ID    = match.getId();

            Log.d("matchName", "onReceive Match: " + match.getName());
            Log.d("gameIcon", "onReceive: " + gameIcon);
            createNotificationChannel();
            sendNotification("Match is Starting", gameIcon, match);
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "EStream Notifications";
            String description = "All EStream notifications";
            int importance = notificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void sendNotification(String msg, String gameIcon, Match match) {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        String timeOfEvent = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(match.getBegin_at());
        String defLogo = "game_logo1" ;
        String urlCsgo = "https://www.twitch.tv/directory/game/Counter-Strike%3A%20Global%20Offensive";
        String urlLol = "https://www.twitch.tv/directory/game/League%20of%20Legends";
        String urlOw = "https://www.twitch.tv/directory/game/Overwatch";
        String urlDota2 = "https://www.twitch.tv/directory/game/Dota%202";

        Intent streamIntent = new Intent(Intent.ACTION_VIEW);

        switch (gameIcon) {
            case "csgo":
                defLogo = defLogo.replace("1", "2");
                streamIntent.setData(Uri.parse(urlCsgo));
                //uri.parse("https://twitch.tv/ + channelName")
                break;
            case "lol":
                defLogo = defLogo.replace("1", "5");
                streamIntent.setData(Uri.parse(urlLol));
                break;
            case "ow":
                defLogo = defLogo.replace("1", "4");
                streamIntent.setData(Uri.parse(urlOw));
                break;
            case "dota2":
                defLogo = defLogo.replace("1", "3");
                streamIntent.setData(Uri.parse(urlDota2));
                break;
        }

        int gameIdentifier = this.getResources().getIdentifier(defLogo, "drawable",
                this.getPackageName());

        String message = ("You've set a notification for " + match.getName() + "\n" + "Match starts at: "
                + timeOfEvent);

        PendingIntent pendingStreamIntent = PendingIntent.getActivity(this, 0, streamIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification myNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(match.getLeague().getName())
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                //.setDefaults(Notification.DEFAULT_SOUND)
                //.setAutoCancel(true)
                .setSmallIcon(gameIdentifier)
//                .setOngoing(true)
                .setContentIntent(pendingStreamIntent)
                .build();

        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
    }
}

//public class AlarmNotificationService extends IntentService {
//private static final String ACTION_START = "ACTION_START";
//    private static final String ACTION_DELETE = "ACTION_DELETE";

//    public static Intent createIntentStartNotification(Context context){
//        Intent intent = new Intent(context, AlarmNotificationService.class);
//        intent.setAction(ACTION_START);
//        return intent;
//    }
//    public static Intent createIntentDeleteNotification(Context context) {
//        Intent intent = new Intent(context, AlarmNotificationService.class);
//        intent.setAction(ACTION_DELETE);
//        return intent;
//    }
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        try {
//            String action = intent.getAction();
//            if (ACTION_START.equals(action)) {
//                processStartNotification(intent);
//            }
//            if (ACTION_DELETE.equals(action)) {
//                processDeleteNotification(intent);
//            }
//        } finally {
//            WakefulBroadcastReceiver.completeWakefulIntent(intent);
//        }
//    }
//    private void processDeleteNotification(Intent intent) { }
//    private void processStartNotification(Intent intent) {
//        String gameIcon           = intent.getStringExtra("gameIcon");
//        Serializable serializable = intent.getSerializableExtra("match");
//        if(serializable instanceof Match) {
//            Match match           = (Match) serializable;
//            MY_NOTIFICATION_ID    = match.getId();
//
//            Log.d("matchName", "onReceive Match: " + match.getName());
//            Log.d("gameIcon", "onReceive: " + gameIcon);
//            sendNotification("Match Start", gameIcon, match);
//        }
//    }

//    private void sendNotification(String msg, String gameIcon, Match match) {
//
//    final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentTitle("\uD83D\uDD14 " + match.getLeague().getName().toString())
//                .setContentText(message)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
//                .setTicker("Notification!")
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(gameIdentifier);
//
//                PendingIntent contentIntent = PendingIntent.getActivity(this, MY_NOTIFICATION_ID,
//                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
//
//        builder.setContentIntent(contentIntent);
//        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));
//
//
//        notificationManager.notify(MY_NOTIFICATION_ID, builder.build());
//        }
//}

