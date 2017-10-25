package com.menilv.notimem;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NotificationItem extends RealmObject {

  @PrimaryKey private String uid;
  private String appGroupKey;
  private String title;
  private String subText;
  private String text;
  private long icon;
  private long time;
  private String appName;

  public String getUid() {
    return uid;
  }

  public String getUidString() {
    return String.valueOf(this.uid);
  }

  public NotificationItem setUid(String uid) {
    this.uid = uid;
    return this;
  }

  public String getAppGroupKey() {
    return appGroupKey;
  }

  public NotificationItem setAppGroupKey(String appGroupKey) {
    this.appGroupKey = appGroupKey;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public NotificationItem setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getSubText() {
    return subText;
  }

  public NotificationItem setSubText(String subText) {
    this.subText = subText;
    return this;
  }

  public String getText() {
    return text;
  }

  public NotificationItem setText(String text) {
    this.text = text;
    return this;
  }

  public long getIcon() {
    return icon;
  }

  public NotificationItem setIcon(long icon) {
    this.icon = icon;
    return this;
  }

  public long getTime() {
    return time;
  }

  public NotificationItem setTime(long time) {
    this.time = time;
    return this;
  }

  public NotificationItem setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public String getAppName() {
    return appName;
  }
}
