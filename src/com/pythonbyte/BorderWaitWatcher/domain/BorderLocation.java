package com.pythonbyte.BorderWaitWatcher.domain;

import com.j256.ormlite.field.DatabaseField;

public class BorderLocation extends DomainObject {
    @DatabaseField
    private String title = "";

    @DatabaseField
    private String subTitle = "";

    @DatabaseField
    private String waitTime = "";

    @DatabaseField( foreign=true, foreignColumnName="id", foreignAutoRefresh=true )
    private Country country;

    @DatabaseField
    private String portId;

    public BorderLocation() {
    }

    public BorderLocation( String title, String subTitle, Country country ) {
        this.title = title;
        this.subTitle = subTitle;
        this.country = country;
    }

    public BorderLocation( String title, String subTitle, Country country, String portId ) {
        this.title = title;
        this.subTitle = subTitle;
        this.country = country;
        this.portId = portId;
    }

    /* Getters/Setters */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }
}

