package com.vedant.nav_realmadrid;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import static com.facebook.ads.CacheFlag.ALL;

public  class Second extends AppCompatActivity {

    TextView t1;
    String s2;
    String s3="tom and jerry";
    ArrayList<String> c_id,c_title,c_image;
    ListView lview;
    ProgressDialog pb;
    String TAG = "Error";
    com.vedant.nav_realmadrid.Customlist_adpter lAdapter;
    private final String TAGG = Second.class.getSimpleName();

    public InterstitialAd interstitialAd;
    //InterstitialAdListener interstitialAdListener;

    String s1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        c_id=new ArrayList<>();
        c_title=new ArrayList<>();
        c_image=new ArrayList<>();

        lview=findViewById(R.id.listview);
        new GetContacts().execute();





  /*      interstitialAdListener = new InterstitialAdListener()
        {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAGG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAGG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAGG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAGG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAGG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAGG, "Interstitial ad impression logged!");
            }
        };*/




        Intent in= getIntent();
        s1=in.getStringExtra("idd");
        // t1.setText(s1);



        if (s1.equals("at0"))
        {
            s2="Karim Benzema";
        }

        else if(s1.equals("at1"))
        {
            s2="Vinicius Junior";
        }
        else if(s1.equals("at2"))
        {
            s2="Eder Militao";
        }

        else if(s1.equals("at3"))
        {
            s2="Thibaut Courtois";
        }

        else if(s1.equals("at4"))
        {
            s2="Real Madrid LIVE";
        }

        else if(s1.equals("at5"))
        {
            s2="Luca modric";
        }

        else if(s1.equals("at6"))
        {
            s2="karim benzema";
        }

        else if(s1.equals("at7"))
        {
            s2="Carlo Ancelotti";
        }
        else if(s1.equals("at8"))
        {
            s2="Cristiano Ronaldo";
        }

        AudienceNetworkAds.initialize(this);
        interstitialAd = new InterstitialAd(this, "512674840596492_512675483929761");

    }


    @Override
    protected void onDestroy() {
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }



    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected  void onPreExecute(){
            super.onPreExecute();

            pb=new ProgressDialog(Second.this);
            pb.setMessage("Loading..");
            pb.setCancelable(false);
            pb.show();
        }



        @Override
        protected Void doInBackground(Void... arg0) {
            com.vedant.nav_realmadrid.HttpHandler sh = new com.vedant.nav_realmadrid.HttpHandler();
            // Making a request to url and getting response
            String url = "https://www.googleapis.com/youtube/v3/search?order=viewCount&q= "+s2+" &type=video&maxResults=50&part=snippet&fields=items(id/videoId,snippet/title,snippet/thumbnails)&key=AIzaSyA5xEfctDSUIjiXkVGtDurPbYoUfWkVtTw";
            // Log.v("output",sh.makeServiceCall(url));
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray item = jsonObj.getJSONArray("items");

                    // looping through All Contacts
                    for (int i = 0; i < item.length(); i++) {


                        JSONObject c = item.getJSONObject(i);

                        JSONObject d =c.getJSONObject("id");
                        JSONObject e=c.getJSONObject("snippet");

                        JSONObject f=e.getJSONObject("thumbnails");
                        JSONObject g=f.getJSONObject("high");

                        c_id.add(d.getString("videoId"));
                        c_title.add(e.getString("title"));
                        c_image.add(g.getString("url"));



                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Second.this,
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Second.this,
                                "Maintenance Break || Try Later",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            /*ListAdapter adapter = new SimpleAdapter(Customlistmain_main.this, contactList,
                    R.layout.list_item, new String[]{ "email","mobile"},
                    new int[]{R.id.email, R.id.mobile});
            lv.setAdapter(adapter);*/
            pb.dismiss();

            // interstitialAd.loadAd();


            InterstitialAdListener interstitialAdListener = new  InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    // Interstitial ad displayed callback
                    Log.e(TAG, "Interstitial ad displayed.");
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    // Interstitial dismissed callback
                    Log.e(TAG, "Interstitial ad dismissed.");
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
                    Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Interstitial ad is loaded and ready to be displayed
                    Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                    // Show the ad
                    interstitialAd.show();
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                    Log.d(TAG, "Interstitial ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Ad impression logged callback
                    Log.d(TAG, "Interstitial ad impression logged!");
                }
            };

            // For auto play video ads, it's recommended to load the ad
            // at least 30 seconds before it is shown
            /*interstitialAd.loadAd();*/
            interstitialAd.loadAd(interstitialAd.buildLoadAdConfig()
                    .withAdListener(interstitialAdListener)
                    .withCacheFlags(ALL)
                    .build());


            lAdapter = new com.vedant.nav_realmadrid.Customlist_adpter(Second.this,c_id,c_title,c_image);
            Log.d(TAGG,"beffore set Adapter");

            lview.setAdapter(lAdapter);
            Log.d(TAGG,"DONE DONE DONE DONE DONE DONE....?");

            lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent i =new Intent(Second.this, com.vedant.nav_realmadrid.YTplay.class);


                    i.putExtra("idd",c_id.get(position));
                    i.putExtra("img",c_image.get(position));
                    i.putExtra("title",c_title.get(position));
                    startActivity(i);
                }
            });

        }

    }

}


