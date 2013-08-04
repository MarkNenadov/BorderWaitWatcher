package com.pythonbyte.BorderWaitWatcher.helpers;

import com.pythonbyte.BorderWaitWatcher.BorderLocation;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.List;

public class ScrapingHelpers {
    private static String CANADIAN_GOVERNMENT_BORDER_URL = "http://www.cbsa-asfc.gc.ca/bwt-taf/menu-eng.html";

    public static List<BorderLocation> loadWaitTimesIntoBorderLocationsFromCanadianGovernmentUrl(List<BorderLocation> borderLocations) {
        try {
            Document borderWaitTimePageDocument = Jsoup.connect( CANADIAN_GOVERNMENT_BORDER_URL ).get();
            Element borderWaitTimeTable = borderWaitTimePageDocument.select( "table[class=bwt]" ).first();

            Elements borderWaitTimeTableRows = borderWaitTimeTable.select( "tr" );

            for ( BorderLocation borderLocation: borderLocations ) {
                for ( Element rowElement: borderWaitTimeTableRows ) {
                    if ( rowElement.select( "td[headers=Office]" ).first() != null ) {
                        Element officeTdBold = rowElement.select( "td[headers=Office]" ).first().select( "b" ).first();

                        if ( officeTdBold.text().equals( borderLocation.getTitle() ) ) {
                            borderLocation.setWaitTime( rowElement.select( "td[headers=Trav TravCanada]" ).first().text() );
                        }
                    }
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return borderLocations;
    }

    public static List<BorderLocation> loadWaitTimesIntoBorderLocationsFromUnitedStatesGovernmentUrl(List<BorderLocation> borderLocations) {
        for ( BorderLocation borderLocation: borderLocations ) {
            String feedUrl = "http://apps.cbp.gov/bwt/customize_rss.asp?portList=" + borderLocation.getPortId() + "&lane=pov&action=rss&f=csv";
            String description = "Unknown";

            RssParser rss = new RssParser();

            try {
                RssFeed feed = rss.load( feedUrl );

                description = feed.getItems().get( 0 ).getDescription();
                description = description.split( ", " )[1];
                description = description.split( " " )[0] + " " + description.split( " " )[1];
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            borderLocation.setWaitTime(description);
        }

        return borderLocations;
    }

}
