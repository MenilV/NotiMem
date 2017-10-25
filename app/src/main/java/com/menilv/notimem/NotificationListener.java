package com.menilv.notimem;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import io.realm.Realm;
import java.util.Calendar;
import java.util.TimeZone;

public class NotificationListener extends NotificationListenerService {

  private Context context;

  @Override public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
  }

  @Override public void onNotificationPosted(StatusBarNotification sbn, RankingMap rankingMap) {
    Log.e("NOTIMEM", "Notified");
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    long moment = cal.getTimeInMillis();
    PackageManager packageManager= getApplicationContext().getPackageManager();
    String appName = null;
    try {
      appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(sbn.getPackageName(), PackageManager.GET_META_DATA));
    } catch (PackageManager.NameNotFoundException e) {
      appName = sbn.getPackageName();
    }
    NotificationItem item =
        new NotificationItem().setUid(sbn.getGroupKey().split("\\|")[4] + String.valueOf(moment))
            .setAppGroupKey(sbn.getPackageName())
            .setAppName(appName)
            .setIcon(sbn.getNotification().extras.getInt(Notification.EXTRA_SMALL_ICON))
            .setTitle(sbn.getNotification().extras.getString("android.title"))
            .setText(sbn.getNotification().extras.getString("android.text"))
            .setSubText(sbn.getNotification().extras.getString("android.subText"))
            .setTime(moment);

    Realm.getDefaultInstance().beginTransaction();
    Realm.getDefaultInstance().copyToRealmOrUpdate(item);
    Realm.getDefaultInstance().commitTransaction();
  }

  @Override public void onNotificationRemoved(StatusBarNotification sbn, RankingMap rankingMap) {
    Log.e("NOTIMEM", "removed");
    super.onNotificationRemoved(sbn, rankingMap);
  }

  @Override public void onListenerConnected() {
    Log.e("NOTIMEM", "connecteed");
    super.onListenerConnected();
  }

  @Override public void onListenerDisconnected() {
    Log.e("NOTIMEM", "disconnecteed");
    super.onListenerDisconnected();
  }
}
