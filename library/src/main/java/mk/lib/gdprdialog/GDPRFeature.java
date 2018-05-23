package mk.lib.gdprdialog;

public class GDPRFeature {
    private String text;
    private String url;

    public GDPRFeature(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return getText();
    }


    public static final GDPRFeature GOOGLE = new GDPRFeature("Google - tap to see details", "https://policies.google.com/technologies/partner-sites");
    public static final GDPRFeature FACEBOOK = new GDPRFeature("Facebook - tap to see details", "https://www.facebook.com/about/privacy/update");
    public static final GDPRFeature FIREBASE = new GDPRFeature("Firebase - tap to see details", "https://firebase.google.com/support/privacy/");
    public static final GDPRFeature FIREBASE_CLOUD = new GDPRFeature("Cloud Functions for Firebase - How it helps: Cloud Functions uses IP addresses to execute event-handling functions and HTTP functions based on end-user actions.", "https://firebase.google.com/support/privacy/");
    public static final GDPRFeature FIREBASE_AUTHENTICATION = new GDPRFeature("Firebase Authentication - How it helps: Firebase Authentication uses the data to enable end-user authentication, and facilitate end-user account management. It also uses user-agent strings and IP addresses to provide added security and prevent abuse during sign-up and authentication.", "https://firebase.google.com/support/privacy/");
    public static final GDPRFeature FIREBASE_CLOUD_MESSAGING = new GDPRFeature("Firebase Cloud Messaging - How it helps: Firebase Cloud Messaging uses Instance IDs to determine which devices to deliver messages to.", "https://firebase.google.com/support/privacy/");
    public static final GDPRFeature FIREBASE_CRASH_REPORTING = new GDPRFeature("Firebase Crash Reporting - How it helps: Crash Reporting uses crash stack traces to associate crashes with a project, send email alerts to project members and display them in the Firebase Console, and help Firebase customers debug crashes. It uses Instance IDs to measure number of users impacted by a crash.", "https://firebase.google.com/support/privacy/");
    public static final GDPRFeature FIREBASE_DYNAMIC_LINKS = new GDPRFeature("Firebase Dynamic Links - How it helps: Dynamic Links uses device specs on iOS to open newly-installed apps to a specific page or context.", "https://firebase.google.com/support/privacy/");
    public static final GDPRFeature FIREBASE_HOSTING = new GDPRFeature("Firebase Hosting - How it helps: Hosting uses IP addresses of incoming requests to detect abuse and provide customers with detailed analysis of usage data.", "https://firebase.google.com/support/privacy/");
}
