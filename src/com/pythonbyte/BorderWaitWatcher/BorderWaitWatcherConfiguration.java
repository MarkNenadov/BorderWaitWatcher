package com.pythonbyte.BorderWaitWatcher;

public class BorderWaitWatcherConfiguration {
    public static boolean PRINT_STACKTRACES = true;
    public static String ATTRIBUTION_TEXT = "Developed by Mark Nenadov, 2013-2015";

    public static String CANADIAN_GOVERNMENT_BORDER_URL = "http://www.cbsa-asfc.gc.ca/bwt-taf/menu-eng.html";
    public static String US_GOVERNMENT_BORDER_URL_PREFIX = "http://apps.cbp.gov/bwt/customize_rss.asp?portList=";
    public static String US_GOVERNMENT_BORDER_URL_SUFFIX = "&lane=pov&action=rss&f=csv";

    public final static String DB_NAME = "borderwaitwatcher.sqlite";
    public final static int DB_VERSION = 1;
}
