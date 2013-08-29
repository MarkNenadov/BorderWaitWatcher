package com.pythonbyte.BorderWaitWatcher.database;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.pythonbyte.BorderWaitWatcher.domain.Country;
import com.pythonbyte.BorderWaitWatcher.domain.BorderLocation;
import com.pythonbyte.BorderWaitWatcher.helpers.ExceptionHelpers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorderLocationManager {
    DatabaseHelper helper;

    static private BorderLocationManager instance;

    static public void init( Context context ) {
        if ( instance == null ) {
            instance = new BorderLocationManager( context );
        }
    }

    static public BorderLocationManager getInstance() {
        return instance;
    }

    private BorderLocationManager( Context context ) {
        helper = new DatabaseHelper( context );
    }

    public DatabaseHelper getHelper() {
        return helper;
    }

    public Dao<BorderLocation, Integer> getDao() {
        return helper.getBorderLocationDao();
    }

    /* Service methods */
    public List<BorderLocation> getAllBorderLocationsByCountry( Country country ) {
        List<BorderLocation> borderLocations = new ArrayList<BorderLocation>();

        try {
            QueryMap queryMap = new QueryMap();
            queryMap.addProperty( "country", country );

            borderLocations = getHelper().getBorderLocationDao().queryForFieldValues( queryMap.getProperties() );
        } catch ( SQLException e ) {
            ExceptionHelpers.printStackTrace( e );
        }

        return borderLocations;
    }

}
