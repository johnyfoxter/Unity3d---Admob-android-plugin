package com.projectikeu.admobplugin;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.unity3d.player.UnityPlayer;
import android.app.Activity;
public class Interstitial {
     
    private Activity activity; 
    private PublisherInterstitialAd interstitial;
    private boolean isLoaded = false;
     
    public void DisplayInterstitial() {
        activity.runOnUiThread(new Runnable() {
            public void run(){
                if (interstitial.isLoaded()) {
                    interstitial.show();
                }
            }
        });
      }
     
    public boolean IsInterstitialLoaded() {
        return isLoaded;
    }
     
    private void loadNewInterstitial(String TestDeviceID)
    {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .addTestDevice(TestDeviceID)
        .build();
         
        interstitial.loadAd(adRequest);
    }
     
    public Interstitial (final String PublisherID,final String TestDeviceID) {
         
        activity = UnityPlayer.currentActivity;
          
        activity.runOnUiThread(new Runnable() {
            public void run(){
                interstitial = new PublisherInterstitialAd(activity);
                interstitial.setAdUnitId(PublisherID);
                 
                interstitial.setAdListener(new AdListener() {
                    public void onAdClosed() {
                        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .addTestDevice(TestDeviceID)
                        .build();
                         
                        interstitial.loadAd(adRequest);
                        isLoaded = false;
                    }
                     
                    public void onAdLoaded() {
                        isLoaded = true;
                    }
                });
                 
                loadNewInterstitial(TestDeviceID);
            }
        });
    }
}
