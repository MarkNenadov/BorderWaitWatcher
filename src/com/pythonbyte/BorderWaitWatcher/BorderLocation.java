package com.pythonbyte.BorderWaitWatcher;

public class BorderLocation {
    private String title = "";
    private String subTitle = "";
    private String waitTime = "";
    private Country country;
    private String portId;

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

