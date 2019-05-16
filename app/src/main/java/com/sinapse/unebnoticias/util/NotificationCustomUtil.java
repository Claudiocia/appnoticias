package com.sinapse.unebnoticias.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import androidx.core.app.NotificationCompat;

import com.sinapse.unebnoticias.R;
import com.sinapse.unebnoticias.SplashActivity;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class NotificationCustomUtil {
    private static NotificationManager mNotificationManager;

    public static void sendNotification(Context context, String title, String author, String message) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, SplashActivity.class), 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        /*
                .setSmallIcon(R.drawable.ic_launcher)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[] { 100, 250, 100, 500 })
                .setContentTitle(title)
                .setContentText(author+": "+message); */

        mBuilder.setContentIntent(contentIntent);

        // BIG TEXT

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();

        bigText.bigText(author);
        bigText.setBigContentTitle(title);
        if(author != null) {
            mBuilder.setGroupSummary(true);
            bigText.setSummaryText(message);

        }
        mBuilder.setStyle(bigText)
                .setSmallIcon(R.drawable.ic_launcher)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[] { 100, 250, 100, 500 });
        //.setContentTitle(title)
        //.setContentText(author + ": " + message);

        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(AndroidSystemUtil.randInt(), notification);
    }
}
