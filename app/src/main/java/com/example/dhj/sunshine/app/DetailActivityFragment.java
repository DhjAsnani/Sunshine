package com.example.dhj.sunshine.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private static final String FORECAST_SHARE_HASHTAG = "#SunshineApp";
    private String mForecastStr;
    public DetailActivityFragment() {
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider mshareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        if(mshareActionProvider!=null)
        {
            mshareActionProvider.setShareIntent(createShareForecastIntent());
        }
        else {
            Log.d(LOG_TAG,"muh");
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.v("Kamina","kamina");
        Intent intent = getActivity().getIntent();
        View rootview =  inflater.inflate(R.layout.fragment_detail, container, false);
        TextView t = (TextView) rootview.findViewById(R.id.detail_text);
        if(intent!=null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            mForecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            t.setText(mForecastStr);
            //t.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
        return rootview;

}
private Intent createShareForecastIntent(){
Intent shareIntent = new Intent(Intent.ACTION_SEND);
shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_TEXT,mForecastStr+FORECAST_SHARE_HASHTAG);
    return shareIntent;
}
}
