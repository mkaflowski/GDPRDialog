package gdprdialog.lib.mk.gdprdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.socks.library.KLog;

import mk.lib.gdprdialog.GDPRDialog;
import mk.lib.gdprdialog.GDPRFeature;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showGDPR(View view) {
        KLog.i("gdpr status" + ConsentInformation.getInstance(this).getConsentStatus());
        KLog.i("gdpr loaction" + ConsentInformation.getInstance(this).isRequestLocationInEeaOrUnknown());

        GDPRDialog gdpr = new GDPRDialog(this, false);

        gdpr.setConsentFormListener(new ConsentFormListener() {
            @Override
            public void onConsentFormOpened() {
                super.onConsentFormOpened();
            }

            @Override
            public void onConsentFormClosed(ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                super.onConsentFormClosed(consentStatus, userPrefersAdFree);
                KLog.d("gdpr " + consentStatus);
                if (consentStatus == ConsentStatus.NON_PERSONALIZED) {
                    //disablePersonalizedFeatures(activity);

                } else {
                    //enablePersonalizedFeatures(activity);
                }
            }
        });
        gdpr.setFeatures(GDPRFeature.GOOGLE, GDPRFeature.FACEBOOK, GDPRFeature.FIREBASE_CRASH_REPORTING, GDPRFeature.FIREBASE_CLOUD_MESSAGING, GDPRFeature.FIREBASE);
        gdpr.setPrivacyPolicy("https://play.google.com/store/apps/");
        gdpr.show();
    }
}
