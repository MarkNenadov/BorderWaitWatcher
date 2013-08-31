package com.pythonbyte.BorderWaitWatcher.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Country extends DomainObject {
    public static Country CANADA = new Country( 1, "Canada", "CA" );
    public static Country USA = new Country( 2, "United States", "US" );

    @DatabaseField
    private String name = "";

    @DatabaseField
    private String code = "";

    /* Constructors */
    public Country() {
    }

    public Country( long id, String name, String code ) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    /* Getters/Setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
