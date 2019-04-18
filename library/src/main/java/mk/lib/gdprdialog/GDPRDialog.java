package mk.lib.gdprdialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GDPRDialog {
    private final AlertDialog alertDialog;
    private Context context;
    private ConsentFormListener consentFormListener;
    private List<GDPRFeature> features;
    private GDPRLearnDialog gdprLearnDialog;

    public GDPRDialog(final Context context, boolean canDismiss) {
        this.context = context;

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_gdpr, null);

        alertDialog = builder.setView(dialogView).setCancelable(canDismiss)
                .create();

        gdprLearnDialog = new GDPRLearnDialog(context);

        Button btAccept = dialogView.findViewById(R.id.btGDPRAgree);
        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsentInformation.getInstance(context).setConsentStatus(ConsentStatus.PERSONALIZED);
                if (consentFormListener != null) {
                    consentFormListener.onConsentFormClosed(ConsentStatus.PERSONALIZED, false);
                } else Log.e("GDPRDialog", "You need to add ConsentFormListener to GDPRDialog.");
                alertDialog.dismiss();
            }
        });

        TextView tvDisagree = dialogView.findViewById(R.id.btGDPRDisagree);
        tvDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsentInformation.getInstance(context).setConsentStatus(ConsentStatus.NON_PERSONALIZED);
                if (consentFormListener != null) {
                    consentFormListener.onConsentFormClosed(ConsentStatus.NON_PERSONALIZED, false);
                } else Log.e("GDPRDialog", "You need to add ConsentFormListener to GDPRDialog.");
                alertDialog.dismiss();
            }
        });

        TextView tvLearnMore = dialogView.findViewById(R.id.tvGDPRLearnMore);
        tvLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gdprLearnDialog.show();
            }
        });
    }

    public void setConsentFormListener(ConsentFormListener consentFormListener) {
        this.consentFormListener = consentFormListener;
    }

    public void setFeatures(GDPRFeature... features) {
        this.features = new ArrayList<>();
        Collections.addAll(this.features, features);
        gdprLearnDialog.setFeatures(Arrays.asList(features));
    }

    public void setFeatures(List<GDPRFeature> features) {
        this.features = features;
    }

    public void show() {
        alertDialog.show();
        if (consentFormListener != null) {
            consentFormListener.onConsentFormLoaded();
            consentFormListener.onConsentFormOpened();
        } else Log.e("GDPRDialog", "You need to add ConsentFormListener to GDPRDialog.");
    }

    public void setPrivacyPolicy(String url) {
        gdprLearnDialog.setPrivacyPolicy(url);
    }
}
