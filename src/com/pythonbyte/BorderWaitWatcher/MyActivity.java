package com.pythonbyte.BorderWaitWatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.pythonbyte.BorderWaitWatcher.database.BorderLocationManager;
import com.pythonbyte.BorderWaitWatcher.domain.BorderLocation;
import com.pythonbyte.BorderWaitWatcher.domain.Country;
import com.pythonbyte.BorderWaitWatcher.helpers.ScrapingHelpers;
import com.pythonbyte.BorderWaitWatcher.helpers.ViewHelpers;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        refresh( null );
    }

    public void refresh( View view ) {
        BorderLocationManager.init( this );
        BorderLocationManager borderLocationManager = BorderLocationManager.getInstance();

        List<BorderLocation> canadianBorderLocations = borderLocationManager.getAllBorderLocationsByCountry( Country.CANADA );

        canadianBorderLocations = ScrapingHelpers.loadWaitTimesIntoBorderLocationsFromCanadianGovernmentUrl( canadianBorderLocations );
        String canadaText = ViewHelpers.getPresentationTextFromBorderLocationsList( canadianBorderLocations );

        List<BorderLocation> americanBorderLocations = borderLocationManager.getAllBorderLocationsByCountry( Country.USA );

        americanBorderLocations = ScrapingHelpers.loadWaitTimesIntoBorderLocationsFromUnitedStatesGovernmentUrl( americanBorderLocations );
        String americanText = ViewHelpers.getPresentationTextFromBorderLocationsList( americanBorderLocations );

        TextView text = (TextView) findViewById( R.id.level1 );
        text.setText( "Developed by Mark Nenadov, 2013\n\n" + canadaText ) ;
        TextView text2 = (TextView) findViewById( R.id.level2 );
        text2.setText( americanText ) ;
    }
}
