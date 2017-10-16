package com.eekrupin.votinglunch.to;

import com.eekrupin.votinglunch.HasId;

public abstract class BaseTo implements HasId {
    protected Integer id;

    protected Boolean deletionMark;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDeletionMark() {
        return deletionMark;
    }

    public void setDeletionMark(Boolean deletionMark) {
        this.deletionMark = deletionMark;
    }

}
