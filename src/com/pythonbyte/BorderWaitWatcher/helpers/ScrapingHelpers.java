package com.pythonbyte.BorderWaitWatcher.helpers;

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
    private static String CANADIAN_GOVERNMENT_BORDER_URL = "http://www.cbsa-asfc.gc.ca/bwt-taf/menu-eng.html";
    private static String US_GOVERNMENT_BORDER_URL_PREFIX = "http://apps.cbp.gov/bwt/customize_rss.asp?portList=";
    private static String US_GOVERNMENT_BORDER_URL_SUFFIX = "&lane=pov&action=rss&f=csv";

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
        Document borderWaitTimePageDocument = Jsoup.connect( CANADIAN_GOVERNMENT_BORDER_URL ).get();
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

                if ( officeTdBold.text().equals( borderLocation.getTitle() ) ) {
                    borderLocation.setWaitTime( rowElement.select( "td[headers=Trav TravCanada]" ).first().text() );
                }
            }
        }
    }

    public static List<BorderLocation> loadWaitTimesIntoBorderLocationsFromUnitedStatesGovernmentUrl( List<BorderLocation> borderLocations ) {
        for ( BorderLocation borderLocation: borderLocations ) {
            String feedUrl = US_GOVERNMENT_BORDER_URL_PREFIX + borderLocation.getPortId() + US_GOVERNMENT_BORDER_URL_SUFFIX;
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
