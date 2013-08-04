package com.pythonbyte.BorderWaitWatcher;

public class Country {
    public static Country CANADA = new Country( "Canada", "CA" );
    public static Country USA = new Country( "United States", "US" );

    private String name = "";
    private String code = "";

    /* Constructors */
    public Country( String name, String code ) {
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
