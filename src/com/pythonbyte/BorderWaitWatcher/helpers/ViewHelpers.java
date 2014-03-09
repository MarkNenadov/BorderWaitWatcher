package com.pythonbyte.BorderWaitWatcher.helpers;

import com.pythonbyte.BorderWaitWatcher.domain.BorderLocation;

import java.util.List;

public class ViewHelpers {
    public static String getPresentationTextFromBorderLocationsList( List<BorderLocation> borderLocations ) {
        String text = "Into " + borderLocations.get( 0 ).getCountry().getName() + ":\n\n";
        for ( BorderLocation borderLocation: borderLocations ) {
            text += getPresentationTextFromBorderLocation( borderLocation );
        }
        return text;
    }

    private static String getPresentationTextFromBorderLocation(BorderLocation borderLocation) {
        String newText = borderLocation.getTitle();

        if ( !"".equals( borderLocation.getSubTitle() ) ) {
            newText += " (" + borderLocation.getSubTitle() + ")";
        }

        newText += ": " + borderLocation.getWaitTime() + "\n";

        return newText;
    }
}
