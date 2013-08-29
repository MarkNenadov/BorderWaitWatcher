package com.pythonbyte.BorderWaitWatcher.database;

import com.pythonbyte.BorderWaitWatcher.domain.Country;

import java.util.HashMap;

public class QueryMap {
    private HashMap properties = new HashMap<String, String>();

    public void addProperty( String propertyName, String propertyValue ) {
        if ( propertyValue != null && !"".equals( propertyValue ) ) {
            properties.put( propertyName, propertyValue );
        }
    }

    public void addProperty( String propertyName, Country propertyValue ) {
        if ( propertyValue != null ) {
            properties.put( propertyName, propertyValue );
        }
    }

    public HashMap getProperties() {
        return properties;
    }
}
