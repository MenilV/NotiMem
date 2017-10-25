package com.menilv.notimem;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final String notifListenerEnabled = "enabled_notification_listeners";
  private static final String notifListenerSettings = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
  private ItemListAdapter adapter;

  private ListView list;
  List<NotificationItem> modelList = new ArrayList<>();
  private String serviceID;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    String enabledListeners = Settings.Secure.getString(getContentResolver(), notifListenerEnabled);
    if(!hasServiceEnabled(enabledListeners)){
      Intent intent = new Intent(notifListenerSettings);
      startActivity(intent);
    }

    fillData();
  }

  private void fillData() {
    modelList = Realm.getDefaultInstance().copyFromRealm(
        Realm.getDefaultInstance()
            .where(NotificationItem.class)
            .findAll());

    Collections.reverse(modelList);
    adapter = new ItemListAdapter (this, modelList);
    RecyclerView view = (RecyclerView) findViewById(R.id.list);
    view.setAdapter(adapter);
  }

  private boolean hasServiceEnabled(String serviceList){
    serviceID = getPackageName() + "/" + getPackageName() + "." + NotificationListener.class.getSimpleName();
    return serviceList.contains(serviceID);
  }
}
