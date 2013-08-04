package com.pythonbyte.BorderWaitWatcher.helpers;

import com.pythonbyte.BorderWaitWatcher.BorderLocation;

import java.util.List;

public class ViewHelpers {

    public static String getPresentationTextFromBorderLocationsList(List<BorderLocation> borderLocations) {
        String text = "Into " + borderLocations.get( 0 ).getCountry().getName() + ":\n\n";
        for ( BorderLocation borderLocation: borderLocations ) {
            text += borderLocation.getTitle();

            if ( !"".equals(borderLocation.getSubTitle()) ) {
                text += " (" + borderLocation.getSubTitle() + ")";
            }

            text += ": " + borderLocation.getWaitTime() + "\n";
        }
        return text;
    }
}
