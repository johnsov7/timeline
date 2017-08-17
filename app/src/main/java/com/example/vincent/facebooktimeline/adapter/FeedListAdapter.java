package com.example.vincent.facebooktimeline.adapter;

/**
 * Created by vincent on 12/3/16.
 */

import com.example.vincent.facebooktimeline.Barcode;
import com.example.vincent.facebooktimeline.FeedImageView;
import com.example.vincent.facebooktimeline.R;
import com.example.vincent.facebooktimeline.app.AppController;
import com.example.vincent.facebooktimeline.data.FeedItem;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;



public class FeedListAdapter extends BaseAdapter {

    private Context ctx;
    private List<FeedItem> feedItems;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public FeedListAdapter(Context ctx, List<FeedItem> feedItems) {
        this.ctx = ctx;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int position) {
        return feedItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(ctx).inflate(R.layout.feed_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.timeStamp = (TextView) convertView.findViewById(R.id.timestamp);
            holder.statusMsg = (TextView) convertView.findViewById(R.id.txtStatusMsg);
            holder.url = (TextView) convertView.findViewById(R.id.txtUrl);
            holder.profilePic = (NetworkImageView) convertView.findViewById(R.id.profilePic);
            holder.feedImageView = (FeedImageView) convertView.findViewById(R.id.feedImage1);
            holder.follow = (ImageView) convertView.findViewById(R.id.btn_follow);
            holder.couponBarcode = (ImageView) convertView.findViewById(R.id.coupon_barcode);
            holder.share = (ImageView) convertView.findViewById(R.id.btn_share);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        FeedItem item = feedItems.get(position);

        holder.name.setText(item.getCoupon_name());

        holder.couponBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctx.startActivity(new Intent(ctx, Barcode.class));
            }
        });

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getCoupon_barcode()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timeStamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus())) {
            holder.statusMsg.setText(item.getStatus());
            holder.statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            holder.statusMsg.setVisibility(View.GONE);
        }

//        if (item.getUrl() != null) {
//            holder.url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
//                    + item.getUrl() + "</a> "));
//
//            //Making url clickable
//            holder.url.setMovementMethod(LinkMovementMethod.getInstance());
//            holder.url.setVisibility(View.VISIBLE);
//        } else {
//            holder.url.setVisibility(View.GONE);
//        }

        //User profile picture
        holder.profilePic.setImageUrl(item.getCoupon_image(), imageLoader);

        //Feed image
        if (item.getCoupon_image() != null) {
            holder.feedImageView.setImageUrl(item.getCoupon_image(), imageLoader);
            holder.feedImageView.setVisibility(View.VISIBLE);
            holder.feedImageView.setResponseObserver(new FeedImageView.ResponseObserver() {
                @Override
                public void onError() {
                }

                @Override
                public void onSuccess() {
                }
            });
        } else {
            holder.feedImageView.setVisibility(View.GONE);
        }


        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView timeStamp;
        TextView statusMsg;
        TextView url;
        NetworkImageView profilePic;
        FeedImageView feedImageView;
        ImageView couponBarcode;
        ImageView follow;
        ImageView share;
    }
}
