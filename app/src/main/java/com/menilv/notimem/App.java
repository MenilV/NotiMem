package com.menilv.notimem;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
  @Override public void onCreate() {
    super.onCreate();
    Realm.init(this);
    RealmConfiguration config = new RealmConfiguration.Builder().name("notimem.realm")
        .schemaVersion(1) // Must be bumped when the schema changes
        .migration(new MyMigration()).build();
    Realm.setDefaultConfiguration(config);
  }
}