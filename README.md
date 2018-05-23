# GDPRDialog
Dialog with privacy policy.

## Installation

To use the library, first include it your project using Gradle

    allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
    }

	dependencies {
	        compile 'com.github.mkaflowski:GDPRDialog:1.x'
	}
	

## How to use

### Google Consent SDK

```java
	final ConsentInformation consentInformation = ConsentInformation.getInstance(this);
        consentStatus = consentInformation.getConsentStatus();
        String[] publisherIds = {"<pub-number>"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
                SplashActivity.this.consentStatus = consentStatus;
                if (consentStatus == ConsentStatus.UNKNOWN && ConsentInformation.getInstance(context).isRequestLocationInEeaOrUnknown()) {
                    showGDPRDialog(SplashActivity.this);
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
                KLog.w("gdpr " + errorDescription);
            }
        });
```

### GDPRDialog

```java

   //Dialog:
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
        gdpr.show();
```
