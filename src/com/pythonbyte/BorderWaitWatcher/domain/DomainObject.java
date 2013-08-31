package com.pythonbyte.BorderWaitWatcher.domain;

import com.j256.ormlite.field.DatabaseField;

public class DomainObject {
    @DatabaseField( generatedId=true )
    protected long id;

    /* Properties */
    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }
}
