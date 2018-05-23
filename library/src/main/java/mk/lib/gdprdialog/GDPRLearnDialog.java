package mk.lib.gdprdialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;


public class GDPRLearnDialog {
    private AlertDialog alertDialog;
    private Context context;
    private List<GDPRFeature> features;

    public GDPRLearnDialog(Context context) {
        this.context = context;
    }


    public void show() {
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_gdpr_learn, null);

        alertDialog = builder.setView(dialogView).setCancelable(true)
                .create();
        ListView listView = dialogView.findViewById(R.id.lvGDPR);


        final FeatureAdapter adapter = new FeatureAdapter(features, context);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final GDPRFeature item = (GDPRFeature) parent.getItemAtPosition(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                context.startActivity(browserIntent);
            }

        });
        alertDialog.show();
    }

    public void setFeatures(List<GDPRFeature> features) {
        this.features = features;
    }

    private class FeatureAdapter extends ArrayAdapter<GDPRFeature> {

        HashMap<GDPRFeature, Integer> mIdMap = new HashMap<>();

        public FeatureAdapter(List<GDPRFeature> data, Context context) {
            super(context, R.layout.gdpr_list_element, R.id.featuretext, data);
            for (int i = 0; i < data.size(); ++i) {
                mIdMap.put(data.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            GDPRFeature item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }


}
