package calendar.esports;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.io.Serializable;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            ComponentName comp = new ComponentName(context.getPackageName(), AlarmNotificationService.class.getName());
            startWakefulService(context, (intent.setComponent(comp)));
    }
}
