package com.example.vincent.facebooktimeline;

import com.example.vincent.facebooktimeline.adapter.FeedListAdapter;
import com.example.vincent.facebooktimeline.app.AppController;
import com.example.vincent.facebooktimeline.data.FeedItem;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import static com.example.vincent.facebooktimeline.R.id.btn_follow;
import static com.example.vincent.facebooktimeline.R.id.btn_share;
import static com.example.vincent.facebooktimeline.R.id.coupon_barcode;

public class MainActivity extends AppCompatActivity {
        private static final String TAG = MainActivity.class.getSimpleName();
        private ListView mListView;
        private FeedListAdapter mListAdapter;
        private List <FeedItem> mFeedItems;
        private final String URL_FEED = "http://cap-api.solutetech.io/customer_coupons/390";

        @SuppressLint("NewApi")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            mListView = (ListView) findViewById(R.id.list);

            mFeedItems = new ArrayList<>();

            mListAdapter = new FeedListAdapter(this, mFeedItems);
            mListView.setAdapter(mListAdapter);



            //Check for cache request
            Cache cache = AppController.getInstance().getRequestQueue().getCache();
            Cache.Entry entry = cache.get(URL_FEED);
            if (entry != null) {
                //fetch the data from the cache
                try {
                    String data = new String(entry.data, "UTF-8");
                    fetchData(new JSONObject(data));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            } else {
                //Making fresh volley request and getting Json
                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                        URL_FEED, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(TAG, "Response: " + response.toString());
                        if (response != null) {
                            fetchData(response);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });

                //Adding request to volley request queue
                AppController.getInstance().addToRequestQueue(jsonReq);
            }

        }

        /**
         * Parsing json response and passing he dat to feed view list adapter
         */
            private void fetchData(JSONObject response) {


                mFeedItems.clear();

                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));

                    FeedItem pImage = new FeedItem();

                    JSONObject game = jsonObject.getJSONObject("data");
                    JSONObject life = game.getJSONObject("customer");
                    pImage.setFirst_name(life.getString("first_name"));
                    pImage.setPhoto(life.getString("photo"));
                    pImage.setLast_name(life.getString("last_name"));

                    JSONObject coupon = life.getJSONObject("saved");

                    JSONArray work = coupon.getJSONArray("coupons");
                    for (int i = 0; i < work.length(); i++) {
                        JSONObject time = work.getJSONObject(i);



                        pImage.setCoupon_name(time.getString("coupon_name"));
                        pImage.setCoupon_image(time.getString("coupon_image"));
                        pImage.setCoupon_barcode(time.getString("coupon_barcode"));
                        pImage.setCoupon_expiration(time.getString("coupon_expiration"));
                        pImage.setCoupon_description(time.getString("coupon_description"));

                        mFeedItems.add(pImage);
                    }
                    mListAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }