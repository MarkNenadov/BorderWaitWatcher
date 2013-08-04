package com.pythonbyte.BorderWaitWatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
        List<BorderLocation> canadianBorderLocations = new ArrayList<BorderLocation>();
        canadianBorderLocations.add( new BorderLocation( "Ambassador Bridge", "Windsor/Detroit", Country.CANADA ) );
        canadianBorderLocations.add( new BorderLocation( "Detroit and Canada Tunnel", "Windsor/Detroit", Country.CANADA ) );
        canadianBorderLocations.add( new BorderLocation( "Rainbow Bridge", "Niagara", Country.CANADA ) );
        canadianBorderLocations.add( new BorderLocation( "Peace Bridge", "Buffalo", Country.CANADA ) );
        canadianBorderLocations.add( new BorderLocation( "Queenston-Lewiston Bridge", "", Country.CANADA ) );

        canadianBorderLocations = ScrapingHelpers.loadWaitTimesIntoBorderLocationsFromCanadianGovernmentUrl( canadianBorderLocations);
        String canadaText = ViewHelpers.getPresentationTextFromBorderLocationsList( canadianBorderLocations );

        List<BorderLocation> americanBorderLocations = new ArrayList<BorderLocation>();
        americanBorderLocations.add( new BorderLocation( "Ambassador Bridge", "Detroit/Windsor", Country.USA, "380001" ) );
        americanBorderLocations.add( new BorderLocation( "Windsor Tunnel", "Detroit/Windsor", Country.USA, "380002" ) );
        americanBorderLocations.add( new BorderLocation( "Rainbow Bridge", "Niagara", Country.USA, "090102" ) );
        americanBorderLocations.add( new BorderLocation( "Peace Bridge", "Buffalo", Country.USA, "090101" ) );
        americanBorderLocations.add( new BorderLocation( "Queenston-Lewiston Bridge", "", Country.USA, "090104" ) );

        americanBorderLocations = ScrapingHelpers.loadWaitTimesIntoBorderLocationsFromUnitedStatesGovernmentUrl( americanBorderLocations );
        String americanText = ViewHelpers.getPresentationTextFromBorderLocationsList( americanBorderLocations );

        TextView text = (TextView) findViewById( R.id.level1 );
        text.setText( "Developed by Mark Nenadov, 2013\n\n" + canadaText ) ;
        TextView text2 = (TextView) findViewById( R.id.level2 );
        text2.setText( americanText ) ;
    }
}
