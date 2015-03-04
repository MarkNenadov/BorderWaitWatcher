package com.pythonbyte.BorderWaitWatcher.helpers;

import android.util.Log;
import com.pythonbyte.BorderWaitWatcher.BorderWaitWatcherConfiguration;
import com.pythonbyte.BorderWaitWatcher.domain.BorderLocation;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssItemBean;
import org.horrabin.horrorss.RssParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class ScrapingHelpers {
    public static List<BorderLocation> loadWaitTimesIntoBorderLocationsFromCanadianGovernmentUrl(List<BorderLocation> borderLocations) {
        try {
            Elements borderWaitTimeTableRows = getBorderWaitTimeRows();

            loadBorderLocationsFromWaitTimeTableRows( borderLocations, borderWaitTimeTableRows );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return borderLocations;
    }

    private static Elements getBorderWaitTimeRows() throws Exception {
        Document borderWaitTimePageDocument = Jsoup.connect( BorderWaitWatcherConfiguration.CANADIAN_GOVERNMENT_BORDER_URL ).get();
        Element borderWaitTimeTable = borderWaitTimePageDocument.select( "table[class=bwt]" ).first();
        return borderWaitTimeTable.select( "tr" );
    }

    private static void loadBorderLocationsFromWaitTimeTableRows(List<BorderLocation> borderLocations, Elements borderWaitTimeTableRows) {
        for ( BorderLocation borderLocation: borderLocations ) {
            loadBorderLocationFromWaitTimeTableRows( borderLocation, borderWaitTimeTableRows );
        }
    }

    private static void loadBorderLocationFromWaitTimeTableRows(BorderLocation borderLocation, Elements borderWaitTimeTableRows) {
        for ( Element rowElement: borderWaitTimeTableRows ) {
            if ( rowElement.select( "td[headers=Office]" ).first() != null ) {
                Element officeTdBold = rowElement.select( "td[headers=Office]" ).first().select( "b" ).first();

                if ( borderLocation.getTitle() != null && officeTdBold.text().trim().equals( borderLocation.getTitle().trim() ) ) {
                    borderLocation.setWaitTime( rowElement.select( "td[headers=Trav TravCanada]" ).first().text() );
                }
            }
        }
    }

    public static List<BorderLocation> loadWaitTimesIntoBorderLocationsFromUnitedStatesGovernmentUrl( List<BorderLocation> borderLocations ) {
        for ( BorderLocation borderLocation: borderLocations ) {
            String feedUrl = BorderWaitWatcherConfiguration.US_GOVERNMENT_BORDER_URL_PREFIX + borderLocation.getPortId() + BorderWaitWatcherConfiguration.US_GOVERNMENT_BORDER_URL_SUFFIX;
            String description = "Unknown";

            RssParser rss = new RssParser();

            try {
                RssFeed feed = rss.load( feedUrl );

                description = parseWaitTimeDescription( feed.getItems().get( 0 ) );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            borderLocation.setWaitTime(description);
        }

        return borderLocations;
    }

    private static String parseWaitTimeDescription( RssItemBean rssItemBean ) {
        String description = rssItemBean.getDescription();
        description = description.split( ", " )[1];
        description = description.split( " " )[0] + " " + description.split( " " )[1];

        return description;
    }

}
