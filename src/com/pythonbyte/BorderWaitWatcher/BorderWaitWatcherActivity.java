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

public class BorderWaitWatcherActivity extends Activity {
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        refresh( null );
    }

    public void refresh( View view ) {
        BorderLocationManager.init( this );
        BorderLocationManager borderLocationManager = BorderLocationManager.getInstance();

        TextView text = (TextView) findViewById( R.id.level1 );
        text.setText( BorderWaitWatcherConfiguration.ATTRIBUTION_TEXT + "\n\n" + getCanadianText( borderLocationManager ) ) ;
        TextView text2 = (TextView) findViewById( R.id.level2 );
        text2.setText( getAmericanText( borderLocationManager ) ) ;
    }

    private String getCanadianText( BorderLocationManager borderLocationManager ) {
        List<BorderLocation> canadianBorderLocations = borderLocationManager.getAllBorderLocationsByCountry( Country.CANADA );
        canadianBorderLocations = ScrapingHelpers.loadWaitTimesIntoBorderLocationsFromCanadianGovernmentUrl( canadianBorderLocations );

        return ViewHelpers.getPresentationTextFromBorderLocationsList( canadianBorderLocations );

    }

    private String getAmericanText(BorderLocationManager borderLocationManager) {
        List<BorderLocation> americanBorderLocations = borderLocationManager.getAllBorderLocationsByCountry( Country.USA );
        americanBorderLocations = ScrapingHelpers.loadWaitTimesIntoBorderLocationsFromUnitedStatesGovernmentUrl( americanBorderLocations );

        return ViewHelpers.getPresentationTextFromBorderLocationsList( americanBorderLocations );
    }
}