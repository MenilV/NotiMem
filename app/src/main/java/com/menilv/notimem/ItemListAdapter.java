package com.menilv.notimem;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemVH> {

  Context context;
  List<NotificationItem> modelList;

  public ItemListAdapter(Context context, List<NotificationItem> modelList) {
    this.context = context;
    this.modelList = modelList;
  }

  @Override public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ItemVH(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
  }

  @Override public void onBindViewHolder(ItemVH holder, int position) {
    holder.setData(modelList.get(position));
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public int getItemCount() {
    return modelList.size();
  }

  class ItemVH extends RecyclerView.ViewHolder {

    TextView title;
    TextView text;
    TextView subtext;
    TextView appgroupname;
    TextView time;
    ImageView image;

    ItemVH (View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.title);
      text = (TextView) itemView.findViewById(R.id.text);
      subtext = (TextView) itemView.findViewById(R.id.subtext);
      appgroupname = (TextView) itemView.findViewById(R.id.appgroupname);
      time = (TextView) itemView.findViewById(R.id.time);
      image = (ImageView) itemView.findViewById(R.id.icon);
    }

    public void setData(NotificationItem item) {
      title.setText("Title: " + item.getTitle());
      text.setText("Text: " + item.getText());
      subtext.setText("Subtext: " + item.getSubText());
      appgroupname.setText(item.getAppName());
      time.setText("Time: " + TimeAgo.using(item.getTime()));
      Drawable icon = null;
      try {
        icon = context.getPackageManager().getApplicationIcon(item.getAppGroupKey());
      } catch (PackageManager.NameNotFoundException e) {
        e.printStackTrace();
      }
      image.setImageDrawable(icon);
    }
  }
}