package calendar.esports;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.Serializable;

public class NotificationServiceStarterReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Serializable serializable = intent.getSerializableExtra("match");
        if(serializable instanceof Match) {
            Match match           = (Match) serializable;
//            NotificationEventReceiver.setupAlarm(context, match);
        }
    }
}
