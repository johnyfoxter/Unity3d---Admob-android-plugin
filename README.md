# Unity3d-Admob-android-plugin
==============================
The Google Mobile Ads SDK is the latest generation in Google mobile advertising
featuring refined ad formats and streamlined APIs for access to mobile ad
networks and advertising solutions. The SDK enables mobile app developers to
maximize their monetization in native mobile apps.

Podmienky :

    Unity3D verzie 4.5 a viac
    Eclipse + ADT plugin
    Android SDK Tools
    Android Play Services plugin, ktorý stiahneme použitím Android SDK Manageru.
    Android SDK

Documentation
--------------
For instructions on using the plugin, please refer to
[this developer guide](https://www.projectik.eu/index.php/programovanie/unity3d/item/249-unity3d-admob-android-plugin-google-play-services-interstitial-ads).

```csharp
using UnityEngine;
using System;
public class ProjectikeuAdmob : MonoBehaviour {
    private string msg="nic";
    public string PublisherID = "YOUR_AD_UNIT_ID";
    public string TestDeviceID = "";
    private static AndroidJavaObject jo;
    // Dont destroy on load and prevent duplicate
    private static bool created = false;
    void Awake()
    {
        try
        {
            if (!created)
            {
                DontDestroyOnLoad(this.gameObject);
                created = true;
                initializeInterstitial();
            }
            else
            {
                Destroy(this.gameObject);
            }
        }
        catch (Exception ex)
        {
            msg = ex.Message;
        }
    }
    void OnGUI()
    {
        GUI.Label(new Rect(10, 10, 100, 100), msg);
    }
    void initializeInterstitial()
    {
        #if UNITY_ANDROID       
        jo = new AndroidJavaObject("com.projectikeu.admobplugin.Interstitial", PublisherID, TestDeviceID);
        #endif
    }
    /// <summary>
    /// Load and show the interstitial.
    /// </summary>
    public static void ShowInterstitial()
    {
#if UNITY_ANDROID       
        jo.Call("DisplayInterstitial");
#endif
    }
}
```
