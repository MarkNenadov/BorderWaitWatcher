package com.pythonbyte.BorderWaitWatcher.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.pythonbyte.BorderWaitWatcher.domain.Country;
import com.pythonbyte.BorderWaitWatcher.domain.BorderLocation;
import com.pythonbyte.BorderWaitWatcher.helpers.ExceptionHelpers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    final static int DB_VERSION = 1;
    final static String DB_NAME = "borderwaitwatcher.sqlite";
    Context context;

    private Dao<BorderLocation, Integer> borderLocationDao = null;
    private Dao<Country, Integer> countryDao = null;

    public DatabaseHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate( SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource ) {
        try {
            createDatabaseTables();
        } catch ( SQLException e ) {
            Log.e(DatabaseHelper.class.getName(), "Can't create the database", e);
            throw new RuntimeException( e );
        }

        try {
            createOrUpdateCountries();
            createOrUpdateBorderLocations( getInitialBorderLocations() );
        } catch ( Exception e) {
        }
    }

    private void createDatabaseTables() throws SQLException {
        TableUtils.createTable( connectionSource, BorderLocation.class );
        TableUtils.createTable( connectionSource, Country.class );
    }

    private void createOrUpdateCountries() throws SQLException {
        Country canada = Country.CANADA;
        Country usa = Country.USA;

        getCountryDao().createOrUpdate( canada );
        getCountryDao().createOrUpdate( usa );
    }

    private void createOrUpdateBorderLocations( List<BorderLocation> borderLocations ) throws SQLException {
        for ( BorderLocation borderLocation: borderLocations ) {
            getBorderLocationDao().createOrUpdate( borderLocation );
        }
    }

    private List<BorderLocation> getInitialBorderLocations() {
        List<BorderLocation> locations = new ArrayList<BorderLocation>();

        locations.add( new BorderLocation( "Ambassador Bridge", "Windsor/Detroit", Country.CANADA ) );
        locations.add( new BorderLocation( "Detroit and Canada Tunnel", "Windsor/Detroit", Country.CANADA ) );
        locations.add( new BorderLocation( "Rainbow Bridge", "Niagara", Country.CANADA ) );
        locations.add( new BorderLocation( "Peace Bridge", "Buffalo", Country.CANADA ) );
        locations.add( new BorderLocation( "Queenston-Lewiston Bridge", "", Country.CANADA ) );
        locations.add( new BorderLocation( "Ambassador Bridge", "Detroit/Windsor", Country.USA, "380001" ) );
        locations.add( new BorderLocation( "Windsor Tunnel", "Detroit/Windsor", Country.USA, "380002" ) );
        locations.add( new BorderLocation( "Rainbow Bridge", "Niagara", Country.USA, "090102" ) );
        locations.add( new BorderLocation( "Peace Bridge", "Buffalo", Country.USA, "090101" ) );
        locations.add( new BorderLocation( "Queenston-Lewiston Bridge", "", Country.USA, "090104" ) );

        return locations;
    }

    @Override
    public void onUpgrade( SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion ) {
        //try {
        List<String> allSql = new ArrayList<String>();
        switch( oldVersion ) {
            case 1:
                //allSql.add( );
        }

        for ( String sql: allSql ) {
            sqLiteDatabase.execSQL( sql );
        }
        /*} catch ( SQLException e ) {
            Log.e( KuartzDatabaseHelper.class.getName(), "Can't create the database", e );
            throw new RuntimeException( e );
        }*/
    }

    public Dao<BorderLocation, Integer> getBorderLocationDao() {
        if ( borderLocationDao == null ) {
            try {
                borderLocationDao = getDao( BorderLocation.class );
            } catch ( SQLException e ) {
                ExceptionHelpers.printStackTrace(e);
            }
        }
        return borderLocationDao;
    }

    public Dao<Country, Integer> getCountryDao() {
        if ( countryDao == null ) {
            try {
                countryDao = getDao( Country.class );
            } catch ( SQLException e ) {
                ExceptionHelpers.printStackTrace(e);
            }
        }
        return countryDao;
    }

}
